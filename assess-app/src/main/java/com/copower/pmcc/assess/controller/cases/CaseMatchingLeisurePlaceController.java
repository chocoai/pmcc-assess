package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlace;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingLeisurePlaceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/9/17 16:21
 * @Description:
 */
@RequestMapping(value = "/caseMatchingLeisurePlace")
@Controller
public class CaseMatchingLeisurePlaceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingLeisurePlaceById",method = {RequestMethod.GET},name = "获取休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult getById(Integer id) {
        CaseMatchingLeisurePlace caseMatchingLeisurePlace = null;
        try {
            if (id!=null){
                caseMatchingLeisurePlace = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingLeisurePlace);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingLeisurePlaceList",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮列表")
    public BootstrapTableVo getCaseMatchingLeisurePlaceList( String type,Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingLeisurePlace caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
            if (estateId != null){
                caseMatchingLeisurePlace.setEstateId(estateId);
            }
            if (!StringUtils.isEmpty(type)){
                caseMatchingLeisurePlace.setType(type);
            }
            caseMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
            vo = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceLists(caseMatchingLeisurePlace);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingLeisurePlaceById",method = {RequestMethod.POST},name = "删除休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingLeisurePlaceService.deleteCaseMatchingLeisurePlace(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingLeisurePlace",method = {RequestMethod.POST},name = "更新休闲场所-包含-购物-娱乐-餐饮")
    public HttpResult save(CaseMatchingLeisurePlace caseMatchingLeisurePlace){
        try {
            if (caseMatchingLeisurePlace.getId()==null || caseMatchingLeisurePlace.getId().equals(0)){
                caseMatchingLeisurePlaceService.addCaseMatchingLeisurePlace(caseMatchingLeisurePlace);
            }else {
                caseMatchingLeisurePlaceService.updateCaseMatchingLeisurePlace(caseMatchingLeisurePlace);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/caseMatchingLeisurePlace_category",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮类别")
    public HttpResult caseMatchingLeisurePlace_category(String type) {
        try {
            List<BaseDataDic> baseDataDic = null;
            if (!StringUtils.isEmpty(type)){
                ExamineMatchingLeisurePlaceTypeEnum typeEnum = ExamineMatchingLeisurePlaceTypeEnum.getEnumByName(ExamineMatchingLeisurePlaceTypeEnum.getNameByKey(type));
                baseDataDic = caseMatchingLeisurePlaceService.caseMatchingLeisurePlace_category(typeEnum);
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
    @RequestMapping(value = "/caseMatchingLeisurePlace_grade",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮 档次")
    public HttpResult caseMatchingLeisurePlace_grade(String type) {
        try {
            List<BaseDataDic> baseDataDic = null;
            if (!StringUtils.isEmpty(type)){
                ExamineMatchingLeisurePlaceTypeEnum typeEnum = ExamineMatchingLeisurePlaceTypeEnum.getEnumByName(ExamineMatchingLeisurePlaceTypeEnum.getNameByKey(type));
                baseDataDic = caseMatchingLeisurePlaceService.caseMatchingLeisurePlace_grade(typeEnum);
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
    @RequestMapping(value = "/caseMatchingLeisurePlace_distance",method = {RequestMethod.GET},name = "休闲场所-包含-购物-娱乐-餐饮 距离")
    public HttpResult caseMatchingLeisurePlace_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SHOP_OR_ENTERTAINMENT_OR_DINING_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

}
