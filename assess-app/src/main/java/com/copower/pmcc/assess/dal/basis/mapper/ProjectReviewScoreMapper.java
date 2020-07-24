package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectReviewScoreMapper {
    long countByExample(ProjectReviewScoreExample example);

    int deleteByExample(ProjectReviewScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectReviewScore record);

    int insertSelective(ProjectReviewScore record);

    List<ProjectReviewScore> selectByExample(ProjectReviewScoreExample example);

    ProjectReviewScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectReviewScore record, @Param("example") ProjectReviewScoreExample example);

    int updateByExample(@Param("record") ProjectReviewScore record, @Param("example") ProjectReviewScoreExample example);

    int updateByPrimaryKeySelective(ProjectReviewScore record);

    int updateByPrimaryKey(ProjectReviewScore record);
}