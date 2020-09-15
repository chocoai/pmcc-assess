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
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
            logger.error(e.getMessage(),e);
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
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @RequestMapping(value = "/getLandAcquisitionBhou", name = "获取土地取得费", method = RequestMethod.GET)
    public HttpResult getLandAcquisitionBhou(Integer masterId) {
        try {
            BigDecimal landAcquisitionBhou = costApproachService.getLandAcquisitionBhou(masterId);
            return HttpResult.newCorrectResult(landAcquisitionBhou);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("获取失败");
        }
    }
}
