package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetLandTransaction;
import com.copower.pmcc.assess.dal.basis.entity.NetLandTransactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetLandTransactionMapper {
    int countByExample(NetLandTransactionExample example);

    int deleteByExample(NetLandTransactionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetLandTransaction record);

    int insertSelective(NetLandTransaction record);

    List<NetLandTransaction> selectByExample(NetLandTransactionExample example);

    NetLandTransaction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetLandTransaction record, @Param("example") NetLandTransactionExample example);

    int updateByExample(@Param("record") NetLandTransaction record, @Param("example") NetLandTransactionExample example);

    int updateByPrimaryKeySelective(NetLandTransaction record);

    int updateByPrimaryKey(NetLandTransaction record);
}