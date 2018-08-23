package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfit;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingOutfitService;
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
 * @Date: 2018/7/24 15:24
 * @Description:楼栋外装情况
 */
@RequestMapping(value = "/examineBuildingOutfit")
@Controller
public class ExamineBuildingOutfitController {
    @Autowired
    private ExamineBuildingOutfitService examineBuildingOutfitService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/examineBuildingOutfit" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingOutfitById",method = {RequestMethod.GET},name = "获取楼栋外装情况")
    public HttpResult getById(Integer id) {
        ExamineBuildingOutfit examineBuildingOutfit = null;
        try {
            if (id!=null){
                examineBuildingOutfit = examineBuildingOutfitService.getExamineBuildingOutfitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuildingOutfit);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingOutfitList",method = {RequestMethod.GET},name = "楼栋外装情况列表")
    public BootstrapTableVo getExamineBuildingOutfitList(Integer examineType, Integer declareId,Integer planDetailsId, Integer buildingId) {
        BootstrapTableVo vo = null;
        try {
            ExamineBuildingOutfit examineBuildingOutfit = new ExamineBuildingOutfit();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineBuildingOutfit.setExamineType(examineType);
            }
            if (declareId != null ) {
                examineBuildingOutfit.setDeclareId(declareId);
            }
            if (planDetailsId != null ) {
                examineBuildingOutfit.setPlanDetailsId(planDetailsId);
            }
            if (buildingId != null) {
                examineBuildingOutfit.setBuildingId(buildingId);
            }
            vo = examineBuildingOutfitService.getExamineBuildingOutfitLists(examineBuildingOutfit);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineBuildingOutfitById",method = {RequestMethod.POST},name = "删除楼栋外装情况")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineBuildingOutfitService.deleteExamineBuildingOutfit(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineBuildingOutfit",method = {RequestMethod.POST},name = "更新楼栋外装情况")
    public HttpResult save(ExamineBuildingOutfit examineBuildingOutfit){
        try {
            if (examineBuildingOutfit.getId()==null || examineBuildingOutfit.getId().equals(0)){
                examineBuildingOutfitService.addExamineBuildingOutfit(examineBuildingOutfit);
            }else {
                examineBuildingOutfitService.updateExamineBuildingOutfit(examineBuildingOutfit);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_decoration_part",method = {RequestMethod.GET},name = "装修部位")
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
    @RequestMapping(value = "/examine_building_decorating_material",method = {RequestMethod.GET},name = "装修材料")
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
    @RequestMapping(value = "/examine_building_material_price",method = {RequestMethod.GET},name = "材料价格区间")
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
    @RequestMapping(value = "/examine_building_construction_technology",method = {RequestMethod.GET},name = "施工工艺")
    public HttpResult examine_building_construction_technology() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initRemoveExamineBuildingOutfit",method = {RequestMethod.POST},name = "楼栋外装初始化 (删除id==0的数据)")
    public HttpResult initRemove() {
        try {
            return HttpResult.newCorrectResult(examineBuildingOutfitService.initRemove());
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
