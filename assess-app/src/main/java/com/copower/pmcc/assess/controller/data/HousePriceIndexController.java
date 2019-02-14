package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dto.input.data.DataHousePriceIndexDto;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/housePriceIndex", name = "房价指数")
@Controller
public class HousePriceIndexController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataHousePriceIndexService housePriceIndexService;

    @RequestMapping(value = "/view")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/housePriceIndex");
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}, name = "房价指数 列表")
    public BootstrapTableVo list(String startTime, String endTime, String province, String city, String district) {
        BootstrapTableVo vo = null;
        try {
            vo = housePriceIndexService.getDataHousePriceIndexListVos(DateUtils.convertDate(startTime), DateUtils.convertDate(endTime), province, city, district);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return vo;
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public HttpResult get(Integer id) {
        DataHousePriceIndexVo dataHousePriceIndexVo = null;
        try {
            dataHousePriceIndexVo = housePriceIndexService.getDataHousePriceIndexVo(housePriceIndexService.getDataHousePriceIndexById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(dataHousePriceIndexVo);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdate", method = {RequestMethod.POST}, name = "房价指数 更新")
    public HttpResult add(String formData) {
        DataHousePriceIndexDto housePriceIndexDto = JSON.parseObject(formData, DataHousePriceIndexDto.class);
        try {
            housePriceIndexService.saveAndUpdateDataHousePriceIndex(housePriceIndexDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.POST}, name = "房价指数 删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                if (id.intValue() != 0) {
                    DataHousePriceIndex dataHousePriceIndex = new DataHousePriceIndex();
                    dataHousePriceIndex.setId(id);
                    housePriceIndexService.removeDataHousePriceIndex(dataHousePriceIndex);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
