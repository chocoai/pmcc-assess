package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataNumberRuleDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping(value = "/numberRule")
@Controller
public class DataNumberRuleController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataNumberRuleService dataNumberRuleService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private DataNumberRuleDao dataNumberRuleDao;

    @RequestMapping(value = "/index", name = "文号规则视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataNumberRule");
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("assessProjectTypeList", projectInfoService.getAssessProjectTypeList());
        modelAndView.addObject("reportTypeList", reportTypeList);
        modelAndView.addObject("sysSymbolRuleList", erpRpcToolsService.getSysSymbolRuleDto(applicationConstant.getAppKey()));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得文号规则", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer reportType) {
        BootstrapTableVo vo = dataNumberRuleService.getList(reportType);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改文号规则", method = RequestMethod.POST)
    public HttpResult save(DataNumberRule dataNumberRule) {
        try {
            dataNumberRuleService.save(dataNumberRule);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除文号规则", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            dataNumberRuleService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getDataByProjectType", name = "取得文号规则", method = RequestMethod.POST)
    public HttpResult getDataByProjectType(String assessProjectType) {
        try {
            AssessProjectTypeEnum assessProjectTypeEnum = AssessProjectTypeEnum.getAssessProjectTypeEnumByKey(assessProjectType);
            List<DataNumberRule> ruleList = dataNumberRuleDao.getDataNumberRule(assessProjectTypeEnum.getKey(), null);
            List<DataNumberRuleVo> voList = dataNumberRuleService.getVoList(ruleList);
            return HttpResult.newCorrectResult(voList);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataNumberRuleList", name = "取得文号规则", method = RequestMethod.POST)
    public HttpResult getDataNumberRuleList(String projectType,Integer reportType) {
        try {
            List<DataNumberRule> ruleList = dataNumberRuleService.getDataNumberRuleList(projectType, reportType);
            return HttpResult.newCorrectResult(ruleList);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
