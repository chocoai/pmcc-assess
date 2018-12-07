package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupply;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineEstateSupplyService;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/20 09:19
 * @Description:
 */
@RequestMapping(value = "/examineEstateSupply",name = "供应信息")
@Controller
public class ExamineEstateSupplyController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ExamineEstateSupplyService examineEstateSupplyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
////        String view = "/task/survey/examine/residence/apply/estateSupplyGas" ;//供气
////        String view = "/task/survey/examine/residence/apply/estateSupplyPower" ;//供电
//        String view = "/task/survey/examine/residence/apply/estateSupplyWater" ;//供水
////        String view = "/task/survey/examine/residence/apply/estateSupplyHeating" ;//供热
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateSupplyById",method = {RequestMethod.GET},name = "获取供应信息")
    public HttpResult getById(Integer id) {
        ExamineEstateSupply examineEstateSupply = null;
        try {
            if (id!=null){
                examineEstateSupply = examineEstateSupplyService.getExamineEstateSupplyById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineEstateSupply);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateSupplyList",method = {RequestMethod.GET},name = "供应信息列表")
    public BootstrapTableVo getExamineEstateSupplyList(String name, Integer examineType,String type,Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineEstateSupply examineEstateSupply = new ExamineEstateSupply();
            if (!StringUtils.isEmpty(name)){
                examineEstateSupply.setName(name);
            }
            if (!ObjectUtils.isEmpty(examineType)){
                examineEstateSupply.setExamineType(examineType);
            }
            if (!StringUtils.isEmpty(type)){
                examineEstateSupply.setType(type);
            }
            if (declareId!=null ){
                examineEstateSupply.setDeclareId(declareId);
            }
            if (planDetailsId!=null ){
                examineEstateSupply.setPlanDetailsId(planDetailsId);
            }
            vo = examineEstateSupplyService.getExamineEstateNetworkList(examineEstateSupply);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineEstateSupplyById",method = {RequestMethod.POST},name = "删除供应信息")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineEstateSupplyService.deleteExamineEstateSupply(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineEstateSupply",method = {RequestMethod.POST},name = "更新供应信息")
    public HttpResult save(ExamineEstateSupply examineEstateSupply){
        try {
            if (examineEstateSupply.getId()==null || examineEstateSupply.getId().equals(0)){
                examineEstateSupplyService.addExamineEstateSupply(examineEstateSupply);
            }else {
                examineEstateSupplyService.updateExamineEstateSupply(examineEstateSupply);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/line_water_supply_pipe_grade",method = {RequestMethod.GET},name = "供应保障等级")
    public HttpResult estate_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_LINE_WATER_SUPPLY_PIPE_GRADE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/supplier_grade",method = {RequestMethod.GET},name = "供应商等级")
    public HttpResult supplier_grade() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLIER_GRADE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

}
