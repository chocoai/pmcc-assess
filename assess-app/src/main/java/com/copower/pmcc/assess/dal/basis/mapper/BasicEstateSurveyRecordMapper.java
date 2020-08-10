package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSurveyRecord;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSurveyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateSurveyRecordMapper {
    long countByExample(BasicEstateSurveyRecordExample example);

    int deleteByExample(BasicEstateSurveyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateSurveyRecord record);

    int insertSelective(@Param("record") BasicEstateSurveyRecord record, @Param("selective") BasicEstateSurveyRecord.Column ... selective);

    List<BasicEstateSurveyRecord> selectByExample(BasicEstateSurveyRecordExample example);

    BasicEstateSurveyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateSurveyRecord record, @Param("example") BasicEstateSurveyRecordExample example, @Param("selective") BasicEstateSurveyRecord.Column ... selective);

    int updateByExample(@Param("record") BasicEstateSurveyRecord record, @Param("example") BasicEstateSurveyRecordExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicEstateSurveyRecord record, @Param("selective") BasicEstateSurveyRecord.Column ... selective);

    int updateByPrimaryKey(BasicEstateSurveyRecord record);

    int batchInsert(@Param("list") List<BasicEstateSurveyRecord> list);

    int batchInsertSelective(@Param("list") List<BasicEstateSurveyRecord> list, @Param("selective") BasicEstateSurveyRecord.Column ... selective);
}