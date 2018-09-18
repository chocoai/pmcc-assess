package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingMaintenanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/18 11:58
 * @Description:
 */
@Service
public class CaseBuildingMaintenanceService {
    @Autowired
    private CaseBuildingMaintenanceDao caseBuildingMaintenanceDao;
}
