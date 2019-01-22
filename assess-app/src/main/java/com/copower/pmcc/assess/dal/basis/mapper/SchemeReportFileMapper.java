package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFile;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReportFileMapper {
    int countByExample(SchemeReportFileExample example);

    int deleteByExample(SchemeReportFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReportFile record);

    int insertSelective(SchemeReportFile record);

    List<SchemeReportFile> selectByExample(SchemeReportFileExample example);

    SchemeReportFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReportFile record, @Param("example") SchemeReportFileExample example);

    int updateByExample(@Param("record") SchemeReportFile record, @Param("example") SchemeReportFileExample example);

    int updateByPrimaryKeySelective(SchemeReportFile record);

    int updateByPrimaryKey(SchemeReportFile record);
}