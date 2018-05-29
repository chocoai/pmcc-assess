package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.BaseAssistDao;
import com.copower.pmcc.assess.dal.entity.BaseAssist;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotationEntity;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 11:24
 */
@Service
public class BaseAssistService {
    @Autowired
    private BaseAssistDao baseAssistDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<BaseAssist> getBaseAssist(String assistType, String search) {
        List<BaseAssist> BaseAssists = null;
        String cacheKey = CacheConstant.getCostsKeyPrefix(BaseConstant.PMCC_ASSESS_BASE_ASSIST, assistType);
        try {
            BaseAssists = LangUtils.listCache(cacheKey, assistType, BaseAssist.class, input -> baseAssistDao.getBaseAssist(input));
        } catch (Exception e) {
            BaseAssists = baseAssistDao.getBaseAssist(assistType);
        }
        List<BaseAssist> filter = LangUtils.filter(BaseAssists, o -> {
            return o.getName().contains(search);
        });
        return filter;

    }

    public void initBaseAssist(List<WorkFlowAnnotationEntity> workFlowAnnotationEntities, String type) {
        for (WorkFlowAnnotationEntity item : workFlowAnnotationEntities) {
            BaseAssist BaseAssist = new BaseAssist();
            BaseAssist.setName(item.getAnnotationDes());
            BaseAssist.setAssistName(item.getBeanName());
            BaseAssist.setAssistType(type);
            Boolean aBoolean = baseAssistDao.updateBaseAssistByAssistName(BaseAssist, item.getBeanName());
            if (!aBoolean) {
                baseAssistDao.insertBaseAssist(BaseAssist);
            }
        }
        processControllerComponent.removeRedisKeyValues(BaseConstant.PMCC_ASSESS_BASE_ASSIST, type);
    }
}
