package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareLandUsePermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:01
 * @Description:
 */
@Repository
public class DeclareLandUsePermitDao {
    @Autowired
    private DeclareLandUsePermitMapper declareLandUsePermitMapper;

    public Integer addDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit){
        declareLandUsePermitMapper.insertSelective(declareLandUsePermit);
        return declareLandUsePermit.getId();
    }

    public DeclareLandUsePermit getDeclareLandUsePermitById(Integer id){
        return declareLandUsePermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit){
        return declareLandUsePermitMapper.updateByPrimaryKeySelective(declareLandUsePermit)==1;
    }

    public void removeDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit){
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        MybatisUtils.convertObj2Example(declareLandUsePermit, example);
        declareLandUsePermitMapper.deleteByExample(example);
    }

    public List<DeclareLandUsePermit> getDeclareLandUsePermitList(DeclareLandUsePermit declareLandUsePermit){
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        MybatisUtils.convertObj2Example(declareLandUsePermit, example);
        return declareLandUsePermitMapper.selectByExample(example);
    }
}
