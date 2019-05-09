package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPrice;
import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdBaseLandPriceMapper {
    int countByExample(MdBaseLandPriceExample example);

    int deleteByExample(MdBaseLandPriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdBaseLandPrice record);

    int insertSelective(MdBaseLandPrice record);

    List<MdBaseLandPrice> selectByExample(MdBaseLandPriceExample example);

    MdBaseLandPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdBaseLandPrice record, @Param("example") MdBaseLandPriceExample example);

    int updateByExample(@Param("record") MdBaseLandPrice record, @Param("example") MdBaseLandPriceExample example);

    int updateByPrimaryKeySelective(MdBaseLandPrice record);

    int updateByPrimaryKey(MdBaseLandPrice record);
}