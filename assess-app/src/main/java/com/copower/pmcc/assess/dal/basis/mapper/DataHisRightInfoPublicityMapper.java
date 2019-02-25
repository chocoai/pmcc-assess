package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataHisRightInfoPublicityMapper {
    int countByExample(DataHisRightInfoPublicityExample example);

    int deleteByExample(DataHisRightInfoPublicityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataHisRightInfoPublicity record);

    int insertSelective(DataHisRightInfoPublicity record);

    List<DataHisRightInfoPublicity> selectByExample(DataHisRightInfoPublicityExample example);

    DataHisRightInfoPublicity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataHisRightInfoPublicity record, @Param("example") DataHisRightInfoPublicityExample example);

    int updateByExample(@Param("record") DataHisRightInfoPublicity record, @Param("example") DataHisRightInfoPublicityExample example);

    int updateByPrimaryKeySelective(DataHisRightInfoPublicity record);

    int updateByPrimaryKey(DataHisRightInfoPublicity record);
}