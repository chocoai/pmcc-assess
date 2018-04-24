package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataDeclareForm;
import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dto.input.data.HousePriceIndexDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataDeclareFormService;
import com.copower.pmcc.assess.service.data.HousePriceIndexService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping(value = "/dataDeclareForm")
@Controller
public class DataDeclareFormController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private DataDeclareFormService dataDeclareFormService;

    @RequestMapping(value = "/view")
    public ModelAndView index() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/dataDeclareForm");
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ASSESS_CLASS);
        modelAndView.addObject("assessClassList",baseDataDicList);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BootstrapTableVo list(Integer assessClass, String name) {
        return dataDeclareFormService.getList(assessClass, name);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult save(DataDeclareForm dataDeclareFormDto) {
        try {
            dataDeclareFormService.saveData(dataDeclareFormDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            dataDeclareFormService.deleteData(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
