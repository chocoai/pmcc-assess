package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensional;
import com.copower.pmcc.assess.service.data.DataImgTwoDimensionalService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/11/28 13:58
 * @Description:
 */
@RequestMapping(value = "/dataImgTwoDimensional")
@Controller
public class DataImgTwoDimensionalController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataImgTwoDimensionalService dataImgTwoDimensionalService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/view", name = "转到view页面 ", method = {RequestMethod.GET})
    public ModelAndView view() {
        String view = "/data/imgTwoDimensionalView/view";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/index", name = "转到index页面 save and update ", method = {RequestMethod.GET})
    public ModelAndView index(Integer id) {
        String view = "/data/imgTwoDimensionalView/index";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null){
            DataImgTwoDimensional dataImgTwoDimensional = dataImgTwoDimensionalService.getDataImgTwoDimensionalById(id);
            if (dataImgTwoDimensional != null){
                modelAndView.addObject("dataImgTwoDimensional",dataImgTwoDimensional);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "转到详情页面 ", method = {RequestMethod.GET})
    public ModelAndView detail() {
        String view = "/data/imgTwoDimensionalView/detail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataImgTwoDimensionalById", method = {RequestMethod.GET}, name = "获取户型在图片上的二维坐标信息")
    public HttpResult getDataImgTwoDimensionalById(Integer id) {
        DataImgTwoDimensional dataImgTwoDimensional = null;
        try {
            if (id != null) {
                dataImgTwoDimensional = dataImgTwoDimensionalService.getDataImgTwoDimensionalById(id);
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataImgTwoDimensional);
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "获取户型在图片上的二维坐标信息列表")
    public BootstrapTableVo getDataImgTwoDimensionalList(DataImgTwoDimensional dataImgTwoDimensional) {
        BootstrapTableVo vo = null;
        if (dataImgTwoDimensional == null){
            dataImgTwoDimensional = new DataImgTwoDimensional();
        }
        try {
            vo = dataImgTwoDimensionalService.getBootstrapTableVo(dataImgTwoDimensional);
        } catch (Exception e1) {
            log.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataImgTwoDimensionalById", method = {RequestMethod.POST}, name = "删除户型在图片上的二维坐标信息")
    public HttpResult deleteDataImgTwoDimensionalById(Integer id) {
        try {
            if (id != null) {
                DataImgTwoDimensional dataImgTwoDimensional = new DataImgTwoDimensional();
                dataImgTwoDimensional.setId(id);
                dataImgTwoDimensionalService.removeDataImgTwoDimensional(dataImgTwoDimensional);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataImgTwoDimensional", method = {RequestMethod.POST}, name = "更新户型在图片上的二维坐标信息")
    public HttpResult saveAndUpdateDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional) {
        try {
            dataImgTwoDimensionalService.saveAndUpdateDataImgTwoDimensional(dataImgTwoDimensional);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            log.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
