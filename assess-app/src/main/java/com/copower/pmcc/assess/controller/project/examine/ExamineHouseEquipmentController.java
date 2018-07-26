package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseEquipmentService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/25 15:12
 * @Description:设备包含（空调、新风、供暖）
 */
@RequestMapping(value = "/examineHouseEquipment")
@Controller
public class ExamineHouseEquipmentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseEquipmentService examineHouseEquipmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Deprecated
    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/houseAirConditioner" ; //空调情况
        String view = "/task/survey/examine/residence/apply/houseHeating" ; //供暖情况
//        String view = "/task/survey/examine/residence/apply/houseNewWind" ; //新风情况
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseEquipmentById",method = {RequestMethod.GET},name = "获取设备包含（空调、新风、供暖）")
    public HttpResult getById(Integer id) {
        ExamineHouseEquipment examineHouseEquipment = null;
        try {
            if (id!=null){
                examineHouseEquipment = examineHouseEquipmentService.getExamineHouseEquipmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseEquipment);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseEquipmentList",method = {RequestMethod.GET},name = "设备包含（空调、新风、供暖）列表")
    public BootstrapTableVo getExamineHouseEquipmentList(Integer examineType, Integer declareId, String type) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseEquipment examineHouseEquipment = new ExamineHouseEquipment();
            if (!ObjectUtils.isEmpty(examineType)){
                examineHouseEquipment.setExamineType(examineType);
            }
            if (declareId!=null && declareId.equals(0)){
                examineHouseEquipment.setDeclareId(declareId);
            }
            if (!StringUtils.isEmpty(type)){
                examineHouseEquipment.setType(type);
            }
            vo = examineHouseEquipmentService.getExamineHouseEquipmentLists(examineHouseEquipment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseEquipmentById",method = {RequestMethod.POST},name = "删除设备包含（空调、新风、供暖）")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseEquipmentService.deleteExamineHouseEquipment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseEquipment",method = {RequestMethod.POST},name = "更新设备包含（空调、新风、供暖）")
    public HttpResult save(ExamineHouseEquipment examineHouseEquipment){
        try {
            if (examineHouseEquipment.getId()==null || examineHouseEquipment.getId().equals(0)){
                examineHouseEquipmentService.addExamineHouseEquipment(examineHouseEquipment);
            }else {
                examineHouseEquipmentService.updateExamineHouseEquipment(examineHouseEquipment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/examineHouseEquipment_grade",method = {RequestMethod.GET},name = "设备包含（空调、新风、供暖） 类型")
    public HttpResult examineHouseEquipment_grade(String type) {
        try {
            List<BaseDataDic> baseDataDic = null;
            if (!StringUtils.isEmpty(type)){
                ExamineHouseEquipmentTypeEnum typeEnum = ExamineHouseEquipmentTypeEnum.getEnumByName(ExamineHouseEquipmentTypeEnum.getNameByKey(type));
                baseDataDic = examineHouseEquipmentService.examineExamineHouseEquipment_grade(typeEnum);
            }
            if (!ObjectUtils.isEmpty(baseDataDic)){
                return HttpResult.newCorrectResult(baseDataDic);
            }else {
                return HttpResult.newErrorResult("没有获取到数据!");
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_equipment_price_range",method = {RequestMethod.GET},name = "设备包含（空调、新风、供暖） 设备价格区间")
    public HttpResult examine_house_equipment_price_range() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_EQUIPMENT_PRICE_RANGE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
