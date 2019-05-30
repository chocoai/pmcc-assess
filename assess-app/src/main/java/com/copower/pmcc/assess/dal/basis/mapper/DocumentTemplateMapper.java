package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentTemplateMapper {
    int countByExample(DocumentTemplateExample example);

    int deleteByExample(DocumentTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentTemplate record);

    int insertSelective(DocumentTemplate record);

    List<DocumentTemplate> selectByExample(DocumentTemplateExample example);

    DocumentTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentTemplate record, @Param("example") DocumentTemplateExample example);

    int updateByExample(@Param("record") DocumentTemplate record, @Param("example") DocumentTemplateExample example);

    int updateByPrimaryKeySelective(DocumentTemplate record);

    int updateByPrimaryKey(DocumentTemplate record);
}