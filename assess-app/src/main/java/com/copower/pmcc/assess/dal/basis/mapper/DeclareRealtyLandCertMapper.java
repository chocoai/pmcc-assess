package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyLandCertMapper {
    long countByExample(DeclareRealtyLandCertExample example);

    int deleteByExample(DeclareRealtyLandCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyLandCert record);

    int insertSelective(@Param("record") DeclareRealtyLandCert record, @Param("selective") DeclareRealtyLandCert.Column ... selective);

    List<DeclareRealtyLandCert> selectByExample(DeclareRealtyLandCertExample example);

    DeclareRealtyLandCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyLandCert record, @Param("example") DeclareRealtyLandCertExample example, @Param("selective") DeclareRealtyLandCert.Column ... selective);

    int updateByExample(@Param("record") DeclareRealtyLandCert record, @Param("example") DeclareRealtyLandCertExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareRealtyLandCert record, @Param("selective") DeclareRealtyLandCert.Column ... selective);

    int updateByPrimaryKey(DeclareRealtyLandCert record);

    int batchInsert(@Param("list") List<DeclareRealtyLandCert> list);

    int batchInsertSelective(@Param("list") List<DeclareRealtyLandCert> list, @Param("selective") DeclareRealtyLandCert.Column ... selective);
}