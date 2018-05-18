package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliary;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliaryExample;
import com.copower.pmcc.assess.dal.mapper.SchemeAreaGroupAuxiliaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 13426 on 2018/5/18.
 */
@Repository
public class SchemeAreaGroupAuxiliaryDao {

    @Autowired
    private SchemeAreaGroupAuxiliaryMapper mapper;

    public boolean add(SchemeAreaGroupAuxiliary auxiliary){
        return mapper.insertSelective(auxiliary)==1;
    }

    public List<SchemeAreaGroupAuxiliary> list(){
        SchemeAreaGroupAuxiliaryExample example = new SchemeAreaGroupAuxiliaryExample();
        example.createCriteria().andIdIsNotNull().andGroupIdIsNotNull();
        return mapper.selectByExample(example);
    }

    public SchemeAreaGroupAuxiliary get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }


}
