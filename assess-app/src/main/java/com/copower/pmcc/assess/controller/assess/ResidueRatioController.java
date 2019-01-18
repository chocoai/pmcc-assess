package com.copower.pmcc.assess.controller.assess;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseWaterDrainVo;
import com.copower.pmcc.assess.service.basic.BasicHouseDamagedDegreeService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kings on 2019-1-17.
 */
@Controller
@RequestMapping("/residueRatio")
public class ResidueRatioController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;


    @ResponseBody
    @RequestMapping(value = "/getObserveList", name = "观察法数据", method = RequestMethod.GET)
    public BootstrapTableVo getHouseDamagedDegreeList(Integer houseId, String type) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        DataDamagedDegree degree = dataDamagedDegreeService.getCacheDamagedDegreeByFieldName(type);
        List<BasicHouseDamagedDegreeVo> list = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId, degree.getId());
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(list) ? new ArrayList<BasicHouseWaterDrainVo>(10) : list);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveResidueRatio", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveResidueRatio(String formData) {
        try {
            return HttpResult.newCorrectResult(dataBlockService.saveResidueRatio(formData));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initObserve", method = {RequestMethod.POST}, name = "保存")
    public HttpResult initObserve(Integer residueRatioId) {
        try {
            HashMap<String, String> observeDate = dataBlockService.getObserveDate(residueRatioId);
            return HttpResult.newCorrectResult(JSONObject.toJSON(observeDate));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initAgeLimit", method = {RequestMethod.POST}, name = "保存")
    public HttpResult initAgeLimit(Integer residueRatioId) {
        try {
            ToolResidueRatio residueRatio = dataBlockService.initAgeLimit(residueRatioId);
            return HttpResult.newCorrectResult(residueRatio);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }
}
