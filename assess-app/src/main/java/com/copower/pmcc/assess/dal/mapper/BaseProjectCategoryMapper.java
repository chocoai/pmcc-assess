package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseProjectCategory;
import com.copower.pmcc.assess.dal.entity.BaseProjectCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseProjectCategoryMapper {
    int countByExample(BaseProjectCategoryExample example);

    int deleteByExample(BaseProjectCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseProjectCategory record);

    int insertSelective(BaseProjectCategory record);

    List<BaseProjectCategory> selectByExample(BaseProjectCategoryExample example);

    BaseProjectCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseProjectCategory record, @Param("example") BaseProjectCategoryExample example);

    int updateByExample(@Param("record") BaseProjectCategory record, @Param("example") BaseProjectCategoryExample example);

    int updateByPrimaryKeySelective(BaseProjectCategory record);

    int updateByPrimaryKey(BaseProjectCategory record);
}