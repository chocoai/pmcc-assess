package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DeclareUseClassify;
import com.copower.pmcc.assess.dal.entity.DeclareUseClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareUseClassifyMapper {
    int countByExample(DeclareUseClassifyExample example);

    int deleteByExample(DeclareUseClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareUseClassify record);

    int insertSelective(DeclareUseClassify record);

    List<DeclareUseClassify> selectByExample(DeclareUseClassifyExample example);

    DeclareUseClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareUseClassify record, @Param("example") DeclareUseClassifyExample example);

    int updateByExample(@Param("record") DeclareUseClassify record, @Param("example") DeclareUseClassifyExample example);

    int updateByPrimaryKeySelective(DeclareUseClassify record);

    int updateByPrimaryKey(DeclareUseClassify record);
}