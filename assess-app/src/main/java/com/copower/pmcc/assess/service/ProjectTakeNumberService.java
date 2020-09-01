package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldCommonEnum;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectTakeNumberDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectTakeNumberVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectTakeNumberServiceEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 项目拿号
 */
@Service
public class ProjectTakeNumberService {
    @Autowired
    private ProjectTakeNumberDao projectTakeNumberDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectTakeNumberDetailService projectTakeNumberDetailService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectTakeNumber projectTakeNumber, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            projectTakeNumber.setCreator(commonService.thisUserAccount());
            projectTakeNumber.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(projectTakeNumber, baseParameterEnum);
            if (processUserDto != null) projectTakeNumber.setProcessInsId(processUserDto.getProcessInsId());
            projectTakeNumberDao.addProjectTakeNumber(projectTakeNumber);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(projectTakeNumber.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectTakeNumber projectTakeNumber, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectTakeNumber.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());

        processInfo.setFolio(String.format("%s【项目拿号申请】", costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));
        processInfo.setProcessEventExecutor(ProjectTakeNumberServiceEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(projectTakeNumber.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(projectTakeNumber.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectTakeNumber getDataByProcessInsId(String processInsId) {
        ProjectTakeNumber projectTakeNumber = new ProjectTakeNumber();
        projectTakeNumber.setProcessInsId(processInsId);
        List<ProjectTakeNumber> projectTakeNumbers = getProjectTakeNumberListQuery(projectTakeNumber);
        if (CollectionUtils.isNotEmpty(projectTakeNumbers))
            projectTakeNumber = projectTakeNumbers.get(0);
        return projectTakeNumber;
    }

    public List<ProjectTakeNumber> getProjectTakeNumberListQuery(ProjectTakeNumber target) {
        return projectTakeNumberDao.getProjectTakeNumber(target);
    }

    public void approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            e.printStackTrace();
            log.error("提交失败", e);
        }
    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public void editData(ProjectTakeNumber projectTakeNumber) {
        projectTakeNumberDao.modifyProjectTakeNumber(projectTakeNumber);
    }

    public void applyCommit(String formData) {
        ProjectTakeNumber projectTakeNumber = JSONObject.parseObject(formData, ProjectTakeNumber.class);
        saveAndUpdateProjectTakeNumber(projectTakeNumber, false);
        List<ProjectTakeNumberDetail> projectTakeNumberDetailList = projectTakeNumberDetailService.getProjectTakeNumberDetailListByMasterId(projectTakeNumber.getId());
        if (CollectionUtils.isNotEmpty(projectTakeNumberDetailList)) {
            Iterator<ProjectTakeNumberDetail> projectTakeNumberDetailIterator = projectTakeNumberDetailList.iterator();
            while (projectTakeNumberDetailIterator.hasNext()) {
                ProjectTakeNumberDetail projectTakeNumberDetail = projectTakeNumberDetailIterator.next();
                if (StringUtils.isNotBlank(projectTakeNumberDetail.getReportTypeName())) {
                    continue;
                }
                projectTakeNumberDetail.setReportTypeName(baseDataDicService.getNameById(projectTakeNumberDetail.getReportType()));
                projectTakeNumberDetailService.saveAndUpdateProjectTakeNumberDetail(projectTakeNumberDetail, true);
            }
        }
    }

    public void saveAndUpdateProjectTakeNumber(ProjectTakeNumber projectTakeNumber, Boolean updateNull) {
        if (projectTakeNumber == null) {
            return;
        }
        if (projectTakeNumber.getId() == null || projectTakeNumber.getId() == 0) {
            if (StringUtils.isEmpty(projectTakeNumber.getCreator())) {
                projectTakeNumber.setCreator(processControllerComponent.getThisUser());
            }
            projectTakeNumberDao.addProjectTakeNumber(projectTakeNumber);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class), projectTakeNumber.getId());

        } else {
            projectTakeNumberDao.updateProjectTakeNumber(projectTakeNumber, updateNull == null ? Boolean.FALSE : updateNull);
        }
    }

