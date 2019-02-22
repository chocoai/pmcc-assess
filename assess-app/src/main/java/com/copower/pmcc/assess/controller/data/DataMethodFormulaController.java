package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormula;
import com.copower.pmcc.assess.dto.output.data.DataMethodFormulaVo;
import com.copower.pmcc.assess.service.AdRpcQualificationsAppService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/dataMethodFormula")
public class DataMethodFormulaController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataMethodFormulaService dataMethodFormulaService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataMethodFormula";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> methods = baseDataDicService.getCacheDataDicList("data.evaluation.method");
        modelAndView.addObject("methods",methods);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataMethodFormulaById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataMethodFormulaVo dataMethodFormulaVo = null;
        try {
            if (id != null) {
                dataMethodFormulaVo = dataMethodFormulaService.getByDataMethodFormulaId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataMethodFormulaVo);
    }

    @RequestMapping(value = "/getDataMethodFormulaList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer type) {
        BootstrapTableVo vo = null;
        try {
            vo = dataMethodFormulaService.getListVos(type);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteDataMethodFormulaById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataMethodFormulaService.deleteDataMethodFormula(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataMethodFormula", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataMethodFormula dataMethodFormula) {
        try {
            if (dataMethodFormula.getId() == null || dataMethodFormula.getId().equals(0)) {
                dataMethodFormulaService.addDataMethodFormulaReturnId(dataMethodFormula);
            } else {
                dataMethodFormulaService.updateDataMethodFormula(dataMethodFormula);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
