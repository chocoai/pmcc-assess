package com.copower.pmcc.assess.controller.report;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcCustomReportService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/6
 * @time: 14:29
 */
@Controller
@RequestMapping(value = "/reportPage")
public class ReportPageController {
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private ErpRpcCustomReportService erpRpcCustomReportService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;

    @ResponseBody
    @RequestMapping(value = "/getTableInfoColumns", name = "根据表名和编号取得相应的显示字段", method = RequestMethod.GET)
    public HttpResult getTableInfoColumns(Integer tableId) {
        List<ReportColumnsDto> reportColumnsDtos = erpRpcCustomReportService.getColumnsByTableId(tableId, false);
        List<KeyValueDto> keyValueDtos = LangUtils.transform(reportColumnsDtos, reportColumns -> {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(reportColumns.getColumnsName());
            keyValueDto.setValue(reportColumns.getColumnsCnName());
            return keyValueDto;
        });
        return HttpResult.newCorrectResult(keyValueDtos);
    }

    @RequestMapping(value = "/pageIndex", name = "报表及基础数据原始页")
    public ModelAndView pageIndex(Integer menuId) {
        ModelAndView modelAndView = commonService.baseView("/report/pageIndex");
        ReportTableDto reportTableDto = erpRpcCustomReportService.getReportTableByMenuId(menuId);
        modelAndView.addObject("tableName", reportTableDto.getTableName());
        modelAndView.addObject("tableId", reportTableDto.getId());
        modelAndView.addObject("isBaseData", erpRpcCustomReportService.getReportTableByMenuId(menuId).getReportType().equals(1) ? "1" : "0");
        modelAndView.addObject("html", erpRpcCustomReportService.html(reportTableDto.getId()));
        List<ReportColumnsDto> reportColumnsDtos = erpRpcCustomReportService.getColumnsByTableId(reportTableDto.getId(), true);
        if (CollectionUtils.isNotEmpty(reportColumnsDtos)) {
            StringBuilder opionHtml = new StringBuilder();
            for (ReportColumnsDto item : reportColumnsDtos) {
                opionHtml.append(String.format("<option value='%s' columnsType='%s' >%s</option>", item.getColumnsName(),

                        item.getTypeId(), item.getColumnsCnName()));
            }
            modelAndView.addObject("opionHtml", opionHtml);
        }

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getTableData", name = "取得表数据", method = RequestMethod.GET)
    public BootstrapTableVo getTableData(Integer limit, Integer offset, String tableName, Integer tableId, String pageSearch) {
        KeyValueDto userAccountDataRole = bpmRpcToolsService.getUserAccountDataRole(commonService.thisUserAccount());
        String dataRole = JSON.toJSONString(userAccountDataRole);
        SQLStringVo tableDataSql = erpRpcCustomReportService.getTableDataSql(limit, offset, tableName, tableId, pageSearch, dataRole);
        List<Map> countMap = ddlMySqlAssist.customTableSelect(tableDataSql.getCountSql());
        List<Map> dataMap = ddlMySqlAssist.customTableSelect(tableDataSql.getDataSql());

        List<KeyValueDto> selectSourceSql = erpRpcCustomReportService.getSelectSourceSql(dataMap, tableId);

        List<KeyValueDto> keyValueDtos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(selectSourceSql)) {
            for (KeyValueDto item : selectSourceSql) {
                List<Map> maps = ddlMySqlAssist.customTableSelect(item.getValue());
                String string = maps.get(0).get("name").toString();
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(item.getKey());
                keyValueDto.setExplain(item.getExplain());
                keyValueDto.setValue(string);
                keyValueDtos.add(keyValueDto);
            }
        }

        return erpRpcCustomReportService.getTableData(countMap, dataMap, keyValueDtos, tableId);
    }

    @ResponseBody
    @RequestMapping(value = "/saveTableData", name = "保存数据", method = RequestMethod.POST)
    public HttpResult saveTableData(String ds) {
        try {
            String saveTableDataSQL = erpRpcCustomReportService.getSaveTableDataSQL(ds);
            ddlMySqlAssist.customTableDdlInsert(saveTableDataSQL);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTableData", name = "删除数据", method = RequestMethod.POST)
    public HttpResult deleteTableData(Integer id, String tableName) {
        try {
            String deleteTableDataSQL = erpRpcCustomReportService.getDeleteTableDataSQL(id, tableName);
            ddlMySqlAssist.customTableDdl(deleteTableDataSQL);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getTableDataById", name = "提取单个数据", method = RequestMethod.GET)
    public HttpResult getTableDataById(Integer id, Integer tableId, String tableName) {
        try {
            String tableDataByIdSQL = erpRpcCustomReportService.getTableDataByIdSQL(id, tableId, tableName);
            List<Map> maps = ddlMySqlAssist.customTableSelect(tableDataByIdSQL);
            List<CustomFormElementValueDto> customFormElementValueDtos = erpRpcCustomReportService.getTableDataById(maps, tableId);
            return HttpResult.newCorrectResult(customFormElementValueDtos);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getSelectSource", name = "根据表编号和字段，取得字段的数据源", method = RequestMethod.GET)
    public HttpResult getSelectSource(Integer tableId, String columnsName, String typeSel) {
        if (StringUtils.isNotBlank(typeSel)) {
            if (typeSel.equals("input")) {
                columnsName = FormatUtils.camelToUnderline(columnsName);
            }
        }
        String selectSourceSQL = erpRpcCustomReportService.getSelectSourceSQL(tableId, columnsName);

        try {
            List<KeyValueDto> keyValueDtos = new ArrayList<>();
            List<Map> maps = ddlMySqlAssist.customTableSelect(selectSourceSQL);
            if (CollectionUtils.isNotEmpty(maps)) {

                for (int i = 0; i < maps.size(); i++) {
                    Map sqlmap = maps.get(i);
                    KeyValueDto keyValueDto = new KeyValueDto();
                    keyValueDto.setKey(String.valueOf(sqlmap.get("id")));
                    keyValueDto.setValue(String.valueOf(sqlmap.get("name")));
                    keyValueDtos.add(keyValueDto);
                }
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
