package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/7/24 10:05
 * @Description:楼栋基础信息
 */
@RequestMapping(value = "/examineBuilding")
@Controller
public class ExamineBuildingController {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ExamineBuildingService examineBuildingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingList", method = {RequestMethod.GET}, name = "获取楼栋信息")
    public HttpResult getExamineBuildingList(Integer declareId, Integer planDetailsId, Integer examineType) {
        try {
            return HttpResult.newCorrectResult(examineBuildingService.getByDeclareIdAndExamineType(declareId, planDetailsId, examineType));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/initSonMainOutfitSurface", method = {RequestMethod.POST}, name = "初始化 子类")
    public HttpResult initSonMainOutfitSurface() {
        try {
            examineBuildingService.initSonMainOutfitSurfaceFunction();
            return HttpResult.newCorrectResult("success");
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/copySonMainOutfitSurfaceFunction", method = {RequestMethod.POST}, name = "获取所有 子类")
    public HttpResult copySonMainOutfitSurfaceFunction(Integer planDetailsId, Integer examineType, Integer declareId, String newBuildNumber, String oldBuildNumber) {
        try {
            return HttpResult.newCorrectResult(examineBuildingService.copySonMainOutfitSurfaceFunction(planDetailsId, examineType, declareId, oldBuildNumber, newBuildNumber));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/copyBuildFileData", method = {RequestMethod.POST}, name = "处理附件")
    public HttpResult copyBuildFileData(String formData) {
        try {
            return HttpResult.newCorrectResult(examineBuildingService.copyBuildFileData(formData));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateSonMainOutfitSurface", method = {RequestMethod.POST}, name = "更新 子类")
    public HttpResult updateSonMainOutfitSurface(String buildNumber) {
        try {
            examineBuildingService.updateSonMainOutfitSurface(buildNumber);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/estate_examineBuilding_category", method = {RequestMethod.GET}, name = "楼栋基础 建筑类别")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_PROPERTY_CATEGORY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_building_structure", method = {RequestMethod.GET}, name = "楼栋基础 建筑结构 上")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_PROPERTY_STRUCTURE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasisList", method = {RequestMethod.GET}, name = "楼栋基础 建筑结构 下")
    public HttpResult getBasisList(Integer id) {
        List<BaseDataDic> baseDataDic = null;
        try {
            if (id != null) {
                baseDataDic = baseDataDicService.getCacheDataDicListByPid(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(baseDataDic);
    }

    @ResponseBody
    @RequestMapping(value = "/estate_building_type", method = {RequestMethod.GET}, name = "楼栋基础 物业类型")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_PROPERTY_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBuildAndProperty", method = {RequestMethod.GET}, name = "楼栋基础 物业公司与建筑公司以及开发商")
    public HttpResult getBuildAndProperty(String type) {
        try {
            if (!StringUtils.isEmpty(type)) {
                if (Objects.equal(type, "DataBuilder")) {
                    return HttpResult.newCorrectResult(examineBuildingService.getDataBuilderList());
                }
                if (Objects.equal(type, "DataProperty")) {
                    return HttpResult.newCorrectResult(examineBuildingService.getDataPropertyList());
                }
                if (Objects.equal(type, "DataDeveloper")) {
                    return HttpResult.newCorrectResult(examineBuildingService.getDataDeveloperList());
                }
                if (Objects.equal("type", type)) {
                    Map<Object, Object> map = Maps.newHashMap();
                    map.put(DataDeveloper.class.getSimpleName(), examineBuildingService.getDataDeveloperList());
                    map.put(DataProperty.class.getSimpleName(), examineBuildingService.getDataPropertyList());
                    map.put(DataBuilder.class.getSimpleName(), examineBuildingService.getDataBuilderList());
                    return HttpResult.newCorrectResult(map);
                }
            }
            return null;
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


}
