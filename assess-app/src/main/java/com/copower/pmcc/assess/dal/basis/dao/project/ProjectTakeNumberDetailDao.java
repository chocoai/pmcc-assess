package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetail;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectTakeNumberDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class ProjectTakeNumberDetailDao {

    @Autowired
    private ProjectTakeNumberDetailMapper mapper;

    public boolean updateProjectTakeNumberDetail(ProjectTakeNumberDetail oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteProjectTakeNumberDetailById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteProjectTakeNumberDetailByIds(List<Integer> ids){
        ProjectTakeNumberDetailExample example = new ProjectTakeNumberDetailExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public ProjectTakeNumberDetail getProjectTakeNumberDetailById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveProjectTakeNumberDetail(ProjectTakeNumberDetail oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<ProjectTakeNumberDetail> getProjectTakeNumberDetailByIds(List<Integer> ids){
        ProjectTakeNumberDetailExample example = new ProjectTakeNumberDetailExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<ProjectTakeNumberDetail> getProjectTakeNumberDetailListByExample(ProjectTakeNumberDetail oo){
        ProjectTakeNumberDetailExample example = new ProjectTakeNumberDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
}
