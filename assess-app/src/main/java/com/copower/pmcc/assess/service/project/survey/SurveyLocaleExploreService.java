package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.dao.project.suvey.SurveyLocaleExploreDao;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyLocaleExploreService {

    @Autowired
    private SurveyLocaleExploreDao surveyLocaleExploreDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public boolean save(SurveyLocaleExplore surveyLocaleExploreDto) throws BusinessException {
        if(surveyLocaleExploreDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyLocaleExploreDto.getId() != null && surveyLocaleExploreDto.getId() > 0){
            return surveyLocaleExploreDao.update(surveyLocaleExploreDto);
        }else{
            surveyLocaleExploreDto.setCreator(processControllerComponent.getThisUser());
            return surveyLocaleExploreDao.save(surveyLocaleExploreDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyLocaleExploreDao.delete(id);

    }


    public SurveyLocaleExplore getSurveyLocaleExplore(String processInsId) {
        SurveyLocaleExplore surveyLocaleExplore= surveyLocaleExploreDao.getSurveyLocaleExplore(processInsId);
        return surveyLocaleExplore;
    }
}
