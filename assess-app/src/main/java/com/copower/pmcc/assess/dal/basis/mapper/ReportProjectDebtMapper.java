package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ReportProjectDebt;
import com.copower.pmcc.assess.dal.basis.entity.ReportProjectDebtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportProjectDebtMapper {
    int countByExample(ReportProjectDebtExample example);

    int deleteByExample(ReportProjectDebtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportProjectDebt record);

    int insertSelective(ReportProjectDebt record);

    List<ReportProjectDebt> selectByExample(ReportProjectDebtExample example);

    ReportProjectDebt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportProjectDebt record, @Param("example") ReportProjectDebtExample example);

    int updateByExample(@Param("record") ReportProjectDebt record, @Param("example") ReportProjectDebtExample example);

    int updateByPrimaryKeySelective(ReportProjectDebt record);

    int updateByPrimaryKey(ReportProjectDebt record);
}