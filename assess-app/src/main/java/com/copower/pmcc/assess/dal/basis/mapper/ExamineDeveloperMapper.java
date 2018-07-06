package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.ExamineDeveloperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineDeveloperMapper {
    int countByExample(ExamineDeveloperExample example);

    int deleteByExample(ExamineDeveloperExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineDeveloper record);

    int insertSelective(ExamineDeveloper record);

    List<ExamineDeveloper> selectByExample(ExamineDeveloperExample example);

    ExamineDeveloper selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineDeveloper record, @Param("example") ExamineDeveloperExample example);

    int updateByExample(@Param("record") ExamineDeveloper record, @Param("example") ExamineDeveloperExample example);

    int updateByPrimaryKeySelective(ExamineDeveloper record);

    int updateByPrimaryKey(ExamineDeveloper record);
}