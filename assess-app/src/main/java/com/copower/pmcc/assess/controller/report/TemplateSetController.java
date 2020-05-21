package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.service.TemplateSetService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.ZtreeDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 14:50
 */
@Controller
@RequestMapping(value = "/templateSet", name = "设置评估报告模板")
public class TemplateSetController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private TemplateSetService templateSetService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseReportService baseReportService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/templateSetIndex", name = "进入报告配置页面")
    public ModelAndView templateSetIndex() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/templateSetIndex");
        List<BaseProjectClassify> projectTypeList = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE);
        modelAndView.addObject("projectTypeList", projectTypeList);
        List<BaseDataDic> entrustPurposeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrustPurposeList", entrustPurposeList);
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("loanTypeList", loanTypeList);
        List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportType", cacheDataDicList);
        modelAndView.addObject("currUserAccount", processControllerComponent.getThisUser());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCustomerTree", name = "取得客户树", method = RequestMethod.GET)
    public HttpResult queryCustomerTree() {
        try {
            List<ZtreeDto> crmTree = templateSetService.getCrmTree();
            return HttpResult.newCorrectResult(crmTree);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveTemplateData", name = "保存模板文件设置的字段内容 ", method = RequestMethod.POST)
    public HttpResult saveTemplateData(BaseReportTemplate baseReportTemplate) {
        try {
            if (baseReportTemplate.getId() != null && baseReportTemplate.getId() > 0) {
                baseReportService.updateBaseReportTemplate(baseReportTemplate);
            } else {
                baseReportService.addBaseReportTemplate(baseReportTemplate);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/startBaseReportTemplate", name = "启用一个模板定义 ", method = RequestMethod.POST)
    public HttpResult startBaseReportTemplate(Integer id) {
        try {
            baseReportService.changeBaseReportTemplate(id, 1);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/stopBaseReportTemplate", name = "停用一个模板定义 ", method = RequestMethod.POST)
    public HttpResult stopBaseReportTemplate(Integer id) {
        try {
            baseReportService.changeBaseReportTemplate(id, 0);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseReportTemplateList", name = "取得模板文件列表 ", method = RequestMethod.GET)
    public BootstrapTableVo getBaseReportTemplateList(Integer useUnit, Integer type,Integer category, Integer reportType) {
        BaseReportTemplate baseReportTemplate = new BaseReportTemplate();
        baseReportTemplate.setUseUnit(useUnit);
        baseReportTemplate.setType(type);
        baseReportTemplate.setCategory(category);
        baseReportTemplate.setReportType(reportType);
        BootstrapTableVo baseReportTemplateByExample = baseReportService.getBaseReportTemplateByExample(baseReportTemplate,null);
        return baseReportTemplateByExample;
    }

}
