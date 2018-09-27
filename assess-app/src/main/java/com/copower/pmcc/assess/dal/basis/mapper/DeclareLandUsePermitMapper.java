package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareLandUsePermitMapper {
    int countByExample(DeclareLandUsePermitExample example);

    int deleteByExample(DeclareLandUsePermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareLandUsePermit record);

    int insertSelective(DeclareLandUsePermit record);

    List<DeclareLandUsePermit> selectByExample(DeclareLandUsePermitExample example);

    DeclareLandUsePermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareLandUsePermit record, @Param("example") DeclareLandUsePermitExample example);

    int updateByExample(@Param("record") DeclareLandUsePermit record, @Param("example") DeclareLandUsePermitExample example);

    int updateByPrimaryKeySelective(DeclareLandUsePermit record);

    int updateByPrimaryKey(DeclareLandUsePermit record);
}