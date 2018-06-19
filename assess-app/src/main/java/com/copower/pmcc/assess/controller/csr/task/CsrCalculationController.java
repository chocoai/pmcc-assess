package com.copower.pmcc.assess.controller.csr.task;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.entity.CsrCalculation;
import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.assess.dto.output.project.csr.CsrCalculationVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.csr.CsrCalculationService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.redis.client.SimpleRedisStandalone;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:08
 */
@Controller
@RequestMapping(value = "/csrCalculation", name = "测算情况")
public class CsrCalculationController {
    @Autowired
    private CsrCalculationService csrCalculationService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanCalculation", name = "测算情况", method = RequestMethod.POST)
    public HttpResult saveLoanCalculation(CsrCalculation csrCalculation, Integer detailsId, String taskRemarks, String actualHours) {
        try {
            csrCalculation = csrCalculationService.saveCsrCalculation(csrCalculation, detailsId, taskRemarks, actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrCalculation.getId());
    }

    //取得页面下拉选项
    @ResponseBody
    @RequestMapping(value = "/getSelectOption", name = "取得测算情况", method = RequestMethod.GET)
    public HttpResult getSelectOption() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        //取评估方法
        List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);//
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append(String.format("\"evaluation_method\":%s", JSON.toJSONString(cacheDataDicList)));
        stringBuilder.append("}");
        Object parse = JSON.parse(stringBuilder.toString());
        return HttpResult.newCorrectResult(parse);
    }

    //取得测算情况
    @ResponseBody
    @RequestMapping(value = "/getCsrCalculation", name = "取得测算情况", method = RequestMethod.GET)
    public BootstrapTableVo getCsrCalculation(Integer borrowerId, Integer detailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrCalculationVo> csrCalculation = csrCalculationService.getCsrCalculation(borrowerId, detailsId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(csrCalculation) ? new ArrayList<CsrCalculationVo>() : csrCalculation);
        bootstrapTableVo.setTotal(page.getTotal());
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCsrCalculation", name = "删除记录", method = RequestMethod.POST)
    public HttpResult deleteCsrCalculation(Integer id) {
        try {
            csrCalculationService.deleteCsrCalculation(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
