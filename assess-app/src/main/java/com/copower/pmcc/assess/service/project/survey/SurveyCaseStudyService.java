package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCaseStudyService {

    @Autowired
    private SurveyCaseStudyDao surveyCaseStudyDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public boolean save(SurveyCaseStudy surveyCaseStudyDto) throws BusinessException {
        if(surveyCaseStudyDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyCaseStudyDto.getId() != null && surveyCaseStudyDto.getId() > 0){
            return surveyCaseStudyDao.update(surveyCaseStudyDto);
        }else{
            surveyCaseStudyDto.setCreator(processControllerComponent.getThisUser());
            return surveyCaseStudyDao.save(surveyCaseStudyDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyCaseStudyDao.delete(id);

    }


    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudy surveyCaseStudy= surveyCaseStudyDao.getSurveyCaseStudy(processInsId);
        return surveyCaseStudy;
    }
}
