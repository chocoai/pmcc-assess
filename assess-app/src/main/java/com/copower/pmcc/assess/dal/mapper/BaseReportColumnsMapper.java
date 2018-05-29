package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseReportColumns;
import com.copower.pmcc.assess.dal.entity.BaseReportColumnsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportColumnsMapper {
    int countByExample(BaseReportColumnsExample example);

    int deleteByExample(BaseReportColumnsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportColumns record);

    int insertSelective(BaseReportColumns record);

    List<BaseReportColumns> selectByExample(BaseReportColumnsExample example);

    BaseReportColumns selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportColumns record, @Param("example") BaseReportColumnsExample example);

    int updateByExample(@Param("record") BaseReportColumns record, @Param("example") BaseReportColumnsExample example);

    int updateByPrimaryKeySelective(BaseReportColumns record);

    int updateByPrimaryKey(BaseReportColumns record);
}