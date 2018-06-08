package com.copower.pmcc.assess.controller.csr;

import com.copower.pmcc.assess.dal.dao.csr.Sheet1Dao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.Sheet1;
import com.copower.pmcc.assess.dto.output.report.Sheet1Vo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;

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

        List<Sheet1Vo> transform = LangUtils.transform(sheet1List, o -> {
            Sheet1Vo sheet1Vo = new Sheet1Vo();
            BeanUtils.copyProperties(o, sheet1Vo);
            if (o.getAttachmentId() != null && o.getAttachmentId() > 0) {
                BaseAttachment baseAttachment = baseAttachmentService.getBaseAttachment(o.getAttachmentId());
                sheet1Vo.setAttachmentHtml(baseAttachmentService.getViewHtml(baseAttachment));
            }
            return sheet1Vo;
        });

        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<Sheet1Vo>());
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/generateTemp", name = "生成报告 ", method = RequestMethod.POST)
    public HttpResult generateTemp(String ids) {
        try {
            csrProjectInfoService.generateTemp(ids);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
