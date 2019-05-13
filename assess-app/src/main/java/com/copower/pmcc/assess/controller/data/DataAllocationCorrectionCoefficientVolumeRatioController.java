package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
import com.copower.pmcc.assess.dto.output.data.DataAllocationCorrectionCoefficientVolumeRatioVo;
import com.copower.pmcc.assess.service.data.DataAllocationCorrectionCoefficientVolumeRatioService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: zch
 * @date: 2019/5/6 14:38
 * @description:容积率修正系数配置
 */
@RequestMapping(value = "/dataAllocationCorrectionCoefficientVolumeRatio")
@RestController
public class DataAllocationCorrectionCoefficientVolumeRatioController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioService coefficientVolumeRatioService;

    @RequestMapping(value = "/view")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataAllocationCorrectionCoefficientVolumeRatioView");
        return modelAndView;
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = " 列表")
    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatio oo) {
        BootstrapTableVo vo = coefficientVolumeRatioService.getBootstrapTableVo(oo);
        return vo;
    }

    @GetMapping(value = "/get/{id}",name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
        DataAllocationCorrectionCoefficientVolumeRatioVo dataAllocationCorrectionCoefficientVolumeRatioVo = null;
        try {
            dataAllocationCorrectionCoefficientVolumeRatioVo = coefficientVolumeRatioService.getDataAllocationCorrectionCoefficientVolumeRatioVo(coefficientVolumeRatioService.getDataAllocationCorrectionCoefficientVolumeRatioById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(dataAllocationCorrectionCoefficientVolumeRatioVo);
    }


    @PostMapping(value = "/save",name = "restful post")
    public HttpResult save(String formData){
        try {
            DataAllocationCorrectionCoefficientVolumeRatio coefficientVolumeRatio = JSON.parseObject(formData,DataAllocationCorrectionCoefficientVolumeRatio.class);
            coefficientVolumeRatioService.saveDataAllocationCorrectionCoefficientVolumeRatio(coefficientVolumeRatio);
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
                DataAllocationCorrectionCoefficientVolumeRatio correctionCoefficientVolumeRatio = new DataAllocationCorrectionCoefficientVolumeRatio();
                correctionCoefficientVolumeRatio.setId(id);
                coefficientVolumeRatioService.deleteDataAllocationCorrectionCoefficientVolumeRatio(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    
}
