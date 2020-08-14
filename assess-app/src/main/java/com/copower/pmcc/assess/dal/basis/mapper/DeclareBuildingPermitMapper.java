package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildingPermitMapper {
    long countByExample(DeclareBuildingPermitExample example);

    int deleteByExample(DeclareBuildingPermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildingPermit record);

    int insertSelective(@Param("record") DeclareBuildingPermit record, @Param("selective") DeclareBuildingPermit.Column ... selective);

    List<DeclareBuildingPermit> selectByExample(DeclareBuildingPermitExample example);

    DeclareBuildingPermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildingPermit record, @Param("example") DeclareBuildingPermitExample example, @Param("selective") DeclareBuildingPermit.Column ... selective);

    int updateByExample(@Param("record") DeclareBuildingPermit record, @Param("example") DeclareBuildingPermitExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareBuildingPermit record, @Param("selective") DeclareBuildingPermit.Column ... selective);

    int updateByPrimaryKey(DeclareBuildingPermit record);

    int batchInsert(@Param("list") List<DeclareBuildingPermit> list);

    int batchInsertSelective(@Param("list") List<DeclareBuildingPermit> list, @Param("selective") DeclareBuildingPermit.Column ... selective);
}