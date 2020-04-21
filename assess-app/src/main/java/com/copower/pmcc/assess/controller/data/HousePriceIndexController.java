package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexVo;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/housePriceIndex", name = "指数")
@RestController
public class HousePriceIndexController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataHousePriceIndexService housePriceIndexService;

    @RequestMapping(value = "/view")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/housePriceIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "房价指数 列表")
    public BootstrapTableVo getBootstrapTableVo(DataHousePriceIndex dataHousePriceIndex) {
        BootstrapTableVo vo = housePriceIndexService.getDataHousePriceIndexListVos(dataHousePriceIndex);
        return vo;
    }

    @GetMapping(value = "/get/{id}", name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
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
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(String formData) {
        try {
            DataHousePriceIndex dataHousePriceIndex = JSON.parseObject(formData, DataHousePriceIndex.class);
            housePriceIndexService.saveAndUpdateDataHousePriceIndex(dataHousePriceIndex);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @DeleteMapping(value = "/delete/{id}", name = "restful delete")
    public HttpResult delete(@PathVariable Integer id) {
        try {
            if (id != null && id != 0) {
                DataHousePriceIndex dataHousePriceIndex = new DataHousePriceIndex();
                dataHousePriceIndex.setId(id);
                housePriceIndexService.deleteDataHousePriceIndex(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
