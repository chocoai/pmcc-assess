package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dal.entity.SurveyAssetOtherTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zly on 2018/5/11.
 */
@Service
public class SurveyAssetOtherTemplateService {

    @Autowired
    private SurveyAssetOtherTemplateDao surveyAssetOtherTemplateDao;

    public ModelAndView getSurveyAssetOtherTemplateByPid(ModelAndView modelAndView, Integer pid){
        SurveyAssetOtherTemplate surveyAssetOtherTemplate = surveyAssetOtherTemplateDao.getSurveyAssetOtherTemplateByPid(pid);
        if (surveyAssetOtherTemplate != null){
            modelAndView.addObject("surveyAssetOtherTemplate",surveyAssetOtherTemplate);
            return modelAndView;
        }
        return modelAndView;
    }
}
