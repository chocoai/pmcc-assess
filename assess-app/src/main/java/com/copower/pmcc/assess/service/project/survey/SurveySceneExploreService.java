package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveySceneExploreDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveySceneExploreService {
    @Autowired
    private SurveySceneExploreDao surveySceneExploreDao;
    @Autowired
    private CommonService commonService;

    /**
     * 数据保存
     *
     * @param surveySceneExplore
     * @return
     * @throws BusinessException
     */
    public boolean saveSurveySceneExplore(SurveySceneExplore surveySceneExplore) throws BusinessException {
        if (surveySceneExplore == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveySceneExplore.getId() != null && surveySceneExplore.getId() > 0) {
            return surveySceneExploreDao.updateSurveySceneExplore(surveySceneExplore);
        } else {
            surveySceneExplore.setCreator(commonService.thisUserAccount());
            return surveySceneExploreDao.addSurveySceneExplore(surveySceneExplore);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean deleteSurveySceneExplore(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveySceneExploreDao.deleteSurveySceneExplore(id);

    }

    /**
     * 获取数据
     *
     * @param processInsId
     * @return
     */
    public SurveySceneExplore getSurveySceneExplore(String processInsId) {
        SurveySceneExplore surveySceneExplore = surveySceneExploreDao.getSurveySceneExplore(processInsId);
        return surveySceneExplore;
    }


}
