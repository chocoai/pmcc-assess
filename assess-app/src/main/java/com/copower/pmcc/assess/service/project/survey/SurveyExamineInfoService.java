package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineInfo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineInfoService {

    @Autowired
    private SurveyExamineInfoDao surveyExamineInfoDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public boolean save(SurveyExamineInfo surveyExamineInfoDto) throws BusinessException {
        if(surveyExamineInfoDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyExamineInfoDto.getId() != null && surveyExamineInfoDto.getId() > 0){
            return surveyExamineInfoDao.update(surveyExamineInfoDto);
        }else{
            surveyExamineInfoDto.setCreator(processControllerComponent.getThisUser());
            return surveyExamineInfoDao.save(surveyExamineInfoDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyExamineInfoDao.delete(id);

    }

    public SurveyExamineInfo getExamineInfoByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineInfo surveyExamineInfo= surveyExamineInfoDao.getExamineInfoByPlanDetailsId(planDetailsId);
        return surveyExamineInfo;
    }
}
