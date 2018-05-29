package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.dal.dao.VwDbInfoDao;
import com.copower.pmcc.assess.dal.entity.VwDbColumnsInfo;
import com.copower.pmcc.assess.dal.entity.VwDbTableInfo;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcCustomReportService;
import com.copower.pmcc.erp.api.provider.ErpRpcMenuService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:配置基础数据维护表单或查询报表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/7
 * @time: 16:59
 */
@Controller
@RequestMapping(value = "/reportSet")
public class ReportSetController {
    @Autowired
    private ErpRpcMenuService erpRpcMenuService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpRpcCustomReportService erpRpcCustomReportService;
    @Autowired
    private VwDbInfoDao vwDbInfoDao;
    @Autowired
    private ApplicationConstant applicationConstant;

    @RequestMapping(value = "/setIndex", name = "报表及基础数据原始页")
    public ModelAndView setIndex() {
        ModelAndView modelAndView = commonService.baseView("/report/setIndex");
        List<CustomTableTypeDto> customSetType = CustomTableTypeEnum.getCustomSetType();
        StringBuilder sbHtml = new StringBuilder();
        for (CustomTableTypeDto item : customSetType) {
            sbHtml.append(String.format("<option value='%s'>%s</option>", item.getId(), item.getCnName()));
        }
        modelAndView.addObject("typeList", sbHtml.toString());
        List<MenuTree> appkeyParentSysMenu = erpRpcMenuService.getAppkeyParentSysMenu(applicationConstant.getAppKey());
        modelAndView.addObject("sysMenus", appkeyParentSysMenu);

        List<VwDbTableInfo> tableAll = vwDbInfoDao.getTableAll();
        modelAndView.addObject("tableInfo", tableAll);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getReportTableList", name = "取得报表定义表内容", method = RequestMethod.GET)
    public BootstrapTableVo getReportTableList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = erpRpcCustomReportService.getReportTableList(applicationConstant.getAppKey(), requestBaseParam.getOffset(), requestBaseParam.getLimit(),
                requestBaseParam.getSearch());
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveReportTableList", name = "保存定义表内容", method = RequestMethod.POST)
    public HttpResult saveReportTableList(ReportTableDto reportTable) {
        try {
            erpRpcCustomReportService.saveReportTableList(reportTable, applicationConstant.getAppKey());
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteReportTableList", name = "删除定义表内容", method = RequestMethod.POST)
    public HttpResult deleteReportTableList(Integer id) {
        try {
            erpRpcCustomReportService.deleteReportTableList(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getReportTableById", name = "根据ID取得相应的数据", method = RequestMethod.GET)
    public HttpResult getReportTableById(Integer id) {
        try {
            ReportTableDto reportTableById = erpRpcCustomReportService.getReportTableById(id);
            return HttpResult.newCorrectResult(reportTableById);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getTableColumns", name = "根据表编号取得表的字段信息", method = RequestMethod.GET)
    public HttpResult getTableColumns(Integer tableId, String tableName) {
        try {
            List<VwDbColumnsInfo> tableColumnsByTableName = vwDbInfoDao.getTableColumnsByTableName(tableName);

            List<DbColumnsInfoDto> dbColumnsInfoDtos = LangUtils.transform(tableColumnsByTableName, o -> {
                DbColumnsInfoDto dbColumnsInfoDto = new DbColumnsInfoDto();
                BeanUtils.copyProperties(o, dbColumnsInfoDto);
                return dbColumnsInfoDto;
            });
            List<ReportColumnsDto> tableColumns = erpRpcCustomReportService.getTableColumns(tableId, dbColumnsInfoDtos);
            return HttpResult.newCorrectResult(tableColumns);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveReportColumnList", name = "保存数据库表设置字段", method = RequestMethod.POST)
    public HttpResult saveReportColumnList(String ds, Integer tableId) {
        try {
            erpRpcCustomReportService.saveReportColumnList(ds, tableId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
