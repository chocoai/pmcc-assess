package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.BaseAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseAttachmentMapper {
    int countByExample(BaseAttachmentExample example);

    int deleteByExample(BaseAttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseAttachment record);

    int insertSelective(BaseAttachment record);

    List<BaseAttachment> selectByExample(BaseAttachmentExample example);

    BaseAttachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseAttachment record, @Param("example") BaseAttachmentExample example);

    int updateByExample(@Param("record") BaseAttachment record, @Param("example") BaseAttachmentExample example);

    int updateByPrimaryKeySelective(BaseAttachment record);

    int updateByPrimaryKey(BaseAttachment record);
}