package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectReviewScoreItemMapper {
    long countByExample(ProjectReviewScoreItemExample example);

    int deleteByExample(ProjectReviewScoreItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectReviewScoreItem record);

    int insertSelective(ProjectReviewScoreItem record);

    List<ProjectReviewScoreItem> selectByExample(ProjectReviewScoreItemExample example);

    ProjectReviewScoreItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectReviewScoreItem record, @Param("example") ProjectReviewScoreItemExample example);

    int updateByExample(@Param("record") ProjectReviewScoreItem record, @Param("example") ProjectReviewScoreItemExample example);

    int updateByPrimaryKeySelective(ProjectReviewScoreItem record);

    int updateByPrimaryKey(ProjectReviewScoreItem record);
}