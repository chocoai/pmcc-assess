package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuild;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniHousesBuildMapper {
    int countByExample(FuniHousesBuildExample example);

    int deleteByExample(FuniHousesBuildExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniHousesBuild record);

    int insertSelective(FuniHousesBuild record);

    List<FuniHousesBuild> selectByExample(FuniHousesBuildExample example);

    FuniHousesBuild selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniHousesBuild record, @Param("example") FuniHousesBuildExample example);

    int updateByExample(@Param("record") FuniHousesBuild record, @Param("example") FuniHousesBuildExample example);

    int updateByPrimaryKeySelective(FuniHousesBuild record);

    int updateByPrimaryKey(FuniHousesBuild record);
}