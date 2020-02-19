package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfig;
import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetUrlConfigMapper {
    int countByExample(NetUrlConfigExample example);

    int deleteByExample(NetUrlConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetUrlConfig record);

    int insertSelective(NetUrlConfig record);

    List<NetUrlConfig> selectByExample(NetUrlConfigExample example);

    NetUrlConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetUrlConfig record, @Param("example") NetUrlConfigExample example);

    int updateByExample(@Param("record") NetUrlConfig record, @Param("example") NetUrlConfigExample example);

    int updateByPrimaryKeySelective(NetUrlConfig record);

    int updateByPrimaryKey(NetUrlConfig record);
}