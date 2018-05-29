package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseAttachmentKeep;
import com.copower.pmcc.assess.dal.entity.BaseAttachmentKeepExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAttachmentKeepMapper {
    int countByExample(BaseAttachmentKeepExample example);

    int deleteByExample(BaseAttachmentKeepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseAttachmentKeep record);

    int insertSelective(BaseAttachmentKeep record);

    List<BaseAttachmentKeep> selectByExample(BaseAttachmentKeepExample example);

    BaseAttachmentKeep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseAttachmentKeep record, @Param("example") BaseAttachmentKeepExample example);

    int updateByExample(@Param("record") BaseAttachmentKeep record, @Param("example") BaseAttachmentKeepExample example);

    int updateByPrimaryKeySelective(BaseAttachmentKeep record);

    int updateByPrimaryKey(BaseAttachmentKeep record);
}