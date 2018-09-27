package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildingPermitMapper {
    int countByExample(DeclareBuildingPermitExample example);

    int deleteByExample(DeclareBuildingPermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildingPermit record);

    int insertSelective(DeclareBuildingPermit record);

    List<DeclareBuildingPermit> selectByExample(DeclareBuildingPermitExample example);

    DeclareBuildingPermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildingPermit record, @Param("example") DeclareBuildingPermitExample example);

    int updateByExample(@Param("record") DeclareBuildingPermit record, @Param("example") DeclareBuildingPermitExample example);

    int updateByPrimaryKeySelective(DeclareBuildingPermit record);

    int updateByPrimaryKey(DeclareBuildingPermit record);
}