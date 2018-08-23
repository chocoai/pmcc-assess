package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingEnvironmentService;
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
 * @Date: 2018/7/20 16:06
 * @Description:环境因素
 */
@RequestMapping(value = "/examineMatchingEnvironment")
@Controller
public class ExamineMatchingEnvironmentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingEnvironmentService examineMatchingEnvironmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/matchingEnvironment" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingEnvironmentById",method = {RequestMethod.GET},name = "获取环境因素")
    public HttpResult getById(Integer id) {
        ExamineMatchingEnvironment examineMatchingEnvironment = null;
        try {
            if (id!=null){
                examineMatchingEnvironment = examineMatchingEnvironmentService.getExamineMatchingEnvironmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingEnvironment);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingEnvironmentList",method = {RequestMethod.GET},name = "环境因素列表")
    public BootstrapTableVo getExamineMatchingEnvironmentList(Integer examineType, Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingEnvironment examineMatchingEnvironment = new ExamineMatchingEnvironment();
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingEnvironment.setExamineType(examineType);
            }
            if (declareId!=null ){
                examineMatchingEnvironment.setDeclareId(declareId);
            }
            if (planDetailsId!=null ){
                examineMatchingEnvironment.setPlanDetailsId(planDetailsId);
            }
            vo = examineMatchingEnvironmentService.getExamineMatchingEnvironmentLists(examineMatchingEnvironment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingEnvironmentById",method = {RequestMethod.POST},name = "删除环境因素")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingEnvironmentService.deleteExamineMatchingEnvironment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingEnvironment",method = {RequestMethod.POST},name = "更新环境因素")
    public HttpResult save(ExamineMatchingEnvironment examineMatchingEnvironment){
        try {
            if (examineMatchingEnvironment.getId()==null || examineMatchingEnvironment.getId().equals(0)){
                examineMatchingEnvironmentService.addExamineMatchingEnvironment(examineMatchingEnvironment);
            }else {
                examineMatchingEnvironmentService.updateExamineMatchingEnvironment(examineMatchingEnvironment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/environment_type",method = {RequestMethod.GET},name = "环境类型")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/environment_category",method = {RequestMethod.GET},name = "环境类别")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_CATEGORY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/environment_influence_degree",method = {RequestMethod.GET},name = "影响程度")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_INFLUENCE_DEGREE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
