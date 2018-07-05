package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDataDicMapper {
    int countByExample(BaseDataDicExample example);

    int deleteByExample(BaseDataDicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseDataDic record);

    int insertSelective(BaseDataDic record);

    List<BaseDataDic> selectByExample(BaseDataDicExample example);

    BaseDataDic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseDataDic record, @Param("example") BaseDataDicExample example);

    int updateByExample(@Param("record") BaseDataDic record, @Param("example") BaseDataDicExample example);

    int updateByPrimaryKeySelective(BaseDataDic record);

    int updateByPrimaryKey(BaseDataDic record);
}