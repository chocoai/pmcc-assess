package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitMapper {
    long countByExample(BasicUnitExample example);

    int deleteByExample(BasicUnitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnit record);

    int insertSelective(BasicUnit record);

    List<BasicUnit> selectByExample(BasicUnitExample example);

    BasicUnit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnit record, @Param("example") BasicUnitExample example);

    int updateByExample(@Param("record") BasicUnit record, @Param("example") BasicUnitExample example);

    int updateByPrimaryKeySelective(BasicUnit record);

    int updateByPrimaryKey(BasicUnit record);
}