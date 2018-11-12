package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterial;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingMaterialService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 16:22
 * @Description:
 */
@RequestMapping(value = "/caseMatchingMaterial")
@Controller
public class CaseMatchingMaterialController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingMaterialById", method = {RequestMethod.GET}, name = "获取原料供应及销售条件")
    public HttpResult getById(Integer id) {
        CaseMatchingMaterial caseMatchingMaterial = null;
        try {
            if (id != null) {
                caseMatchingMaterial = caseMatchingMaterialService.getCaseMatchingMaterialById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingMaterial);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingMaterialList", method = {RequestMethod.GET}, name = "原料供应及销售条件列表")
    public BootstrapTableVo getCaseMatchingMaterialList(Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingMaterial caseMatchingMaterial = new CaseMatchingMaterial();
            if (estateId != null) {
                caseMatchingMaterial.setEstateId(estateId);
            }
            caseMatchingMaterial.setCreator(commonService.thisUserAccount());
            vo = caseMatchingMaterialService.getCaseMatchingMaterialLists(caseMatchingMaterial);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingMaterialById", method = {RequestMethod.POST}, name = "删除原料供应及销售条件")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseMatchingMaterialService.deleteCaseMatchingMaterial(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingMaterial", method = {RequestMethod.POST}, name = "更新原料供应及销售条件")
    public HttpResult save(CaseMatchingMaterial caseMatchingMaterial) {
        try {
            if (caseMatchingMaterial.getId() == null || caseMatchingMaterial.getId().equals(0)) {
                caseMatchingMaterialService.addCaseMatchingMaterial(caseMatchingMaterial);
            } else {
                caseMatchingMaterialService.updateCaseMatchingMaterial(caseMatchingMaterial);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_type", method = {RequestMethod.GET}, name = "购物场所类别")
    public HttpResult estate_supply_new_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_scale", method = {RequestMethod.GET}, name = "购物场所规模")
    public HttpResult estate_supply_new_scale() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_SCALE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_supply_new_distance", method = {RequestMethod.GET}, name = "购物场所距离")
    public HttpResult estate_supply_new_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
