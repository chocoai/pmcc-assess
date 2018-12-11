package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:38
 * @Description:土地级别维护
 */
@RequestMapping(value = "/dataLandLevel")
@Controller
public class DataLandLevelController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLandLevelView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLandLevelById", method = {RequestMethod.GET}, name = "获取土地级别维护")
    public HttpResult getById(Integer id) {
        DataLandLevel dataLandLevel = null;
        try {
            if (id != null) {
                dataLandLevel = dataLandLevelService.getDataLandLevelById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataLandLevel);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLandLevelList", method = {RequestMethod.GET}, name = "获取土地级别维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(String province, String city, String district) {
        DataLandLevel dataLandLevel = new DataLandLevel();
        if (!StringUtils.isEmpty(province)) {
            dataLandLevel.setProvince(province);
        }
        if (!StringUtils.isEmpty(city)) {
            dataLandLevel.setProvince(city);
        }
        if (!StringUtils.isEmpty(district)) {
            dataLandLevel.setProvince(district);
        }
        BootstrapTableVo vo = null;
        try {
            vo = dataLandLevelService.getDataLandLevelListVos(dataLandLevel);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataLandLevelById", method = {RequestMethod.POST}, name = "删除土地级别维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                int count = dataLandLevelDetailService.getCountByLandLevelId(id);
                if (count > 0) {
                    return HttpResult.newErrorResult("请先删除明细中的数据");
                }
                DataLandLevel dataLandLevel = new DataLandLevel();
                dataLandLevel.setId(id);
                dataLandLevelService.removeDataLandLevel(dataLandLevel);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataLandLevel", method = {RequestMethod.POST}, name = "更新土地级别维护")
    public HttpResult saveAndUpdate(String formData) {
        try {
            DataLandLevel dataLandLevel = JSON.parseObject(formData, DataLandLevel.class);
            dataLandLevelService.saveAndUpdateDataLandLevel(dataLandLevel);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLandLevelDetailList", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailList(Integer landLevelId) {
        return dataLandLevelDetailService.getDataLandLevelDetailList(landLevelId);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLandLevelDetailListByArea", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailListByArea(String province, String city, String district) {
        return dataLandLevelDetailService.getDataLandLevelDetailListByArea(province, city, district);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataLandLevelDetail", method = {RequestMethod.POST}, name = "保存土地级别信息")
    public HttpResult saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        try {
            dataLandLevelDetailService.saveAndUpdateDataLandLevelDetail(dataLandLevelDetail);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeDataLandLevelDetail", method = {RequestMethod.POST}, name = "删除土地级别信息")
    public HttpResult removeDataLandLevelDetail(Integer id) {
        try {
            dataLandLevelDetailService.removeDataLandLevelDetail(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("删除异常");
        }
    }


}
