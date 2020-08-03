package com.copower.pmcc.assess.job;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataAreaAssessmentBonusService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.hr.api.dto.HrLegworkDto;
import com.copower.pmcc.hr.api.provider.HrRpcToolService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by kings on 2019-7-8.
 */
@Component
public class AssessmentBonusJob {
    private final static Logger logger = LoggerFactory.getLogger(AssessmentBonusJob.class);
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private HrRpcToolService hrRpcToolService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectAssessmentBonusService projectAssessmentBonusService;
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
    private PublicService publicService;

    /**
     * 执行任务
     */
    public void execute() throws BusinessException {
        String parameter = baseParameterService.getBaseParameter(BaseParameterEnum.LEGWORK_BONUS_ASSESSMENT);
        if (StringUtils.isNotBlank(parameter) && Boolean.valueOf(parameter).equals(Boolean.TRUE)) {
            String lockKey = CacheConstant.getCostsKeyPrefix(AssessmentBonusJob.class.getSimpleName(), applicationConstant.getAppKey());
            RLock lock = redissonClient.getLock(lockKey);
            boolean res = false;
            try {
                res = lock.tryLock(10, 20 * 60, TimeUnit.SECONDS); // 尝试加锁,最多等待10秒,上锁以后20*60秒自动解锁
            } catch (InterruptedException e) {
                logger.debug("get the lock error;" + e.getMessage(), e);
            }
            if (!res) {//加锁不成功,不执行逻辑
                logger.debug("----LegworkBonusAssessmentJob, Did not get the lock");
                return;
            }
            logger.info("----LegworkBonusAssessmentJob, start---------");
            launchAssessmentBonusTask();//发起任务
            logger.info("----LegworkBonusAssessmentJob, end---------");
        }
    }

