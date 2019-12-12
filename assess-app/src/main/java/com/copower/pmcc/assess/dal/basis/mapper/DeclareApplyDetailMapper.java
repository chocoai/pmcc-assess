package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetail;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareApplyDetailMapper {
    int countByExample(DeclareApplyDetailExample example);

    int deleteByExample(DeclareApplyDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareApplyDetail record);

    int insertSelective(DeclareApplyDetail record);

    List<DeclareApplyDetail> selectByExample(DeclareApplyDetailExample example);

    DeclareApplyDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareApplyDetail record, @Param("example") DeclareApplyDetailExample example);

    int updateByExample(@Param("record") DeclareApplyDetail record, @Param("example") DeclareApplyDetailExample example);

    int updateByPrimaryKeySelective(DeclareApplyDetail record);

    int updateByPrimaryKey(DeclareApplyDetail record);
}