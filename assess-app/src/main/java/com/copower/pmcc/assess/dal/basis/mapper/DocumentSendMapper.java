package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DocumentSend;
import com.copower.pmcc.assess.dal.basis.entity.DocumentSendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentSendMapper {
    int countByExample(DocumentSendExample example);

    int deleteByExample(DocumentSendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentSend record);

    int insertSelective(DocumentSend record);

    List<DocumentSend> selectByExampleWithBLOBs(DocumentSendExample example);

    List<DocumentSend> selectByExample(DocumentSendExample example);

    DocumentSend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentSend record, @Param("example") DocumentSendExample example);

    int updateByExampleWithBLOBs(@Param("record") DocumentSend record, @Param("example") DocumentSendExample example);

    int updateByExample(@Param("record") DocumentSend record, @Param("example") DocumentSendExample example);

    int updateByPrimaryKeySelective(DocumentSend record);

    int updateByPrimaryKeyWithBLOBs(DocumentSend record);

    int updateByPrimaryKey(DocumentSend record);
}