package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMarketCompareFieldMapper {
    int countByExample(MdMarketCompareFieldExample example);

    int deleteByExample(MdMarketCompareFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdMarketCompareField record);

    int insertSelective(MdMarketCompareField record);

    List<MdMarketCompareField> selectByExample(MdMarketCompareFieldExample example);

    MdMarketCompareField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdMarketCompareField record, @Param("example") MdMarketCompareFieldExample example);

    int updateByExample(@Param("record") MdMarketCompareField record, @Param("example") MdMarketCompareFieldExample example);

    int updateByPrimaryKeySelective(MdMarketCompareField record);

    int updateByPrimaryKey(MdMarketCompareField record);
}