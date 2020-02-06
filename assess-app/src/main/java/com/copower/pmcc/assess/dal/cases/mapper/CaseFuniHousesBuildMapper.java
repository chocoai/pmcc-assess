package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuild;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniHousesBuildMapper {
    int countByExample(CaseFuniHousesBuildExample example);

    int deleteByExample(CaseFuniHousesBuildExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniHousesBuild record);

    int insertSelective(CaseFuniHousesBuild record);

    List<CaseFuniHousesBuild> selectByExample(CaseFuniHousesBuildExample example);

    CaseFuniHousesBuild selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniHousesBuild record, @Param("example") CaseFuniHousesBuildExample example);

    int updateByExample(@Param("record") CaseFuniHousesBuild record, @Param("example") CaseFuniHousesBuildExample example);

    int updateByPrimaryKeySelective(CaseFuniHousesBuild record);

    int updateByPrimaryKey(CaseFuniHousesBuild record);
}