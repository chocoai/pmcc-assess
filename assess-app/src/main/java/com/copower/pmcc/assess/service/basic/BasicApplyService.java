package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.event.basic.BasicApplyEvent;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCaseStudyService;
import com.copower.pmcc.assess.service.project.survey.SurveySceneExploreService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Service
public class BasicApplyService {
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private CaseEstateService caseEstateService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BasicApply getByBasicApplyId(Integer id) {
        return basicApplyDao.getBasicApplyById(id);
    }

    public BasicApply getBasicApplyByProcessInsId(String processInsId) {
        BasicApply basicApply = new BasicApply();
        basicApply.setProcessInsId(processInsId);
        List<BasicApply> basicApplies = basicApplyDao.getBasicApplyList(basicApply);
        if (!ObjectUtils.isEmpty(basicApplies)) {
            return basicApplies.get(0);
        } else {
            return null;
        }
    }

    public BasicApply getBasicApplyByPlanDetailsId(Integer planDetailsId) {
        BasicApply basicApply = new BasicApply();
        basicApply.setPlanDetailsId(planDetailsId);
        List<BasicApply> basicApplies = basicApplyDao.getBasicApplyList(basicApply);
        if (!ObjectUtils.isEmpty(basicApplies)) {
            return basicApplies.get(0);
        } else {
            return null;
        }
    }

    public Integer saveBasicApply(BasicApply basicApply) {
        if (basicApply.getId() == null || basicApply.getId() == 0) {
            basicApply.setCreator(commonService.thisUserAccount());
            basicApply.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
            basicApplyDao.addBasicApply(basicApply);
        } else {
            basicApplyDao.updateBasicApply(basicApply);
        }
        return basicApply.getId();
    }

    public boolean updateBasicApply(BasicApply basicApply) {
        return basicApplyDao.updateBasicApply(basicApply);
    }

    /**
     * 流程发起
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public ProcessUserDto processStartSubmit(BasicApply basicApply) throws Exception {
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(getFullName(basicApply.getEstateName(), basicApply.getBuildingNumber(), basicApply.getUnitNumber(), basicApply.getHouseNumber()));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(BasicApply.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BasicApplyEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BasicApplyEvent.class.getSimpleName());
        processInfo.setTableId(basicApply.getId());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(),processInfo, processControllerComponent.getThisUser(), false);
            basicApply.setProcessInsId(processUserDto.getProcessInsId());
            basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
            this.updateBasicApply(basicApply);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }

    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void processApprovalSubmit(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag) throws Exception {
        try {
            if (writeBackBlockFlag != null) {
                BasicApply basicApply = getBasicApplyByProcessInsId(approvalModelDto.getProcessInsId());
                if (basicApply == null)
                    throw new BusinessException("未找到申请数据");
                BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (basicEstate == null)
                    throw new BusinessException("未找到楼盘数据");
                if (writeBackBlockFlag == Boolean.TRUE) {
                    if (dataBlockService.isExistBlock(basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict(), blockName))
                        throw new BusinessException("该版块名称基础数据中已存在");
                }
                basicEstate.setBlockName(blockName);
                basicEstateService.saveAndUpdateBasicEstate(basicEstate);

                basicApply.setWriteBackBlockFlag(writeBackBlockFlag);
                updateBasicApply(basicApply);
            }
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 案例 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(String estateName, Boolean draftFlag) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicApply> basicApplyList = basicApplyDao.getBasicApplyListByName(estateName, commonService.thisUserAccount(), draftFlag);
        List<BasicApplyVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(basicApplyList)) {
            for (BasicApply basicApply1 : basicApplyList) {
                vos.add(getBasicApplyVo(basicApply1));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicApplyVo>(10) : vos);
        return vo;
    }

    public BasicApplyVo getBasicApplyVo(BasicApply basicApply) {
        if (basicApply == null) {
            return null;
        }
        BasicApplyVo vo = new BasicApplyVo();
        BeanUtils.copyProperties(basicApply, vo);
        vo.setFullName(getFullName(basicApply.getEstateName(), basicApply.getBuildingNumber(), basicApply.getUnitNumber(), basicApply.getHouseNumber()));
        if (basicApply.getType() != null) {
            for (BasicApplyTypeEnum typeEnum : BasicApplyTypeEnum.values()) {
                if (basicApply.getType().intValue() == typeEnum.getId().intValue()) {
                    vo.setTypeName(typeEnum.getName());
                }
            }
        }
        return vo;
    }

    /**
     * 获取申请完整名称
     *
     * @param estateName
     * @param buildingNumber
     * @param unitNumber
     * @param houseNumber
     * @return
     */
    public String getFullName(String estateName, String buildingNumber, String unitNumber, String houseNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(estateName))
            stringBuilder.append(estateName);
        if (StringUtils.isNotBlank(buildingNumber))
            stringBuilder.append(buildingNumber).append("栋");
        if (StringUtils.isNotBlank(unitNumber))
            stringBuilder.append(unitNumber).append("单元");
        if (StringUtils.isNotBlank(houseNumber))
            stringBuilder.append(houseNumber).append("号");
        return stringBuilder.toString();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBasicApply(Integer id) throws Exception {
        basicEstateService.clearInvalidData(id);
        basicBuildingService.clearInvalidData(id);
        basicUnitService.clearInvalidData(id);
        basicHouseService.clearInvalidData(id);
        basicApplyDao.deleteBasicApply(id);
    }

