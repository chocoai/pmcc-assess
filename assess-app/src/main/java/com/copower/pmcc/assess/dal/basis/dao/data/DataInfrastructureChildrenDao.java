package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildrenExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureChildrenMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zch on 2019/6/28.
 */
@org.springframework.stereotype.Repository
public class DataInfrastructureChildrenDao {

    @Autowired
    private DataInfrastructureChildrenMapper mapper;

    public boolean updateDataInfrastructureChildren(DataInfrastructureChildren oo) {
        int i = mapper.updateByPrimaryKeySelective(oo);
        return i > 0;
    }

    public void removeDataInfrastructureChildrenIds(List<Integer> ids){
        DataInfrastructureChildrenExample example = new DataInfrastructureChildrenExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example) ;
    }


    public boolean addDataInfrastructureChildren(DataInfrastructureChildren oo) {
        int i = mapper.insertSelective(oo);
        return i > 0;
    }

    public boolean deleteDataInfrastructureChildren(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public DataInfrastructureChildren getByDataInfrastructureChildrenId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<DataInfrastructureChildren> getDataInfrastructureChildrenList(DataInfrastructureChildren oo) {
        DataInfrastructureChildrenExample example = new DataInfrastructureChildrenExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id desc");
        List<DataInfrastructureChildren> childrenList = mapper.selectByExample(example);
        return childrenList;
    }
    
}
