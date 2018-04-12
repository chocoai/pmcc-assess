package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseProcessForm;
import com.copower.pmcc.assess.dal.entity.BaseProcessFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseProcessFormMapper {
    int countByExample(BaseProcessFormExample example);

    int deleteByExample(BaseProcessFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseProcessForm record);

    int insertSelective(BaseProcessForm record);

    List<BaseProcessForm> selectByExample(BaseProcessFormExample example);

    BaseProcessForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseProcessForm record, @Param("example") BaseProcessFormExample example);

    int updateByExample(@Param("record") BaseProcessForm record, @Param("example") BaseProcessFormExample example);

    int updateByPrimaryKeySelective(BaseProcessForm record);

    int updateByPrimaryKey(BaseProcessForm record);
}