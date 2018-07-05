package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseForm;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseFormMapper {
    int countByExample(BaseFormExample example);

    int deleteByExample(BaseFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseForm record);

    int insertSelective(BaseForm record);

    List<BaseForm> selectByExample(BaseFormExample example);

    BaseForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseForm record, @Param("example") BaseFormExample example);

    int updateByExample(@Param("record") BaseForm record, @Param("example") BaseFormExample example);

    int updateByPrimaryKeySelective(BaseForm record);

    int updateByPrimaryKey(BaseForm record);
}