package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWater;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseWaterService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/26 08:50
 * @Description:供排水情况
 */
@RequestMapping(value = "/examineHouseWater")
@Controller
public class ExamineHouseWaterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineHouseWaterService examineHouseWaterService;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/houseWater" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseWaterById",method = {RequestMethod.GET},name = "获取供排水情况")
    public HttpResult getById(Integer id) {
        ExamineHouseWater examineHouseWater = null;
        try {
            if (id!=null){
                examineHouseWater = examineHouseWaterService.getExamineHouseWaterById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseWater);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseWaterList",method = {RequestMethod.GET},name = "供排水情况列表")
    public BootstrapTableVo getExamineHouseWaterList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseWater examineHouseWater = new ExamineHouseWater();
            if (!ObjectUtils.isEmpty(examineType)){
                examineHouseWater.setExamineType(examineType);
            }
            if (declareId!=null ){
                examineHouseWater.setDeclareId(declareId);
            }
            vo = examineHouseWaterService.getExamineHouseWaterLists(examineHouseWater);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseWaterById",method = {RequestMethod.POST},name = "删除供排水情况")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseWaterService.deleteExamineHouseWater(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseWater",method = {RequestMethod.POST},name = "更新供排水情况")
    public HttpResult save(ExamineHouseWater examineHouseWater){
        try {
            if (examineHouseWater.getId()==null || examineHouseWater.getId().equals(0)){
                examineHouseWaterService.addExamineHouseWater(examineHouseWater);
            }else {
                examineHouseWaterService.updateExamineHouseWater(examineHouseWater);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_supply_erection_method",method = {RequestMethod.GET},name = "供水管架设方式")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_SUPPLY_ERECTION_METHOD);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_pretreated_water",method = {RequestMethod.GET},name = "前置净水")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_PRETREATED_WATER);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_water_drainage_circuit",method = {RequestMethod.GET},name = "排水回路")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_WATER_DRAINAGE_CIRCUIT);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_purification_equipment_price",method = {RequestMethod.GET},name = "前置净水设备价格区间")
    public HttpResult examine_house_purification_equipment_price() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_PURIFICATION_EQUIPMENT_PRICE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_water_intake_equipment_price",method = {RequestMethod.GET},name = "取水设备价格区间")
    public HttpResult examine_house_water_intake_equipment_price() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_WATER_INTAKE_EQUIPMENT_PRICE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
