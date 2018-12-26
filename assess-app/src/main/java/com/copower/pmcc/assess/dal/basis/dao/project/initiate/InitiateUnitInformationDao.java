package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformationExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiateUnitInformationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报告使用单位信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateUnitInformationDao {
    @Autowired
    private InitiateUnitInformationMapper mapper;

    public int add(InitiateUnitInformation initiateUnitInformation){
        mapper.insertSelective(initiateUnitInformation);
        return initiateUnitInformation.getId();
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateUnitInformation initiateUnitInformation){
        return mapper.updateByPrimaryKeySelective(initiateUnitInformation)==1;
    }

    public InitiateUnitInformation get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }



    public List<InitiateUnitInformation> initiateUnitInformationList(InitiateUnitInformation query){
        InitiateUnitInformationExample example = new InitiateUnitInformationExample();
        MybatisUtils.convertObj2Example(query, example);
        return mapper.selectByExample(example);
    }
}
