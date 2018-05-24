package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeInfoDetail;
import com.copower.pmcc.assess.dal.entity.SchemeInfoDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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