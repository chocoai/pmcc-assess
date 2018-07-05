package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrGuarantor;
import com.copower.pmcc.assess.dal.basis.entity.CsrGuarantorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrGuarantorMapper {
    int countByExample(CsrGuarantorExample example);

    int deleteByExample(CsrGuarantorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrGuarantor record);

    int insertSelective(CsrGuarantor record);

    List<CsrGuarantor> selectByExample(CsrGuarantorExample example);

    CsrGuarantor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrGuarantor record, @Param("example") CsrGuarantorExample example);

    int updateByExample(@Param("record") CsrGuarantor record, @Param("example") CsrGuarantorExample example);

    int updateByPrimaryKeySelective(CsrGuarantor record);

    int updateByPrimaryKey(CsrGuarantor record);
}