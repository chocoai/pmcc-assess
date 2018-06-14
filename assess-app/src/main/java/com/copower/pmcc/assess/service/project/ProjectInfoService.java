package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.InitiateConsignorEnum;
import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.ProjectInfoDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.*;
import com.copower.pmcc.assess.dto.output.project.*;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
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
import com.google.common.base.Function;
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

    @Lazy
    @Autowired
    private ErpRpcUserService erpRpcUserService;

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

    /**
     * 项目立项申请
     *
     * @param projectDto
     */
    public boolean projectApply(InitiateProjectDto projectDto) throws BusinessException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        return projectApplyChange(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), change(projectMember), projectDto.getProjectInfo());
    }

    /*项目立项修改*/
    @Transactional
    public void projectUpdate(InitiateProjectDto projectDto, Integer projectinfoid, Integer consignorid, Integer possessorid, Integer unitInformationid) throws Exception {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        ProjectInfo projectInfoID = projectInfoDao.getProjectInfoById(projectinfoid);
        projectDto.getProjectInfo().setId(projectinfoid);
        projectMember.setId(projectInfoID.getProjectMemberId());
        projectMember.setProjectId(projectinfoid);
        projectDto.getConsignor().setId(projectInfoID.getConsignorId());
        projectDto.getUnitinformation().setId(projectInfoID.getUnitInformationId());
        projectDto.getPossessor().setId(projectInfoID.getPossessorId());
        projectApplyUpdate(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), change(projectMember), projectDto.getProjectInfo());
    }

    //修改附件中的table id 以及存附件的主表的附件id
    public void update_BaseAttachment_(int pid, String fields_name, int flag) throws Exception {
        int TEMP = 0;
        //默认位置为0
        List<BaseAttachment> baseAttachments = baseAttachmentDao.getByField_tableId(TEMP, fields_name);
        if (baseAttachments.size() >= 1) {
            //一般都只有一个
            BaseAttachment baseAttachment = baseAttachments.get(0);
            // 更新 存附件的主表
            if (flag == 0) {//项目信息 附件
                ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(pid);
                projectInfo.setAttachmentProjectInfoId("" + baseAttachment.getId());
                projectInfoDao.updateProjectInfo(projectInfo);//更新 委托人或者占有人或者项目信息中的附件id
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            } else if (flag == InitiateContactsEnum.ONE.getNum()) {// 委托人 附件
                InitiateConsignorDto dto = consignorService.getById(pid);
                dto.setCsAttachmentProjectEnclosureId("" + baseAttachment.getId());
                consignorService.update(dto);//更新 委托人或者占有人或者项目信息中的附件id
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            } else if (flag == InitiateContactsEnum.TWO.getNum()) {//占有人 附件
                InitiatePossessorDto dto = possessorService.getById(pid);
                dto.setpAttachmentProjectEnclosureId("" + baseAttachment.getId());
                possessorService.update(dto);//更新 委托人或者占有人或者项目信息中的附件id
                //更新附件
                baseAttachment.setTableId(pid);
                baseAttachmentDao.updateAttachment(baseAttachment);
            }

        } else {
            logger.info("没有上传附件!");
        }
    }

    @Transactional
    public void projectApplyUpdate(InitiateConsignorDto consignorDto, InitiateUnitInformationDto unitInformationDto, InitiatePossessorDto possessorDto, ProjectMemberDto projectMemberDto, ProjectInfoDto projectInfoDto) throws Exception {
        projectInfoDao.updateProjectInfo(change(projectInfoDto));
        projectInfoDto.setCreator(commonService.thisUserAccount());
        consignorService.update(consignorDto);
        possessorService.update(possessorDto);
        projectMemberService.saveReturnId(projectMemberDto);
        unitInformationService.update(unitInformationDto);
        update_BaseAttachment_(consignorDto.getId(), InitiateConsignorDto.CSATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.ONE.getNum());
        update_BaseAttachment_(possessorDto.getId(), InitiatePossessorDto.PATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.TWO.getNum());
        update_BaseAttachment_(projectInfoDto.getId(), ProjectInfoDto.ATTACHMENTPROJECTINFOID, 0);
    }

    @Transactional
    public boolean projectApplyChange(InitiateConsignorDto consignorDto, InitiateUnitInformationDto unitInformationDto, InitiatePossessorDto possessorDto, ProjectMemberDto projectMemberDto, ProjectInfoDto projectInfoDto) {
        boolean flag = true;
        try {
            int v = consignorService.add(consignorDto);
            int j = unitInformationService.add(unitInformationDto);
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

            try {
                //附件更新
                update_BaseAttachment_(v, InitiateConsignorDto.CSATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.ONE.getNum());
                update_BaseAttachment_(i, InitiatePossessorDto.PATTACHMENTPROJECTENCLOSUREID, InitiateContactsEnum.TWO.getNum());
                projectMemberDto.setCreator(commonService.thisUserAccount());
                int k = projectMemberService.saveReturnId(projectMemberDto);
                ProjectInfo projectInfo = change(projectInfoDto);
                projectInfo.setConsignorId(v);//设置 关联id
                projectInfo.setPossessorId(i);
                projectInfo.setUnitInformationId(j);
                projectInfo.setProjectMemberId(k);
                if (projectInfo.getCreator() == null) projectInfo.setCreator(commonService.thisUserAccount());

                int id = 0;
                id = projectInfoDao.saveProjectInfo_returnID(projectInfo);// save
                projectMemberDto.setProjectId(id);
                projectMemberDto.setId(k);
                //2018-06-08标记
                projectMemberService.updateProjectMember(projectMemberDto);
                update_BaseAttachment_(id, ProjectInfoDto.ATTACHMENTPROJECTINFOID, 0);
                initProjectInfo(projectInfo);//初始化项目信息
            } catch (Exception e) {
                try {
                    flag = false;
                    logger.error("exception!" + e.getMessage());
                    throw e;
                } catch (Exception e2) {

                }
            }
        } catch (Exception e) {
            try {
                flag = false;
                throw e;
            } catch (Exception e1) {

            }
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
        processInfo.setProjectId(projectInfo.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName("tb_project_info");
        processInfo.setTableId(projectInfo.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setProcessEventExecutorName(ProjectInfoEvent.class.getSimpleName());
        processInfo.setWorkStageId(projectWorkStage.getId());
        try {
            processUserDto = processControllerComponent.processStart(processInfo, processControllerComponent.getThisUser(), false);
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
        List<String> stringList = LangUtils.transform(projectInfos, new Function<ProjectInfo, String>() {
            @Override
            public String apply(ProjectInfo input) {
                return input.getProjectName();
            }
        });
        return FormatUtils.transformListString(stringList);
    }

    public List<ProjectInfo> getProjectInfoByProjectIds(List<Integer> projectIds) {
        return projectInfoDao.getProjectInfoByProjectIds(projectIds);
    }

    public ProjectInfoVo getVo(ProjectInfo projectInfo) {
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        BeanUtils.copyProperties(projectInfo, projectInfoVo);
        if (!ObjectUtils.isEmpty(projectInfo.getId()) && projectInfo.getId() > 0) {
            ProjectMember projectMember = projectMemberService.getById(projectInfo.getProjectMemberId());
            ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
            String s1 = accountGet(projectMember.getUserAccountManager(), 1);
            String s2 = accountGet(projectMember.getUserAccountMember(), 0);
            BeanUtils.copyProperties(projectMember, projectMemberVo);
            if (org.springframework.util.StringUtils.isEmpty(projectMemberVo.getUserAccountManagerName())) {
                projectMemberVo.setUserAccountManagerName(s1);
            }
            if (org.springframework.util.StringUtils.isEmpty(projectMemberVo.getUserAccountMemberName())) {
                projectMemberVo.setUserAccountMemberName(s2);
            }
            projectInfoVo.setProjectMemberVo(projectMemberVo);
        }
        //项目类型
        if (projectInfo.getProjectClassId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(projectInfo.getProjectClassId());
            if (projectClassify != null) {
                projectInfoVo.setProjectClassName(projectClassify.getName());
            }
        }
        //项目类别
        if (projectInfo.getProjectTypeId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(projectInfo.getProjectTypeId());
            if (projectClassify != null) {
                projectInfoVo.setProjectTypeName(projectClassify.getName());
            }
        }
        //项目范围
        if (projectInfo.getProjectCategoryId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(projectInfo.getProjectCategoryId());
            if (projectClassify != null) {
                projectInfoVo.setProjectCategoryName(projectClassify.getName());
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getEntrustPurpose())) {
            //委托目的
            projectInfoVo.setEntrustPurposeName(baseDataDicChange(projectInfo.getEntrustPurpose(), bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE)));
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getProvince())) {
            projectInfoVo.setProvinceName(getProvinceName(projectInfo.getProvince()));//省
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getCity())) {
            projectInfoVo.setCityName(getSysArea(projectInfo.getCity()));//市或者县
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getDistrict())) {
            try {
                projectInfoVo.setDistrictName(getSysArea(projectInfo.getDistrict()));//县
            } catch (Exception e) {
                projectInfoVo.setDistrictName("没有匹配到县的数据");
                logger.error(e.getMessage() + "------------------------");
            }
        }
        //紧急程度
        if (projectInfo.getUrgency() != null) {
            projectInfoVo.setUrgencyName(baseDataDicChange(projectInfo.getUrgency(), bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_INITIATE_URGENCY)));
        }
        if (projectInfo.getDepartmentId() != null) {
            projectInfoVo.setDepartmentName(getDepartmentDto(projectInfo.getDepartmentId()).getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getValueType())) {
            //价值类型
            projectInfoVo.setProjectTypeName(baseDataDicChange(projectInfo.getValueType(), bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE)));
        }
        if (!ObjectUtils.isEmpty(projectInfo.getConsignorId())) {
            InitiateConsignorVo consignorVo = consignorService.get(projectInfo.getConsignorId());
            if (!org.springframework.util.StringUtils.isEmpty(consignorVo.getCsUnitProperties())) {
                try {
                    String csUnitPropertiesName = getBaseDataDic(consignorVo.getCsUnitProperties()).getName();
                    consignorVo.setCsUnitPropertiesName(csUnitPropertiesName);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (!org.springframework.util.StringUtils.isEmpty(consignorVo.getCsEntrustmentUnit())) {
                try {
                    consignorVo.setCsEntrustmentUnitName(getCRM(Integer.parseInt(consignorVo.getCsEntrustmentUnit())).getName());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            projectInfoVo.setConsignorVo(consignorVo);
        }
        if (!ObjectUtils.isEmpty(projectInfo.getPossessorId())) {
            InitiatePossessorVo possessorVo = possessorService.get(projectInfo.getPossessorId());
            if (!org.springframework.util.StringUtils.isEmpty(possessorVo.getpUnitProperties())) {
                try {
                    String pUnitPropertiesName = getBaseDataDic(possessorVo.getpUnitProperties()).getName();
                    possessorVo.setpUnitPropertiesName(pUnitPropertiesName);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (!org.springframework.util.StringUtils.isEmpty(possessorVo.getpEntrustmentUnit())) {
                try {
                    possessorVo.setpEntrustmentUnitName(getCRM(Integer.parseInt(possessorVo.getpEntrustmentUnit())).getName());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            projectInfoVo.setPossessorVo(possessorVo);
        }

        if (!ObjectUtils.isEmpty(projectInfo.getUnitInformationId())) {
            InitiateUnitInformationVo unitInformationVo = unitInformationService.get(projectInfo.getUnitInformationId());
            if (StringUtils.isNotBlank(unitInformationVo.getuUnitProperties())) {
                String uUnitPropertiesName = getBaseDataDic(unitInformationVo.getuUnitProperties()).getName();
                unitInformationVo.setuUnitPropertiesName(uUnitPropertiesName);
            }
            if (!org.springframework.util.StringUtils.isEmpty(unitInformationVo.getuUseUnit())) {
                try {
                    unitInformationVo.setuUseUnitName(getCRM(Integer.parseInt(unitInformationVo.getuUseUnit())).getName());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            projectInfoVo.setUnitInformationVo(unitInformationVo);
        }
        return projectInfoVo;
    }

    public String baseDataDicChange(Integer id, List<BaseDataDic> base) {
        String v = "";
        inner:
        for (BaseDataDic b : base) {
            if (b.getId().equals(id)) {
                v = b.getName();
                break inner;
            }
        }
        return v;
    }

    public String accountGet(String account, int flag) {
        StringBuilder builder = new StringBuilder(1024);
        if (flag == 1) {
            if (!org.springframework.util.StringUtils.isEmpty(account)) {
                String[] strings = account.split(",");
                for (String s : strings) {
                    if (!org.springframework.util.StringUtils.isEmpty(s)) {
                        builder.append(erpRpcUserService.getSysUser(s).getUserName() + " ");
                    }
                }

            }
        } else {
            if (!org.springframework.util.StringUtils.isEmpty(account)) {
                String[] strings = account.split(">");
                for (String s1 : strings) {
                    if (!org.springframework.util.StringUtils.isEmpty(s1)) {
                        String[] strings1 = s1.split(",");
                        for (String ss : strings1) {
                            if (!org.springframework.util.StringUtils.isEmpty(ss)) {
                                builder.append(erpRpcUserService.getSysUser(ss).getUserName() + " ");
                            }
                        }
                    }
                }

            }
        }
        return builder.toString();
    }

    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoDao.updateProjectInfo(projectInfo);
    }

    public CrmCustomerDto getCRM(Integer id) {
        return crmCustomerService.getCustomer(id);
    }

    /*获取所有省*/
    public List<SysAreaDto> getProvinceList() {
        return erpAreaService.getProvinceList();
    }

    /*省名称*/
    public String getProvinceName(int pid) {
        List<SysAreaDto> provinceLists = erpAreaService.getProvinceList();
        String s = provinceAndArea(pid, provinceLists);
        return s;
    }

    /*城市名称*/
    public String getSysArea(Integer id) {
        return erpAreaService.getSysAreaDto(String.valueOf(id)).getName();
    }


    public SysDepartmentDto getDepartmentDto(Integer id) {
        return erpRpcDepartmentService.getDepartmentById(id);
    }

    public String provinceAndArea(int id, List<SysAreaDto> sysAreaDtos) {
        String v = "";
        inner:
        for (SysAreaDto s : sysAreaDtos) {
            if (id == s.getId()) {
                v = s.getName();
                break inner;
            }
        }
        return v;
    }


    /*获取城市*/
    public List<SysAreaDto> getAreaList(String pid) {
        return erpAreaService.getAreaList(pid);
    }


    /*大类*/
    public List<BaseDataDic> listClass_assess() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ASSESS_CLASS);
        return baseDataDics;
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

    public CrmBaseDataDicDto getBaseDataDic(String id) {
        if (!StringUtils.isEmpty(id)) {
            CrmBaseDataDicDto crmBaseDataDicDto = crmRpcBaseDataDicService.getBaseDataDic(Integer.parseInt(id));
            if (!ObjectUtils.isEmpty(crmBaseDataDicDto)) return crmBaseDataDicDto;
        }
        return null;
    }

    public int saveProjectInfo_returnID(ProjectInfo projectInfo) {
        return projectInfoDao.saveProjectInfo_returnID(projectInfo);
    }

    /**
     * 联系人 检查数量是否达到要求
     *
     * @return
     */
    public boolean checkContacts(InitiateContactsEnum initiateContactsEnum) {
        return initiateContactsService.checkContacts(initiateContactsEnum);
    }

    /**
     * 第一次填写后留下的委托人 数据信息
     *
     * @return
     */
    public InitiateConsignorDto oneFirstConsignor() {
        return consignorService.oneFirstConsignor();
    }

}
