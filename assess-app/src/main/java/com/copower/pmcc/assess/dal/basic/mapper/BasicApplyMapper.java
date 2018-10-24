package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicApplyMapper {
    int countByExample(BasicApplyExample example);

    int deleteByExample(BasicApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicApply record);

    int insertSelective(BasicApply record);

    List<BasicApply> selectByExample(BasicApplyExample example);

    BasicApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicApply record, @Param("example") BasicApplyExample example);

    int updateByExample(@Param("record") BasicApply record, @Param("example") BasicApplyExample example);

    int updateByPrimaryKeySelective(BasicApply record);

    int updateByPrimaryKey(BasicApply record);
}