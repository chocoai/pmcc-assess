package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyRealEstateCertMapper {
    long countByExample(DeclareRealtyRealEstateCertExample example);

    int deleteByExample(DeclareRealtyRealEstateCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyRealEstateCert record);

    int insertSelective(@Param("record") DeclareRealtyRealEstateCert record, @Param("selective") DeclareRealtyRealEstateCert.Column ... selective);

    List<DeclareRealtyRealEstateCert> selectByExample(DeclareRealtyRealEstateCertExample example);

    DeclareRealtyRealEstateCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyRealEstateCert record, @Param("example") DeclareRealtyRealEstateCertExample example, @Param("selective") DeclareRealtyRealEstateCert.Column ... selective);

    int updateByExample(@Param("record") DeclareRealtyRealEstateCert record, @Param("example") DeclareRealtyRealEstateCertExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareRealtyRealEstateCert record, @Param("selective") DeclareRealtyRealEstateCert.Column ... selective);

    int updateByPrimaryKey(DeclareRealtyRealEstateCert record);

    int batchInsert(@Param("list") List<DeclareRealtyRealEstateCert> list);

    int batchInsertSelective(@Param("list") List<DeclareRealtyRealEstateCert> list, @Param("selective") DeclareRealtyRealEstateCert.Column ... selective);
}