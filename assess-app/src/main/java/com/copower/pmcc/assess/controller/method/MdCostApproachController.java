package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachItemDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachTaxesDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: HUHAO
 * @Date: 2018/9/7 10:00
 * @Description:成本逼近法
 */
@RequestMapping(value = "/costApproach")
@RestController
public class MdCostApproachController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdCostApproachService costApproachService;
    @Autowired
    private MdCostApproachItemDao costApproachItemDao;
    @Autowired
    private MdCostApproachTaxesDao costApproachTaxesDao;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private MdCostApproachService mdCostApproachService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", name = "成本逼近法")
    public ModelAndView index(Integer dataId, Integer judgeObjectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/maketCostApproachIndex");
        MdCostApproach mdCostApproach = null;
        if (dataId != null && dataId != 0) {
            mdCostApproach = costApproachService.getDataById(dataId);
        } else {
            mdCostApproach = new MdCostApproach();
            mdCostApproach.setJudgeObjectId(judgeObjectId);
            mdCostApproachService.saveMdCostApproach(mdCostApproach);
        }
        mdCostApproachService.initTaxeItem(mdCostApproach);
        modelAndView.addObject("master", mdCostApproach);
        modelAndView.addObject("apply", "apply");
        mdCostApproachService.setViewParam(mdCostApproach, judgeObjectId, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "成本逼近法")
    public ModelAndView detail(Integer dataId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/maketCostApproachDetail");
        MdCostApproach mdCostApproach = costApproachService.getDataById(dataId);
        List<MdCostApproachTaxes> list = mdCostApproachService.getMdCostApproachTaxesListByMasterId(mdCostApproach.getId());
        modelAndView.addObject("taxesVos", list);
        modelAndView.addObject("master", costApproachService.getMdCostApproachVo(mdCostApproach));
        mdCostApproachService.setViewParam(mdCostApproach, mdCostApproach.getJudgeObjectId(), modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/saveResult", name = "保存数据", method = RequestMethod.POST)
    public HttpResult saveResult(String formData){
        try {
            MdCostApproach mdCostApproach = costApproachService.applyCommit(formData);
            return HttpResult.newCorrectResult(200,mdCostApproach);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }


    @RequestMapping(value = "/getMdCostApproachItemList", name = "调查价格列表", method = RequestMethod.GET)
    public BootstrapTableVo getMdCostApproachItemList(Integer masterId) {
        return costApproachService.getMdCostApproachItemList(masterId);
    }

    @RequestMapping(value = "/deleteCostApproachItem", name = "删除调查明细", method = RequestMethod.POST)
    public HttpResult deleteCostApproachItem(@RequestParam(value = "id") Integer id) {
        try {
            costApproachItemDao.deleteCostApproachItem(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/addCostApproachItem", method = {RequestMethod.POST}, name = "保存商品调查价格")
    public HttpResult addCostApproachItem(MdCostApproachItem mdCostApproachItem) {
        try {
            if (mdCostApproachItem.getId() == null) {
                mdCostApproachItem.setCreator(commonService.thisUserAccount());
                costApproachItemDao.addCostApproachItem(mdCostApproachItem);
            } else {
                costApproachItemDao.updateCostApproachItem(mdCostApproachItem);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/getCostApproachItemById", name = "获取一条调查明细", method = RequestMethod.GET)
    public HttpResult getCostApproachItemById(Integer id) {
        try {
            MdCostApproachItem costApproachItem = costApproachItemDao.getCostApproachItemById(id);
            return HttpResult.newCorrectResult(costApproachItem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @RequestMapping(value = "/getMdCostApproachTaxesList", name = "税费列表", method = RequestMethod.GET)
    public BootstrapTableVo getMdCostApproachTaxesList(Integer masterId) {
        return costApproachService.getMdCostApproachTaxesList(masterId);
    }

    @RequestMapping(value = "/deleteCostApproachTaxes", name = "删除一条税费", method = RequestMethod.POST)
    public HttpResult deleteCostApproachTaxes(@RequestParam(value = "id") Integer id) {
        try {
            costApproachTaxesDao.deleteCostApproachTaxes(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @RequestMapping(value = "/getThisPrice", method = {RequestMethod.POST}, name = "计算税费")
    public HttpResult calculatePrice(String formData, String farmlandArea, String ploughArea, String populationNumber) {
        try {
            MdCostApproachTaxes costApproachTaxes = costApproachService.calculatePrice(formData, farmlandArea, ploughArea, populationNumber);
            return HttpResult.newCorrectResult(costApproachTaxes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/addCostApproachTaxes", method = {RequestMethod.POST}, name = "保存税费")
    public HttpResult addCostApproachTaxes(String formData) {
        try {
            MdCostApproachTaxes costApproachTaxes = costApproachService.saveCostApproachTaxes(formData);
            return HttpResult.newCorrectResult(costApproachTaxes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getCostApproachTaxesById", name = "获取一条税费", method = RequestMethod.GET)
    public HttpResult getCostApproachTaxesById(Integer id) {
        try {
            MdCostApproachTaxes costApproachTaxes = costApproachTaxesDao.getCostApproachTaxesById(id);
            return HttpResult.newCorrectResult(costApproachTaxes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @RequestMapping(value = "/getLandAcquisitionBhou", name = "获取土地取得费", method = RequestMethod.GET)
    public HttpResult getLandAcquisitionBhou(Integer masterId) {
        try {
            BigDecimal landAcquisitionBhou = costApproachService.getLandAcquisitionBhou(masterId);
            return HttpResult.newCorrectResult(landAcquisitionBhou);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @PostMapping(value = "/calculationNumeric", name = "后台自动计算")
    public HttpResult calculationNumeric(String fomData) {
        try {
            MdCostApproach target = JSONObject.parseObject(fomData, MdCostApproach.class);
            costApproachService.calculationNumeric(target);
            return HttpResult.newCorrectResult(200, target);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("请检查输入的数据");
        }
    }


}
