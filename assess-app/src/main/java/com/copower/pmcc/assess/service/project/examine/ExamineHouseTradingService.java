package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-7-6.
 */
@Service
public class ExamineHouseTradingService {
    @Autowired
    private ExamineHouseTradingDao examineHouseTradingDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取版块
     *
     * @param id
     * @return
     */
    public ExamineHouseTrading getHouseTradingById(Integer id) {
        return examineHouseTradingDao.getHouseTradingById(id);
    }

    /**
     * 保存版块
     *
     * @param examineHouseTrading
     */
    @Transactional
    public Integer saveHouseTrading(ExamineHouseTrading examineHouseTrading) throws BusinessException {
        if (examineHouseTrading == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineHouseTrading.getId() != null && examineHouseTrading.getId() > 0) {
            examineHouseTradingDao.updateHouseTrading(examineHouseTrading);
        } else {
            examineHouseTrading.setCreator(commonService.thisUserAccount());
            examineHouseTradingDao.addHouseTrading(examineHouseTrading);
        }
        return examineHouseTrading.getId();
    }
}
