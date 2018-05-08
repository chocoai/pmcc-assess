package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.InitiateConsignorEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.ProjectInfoDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.ProcessUserDto;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.project.InitiateContactsVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.*;
import java.util.logging.Logger;

/**
 * 描述:项目基础信息
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:05
 */
@Service
public class ProjectInfoService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ErpAreaService erpAreaService;

    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private BaseDataDicService bidBaseDataDicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Lazy
    @Autowired
    private InitiateContactsService initiateContactsService;

    /**
     * 项目立项申请
     *
     * @param projectInfoDto
     */
    public void projectApply(ProjectInfoDto projectInfoDto) throws BusinessException {
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(projectInfoDto, projectInfo);
        projectInfoDao.saveProjectInfo(projectInfo);
        initProjectInfo(projectInfo);
    }

    /**
     * 初始化项目信息
     *
     * @param projectInfo
     */
    private void initProjectInfo(ProjectInfo projectInfo) throws BusinessException {
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(0, true);
        int i = 1;
        for (ProjectWorkStage item : projectWorkStages) {
            ProjectPlan projectPlan = new ProjectPlan();
            projectPlan.setProjectId(projectInfo.getId());
            projectPlan.setProcessInsId("-1");
            projectPlan.setWorkStageId(item.getId());
            projectPlan.setCategoryId(0);
            projectPlan.setPlanName(item.getWorkStageName());
            projectPlan.setCreator(serviceComponent.getThisUser());
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
            processUserDto = serviceComponent.processStart(processInfo, serviceComponent.getThisUser(), false);
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
        serviceComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
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
        serviceComponent.processSubmitLoopTaskNodeArg(approvalModelDto, true);
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


    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoDao.updateProjectInfo(projectInfo);
    }

    /*获取所有省*/
    public List<SysAreaDto> getProvinceList(){
        return erpAreaService.getProvinceList();
    }

    /*获取城市*/
    public List<SysAreaDto> getAreaList(String pid){
        return erpAreaService.getAreaList(pid);
    }

    /*大类*/
    public List<BaseDataDic> listClass_assess(){
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ASSESS_CLASS);
        return baseDataDics;
    }

    /*委托目的*/
    public List<BaseDataDic> list_entrustment_purpose(){
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        return baseDataDics;
    }

    /*单位性质*/
    public Map<String, Object> getConsignorMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(InitiateConsignorEnum.ONE.getValue() + "", InitiateConsignorEnum.BANK.getDec());
        map.put(InitiateConsignorEnum.THREE.getValue() + "", InitiateConsignorEnum.OTHER.getDec());
        map.put(InitiateConsignorEnum.TWO.getValue() + "", InitiateConsignorEnum.GOVERNMENT_AFFILIATED_INSTITUTIONS.getDec());
        return map;
    }

    /*联系人 vo*/
    public BootstrapTableVo listContactsVo(Integer crmId,Integer flag) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InitiateContactsVo> vos = initiateContactsService.listVo(crmId,flag);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InitiateContactsVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /*联系人类型*/
    public Map<String,String> getTypeInitiateContactsMap(){
        return initiateContactsService.getTypeMap();
    }

    //添加联系人
    public boolean addContacts(InitiateContactsDto dto){
        return initiateContactsService.add(dto);
    }

    /*联系人删除*/
    public boolean removeContacts(Integer id){return initiateContactsService.remove(id);}
}
