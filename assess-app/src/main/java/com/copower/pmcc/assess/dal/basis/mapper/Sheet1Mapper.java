package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.Sheet1;
import com.copower.pmcc.assess.dal.basis.entity.Sheet1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Sheet1Mapper {
    int countByExample(Sheet1Example example);

    int deleteByExample(Sheet1Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sheet1 record);

    int insertSelective(Sheet1 record);

    List<Sheet1> selectByExample(Sheet1Example example);

    Sheet1 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sheet1 record, @Param("example") Sheet1Example example);

    int updateByExample(@Param("record") Sheet1 record, @Param("example") Sheet1Example example);

    int updateByPrimaryKeySelective(Sheet1 record);

    int updateByPrimaryKey(Sheet1 record);
}