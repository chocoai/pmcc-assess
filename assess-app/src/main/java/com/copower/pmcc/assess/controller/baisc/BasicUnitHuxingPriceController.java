package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPrice;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingPriceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value = "/basicUnitHuxingPrice")
@Controller
public class BasicUnitHuxingPriceController {
    @Autowired
    private BasicUnitHuxingPriceService basicUnitHuxingPriceService;


    @ResponseBody
    @RequestMapping(value = "/getBasicUnitHuxingPriceById", name = "获取数据", method = {RequestMethod.POST})
    public HttpResult getBasicUnitHuxingPriceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitHuxingPriceService.getBasicUnitHuxingPriceById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnitHuxingPrice", name = "新增或者修改", method = RequestMethod.POST)
    public HttpResult saveAndUpdateBasicUnitHuxingPrice(BasicUnitHuxingPrice basicUnitHuxing) {
        try {
            return HttpResult.newCorrectResult(basicUnitHuxingPriceService.saveAndUpdateBasicUnitHuxingPrice(basicUnitHuxing, true));
        } catch (Exception e) {
            return HttpResult.newErrorResult("新增或者修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnitHuxingPrice", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitHuxingPrice(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitHuxingPriceService.deleteBasicUnitHuxingPrice(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUnitHuxingPriceList", name = "获取选择户型数据列表")
    public BootstrapTableVo getUnitHuxingPriceList(Integer unitHuxingId) {
        BasicUnitHuxingPrice basicUnitHuxingPrice = new BasicUnitHuxingPrice();
        basicUnitHuxingPrice.setHuxingId(unitHuxingId);
        return basicUnitHuxingPriceService.getBasicUnitHuxingPriceListVos(basicUnitHuxingPrice);

    }

}
