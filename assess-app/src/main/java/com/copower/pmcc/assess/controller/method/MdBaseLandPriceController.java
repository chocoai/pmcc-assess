package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: HUHAO
 * @Date: 2018/9/7 10:00
 * @Description:
 */
@RequestMapping(value = "/baseLandPrice")
@Controller
public class MdBaseLandPriceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicApplyService basicApplyService;


    @ResponseBody
    @RequestMapping(value = "/getLandIndexId", name = "获取土地指数表id", method = RequestMethod.GET)
    public HttpResult getLandIndexId(Integer judgeObjectId) {
        try {
            Integer landIndexId = null;
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());

            //找到委估对象对应的土地指数
            BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
            List<DataHousePriceIndex> dataHouseIndexList = null;
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), basicEstate.getDistrict(), dataDic.getId());
            if (CollectionUtils.isEmpty(dataHouseIndexList)) {
                dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(basicEstate.getProvince(), basicEstate.getCity(), null, dataDic.getId());
            }
            if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
                landIndexId = dataHouseIndexList.get(0).getId();
            }
            return HttpResult.newCorrectResult(landIndexId);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getLevelDetailId", name = "获取土地级别id", method = RequestMethod.GET)
    public HttpResult getLevelDetailId(Integer judgeObjectId) {
        try {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            return HttpResult.newCorrectResult(landStateByEstateId.getLandLevel());
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }
}
