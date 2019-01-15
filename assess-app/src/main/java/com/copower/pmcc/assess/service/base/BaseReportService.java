package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/5/21
 * @time: 18:36
 */
@Service
public class BaseReportService {
    @Autowired
    private BaseReportDao baseReportDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;
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


    //报告模板======================================================
    public void changeBaseReportTemplate(Integer id, Integer type) {
        if (type == 0) {
            baseReportDao.deleteBaseReportTemplate(id);
        } else {
            BaseReportTemplate baseReportTemplate = baseReportDao.getBaseReportTemplateById(id);
            baseReportTemplate.setBisEnable(true);
            baseReportDao.updateBaseReportTemplate(baseReportTemplate);
        }
    }

    public void updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportDao.updateBaseReportTemplate(baseReportTemplate);
    }

    public void addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportTemplate.setCreator(processControllerComponent.getThisUser());
        baseReportTemplate.setBisEnable(false);
        baseReportDao.addBaseReportTemplate(baseReportTemplate);
        //更新附件信息
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        baseAttachment.setTableId(0);
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
        baseAttachmentNew.setTableId(baseReportTemplate.getId());
        baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
    }

    public List<BaseReportTemplate> getBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        return baseReportTemplates;
    }

    public BootstrapTableVo getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, requestBaseParam.getSearch());
        List<BaseReportTemplateVo> transform = LangUtils.transform(baseReportTemplates, o -> getBaseReportTemplateVo(o));
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<BaseReportTemplateVo>());
        return bootstrapTableVo;
    }

    private BaseReportTemplateVo getBaseReportTemplateVo(BaseReportTemplate baseReportTemplate) {

        BaseReportTemplateVo baseReportTemplateVo = new BaseReportTemplateVo();
        BeanUtils.copyProperties(baseReportTemplate, baseReportTemplateVo);
        baseReportTemplateVo.setEntrustPurposeName(baseDataDicService.getNameById(baseReportTemplate.getEntrustPurpose()));

        List<SysAttachmentDto> baseAttachments = baseAttachmentService.getByField_tableId(baseReportTemplate.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        if (CollectionUtils.isNotEmpty(baseAttachments)) {
            List<String> report = LangUtils.transform(baseAttachments, o -> baseAttachmentService.getViewHtml(o));
            baseReportTemplateVo.setReport(report);
        }
        return baseReportTemplateVo;
    }

    /**
     * 根据条件查询报告模板
     *
     * @param projectId  项目id
     * @param reportType 报告类型
     * @return
     */
    public BaseReportTemplate getReportTemplate(Integer projectId, Integer reportType) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        if (projectInfo == null) {
            return null;
        }
        InitiateUnitInformationVo unitInformationVo = initiateUnitInformationService.getDataByProjectId(projectInfo.getId());
        if (unitInformationVo == null) {
            return null;
        }
        List<BaseReportTemplate> reportTemplateList = getReportTemplate(Integer.valueOf(unitInformationVo.getuUseUnit()), projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), reportType);
        if (CollectionUtils.isEmpty(reportTemplateList)) {
            return null;
        }
        return reportTemplateList.get(0);
    }


    /**
     * 根据条件查询报告模板
     *
     * @param useUnit           报告使用单位
     * @param projectTypeId     项目类型
     * @param projectCategoryId 项目类别
     * @param reportType        报告类型
     * @return
     */
    public List<BaseReportTemplate> getReportTemplate(Integer useUnit, Integer projectTypeId, Integer projectCategoryId, Integer reportType) {
        //1.根据传递过来的参数获取模板
        //2.未找到对应的模板 先以范围为全部进行查询 如果依然未找到 则取公司下的模板
        BaseReportTemplate baseReportTemplateWhere = new BaseReportTemplate();
        baseReportTemplateWhere.setReportType(reportType);
        //客户单位
        baseReportTemplateWhere.setUseUnit(useUnit);
        baseReportTemplateWhere.setType(projectTypeId);
        baseReportTemplateWhere.setCategory(projectCategoryId);
        baseReportTemplateWhere.setBisEnable(true);
        List<BaseReportTemplate> reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);

        if (CollectionUtils.isEmpty(reportTemplateList)) {
            //公司下的模板
            useUnit = 0;
            baseReportTemplateWhere.setUseUnit(useUnit);
            reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);
        }
        return reportTemplateList;
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
                    BaseReportTemplate baseReportTemplate = getReportTemplate(projectInfo.getId(), baseDataDic.getId());
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
        this.zipFiles(srcFile, zipFile);
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
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcFile：源文件列表
     * @param zipFile：压缩后的文件
     */
    private void zipFiles(File[] srcFile, File zipFile) throws Exception {
        byte[] buf = new byte[1024];
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < srcFile.length; i++) {
            FileInputStream in = new FileInputStream(srcFile[i]);
            out.putNextEntry(new ZipEntry(srcFile[i].getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
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
                            if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.TEXT.getKey())) {
                                textMap.put(baseReportField.getFieldName(), value.toString());
                            }
                            //自定义(书签)
                            if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.OTHER.getKey())) {
                                bookmarkMap.put(baseReportField.getRemark(), value.toString());
                            }
                            //附件(子模板)
                            if (Objects.equal(replaceEnum.getKey(), BaseReportFieldReplaceEnum.FILE.getKey())) {
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
        //获取文号
        String wordNumber = projectNumberRecordService.getReportNumber(projectInfo.getId(), areaId, baseReportTemplate.getReportType());
        //获取委托人
        String principal = StringUtils.isNotBlank(projectInfo.getConsignorVo().getCsName()) ? projectInfo.getConsignorVo().getCsName() : projectInfo.getConsignorVo().getCsEntrustmentUnit();
        List<BaseReportField> fieldList = baseReportFieldService.getDataDicList(BaseReportFieldEnum.GROUP.getKey());
        for (BaseReportField baseReportField : fieldList) {
            if (Objects.equal(BaseReportFieldEnum.REPORTNUMBER.getName(), baseReportField.getName())) {
                //文号
                mapSet.add(getBaseReportFieldReplaceEnumMap(
                        BaseReportFieldReplaceEnum.getEnumByName(baseReportField.getReplaceType()),
                        baseReportField,
                        wordNumber));
            }
            if (Objects.equal(BaseReportFieldEnum.PRINCIPAL.getName(), baseReportField.getName())) {
                //委托人
                mapSet.add(getBaseReportFieldReplaceEnumMap(
                        BaseReportFieldReplaceEnum.getEnumByName(baseReportField.getReplaceType()),
                        baseReportField,
                        principal));
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
