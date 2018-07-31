package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseIntelligentService;
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
 * @Date: 2018/7/26 08:53
 * @Description:电力通讯网络
 */
@RequestMapping(value = "/examineHouseIntelligent")
@Controller
public class ExamineHouseIntelligentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineHouseIntelligentService examineHouseIntelligentService;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)", method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/houseIntelligent";
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseIntelligentById", method = {RequestMethod.GET}, name = "获取电力通讯网络")
    public HttpResult getById(Integer id) {
        ExamineHouseIntelligent examineHouseIntelligent = null;
        try {
            if (id != null) {
                examineHouseIntelligent = examineHouseIntelligentService.getExamineHouseIntelligentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseIntelligent);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseIntelligentList", method = {RequestMethod.GET}, name = "电力通讯网络列表")
    public BootstrapTableVo getExamineHouseIntelligentList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseIntelligent examineHouseIntelligent = new ExamineHouseIntelligent();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineHouseIntelligent.setExamineType(examineType);
            }
            if (declareId != null && declareId.equals(0)) {
                examineHouseIntelligent.setDeclareId(declareId);
            }
            vo = examineHouseIntelligentService.getExamineHouseIntelligentLists(examineHouseIntelligent);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseIntelligentById", method = {RequestMethod.POST}, name = "删除电力通讯网络")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(examineHouseIntelligentService.deleteExamineHouseIntelligent(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseIntelligent", method = {RequestMethod.POST}, name = "更新电力通讯网络")
    public HttpResult save(ExamineHouseIntelligent examineHouseIntelligent) {
        try {
            if (examineHouseIntelligent.getId() == null || examineHouseIntelligent.getId().equals(0)) {
                examineHouseIntelligentService.addExamineHouseIntelligent(examineHouseIntelligent);
            } else {
                examineHouseIntelligentService.updateExamineHouseIntelligent(examineHouseIntelligent);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_wire_erection_method", method = {RequestMethod.GET}, name = "电线架设方式")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_WIRE_ERECTION_METHOD);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_switch_circuit", method = {RequestMethod.GET}, name = "开关回路")
    public HttpResult examine_house_switch_circuit() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_SWITCH_CIRCUIT);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_lamps_lanterns", method = {RequestMethod.GET}, name = "灯具")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LAMPS_LANTERNS);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_internal_communication", method = {RequestMethod.GET}, name = "屋内通讯")
    public HttpResult examine_house_internal_communication() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_INTERNAL_COMMUNICATION);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_monitoring_system", method = {RequestMethod.GET}, name = "监控系统")
    public HttpResult examine_house_monitoring_system() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_MONITORING_SYSTEM);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_intelligent_system", method = {RequestMethod.GET}, name = "智能系统")
    public HttpResult examine_house_intelligent_system() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_INTELLIGENT_SYSTEM);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

}
