package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectReviewScoreGroupMapper {
    long countByExample(ProjectReviewScoreGroupExample example);

    int deleteByExample(ProjectReviewScoreGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectReviewScoreGroup record);

    int insertSelective(ProjectReviewScoreGroup record);

    List<ProjectReviewScoreGroup> selectByExample(ProjectReviewScoreGroupExample example);

    ProjectReviewScoreGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectReviewScoreGroup record, @Param("example") ProjectReviewScoreGroupExample example);

    int updateByExample(@Param("record") ProjectReviewScoreGroup record, @Param("example") ProjectReviewScoreGroupExample example);

    int updateByPrimaryKeySelective(ProjectReviewScoreGroup record);

    int updateByPrimaryKey(ProjectReviewScoreGroup record);
}