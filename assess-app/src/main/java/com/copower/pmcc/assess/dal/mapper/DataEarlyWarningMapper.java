package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.EarlyWarning;
import com.copower.pmcc.assess.dal.entity.EarlyWarningExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataEarlyWarningMapper {
    int countByExample(EarlyWarningExample example);

    int deleteByExample(EarlyWarningExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EarlyWarning record);

    int insertSelective(EarlyWarning record);

    List<EarlyWarning> selectByExample(EarlyWarningExample example);

    EarlyWarning selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EarlyWarning record, @Param("example") EarlyWarningExample example);

    int updateByExample(@Param("record") EarlyWarning record, @Param("example") EarlyWarningExample example);

    int updateByPrimaryKeySelective(EarlyWarning record);

    int updateByPrimaryKey(EarlyWarning record);
}