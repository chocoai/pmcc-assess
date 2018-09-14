package com.copower.pmcc.assess.dto.output.project.survey;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-7-18.
 */
public class SurveyExamineDataInfoVo {
    private ExamineBlockVo examineBlockVo;
    private ExamineEstateVo examineEstateVo;
    private ExamineEstateLandStateVo examineEstateLandStateVo;
    private ExamineUnitVo examineUnitVo;
    private ExamineHouseVo examineHouseVo;
    private ExamineHouseTradingVo examineHouseTradingVo;
    private Map<String,ExamineBuildingVo> examineBuildingVoMap = Maps.newHashMap();

    public ExamineBlockVo getExamineBlockVo() {
        return examineBlockVo;
    }

    public void setExamineBlockVo(ExamineBlockVo examineBlockVo) {
        this.examineBlockVo = examineBlockVo;
    }

    public ExamineEstateVo getExamineEstateVo() {
        return examineEstateVo;
    }

    public void setExamineEstateVo(ExamineEstateVo examineEstateVo) {
        this.examineEstateVo = examineEstateVo;
    }

    public ExamineEstateLandStateVo getExamineEstateLandStateVo() {
        return examineEstateLandStateVo;
    }

    public void setExamineEstateLandStateVo(ExamineEstateLandStateVo examineEstateLandStateVo) {
        this.examineEstateLandStateVo = examineEstateLandStateVo;
    }

    public ExamineUnitVo getExamineUnitVo() {
        return examineUnitVo;
    }

    public void setExamineUnitVo(ExamineUnitVo examineUnitVo) {
        this.examineUnitVo = examineUnitVo;
    }

    public ExamineHouseVo getExamineHouseVo() {
        return examineHouseVo;
    }

    public void setExamineHouseVo(ExamineHouseVo examineHouseVo) {
        this.examineHouseVo = examineHouseVo;
    }

    public ExamineHouseTradingVo getExamineHouseTradingVo() {
        return examineHouseTradingVo;
    }

    public void setExamineHouseTradingVo(ExamineHouseTradingVo examineHouseTradingVo) {
        this.examineHouseTradingVo = examineHouseTradingVo;
    }


    public Map<String, ExamineBuildingVo> getExamineBuildingVoMap() {
        return examineBuildingVoMap;
    }

    public void setExamineBuildingVoMap(Map<String, ExamineBuildingVo> examineBuildingVoMap) {
        this.examineBuildingVoMap = examineBuildingVoMap;
    }
}
