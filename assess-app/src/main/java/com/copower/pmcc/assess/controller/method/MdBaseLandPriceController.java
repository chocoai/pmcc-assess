package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: HUHAO
 * @Date: 2018/9/7 10:00
 * @Description:基准地价法
 */
@RequestMapping(value = "/baseLandPrice")
@RestController
public class MdBaseLandPriceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private DataHousePriceIndexService dataHousePriceIndexService;

    @RequestMapping(value = "/index", name = "基准地价法测算")
    public ModelAndView index(Integer dataId, Integer judgeObjectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketBaseLandPriceIndex");
        MdBaseLandPrice mdBaseLandPrice = null;
        if (dataId != null && dataId != 0) {
            mdBaseLandPrice = mdBaseLandPriceService.getMdBaseLandPriceDao().getMdBaseLandPrice(dataId);
        } else {
            mdBaseLandPrice = mdBaseLandPriceService.initObject(judgeObjectId);
        }
        modelAndView.addObject("master", mdBaseLandPrice);
        modelAndView.addObject("apply", "apply");
        mdBaseLandPriceService.setViewParam(judgeObjectId, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "基准地价法 详情")
    public ModelAndView detail(Integer dataId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketBaseLandPriceDetail");
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getMdBaseLandPriceDao().getMdBaseLandPrice(dataId);
        modelAndView.addObject("master", mdBaseLandPrice);
        mdBaseLandPriceService.setViewParam(mdBaseLandPrice.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/getLandPriceIndexDetailList", name = "获取土地指数信息", method = RequestMethod.GET)
    public BootstrapTableVo getLandPriceIndexDetailList(Integer judgeObjectId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexService.getLandPriceIndexDetailList(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict(), areaGroup.getValueTimePoint());
        bootstrapTableVo.setTotal((long) detailList.size());
        bootstrapTableVo.setRows(detailList);
        return bootstrapTableVo;
    }


    @RequestMapping(value = "/saveResult", name = "保存数据", method = RequestMethod.POST)
    public HttpResult saveResult(String formData) {
        try {
            MdBaseLandPrice mdBaseLandPrice = JSON.parseObject(formData, MdBaseLandPrice.class);
            mdBaseLandPriceService.saveMdBaseLandPrice(mdBaseLandPrice);
            return HttpResult.newCorrectResult(mdBaseLandPrice);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }


    @RequestMapping(value = "/getLevelDetailId", name = "获取土地级别id", method = RequestMethod.GET)
    public HttpResult getLevelDetailId(Integer judgeObjectId) {
        try {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            return HttpResult.newCorrectResult(landStateByEstateId.getLandLevel());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @PostMapping(value = "/calculationNumeric", name = "后台自动计算")
    public HttpResult calculationNumeric(String fomData, BigDecimal dateAmend, BigDecimal volumeFractionAmend) {
        try {
            MdBaseLandPrice target = JSONObject.parseObject(fomData, MdBaseLandPrice.class);
            mdBaseLandPriceService.calculationNumeric(target, dateAmend, volumeFractionAmend);
            return HttpResult.newCorrectResult(target);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("请检查输入的数据");
        }
    }

}
