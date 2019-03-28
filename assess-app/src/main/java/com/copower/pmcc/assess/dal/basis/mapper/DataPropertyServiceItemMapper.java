package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItem;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataPropertyServiceItemMapper {
    int countByExample(DataPropertyServiceItemExample example);

    int deleteByExample(DataPropertyServiceItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataPropertyServiceItem record);

    int insertSelective(DataPropertyServiceItem record);

    List<DataPropertyServiceItem> selectByExample(DataPropertyServiceItemExample example);

    DataPropertyServiceItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataPropertyServiceItem record, @Param("example") DataPropertyServiceItemExample example);

    int updateByExample(@Param("record") DataPropertyServiceItem record, @Param("example") DataPropertyServiceItemExample example);

    int updateByPrimaryKeySelective(DataPropertyServiceItem record);

    int updateByPrimaryKey(DataPropertyServiceItem record);
}