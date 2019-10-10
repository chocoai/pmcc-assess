package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandle;
import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ToolMapHandleMapper {
    int countByExample(ToolMapHandleExample example);

    int deleteByExample(ToolMapHandleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToolMapHandle record);

    int insertSelective(ToolMapHandle record);

    List<ToolMapHandle> selectByExample(ToolMapHandleExample example);

    ToolMapHandle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToolMapHandle record, @Param("example") ToolMapHandleExample example);

    int updateByExample(@Param("record") ToolMapHandle record, @Param("example") ToolMapHandleExample example);

    int updateByPrimaryKeySelective(ToolMapHandle record);

    int updateByPrimaryKey(ToolMapHandle record);
}