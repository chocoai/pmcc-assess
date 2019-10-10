package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandle;
import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandleExample;
import com.copower.pmcc.assess.dal.basis.mapper.ToolMapHandleMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-10-8.
 */
@Repository
public class ToolMapHandleDao {

    @Autowired
    private ToolMapHandleMapper mapper;

    public boolean addToolMapHandle(ToolMapHandle oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateToolMapHandle(ToolMapHandle oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public ToolMapHandle getToolMapHandleById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public void deleteToolMapHandle(List<Integer> ids){
        ToolMapHandleExample example = new ToolMapHandleExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example) ;
    }

    public boolean deleteToolMapHandleById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeToolMapHandle(ToolMapHandle oo){
        ToolMapHandleExample example = new ToolMapHandleExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<ToolMapHandle> getToolMapHandleListByExample(ToolMapHandle oo){
        ToolMapHandleExample example = new ToolMapHandleExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }


    public List<ToolMapHandle> getToolMapHandleListByType(String type){
        ToolMapHandleExample example = new ToolMapHandleExample();
        example.createCriteria().andTypeEqualTo(type);
        return mapper.selectByExample(example) ;
    }

}
