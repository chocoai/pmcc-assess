package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateBookmark;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateBookmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentTemplateBookmarkMapper {
    int countByExample(DocumentTemplateBookmarkExample example);

    int deleteByExample(DocumentTemplateBookmarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentTemplateBookmark record);

    int insertSelective(DocumentTemplateBookmark record);

    List<DocumentTemplateBookmark> selectByExample(DocumentTemplateBookmarkExample example);

    DocumentTemplateBookmark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentTemplateBookmark record, @Param("example") DocumentTemplateBookmarkExample example);

    int updateByExample(@Param("record") DocumentTemplateBookmark record, @Param("example") DocumentTemplateBookmarkExample example);

    int updateByPrimaryKeySelective(DocumentTemplateBookmark record);

    int updateByPrimaryKey(DocumentTemplateBookmark record);
}