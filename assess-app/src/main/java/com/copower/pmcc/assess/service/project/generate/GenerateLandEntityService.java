package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 土地实体信息
 */
@Service
public class GenerateLandEntityService {

    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 土地名称
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getLandName(BasicApply basicApply)throws Exception{
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return landStateVo.getName();
    }


    public String fourTheFor(BasicApply basicApply)throws Exception{
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return null;
    }
}
