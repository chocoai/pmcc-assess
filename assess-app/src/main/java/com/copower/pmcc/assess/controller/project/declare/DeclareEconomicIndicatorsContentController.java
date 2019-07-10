package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContent;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsContentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zch on 2019/6/25.
 * 经济指标
 */
@RequestMapping(value = "/declareEconomicIndicatorsContent")
@RestController
public class DeclareEconomicIndicatorsContentController {
    @Autowired
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/saveDeclareEconomicIndicatorsContentList", method = {RequestMethod.POST}, name = "保存经济指标")
    public HttpResult saveAndUpdate(Integer planDetailsId, Integer indicatorsHeadId, String formData) {
        try {
            List<DeclareEconomicIndicatorsContent> list = JSON.parseArray(formData, DeclareEconomicIndicatorsContent.class);
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(oo -> {
                    if (planDetailsId != null) {
                        oo.setPlanDetailsId(planDetailsId);
                    }
                    if (indicatorsHeadId != null) {
                        oo.setIndicatorsHeadId(indicatorsHeadId);
                    }
                    if (oo.getId() == null || oo.getId() == 0) {
                        declareEconomicIndicatorsContentService.addDeclareEconomicIndicatorsContent(oo);
                    } else {
                        declareEconomicIndicatorsContentService.updateDeclareEconomicIndicatorsContent(oo);
                    }
                });
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "保存异常");
        }
    }

    @RequestMapping(value = "/getDeclareEconomicIndicatorsContentList", method = {RequestMethod.POST}, name = "获取经济指标")
    public HttpResult getDeclareEconomicIndicatorsContentList(DeclareEconomicIndicatorsContent declareEconomicIndicatorsContent) {
        try {
            List<DeclareEconomicIndicatorsContent> indicatorsList = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(declareEconomicIndicatorsContent);
            return HttpResult.newCorrectResult(indicatorsList);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "保存异常");
        }
    }

    @RequestMapping(value = "/getEntityListByPid", method = {RequestMethod.POST}, name = "获取经济指标")
    public HttpResult getEntityListByPid(Integer indicatorsHeadId) {
        try {
            List<DeclareEconomicIndicatorsContent> indicatorsList = Lists.newArrayList();
            if (indicatorsHeadId != null) {
                DeclareEconomicIndicatorsContent query = new DeclareEconomicIndicatorsContent();
                query.setIndicatorsHeadId(indicatorsHeadId);
                List<DeclareEconomicIndicatorsContent> indicatorsList2 = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(query);
                if (CollectionUtils.isNotEmpty(indicatorsList2)) {
                    indicatorsList.addAll(indicatorsList2);
                }
            }
            return HttpResult.newCorrectResult(indicatorsList);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "保存异常");
        }
    }

}
