package com.copower.pmcc.assess.controller.csr;

import com.copower.pmcc.assess.common.enums.BaseReportDataPoolTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.csr.Sheet1Dao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dal.entity.Sheet1;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateVo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @data: 2018/6/7
 * @time: 15:02
 */
@Controller
@RequestMapping(value = "/sheet1")
public class Sheet1Controller {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private Sheet1Dao sheet1Dao;

    @RequestMapping(value = "/index", name = "进入客户页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/sheet1/index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getSheet1List", name = "取得客户列表 ", method = RequestMethod.GET)
    public BootstrapTableVo getSheet1List() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String search = requestBaseParam.getSearch();
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
        }

        List<Sheet1> sheet1List = sheet1Dao.getSheet1List(search);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(sheet1List != null ? sheet1List : new ArrayList<Sheet1>());
        return bootstrapTableVo;
    }

}
