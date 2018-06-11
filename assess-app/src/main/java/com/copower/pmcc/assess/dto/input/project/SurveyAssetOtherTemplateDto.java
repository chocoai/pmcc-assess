package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.SurveyAssetOtherTemplate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zly on 2018/5/10.
 */
public class SurveyAssetOtherTemplateDto extends SurveyAssetOtherTemplate {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exerciseDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date predictDueDate;

    @Override
    public Date getRegisterDate() {
        return registerDate;
    }

    @Override
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public Date getExerciseDate() {
        return exerciseDate;
    }

    @Override
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    @Override
    public Date getPredictDueDate() {
        return predictDueDate;
    }

    @Override
    public void setPredictDueDate(Date predictDueDate) {
        this.predictDueDate = predictDueDate;
    }
}
