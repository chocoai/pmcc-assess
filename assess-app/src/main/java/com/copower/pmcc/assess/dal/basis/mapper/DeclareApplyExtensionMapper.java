package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtension;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtensionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareApplyExtensionMapper {
    int countByExample(DeclareApplyExtensionExample example);

    int deleteByExample(DeclareApplyExtensionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareApplyExtension record);

    int insertSelective(DeclareApplyExtension record);

    List<DeclareApplyExtension> selectByExample(DeclareApplyExtensionExample example);

    DeclareApplyExtension selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareApplyExtension record, @Param("example") DeclareApplyExtensionExample example);

    int updateByExample(@Param("record") DeclareApplyExtension record, @Param("example") DeclareApplyExtensionExample example);

    int updateByPrimaryKeySelective(DeclareApplyExtension record);

    int updateByPrimaryKey(DeclareApplyExtension record);
}