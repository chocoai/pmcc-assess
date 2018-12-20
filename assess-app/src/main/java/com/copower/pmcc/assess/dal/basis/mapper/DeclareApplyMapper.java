package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareApplyMapper {
    int countByExample(DeclareApplyExample example);

    int deleteByExample(DeclareApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareApply record);

    int insertSelective(DeclareApply record);

    List<DeclareApply> selectByExample(DeclareApplyExample example);

    DeclareApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareApply record, @Param("example") DeclareApplyExample example);

    int updateByExample(@Param("record") DeclareApply record, @Param("example") DeclareApplyExample example);

    int updateByPrimaryKeySelective(DeclareApply record);

    int updateByPrimaryKey(DeclareApply record);
}