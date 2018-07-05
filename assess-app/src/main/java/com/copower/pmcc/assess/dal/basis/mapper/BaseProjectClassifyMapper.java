package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseProjectClassifyMapper {
    int countByExample(BaseProjectClassifyExample example);

    int deleteByExample(BaseProjectClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseProjectClassify record);

    int insertSelective(BaseProjectClassify record);

    List<BaseProjectClassify> selectByExample(BaseProjectClassifyExample example);

    BaseProjectClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseProjectClassify record, @Param("example") BaseProjectClassifyExample example);

    int updateByExample(@Param("record") BaseProjectClassify record, @Param("example") BaseProjectClassifyExample example);

    int updateByPrimaryKeySelective(BaseProjectClassify record);

    int updateByPrimaryKey(BaseProjectClassify record);
}