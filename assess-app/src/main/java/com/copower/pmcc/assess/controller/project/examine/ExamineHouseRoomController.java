package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseRoomDecorateService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseRoomService;
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
 * @Date: 2018/7/24 18:23
 * @Description:房间
 */
@RequestMapping(value = "/examineHouseRoom")
@Controller
public class ExamineHouseRoomController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseRoomService examineHouseRoomService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineHouseRoomDecorateService examineHouseRoomDecorateService;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/houseRoom" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseRoomById",method = {RequestMethod.GET},name = "获取房间 (父类)")
    public HttpResult getById(Integer id) {
        ExamineHouseRoom examineHouseRoom = null;
        try {
            if (id!=null){
                examineHouseRoom = examineHouseRoomService.getExamineHouseRoomById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseRoom);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseRoomList",method = {RequestMethod.GET},name = "获取房间列表 (父类)")
    public BootstrapTableVo getExamineHouseRoomList(ExamineHouseRoom examineHouseRoom) {
        BootstrapTableVo vo = null;
        try {
//            ExamineHouseRoom examineHouseRoom = new ExamineHouseRoom();
//            if (!ObjectUtils.isEmpty(examineType)){
//                examineHouseRoom.setExamineType(examineType);
//            }
            vo = examineHouseRoomService.getExamineHouseRoomLists(examineHouseRoom);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseRoomById",method = {RequestMethod.POST},name = "删除房间 (父类)")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseRoomService.deleteExamineHouseRoom(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseRoom",method = {RequestMethod.POST},name = "更新房间 (父类)")
    public HttpResult save(ExamineHouseRoom examineHouseRoom){
        try {
            if (examineHouseRoom.getId()==null || examineHouseRoom.getId().equals(0)){
                examineHouseRoomService.addExamineHouseRoom(examineHouseRoom);
            }else {
                examineHouseRoomService.updateExamineHouseRoom(examineHouseRoom);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_unit_house_type",method = {RequestMethod.GET},name = "房间类别 (父类)")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_UNIT_HOUSE_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    /*----------------------------------------------------------分割一下  子类方法-----------------------------------------------------------*/

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseRoomDecorateLists",method = {RequestMethod.GET},name = "获取房间装修列表 (子类)")
    public BootstrapTableVo getExamineHouseRoomDecorateLists(Integer examineType) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseRoomDecorate examineHouseRoomDecorate = new ExamineHouseRoomDecorate();
            if (!ObjectUtils.isEmpty(examineType)){
                examineHouseRoomDecorate.setExamineType(examineType);
            }
            vo = examineHouseRoomDecorateService.getExamineHouseRoomDecorateLists(examineHouseRoomDecorate);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseRoomDecorate",method = {RequestMethod.POST},name = "更新房间装修 (子类)")
    public HttpResult saveExamineHouseRoomDecorate(ExamineHouseRoomDecorate examineHouseRoomDecorate){
        try {
            if (examineHouseRoomDecorate.getId()==null || examineHouseRoomDecorate.getId().equals(0)){
                examineHouseRoomDecorateService.addExamineHouseRoomDecorate(examineHouseRoomDecorate);
            }else {
                examineHouseRoomDecorateService.updateExamineHouseRoomDecorate(examineHouseRoomDecorate);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseRoomDecorateById",method = {RequestMethod.POST},name = "删除房间装修 (子类)")
    public HttpResult deleteExamineHouseRoomDecorate(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseRoomDecorateService.deleteExamineHouseRoomDecorate(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_decoration_part",method = {RequestMethod.GET},name = "装修部位 (子类)")
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
    @RequestMapping(value = "/examine_building_decorating_material",method = {RequestMethod.GET},name = "装修材料 (子类)")
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
    @RequestMapping(value = "/examine_building_material_price",method = {RequestMethod.GET},name = "材料价格区间 (子类)")
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
    @RequestMapping(value = "/examine_building_construction_technology",method = {RequestMethod.GET},name = "施工工艺 (子类)")
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
