package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyRealEstateCertMapper {
    int countByExample(DeclareRealtyRealEstateCertExample example);

    int deleteByExample(DeclareRealtyRealEstateCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyRealEstateCert record);

    int insertSelective(DeclareRealtyRealEstateCert record);

    List<DeclareRealtyRealEstateCert> selectByExample(DeclareRealtyRealEstateCertExample example);

    DeclareRealtyRealEstateCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyRealEstateCert record, @Param("example") DeclareRealtyRealEstateCertExample example);

    int updateByExample(@Param("record") DeclareRealtyRealEstateCert record, @Param("example") DeclareRealtyRealEstateCertExample example);

    int updateByPrimaryKeySelective(DeclareRealtyRealEstateCert record);

    int updateByPrimaryKey(DeclareRealtyRealEstateCert record);
}