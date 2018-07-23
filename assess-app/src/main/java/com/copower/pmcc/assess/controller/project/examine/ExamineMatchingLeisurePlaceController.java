package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlace;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingLeisurePlaceService;
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
 * @Date: 2018/7/20 18:18
 * @Description:休闲场所-包含-购物-娱乐-餐饮
 */
@RequestMapping(value = "/examineMatchingLeisurePlace")
@Controller
public class ExamineMatchingLeisurePlaceController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExamineMatchingLeisurePlaceService examineMatchingLeisurePlaceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Deprecated
    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/matchingMarket" ; //购物商场
//        String view = "/task/survey/examine/residence/apply/matchingRecreation" ; //休闲娱乐
        String view = "/task/survey/examine/residence/apply/matchingRestaurant" ; //餐饮
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingLeisurePlaceById",method = {RequestMethod.GET},name = "获取休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult getById(Integer id) {
        ExamineMatchingLeisurePlace examineMatchingLeisurePlace = null;
        try {
            if (id!=null){
                examineMatchingLeisurePlace = examineMatchingLeisurePlaceService.getExamineMatchingLeisurePlaceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingLeisurePlace);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingLeisurePlaceList",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮列表")
    public BootstrapTableVo getExamineMatchingLeisurePlaceList(Integer examineType, Integer declareId,String type) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingLeisurePlace examineMatchingLeisurePlace = new ExamineMatchingLeisurePlace();
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingLeisurePlace.setExamineType(examineType);
            }
            if (declareId!=null && declareId.equals(0)){
                examineMatchingLeisurePlace.setDeclareId(declareId);
            }
            if (!StringUtils.isEmpty(type)){
                examineMatchingLeisurePlace.setType(type);
            }
            vo = examineMatchingLeisurePlaceService.getExamineMatchingLeisurePlaceLists(examineMatchingLeisurePlace);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingLeisurePlaceById",method = {RequestMethod.POST},name = "删除休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingLeisurePlaceService.deleteExamineMatchingLeisurePlace(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingLeisurePlace",method = {RequestMethod.POST},name = "更新休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult save(ExamineMatchingLeisurePlace examineMatchingLeisurePlace){
        try {
            if (examineMatchingLeisurePlace.getId()==null || examineMatchingLeisurePlace.getId().equals(0)){
                examineMatchingLeisurePlaceService.addExamineMatchingLeisurePlace(examineMatchingLeisurePlace);
            }else {
                examineMatchingLeisurePlaceService.updateExamineMatchingLeisurePlace(examineMatchingLeisurePlace);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examineMatchingLeisurePlace_category",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮类别")
    public HttpResult examineMatchingLeisurePlace_category(String type) {
        try {
            List<BaseDataDic> baseDataDic = null;
            if (!StringUtils.isEmpty(type)){
                ExamineMatchingLeisurePlaceTypeEnum typeEnum = ExamineMatchingLeisurePlaceTypeEnum.getEnumByName(ExamineMatchingLeisurePlaceTypeEnum.getNameByKey(type));
                baseDataDic = examineMatchingLeisurePlaceService.examineMatchingLeisurePlace_category(typeEnum);
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
    @RequestMapping(value = "/examineMatchingLeisurePlace_grade",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮 档次")
    public HttpResult examineMatchingLeisurePlace_grade(String type) {
        try {
            List<BaseDataDic> baseDataDic = null;
            if (!StringUtils.isEmpty(type)){
                ExamineMatchingLeisurePlaceTypeEnum typeEnum = ExamineMatchingLeisurePlaceTypeEnum.getEnumByName(ExamineMatchingLeisurePlaceTypeEnum.getNameByKey(type));
                baseDataDic = examineMatchingLeisurePlaceService.examineMatchingLeisurePlace_grade(typeEnum);
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
    @RequestMapping(value = "/examineMatchingLeisurePlace_distance",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮 距离")
    public HttpResult examineMatchingLeisurePlace_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SHOP_OR_ENTERTAINMENT_OR_DINING_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

}
