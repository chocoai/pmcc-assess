package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportRecordVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-23.
 */
@Service
public class GenerateReportService {
    @Autowired
    private GenerateReportDao generateReportDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseReportFieldService baseReportFieldService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseReportService baseReportService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        return schemeAreaGroupService.getAreaGroupList(projectId);
    }

    /**
     * 初始化用于生成报告记录信息
     *
     * @param projectId
     * @param planId
     */
    @Transactional(rollbackFor = {Exception.class})
    public void initGenerateReportRecord(Integer projectId, Integer planId) {
        int count = generateReportDao.getGenerateReportRecordCount(projectId, planId);
        if (count > 0) {
            return;
        }

        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            GenerateReportRecord generateReportRecord = null;
            for (DeclareRecord declareRecord : declareRecords) {
                generateReportRecord = new GenerateReportRecord();
                BeanUtils.copyProperties(declareRecord, generateReportRecord);
                generateReportRecord.setProjectId(projectId);
                generateReportRecord.setPlanId(planId);
                generateReportDao.addGenerateReportRecord(generateReportRecord);
            }
        }
    }

    /**
     * 获取报告记录信息
     *
     * @param projectId
     * @param planId
     * @return
     */
    public List<GenerateReportRecordVo> getGenerateReportRecordList(Integer projectId, Integer planId) {
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        List<GenerateReportRecordVo> voList = Lists.newArrayList();
        return LangUtils.transform(declareRecords, p -> {
            GenerateReportRecordVo generateReportRecordVo = new GenerateReportRecordVo();
            BeanUtils.copyProperties(p, generateReportRecordVo);
            return generateReportRecordVo;
        });
    }

    /**
     * 创建报告模板
     *
     * @param ids
     * @param projectPlanId
     * @param areaId
     * @return
     * @throws Exception
     */
    public Integer createReportWord(String ids, Integer projectPlanId, Integer areaId) throws Exception {
        if (StringUtils.isEmpty(ids) || projectPlanId == null) {
            return null;
        }
        String[] strings = ids.split(",");
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanId);
        if (projectPlan == null) {
            return null;
        }
        List<String> paths = Lists.newArrayList();
        for (String string : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(string));
            if (baseDataDic != null) {
                //预评报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        //获取替换后得报告文件路径 ==>
                        String path = this.fullReportPath(baseReportTemplate, areaId, projectPlan, AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
                        if (StringUtils.isNotBlank(path)) {
                            String localPath = baseAttachmentService.createTempDirPath(UUID.randomUUID().toString());
                            File file = new File(String.format("%s\\预评报告%s%s", localPath, UUID.randomUUID().toString(), ".doc"));
                            org.apache.commons.io.FileUtils.copyFile(new File(path), file);
                            paths.add(file.getCanonicalPath());
                        }
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, areaId, projectPlan, AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
                        if (StringUtils.isNotBlank(path)) {
                            String localPath = baseAttachmentService.createTempDirPath(UUID.randomUUID().toString());
                            File file = new File(String.format("%s\\技术报告%s%s", localPath, UUID.randomUUID().toString(), ".doc"));
                            org.apache.commons.io.FileUtils.copyFile(new File(path), file);
                            paths.add(file.getCanonicalPath());
                        }
                    }
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectPlan.getProjectId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        String path = this.fullReportPath(baseReportTemplate, areaId, projectPlan, AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
                        if (StringUtils.isNotBlank(path)) {
                            String localPath = baseAttachmentService.createTempDirPath(UUID.randomUUID().toString());
                            File file = new File(String.format("%s\\结果报告%s%s", localPath, UUID.randomUUID().toString(), ".doc"));
                            org.apache.commons.io.FileUtils.copyFile(new File(path), file);
                            paths.add(file.getCanonicalPath());
                        }
                    }
                }
            }
        }
        Integer sysAttachmentId = createSysAttachment(paths);
        return sysAttachmentId;
    }

    /**
     * 上传到erp形成附件id
     *
     * @param paths
     * @return
     */
    private Integer createSysAttachment(List<String> paths) throws Exception {
        if (CollectionUtils.isEmpty(paths)) {
            return null;
        }
        File[] srcFile = new File[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            srcFile[i] = new File(paths.get(i));
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setFileExtension("zip");
        //创建本地临时目录
        String localPath = baseAttachmentService.createTempDirPath(UUID.randomUUID().toString());
        File zipFile = new File(String.format("%s\\报告模板%s%s", localPath, UUID.randomUUID().toString(), ".zip"));
        FileUtils.zipFiles(srcFile, zipFile);
        sysAttachmentDto.setCreater(processControllerComponent.getThisUser());
        sysAttachmentDto.setFileSize(new Long(zipFile.length()).toString());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setFileName(zipFile.getName().substring(0, zipFile.getName().lastIndexOf(".")));
        String ftpBasePath = String.format("%s/%s/%s/%s", baseAttachmentService.createFTPBasePath(), DateUtils.format(new Date(), "yyyy-MM-dd"), processControllerComponent.getThisUser(), UUID.randomUUID().toString());
        String ftpFileName = baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(ftpFileName);
        ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(zipFile.getPath()), ftpFileName);
        baseAttachmentService.addAttachment(sysAttachmentDto);
        Integer id = sysAttachmentDto.getId();
        return id;
    }

    /**
     * 创建报告模板(具体)
     *
     * @param baseReportTemplate
     * @param areaId
     * @param projectPlan
     * @return
     * @throws Exception
     */
    private String fullReportPath(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectPlan projectPlan, String report_type) throws Exception {
        String tempDir = "";
        if (baseReportTemplate == null) {
            return "";
        }
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(baseReportTemplate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.get(0).getId());
            //文本map
            Map<String, String> textMap = Maps.newHashMap();
            //书签map
            Map<String, String> bookmarkMap = Maps.newHashMap();
            Map<String, String> fileFixedMap = Maps.newHashMap();
            Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = getReportMap(baseReportTemplate, areaId, projectPlan, new Document(tempDir), report_type);
            if (CollectionUtils.isNotEmpty(mapSet)) {
                Iterator<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> iterator = mapSet.iterator();
                while (iterator.hasNext()) {
                    //这一层实际只有一个值
                    Map<String, Map<BaseReportFieldReplaceEnum, Object>> mapMap = iterator.next();
                    Iterator<Map.Entry<String, Map<BaseReportFieldReplaceEnum, Object>>> entryIterator = mapMap.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, Map<BaseReportFieldReplaceEnum, Object>> mapEntry = entryIterator.next();
                        Map<BaseReportFieldReplaceEnum, Object> baseReportFieldReplaceEnumObjectMap = mapEntry.getValue();
                        String wordKey = mapEntry.getKey();
                        Iterator<Map.Entry<BaseReportFieldReplaceEnum, Object>> it = baseReportFieldReplaceEnumObjectMap.entrySet().iterator();
                        //最终要得这一层
                        while (it.hasNext()) {
                            Map.Entry<BaseReportFieldReplaceEnum, Object> enumObjectEntry = it.next();
                            BaseReportFieldReplaceEnum replaceEnum = enumObjectEntry.getKey();
                            Object value = enumObjectEntry.getValue();
                            //文本(字符串)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        textMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
                                }
                            }
                            //(书签)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        bookmarkMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
                                }
                            }
                            //附件(子模板)需要替换才能合并到目标文档 (如配置好的模板)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        String dir = value.toString();
                                        Document docDelete = new Document(dir);
                                        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(docDelete);
                                        if (bookmarkCollection.getCount() >= 1) {
                                            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                                                docDelete.getRange().getBookmarks().remove(bookmarkCollection.get(i).getName());
                                            }
                                            docDelete.save(dir);
                                        }
                                        fileFixedMap.put(wordKey, dir);
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //固定word (或者已经处理好了的)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE_FIXED.getKey())) {
                                if (value != null) {
                                    if (StringUtils.isNotBlank(value.toString())) {
                                        fileFixedMap.put(wordKey, value.toString());
                                    }
                                } else {
                                    logger.error(String.format("word模板:%s%s", wordKey, "替换失败!"), new Exception());
                                }
                            }
                        }
                    }
                }
            }
            if (!bookmarkMap.isEmpty()) {
                AsposeUtils.replaceBookmark(tempDir, bookmarkMap, false);
            }
            if (!textMap.isEmpty()) {
                AsposeUtils.replaceText(tempDir, textMap);
            }
            if (!fileFixedMap.isEmpty()) {
                AsposeUtils.replaceBookmarkToFile(tempDir, fileFixedMap);
            }
        }
        return tempDir;
    }


    /**
     * 获取报告模板数据数据
     *
     * @param baseReportTemplate
     * @param areaId
     * @param projectPlan
     * @param document
     * @return 如:文号,<文号,四川协和预评（2019）0001号>
     * @throws Exception
     */
    private Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> getReportMap(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectPlan projectPlan, Document document, String report_type) throws Exception {
        Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = Sets.newHashSet();
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectPlan.getProjectId(), areaId, baseReportTemplate.getId(), projectPlan);
        List<BaseReportField> fieldList = baseReportFieldService.query(new BaseReportField());
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);

        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                String bookmarkName = getChinese(bookmarkCollection.get(i).getName());
                /*##########################################公共书签替换 start ###################################################### */
                //文号
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.REPORTNUMBER.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.REPORTNUMBER.getName());
                    if (baseReportField != null) {
                        mapSet.add(getBaseReportFieldReplaceEnumMap(
                                BaseReportFieldReplaceEnum.BOOKMARK,
                                bookmarkCollection.get(i).getName(),
                                generateBaseDataService.getWordNumber()));
                    }
                }
                //报告出具日期
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.reportIssuanceDate.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.reportIssuanceDate.getName());
                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                            BaseReportFieldReplaceEnum.BOOKMARK,
                            bookmarkCollection.get(i).getName(),
                            DateUtils.format(generateBaseDataService.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN)));
                }
                //房地产总价
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.TotalRealEstatePrice.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.TotalRealEstatePrice.getName());
                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                            BaseReportFieldReplaceEnum.BOOKMARK,
                            bookmarkCollection.get(i).getName(),
                            generateBaseDataService.getTotalRealEstatePrice()));
                }
                //大写金额
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.CapitalizationAmount.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.CapitalizationAmount.getName());
                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                            BaseReportFieldReplaceEnum.BOOKMARK,
                            bookmarkCollection.get(i).getName(),
                            generateBaseDataService.getCapitalizationAmount()));
                }
                /*##########################################公共书签替换 end ###################################################### */
                switch (report_type) {
                    case AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT:
                        //区位
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.Location.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.Location.getName());
                            if (baseReportField != null) {
                                //由于区位以后可能会变,现在取权利人
                                if (false) {
                                    BaseReportFieldEnum.notPowerPerson.getName();
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getNotPowerPerson()));
                                }
                                if (true) {
                                    BaseReportFieldEnum.powerPerson.getName();
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getPowerPerson()));
                                }
                            }
                        }
                        //价值类型
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueType.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueType.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        StringUtils.isNotBlank(generateBaseDataService.getValueType()) ? generateBaseDataService.getValueType() : new Object()));

                            }
                        }
                        //价值定义
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.DefinitionValue.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.DefinitionValue.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        StringUtils.isNotBlank(generateBaseDataService.getDefinitionValue()) ? generateBaseDataService.getDefinitionValue() : new Object()));
                            }
                        }
                        //价值含义
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueImplication.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueImplication.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        StringUtils.isNotBlank(generateBaseDataService.getValueImplication()) ? generateBaseDataService.getValueImplication() : new Object()));
                            }
                        }
                        //设定用途
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SetUse.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SetUse.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        StringUtils.isNotBlank(generateBaseDataService.getSetUse()) ? generateBaseDataService.getSetUse() : new Object()));
                            }
                        }
                        //房产类型
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HouseType.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    StringUtils.isNotBlank(generateBaseDataService.getSetUse()) ? generateBaseDataService.getSetUse() : new Object()));
                        }
                        //土地实际用途
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandPracticalUse.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    StringUtils.isNotBlank(generateBaseDataService.getLandPracticalUse()) ? generateBaseDataService.getLandPracticalUse() : new Object()));
                        }
                        //使用权类型
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.UseRightType.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    StringUtils.isNotBlank(generateBaseDataService.getUseRightType()) ? generateBaseDataService.getUseRightType() : new Object()));
                        }
                        //评估面积
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.AssessArea.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.AssessArea.getName());
                            if (baseReportField != null) {
                                if (baseReportField != null) {
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getAssessArea().toString()));
                                }
                            }
                        }
                        //委托目的表述
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatementPurposeEntrustment.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatementPurposeEntrustment.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        generateBaseDataService.getStatementPurposeEntrustment()));
                            }
                        }
                        //评估方法
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethod.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethod.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.BOOKMARK,
                                        bookmarkCollection.get(i).getName(),
                                        generateBaseDataService.getEvaluationMethod()));
                            }
                        }
                        //土地他项权利情况
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.inventoryRight.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.inventoryRight.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getInventoryRight()));
                        }
                        //土地使用管制
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandUseControl.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandUseControl.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getLandUseControl()));
                        }
                        //出租或占用情况
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.rentalPossessionDesc.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.rentalPossessionDesc.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getRentalPossessionDesc()));
                        }
                        //价值时点
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePoint.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePoint.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getValueTimePoint()));
                        }
                        //价值时点说明
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueTimePointRemark.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueTimePointRemark.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getValueTimePointRemark()));
                        }
                        //估价技术思路
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationThink.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationThink.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getEvaluationThink()));
                        }
                        if (true) {
                            List<SchemeJudgeObject> schemeJudgeObjectList = generateBaseDataService.getSchemeJudgeObjectList();
                            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                                //分类评估单价
                                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGory.getName(), bookmarkName)) {
                                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGory.getName());
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getEvaluationPriceCateGoryOne()));
                                }
                                //分类评估面积
                                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationAreaCateGory.getName(), bookmarkName)) {
                                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationAreaCateGory.getName());
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getEvaluationAreaCateGoryOne()));
                                }
                                //分类评估总价
                                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName(), bookmarkName)) {
                                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationPriceCateGoryTotal.getName());
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.BOOKMARK,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getEvaluationPriceCateGoryTotalOne()));
                                }
                            }
                        }
                        //选择估价方法
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionValuationMethod.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionValuationMethod.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getSelectionValuationMethod()));
                        }
                        //分类评估单价计算式
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationExpression.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationExpression.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getEvaluationExpression()));
                        }
                        //分类评估方法结果
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.EvaluationMethodResult.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.EvaluationMethodResult.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getEvaluationMethodResult()));
                        }
                        //权重说明
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.WeightSpecification.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.WeightSpecification.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getWeightSpecification()));
                        }
                        //价值表达结果
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValueExpressionResult.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValueExpressionResult.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getValueExpressionResult()));
                        }
                        //法定优选受偿款
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatutoryOptimumReimbursement.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatutoryOptimumReimbursement.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getStatutoryOptimumReimbursement()));
                        }
                        //房屋所有权登记状况表
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.HousingOwnershipRegistrationStatementSheet.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.FILE_FIXED,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getHousingOwnershipRegistrationStatementSheet()));
                        }
                        //估价对象区位状况表
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectAreaStatusSheet.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.FILE_FIXED,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getJudgeObjectAreaStatusSheet()));
                        }
                        //估价土地实体状况表
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectLandStateSheet.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.FILE_FIXED,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getJudgeObjectLandStateSheet()));
                        }
                        //估价对象建筑实体状况表
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildLandStateSheet.getName(), bookmarkName)) {
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.FILE_FIXED,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getJudgeBuildLandStateSheet()));
                        }
                        break;
                    case AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY:
                        break;
                    case AssessDataDicKeyConstant.REPORT_TYPE_RESULT:
                        //估价项目名称
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ValuationProjectName.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ValuationProjectName.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.BOOKMARK,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getValuationProjectName()));
                        }
                        //估价结果一览表
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeBuildResultSurveySheet.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.judgeBuildResultSurveySheet.getName());
                            mapSet.add(getBaseReportFieldReplaceEnumMap(
                                    BaseReportFieldReplaceEnum.FILE_FIXED,
                                    bookmarkCollection.get(i).getName(),
                                    generateBaseDataService.getjudgeBuildResultSurveySheet()));
                        }
                        //计算过程
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.ComputationProcess.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.ComputationProcess.getName());
                            SysAttachmentDto query = new SysAttachmentDto();
                            if (baseReportField != null) {
                                query.setTableId(baseReportField.getId());
                                query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.FILE,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getComputationProcess(sysAttachmentDtoList.get(0))));
                                }
                            }
                        }
                        //参数选取与应用
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SelectionApplicationParameters.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.SelectionApplicationParameters.getName());
                            SysAttachmentDto query = new SysAttachmentDto();
                            if (baseReportField != null) {
                                query.setTableId(baseReportField.getId());
                                query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.FILE,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getSelectionApplicationParameters(sysAttachmentDtoList.get(0))));
                                }
                            }
                        }
                        //建筑物权益状况
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.StatusBuildingRightsInterests.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.StatusBuildingRightsInterests.getName());
                            SysAttachmentDto query = new SysAttachmentDto();
                            if (baseReportField != null) {
                                query.setTableId(baseReportField.getId());
                                query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.FILE,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.getStatusBuildingRightsInterests(sysAttachmentDtoList.get(0))));
                                }
                            }
                        }
                        //本次估价的总体思路和评估方法的选取
                        if (com.google.common.base.Objects.equal(BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName(), bookmarkName)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods.getName());
                            SysAttachmentDto query = new SysAttachmentDto();
                            if (baseReportField != null) {
                                query.setTableId(baseReportField.getId());
                                query.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class));
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                                            BaseReportFieldReplaceEnum.FILE,
                                            bookmarkCollection.get(i).getName(),
                                            generateBaseDataService.gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(sysAttachmentDtoList.get(0))));
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }

            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            /*##########################################公共字符替换 end ###################################################### */
            //暂无
            /*##########################################公共字符替换 start ###################################################### */
            switch (report_type) {
                case AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT:
                    for (String name : regexS) {
                        //委托人
                        if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), name)) {
                            BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.PRINCIPAL.getName());
                            if (baseReportField != null) {
                                mapSet.add(getBaseReportFieldReplaceEnumMap(
                                        BaseReportFieldReplaceEnum.TEXT,
                                        String.format("${%s}", name),
                                        generateBaseDataService.getPrincipal()));
                            }
                        }
                    }
                    break;
                case AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY:
                    break;
                case AssessDataDicKeyConstant.REPORT_TYPE_RESULT:
                    break;
                default:
                    break;
            }
        }
        return mapSet;
    }

    /**
     * 报告模板map组装值
     *
     * @param baseReportFieldReplaceEnum
     * @param wordKey
     * @param value
     * @return
     */
    private Map<String, Map<BaseReportFieldReplaceEnum, Object>> getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum baseReportFieldReplaceEnum, String wordKey, Object value) {
        Map<String, Map<BaseReportFieldReplaceEnum, Object>> stringEntryMap = new HashMap<String, Map<BaseReportFieldReplaceEnum, Object>>(1);
        Map<BaseReportFieldReplaceEnum, Object> enumObjectMap = new HashMap<BaseReportFieldReplaceEnum, Object>(1);
        enumObjectMap.put(baseReportFieldReplaceEnum, value);
        stringEntryMap.put(wordKey, enumObjectMap);
        return stringEntryMap;
    }

    private List<String> specialTreatment(List<String> strings) {
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(strings)) {
            for (String s : strings) {
                String temp = s.substring(2, s.length() - 1);
                stringList.add(temp);
            }
        }
        return stringList;
    }

    private BaseReportField whereBaseReportFieldByName(List<BaseReportField> baseReportFields, String name) {
        if (CollectionUtils.isNotEmpty(baseReportFields)) {
            for (BaseReportField baseReportField : baseReportFields) {
                if (Objects.equal(name, baseReportField.getName())) {
                    return baseReportField;
                }
            }
        }
        return null;
    }

    /**
     * 利用 ascii 码 配合正则 提取中文
     *
     * @param paramValue
     * @return
     */
    public String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }


}
