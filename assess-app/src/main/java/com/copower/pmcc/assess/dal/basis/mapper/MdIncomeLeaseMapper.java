package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeLeaseMapper {
    int countByExample(MdIncomeLeaseExample example);

    int deleteByExample(MdIncomeLeaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeLease record);

    int insertSelective(MdIncomeLease record);

    List<MdIncomeLease> selectByExample(MdIncomeLeaseExample example);

    MdIncomeLease selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeLease record, @Param("example") MdIncomeLeaseExample example);

    int updateByExample(@Param("record") MdIncomeLease record, @Param("example") MdIncomeLeaseExample example);

    int updateByPrimaryKeySelective(MdIncomeLease record);

    int updateByPrimaryKey(MdIncomeLease record);
}