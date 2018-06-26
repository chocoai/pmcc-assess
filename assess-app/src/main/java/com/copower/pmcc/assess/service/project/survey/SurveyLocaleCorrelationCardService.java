package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.dao.project.suvey.SurveyLocaleCorrelationCardDao;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleCorrelationCardDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zly on 2018/5/16.
 */
@Service
public class SurveyLocaleCorrelationCardService {

    @Autowired
    private SurveyLocaleCorrelationCardDao surveyLocaleCorrelationCardDao;

    public boolean save(SurveyLocaleCorrelationCardDto surveyLocaleCorrelationCardDto) throws BusinessException {
        if(surveyLocaleCorrelationCardDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyLocaleCorrelationCardDto.getId() != null && surveyLocaleCorrelationCardDto.getId() > 0){
            return surveyLocaleCorrelationCardDao.update(surveyLocaleCorrelationCardDto);
        }else{

            return surveyLocaleCorrelationCardDao.save(surveyLocaleCorrelationCardDto);
        }
    }
}
