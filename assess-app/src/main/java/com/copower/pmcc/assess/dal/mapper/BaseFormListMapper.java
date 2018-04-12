package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseFormList;
import com.copower.pmcc.assess.dal.entity.BaseFormListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseFormListMapper {
    int countByExample(BaseFormListExample example);

    int deleteByExample(BaseFormListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseFormList record);

    int insertSelective(BaseFormList record);

    List<BaseFormList> selectByExample(BaseFormListExample example);

    BaseFormList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseFormList record, @Param("example") BaseFormListExample example);

    int updateByExample(@Param("record") BaseFormList record, @Param("example") BaseFormListExample example);

    int updateByPrimaryKeySelective(BaseFormList record);

    int updateByPrimaryKey(BaseFormList record);
}