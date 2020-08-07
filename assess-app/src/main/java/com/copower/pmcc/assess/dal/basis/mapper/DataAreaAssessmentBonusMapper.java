package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAreaAssessmentBonusMapper {
    long countByExample(DataAreaAssessmentBonusExample example);

    int deleteByExample(DataAreaAssessmentBonusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAreaAssessmentBonus record);

    int insertSelective(@Param("record") DataAreaAssessmentBonus record, @Param("selective") DataAreaAssessmentBonus.Column ... selective);

    List<DataAreaAssessmentBonus> selectByExample(DataAreaAssessmentBonusExample example);

    DataAreaAssessmentBonus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAreaAssessmentBonus record, @Param("example") DataAreaAssessmentBonusExample example, @Param("selective") DataAreaAssessmentBonus.Column ... selective);

    int updateByExample(@Param("record") DataAreaAssessmentBonus record, @Param("example") DataAreaAssessmentBonusExample example);

    int updateByPrimaryKeySelective(@Param("record") DataAreaAssessmentBonus record, @Param("selective") DataAreaAssessmentBonus.Column ... selective);

    int updateByPrimaryKey(DataAreaAssessmentBonus record);

    int batchInsert(@Param("list") List<DataAreaAssessmentBonus> list);

    int batchInsertSelective(@Param("list") List<DataAreaAssessmentBonus> list, @Param("selective") DataAreaAssessmentBonus.Column ... selective);
}