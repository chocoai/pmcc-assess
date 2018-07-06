package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorate;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseRoomDecorateMapper {
    int countByExample(CaseHouseRoomDecorateExample example);

    int deleteByExample(CaseHouseRoomDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseRoomDecorate record);

    int insertSelective(CaseHouseRoomDecorate record);

    List<CaseHouseRoomDecorate> selectByExample(CaseHouseRoomDecorateExample example);

    CaseHouseRoomDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseRoomDecorate record, @Param("example") CaseHouseRoomDecorateExample example);

    int updateByExample(@Param("record") CaseHouseRoomDecorate record, @Param("example") CaseHouseRoomDecorateExample example);

    int updateByPrimaryKeySelective(CaseHouseRoomDecorate record);

    int updateByPrimaryKey(CaseHouseRoomDecorate record);
}