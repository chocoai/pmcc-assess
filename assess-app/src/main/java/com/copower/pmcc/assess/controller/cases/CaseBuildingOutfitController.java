package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfit;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingOutfitService;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:07
 * @Description:
 */
@RequestMapping(value = "/caseBuildingOutfit")
@Controller
public class CaseBuildingOutfitController {
    @Autowired
    private CaseBuildingOutfitService caseBuildingOutfitService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingOutfitById",method = {RequestMethod.GET},name = "获取楼栋外装情况")
    public HttpResult getById(Integer id) {
        CaseBuildingOutfit caseBuildingOutfit = null;
        try {
            if (id!=null){
                caseBuildingOutfit = caseBuildingOutfitService.getCaseBuildingOutfitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingOutfit);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingOutfitList",method = {RequestMethod.GET},name = "楼栋外装情况列表")
    public BootstrapTableVo getCaseBuildingOutfitList( Integer buildingId, String buildNumber) {
        BootstrapTableVo vo = null;
        try {
            CaseBuildingOutfit caseBuildingOutfit = new CaseBuildingOutfit();
            if (buildingId != null) {
                caseBuildingOutfit.setBuildingId(buildingId);
            }
            if (!StringUtils.isEmpty(buildNumber)){
                caseBuildingOutfit.setBuildNumber(buildNumber);
            }
            vo = caseBuildingOutfitService.getCaseBuildingOutfitLists(caseBuildingOutfit);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingOutfitById",method = {RequestMethod.POST},name = "删除楼栋外装情况")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseBuildingOutfitService.deleteCaseBuildingOutfit(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuildingOutfit",method = {RequestMethod.POST},name = "更新楼栋外装情况")
    public HttpResult save(CaseBuildingOutfit caseBuildingOutfit){
        try {
            if (caseBuildingOutfit.getId()==null || caseBuildingOutfit.getId().equals(0)){
                caseBuildingOutfitService.addCaseBuildingOutfit(caseBuildingOutfit);
            }else {
                caseBuildingOutfitService.updateCaseBuildingOutfit(caseBuildingOutfit);
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

}
