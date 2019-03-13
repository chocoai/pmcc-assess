package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItem;
import com.copower.pmcc.assess.dto.output.data.DataReportTemplateItemVo;
import com.copower.pmcc.assess.service.data.DataReportTemplateItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dataReportTemplateItem")
public class DataReportTemplateItemController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;


    @RequestMapping(value = "/getDataReportTemplateItemById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataReportTemplateItemVo dataReportTemplateItemVo = null;
        try {
            if (id != null) {
                dataReportTemplateItemVo = dataReportTemplateItemService.getByDataReportTemplateItemId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataReportTemplateItemVo);
    }

    @RequestMapping(value = "/getDataReportTemplateItemList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getDataReportTemplateItemList(Integer masterId) {
        BootstrapTableVo vo = null;
        try {
            if(masterId != null) {
                vo = dataReportTemplateItemService.getListVos(masterId);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteDataReportTemplateItemById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataReportTemplateItemService.deleteDataReportTemplateItem(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataReportTemplateItem", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataReportTemplateItem dataReportTemplateItem) {
        try {
            if (dataReportTemplateItem.getId() == null || dataReportTemplateItem.getId().equals(0)) {
                dataReportTemplateItem.setMasterId(0);
                dataReportTemplateItemService.addDataReportTemplateItemReturnId(dataReportTemplateItem);
            } else {
                dataReportTemplateItemService.updateDataReportTemplateItem(dataReportTemplateItem);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
