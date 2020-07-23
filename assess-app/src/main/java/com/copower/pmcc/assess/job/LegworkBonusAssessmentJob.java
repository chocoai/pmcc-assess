package com.copower.pmcc.assess.job;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.hr.api.dto.HrLegworkDto;
import com.copower.pmcc.hr.api.provider.HrRpcToolService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kings on 2019-7-8.
 */
@Component
public class LegworkBonusAssessmentJob {
    private final static Logger logger = LoggerFactory.getLogger(LegworkBonusAssessmentJob.class);
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

    /**
     * 执行任务
     */
    public void execute() throws BusinessException {
        String parameter = baseParameterService.getBaseParameter(BaseParameterEnum.LEGWORK_BONUS_ASSESSMENT);
        if (StringUtils.isNotBlank(parameter) && Boolean.valueOf(parameter).equals(Boolean.TRUE)) {
            String lockKey = CacheConstant.getCostsKeyPrefix(LegworkBonusAssessmentJob.class.getSimpleName(), applicationConstant.getAppKey());
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
            //开始处理数据


            logger.info("----LegworkBonusAssessmentJob, end---------");
        }
    }

    public void resave() {
        //1.先读取已经完成的外勤信息，以外勤结束时间作为基准，找出上一个月的外勤信息
        //2.
        Date today = DateUtils.today();
        String firstDayOfMonth = DateUtils.getFirstDayOfMonth(DateUtils.getYear(today), DateUtils.getMonth(today));
        Date firstDate = DateUtils.convertDate(firstDayOfMonth);//本月第一天
        Date preMonthLastDay=DateUtils.addDay(firstDate,-1);//上月的最后一天
        Date preMonthFirstDay= DateUtils.convertDate(DateUtils.getFirstDayOfMonth(DateUtils.getYear(preMonthLastDay), DateUtils.getMonth(preMonthLastDay)));
        List<HrLegworkDto> legworkDtoList = hrRpcToolService.getHrLegworkListByEndDate(preMonthFirstDay, firstDate);
        if(CollectionUtils.isEmpty(legworkDtoList)) return;
        //AssessPhaseKeyConstant.SCENE_EXPLORE

        for (HrLegworkDto dto : legworkDtoList) {
            if(StringUtils.isBlank(dto.getProjectId())) continue;//没关联项目直接跳过

            List<Integer> list = FormatUtils.transformString2Integer(dto.getProjectId());
            List<SysProjectDto> projectDtoList = erpRpcProjectService.getProjectInfoListByProjectIds(list, applicationConstant.getAppKey());
            if(CollectionUtils.isEmpty(projectDtoList)) continue;
            for (SysProjectDto sysProjectDto : projectDtoList) {
                //1.找出该项目中的查勘信息，先确定该项目查勘了几个楼盘，再根据配置和楼盘的区域确定是否做加分处理
                //2.如果需要加分则需找出该项目查勘中所获取的工时得分，及各个成员的工时得分，将总的工时得分乘以系数得到对应加分值
                //3.再根据各个成员工时得分的占比，将加分值分摊到成员上，将相关数据写入到对应的表中


            }
        }
        //1.针对相关的项目经理，都发起一个待处理任务，项目经理确认调整各个项目中的加分值
        //2.发起一个审核流程到技术负责人，可看到本次所有相关的项目的加分值及各个项目经理处理的情况
        //3.技术负责人提交流程后大部分负责人审核，当审核完成后将相关数据写入到考核系统中的考核记录表中

    }
}
