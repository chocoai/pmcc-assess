package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterial;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingMaterialService;
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
 * @Date: 2018/8/2 15:42
 * @Description:原料供应及销售条件
 */

@RequestMapping(value = "/examineMatchingMaterial")
@Controller
public class ExamineMatchingMaterialController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingMaterialService examineMatchingMaterialService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingMaterialById",method = {RequestMethod.GET},name = "获取原料供应及销售条件")
    public HttpResult getById(Integer id) {
        ExamineMatchingMaterial examineMatchingMaterial = null;
        try {
            if (id!=null){
                examineMatchingMaterial = examineMatchingMaterialService.getExamineMatchingMaterialById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingMaterial);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingMaterialList",method = {RequestMethod.GET},name = "原料供应及销售条件列表")
    public BootstrapTableVo getExamineMatchingMaterialList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingMaterial examineMatchingMaterial = new ExamineMatchingMaterial();
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingMaterial.setExamineType(examineType);
            }
            if (declareId!=null && declareId.equals(0)){
                examineMatchingMaterial.setDeclareId(declareId);
            }
            vo = examineMatchingMaterialService.getExamineMatchingMaterialLists(examineMatchingMaterial);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingMaterialById",method = {RequestMethod.POST},name = "删除原料供应及销售条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingMaterialService.deleteExamineMatchingMaterial(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingMaterial",method = {RequestMethod.POST},name = "更新原料供应及销售条件")
    public HttpResult save(ExamineMatchingMaterial examineMatchingMaterial){
        try {
            if (examineMatchingMaterial.getId()==null || examineMatchingMaterial.getId().equals(0)){
                examineMatchingMaterialService.addExamineMatchingMaterial(examineMatchingMaterial);
            }else {
                examineMatchingMaterialService.updateExamineMatchingMaterial(examineMatchingMaterial);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_type",method = {RequestMethod.GET},name = "购物场所类别")
    public HttpResult estate_supply_new_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_scale",method = {RequestMethod.GET},name = "购物场所规模")
    public HttpResult estate_supply_new_scale() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_SCALE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_distance",method = {RequestMethod.GET},name = "购物场所距离")
    public HttpResult estate_supply_new_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
    
}
