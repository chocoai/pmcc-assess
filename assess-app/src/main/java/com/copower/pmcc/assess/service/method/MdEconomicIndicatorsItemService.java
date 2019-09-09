package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsItemDao;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;
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
public class MdEconomicIndicatorsItemService {
    @Autowired
    private MdEconomicIndicatorsItemDao mdEconomicIndicatorsItemDao;
    @Autowired
    private CommonService commonService;

    public List<MdEconomicIndicatorsItem> getEconomicIndicatorsItemList(MdEconomicIndicatorsItem mdEconomicIndicatorsItem) {
        return mdEconomicIndicatorsItemDao.getEconomicIndicatorsItemList(mdEconomicIndicatorsItem);
    }

    public void saveEconomicIndicatorsItem(MdEconomicIndicatorsItem mdEconomicIndicatorsItem) {
        if (mdEconomicIndicatorsItem.getId() != null && mdEconomicIndicatorsItem.getId() > 0) {
            mdEconomicIndicatorsItemDao.updateEconomicIndicatorsItem(mdEconomicIndicatorsItem);
        } else {
            mdEconomicIndicatorsItem.setCreator(commonService.thisUserAccount());
            mdEconomicIndicatorsItemDao.addEconomicIndicatorsItem(mdEconomicIndicatorsItem);
        }
    }

    public void deleteItemByEconomicId(Integer economicId){
        mdEconomicIndicatorsItemDao.deleteItemByEconomicId(economicId);
    }
}
