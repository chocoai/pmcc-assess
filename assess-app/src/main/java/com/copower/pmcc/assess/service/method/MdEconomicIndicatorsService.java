package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsDao;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicators;
import com.copower.pmcc.erp.common.CommonService;
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


}
