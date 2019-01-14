package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseReportFieldMapper {
    int countByExample(BaseReportFieldExample example);

    int deleteByExample(BaseReportFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportField record);

    int insertSelective(BaseReportField record);

    List<BaseReportField> selectByExample(BaseReportFieldExample example);

    BaseReportField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportField record, @Param("example") BaseReportFieldExample example);

    int updateByExample(@Param("record") BaseReportField record, @Param("example") BaseReportFieldExample example);

    int updateByPrimaryKeySelective(BaseReportField record);

    int updateByPrimaryKey(BaseReportField record);
}