package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineUnitHuxingService;
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
 * @Date: 2018/7/24 16:27
 * @Description:户型
 */
@RequestMapping(value = "/examineUnitHuxing")
@Controller
public class ExamineUnitHuxingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineUnitHuxingService examineUnitHuxingService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/unitHuxing" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitHuxingById", method = {RequestMethod.GET}, name = "获取户型")
    public HttpResult getById(Integer id) {
        ExamineUnitHuxing examineUnitHuxing = null;
        try {
            if (id != null) {
                examineUnitHuxing = examineUnitHuxingService.getExamineUnitHuxingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineUnitHuxingService.getExamineUnitHuxingVo(examineUnitHuxing));
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitHuxingList", method = {RequestMethod.GET}, name = "户型列表")
    public BootstrapTableVo getExamineUnitHuxingList(Integer examineType, Integer declareId, Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineUnitHuxing examineUnitHuxing = new ExamineUnitHuxing();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineUnitHuxing.setExamineType(examineType);
            }
            if (declareId != null) {
                examineUnitHuxing.setDeclareId(declareId);
            }
            if (planDetailsId != null) {
                examineUnitHuxing.setPlanDetailsId(planDetailsId);
            }
            vo = examineUnitHuxingService.getExamineUnitHuxingLists(examineUnitHuxing);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineUnitHuxingById", method = {RequestMethod.POST}, name = "删除户型")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(examineUnitHuxingService.deleteExamineUnitHuxing(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineUnitHuxing", method = {RequestMethod.POST}, name = "更新户型")
    public HttpResult save(ExamineUnitHuxing examineUnitHuxing) {
        try {
            if (examineUnitHuxing.getId() == null || examineUnitHuxing.getId().equals(0)) {
                examineUnitHuxingService.addExamineUnitHuxing(examineUnitHuxing);
            } else {
                examineUnitHuxingService.updateExamineUnitHuxing(examineUnitHuxing);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_unit_house_layout", method = {RequestMethod.GET}, name = "房型类别")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_UNIT_HOUSE_LAYOUT);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
