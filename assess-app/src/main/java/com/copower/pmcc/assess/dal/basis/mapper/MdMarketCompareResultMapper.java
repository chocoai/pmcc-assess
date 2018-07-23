package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareResult;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMarketCompareResultMapper {
    int countByExample(MdMarketCompareResultExample example);

    int deleteByExample(MdMarketCompareResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdMarketCompareResult record);

    int insertSelective(MdMarketCompareResult record);

    List<MdMarketCompareResult> selectByExample(MdMarketCompareResultExample example);

    MdMarketCompareResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdMarketCompareResult record, @Param("example") MdMarketCompareResultExample example);

    int updateByExample(@Param("record") MdMarketCompareResult record, @Param("example") MdMarketCompareResultExample example);

    int updateByPrimaryKeySelective(MdMarketCompareResult record);

    int updateByPrimaryKey(MdMarketCompareResult record);
}