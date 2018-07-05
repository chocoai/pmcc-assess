package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zly on 2018/5/9.
 */
public class SurveyAssetInventoryDto extends SurveyAssetInventory{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    @Override
    public Date getCheckDate() {
        return checkDate;
    }

    @Override
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
