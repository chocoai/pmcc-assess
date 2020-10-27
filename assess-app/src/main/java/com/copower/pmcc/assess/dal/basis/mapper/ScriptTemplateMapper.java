package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptTemplateMapper {
    long countByExample(ScriptTemplateExample example);

    int deleteByExample(ScriptTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptTemplate record);

    int insertSelective(ScriptTemplate record);

    List<ScriptTemplate> selectByExample(ScriptTemplateExample example);

    ScriptTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScriptTemplate record, @Param("example") ScriptTemplateExample example);

    int updateByExample(@Param("record") ScriptTemplate record, @Param("example") ScriptTemplateExample example);

    int updateByPrimaryKeySelective(ScriptTemplate record);

    int updateByPrimaryKey(ScriptTemplate record);
}