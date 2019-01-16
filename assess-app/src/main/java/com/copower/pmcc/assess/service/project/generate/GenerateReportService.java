package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportRecordVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

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
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private BaseReportFieldService baseReportFieldService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseReportService baseReportService;

    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId){
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
                BeanUtils.copyProperties(declareRecord,generateReportRecord);
                generateReportRecord.setProjectId(projectId);
                generateReportRecord.setPlanId(planId);
                generateReportDao.addGenerateReportRecord(generateReportRecord);
            }
        }
    }

    /**
     * 获取报告记录信息
     * @param projectId
     * @param planId
     * @return
     */
    public List<GenerateReportRecordVo> getGenerateReportRecordList(Integer projectId, Integer planId){
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        List<GenerateReportRecordVo> voList= Lists.newArrayList();
        return LangUtils.transform(declareRecords, p -> {
            GenerateReportRecordVo generateReportRecordVo = new GenerateReportRecordVo();
            BeanUtils.copyProperties(p,generateReportRecordVo);
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
        ProjectInfoVo projectInfo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlan.getProjectId()));
        if (projectInfo == null) {
            return null;
        }
        List<String> paths = Lists.newArrayList();
        for (String string : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(string));
            if (baseDataDic != null) {
                if (baseDataDic.getFieldName().equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT)) {
                    BaseReportTemplate baseReportTemplate = baseReportService.getReportTemplate(projectInfo.getId(), baseDataDic.getId());
                    if (baseReportTemplate != null) {
                        //获取替换后得报告文件路径
                        String path = this.fullReportPath(baseReportTemplate, areaId, projectInfo, projectPlan);
                        if (StringUtils.isNotBlank(path)) {
                            paths.add(path);
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
     * @param projectInfo
     * @param projectPlan
     * @return
     * @throws Exception
     */
    private String fullReportPath(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectInfoVo projectInfo, ProjectPlan projectPlan) throws Exception {
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
            Set<Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = getReportMap(baseReportTemplate, areaId, projectInfo, projectPlan);
            if (CollectionUtils.isNotEmpty(mapSet)) {
                Iterator<Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>> iterator = mapSet.iterator();
                while (iterator.hasNext()) {
                    //这一层实际只有一个值
                    Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>> mapMap = iterator.next();
                    Iterator<Map.Entry<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>> entryIterator = mapMap.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>> mapEntry = entryIterator.next();
                        Map<BaseReportFieldReplaceEnum, Object> baseReportFieldReplaceEnumObjectMap = mapEntry.getValue();
                        BaseReportField baseReportField = mapEntry.getKey();
                        Iterator<Map.Entry<BaseReportFieldReplaceEnum, Object>> it = baseReportFieldReplaceEnumObjectMap.entrySet().iterator();
                        //最终要得这一层
                        while (it.hasNext()) {
                            Map.Entry<BaseReportFieldReplaceEnum, Object> enumObjectEntry = it.next();
                            BaseReportFieldReplaceEnum replaceEnum = enumObjectEntry.getKey();
                            Object value = enumObjectEntry.getValue();
                            //文本(字符串)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT.getKey())) {
                                textMap.put(baseReportField.getFieldName(), value.toString());
                            }
                            //自定义(书签)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.OTHER.getKey())) {
                                bookmarkMap.put(baseReportField.getRemark(), value.toString());
                            }
                            //附件(子模板)
                            if (com.google.common.base.Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE.getKey())) {
                                //暂时不做处理
                            }
                        }
                    }
                }
            }
            AsposeUtils.replaceBookmark(tempDir, bookmarkMap);
            AsposeUtils.replaceText(tempDir, textMap);
        }
        return tempDir;
    }

    /**
     * 获取报告模板数据数据
     *
     * @param baseReportTemplate
     * @param areaId
     * @param projectInfo
     * @param projectPlan
     * @return 如:文号,<BaseReportFieldReplaceEnum,四川协和预评（2019）0001号>
     * @throws Exception
     */
    private Set<Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>> getReportMap(BaseReportTemplate baseReportTemplate, Integer areaId, ProjectInfoVo projectInfo, ProjectPlan projectPlan) throws Exception {
        Set<Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>> mapSet = Sets.newHashSet();
        GenerateBaseDataService generateBaseDataService = new GenerateBaseDataService(projectPlan.getProjectId(),areaId,baseReportTemplate.getId());
        List<BaseReportField> fieldList = baseReportFieldService.getDataDicList(BaseReportFieldEnum.GROUP.getKey());
        for (BaseReportField baseReportField : fieldList) {
            if (com.google.common.base.Objects.equal(BaseReportFieldEnum.REPORTNUMBER.getName(), baseReportField.getName())) {
                //文号
                mapSet.add(getBaseReportFieldReplaceEnumMap(
                        BaseReportFieldReplaceEnum.getEnumByName(baseReportField.getReplaceType()),
                        baseReportField,
                        generateBaseDataService.getWordNumber()));
            }
            if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), baseReportField.getName())) {
                //委托人
                mapSet.add(getBaseReportFieldReplaceEnumMap(
                        BaseReportFieldReplaceEnum.getEnumByName(baseReportField.getReplaceType()),
                        baseReportField,
                        generateBaseDataService.getPrincipal()));
            }
        }
        return mapSet;
    }

    /**
     * 报告模板map组装值
     *
     * @param baseReportFieldReplaceEnum
     * @param baseReportField
     * @param value
     * @return
     */
    private Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>> getBaseReportFieldReplaceEnumMap(BaseReportFieldReplaceEnum baseReportFieldReplaceEnum, BaseReportField baseReportField, Object value) {
        Map<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>> stringEntryMap = new HashMap<BaseReportField, Map<BaseReportFieldReplaceEnum, Object>>(1);
        Map<BaseReportFieldReplaceEnum, Object> enumObjectMap = new HashMap<BaseReportFieldReplaceEnum, Object>(1);
        enumObjectMap.put(baseReportFieldReplaceEnum, value);
        stringEntryMap.put(baseReportField, enumObjectMap);
        return stringEntryMap;
    }

}
