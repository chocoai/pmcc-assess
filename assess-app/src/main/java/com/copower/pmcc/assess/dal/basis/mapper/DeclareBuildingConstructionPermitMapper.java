package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildingConstructionPermitMapper {
    int countByExample(DeclareBuildingConstructionPermitExample example);

    int deleteByExample(DeclareBuildingConstructionPermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildingConstructionPermit record);

    int insertSelective(DeclareBuildingConstructionPermit record);

    List<DeclareBuildingConstructionPermit> selectByExample(DeclareBuildingConstructionPermitExample example);

    DeclareBuildingConstructionPermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildingConstructionPermit record, @Param("example") DeclareBuildingConstructionPermitExample example);

    int updateByExample(@Param("record") DeclareBuildingConstructionPermit record, @Param("example") DeclareBuildingConstructionPermitExample example);

    int updateByPrimaryKeySelective(DeclareBuildingConstructionPermit record);

    int updateByPrimaryKey(DeclareBuildingConstructionPermit record);
}