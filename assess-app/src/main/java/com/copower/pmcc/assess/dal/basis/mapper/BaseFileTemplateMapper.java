package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplate;
import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseFileTemplateMapper {
    int countByExample(BaseFileTemplateExample example);

    int deleteByExample(BaseFileTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseFileTemplate record);

    int insertSelective(BaseFileTemplate record);

    List<BaseFileTemplate> selectByExample(BaseFileTemplateExample example);

    BaseFileTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseFileTemplate record, @Param("example") BaseFileTemplateExample example);

    int updateByExample(@Param("record") BaseFileTemplate record, @Param("example") BaseFileTemplateExample example);

    int updateByPrimaryKeySelective(BaseFileTemplate record);

    int updateByPrimaryKey(BaseFileTemplate record);
}