package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeInfoDetail;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfoDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemeInfoDetailMapper {
    int countByExample(SchemeInfoDetailExample example);

    int deleteByExample(SchemeInfoDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeInfoDetail record);

    int insertSelective(SchemeInfoDetail record);

    List<SchemeInfoDetail> selectByExample(SchemeInfoDetailExample example);

    SchemeInfoDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeInfoDetail record, @Param("example") SchemeInfoDetailExample example);

    int updateByExample(@Param("record") SchemeInfoDetail record, @Param("example") SchemeInfoDetailExample example);

    int updateByPrimaryKeySelective(SchemeInfoDetail record);

    int updateByPrimaryKey(SchemeInfoDetail record);
}