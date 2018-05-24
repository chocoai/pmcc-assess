package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.mapper.SchemeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class SchemeInfoDao {
    @Autowired
    private SchemeInfoMapper mapper;

    public int addReturnID(SchemeInfo schemeInfo){
        mapper.insertSelective(schemeInfo);
        return schemeInfo.getId();
    }

    public boolean update(SchemeInfo schemeInfo){
        return mapper.updateByPrimaryKey(schemeInfo)==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }
}
