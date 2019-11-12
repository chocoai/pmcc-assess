package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.dao.data.ToolRewardRateDao;
import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRate;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huhao
 * @Date: 2018/9/11 10:01
 */
@Service
public class ToolRewardRateService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ToolRewardRateDao toolRewardRateDao;

    public void  copyData(Integer copyId,ToolRewardRate target){
        if (copyId == null){
            return;
        }
        ToolRewardRate toolRewardRate= getToolRewardRateById(copyId) ;
        if (toolRewardRate == null){
            return;
        }
        toolRewardRate.setId(null);
        toolRewardRate.setCreator(null);
        BeanCopyHelp.copyPropertiesIgnoreNull(toolRewardRate, target);
        target.setCreator(commonService.thisUserAccount());
        toolRewardRateDao.addObject(target) ;
    }

    public ToolRewardRate saveToolRewardRate(String formData) throws Exception {
        ToolRewardRate toolRewardRate = JSON.parseObject(formData, ToolRewardRate.class);
        if (toolRewardRate.getId() != null && toolRewardRate.getId() != 0) {
            toolRewardRateDao.updateObject(toolRewardRate);
        } else {
            toolRewardRate.setCreator(commonService.thisUserAccount());
            toolRewardRateDao.addObject(toolRewardRate);
        }
        return toolRewardRate;
    }

    public ToolRewardRate getToolRewardRateById(Integer id)  {
        ToolRewardRate toolRewardRate = toolRewardRateDao.getSingleObject(id);
        return toolRewardRate;
    }

}
