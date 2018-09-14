package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseCorollaryEquipmentService;
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
 * @Date: 2018/8/2 15:46
 * @Description:配套设备设施
 */
@RequestMapping(value = "/examineHouseCorollaryEquipment")
@Controller
public class ExamineHouseCorollaryEquipmentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseCorollaryEquipmentService examineHouseCorollaryEquipmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseCorollaryEquipmentById", method = {RequestMethod.GET}, name = "获取配套设备设施")
    public HttpResult getById(Integer id) {
        ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment = null;
        try {
            if (id != null) {
                examineHouseCorollaryEquipment = examineHouseCorollaryEquipmentService.getExamineHouseCorollaryEquipmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineHouseCorollaryEquipment);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseCorollaryEquipmentList", method = {RequestMethod.GET}, name = "配套设备设施列表")
    public BootstrapTableVo getExamineHouseCorollaryEquipmentList(Integer examineType, Integer declareId,Integer planDetailsId) {
        BootstrapTableVo vo = null;
        try {
            ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment = new ExamineHouseCorollaryEquipment();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineHouseCorollaryEquipment.setExamineType(examineType);
            }
            if (declareId != null) {
                examineHouseCorollaryEquipment.setDeclareId(declareId);
            }
            if (planDetailsId != null) {
                examineHouseCorollaryEquipment.setPlanDetailsId(planDetailsId);
            }
            vo = examineHouseCorollaryEquipmentService.getExamineHouseCorollaryEquipmentLists(examineHouseCorollaryEquipment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseCorollaryEquipmentById", method = {RequestMethod.POST}, name = "删除配套设备设施")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(examineHouseCorollaryEquipmentService.deleteExamineHouseCorollaryEquipment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineHouseCorollaryEquipment", method = {RequestMethod.POST}, name = "更新配套设备设施")
    public HttpResult save(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        try {
            if (examineHouseCorollaryEquipment.getId() == null || examineHouseCorollaryEquipment.getId().equals(0)) {
                examineHouseCorollaryEquipmentService.addExamineHouseCorollaryEquipment(examineHouseCorollaryEquipment);
            } else {
                examineHouseCorollaryEquipmentService.updateExamineHouseCorollaryEquipment(examineHouseCorollaryEquipment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_corollary_equipment_category", method = {RequestMethod.GET}, name = "配套设备设施 类别")
    public HttpResult examine_house_corollary_equipment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_CATEGORY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_corollary_equipment_type", method = {RequestMethod.GET}, name = "配套设备设施 类型")
    public HttpResult examine_house_corollary_equipment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_corollary_equipment_price", method = {RequestMethod.GET}, name = "配套设备设施 价格")
    public HttpResult examine_house_corollary_equipment_price() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_PRICE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
