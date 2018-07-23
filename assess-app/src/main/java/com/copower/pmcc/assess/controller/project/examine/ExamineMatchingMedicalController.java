package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedical;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingMedicalService;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/23 16:41
 * @Description:医养条件
 */
@RequestMapping(value = "/examineMatchingMedical")
@Controller
public class ExamineMatchingMedicalController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingMedicalService examineMatchingMedicalService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Deprecated
    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/task/survey/examine/residence/apply/matchingMedical" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingMedicalById",method = {RequestMethod.GET},name = "获取医养条件")
    public HttpResult getById(Integer id) {
        ExamineMatchingMedical examineMatchingMedical = null;
        try {
            if (id!=null){
                examineMatchingMedical = examineMatchingMedicalService.getExamineMatchingMedicalById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingMedical);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingMedicalList",method = {RequestMethod.GET},name = "医养条件列表")
    public BootstrapTableVo getExamineMatchingMedicalList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingMedical examineMatchingMedical = new ExamineMatchingMedical();
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingMedical.setExamineType(examineType);
            }
            if (declareId!=null && declareId.equals(0)){
                examineMatchingMedical.setDeclareId(declareId);
            }
            vo = examineMatchingMedicalService.getExamineMatchingMedicalLists(examineMatchingMedical);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingMedicalById",method = {RequestMethod.POST},name = "删除医养条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingMedicalService.deleteExamineMatchingMedical(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingMedical",method = {RequestMethod.POST},name = "更新医养条件")
    public HttpResult save(ExamineMatchingMedical examineMatchingMedical){
        try {
            if (examineMatchingMedical.getId()==null || examineMatchingMedical.getId().equals(0)){
                examineMatchingMedicalService.addExamineMatchingMedical(examineMatchingMedical);
            }else {
                examineMatchingMedicalService.updateExamineMatchingMedical(examineMatchingMedical);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_examinematchingmedical_level",method = {RequestMethod.GET},name = "医养条件等级")
    public HttpResult estate_examinematchingmedical_level() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_EXAMINEMATCHINGMEDICAL_LEVEL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_examinematchingmedical_distance",method = {RequestMethod.GET},name = "医养条件距离")
    public HttpResult estate_examinematchingmedical_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_EXAMINEMATCHINGMEDICAL_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

}
