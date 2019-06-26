package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHead;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsContentService;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsHeadService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zch on 2019/6/25.
 * 经济指标
 */
@RequestMapping(value = "/declareEconomicIndicatorsHead")
@RestController
public class DeclareEconomicIndicatorsHeadController {

    @Autowired
    private DeclareEconomicIndicatorsHeadService declareEconomicIndicatorsHeadService;
    @Autowired
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/saveAndUpdate")
    public HttpResult saveAndUpdate( String formData) {
        try {
            DeclareEconomicIndicatorsHead declareEconomicIndicatorsHead = JSON.parseObject(formData,DeclareEconomicIndicatorsHead.class);
            if (declareEconomicIndicatorsHead != null) {
                if (declareEconomicIndicatorsHead.getId() == null || declareEconomicIndicatorsHead.getId() == 0) {
                    declareEconomicIndicatorsHeadService.addDeclareEconomicIndicatorsHead(declareEconomicIndicatorsHead);
                } else {
                    declareEconomicIndicatorsHeadService.updateDeclareEconomicIndicatorsHead(declareEconomicIndicatorsHead);
                }
            }
            return HttpResult.newCorrectResult(declareEconomicIndicatorsHead);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "保存异常");
        }
    }

    @GetMapping(value = "/getByDeclareEconomicIndicatorsHeadId")
    public HttpResult getByDeclareEconomicIndicatorsHeadId(Integer id) {
        DeclareEconomicIndicatorsHead declareEconomicIndicatorsHead = null;
        try {
            declareEconomicIndicatorsHead = declareEconomicIndicatorsHeadService.getByDeclareEconomicIndicatorsHeadId(id);
            if (declareEconomicIndicatorsHead != null) {
                return HttpResult.newCorrectResult(declareEconomicIndicatorsHead);
            } else {
                return HttpResult.newErrorResult("未获取到数据!");
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, "异常" + e.getMessage());
        }
    }

    @GetMapping(value = "/getDeclareEconomicIndicatorsHeadList")
    public HttpResult getDeclareEconomicIndicatorsHeadList(DeclareEconomicIndicatorsHead declareEconomicIndicatorsHead) {
        try {
            return HttpResult.newCorrectResult(declareEconomicIndicatorsHeadService.getDeclareEconomicIndicatorsHeadList(declareEconomicIndicatorsHead));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, "异常" + e.getMessage());
        }
    }

    @PostMapping(value = "/removeDeclareEconomicIndicatorsHeadById")
    public HttpResult removeDeclareEconomicIndicatorsHeadById(Integer id){
        try {
            declareEconomicIndicatorsHeadService.removeDeclareEconomicIndicatorsHeadById(id) ;
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, "异常" + e.getMessage());
        }
    }

    @PostMapping(value = "/removeDeclareEconomicIndicatorsByContent",name = "删除子类  仅仅删除子类而已")
    public HttpResult removeDeclareEconomicIndicatorsByContent(Integer id){
        try {
            declareEconomicIndicatorsContentService.removeDeclareEconomicIndicatorsByContent(id) ;
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, "异常" + e.getMessage());
        }
    }

}
