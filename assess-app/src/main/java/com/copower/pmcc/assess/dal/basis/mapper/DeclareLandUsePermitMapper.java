package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareLandUsePermitMapper {
    long countByExample(DeclareLandUsePermitExample example);

    int deleteByExample(DeclareLandUsePermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareLandUsePermit record);

    int insertSelective(@Param("record") DeclareLandUsePermit record, @Param("selective") DeclareLandUsePermit.Column ... selective);

    List<DeclareLandUsePermit> selectByExample(DeclareLandUsePermitExample example);

    DeclareLandUsePermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareLandUsePermit record, @Param("example") DeclareLandUsePermitExample example, @Param("selective") DeclareLandUsePermit.Column ... selective);

    int updateByExample(@Param("record") DeclareLandUsePermit record, @Param("example") DeclareLandUsePermitExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareLandUsePermit record, @Param("selective") DeclareLandUsePermit.Column ... selective);

    int updateByPrimaryKey(DeclareLandUsePermit record);

    int batchInsert(@Param("list") List<DeclareLandUsePermit> list);

    int batchInsertSelective(@Param("list") List<DeclareLandUsePermit> list, @Param("selective") DeclareLandUsePermit.Column ... selective);
}