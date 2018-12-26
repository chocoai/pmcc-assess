package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessorExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiatePossessorMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资产占有人信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiatePossessorDao {

    @Autowired
    private InitiatePossessorMapper mapper;

    public int add(InitiatePossessor possessor){
        mapper.insertSelective(possessor);
        return possessor.getId();
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiatePossessor initiatePossessor){
        return mapper.updateByPrimaryKeySelective(initiatePossessor)==1;
    }

    public InitiatePossessor get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<InitiatePossessor> initiatePossessorList(InitiatePossessor initiatePossessor){
        InitiatePossessorExample example = new InitiatePossessorExample();
        MybatisUtils.convertObj2Example(initiatePossessor, example);
        return mapper.selectByExample(example) ;
    }

}