    /**
     * 楼盘是否重复申请
     *
     * @param estateName
     * @param province
     * @param city
     * @param applyId
     * @return
     */
    public Boolean hasApplyEstate(String estateName, String province, String city, Integer applyId) {
        BasicApply basicApply = new BasicApply();
        basicApply.setEstateName(estateName);
        basicApply.setProvince(province);
        basicApply.setCity(city);
        return basicApplyDao.getBasicApplyCount(basicApply, applyId) > 0;
    }

    //获取项目查勘案例数据
    public BootstrapTableVo getProjectCaseItemList(Integer projectId, Integer projectCategoryId,Integer basicApplyTypeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        //案列事项
        ProjectPhase projectCasePhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.CASE_STUDY, projectCategoryId);
        ProjectPlanDetails projectCaseDetails = new ProjectPlanDetails();
        projectCaseDetails.setProjectId(projectId);
        projectCaseDetails.setProjectPhaseId(projectCasePhase.getId());
        List<ProjectPlanDetails> caseLists = projectPlanDetailsService.getProjectDetails(projectCaseDetails);
        List<ProjectPlanDetails> caseList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(caseLists)) {
            for (ProjectPlanDetails item : caseLists) {
                List<ProjectPlanDetails> projectPlanDetailsByPid = projectPlanDetailsDao.getProjectPlanDetailsByPid(item.getId());
                if (CollectionUtils.isNotEmpty(projectPlanDetailsByPid)) {
                    for (ProjectPlanDetails data : projectPlanDetailsByPid) {
                        BasicApply basicApply = this.getBasicApplyByPlanDetailsId(data.getId());
                        if(basicApply.getType().equals(basicApplyTypeId)) {
                            caseList.addAll(projectPlanDetailsDao.getProjectPlanDetailsByPid(data.getId()));
                        }
                    }
                }
            }
        }
        //查勘事项
        ProjectPhase projectSurveyPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectCategoryId);
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setProjectId(projectId);
        projectPlanDetails.setProjectPhaseId(projectSurveyPhase.getId());
        List<ProjectPlanDetails> projectDetailLists = projectPlanDetailsService.getProjectDetails(projectPlanDetails);
        if (CollectionUtils.isNotEmpty(projectDetailLists)) {
            for (ProjectPlanDetails item : projectDetailLists) {
                BasicApply basicApply = this.getBasicApplyByPlanDetailsId(item.getId());
                if(basicApply==null) continue;
                if(basicApply.getType().equals(basicApplyTypeId)) {
                    caseList.addAll(projectPlanDetailsDao.getProjectPlanDetailsByPid(item.getId()));
                }
            }
        }

        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(caseList) ? new ArrayList<ProjectPlanDetails>(10) : caseList);
        return vo;
    }


    //获取查勘及案例的BasicApply
    public BasicApply getCaseBasicApply(Integer id, Integer projectPhaseId) throws Exception {
       // Integer basicApplyId = null;
        //现场查勘事项调查信息
//        ProjectPhase projectSurveyPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE);
//        if (projectPhaseId.equals(projectSurveyPhase.getId())) {
//            basicApplyId = surveySceneExploreService.getSurveySceneExplore(id).getBasicApplyId();
//
//        }
//        //案例事项调查信息
//        ProjectPhase projectCasePhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
//        if (projectPhaseId.equals(projectCasePhase.getId())) {
//            basicApplyId = surveyCaseStudyService.getSurveyCaseStudy(id).getBasicApplyId();
//        }
        Integer applyPlanDetailsId = projectPlanDetailsService.getProjectPlanDetailsById(id).getPid();
        BasicApply basicApply = this.getBasicApplyByPlanDetailsId(applyPlanDetailsId);
       // BasicApply basicApply = basicApplyDao.getBasicApplyById(basicApplyId);
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (caseEstateService.hasEstateByName(basicEstate.getName(), basicEstate.getProvince(), basicEstate.getCity())) {
            throw new BusinessException("案例中已存在相同名称楼盘");
        }
        return basicApply;
    }
}
