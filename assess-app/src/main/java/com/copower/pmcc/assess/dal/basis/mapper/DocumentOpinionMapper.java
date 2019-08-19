package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DocumentOpinion;
import com.copower.pmcc.assess.dal.basis.entity.DocumentOpinionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentOpinionMapper {
    int countByExample(DocumentOpinionExample example);

    int deleteByExample(DocumentOpinionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentOpinion record);

    int insertSelective(DocumentOpinion record);

    List<DocumentOpinion> selectByExampleWithBLOBs(DocumentOpinionExample example);

    List<DocumentOpinion> selectByExample(DocumentOpinionExample example);

    DocumentOpinion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentOpinion record, @Param("example") DocumentOpinionExample example);

    int updateByExampleWithBLOBs(@Param("record") DocumentOpinion record, @Param("example") DocumentOpinionExample example);

    int updateByExample(@Param("record") DocumentOpinion record, @Param("example") DocumentOpinionExample example);

    int updateByPrimaryKeySelective(DocumentOpinion record);

    int updateByPrimaryKeyWithBLOBs(DocumentOpinion record);

    int updateByPrimaryKey(DocumentOpinion record);
}