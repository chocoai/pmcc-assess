package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ReportTable;
import com.copower.pmcc.assess.dal.entity.ReportTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportTableMapper {
    int countByExample(ReportTableExample example);

    int deleteByExample(ReportTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportTable record);

    int insertSelective(ReportTable record);

    List<ReportTable> selectByExample(ReportTableExample example);

    ReportTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportTable record, @Param("example") ReportTableExample example);

    int updateByExample(@Param("record") ReportTable record, @Param("example") ReportTableExample example);

    int updateByPrimaryKeySelective(ReportTable record);

    int updateByPrimaryKey(ReportTable record);
}