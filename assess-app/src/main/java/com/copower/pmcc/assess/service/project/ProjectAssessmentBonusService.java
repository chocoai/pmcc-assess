package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectAssessmentBonusDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectAssessmentBonusItemVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataAreaAssessmentBonusService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.hr.api.dto.HrLegworkDto;
import com.copower.pmcc.hr.api.provider.HrRpcToolService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service
public class ProjectAssessmentBonusService {
    @Autowired
    private ProjectAssessmentBonusDao projectAssessmentBonusDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DataAreaAssessmentBonusService dataAreaAssessmentBonusService;
    @Autowired
    private ChksRpcAssessmentPerformanceService assessmentPerformanceService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private HrRpcToolService hrRpcToolService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 相同标题  相同年  相同月  视为一个组合主键  不能与其相同
     *
     * @param title
     * @param year
     * @param month
     * @return
     */
    public Long getProjectAssessmentBonusByCount(String title, Integer year, Integer month) {
        return projectAssessmentBonusDao.getProjectAssessmentBonusByCount(title, year, month);
    }

    public BootstrapTableVo<ProjectAssessmentBonus> getProjectAssessmentBonusDataList(String processInsId, String title, String status, String creator, Integer year, Integer month) {
        BootstrapTableVo<ProjectAssessmentBonus> bootstrapTableVo = new BootstrapTableVo<>();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectAssessmentBonus> list = getProjectAssessmentBonusByWhere(processInsId, title, status, creator, year, month);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>());
        return bootstrapTableVo;
    }

    public List<ProjectAssessmentBonus> getProjectAssessmentBonusByWhere(String processInsId, String title, String status, String creator, Integer year, Integer month) {
        return projectAssessmentBonusDao.getProjectAssessmentBonusByWhere(processInsId, title, status, creator, year, month);
    }

    public ProjectAssessmentBonus getAssessmentBonusById(Integer id) {
        return projectAssessmentBonusDao.getAssessmentBonusById(id);
    }

    public ProjectAssessmentBonus getAssessmentBonusByProcessInsId(String processInsId) {
        return projectAssessmentBonusDao.getAssessmentBonusByProcessInsId(processInsId);
    }

    public void saveAssessmentBonus(ProjectAssessmentBonus assessmentBonus) {
        if (assessmentBonus.getId() != null && assessmentBonus.getId() > 0) {
            projectAssessmentBonusDao.updateAssessmentBonus(assessmentBonus);
        } else {
            assessmentBonus.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonus(assessmentBonus);
        }
    }

    public void saveAssessmentBonusItem(ProjectAssessmentBonusItem assessmentBonusItem) {
        if (assessmentBonusItem.getId() != null && assessmentBonusItem.getId() > 0) {
            ProjectAssessmentBonusItem bonusItem = projectAssessmentBonusDao.getAssessmentBonusItemById(assessmentBonusItem.getId());
            ProjectAssessmentBonusItemHistory history = new ProjectAssessmentBonusItemHistory();
            BeanUtils.copyProperties(bonusItem, history);
            history.setItemId(assessmentBonusItem.getId());
            projectAssessmentBonusDao.addAssessmentBonusItemHistory(history);//存档到历史

            assessmentBonusItem.setExamineUserAccount(commonService.thisUserAccount());
            assessmentBonusItem.setExamineUserName(commonService.thisUser().getUserName());
            projectAssessmentBonusDao.updateAssessmentBonusItem(assessmentBonusItem);
        } else {
            assessmentBonusItem.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonusItem(assessmentBonusItem);
        }
    }

    /**
     * 根据项目id获取数量
     *
     * @param projectId
     * @return
     */
    public Long getAssessmentBonusItemCount(Integer projectId) {
        return projectAssessmentBonusDao.getAssessmentBonusItemCount(projectId);
    }

    /**
     * 获取数据
     *
     * @param bonusId
     * @param projectManager
     * @return
     */
    public BootstrapTableVo getAssessmentBonusItems(Integer bonusId, String projectManager) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (bonusId == null) return bootstrapTableVo;
        List<ProjectAssessmentBonusItem> bonusItemList = projectAssessmentBonusDao.getAssessmentBonusItemList(bonusId, projectManager);
        List<ProjectAssessmentBonusItemVo> itemVos = LangUtils.transform(bonusItemList, o -> getAssessmentBonusItemVo(o));
        bootstrapTableVo.setRows(itemVos);
        return bootstrapTableVo;
    }

    public List<ProjectAssessmentBonusItem> getAssessmentBonusItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        return projectAssessmentBonusDao.getAssessmentBonusItemList(masterId, null);
    }

    public List<ProjectAssessmentBonusItem> getAssessmentBonusItemsByManager(Integer masterId, String projectManager) {
        if (masterId == null || StringUtils.isBlank(projectManager)) return null;
        return projectAssessmentBonusDao.getAssessmentBonusItemList(masterId, projectManager);
    }

    public ProjectAssessmentBonusItemVo getAssessmentBonusItemVo(ProjectAssessmentBonusItem assessmentBonusItem) {
        if (assessmentBonusItem == null) return null;
        ProjectAssessmentBonusItemVo vo = new ProjectAssessmentBonusItemVo();
        BeanUtils.copyProperties(assessmentBonusItem, vo);
        vo.setProjectManagerName(publicService.getUserNameByAccount(assessmentBonusItem.getProjectManager()));
        return vo;
    }

    /**
     * 是否全部完成
     *
     * @param masterId
     * @return
     */
    public Boolean isAllFinish(Integer masterId) {
        Long count = projectAssessmentBonusDao.getAssessmentBonusItemCountByStatus(masterId, ProjectStatusEnum.WAIT.getKey());
        return count <= 0;
    }

    /**
     * 获取历史数据信息
     *
     * @param itemId
     * @return
     */
    public BootstrapTableVo getAssessmentBonusItemHistorys(Integer itemId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectAssessmentBonusItemHistory> bonusItemHistories = projectAssessmentBonusDao.getAssessmentBonusItemHistoryList(itemId);
        bootstrapTableVo.setRows(bonusItemHistories);
        return bootstrapTableVo;
    }


    /**
     * 外勤加分考核 重新发起
     *
     * @param assessmentBonus
     * @throws BusinessException
     */
    @Transactional(rollbackFor = {Exception.class})
    public void afreshAssessmentBonusTask(ProjectAssessmentBonus assessmentBonus) throws BusinessException {
        if (assessmentBonus == null) {
            throw new BusinessException("参数异常");
        }
        //删除 子数据 历史数据
        List<ProjectAssessmentBonusItem> assessmentBonusItemList = projectAssessmentBonusDao.getAssessmentBonusItemList(assessmentBonus.getId(), null);
        if (CollectionUtils.isNotEmpty(assessmentBonusItemList)) {
            List<Integer> integerList = LangUtils.transform(assessmentBonusItemList, obj -> obj.getId());
            projectAssessmentBonusDao.deleteProjectAssessmentBonusItemHistoryByItemIds(integerList);
        }
        //删除 子数据
        projectAssessmentBonusDao.deleteProjectAssessmentBonusItemByMasterId(assessmentBonus.getId());
        //删除 已经填了的考核数据
        AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
        performanceDto.setAppKey(applicationConstant.getAppKey());
        performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
        performanceDto.setAssessmentKey(AssessmentTypeEnum.WORK_HOURS.getValue());
        performanceDto.setExamineStatus(ProcessStatusEnum.FINISH.getValue());
        performanceDto.setProcessInsId(assessmentBonus.getProcessInsId());
        List<AssessmentPerformanceDto> performanceDtoList = assessmentPerformanceService.getPerformancesByParam(performanceDto);
        if (CollectionUtils.isNotEmpty(performanceDtoList)) {
            List<Integer> integerList = LangUtils.transform(performanceDtoList, obj -> obj.getId());
            assessmentPerformanceService.deletePerformanceByIds(integerList);
        }

        //流程实例关闭
        if (!assessmentBonus.getStatus().equals(ProcessStatusEnum.FINISH.getValue())) {
            try {
                //允许报错  这里有可能都没有发起流程的时候afreshAssessmentBonusTask就被调用了(重新发起考核的时候会清除所有任务和数据)
                if (StringUtils.isNotBlank(assessmentBonus.getProcessInsId())) {
                    if (!"0".equals(assessmentBonus.getProcessInsId())) {
                        bpmRpcActivitiProcessManageService.closeProcess(assessmentBonus.getProcessInsId());
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
        projectPlanResponsibility.setBusinessKey(ProjectAssessmentBonus.class.getSimpleName());
        projectPlanResponsibility.setBusinessId(assessmentBonus.getId());
        projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectPlanResponsibility);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            List<Integer> integerList = LangUtils.transform(projectTaskList, obj -> obj.getId());
            for (Integer integer : integerList) {
                bpmRpcProjectTaskService.deleteProjectTask(integer);
            }
        }

        assessmentBonus.setProcessInsId("0");
        //删除完毕 重新发起 外勤加分考核
        runAssessmentBonusTask(assessmentBonus);
    }

    /**
     * 获取 外勤记录
     *
     * @param assessmentBonus
     * @return
     * @throws BusinessException
     */
    public List<HrLegworkDto> getHrLegworkDtoList(ProjectAssessmentBonus assessmentBonus) throws BusinessException {
        if (assessmentBonus == null) {
            throw new BusinessException("参数异常");
        }
        String firstDayOfMonth = DateUtils.getFirstDayOfMonth(assessmentBonus.getYear(), assessmentBonus.getLegworkStartMonth());//获得该月第一天
        Date firstDate = DateUtils.convertDate(firstDayOfMonth);//本月第一天 time
        String lastDayOfMonth = DateUtils.getLastDayOfMonth(assessmentBonus.getYear(), assessmentBonus.getLegworkEndMonth());//获得该月最后一天
        Date endDate = DateUtils.convertDate(lastDayOfMonth);//本月第最后一天 time
        List<HrLegworkDto> legworkDtoList = hrRpcToolService.getHrLegworkListByEndDate(firstDate, endDate);
        return legworkDtoList;
    }

    /**
     * 外勤加分考核
     *
     * @param assessmentBonus
     * @throws BusinessException
     */
    @Transactional(rollbackFor = {Exception.class})
    public void launchAssessmentBonusTask(ProjectAssessmentBonus assessmentBonus) throws BusinessException {
        runAssessmentBonusTask(assessmentBonus);
    }

    private void runAssessmentBonusTask(ProjectAssessmentBonus assessmentBonus) throws BusinessException {
        if (assessmentBonus == null) {
            throw new BusinessException("参数异常");
        }
        List<HrLegworkDto> legworkDtoList = getHrLegworkDtoList(assessmentBonus);
        if (CollectionUtils.isEmpty(legworkDtoList)) return;
        if (StringUtils.isBlank(assessmentBonus.getTitle())) {
            assessmentBonus.setTitle(String.format("%s年-%s月外勤加分考核", assessmentBonus.getYear(), assessmentBonus.getMonth()));
        }
        final int projectId = -1;//
        assessmentBonus.setStatus(ProcessStatusEnum.RUN.getValue());
        saveAssessmentBonus(assessmentBonus);
        Set<String> managerList = Sets.newHashSet();
        String parameter = baseParameterService.getBaseParameter(BaseParameterEnum.ASSESSMENT_TASK_GENERATE_PROJECT_ID);
        List<Integer> projectIds = FormatUtils.transformString2Integer(parameter);

        for (HrLegworkDto dto : legworkDtoList) {
            if (StringUtils.isBlank(dto.getProjectId())) continue;//没关联项目直接跳过
            List<Integer> list = FormatUtils.transformString2Integer(dto.getProjectId());
            List<SysProjectDto> projectDtoList = erpRpcProjectService.getProjectInfoListByProjectIds(list, applicationConstant.getAppKey());
            if (CollectionUtils.isEmpty(projectDtoList)) continue;
            //只取评估系统的外勤数据
            projectDtoList = LangUtils.filter(projectDtoList, o -> applicationConstant.getAppKey().equalsIgnoreCase(o.getAppKey()));
            for (SysProjectDto sysProjectDto : projectDtoList) {
                //1.找出该项目中的查勘信息，先确定该项目查勘了几个楼盘，再根据配置和楼盘的区域确定是否做加分处理
                //2.如果需要加分则需找出该项目查勘中所获取的工时得分，及各个成员的工时得分，将总的工时得分乘以系数得到对应加分值
                //3.再根据各个成员工时得分的占比，将加分值分摊到成员上，将相关数据写入到对应的表中
                if (CollectionUtils.isNotEmpty(projectIds) && !projectIds.contains(sysProjectDto.getProjectId())) {
                    continue;//不处理
                }
                if (getAssessmentBonusItemCount(sysProjectDto.getProjectId()) > 0) {
                    continue;//每个项目只计算一次
                }
                if (!projectInfoService.chksValidProject(sysProjectDto.getProjectId())) {
                    continue;//没有在配置中的项目
                }
                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(sysProjectDto.getProjectId());
                String projectManager = projectMemberService.getProjectManager(projectInfo.getId());//项目经理
                managerList.add(projectManager);
                List<BasicApplyBatch> basicApplyBatchList = getBasicApplyBatchList(projectInfo);
                if (CollectionUtils.isEmpty(basicApplyBatchList)) continue;
                BigDecimal totalBonusScore = BigDecimal.ZERO;
                List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
                for (BasicApplyBatch basicApplyBatch : basicApplyBatchList) {
                    BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
                    //找出该楼盘下的工时考核得分
                    List<AssessmentPerformanceDto> resultPerformanceDtos = Lists.newArrayList();
                    AssessmentPerformanceDto where = new AssessmentPerformanceDto();
                    where.setProjectId(projectInfo.getId());
                    where.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
                    where.setBisEffective(true);
                    where.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                    List<AssessmentPerformanceDto> performanceDtos = assessmentPerformanceService.getPerformancesByParam(where);
                    if (CollectionUtils.isNotEmpty(performanceDtos))
                        resultPerformanceDtos.addAll(performanceDtos);
                    BasicApplyBatch refBatch = basicApplyBatchService.getBasicApplyBatchByRefId(basicApplyBatch.getId());
                    if (refBatch != null) {
                        where.setPlanDetailsId(refBatch.getPlanDetailsId());
                        performanceDtos = assessmentPerformanceService.getPerformancesByParam(where);
                        if (CollectionUtils.isNotEmpty(performanceDtos))
                            resultPerformanceDtos.addAll(performanceDtos);
                    }
                    //找出各个人员的工时得分情况
                    if (CollectionUtils.isNotEmpty(resultPerformanceDtos)) {
                        Map<String, BigDecimal> map = statisticalScoreByUser(resultPerformanceDtos);
                        if (!map.isEmpty()) {
                            KeyValueDto keyValueDto = new KeyValueDto();
                            for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                                SysUserDto sysUser = erpRpcUserService.getSysUser(entry.getKey());
                                DataAreaAssessmentBonus bonus = dataAreaAssessmentBonusService.getDataAreaAssessmentBonusByArea(sysUser.getDepartmentId(), basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict());
                                if (bonus == null) continue;
                                KeyValueDto valueDto = getKeyValueDtoByKey(keyValueDtoList, entry.getKey());
                                BigDecimal bonusScore = entry.getValue().multiply(bonus.getCoefficient());
                                totalBonusScore = totalBonusScore.add(bonusScore);
                                if (valueDto == null) {
                                    keyValueDto.setKey(entry.getKey());
                                    keyValueDto.setValue(String.valueOf(bonusScore.setScale(2, BigDecimal.ROUND_HALF_UP)));
                                    keyValueDto.setExplain(publicService.getUserNameByAccount(entry.getKey()));
                                    keyValueDtoList.add(keyValueDto);
                                } else {
                                    String value = valueDto.getValue();
                                    valueDto.setValue(String.valueOf(new BigDecimal(value).add(bonusScore).setScale(2, RoundingMode.HALF_UP)));
                                }
                            }
                        }
                    }
                }
                if (totalBonusScore.compareTo(BigDecimal.ZERO) <= 0) continue;
                ProjectAssessmentBonusItem assessmentBonusItem = new ProjectAssessmentBonusItem();
                assessmentBonusItem.setMasterId(assessmentBonus.getId());
                assessmentBonusItem.setProjectId(projectInfo.getId());
                assessmentBonusItem.setProjectName(projectInfo.getProjectName());
                assessmentBonusItem.setProjectManager(projectManager);
                assessmentBonusItem.setExamineUserAccount(projectManager);
                assessmentBonusItem.setExamineUserName(publicService.getUserNameByAccount(projectManager));
                assessmentBonusItem.setMemberScoreCondition(JSON.toJSONString(keyValueDtoList));
                assessmentBonusItem.setTotalScore(totalBonusScore);
                assessmentBonusItem.setStatus(ProjectStatusEnum.WAIT.getKey());
                saveAssessmentBonusItem(assessmentBonusItem);
            }
        }

        //1.针对相关的项目经理，都发起一个待处理任务，项目经理确认调整各个项目中的加分值
        //2.发起一个待处理任务到技术负责人，可看到本次所有相关的项目的加分值及各个项目经理处理的情况
        //3.技术负责人提交流程后到部门负责人审核，当审核完成后将相关数据写入到考核系统中的考核记录表中
        if (CollectionUtils.isNotEmpty(managerList)) {
            for (String manager : managerList) {//为项目经理添加任务
                List<ProjectAssessmentBonusItem> bonusItemList = projectAssessmentBonusDao.getAssessmentBonusItemList(assessmentBonus.getId(), manager);
                if (CollectionUtils.isEmpty(bonusItemList)) continue;
                String url = String.format("/%s/projectAssessmentBonus/index?bonusId=%s", applicationConstant.getAppKey(), assessmentBonus.getId());
                ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
                projectPlanResponsibility.setBusinessKey(ProjectStatusEnum.NORMAL.getKey());
                projectPlanResponsibility.setPlanDetailsName(String.format("【%s】", publicService.getUserNameByAccount(manager)));
                projectPlanResponsibility.setProjectName(assessmentBonus.getTitle());
                projectPlanResponsibility.setUserAccount(manager);
                projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
                projectPlanResponsibility.setCreator(manager);
                projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                projectPlanResponsibility.setUrl(url);
                projectPlanResponsibility.setPlanEndTime(new Date());
                projectPlanResponsibility.setProjectId(projectId);
                //这两个参数用作清除任务的时候查询用
                projectPlanResponsibility.setBusinessKey(ProjectAssessmentBonus.class.getSimpleName());
                projectPlanResponsibility.setBusinessId(assessmentBonus.getId());
                bpmRpcProjectTaskService.saveProjectTask(projectPlanResponsibility);
            }

            //为技术负责人添加任务
            SysDepartmentDto departmentAssess = erpRpcDepartmentService.getDepartmentAssess();
            List<String> jszgList = bpmRpcBoxRoleUserService.getDepartmentJszg(departmentAssess.getId());//技术负责人
            if (CollectionUtils.isNotEmpty(jszgList)) {
                for (String s : jszgList) {
                    String url = String.format("/%s/projectAssessmentBonus/apply?bonusId=%s", applicationConstant.getAppKey(), assessmentBonus.getId());
                    ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
                    projectPlanResponsibility.setBusinessKey(ProjectStatusEnum.NORMAL.getKey());
                    projectPlanResponsibility.setPlanDetailsName(String.format("【%s】", publicService.getUserNameByAccount(s)));
                    projectPlanResponsibility.setProjectName(assessmentBonus.getTitle());
                    projectPlanResponsibility.setUserAccount(s);
                    projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
                    projectPlanResponsibility.setCreator(s);
                    projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                    projectPlanResponsibility.setUrl(url);
                    projectPlanResponsibility.setProjectId(projectId);
                    projectPlanResponsibility.setPlanEndTime(new Date());
                    //这两个参数用作清除任务的时候查询用
                    projectPlanResponsibility.setBusinessKey(ProjectAssessmentBonus.class.getSimpleName());
                    projectPlanResponsibility.setBusinessId(assessmentBonus.getId());
                    bpmRpcProjectTaskService.saveProjectTask(projectPlanResponsibility);
                }
            }
        }
    }


    //确定本项目查勘了几个楼盘
    private List<BasicApplyBatch> getBasicApplyBatchList(ProjectInfo projectInfo) {
        String sceneExploreKey = AssessPhaseKeyConstant.SCENE_EXPLORE;
        String caseStudyExtend = AssessPhaseKeyConstant.CASE_STUDY_EXTEND;
        List<ProjectPlanDetails> detailsList = Lists.newArrayList();
        List<ProjectPlanDetails> sceneDetailsList = projectPlanDetailsService.getProjectPlanDetailsByPhaseKey(projectInfo.getId(), projectInfo.getProjectCategoryId(), sceneExploreKey);
        List<ProjectPlanDetails> caseDetailsList = projectPlanDetailsService.getProjectPlanDetailsByPhaseKey(projectInfo.getId(), projectInfo.getProjectCategoryId(), caseStudyExtend);
        if (CollectionUtils.isNotEmpty(sceneDetailsList))
            detailsList.addAll(sceneDetailsList);
        if (CollectionUtils.isNotEmpty(caseDetailsList))
            detailsList.addAll(caseDetailsList);
        if (CollectionUtils.isEmpty(detailsList)) return null;
        List<BasicApplyBatch> batchs = basicApplyBatchService.getBasicApplyBatchsByPlanDetailsIds(LangUtils.transform(detailsList, o -> o.getId()));
        if (CollectionUtils.isEmpty(batchs)) return null;
        return LangUtils.filter(batchs, o -> o.getReferenceApplyBatchId() == null);
    }


    //统计项目各个成员得分
    private Map<String, BigDecimal> statisticalScoreByUser(List<AssessmentPerformanceDto> performanceDtos) {
        Map<String, BigDecimal> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(performanceDtos)) {
            for (AssessmentPerformanceDto performanceDto : performanceDtos) {
                if (map.containsKey(performanceDto.getByExaminePeople())) {
                    BigDecimal bigDecimal = map.get(performanceDto.getByExaminePeople());
                    if (bigDecimal != null) {
                        map.put(performanceDto.getByExaminePeople(), performanceDto.getExamineScore().add(bigDecimal));
                    }
                } else {
                    map.put(performanceDto.getByExaminePeople(), performanceDto.getExamineScore());
                }
            }
        }
        return map;
    }

    private KeyValueDto getKeyValueDtoByKey(List<KeyValueDto> keyValueDtoList, String key) {
        if (CollectionUtils.isEmpty(keyValueDtoList) || StringUtils.isBlank(key)) return null;
        for (KeyValueDto keyValueDto : keyValueDtoList) {
            if (key.equalsIgnoreCase(keyValueDto.getKey()))
                return keyValueDto;
        }
        return null;
    }

}
