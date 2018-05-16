package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportTemplateBookmarkMapper {
    int countByExample(ReportTemplateBookmarkExample example);

    int deleteByExample(ReportTemplateBookmarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportTemplateBookmark record);

    int insertSelective(ReportTemplateBookmark record);

    List<ReportTemplateBookmark> selectByExample(ReportTemplateBookmarkExample example);

    ReportTemplateBookmark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportTemplateBookmark record, @Param("example") ReportTemplateBookmarkExample example);

    int updateByExample(@Param("record") ReportTemplateBookmark record, @Param("example") ReportTemplateBookmarkExample example);

    int updateByPrimaryKeySelective(ReportTemplateBookmark record);

    int updateByPrimaryKey(ReportTemplateBookmark record);
}