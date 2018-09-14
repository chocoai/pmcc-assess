package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducation;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingEducationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/7/20 14:13
 * @Description:教育条件
 */
@RequestMapping(value = "/examineMatchingEducation")
@Controller
public class ExamineMatchingEducationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineMatchingEducationService examineMatchingEducationService;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/matchingEducation" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingEducationById",method = {RequestMethod.GET},name = "获取教育条件")
    public HttpResult getById(Integer id) {
        ExamineMatchingEducation examineMatchingEducation = null;
        try {
            if (id!=null){
                examineMatchingEducation = examineMatchingEducationService.getExamineMatchingEducationById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingEducation);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingEducationList",method = {RequestMethod.GET},name = "教育条件列表")
    public BootstrapTableVo getExamineMatchingEducationList(String name, Integer examineType, Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingEducation examineMatchingEducation = new ExamineMatchingEducation();
            if (!StringUtils.isEmpty(name)){
                examineMatchingEducation.setSchoolName(name);
            }
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingEducation.setExamineType(examineType);
            }
            if (declareId!=null ){
                examineMatchingEducation.setDeclareId(declareId);
            }
            if (planDetailsId!=null ){
                examineMatchingEducation.setPlanDetailsId(planDetailsId);
            }
            vo = examineMatchingEducationService.getExamineMatchingEducationLists(examineMatchingEducation);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingEducationById",method = {RequestMethod.POST},name = "删除教育条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingEducationService.deleteExamineMatchingEducation(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingEducation",method = {RequestMethod.POST},name = "更新教育条件")
    public HttpResult save(ExamineMatchingEducation examineMatchingEducation){
        try {
            if (examineMatchingEducation.getId()==null || examineMatchingEducation.getId().equals(0)){
                examineMatchingEducationService.addExamineMatchingEducation(examineMatchingEducation);
            }else {
                examineMatchingEducationService.updateExamineMatchingEducation(examineMatchingEducation);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/school_nature",method = {RequestMethod.GET},name = "学校性质")
    public HttpResult school_nature() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SCHOOL_NATURE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/school_gradation",method = {RequestMethod.GET},name = "学校级次")
    public HttpResult school_gradation() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SCHOOL_GRADATION);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/school_level",method = {RequestMethod.GET},name = "学校等级")
    public HttpResult school_level() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SCHOOL_LEVEL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_distance",method = {RequestMethod.GET},name = "获取距离")
    public HttpResult estate_distance(Integer id) {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
