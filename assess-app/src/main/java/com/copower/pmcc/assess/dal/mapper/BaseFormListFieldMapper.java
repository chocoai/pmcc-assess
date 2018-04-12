package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseFormListField;
import com.copower.pmcc.assess.dal.entity.BaseFormListFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseFormListFieldMapper {
    int countByExample(BaseFormListFieldExample example);

    int deleteByExample(BaseFormListFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseFormListField record);

    int insertSelective(BaseFormListField record);

    List<BaseFormListField> selectByExample(BaseFormListFieldExample example);

    BaseFormListField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseFormListField record, @Param("example") BaseFormListFieldExample example);

    int updateByExample(@Param("record") BaseFormListField record, @Param("example") BaseFormListFieldExample example);

    int updateByPrimaryKeySelective(BaseFormListField record);

    int updateByPrimaryKey(BaseFormListField record);
}