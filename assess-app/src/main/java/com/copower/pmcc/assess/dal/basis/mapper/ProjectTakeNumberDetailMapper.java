package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetail;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectTakeNumberDetailMapper {
    int countByExample(ProjectTakeNumberDetailExample example);

    int deleteByExample(ProjectTakeNumberDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTakeNumberDetail record);

    int insertSelective(ProjectTakeNumberDetail record);

    List<ProjectTakeNumberDetail> selectByExample(ProjectTakeNumberDetailExample example);

    ProjectTakeNumberDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTakeNumberDetail record, @Param("example") ProjectTakeNumberDetailExample example);

    int updateByExample(@Param("record") ProjectTakeNumberDetail record, @Param("example") ProjectTakeNumberDetailExample example);

    int updateByPrimaryKeySelective(ProjectTakeNumberDetail record);

    int updateByPrimaryKey(ProjectTakeNumberDetail record);
}