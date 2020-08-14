package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyHouseCertMapper {
    long countByExample(DeclareRealtyHouseCertExample example);

    int deleteByExample(DeclareRealtyHouseCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyHouseCert record);

    int insertSelective(@Param("record") DeclareRealtyHouseCert record, @Param("selective") DeclareRealtyHouseCert.Column ... selective);

    List<DeclareRealtyHouseCert> selectByExample(DeclareRealtyHouseCertExample example);

    DeclareRealtyHouseCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyHouseCert record, @Param("example") DeclareRealtyHouseCertExample example, @Param("selective") DeclareRealtyHouseCert.Column ... selective);

    int updateByExample(@Param("record") DeclareRealtyHouseCert record, @Param("example") DeclareRealtyHouseCertExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareRealtyHouseCert record, @Param("selective") DeclareRealtyHouseCert.Column ... selective);

    int updateByPrimaryKey(DeclareRealtyHouseCert record);

    int batchInsert(@Param("list") List<DeclareRealtyHouseCert> list);

    int batchInsertSelective(@Param("list") List<DeclareRealtyHouseCert> list, @Param("selective") DeclareRealtyHouseCert.Column ... selective);
}