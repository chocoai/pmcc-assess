package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMarketCompareItemMapper {
    int countByExample(MdMarketCompareItemExample example);

    int deleteByExample(MdMarketCompareItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdMarketCompareItem record);

    int insertSelective(MdMarketCompareItem record);

    List<MdMarketCompareItem> selectByExample(MdMarketCompareItemExample example);

    MdMarketCompareItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdMarketCompareItem record, @Param("example") MdMarketCompareItemExample example);

    int updateByExample(@Param("record") MdMarketCompareItem record, @Param("example") MdMarketCompareItemExample example);

    int updateByPrimaryKeySelective(MdMarketCompareItem record);

    int updateByPrimaryKey(MdMarketCompareItem record);
}