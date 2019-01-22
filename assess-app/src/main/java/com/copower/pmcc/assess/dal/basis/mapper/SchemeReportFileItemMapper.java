package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReportFileItemMapper {
    int countByExample(SchemeReportFileItemExample example);

    int deleteByExample(SchemeReportFileItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReportFileItem record);

    int insertSelective(SchemeReportFileItem record);

    List<SchemeReportFileItem> selectByExample(SchemeReportFileItemExample example);

    SchemeReportFileItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReportFileItem record, @Param("example") SchemeReportFileItemExample example);

    int updateByExample(@Param("record") SchemeReportFileItem record, @Param("example") SchemeReportFileItemExample example);

    int updateByPrimaryKeySelective(SchemeReportFileItem record);

    int updateByPrimaryKey(SchemeReportFileItem record);
}