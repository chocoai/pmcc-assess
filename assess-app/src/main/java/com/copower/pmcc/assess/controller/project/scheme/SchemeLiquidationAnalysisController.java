package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeLiquidationAnalysisGroupDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeLiquidationAnalysisGroupVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
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
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/schemeLiquidationAnalysis")
public class SchemeLiquidationAnalysisController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeLiquidationAnalysisService projectTaskLiquidationAnalysisService;
    @Autowired
    private SchemeLiquidationAnalysisItemDao schemeLiquidationAnalysisItemDao;
    @Autowired
    private BaseService baseService;


    @ResponseBody
    @RequestMapping(value = "/getAnalysisItemList", name = "获取税率明细", method = RequestMethod.POST)
    public HttpResult getAnalysisItemList(Integer planDetailsId) {
        return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getAnalysisItemList(planDetailsId));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteItem", name = "删除一条明细", method = RequestMethod.POST)
    public HttpResult deleteCsrCalculation(Integer id) {
        try {
            schemeLiquidationAnalysisItemDao.deleteSchemeLiquidationAnalysisItem(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/removeLiquidationAnalysisGroup", name = "删除", method = RequestMethod.POST)
    public HttpResult removeLiquidationAnalysisGroup(Integer id) {
        try {
            projectTaskLiquidationAnalysisService.removeLiquidationAnalysisGroup(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/getSchemeLiquidationAnalysisGroupList", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getSchemeLiquidationAnalysisGroupList(Integer planDetailsId, Integer projectId) {
        try {
            List<SchemeLiquidationAnalysisGroupVo> schemeLiquidationAnalysisGroupList = projectTaskLiquidationAnalysisService.getSchemeLiquidationAnalysisGroupList(planDetailsId, projectId);
            return HttpResult.newCorrectResult(schemeLiquidationAnalysisGroupList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAnalysisItemListByGroupId", name = "获取税率明细", method = RequestMethod.POST)
    public HttpResult getAnalysisItemListByGroupId(Integer groupId) {
        try {
            return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getAnalysisItemListByGroupId(groupId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getGroupAndPriceVo", name = "获取面积及单价", method = RequestMethod.POST)
    public HttpResult getGroupAndPriceVoByJsonStr(String recordIds) {
        try {
            return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getGroupAndPriceVoByJsonStr(recordIds));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAnalysisGroup", name = "新增和修改变现分析", method = RequestMethod.POST)
    public HttpResult saveAnalysisGroup(String formData) {
        try {
            SchemeLiquidationAnalysisGroupDto analysisGroupDto = JSON.parseObject(formData, SchemeLiquidationAnalysisGroupDto.class);
            projectTaskLiquidationAnalysisService.addAnalysisGroupFromDto(analysisGroupDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/addLiquidationAnalysisGroup", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult addLiquidationAnalysisGroup(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        try {
            projectTaskLiquidationAnalysisService.saveLiquidationAnalysisGroup(schemeLiquidationAnalysisGroup);
            projectTaskLiquidationAnalysisService.initTaxAllocation(schemeLiquidationAnalysisGroup.getAreaId(), schemeLiquidationAnalysisGroup.getPlanDetailsId(), schemeLiquidationAnalysisGroup.getId());
            return HttpResult.newCorrectResult(schemeLiquidationAnalysisGroup);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getGroupAndPrice", method = RequestMethod.POST)
    public HttpResult getGroupAndPriceVoByString(String recordIds) {
        try {
            return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getGroupAndPriceVoByString(recordIds));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
