package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateSupplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/14 16:52
 * @Description:
 */
@Service
public class CaseEstateSupplyService {
    @Autowired
    private CaseEstateSupplyDao caseEstateSupplyDao;
}
