package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreet;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseFaceStreetService;
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
 * @Date: 2018/7/25 15:08
 * @Description:临街（路）状况
 */
@RequestMapping(value = "/examineHouseFaceStreet")
@Controller
public class ExamineHouseFaceStreetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseFaceStreetService examineHouseFaceStreetService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/houseFaceStreet" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseFaceStreetById",method = {RequestMethod.GET},name = "获取临街（路）状况")
    public HttpResult getById(Integer id) {
        ExamineHouseFaceStreet examineHouseFaceStreet = null;
        try {
            if (id!=null){
                examineHouseFaceStreet = examineHouseFaceStreetService.getExamineHouseFaceStreetById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseFaceStreet);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseFaceStreetList",method = {RequestMethod.GET},name = "临街（路）状况列表")
    public BootstrapTableVo getExamineHouseFaceStreetList(Integer examineType, Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseFaceStreet examineHouseFaceStreet = new ExamineHouseFaceStreet();
            if (!ObjectUtils.isEmpty(examineType)){
                examineHouseFaceStreet.setExamineType(examineType);
            }
            if (declareId!=null){
                examineHouseFaceStreet.setDeclareId(declareId);
            }
            if (planDetailsId!=null){
                examineHouseFaceStreet.setPlanDetailsId(planDetailsId);
            }
            vo = examineHouseFaceStreetService.getExamineHouseFaceStreetLists(examineHouseFaceStreet);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseFaceStreetById",method = {RequestMethod.POST},name = "删除临街（路）状况")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseFaceStreetService.deleteExamineHouseFaceStreet(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseFaceStreet",method = {RequestMethod.POST},name = "更新临街（路）状况")
    public HttpResult save(ExamineHouseFaceStreet examineHouseFaceStreet){
        try {
            if (examineHouseFaceStreet.getId()==null || examineHouseFaceStreet.getId().equals(0)){
                examineHouseFaceStreetService.addExamineHouseFaceStreet(examineHouseFaceStreet);
            }else {
                examineHouseFaceStreetService.updateExamineHouseFaceStreet(examineHouseFaceStreet);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_street_level",method = {RequestMethod.GET},name = "街道级别")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_STREET_LEVEL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_traffic_flow",method = {RequestMethod.GET},name = "交通流量")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_TRAFFIC_FLOW);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_visitors_flowrate",method = {RequestMethod.GET},name = "人流量")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_VISITORS_FLOWRATE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
