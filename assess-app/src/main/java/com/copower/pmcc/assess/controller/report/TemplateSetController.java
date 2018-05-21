package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.VwDbTableInfo;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.CrmTreeDto;
import com.copower.pmcc.assess.service.TemplateSetService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.CustomTableTypeDto;
import com.copower.pmcc.erp.api.dto.DepartmentTree;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.MenuTree;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
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
    private ControllerComponent controllerComponent;
    @Autowired
    private TemplateSetService templateSetService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/templateSetIndex", name = "进入报告配置页面")
    public ModelAndView templateSetIndex() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/base/templateSetIndex");
        List<BaseDataDic> baseDataDicsA = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrust", baseDataDicsA);
        modelAndView.addObject("firstEntrust",baseDataDicsA.get(0).getId());
        List<BaseDataDic> cacheDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportType",cacheDataDicList);
        List<KeyValueDto> baseReportTemplateTypeEnumList = BaseReportTemplateTypeEnum.getBaseReportTemplateTypeEnumList();
        modelAndView.addObject("baseReportTemplateTypeEnumList",baseReportTemplateTypeEnumList);//取得模板类型
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCustomerTree", method = RequestMethod.GET)
    public HttpResult queryCustomerTree(int pid) {

        try {
            CrmTreeDto crmTree = templateSetService.getCrmTree();
            return HttpResult.newCorrectResult(crmTree);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

}
