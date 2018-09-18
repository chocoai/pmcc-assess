package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingFunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/18 11:57
 * @Description:
 */
@Service
public class CaseBuildingFunctionService {
    @Autowired
    private CaseBuildingFunctionDao caseBuildingFunctionDao;
}
