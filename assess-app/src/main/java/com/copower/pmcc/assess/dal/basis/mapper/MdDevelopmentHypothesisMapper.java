package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentHypothesis;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentHypothesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentHypothesisMapper {
    int countByExample(MdDevelopmentHypothesisExample example);

    int deleteByExample(MdDevelopmentHypothesisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentHypothesis record);

    int insertSelective(MdDevelopmentHypothesis record);

    List<MdDevelopmentHypothesis> selectByExample(MdDevelopmentHypothesisExample example);

    MdDevelopmentHypothesis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentHypothesis record, @Param("example") MdDevelopmentHypothesisExample example);

    int updateByExample(@Param("record") MdDevelopmentHypothesis record, @Param("example") MdDevelopmentHypothesisExample example);

    int updateByPrimaryKeySelective(MdDevelopmentHypothesis record);

    int updateByPrimaryKey(MdDevelopmentHypothesis record);
}