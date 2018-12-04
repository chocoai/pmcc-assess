package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPosition;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.data.DataPositionService;
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
 * @Date: 2018/12/4 10:39
 * @Description:方位维护
 */
@RequestMapping(value = "/dataPosition")
@Controller
public class DataPositionController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataPositionService dataPositionService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataPositionView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPositionById", method = {RequestMethod.GET}, name = "获取方位维护")
    public HttpResult getDataPositionById(Integer id) {
        DataPosition dataPosition = null;
        try {
            if (id != null) {
                dataPosition = dataPositionService.getDataPositionById(id);
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataPosition);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPositionList", method = {RequestMethod.GET}, name = "获取方位维护列表")
    public BootstrapTableVo getDataPositionList(String province, String city, String district, String name) {
        DataPosition dataPosition = new DataPosition();
        BootstrapTableVo vo = null;
        try {
            vo = dataPositionService.getDataPositionListVos(province, city, district,name);
        } catch (Exception e1) {
            log.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataPositionById", method = {RequestMethod.POST}, name = "删除方位维护")
    public HttpResult deleteDataPositionById(Integer id) {
        try {
            if (id != null) {
                DataPosition dataPosition = new DataPosition();
                dataPosition.setId(id);
                dataPositionService.removeDataPosition(dataPosition);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataPosition", method = {RequestMethod.POST}, name = "更新方位维护")
    public HttpResult saveAndUpdateDataPosition(DataPosition dataPosition) {
        try {
            dataPositionService.saveAndUpdateDataPosition(dataPosition);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            log.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPositionListByArea", method = {RequestMethod.GET}, name = "获取by区域")
    public HttpResult getDataPositionListByArea(String province, String city, String district,String name) {
        try {
            return HttpResult.newCorrectResult(dataPositionService.dataPositionVoList(province, city, district,name));
        } catch (Exception e) {
            log.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取版块信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dataPositionList", method = {RequestMethod.GET}, name = "获取 list")
    public HttpResult dataPositionList(String province, String city, String district) {
        try {
            DataPosition dataPosition = new DataPosition();
            if (org.apache.commons.lang.StringUtils.isNotBlank(province)) {
                dataPosition.setProvince(province);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(city)) {
                dataPosition.setCity(city);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(district)) {
                dataPosition.setDistrict(district);
            }
            return HttpResult.newCorrectResult(dataPositionService.dataPositionVos(dataPosition));
        } catch (Exception e) {
            log.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取版块信息 list exception");
        }
    }
    
}
