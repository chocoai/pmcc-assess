package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseReportTable;
import com.copower.pmcc.assess.dal.entity.BaseReportTableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportTableMapper {
    int countByExample(BaseReportTableExample example);

    int deleteByExample(BaseReportTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportTable record);

    int insertSelective(BaseReportTable record);

    List<BaseReportTable> selectByExample(BaseReportTableExample example);

    BaseReportTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportTable record, @Param("example") BaseReportTableExample example);

    int updateByExample(@Param("record") BaseReportTable record, @Param("example") BaseReportTableExample example);

    int updateByPrimaryKeySelective(BaseReportTable record);

    int updateByPrimaryKey(BaseReportTable record);
}