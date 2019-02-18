package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingLeisurePlaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingLeisurePlaceMapper {
    int countByExample(BasicMatchingLeisurePlaceExample example);

    int deleteByExample(BasicMatchingLeisurePlaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingLeisurePlace record);

    int insertSelective(BasicMatchingLeisurePlace record);

    List<BasicMatchingLeisurePlace> selectByExample(BasicMatchingLeisurePlaceExample example);

    BasicMatchingLeisurePlace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingLeisurePlace record, @Param("example") BasicMatchingLeisurePlaceExample example);

    int updateByExample(@Param("record") BasicMatchingLeisurePlace record, @Param("example") BasicMatchingLeisurePlaceExample example);

    int updateByPrimaryKeySelective(BasicMatchingLeisurePlace record);

    int updateByPrimaryKey(BasicMatchingLeisurePlace record);
}