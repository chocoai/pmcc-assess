package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclarePreSalePermitMapper {
    long countByExample(DeclarePreSalePermitExample example);

    int deleteByExample(DeclarePreSalePermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclarePreSalePermit record);

    int insertSelective(@Param("record") DeclarePreSalePermit record, @Param("selective") DeclarePreSalePermit.Column ... selective);

    List<DeclarePreSalePermit> selectByExample(DeclarePreSalePermitExample example);

    DeclarePreSalePermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclarePreSalePermit record, @Param("example") DeclarePreSalePermitExample example, @Param("selective") DeclarePreSalePermit.Column ... selective);

    int updateByExample(@Param("record") DeclarePreSalePermit record, @Param("example") DeclarePreSalePermitExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclarePreSalePermit record, @Param("selective") DeclarePreSalePermit.Column ... selective);

    int updateByPrimaryKey(DeclarePreSalePermit record);

    int batchInsert(@Param("list") List<DeclarePreSalePermit> list);

    int batchInsertSelective(@Param("list") List<DeclarePreSalePermit> list, @Param("selective") DeclarePreSalePermit.Column ... selective);
}