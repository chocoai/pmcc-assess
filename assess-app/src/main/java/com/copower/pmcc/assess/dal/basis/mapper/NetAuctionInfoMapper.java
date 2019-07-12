package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfo;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetAuctionInfoMapper {
    int countByExample(NetAuctionInfoExample example);

    int deleteByExample(NetAuctionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetAuctionInfo record);

    int insertSelective(NetAuctionInfo record);

    List<NetAuctionInfo> selectByExample(NetAuctionInfoExample example);

    NetAuctionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetAuctionInfo record, @Param("example") NetAuctionInfoExample example);

    int updateByExample(@Param("record") NetAuctionInfo record, @Param("example") NetAuctionInfoExample example);

    int updateByPrimaryKeySelective(NetAuctionInfo record);

    int updateByPrimaryKey(NetAuctionInfo record);
}