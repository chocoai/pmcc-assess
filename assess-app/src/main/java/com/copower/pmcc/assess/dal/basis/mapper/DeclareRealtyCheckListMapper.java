package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckList;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyCheckListMapper {
    int countByExample(DeclareRealtyCheckListExample example);

    int deleteByExample(DeclareRealtyCheckListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyCheckList record);

    int insertSelective(DeclareRealtyCheckList record);

    List<DeclareRealtyCheckList> selectByExample(DeclareRealtyCheckListExample example);

    DeclareRealtyCheckList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyCheckList record, @Param("example") DeclareRealtyCheckListExample example);

    int updateByExample(@Param("record") DeclareRealtyCheckList record, @Param("example") DeclareRealtyCheckListExample example);

    int updateByPrimaryKeySelective(DeclareRealtyCheckList record);

    int updateByPrimaryKey(DeclareRealtyCheckList record);
}