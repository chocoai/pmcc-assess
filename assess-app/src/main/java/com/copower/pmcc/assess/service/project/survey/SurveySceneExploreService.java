package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveySceneExploreDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 * 现场查勘主表
 */
@Service
public class SurveySceneExploreService {
    @Autowired
    private SurveySceneExploreDao surveySceneExploreDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyDao basicApplyDao;

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

    public boolean updateSurveySceneExplore(SurveySceneExplore surveySceneExplore) {
        if (surveySceneExplore == null) {
            return false;
        }
        return surveySceneExploreDao.updateSurveySceneExplore(surveySceneExplore);
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

    public SurveySceneExplore getSurveySceneExplore(Integer planDetailsId) {
        SurveySceneExplore where = new SurveySceneExplore();
        where.setPlanDetailsId(planDetailsId);
        SurveySceneExplore surveySceneExplore = surveySceneExploreDao.getSurveySceneExplore(where);
        return surveySceneExplore;
    }

    public SurveySceneExplore getSurveySceneExploreById(Integer id){
        return surveySceneExploreDao.getSurveySceneExploreById(id);
    }

    public SurveySceneExplore getSurveySceneExploreByBatchApplyId(Integer batchApplyId) {
        SurveySceneExplore surveySceneExplore = new SurveySceneExplore();
        surveySceneExplore.setBatchApplyId(batchApplyId);
        return surveySceneExploreDao.getSurveySceneExplore(surveySceneExplore);
    }

    //删除未完成数据
    public void deleteUnfinishedData() {
        List<SurveySceneExplore> unfinishedDatas = surveySceneExploreDao.getUnfinishedData();
        if(CollectionUtils.isEmpty(unfinishedDatas)) return;
        for (SurveySceneExplore item : unfinishedDatas) {
            BasicApply basicApply = new BasicApply();
            Integer pid = projectPlanDetailsService.getProjectPlanDetailsById(item.getPlanDetailsId()).getPid();
            basicApply.setPlanDetailsId(pid);
            List<BasicApply> basicApplyList = basicApplyDao.getBasicApplyList(basicApply);
            if(CollectionUtils.isNotEmpty(basicApplyList)) {
                for (BasicApply basicApplyItem : basicApplyList) {
                    basicApplyDao.deleteBasicApply(basicApplyItem.getId());
                }
            }
            surveySceneExploreDao.deleteSurveySceneExplore(item.getId());
        }
    }


}
