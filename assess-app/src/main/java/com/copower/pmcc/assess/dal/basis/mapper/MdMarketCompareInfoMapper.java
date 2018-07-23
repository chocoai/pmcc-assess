package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareInfo;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMarketCompareInfoMapper {
    int countByExample(MdMarketCompareInfoExample example);

    int deleteByExample(MdMarketCompareInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdMarketCompareInfo record);

    int insertSelective(MdMarketCompareInfo record);

    List<MdMarketCompareInfo> selectByExample(MdMarketCompareInfoExample example);

    MdMarketCompareInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdMarketCompareInfo record, @Param("example") MdMarketCompareInfoExample example);

    int updateByExample(@Param("record") MdMarketCompareInfo record, @Param("example") MdMarketCompareInfoExample example);

    int updateByPrimaryKeySelective(MdMarketCompareInfo record);

    int updateByPrimaryKey(MdMarketCompareInfo record);
}