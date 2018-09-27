package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclarePreSalePermitMapper {
    int countByExample(DeclarePreSalePermitExample example);

    int deleteByExample(DeclarePreSalePermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclarePreSalePermit record);

    int insertSelective(DeclarePreSalePermit record);

    List<DeclarePreSalePermit> selectByExample(DeclarePreSalePermitExample example);

    DeclarePreSalePermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclarePreSalePermit record, @Param("example") DeclarePreSalePermitExample example);

    int updateByExample(@Param("record") DeclarePreSalePermit record, @Param("example") DeclarePreSalePermitExample example);

    int updateByPrimaryKeySelective(DeclarePreSalePermit record);

    int updateByPrimaryKey(DeclarePreSalePermit record);
}