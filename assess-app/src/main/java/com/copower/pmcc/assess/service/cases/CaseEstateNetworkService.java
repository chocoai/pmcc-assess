package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateNetworkDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/14 16:47
 * @Description:
 */
@Service
public class CaseEstateNetworkService {
    @Autowired
    private CaseEstateNetworkDao caseEstateNetworkDao;
}
