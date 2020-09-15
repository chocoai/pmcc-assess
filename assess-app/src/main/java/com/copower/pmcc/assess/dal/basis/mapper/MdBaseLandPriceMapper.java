package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPrice;
import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdBaseLandPriceMapper {
    long countByExample(MdBaseLandPriceExample example);

    int deleteByExample(MdBaseLandPriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdBaseLandPrice record);

    int insertSelective(@Param("record") MdBaseLandPrice record, @Param("selective") MdBaseLandPrice.Column ... selective);

    List<MdBaseLandPrice> selectByExampleWithBLOBs(MdBaseLandPriceExample example);

    List<MdBaseLandPrice> selectByExample(MdBaseLandPriceExample example);

    MdBaseLandPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdBaseLandPrice record, @Param("example") MdBaseLandPriceExample example, @Param("selective") MdBaseLandPrice.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") MdBaseLandPrice record, @Param("example") MdBaseLandPriceExample example);

    int updateByExample(@Param("record") MdBaseLandPrice record, @Param("example") MdBaseLandPriceExample example);

    int updateByPrimaryKeySelective(@Param("record") MdBaseLandPrice record, @Param("selective") MdBaseLandPrice.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(MdBaseLandPrice record);

    int updateByPrimaryKey(MdBaseLandPrice record);

    int batchInsert(@Param("list") List<MdBaseLandPrice> list);

    int batchInsertSelective(@Param("list") List<MdBaseLandPrice> list, @Param("selective") MdBaseLandPrice.Column ... selective);
}