    /**
     * 替换文号和 二维码
     *
     * @param attachmentIds 需要替换的文档
     * @param symbol        文号
     * @param query         二维码附件
     */
    public void replaceDocument(String attachmentIds, String symbol, SysAttachmentDto query) throws Exception {
        if (StringUtils.isEmpty(attachmentIds)) {
            return;
        }
        if (StringUtils.isEmpty(symbol)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(attachmentIds);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        Map<Integer, String> integerStringMap = new HashMap<>(ids.size());
        Iterator<Integer> integerIterator = ids.iterator();
        while (integerIterator.hasNext()) {
            Integer id = integerIterator.next();
            String path = baseAttachmentService.downloadFtpFileToLocal(id);
            if (StringUtils.isNotBlank(path)) {
                String extension = FilenameUtils.getExtension(path);
                //必须是规定的文档
                int[] arr = new int[]{SaveFormat.DOC, SaveFormat.DOT, SaveFormat.DOTX, SaveFormat.DOCM, SaveFormat.DOTX, SaveFormat.DOTM};
                for (int i = 0; i < arr.length; i++) {
                    if (StringUtils.equalsIgnoreCase(SaveFormat.getName(arr[i]), extension)) {
                        integerStringMap.put(id, path);
                    }
                }
            }
        }
        if (integerStringMap.isEmpty()) {
            return;
        }
        String otherPath = null;
        SysAttachmentDto sysAttachmentDto = null;
        if (query != null) {
            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                Ordering<SysAttachmentDto> ordering = Ordering.from(new Comparator<SysAttachmentDto>() {
                    @Override
                    public int compare(SysAttachmentDto o1, SysAttachmentDto o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                }).reverse();
                Collections.sort(sysAttachmentDtoList, ordering);
                sysAttachmentDto = sysAttachmentDtoList.get(0);
            }
        }
        if (sysAttachmentDto != null) {
            otherPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        }
        Iterator<Map.Entry<Integer, String>> entryIterator = integerStringMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            Integer id = entry.getKey();
            SysAttachmentDto params = baseAttachmentService.getSysAttachmentDto(id);
            params.setId(null);
            params.setFilePath(null);
            params.setFileSize(null);
//            params.setFileName(null);
            String tempPath = entry.getValue();

            //开始替换
            replaceDocument(tempPath, symbol, otherPath);

            baseAttachmentService.importAjaxFile(tempPath, params);
            baseAttachmentService.deleteAttachment(id);
        }
    }

    /**
     * 替换类似${文号},${二维码}
     *
     * @param tempPath  源文件
     * @param symbol    文号字符串
     * @param otherPath 二维码文件
     */
    public void replaceDocument(String tempPath, String symbol, String otherPath) throws Exception {
        Document document = new Document(tempPath);
        String reportNumber = String.join("", "${", ReportFieldCommonEnum.CommonReportNumber.getName(), "}");
        String reportQrcode = String.join("", "\\$\\{", ReportFieldCommonEnum.CommonReportQrcode.getName(), "\\}");
        document.getRange().replace(reportNumber, symbol, false, false);
        if (StringUtils.isNotBlank(otherPath)) {
            IReplacingCallback callback = new IReplacingCallback() {
                @Override
                public int replacing(ReplacingArgs e) throws Exception {
                    DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                    builder.moveTo(e.getMatchNode());

                    String folder = System.getProperty("java.io.tmpdir");
                    String replacePath = String.join("", folder, File.separator, UUID.randomUUID().toString(), ".", "doc");
                    Document replacingCallback = new Document();
                    DocumentBuilder documentBuilder = new DocumentBuilder(replacingCallback);
                    documentBuilder.insertImage(new FileInputStream(otherPath));
                    AsposeUtils.saveWord(replacePath, replacingCallback);

                    builder.insertDocument(replacingCallback, 0);
                    return 0;
                }
            };
            document.getRange().replace(Pattern.compile(reportQrcode), callback, false);
        }
        AsposeUtils.saveWord(tempPath, document);
    }


