package com.copower.pmcc.assess.job;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
