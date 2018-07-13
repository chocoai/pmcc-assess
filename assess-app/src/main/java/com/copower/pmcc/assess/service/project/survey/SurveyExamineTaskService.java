package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.suvey.SurveyExamineTaskDao;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineTaskService {
    @Autowired
    private SurveyExamineTaskDao surveyExamineTaskDao;
    @Autowired
    private CommonService commonService;


}
