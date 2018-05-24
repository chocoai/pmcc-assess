package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeInfoDao;
import com.copower.pmcc.assess.dal.entity.SchemeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeInfoService {

    @Autowired
    private SchemeInfoDao dao;

    @Transactional
    public int addReturnID(SchemeInfo schemeInfo){
        return dao.addReturnID(schemeInfo);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeInfo schemeInfo){
        return dao.update(schemeInfo);
    }
}
