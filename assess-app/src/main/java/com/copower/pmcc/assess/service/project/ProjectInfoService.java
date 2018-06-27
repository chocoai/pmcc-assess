package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.InitiateConsignorEnum;
import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessParameterConstant;
import com.copower.pmcc.assess.dal.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.*;
import com.copower.pmcc.assess.dto.input.project.initiate.*;
import com.copower.pmcc.assess.dto.output.project.*;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateContactsVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectAssignEvent;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateContactsService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 描述:项目基础信息
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:05
 */
@Service
public class ProjectInfoService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Lazy
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Lazy
    @Autowired
    private CrmCustomerService crmCustomerService;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private BaseDataDicService bidBaseDataDicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Lazy
    @Autowired
    private InitiateContactsService initiateContactsService;
    @Autowired
    private InitiateConsignorService consignorService;
    @Autowired
    private InitiateUnitInformationService unitInformationService;
    @Autowired
    private InitiatePossessorService possessorService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private ProjectMemberDao projectMemberDao;

    /**
     * 项目立项申请
     *
     * @param projectDto
     */
    public boolean projectApply(InitiateProjectDto projectDto, String bisNextUser) throws BusinessException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        return projectApplyChange(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), change(projectMember), projectDto.getProjectInfo(), bisNextUser);
    }

    /*项目立项修改*/
    @Transactional
    public void projectUpdate(InitiateProjectDto projectDto, Integer projectinfoid) throws Exception {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        ProjectInfo projectInfoID = projectInfoDao.getProjectInfoById(projectinfoid);
        projectDto.getProjectInfo().setId(projectinfoid);
        projectMember.setProjectId(projectinfoid);
        projectApplyUpdate(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), change(projectMember), projectDto.getProjectInfo());
    }

    //修改附件中的table id 以及存附件的主表的附件id
    public void update_BaseAttachment_(int pid, String fields_name, int flag) throws Exception {
        int TEMP = 0;
        //默认位置为0
        List<BaseAttachment> baseAttachments = baseAttachmentDao.getByField_tableId(TEMP, fields_name, null);
        if (baseAttachments.size() >= 1) {
            //一般都只有一个
            BaseAttachment baseAttachment = baseAttachments.get(0);
            // 更新 存附件的主表
            if (flag == 0) {//项目信息 附件
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            } else if (flag == InitiateContactsEnum.ONE.getNum()) {// 委托人 附件
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            } else if (flag == InitiateContactsEnum.TWO.getNum()) {//占有人 附件
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            }

        } else {
            logger.info("没有上传附件!");
        }
    }

    @Transactional
    public void projectApplyUpdate(InitiateConsignorDto consignorDto, InitiateUnitInformationDto unitInformationDto, InitiatePossessorDto possessorDto, ProjectMemberDto projectMemberDto,
                                   ProjectInfoDto projectInfoDto) throws Exception {
        projectInfoDao.updateProjectInfo(change(projectInfoDto));
        projectInfoDto.setCreator(commonService.thisUserAccount());
        consignorDto.setProjectId(projectInfoDto.getId());
        consignorService.update(consignorDto);
        possessorDto.setProjectId(projectInfoDto.getId());
        possessorService.update(possessorDto);
        projectMemberService.saveReturnId(projectMemberDto);
        unitInformationDto.setProjectId(projectInfoDto.getId());
        unitInformationService.update(unitInformationDto);
        update_BaseAttachment_(consignorDto.getId(), InitiateConsignorDto.CSATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.ONE.getNum());
        update_BaseAttachment_(possessorDto.getId(), InitiatePossessorDto.PATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.TWO.getNum());
        update_BaseAttachment_(projectInfoDto.getId(), ProjectInfoDto.ATTACHMENTPROJECTINFOID, 0);
    }

    @Transactional
    public boolean projectApplyChange(InitiateConsignorDto consignorDto, InitiateUnitInformationDto unitInformationDto, InitiatePossessorDto possessorDto, ProjectMemberDto projectMemberDto,
                                      ProjectInfoDto projectInfoDto, String bisNextUser) {
        boolean flag = true;
        try {

            ProjectInfo projectInfo = change(projectInfoDto);
            if (projectInfo.getCreator() == null)
                projectInfo.setCreator(commonService.thisUserAccount());

            int projectId = 0;
            projectId = projectInfoDao.saveProjectInfo_returnID(projectInfo);// save

            consignorDto.setProjectId(projectId);
            int v = consignorService.add(consignorDto);
            unitInformationDto.setProjectId(projectId);
            int j = unitInformationService.add(unitInformationDto);
            possessorDto.setProjectId(projectId);
            int i = possessorService.add(possessorDto);
            //更新联系人中的主表id (这根据联系人的标识符(flag)来确定联系人类型)

            if (consignorDto.getCsType() == 1 && possessorDto.getpType() == 1) {//说明是法人 则不需要更新
                //修改之后法人的联系人被添加进本地数据库  因此也可以修改了
                initiateContactsService.update(v, InitiateContactsEnum.ONE.getNum());
                initiateContactsService.update(i, InitiateContactsEnum.TWO.getNum());
                initiateContactsService.update(j, InitiateContactsEnum.THREE.getNum());
            } else {
                initiateContactsService.update(v, InitiateContactsEnum.ONE.getNum());
                initiateContactsService.update(i, InitiateContactsEnum.TWO.getNum());
                initiateContactsService.update(j, InitiateContactsEnum.THREE.getNum());
            }
            //附件更新
            update_BaseAttachment_(v, InitiateConsignorDto.CSATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.ONE.getNum());
            update_BaseAttachment_(i, InitiatePossessorDto.PATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.TWO.getNum());

            projectMemberDto.setProjectId(projectId);
            projectMemberDto.setCreator(commonService.thisUserAccount());
            projectMemberService.saveReturnId(projectMemberDto);
            update_BaseAttachment_(projectId, ProjectInfoDto.ATTACHMENTPROJECTINFOID, 0);

            //判断是否需要下级再进行任务分派 //20180621 Calvin
            if (bisNextUser.equals("1")) {
                //发起流程
                String boxName = baseParameterServcie.getParameterValues(AssessParameterConstant.PROJECT_APPLY_ASSIGN_PROCESS_KEY);
                Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                ProcessInfo processInfo = new ProcessInfo();
                processInfo.setProcessName(boxReDto.getProcessName());
                processInfo.setGroupName(boxReDto.getGroupName());
                processInfo.setFolio(projectInfo.getProjectName());//流程描述
                processInfo.setTableName("tb_project_info");
                processInfo.setBoxId(boxReDto.getId());
                processInfo.setStartUser(commonService.thisUserAccount());
                processInfo.setProcessEventExecutorName(ProjectAssignEvent.class.getSimpleName());
                processInfo.setTableId(projectInfo.getId());
                //取审批人
                if (StringUtils.isNotEmpty(projectMemberDto.getUserAccountManager())) {
                    processInfo.setNextUser(Lists.newArrayList(projectMemberDto.getUserAccountManager()));
                } else {
                    Integer departmentId = projectInfo.getDepartmentId();
                    List<String> departmentDA = bpmRpcBoxRoleUserService.getDepartmentDA(departmentId);
                    processInfo.setNextUser(departmentDA);
                }
                try {
                    String processInsId = processControllerComponent.processStartPending(processInfo);
                    projectInfo.setAssignStatus(ProcessStatusEnum.RUN.getValue());
                    projectInfo.setAssignProcessInsId(processInsId);
                    projectInfoDao.updateProjectInfo(projectInfo);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            } else {
                initProjectInfo(projectInfo);//初始化项目信息
            }
        } catch (Exception e) {
            flag = false;
            logger.error("exception!" + e.getMessage());
        }
        return flag;
    }

    /**
     * 初始化项目信息
     *
     * @param projectInfo
     */
    @Transactional
    public void initProjectInfo(ProjectInfo projectInfo) throws BusinessException {
        Integer projectTypeId = projectInfo.getProjectTypeId();//项目类别id
        Integer projectCategoryId = projectInfo.getProjectCategoryId();//项目范围id
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);

        if (CollectionUtils.isEmpty(projectWorkStages)) {
            BaseProjectClassify defaultType = baseProjectClassifyService.getDefaultType();
            if (defaultType != null) {
                projectTypeId = defaultType.getId();
            }
            projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);
        }

        List<ProjectPhase> projectPhaseList = null;
        if (projectCategoryId != null && projectCategoryId > 0) {
            //取一次事项
            projectPhaseList = projectPhaseService.getCacheProjectPhaseByCategoryId(projectCategoryId);
        }

        if (projectCategoryId == null || projectCategoryId <= 0 || CollectionUtils.isEmpty(projectPhaseList)) {
            //取得默认项目范围
            BaseProjectClassify defaultCategory = baseProjectClassifyService.getDefaultCategory(projectTypeId);
            if (defaultCategory != null) {
                projectCategoryId = defaultCategory.getId();
            }
        }

        if (CollectionUtils.isEmpty(projectWorkStages))
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        int i = 1;
        for (ProjectWorkStage item : projectWorkStages) {
            ProjectPlan projectPlan = new ProjectPlan();
            projectPlan.setProjectId(projectInfo.getId());
            projectPlan.setProcessInsId("-1");
            projectPlan.setWorkStageId(item.getId());
            projectPlan.setCategoryId(projectCategoryId);
            projectPlan.setPlanName(item.getWorkStageName());
            projectPlan.setCreator(processControllerComponent.getThisUser());
            projectPlan.setCreated(new Date());
            projectPlan.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlan.setProjectStatus(ProjectStatusEnum.WAIT.getName());
            projectPlan.setBisRestart(false);
            projectPlan.setStageSort(i);
            projectPlanDao.addProjectPlan(projectPlan);
            i++;//系统对不同项目进行分别排序，你处理某些阶段不需要执行的问题
        }

        ProjectWorkStage projectWorkStage = projectWorkStages.get(0);//取得第一个阶段，即为项目审批立项阶段的审批
        if (StringUtils.isNotBlank(projectWorkStage.getReviewBoxName()))//注意是取复核模型，因为第一个阶段没有工作成果提交，所以取复核较为合理
        {
            ProcessUserDto processUserDto = startProjectProcess(projectInfo, projectWorkStage);
            //发起流程后更新项目的项目id
            projectInfo.setProcessInsId(processUserDto.getProcessInsId());
            projectInfo.setStatus(ProcessStatusEnum.RUN.getValue());
            projectInfoDao.updateProjectInfo(projectInfo);
        } else {//直接进入下一阶段
            projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getName());//更新流程状态
            updateProjectInfo(projectInfo);
            List<ProjectPlan> projectPlans = projectPlanService.getProjectplanByProjectId(projectInfo.getId(), "");
            projectPlanService.updatePlanStatus(projectPlans.get(0).getId());
        }
    }

    /**
     * 发起立项流程
     *
     * @param projectInfo
     * @param projectWorkStage
     * @return
     * @throws BusinessException
     */
    private ProcessUserDto startProjectProcess(ProjectInfo projectInfo, ProjectWorkStage projectWorkStage) throws BusinessException {
        ProcessUserDto processUserDto = null;
        //发起相应的流程
        String folio = "【立项审批】" + projectInfo.getProjectName();
        Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectWorkStage.getReviewBoxName());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setStartUser(projectInfo.getCreator());
        processInfo.setProjectId(projectInfo.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        processInfo.setTableId(projectInfo.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setProcessEventExecutorName(ProjectInfoEvent.class.getSimpleName());
        processInfo.setWorkStageId(projectWorkStage.getId());
        try {
            processUserDto = processControllerComponent.processStart(processInfo, projectInfo.getCreator(), false);
        } catch (BpmException e) {
            logger.info(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return processUserDto;
    }

    /**
     * 立项审批
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void projectApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void projectAssignApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        if (StringUtils.isNotEmpty(approvalModelDto.getAppointUserAccount())) {
            approvalModelDto.setNextApproval(Lists.newArrayList(approvalModelDto.getAppointUserAccount()));
            ProjectMember projectMember = projectMemberService.get(approvalModelDto.getProjectId());
            if(projectMember==null)
            {
                projectMember=new ProjectMember();
                projectMember.setProjectId(approvalModelDto.getProjectId());
                projectMember.setUserAccountManager(approvalModelDto.getAgentUserAccount());
                projectMemberDao.saveProjectMember(projectMember);
            }
            else {
                projectMember.setUserAccountManager(approvalModelDto.getAppointUserAccount());
                projectMemberDao.updateProjectMember(projectMember);
            }
        }
        processControllerComponent.processSubmitPendingTaskNodeArg(approvalModelDto);
    }

    /**
     * 返回修改
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void projectEditApproval(ApprovalModelDto approvalModelDto, ProjectInfoDto projectInfoDto) throws BusinessException, BpmException {
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, true);
    }

    public ProjectInfo getProjectInfoByProcessInsId(String processInsId) {
        return projectInfoDao.getProjectInfoByProcessInsId(processInsId);
    }

    public ProjectInfo getProjectInfoById(Integer projectId) {
        return projectInfoDao.getProjectInfoById(projectId);
    }

    public String getProjectName(List<ProjectInfo> projectInfos) {
        if (CollectionUtils.isEmpty(projectInfos))
            return "";
        List<String> stringList = LangUtils.transform(projectInfos, input -> input.getProjectName());
        return FormatUtils.transformListString(stringList);
    }

    public List<ProjectInfo> getProjectInfoByProjectIds(List<Integer> projectIds) {
        return projectInfoDao.getProjectInfoByProjectIds(projectIds);
    }

    public ProjectInfoVo  getSimpleProjectInfoVo(ProjectInfo projectInfo) {
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        BeanUtils.copyProperties(projectInfo, projectInfoVo);
        if (!ObjectUtils.isEmpty(projectInfo.getId()) && projectInfo.getId() > 0) {
            ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
            projectInfoVo.setProjectMemberVo(projectMemberVo);
        }
        //项目类型
        if (projectInfo.getProjectClassId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectClassId());
            if (projectClassify != null) {
                projectInfoVo.setProjectClassName(projectClassify.getName());
            }
        }
        //项目类别
        if (projectInfo.getProjectTypeId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectTypeId());
            if (projectClassify != null) {
                projectInfoVo.setProjectTypeName(projectClassify.getName());
            }
        }
        //项目范围
        if (projectInfo.getProjectCategoryId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectCategoryId());
            if (projectClassify != null) {
                projectInfoVo.setProjectCategoryName(projectClassify.getName());
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getEntrustPurpose())) {
            //委托目的
            projectInfoVo.setEntrustPurposeName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getEntrustPurpose()).getName());
        }
        return projectInfoVo;
    }

    public ProjectInfoVo getProjectInfoVo(ProjectInfo projectInfo) {
        ProjectInfoVo projectInfoVo = getSimpleProjectInfoVo(projectInfo);
        if (projectInfo.getProvince() != null && projectInfo.getProvince() > 0) {

            projectInfoVo.setProvinceName(erpAreaService.getSysAreaName(projectInfo.getProvince()+""));//省
        }
        if (projectInfo.getCity() != null && projectInfo.getCity() > 0) {
            projectInfoVo.setCityName(erpAreaService.getSysAreaName(projectInfo.getCity()+""));//市或者县
        }
        if (projectInfo.getDistrict() != null && projectInfo.getDistrict() > 0) {
            projectInfoVo.setDistrictName(erpAreaService.getSysAreaName(projectInfo.getDistrict()+""));//县
        }
        //紧急程度
        if (projectInfo.getUrgency() != null) {
            projectInfoVo.setUrgencyName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getUrgency()).getName());
        }
        if (projectInfo.getDepartmentId() != null) {
            projectInfoVo.setDepartmentName(getDepartmentDto(projectInfo.getDepartmentId()).getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getValueType())) {
            //价值类型
            projectInfoVo.setValueTypeName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getValueType()).getName());
        }
        InitiateConsignorVo consignorVo = consignorService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setConsignorVo(consignorVo);

        InitiatePossessorVo possessorVo = possessorService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setPossessorVo(possessorVo);

        InitiateUnitInformationVo informationVo = unitInformationService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setUnitInformationVo(informationVo);

        return projectInfoVo;
    }

    public List<ProjectInfo> getProjectInfoList(ProjectInfo projectInfo) {
        return projectInfoDao.getProjectInfoList(projectInfo);
    }

    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoDao.updateProjectInfo(projectInfo);
    }

    public CrmCustomerDto getCRM(Integer id) {
        return crmCustomerService.getCustomer(id);
    }

    public SysDepartmentDto getDepartmentDto(Integer id) {
        return erpRpcDepartmentService.getDepartmentById(id);
    }

    /*委托目的*/
    public List<BaseDataDic> list_entrustment_purpose() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        return baseDataDics;
    }

    /*紧急程度*/
    public List<BaseDataDic> project_initiate_urgency() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_INITIATE_URGENCY);
        return baseDataDics;
    }

    /*价值类型*/
    public List<BaseDataDic> value_type() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE);
        return baseDataDics;
    }

    /*单位性质 非CRM*/
    public Map<String, Object> getConsignorMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(InitiateConsignorEnum.ONE.getValue() + "", InitiateConsignorEnum.BANK.getDec());
        map.put(InitiateConsignorEnum.THREE.getValue() + "", InitiateConsignorEnum.OTHER.getDec());
        map.put(InitiateConsignorEnum.TWO.getValue() + "", InitiateConsignorEnum.GOVERNMENT_AFFILIATED_INSTITUTIONS.getDec());
        return map;
    }

    /*联系人 vo crm*/
    public BootstrapTableVo listContactsVo(Integer crmId, Integer cType, Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InitiateContactsVo> vos = null;
        if (ObjectUtils.isEmpty(pid)) {
            pid = InitiateContactsDto.CPID;
        }
        if (!ObjectUtils.isEmpty(crmId)) {
            initiateContactsService.writeContacts(crmId, cType, pid);//写入本地
        }
        vos = initiateContactsService.getVoList(pid, cType);//从本地获取
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InitiateContactsVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /*联系人类型*/
    public Map<String, String> getTypeInitiateContactsMap() {
        return initiateContactsService.getTypeMap();
    }

    //添加联系人
    public boolean addContacts(InitiateContactsDto dto) {
        return initiateContactsService.add(dto);
    }

    /*联系人删除*/
    public boolean removeContacts(Integer id) {
        return initiateContactsService.remove(id);
    }

    /*联系人修改*/
    public boolean updateContacts(InitiateContactsDto dto) {
        return initiateContactsService.update(dto);
    }

    public ProjectInfo change(ProjectInfoDto dto) {
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(dto, projectInfo);
        return projectInfo;
    }

    public ProjectMemberDto change(ProjectMember projectMember) {
        ProjectMemberDto dto = new ProjectMemberDto();
        BeanUtils.copyProperties(projectMember, dto);
        return dto;
    }

    public InitiateProjectDto format(String val) {
        InitiateProjectDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSONObject.parseObject(val, InitiateProjectDto.class);
        }
        return dto;
    }

    /*报告使用单位 获取*/
    public InitiateUnitInformationVo getInitiateUnitInformation(Integer id) {
        return unitInformationService.get(id);
    }

    /*占有人 获取*/
    public InitiatePossessorVo getInitiatePossessor(Integer id) {
        return possessorService.get(id);
    }

    /*委托人 获取*/
    public InitiateConsignorVo getInitiateConsignor(Integer id) {
        return consignorService.get(id);
    }

    /**
     * 单位性质 crm
     *
     * @return
     */
    public List<CrmBaseDataDicDto> getUnitPropertiesList() {
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        return crmBaseDataDicDtos;
    }

    public int saveProjectInfo_returnID(ProjectInfo projectInfo) {
        return projectInfoDao.saveProjectInfo_returnID(projectInfo);
    }

    /**
     * 回写到CRM中
     *
     * @param projectID
     * @param cType
     */
    public void writeCrmCustomerDto(Integer projectID, Integer cType) {
        initiateContactsService.writeCrmCustomerDto(projectID, cType);
    }

}
