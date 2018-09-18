package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingFunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zch
 * @Date: 2018/9/18 11:56
 * @Description:
 */
@Repository
public class CaseBuildingFunctionDao {
    @Autowired
    private CaseBuildingFunctionMapper caseBuildingFunctionMapper;
}
