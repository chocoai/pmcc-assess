package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateField;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentTemplateFieldMapper {
    int countByExample(DocumentTemplateFieldExample example);

    int deleteByExample(DocumentTemplateFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentTemplateField record);

    int insertSelective(DocumentTemplateField record);

    List<DocumentTemplateField> selectByExample(DocumentTemplateFieldExample example);

    DocumentTemplateField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentTemplateField record, @Param("example") DocumentTemplateFieldExample example);

    int updateByExample(@Param("record") DocumentTemplateField record, @Param("example") DocumentTemplateFieldExample example);

    int updateByPrimaryKeySelective(DocumentTemplateField record);

    int updateByPrimaryKey(DocumentTemplateField record);
}