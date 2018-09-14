package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateParkingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/14 16:48
 * @Description:
 */
@Service
public class CaseEstateParkingService {
    @Autowired
    private CaseEstateParkingDao caseEstateParkingDao;
}
