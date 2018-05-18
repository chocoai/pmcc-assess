package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliary;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeAreaGroupAuxiliaryMapper {
    int countByExample(SchemeAreaGroupAuxiliaryExample example);

    int deleteByExample(SchemeAreaGroupAuxiliaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeAreaGroupAuxiliary record);

    int insertSelective(SchemeAreaGroupAuxiliary record);

    List<SchemeAreaGroupAuxiliary> selectByExample(SchemeAreaGroupAuxiliaryExample example);

    SchemeAreaGroupAuxiliary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeAreaGroupAuxiliary record, @Param("example") SchemeAreaGroupAuxiliaryExample example);

    int updateByExample(@Param("record") SchemeAreaGroupAuxiliary record, @Param("example") SchemeAreaGroupAuxiliaryExample example);

    int updateByPrimaryKeySelective(SchemeAreaGroupAuxiliary record);

    int updateByPrimaryKey(SchemeAreaGroupAuxiliary record);
}