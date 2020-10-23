package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeJudgeObjectMapper {
    long countByExample(SchemeJudgeObjectExample example);

    int deleteByExample(SchemeJudgeObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeJudgeObject record);

    int insertSelective(@Param("record") SchemeJudgeObject record, @Param("selective") SchemeJudgeObject.Column ... selective);

    List<SchemeJudgeObject> selectByExample(SchemeJudgeObjectExample example);

    SchemeJudgeObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeJudgeObject record, @Param("example") SchemeJudgeObjectExample example, @Param("selective") SchemeJudgeObject.Column ... selective);

    int updateByExample(@Param("record") SchemeJudgeObject record, @Param("example") SchemeJudgeObjectExample example);

    int updateByPrimaryKeySelective(@Param("record") SchemeJudgeObject record, @Param("selective") SchemeJudgeObject.Column ... selective);

    int updateByPrimaryKey(SchemeJudgeObject record);

    int batchInsert(@Param("list") List<SchemeJudgeObject> list);

    int batchInsertSelective(@Param("list") List<SchemeJudgeObject> list, @Param("selective") SchemeJudgeObject.Column ... selective);
}