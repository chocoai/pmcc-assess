package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsItemDao;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基准地价系数修正法
 *
 * @author noOne
 */

@Service
public class MdEconomicIndicatorsService {
    @Autowired
    private MdEconomicIndicatorsDao mdEconomicIndicatorsDao;
    @Autowired
    private MdEconomicIndicatorsItemDao mdEconomicIndicatorsItemDao;
    @Autowired
    private CommonService commonService;

    public List<MdEconomicIndicators> getEconomicIndicatorsList(MdEconomicIndicators mdEconomicIndicators) {
        return mdEconomicIndicatorsDao.getEconomicIndicatorsList(mdEconomicIndicators);
    }

    public void saveEconomicIndicators(MdEconomicIndicators mdEconomicIndicators) {
        if (mdEconomicIndicators.getId() != null && mdEconomicIndicators.getId() > 0) {
            mdEconomicIndicatorsDao.updateEconomicIndicators(mdEconomicIndicators);
        } else {
            mdEconomicIndicators.setCreator(commonService.thisUserAccount());
            mdEconomicIndicatorsDao.addEconomicIndicators(mdEconomicIndicators);
        }
    }

    public MdEconomicIndicators saveEconomicIndicators(MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto) {
        MdEconomicIndicators mdEconomicIndicators = mdEconomicIndicatorsApplyDto.getEconomicIndicators();
        List<MdEconomicIndicatorsItem> mdEconomicIndicatorsItems = mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList();
        if (mdEconomicIndicators != null) {
            saveEconomicIndicators(mdEconomicIndicators);
        }
        mdEconomicIndicatorsItemDao.deleteItemByEconomicId(mdEconomicIndicators.getId());
        if (CollectionUtils.isNotEmpty(mdEconomicIndicatorsItems)) {
            for (MdEconomicIndicatorsItem mdEconomicIndicatorsItem : mdEconomicIndicatorsItems) {
                mdEconomicIndicatorsItem.setEconomicId(mdEconomicIndicators.getId());
                mdEconomicIndicatorsItemDao.addEconomicIndicatorsItem(mdEconomicIndicatorsItem);
            }
        }
        return mdEconomicIndicators;
    }
}
