package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateNetworkMapper {
    int countByExample(CaseEstateNetworkExample example);

    int deleteByExample(CaseEstateNetworkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateNetwork record);

    int insertSelective(CaseEstateNetwork record);

    List<CaseEstateNetwork> selectByExample(CaseEstateNetworkExample example);

    CaseEstateNetwork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateNetwork record, @Param("example") CaseEstateNetworkExample example);

    int updateByExample(@Param("record") CaseEstateNetwork record, @Param("example") CaseEstateNetworkExample example);

    int updateByPrimaryKeySelective(CaseEstateNetwork record);

    int updateByPrimaryKey(CaseEstateNetwork record);
}