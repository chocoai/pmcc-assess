package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectFileComplete;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFileCompleteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFileCompleteMapper {
    long countByExample(ProjectFileCompleteExample example);

    int deleteByExample(ProjectFileCompleteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFileComplete record);

    int insertSelective(@Param("record") ProjectFileComplete record, @Param("selective") ProjectFileComplete.Column ... selective);

    List<ProjectFileComplete> selectByExample(ProjectFileCompleteExample example);

    ProjectFileComplete selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFileComplete record, @Param("example") ProjectFileCompleteExample example, @Param("selective") ProjectFileComplete.Column ... selective);

    int updateByExample(@Param("record") ProjectFileComplete record, @Param("example") ProjectFileCompleteExample example);

    int updateByPrimaryKeySelective(@Param("record") ProjectFileComplete record, @Param("selective") ProjectFileComplete.Column ... selective);

    int updateByPrimaryKey(ProjectFileComplete record);

    int batchInsert(@Param("list") List<ProjectFileComplete> list);

    int batchInsertSelective(@Param("list") List<ProjectFileComplete> list, @Param("selective") ProjectFileComplete.Column ... selective);
}