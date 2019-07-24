package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingPropertyServiceItemMapper {
    int countByExample(BasicBuildingPropertyServiceItemExample example);

    int deleteByExample(BasicBuildingPropertyServiceItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingPropertyServiceItem record);

    int insertSelective(BasicBuildingPropertyServiceItem record);

    List<BasicBuildingPropertyServiceItem> selectByExample(BasicBuildingPropertyServiceItemExample example);

    BasicBuildingPropertyServiceItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingPropertyServiceItem record, @Param("example") BasicBuildingPropertyServiceItemExample example);

    int updateByExample(@Param("record") BasicBuildingPropertyServiceItem record, @Param("example") BasicBuildingPropertyServiceItemExample example);

    int updateByPrimaryKeySelective(BasicBuildingPropertyServiceItem record);

    int updateByPrimaryKey(BasicBuildingPropertyServiceItem record);
}