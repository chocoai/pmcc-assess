package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachItemDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachTaxesDao;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItem;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @Auther: HUHAO
 * @Date: 2018/9/7 10:00
 * @Description:配置
 */
@RequestMapping(value = "/costApproach")
@Controller
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


    @ResponseBody
    @RequestMapping(value = "/getMdCostApproachItemList", name = "调查价格列表", method = RequestMethod.GET)
    public BootstrapTableVo getMdCostApproachItemList(Integer masterId) {
        return costApproachService.getMdCostApproachItemList(masterId);
    }

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @RequestMapping(value = "/getCostApproachItemById", name = "获取一条调查明细", method = RequestMethod.GET)
    public HttpResult getCostApproachItemById(Integer id) {
        try {
            MdCostApproachItem costApproachItem = costApproachItemDao.getCostApproachItemById(id);
            return HttpResult.newCorrectResult(costApproachItem);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getMdCostApproachTaxesList", name = "税费列表", method = RequestMethod.GET)
    public BootstrapTableVo getMdCostApproachTaxesList(Integer masterId) {
        return costApproachService.getMdCostApproachTaxesList(masterId);
    }

    @ResponseBody
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


    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @RequestMapping(value = "/getCostApproachTaxesById", name = "获取一条税费", method = RequestMethod.GET)
    public HttpResult getCostApproachTaxesById(Integer id) {
        try {
            MdCostApproachTaxes costApproachTaxes = costApproachTaxesDao.getCostApproachTaxesById(id);
            return HttpResult.newCorrectResult(costApproachTaxes);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getLandAcquisitionBhou", name = "获取土地取得费", method = RequestMethod.GET)
    public HttpResult getLandAcquisitionBhou(Integer masterId) {
        try {
            BigDecimal landAcquisitionBhou = costApproachService.getLandAcquisitionBhou(masterId);
            return HttpResult.newCorrectResult(landAcquisitionBhou);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }
}
