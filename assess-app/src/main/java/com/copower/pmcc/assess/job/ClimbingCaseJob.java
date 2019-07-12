package com.copower.pmcc.assess.job;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.service.NetAuctionInfoService;
import com.copower.pmcc.erp.constant.CacheConstant;
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
public class ClimbingCaseJob {
    private final static Logger logger = LoggerFactory.getLogger(ClimbingCaseJob.class);
    private final static String JOB_RUNNING_LOCK_KEY = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_JOB_CLIMBING_CASE, "lock");
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private NetAuctionInfoService netAuctionInfoService;
    /**
     * 爬取案例任务
     */
    public void climbing(){
        RLock lock = redissonClient.getLock(JOB_RUNNING_LOCK_KEY);
        // 尝试加锁,最多等待10秒,上锁以后20秒自动解锁
        boolean res = false;
        try {
            res = lock.tryLock(10, 20*60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.debug("get the lock error;"+e.getMessage(),e);
        }
        //加锁不成功,不执行逻辑
        if (!res) {
            logger.debug("----ClimbingCaseJob, Did not get the lock");
            return;
        }
        logger.info("----ClimbingCaseJob, start---------");

        //抓取数据
        netAuctionInfoService.climbingData();

        logger.info("----ClimbingCaseJob, end---------");
    }
}