    /**
     * 获取数据列表
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getDocumentSendVoList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectTakeNumber takeNumber = new ProjectTakeNumber();
        takeNumber.setProjectId(projectId);
        takeNumber.setStatus(ProcessStatusEnum.FINISH.getValue());
        List<ProjectTakeNumber> list = getProjectTakeNumberListQuery(takeNumber);
        List<ProjectTakeNumberVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            vos = LangUtils.transform(list, o -> getProjectTakeNumberVo(o, o.getNumberRecordId()));
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public ProjectTakeNumberVo getProjectTakeNumberVo(ProjectTakeNumber projectTakeNumber, Integer numberRecordId) {
        if (projectTakeNumber == null) {
            return null;
        }
        ProjectTakeNumberVo vo = new ProjectTakeNumberVo();
        BeanUtils.copyProperties(projectTakeNumber, vo);
        vo.setReportTypeName(baseDataDicService.getNameById(projectTakeNumber.getReportType()));
        if (projectTakeNumber.getReportType() != null) {
            vo.setCreatorName(publicService.getUserNameByAccount(projectTakeNumber.getCreator()));
        }
        if (numberRecordId != null) {

        }
        //取资质名称
        if (StringUtils.isNotBlank(projectTakeNumber.getQualificationType())) {
            AdPersonalEnum adPersonalEnum = AdPersonalEnum.create(projectTakeNumber.getQualificationType());
            if (adPersonalEnum != null)
                vo.setQualificationTypeName(adPersonalEnum.getName());
        }
        //取估价师名称
        if (StringUtils.isNotBlank(projectTakeNumber.getRealEstateAppraiser())) {
            List<String> stringList = FormatUtils.transformString2List(projectTakeNumber.getRealEstateAppraiser());
            if (!org.springframework.util.CollectionUtils.isEmpty(stringList)) {
                String userName = publicService.getUserNameByAccountList(stringList);
                vo.setRealEstateAppraiserName(userName);
            }
        }
        return vo;
    }

    public ProjectTakeNumberVo getProjectTakeNumberVo(ProjectTakeNumber projectTakeNumber) {
        return getProjectTakeNumberVo(projectTakeNumber, null);
    }

    public ProjectTakeNumber getProjectTakeNumberById(Integer id) {
        return projectTakeNumberDao.getProjectTakeNumberById(id);
    }

    /**
     * 初始化 拿号 工作事项
     *
     * @param projectPlanDetails
     */
    public void initProjectTakeNumber(ProjectPlanDetails projectPlanDetails) {
        Map<String, AssessProjectTypeEnum> typeEnumMap = Maps.newHashMap();
        typeEnumMap.put(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE, AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE);
        typeEnumMap.put(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE, AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND);
        typeEnumMap.put(AssessProjectClassifyConstant.COMPREHENSIVE_ASSETS_TYPE, AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_ASSETS);
        ProjectTakeNumber query = new ProjectTakeNumber();
        query.setPlanDetailsId(projectPlanDetails.getId());
        query.setProjectId(projectPlanDetails.getProjectId());
        if (StringUtils.isNotBlank(projectPlanDetails.getProcessInsId())) {
            query.setProcessInsId(projectPlanDetails.getProcessInsId());
        }
        List<ProjectTakeNumber> projectTakeNumberList = getProjectTakeNumberListQuery(query);
        if (CollectionUtils.isNotEmpty(projectTakeNumberList)) {
            return;
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        List<AssessProjectTypeEnum> assessProjectTypeEnumList = new ArrayList<>(1);
        Map<String, BaseProjectClassify> classifyMap = Maps.newHashMap();
        typeEnumMap.forEach((s, assessProjectTypeEnum) -> classifyMap.put(s, baseProjectClassifyService.getCacheProjectClassifyByFieldName(s)));
        if (!classifyMap.isEmpty()) {
            classifyMap.forEach((s, baseProjectClassify) -> {
                if (com.google.common.base.Objects.equal(baseProjectClassify.getId(), projectInfo.getProjectCategoryId())) {
                    if (typeEnumMap.containsKey(s)) {
                        assessProjectTypeEnumList.add(typeEnumMap.get(s));
                    }
                }
            });
        }
        query.setAssessProjectType(assessProjectTypeEnumList.get(0).getKey());
        saveAndUpdateProjectTakeNumber(query, true);
    }

}
