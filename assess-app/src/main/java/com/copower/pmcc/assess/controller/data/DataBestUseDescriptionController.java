package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBestUseDescription;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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

@RequestMapping(value = "/bestUse")
@Controller
public class DataBestUseDescriptionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;

    @RequestMapping(value = "/Index", name = "最佳利用方式查看 ")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataBestUseDescription");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBestUseDescription", name = "取得最佳利用方式", method = RequestMethod.GET)
    public BootstrapTableVo getBestUseDescription(@RequestParam(value = "name") String name) {
        return dataBestUseDescriptionService.getBestUseListVo(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addBestUseDescription", name = "新增最佳利用方式", method = RequestMethod.POST)
    public HttpResult addBestUseDescription(DataBestUseDescription dataBestUseDescription) {
        try {
            if (dataBestUseDescription.getId() != null && dataBestUseDescription.getId() != 0) {
                dataBestUseDescriptionService.editDataBestUseDescription(dataBestUseDescription);
            } else {
                if(dataBestUseDescriptionService.addDataBestUseDescription(dataBestUseDescription) == false){
                    return HttpResult.newErrorResult("添加内容已存在!");
                }
            }
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBestUseDescription", name = "删除最佳利用方式", method = RequestMethod.POST)
    public HttpResult deleteBestUseDescription(@RequestParam(value = "id") Integer id) {
        dataBestUseDescriptionService.deleteDataBestUseDescription(id);
        return HttpResult.newCorrectResult();
    }

}
