package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeInfoMapper {
    int countByExample(SchemeInfoExample example);

    int deleteByExample(SchemeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeInfo record);

    int insertSelective(SchemeInfo record);

    List<SchemeInfo> selectByExample(SchemeInfoExample example);

    SchemeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeInfo record, @Param("example") SchemeInfoExample example);

    int updateByExample(@Param("record") SchemeInfo record, @Param("example") SchemeInfoExample example);

    int updateByPrimaryKeySelective(SchemeInfo record);

    int updateByPrimaryKey(SchemeInfo record);
}