package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisJudgeDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeLiquidationAnalysisGroupDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeLiquidationAnalysisGroupVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeLiquidationAnalysisJudgeDao schemeLiquidationAnalysisJudgeDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;


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
    public HttpResult getGroupAndPriceVoByJsonStr(String judgeObjectIds, Integer areaGroupId, Integer groupId) {
        try {
            return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getGroupAndPriceVoByJsonStr(judgeObjectIds, areaGroupId, groupId));
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
    public HttpResult getGroupAndPriceVoByString(Integer groupId) {
        try {
            return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getGroupAndPriceVoByString(groupId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSchemeJudgeObjList", method = {RequestMethod.GET}, name = "获取启用的估价对象列表")
    public BootstrapTableVo getDataBlockList(String name, String certName, String seat, String ownership, Integer areaGroupId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectListByQuery(name, certName, seat, ownership, areaGroupId, null);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(judgeObjectList) ? new ArrayList<SchemeJudgeObject>() : judgeObjectList);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getSchemeLiquidationJudgeList", method = {RequestMethod.GET}, name = "获取启用的估价对象列表")
    public BootstrapTableVo getSchemeLiquidationJudgeList(String name, String certName, String seat, String ownership, Integer areaGroupId, Integer groupId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<Integer> ids = projectTaskLiquidationAnalysisService.getSchemeJudgeObjIds(groupId);
        if (CollectionUtils.isEmpty(ids)) {
            //防止查出所有数据
            ids.add(0);
        }
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectListByQuery(name, certName, seat, ownership, areaGroupId, ids);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(judgeObjectList) ? new ArrayList<SchemeJudgeObject>() : judgeObjectList);
        return vo;
    }
}
