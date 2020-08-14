package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildingConstructionPermitMapper {
    long countByExample(DeclareBuildingConstructionPermitExample example);

    int deleteByExample(DeclareBuildingConstructionPermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildingConstructionPermit record);

    int insertSelective(@Param("record") DeclareBuildingConstructionPermit record, @Param("selective") DeclareBuildingConstructionPermit.Column ... selective);

    List<DeclareBuildingConstructionPermit> selectByExample(DeclareBuildingConstructionPermitExample example);

    DeclareBuildingConstructionPermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildingConstructionPermit record, @Param("example") DeclareBuildingConstructionPermitExample example, @Param("selective") DeclareBuildingConstructionPermit.Column ... selective);

    int updateByExample(@Param("record") DeclareBuildingConstructionPermit record, @Param("example") DeclareBuildingConstructionPermitExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareBuildingConstructionPermit record, @Param("selective") DeclareBuildingConstructionPermit.Column ... selective);

    int updateByPrimaryKey(DeclareBuildingConstructionPermit record);

    int batchInsert(@Param("list") List<DeclareBuildingConstructionPermit> list);

    int batchInsertSelective(@Param("list") List<DeclareBuildingConstructionPermit> list, @Param("selective") DeclareBuildingConstructionPermit.Column ... selective);
}