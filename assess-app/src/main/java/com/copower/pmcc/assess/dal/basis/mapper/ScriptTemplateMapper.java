package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateExample;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptTemplateMapper {
    long countByExample(ScriptTemplateExample example);

    int deleteByExample(ScriptTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScriptTemplateWithBLOBs record);

    int insertSelective(@Param("record") ScriptTemplateWithBLOBs record, @Param("selective") ScriptTemplateWithBLOBs.Column ... selective);

    List<ScriptTemplateWithBLOBs> selectByExampleWithBLOBs(ScriptTemplateExample example);

    List<ScriptTemplate> selectByExample(ScriptTemplateExample example);

    ScriptTemplateWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScriptTemplateWithBLOBs record, @Param("example") ScriptTemplateExample example, @Param("selective") ScriptTemplateWithBLOBs.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") ScriptTemplateWithBLOBs record, @Param("example") ScriptTemplateExample example);

    int updateByExample(@Param("record") ScriptTemplate record, @Param("example") ScriptTemplateExample example);

    int updateByPrimaryKeySelective(@Param("record") ScriptTemplateWithBLOBs record, @Param("selective") ScriptTemplateWithBLOBs.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(ScriptTemplateWithBLOBs record);

    int updateByPrimaryKey(ScriptTemplate record);

    int batchInsert(@Param("list") List<ScriptTemplateWithBLOBs> list);

    int batchInsertSelective(@Param("list") List<ScriptTemplateWithBLOBs> list, @Param("selective") ScriptTemplateWithBLOBs.Column ... selective);
}