package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustom;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReportFileCustomMapper {
    int countByExample(SchemeReportFileCustomExample example);

    int deleteByExample(SchemeReportFileCustomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReportFileCustom record);

    int insertSelective(SchemeReportFileCustom record);

    List<SchemeReportFileCustom> selectByExample(SchemeReportFileCustomExample example);

    SchemeReportFileCustom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReportFileCustom record, @Param("example") SchemeReportFileCustomExample example);

    int updateByExample(@Param("record") SchemeReportFileCustom record, @Param("example") SchemeReportFileCustomExample example);

    int updateByPrimaryKeySelective(SchemeReportFileCustom record);

    int updateByPrimaryKey(SchemeReportFileCustom record);
}