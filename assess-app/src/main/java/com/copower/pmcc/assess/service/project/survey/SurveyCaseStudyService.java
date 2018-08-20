package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
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
    private CommonService commonService;

    public boolean saveSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) throws BusinessException {
        if(surveyCaseStudy == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyCaseStudy.getId() != null && surveyCaseStudy.getId() > 0){
            return surveyCaseStudyDao.updateSurveyCaseStudy(surveyCaseStudy);
        }else{
            surveyCaseStudy.setCreator(commonService.thisUserAccount());
            return surveyCaseStudyDao.addSurveyCaseStudy(surveyCaseStudy);
        }
    }

    public boolean deleteSurveyCaseStudy(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyCaseStudyDao.deleteSurveyCaseStudy(id);

    }


    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudy surveyCaseStudy= surveyCaseStudyDao.getSurveyCaseStudy(processInsId);
        return surveyCaseStudy;
    }
}