    public void launchAssessmentBonusTask() throws BusinessException {
        Date today = DateUtils.today();
        String firstDayOfMonth = DateUtils.getFirstDayOfMonth(DateUtils.getYear(today), DateUtils.getMonth(today));
        Date firstDate = DateUtils.convertDate(firstDayOfMonth);//本月第一天
        Date preMonthLastDay = DateUtils.addDay(firstDate, -1);//上月的最后一天
        Date preMonthFirstDay = DateUtils.convertDate(DateUtils.getFirstDayOfMonth(DateUtils.getYear(preMonthLastDay), DateUtils.getMonth(preMonthLastDay)));
        List<HrLegworkDto> legworkDtoList = hrRpcToolService.getHrLegworkListByEndDate(preMonthFirstDay, firstDate);
        if (CollectionUtils.isEmpty(legworkDtoList)) return;
        String title=String.format("%s外勤加分考核", DateUtils.formatDate(preMonthFirstDay, DateUtils.MONTH_PATTERN));
        ProjectAssessmentBonus assessmentBonus = new ProjectAssessmentBonus();
        assessmentBonus.setTitle(title);
        assessmentBonus.setYear(DateUtils.getYear(preMonthFirstDay));
        assessmentBonus.setMonth(DateUtils.getMonth(preMonthFirstDay));
        assessmentBonus.setStatus(ProcessStatusEnum.RUN.getValue());
        projectAssessmentBonusService.saveAssessmentBonus(assessmentBonus);
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
                if (CollectionUtils.isNotEmpty(projectIds) && !projectIds.contains(sysProjectDto.getProjectId()))
                    continue;//不处理

                if (projectAssessmentBonusService.getAssessmentBonusItemCount(sysProjectDto.getProjectId()) > 0) {
                    continue;//每个项目只计算一次
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
                    DataAreaAssessmentBonus bonus = dataAreaAssessmentBonusService.getDataAreaAssessmentBonusByArea(basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict());
                    if (bonus == null) continue;
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
                                KeyValueDto valueDto = getKeyValueDtoByKey(keyValueDtoList, entry.getKey());
                                BigDecimal bonusScore = entry.getValue().multiply(bonus.getCoefficient());
                                totalBonusScore = totalBonusScore.add(bonusScore);
                                if (valueDto == null) {
                                    keyValueDto.setKey(entry.getKey());
                                    keyValueDto.setValue(String.valueOf(bonusScore.setScale(2,BigDecimal.ROUND_HALF_UP)));
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
                ProjectAssessmentBonusItem assessmentBonusItem = new ProjectAssessmentBonusItem();
                assessmentBonusItem.setMasterId(assessmentBonus.getId());
                assessmentBonusItem.setProjectId(projectInfo.getId());
                assessmentBonusItem.setProjectName(projectInfo.getProjectName());
                assessmentBonusItem.setProjectManager(projectManager);
                assessmentBonusItem.setMemberScoreCondition(JSON.toJSONString(keyValueDtoList));
                assessmentBonusItem.setTotalScore(totalBonusScore);
                assessmentBonusItem.setStatus(ProjectStatusEnum.WAIT.getKey());
                projectAssessmentBonusService.saveAssessmentBonusItem(assessmentBonusItem);
            }
        }

        //1.针对相关的项目经理，都发起一个待处理任务，项目经理确认调整各个项目中的加分值
        //2.发起一个待处理任务到技术负责人，可看到本次所有相关的项目的加分值及各个项目经理处理的情况
        //3.技术负责人提交流程后大部分负责人审核，当审核完成后将相关数据写入到考核系统中的考核记录表中
        if (CollectionUtils.isNotEmpty(managerList)) {
            for (String manager : managerList) {//为项目经理添加任务
                String url = String.format("/%s/projectAssessmentBonus/index?bonusId=%s", applicationConstant.getAppKey(), assessmentBonus.getId());
                ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
                projectPlanResponsibility.setPlanDetailsName(String.format("【%s】", publicService.getUserNameByAccount(manager)));
                projectPlanResponsibility.setProjectName(title);
                projectPlanResponsibility.setUserAccount(manager);
                projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
                projectPlanResponsibility.setCreator(manager);
                projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                projectPlanResponsibility.setUrl(url);
                bpmRpcProjectTaskService.saveProjectTask(projectPlanResponsibility);
            }

            //为技术负责人添加任务
            SysDepartmentDto departmentAssess = erpRpcDepartmentService.getDepartmentAssess();
            List<String> jszgList = bpmRpcBoxRoleUserService.getDepartmentJszg(departmentAssess.getId());//技术负责人
            if (CollectionUtils.isNotEmpty(jszgList)) {
                for (String s : jszgList) {
                    String url = String.format("/%s/projectAssessmentBonus/apply?bonusId=%s", applicationConstant.getAppKey(), assessmentBonus.getId());
                    ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
                    projectPlanResponsibility.setPlanDetailsName(String.format("【%s】", publicService.getUserNameByAccount(s)));
                    projectPlanResponsibility.setProjectName(title);
                    projectPlanResponsibility.setUserAccount(s);
                    projectPlanResponsibility.setModel(ResponsibileModelEnum.TASK.getId());
                    projectPlanResponsibility.setCreator(s);
                    projectPlanResponsibility.setAppKey(applicationConstant.getAppKey());
                    projectPlanResponsibility.setUrl(url);
                    bpmRpcProjectTaskService.saveProjectTask(projectPlanResponsibility);
                }
            }
        }
    }

    //确定本项目查勘了几个楼盘
    private List<BasicApplyBatch> getBasicApplyBatchList(ProjectInfo projectInfo) {
        String sceneExploreKey = AssessPhaseKeyConstant.SCENE_EXPLORE;
        List<ProjectPlanDetails> detailsList = projectPlanDetailsService.getProjectPlanDetailsByPhaseKey(projectInfo.getId(), projectInfo.getProjectCategoryId(), sceneExploreKey);
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
                    map.put(performanceDto.getByExaminePeople(), performanceDto.getExamineScore().add(bigDecimal));
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
