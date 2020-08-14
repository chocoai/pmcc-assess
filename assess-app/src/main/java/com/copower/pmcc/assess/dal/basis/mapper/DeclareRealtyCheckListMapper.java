package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckList;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareRealtyCheckListMapper {
    long countByExample(DeclareRealtyCheckListExample example);

    int deleteByExample(DeclareRealtyCheckListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareRealtyCheckList record);

    int insertSelective(@Param("record") DeclareRealtyCheckList record, @Param("selective") DeclareRealtyCheckList.Column ... selective);

    List<DeclareRealtyCheckList> selectByExample(DeclareRealtyCheckListExample example);

    DeclareRealtyCheckList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareRealtyCheckList record, @Param("example") DeclareRealtyCheckListExample example, @Param("selective") DeclareRealtyCheckList.Column ... selective);

    int updateByExample(@Param("record") DeclareRealtyCheckList record, @Param("example") DeclareRealtyCheckListExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareRealtyCheckList record, @Param("selective") DeclareRealtyCheckList.Column ... selective);

    int updateByPrimaryKey(DeclareRealtyCheckList record);

    int batchInsert(@Param("list") List<DeclareRealtyCheckList> list);

    int batchInsertSelective(@Param("list") List<DeclareRealtyCheckList> list, @Param("selective") DeclareRealtyCheckList.Column ... selective);
}