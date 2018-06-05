package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.common.enums.BaseReportDataPoolTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ReportColumns;
import com.copower.pmcc.assess.dal.entity.ReportTable;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dto.output.CrmTreeDto;
import com.copower.pmcc.assess.service.BaseReportService;
import com.copower.pmcc.assess.service.TemplateSetService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    private CommonService commonService;

    @RequestMapping(value = "/templateSetIndex", name = "进入报告配置页面")
    public ModelAndView templateSetIndex() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/templateSetIndex");
        List<BaseDataDic> baseDataDicsA = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrust", baseDataDicsA);
        modelAndView.addObject("firstEntrust", baseDataDicsA.get(0).getId());//第一个抵押评估对象
        List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportType", cacheDataDicList);
        modelAndView.addObject("firstReportType", cacheDataDicList.get(0).getId());
        List<KeyValueDto> baseReportTemplateTypeEnumList = BaseReportTemplateTypeEnum.getBaseReportTemplateTypeEnumList();
        modelAndView.addObject("baseReportTemplateTypeEnumList", baseReportTemplateTypeEnumList);//取得模板类型

        List<KeyValueDto> baseReportDataPoolTypeEnumList = BaseReportDataPoolTypeEnum.getBaseReportDataPoolTypeEnumList();
        modelAndView.addObject("baseReportDataPoolTypeEnumList", baseReportDataPoolTypeEnumList);//取得数据源的类型

        Integer key = BaseReportDataPoolTypeEnum.TEMPLATE.getKey();
        modelAndView.addObject("templateId", key);//模板类型
        modelAndView.addObject("templateTypeId", BaseReportTemplateTypeEnum.TEMPLATE.getKey());
        modelAndView.addObject("currUserAccount",commonService.thisUserAccount());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCustomerTree", name = "取得客户树", method = RequestMethod.GET)
    public HttpResult queryCustomerTree(int pid) {

        try {
            CrmTreeDto crmTree = templateSetService.getCrmTree();
            return HttpResult.newCorrectResult(crmTree);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseReportColumnsList", name = "根据数据库表ID，取得相应的字段列表 ", method = RequestMethod.GET)
    public HttpResult getBaseReportColumnsList(int tableId) {

        try {
            List<ReportColumns> baseReportColumnsList = baseReportService.getReportColumnsList(tableId);
            return HttpResult.newCorrectResult(baseReportColumnsList);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseReportTemplateById", name = "根据数据库表ID，取得相应的字段列表 ", method = RequestMethod.GET)
    public HttpResult getBaseReportTemplateById(int id) {

        try {
            ReportTemplate baseReportTemplate = baseReportService.getReportTemplateById(id);
            return HttpResult.newCorrectResult(baseReportTemplate);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseDataPool", name = "根据数据源类型取得相应的数据源 ", method = RequestMethod.GET)
    public HttpResult getBaseDataPool(int typeId, Integer customerId) {

        try {
            BaseReportDataPoolTypeEnum enumByName = BaseReportDataPoolTypeEnum.getEnumByName(typeId);
            List<KeyValueDto> keyValueDtos = new ArrayList<>();
            switch (enumByName) {
                case FILES:
                case COLUMNS: {
                    List<ReportTable> baseReportTableList = baseReportService.getReportTableList(typeId);
                    if (CollectionUtils.isNotEmpty(baseReportTableList)) {
                        keyValueDtos = LangUtils.transform(baseReportTableList, o -> {
                            KeyValueDto keyValueDto = new KeyValueDto();
                            keyValueDto.setKey(String.valueOf(o.getId()));
                            keyValueDto.setValue(o.getCnName());
                            return keyValueDto;
                        });
                    }
                    break;
                }
                case TEMPLATE: {
                    List<ReportTemplate> baseReportTemplateByTemplate = baseReportService.getReportTemplateByTemplate(customerId);
                    if (CollectionUtils.isNotEmpty(baseReportTemplateByTemplate)) {
                        keyValueDtos = LangUtils.transform(baseReportTemplateByTemplate, o -> {
                            KeyValueDto keyValueDto = new KeyValueDto();
                            keyValueDto.setKey(String.valueOf(o.getId()));
                            //keyValueDto.setValue(o.getBookmarkName());
                            return keyValueDto;
                        });
                    }
                    break;
                }
            }
            return HttpResult.newCorrectResult(keyValueDtos);

        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveTemplateData", name = "保存模板设置的字段内容 ", method = RequestMethod.POST)
    public HttpResult saveTemplateData(ReportTemplate baseReportTemplate) {
        try {
            if (baseReportTemplate.getId() != null && baseReportTemplate.getId() > 0) {
                baseReportService.updateReportTemplate(baseReportTemplate);
            } else {
                baseReportService.addReportTemplate(baseReportTemplate);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/deleteBaseReportTemplate", name = "删除一个书签定义 ", method = RequestMethod.POST)
    public HttpResult deleteBaseReportTemplate(Integer id) {
        try {
            baseReportService.deleteReportTemplate(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getBaseReportTemplateList", name = "取得模板列表 ", method = RequestMethod.GET)
    public BootstrapTableVo getBaseReportTemplateList(Integer customId, Integer entrustId, Integer reportId,Integer csType) {

        ReportTemplate baseReportTemplate = new ReportTemplate();
        baseReportTemplate.setCustomerId(customId);
        baseReportTemplate.setEntrustId(entrustId);
        baseReportTemplate.setReportTypeId(reportId);
        baseReportTemplate.setCsType(csType);
        //baseReportTemplate.setPid(0);
        BootstrapTableVo baseReportTemplateByExample = baseReportService.getReportTemplateByExample(baseReportTemplate);
        return baseReportTemplateByExample;
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseReportSubTemplateList", name = "取得子模板列表 ", method = RequestMethod.GET)
    public BootstrapTableVo getBaseReportSubTemplateList(Integer customId, Integer entrustId, Integer reportId, Integer pid) {

        ReportTemplate baseReportTemplate = new ReportTemplate();
        baseReportTemplate.setCustomerId(customId);
        baseReportTemplate.setEntrustId(entrustId);
        baseReportTemplate.setReportTypeId(reportId);
        //baseReportTemplate.setPid(pid);
        BootstrapTableVo baseReportTemplateByExample = baseReportService.getReportTemplateByExample(baseReportTemplate);
        return baseReportTemplateByExample;
    }
}
