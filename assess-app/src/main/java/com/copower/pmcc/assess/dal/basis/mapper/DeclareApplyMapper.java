package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareApplyMapper {
    long countByExample(DeclareApplyExample example);

    int deleteByExample(DeclareApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareApply record);

    int insertSelective(@Param("record") DeclareApply record, @Param("selective") DeclareApply.Column ... selective);

    List<DeclareApply> selectByExample(DeclareApplyExample example);

    DeclareApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareApply record, @Param("example") DeclareApplyExample example, @Param("selective") DeclareApply.Column ... selective);

    int updateByExample(@Param("record") DeclareApply record, @Param("example") DeclareApplyExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareApply record, @Param("selective") DeclareApply.Column ... selective);

    int updateByPrimaryKey(DeclareApply record);

    int batchInsert(@Param("list") List<DeclareApply> list);

    int batchInsertSelective(@Param("list") List<DeclareApply> list, @Param("selective") DeclareApply.Column ... selective);
}