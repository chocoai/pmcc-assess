package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParking;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineEstateParkingService;
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
 * @Date: 2018/7/19 18:13
 * @Description:车位信息
 */
@RequestMapping(value = "/examineEstateParking")
@Controller
public class ExamineEstateParkingController {

    @Autowired
    private ExamineEstateParkingService examineEstateParkingService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/estateParking" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateParkingById",method = {RequestMethod.GET},name = "获取车位")
    public HttpResult getById(Integer id) {
        ExamineEstateParking examineEstateParking = null;
        try {
            if (id!=null){
                examineEstateParking = examineEstateParkingService.getEstateParkingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineEstateParking);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateParkingList",method = {RequestMethod.GET},name = "车位列表")
    public BootstrapTableVo getExamineEstateParkingList(String location, Integer examineType,  Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineEstateParking examineEstateParking = new ExamineEstateParking();
            if (!ObjectUtils.isEmpty(examineType)){
                examineEstateParking.setExamineType(examineType);
            }
            if (declareId!=null ){
                examineEstateParking.setDeclareId(declareId);
            }
            if (!StringUtils.isEmpty(location)){
                examineEstateParking.setLocation(location);
            }
            vo = examineEstateParkingService.getExamineEstateNetworkList(examineEstateParking);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineEstateParkingById",method = {RequestMethod.POST},name = "删除车位")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineEstateParkingService.deleteEstateParking(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineEstateParking",method = {RequestMethod.POST},name = "更新车位")
    public HttpResult save(ExamineEstateParking examineEstateParking){
        try {
            if (examineEstateParking.getId()==null || examineEstateParking.getId().equals(0)){
                examineEstateParkingService.addEstateParking(examineEstateParking);
            }else {
                examineEstateParkingService.updateEstateParking(examineEstateParking);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_car_type",method = {RequestMethod.GET},name = "获取车位类型")
    public HttpResult estate_distance(Integer id) {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_CAR_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
