package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseType;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandUseTypeMapper {
    int countByExample(BasicEstateLandUseTypeExample example);

    int deleteByExample(BasicEstateLandUseTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandUseType record);

    int insertSelective(BasicEstateLandUseType record);

    List<BasicEstateLandUseType> selectByExample(BasicEstateLandUseTypeExample example);

    BasicEstateLandUseType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandUseType record, @Param("example") BasicEstateLandUseTypeExample example);

    int updateByExample(@Param("record") BasicEstateLandUseType record, @Param("example") BasicEstateLandUseTypeExample example);

    int updateByPrimaryKeySelective(BasicEstateLandUseType record);

    int updateByPrimaryKey(BasicEstateLandUseType record);
}