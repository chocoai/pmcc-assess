package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.Infrastructure;
import com.copower.pmcc.assess.dal.entity.InfrastructureExample;
import com.copower.pmcc.assess.dal.mapper.InfrastructureMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuwei
 */
@Repository
public class DataInfrastructureDao {
    @Autowired
    private InfrastructureMapper infrastructureMapper;

    /**查询发文单位*/
    public List<Infrastructure> getInfrastructureList(String name){
        InfrastructureExample example = new InfrastructureExample();
        InfrastructureExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
           criteria.andFileNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        return infrastructureMapper.selectByExample(example) ;
    }

    /**添加发文单位*/
    public Boolean addInfrastructure(Infrastructure infrastructure){
        int result = infrastructureMapper.insert(infrastructure);
        return result > 0;
    }

    /**修改发文单位*/
    public Boolean editInfrastructure(Infrastructure infrastructure){
        int result = infrastructureMapper.updateByPrimaryKeySelective(infrastructure);
        return result > 0;
    }

    /**删除发文单位*/
    public Boolean deleteInfrastructure(Integer id){
        int result = infrastructureMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
}
