package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilding;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineBuildingDto;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    @Deprecated
    @RequestMapping(value = "/view", name = "转到index页面 (临时)", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/task/survey/examine/residence/apply/building";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingById", method = {RequestMethod.GET}, name = "获取楼栋基础")
    public HttpResult getById(Integer id) {
        ExamineBuildingVo examineBuilding = null;
        try {
            if (id != null) {
                examineBuilding = examineBuildingService.getExamineBuildingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuilding);
    }

    @ResponseBody
    @RequestMapping(value = "/getFirstData", method = {RequestMethod.GET}, name = "获取 第一楼栋基础")
    public HttpResult getFirstData(Integer examineType, Integer declareId) {
        ExamineBuilding examineBuilding = null;
        try {
           examineBuilding = examineBuildingService.getOneAndTwoData(examineType,declareId,1);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuilding);
    }

    @ResponseBody
    @RequestMapping(value = "/getTwoData", method = {RequestMethod.GET}, name = "获取 第二楼栋基础")
    public HttpResult getTwoData(Integer examineType, Integer declareId) {
        ExamineBuilding examineBuilding = null;
        try {
            examineBuilding = examineBuildingService.getOneAndTwoData(examineType,declareId,2);
        } catch (Exception e1) {
            logger.error( e1.getMessage(), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuilding);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingList", method = {RequestMethod.GET}, name = "楼栋基础列表")
    public BootstrapTableVo getExamineBuildingList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineBuilding examineBuilding = new ExamineBuilding();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineBuilding.setExamineType(examineType);
            }
            if (declareId != null && declareId.equals(0)) {
                examineBuilding.setDeclareId(declareId);
            }
            vo = examineBuildingService.getExamineBuildingLists(examineBuilding);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineBuildingById", method = {RequestMethod.POST}, name = "删除楼栋基础")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(examineBuildingService.deleteExamineBuilding(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineBuilding", method = {RequestMethod.POST}, name = "更新楼栋基础")
    public HttpResult save(ExamineBuildingDto examineBuildingDto) {
        ExamineBuilding examineBuilding = new ExamineBuilding();
        BeanUtils.copyProperties(examineBuildingDto, examineBuilding);
        try {
            if (examineBuilding.getId() == null || examineBuilding.getId().equals(0)) {
                examineBuildingService.addExamineBuilding(examineBuilding);
            } else {
                examineBuildingService.updateExamineBuilding(examineBuilding);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
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
            }
            return null;
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    //-----------
    @ResponseBody
    @RequestMapping(value = "/estate_total_building_type", method = {RequestMethod.GET}, name = "总栋数 (0一栋 1多栋)")
    public HttpResult estate_total_building_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_BUILDING_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_total_land_use", method = {RequestMethod.GET}, name = "土地用途")
    public HttpResult estate_total_land_use() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_total_land_level", method = {RequestMethod.GET}, name = "土地级别")
    public HttpResult estate_total_land_level() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_LEVEL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

}
