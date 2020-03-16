package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgrade;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoUpgradeMapper {
    int countByExample(NetInfoUpgradeExample example);

    int deleteByExample(NetInfoUpgradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoUpgrade record);

    int insertSelective(NetInfoUpgrade record);

    List<NetInfoUpgrade> selectByExample(NetInfoUpgradeExample example);

    NetInfoUpgrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoUpgrade record, @Param("example") NetInfoUpgradeExample example);

    int updateByExample(@Param("record") NetInfoUpgrade record, @Param("example") NetInfoUpgradeExample example);

    int updateByPrimaryKeySelective(NetInfoUpgrade record);

    int updateByPrimaryKey(NetInfoUpgrade record);
}