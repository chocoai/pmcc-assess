package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyLandCertMapper {
    int countByExample(DeclareRealtyLandCertExample example);

    int deleteByExample(DeclareRealtyLandCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyLandCert record);

    int insertSelective(DeclareRealtyLandCert record);

    List<DeclareRealtyLandCert> selectByExample(DeclareRealtyLandCertExample example);

    DeclareRealtyLandCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyLandCert record, @Param("example") DeclareRealtyLandCertExample example);

    int updateByExample(@Param("record") DeclareRealtyLandCert record, @Param("example") DeclareRealtyLandCertExample example);

    int updateByPrimaryKeySelective(DeclareRealtyLandCert record);

    int updateByPrimaryKey(DeclareRealtyLandCert record);
}