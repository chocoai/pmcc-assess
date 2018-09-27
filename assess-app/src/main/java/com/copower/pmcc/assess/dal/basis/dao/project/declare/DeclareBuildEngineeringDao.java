package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineering;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEngineeringMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:28
 * @Description:
 */
@Repository
public class DeclareBuildEngineeringDao {
    @Autowired
    private DeclareBuildEngineeringMapper declareBuildEngineeringMapper;

    public Integer addDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering){
        declareBuildEngineeringMapper.insertSelective(declareBuildEngineering);
        return declareBuildEngineering.getId();
    }

    public DeclareBuildEngineering getDeclareBuildEngineeringById(Integer id){
        return declareBuildEngineeringMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering){
        return declareBuildEngineeringMapper.updateByPrimaryKeySelective(declareBuildEngineering)==1;
    }

    public void removeDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering){
        DeclareBuildEngineeringExample example = new DeclareBuildEngineeringExample();
        MybatisUtils.convertObj2Example(declareBuildEngineering, example);
        declareBuildEngineeringMapper.deleteByExample(example);
    }

    public List<DeclareBuildEngineering> getDeclareBuildEngineeringList(DeclareBuildEngineering declareBuildEngineering){
        DeclareBuildEngineeringExample example = new DeclareBuildEngineeringExample();
        MybatisUtils.convertObj2Example(declareBuildEngineering, example);
        return declareBuildEngineeringMapper.selectByExample(example);
    }
}
