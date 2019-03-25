package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取区位数据
 */
@Service
public class GenerateLoactionService {
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicHouseService basicHouseService;


    /**
     * 获取坐落信息
     * @param declareId
     * @param eatateId
     * @return
     */
    public String getSeat(Integer declareId,Integer eatateId){

        return null;
    }

    /**
     * 获取楼层信息
     * @param judgeObjectIds
     * @return
     */
    public String getFloor(List<Integer> judgeObjectIds){
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            String floor = basicHouse.getFloor();
        }
        return null;
    }
}
