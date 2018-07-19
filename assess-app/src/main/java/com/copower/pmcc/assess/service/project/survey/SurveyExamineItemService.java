package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineItemDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItem;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineItemService {
    @Autowired
    private SurveyExamineItemDao surveyExamineItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public boolean save(SurveyExamineItem surveyExamineItemDto) throws BusinessException {
        if(surveyExamineItemDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyExamineItemDto.getId() != null && surveyExamineItemDto.getId() > 0){
            return surveyExamineItemDao.update(surveyExamineItemDto);
        }else{
            surveyExamineItemDto.setCreator(processControllerComponent.getThisUser());
            return surveyExamineItemDao.save(surveyExamineItemDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyExamineItemDao.delete(id);

    }


    public SurveyExamineItem getSurveyExamineItem(String processInsId) {
        SurveyExamineItem surveyExamineItem= surveyExamineItemDao.getSurveyExamineItem(processInsId);
        return surveyExamineItem;
    }

    public SurveyExamineItem getExploreByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineItem surveyExamineItem= surveyExamineItemDao.getExamineItemByPlanDetailsId(planDetailsId);
        return surveyExamineItem;
    }
}
