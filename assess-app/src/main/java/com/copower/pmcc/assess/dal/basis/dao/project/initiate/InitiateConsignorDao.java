package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignorExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiateConsignorMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateConsignorDao {

    @Autowired
    private InitiateConsignorMapper mapper;

    public int add(InitiateConsignor initiateConsignor) {
        mapper.insertSelective(initiateConsignor);
        return initiateConsignor.getId();
    }


    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(InitiateConsignor consignor) {
        return mapper.updateByPrimaryKeySelective(consignor) == 1;
    }


    public InitiateConsignor get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<InitiateConsignor> initiateConsignorList(InitiateConsignor query){
        InitiateConsignorExample example = new InitiateConsignorExample();
        MybatisUtils.convertObj2Example(example, example);
        return mapper.selectByExample(example);
    }


}
