package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMarketCompareMapper {
    long countByExample(MdMarketCompareExample example);

    int deleteByExample(MdMarketCompareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdMarketCompare record);

    int insertSelective(MdMarketCompare record);

    List<MdMarketCompare> selectByExample(MdMarketCompareExample example);

    MdMarketCompare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdMarketCompare record, @Param("example") MdMarketCompareExample example);

    int updateByExample(@Param("record") MdMarketCompare record, @Param("example") MdMarketCompareExample example);

    int updateByPrimaryKeySelective(MdMarketCompare record);

    int updateByPrimaryKey(MdMarketCompare record);
}