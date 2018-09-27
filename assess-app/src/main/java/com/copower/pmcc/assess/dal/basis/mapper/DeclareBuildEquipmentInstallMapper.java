package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEquipmentInstallMapper {
    int countByExample(DeclareBuildEquipmentInstallExample example);

    int deleteByExample(DeclareBuildEquipmentInstallExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEquipmentInstall record);

    int insertSelective(DeclareBuildEquipmentInstall record);

    List<DeclareBuildEquipmentInstall> selectByExample(DeclareBuildEquipmentInstallExample example);

    DeclareBuildEquipmentInstall selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEquipmentInstall record, @Param("example") DeclareBuildEquipmentInstallExample example);

    int updateByExample(@Param("record") DeclareBuildEquipmentInstall record, @Param("example") DeclareBuildEquipmentInstallExample example);

    int updateByPrimaryKeySelective(DeclareBuildEquipmentInstall record);

    int updateByPrimaryKey(DeclareBuildEquipmentInstall record);
}