package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeHistory;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeHistoryMapper {
    int countByExample(MdIncomeHistoryExample example);

    int deleteByExample(MdIncomeHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeHistory record);

    int insertSelective(MdIncomeHistory record);

    List<MdIncomeHistory> selectByExample(MdIncomeHistoryExample example);

    MdIncomeHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeHistory record, @Param("example") MdIncomeHistoryExample example);

    int updateByExample(@Param("record") MdIncomeHistory record, @Param("example") MdIncomeHistoryExample example);

    int updateByPrimaryKeySelective(MdIncomeHistory record);

    int updateByPrimaryKey(MdIncomeHistory record);
}