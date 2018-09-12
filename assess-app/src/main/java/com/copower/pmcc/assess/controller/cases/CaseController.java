package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/case",name = "案例 基础")
public class CaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/case/baseView" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        modelAndView.addObject("dataBlocks",dataBlockService.dataBlockVos(new DataBlock()));//基础板块信息
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(name = "版块信息自动补全",value = "/autoCompleteBlock")
    public HttpResult autoCompleteBlock(String term,Integer maxRows){
        List<KeyValueDto> keyValueDtos= Lists.newArrayList();
        KeyValueDto keyValueDto=new KeyValueDto();
        keyValueDto.setKey("1");
        keyValueDto.setValue("张三");
        keyValueDtos.add(keyValueDto);
        keyValueDto.setKey("2");
        keyValueDto.setValue("李四");
        keyValueDtos.add(keyValueDto);
        return HttpResult.newCorrectResult(keyValueDtos);
    }
}
