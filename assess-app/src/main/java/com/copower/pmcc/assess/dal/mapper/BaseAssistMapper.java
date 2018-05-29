package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseAssist;
import com.copower.pmcc.assess.dal.entity.BaseAssistExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAssistMapper {
    int countByExample(BaseAssistExample example);

    int deleteByExample(BaseAssistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseAssist record);

    int insertSelective(BaseAssist record);

    List<BaseAssist> selectByExample(BaseAssistExample example);

    BaseAssist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseAssist record, @Param("example") BaseAssistExample example);

    int updateByExample(@Param("record") BaseAssist record, @Param("example") BaseAssistExample example);

    int updateByPrimaryKeySelective(BaseAssist record);

    int updateByPrimaryKey(BaseAssist record);
}