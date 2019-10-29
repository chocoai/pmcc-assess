package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:38
 * @Description:土地级别维护
 */
@RequestMapping(value = "/dataLandLevel")
@RestController
public class DataLandLevelController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLandLevelView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataLandLevelListVos", method = {RequestMethod.GET}, name = "获取土地级别维护列表")
    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel) {
        return dataLandLevelService.getDataLandLevelListVos(dataLandLevel);
    }

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
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataLandLevel", method = {RequestMethod.POST}, name = "更新土地级别维护")
    public HttpResult saveAndUpdate(String formData) {
        try {
            DataLandLevel dataLandLevel = JSON.parseObject(formData, DataLandLevel.class);
            dataLandLevelService.saveAndUpdateDataLandLevel(dataLandLevel);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @RequestMapping(value = "/getDataLandLevelDetailList", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailList(Integer landLevelId) {
        return dataLandLevelDetailService.getDataLandLevelDetailList(landLevelId);
    }

    @RequestMapping(value = "/getDataLandLevelDetailListByArea", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailListByArea(String province, String city, String district) {
        return dataLandLevelDetailService.getDataLandLevelDetailListByArea(province, city, district);
    }

    @RequestMapping(value = "/saveAndUpdateDataLandLevelDetail", method = {RequestMethod.POST}, name = "保存土地级别信息")
    public HttpResult saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        try {
            dataLandLevelDetailService.saveAndUpdateDataLandLevelDetail(dataLandLevelDetail);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @GetMapping(value = "/submitDataLandLevel")
    public HttpResult submit(String formData){
        try {
            DataLandLevel dataLandLevel = JSONObject.parseObject(formData,DataLandLevel.class) ;
            dataLandLevelService.submit(dataLandLevel);
            return HttpResult.newCorrectResult(200,dataLandLevel);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/removeDataLandLevelDetail", method = {RequestMethod.POST}, name = "删除土地级别信息")
    public HttpResult removeDataLandLevelDetail(Integer id) {
        try {
            dataLandLevelDetailService.removeDataLandLevelDetail(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("删除异常");
        }
    }


}
