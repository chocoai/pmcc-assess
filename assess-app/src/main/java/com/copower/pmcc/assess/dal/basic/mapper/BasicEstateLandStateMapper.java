package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandStateMapper {
    int countByExample(BasicEstateLandStateExample example);

    int deleteByExample(BasicEstateLandStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandState record);

    int insertSelective(BasicEstateLandState record);

    List<BasicEstateLandState> selectByExample(BasicEstateLandStateExample example);

    BasicEstateLandState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandState record, @Param("example") BasicEstateLandStateExample example);

    int updateByExample(@Param("record") BasicEstateLandState record, @Param("example") BasicEstateLandStateExample example);

    int updateByPrimaryKeySelective(BasicEstateLandState record);

    int updateByPrimaryKey(BasicEstateLandState record);
}