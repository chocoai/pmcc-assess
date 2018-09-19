package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyHouseCertMapper {
    int countByExample(DeclareRealtyHouseCertExample example);

    int deleteByExample(DeclareRealtyHouseCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyHouseCert record);

    int insertSelective(DeclareRealtyHouseCert record);

    List<DeclareRealtyHouseCert> selectByExample(DeclareRealtyHouseCertExample example);

    DeclareRealtyHouseCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyHouseCert record, @Param("example") DeclareRealtyHouseCertExample example);

    int updateByExample(@Param("record") DeclareRealtyHouseCert record, @Param("example") DeclareRealtyHouseCertExample example);

    int updateByPrimaryKeySelective(DeclareRealtyHouseCert record);

    int updateByPrimaryKey(DeclareRealtyHouseCert record);
}