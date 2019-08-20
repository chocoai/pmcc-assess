package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveySceneExploreDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/projectTaskCIP")
public class ProjectTaskCIPController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private SurveySceneExploreDao surveySceneExploreDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicEstateService basicEstateService;

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", method = {RequestMethod.POST}, name = "保存")
    public HttpResult save(String formData, Integer masterId,Integer planDetailsId) {
        try {
            Map<String, Object> objectMap = Maps.newHashMap();
            SurveySceneExplore sceneExploreById = surveySceneExploreDao.getSurveySceneExploreById(masterId);
            if (sceneExploreById != null) {
                JSONObject jsonObject = JSON.parseObject(formData);
                BasicApplyBatch applyBatch = basicApplyBatchService.getInfoById(sceneExploreById.getBatchApplyId());
                applyBatch.setEstateName(jsonObject.getString("estateName"));
                basicApplyBatchDao.updateInfo(applyBatch);
                String estateId = jsonObject.getString("estateId");
                BasicEstate estate = basicEstateService.getBasicEstateById(Integer.valueOf(estateId));
                estate.setName(jsonObject.getString("estateName"));
                basicEstateService.saveAndUpdateBasicEstate(estate);

                objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicApplyBatch.class.getSimpleName()), applyBatch);
                objectMap.put(FormatUtils.toLowerCaseFirstChar(SurveySceneExplore.class.getSimpleName()), sceneExploreById);
            } else {
                BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
                basicApplyBatchService.saveApplyInfo(applyBatch);
                SurveySceneExplore surveySceneExplore = new SurveySceneExplore();
                surveySceneExplore.setBatchApplyId(applyBatch.getId());
                surveySceneExplore.setPlanDetailsId(planDetailsId);
                surveySceneExplore.setCreator(commonService.thisUserAccount());
                surveySceneExploreDao.addSurveySceneExplore(surveySceneExplore);
                objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicApplyBatch.class.getSimpleName()), applyBatch);
                objectMap.put(FormatUtils.toLowerCaseFirstChar(SurveySceneExplore.class.getSimpleName()), surveySceneExplore);
            }
            return HttpResult.newCorrectResult(objectMap);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveData", name = "保存数据", method = RequestMethod.POST)
    public HttpResult submitTask(String formData) {
        try {
            SurveySceneExplore surveySceneExplore = JSON.parseObject(formData, SurveySceneExplore.class);
            surveySceneExploreDao.updateSurveySceneExplore(surveySceneExplore);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("保存数据异常");
        }
        return HttpResult.newCorrectResult();
    }

}
