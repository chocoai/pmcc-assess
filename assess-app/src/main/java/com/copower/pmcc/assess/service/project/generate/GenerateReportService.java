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
                        String path = this.fullReportPath(baseReportTemplate, areaId, projectPlan);
                        if (StringUtils.isNotBlank(path)) {
                            paths.add(path);
                        }
                    }
                }
                //技术报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY)) {
                    //暂时未提供模板
                }
                //结果报告
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_RESULT)) {
                    //暂时未提供模板
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
        String localPath = baseAttachmentService.createTempDirPath(UUID.randomUUID().toString().substring(2, 9));
        File zipFile = new File(String.format("%s\\报告模板%s%s", localPath, UUID.randomUUID().toString().substring(1, 7), ".zip"));
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
    private String fullReportPath(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectPlan projectPlan) throws Exception {
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
            //模板map(模板map中为erp中得附件id以及替换值)
            Map<Integer, Object> fileMap = Maps.newHashMap();
            Map<String, String> fileFixedMap = Maps.newHashMap();
            Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = getReportMap(baseReportTemplate, areaId, projectPlan, new Document(tempDir));
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
                                    textMap.put(wordKey, value.toString());
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //(书签)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.BOOKMARK.getKey())) {
                                if (value != null) {
                                    bookmarkMap.put(wordKey, value.toString());
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //附件(子模板)需要替换才能合并到目标文档
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE.getKey())) {
                                if (value != null) {
                                    template(wordKey, value);
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                            //固定word
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE_FIXED.getKey())) {
                                if (value != null) {
                                    fileFixedMap.put(wordKey,value.toString());
                                } else {
                                    logger.error(String.format("word模板:%s%s", ChineseToPy.getFullSpell(wordKey), "替换失败!"), new Exception());
                                }
                            }
                        }
                    }
                }
            }
            AsposeUtils.replaceBookmark(tempDir, bookmarkMap, false);
            AsposeUtils.replaceText(tempDir, textMap);
            AsposeUtils.replaceBookmarkToFile(tempDir, fileFixedMap);
        }
        return tempDir;
    }

    private void template(String wordKey, Object value) {
        //暂时不做处理
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
    private Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> getReportMap(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectPlan projectPlan, Document document) throws Exception {
        Set<Map<String, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = Sets.newHashSet();
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectPlan.getProjectId(), areaId, baseReportTemplate.getId());
        List<BaseReportField> fieldList = baseReportFieldService.query(new BaseReportField());
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                String bookmarkName =  getChinese(bookmarkCollection.get(i).getName());
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
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.SetUse.getName(), bookmarkName)) {
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
                                    generateBaseDataService.getAssessArea().doubleValue()));
                        }
                    }
                }
                //使用权类型
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.UseRightType.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.UseRightType.getName());
                    if (baseReportField != null) {

                    }
                }
                //土地实际用途
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.LandPracticalUse.getName(), bookmarkName)) {
                    BaseReportField baseReportField = whereBaseReportFieldByName(fieldList, BaseReportFieldEnum.LandPracticalUse.getName());
                    if (baseReportField != null) {

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
                //估价对象区位状况表
                if (com.google.common.base.Objects.equal(BaseReportFieldEnum.judgeObjectAreaStatusSheet.getName(), bookmarkName)) {
                    //测试模板是否能够添加成功!
                    mapSet.add(getBaseReportFieldReplaceEnumMap(
                            BaseReportFieldReplaceEnum.FILE_FIXED,
                            bookmarkCollection.get(i).getName(),
                            generateBaseDataService.getJudgeObjectAreaStatusSheet2()));

//                    mapSet.add(getBaseReportFieldReplaceEnumMap(
//                            BaseReportFieldReplaceEnum.BOOKMARK,
//                            bookmarkCollection.get(i).getName(),
//                            generateBaseDataService.getJudgeObjectAreaStatusSheet()));
                }
            }
        }
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        if (CollectionUtils.isNotEmpty(regexS)) {
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
