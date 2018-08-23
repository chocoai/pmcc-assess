package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineUnitDecorateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/24 17:08
 * @Description:室内公共装修
 */
@RequestMapping(value = "/examineUnitDecorate")
@Controller
public class ExamineUnitDecorateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineUnitDecorateService examineUnitDecorateService;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/unitDecorate" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitDecorateById",method = {RequestMethod.GET},name = "获取室内公共装修")
    public HttpResult getById(Integer id) {
        ExamineUnitDecorate examineUnitDecorate = null;
        try {
            if (id!=null){
                examineUnitDecorate = examineUnitDecorateService.getExamineUnitDecorateById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineUnitDecorate);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitDecorateList",method = {RequestMethod.GET},name = "室内公共装修列表")
    public BootstrapTableVo getExamineUnitDecorateList(Integer examineType, Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineUnitDecorate examineUnitDecorate = new ExamineUnitDecorate();
            if (!ObjectUtils.isEmpty(examineType)){
                examineUnitDecorate.setExamineType(examineType);
            }
            if (declareId!=null ){
                examineUnitDecorate.setDeclareId(declareId);
            }
            if (planDetailsId!=null ){
                examineUnitDecorate.setDeclareId(planDetailsId);
            }
            vo = examineUnitDecorateService.getExamineUnitDecorateLists(examineUnitDecorate);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineUnitDecorateById",method = {RequestMethod.POST},name = "删除室内公共装修")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineUnitDecorateService.deleteExamineUnitDecorate(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineUnitDecorate",method = {RequestMethod.POST},name = "更新室内公共装修")
    public HttpResult save(ExamineUnitDecorate examineUnitDecorate){
        try {
            if (examineUnitDecorate.getId()==null || examineUnitDecorate.getId().equals(0)){
                examineUnitDecorateService.addExamineUnitDecorate(examineUnitDecorate);
            }else {
                examineUnitDecorateService.updateExamineUnitDecorate(examineUnitDecorate);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_decoration_part",method = {RequestMethod.GET},name = "装修部位")
    public HttpResult examine_building_decoration_part() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_decorating_material",method = {RequestMethod.GET},name = "装修材料")
    public HttpResult examine_building_decorating_material() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_material_price",method = {RequestMethod.GET},name = "材料价格区间")
    public HttpResult examine_building_material_price() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_construction_technology",method = {RequestMethod.GET},name = "施工工艺")
    public HttpResult examine_building_construction_technology() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
