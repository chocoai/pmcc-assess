package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItem;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingPropertyServiceItemMapper {
    int countByExample(CaseBuildingPropertyServiceItemExample example);

    int deleteByExample(CaseBuildingPropertyServiceItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingPropertyServiceItem record);

    int insertSelective(CaseBuildingPropertyServiceItem record);

    List<CaseBuildingPropertyServiceItem> selectByExample(CaseBuildingPropertyServiceItemExample example);

    CaseBuildingPropertyServiceItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingPropertyServiceItem record, @Param("example") CaseBuildingPropertyServiceItemExample example);

    int updateByExample(@Param("record") CaseBuildingPropertyServiceItem record, @Param("example") CaseBuildingPropertyServiceItemExample example);

    int updateByPrimaryKeySelective(CaseBuildingPropertyServiceItem record);

    int updateByPrimaryKey(CaseBuildingPropertyServiceItem record);
}