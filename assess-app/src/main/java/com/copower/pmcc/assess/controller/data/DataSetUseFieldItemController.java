package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItem;
import com.copower.pmcc.assess.service.data.DataSetUseFieldItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:设定用途  子表
 */
@RequestMapping(value = "/dataSetUseFieldItem")
@RestController
public class DataSetUseFieldItemController {
    @Autowired
    private DataSetUseFieldItemService dataSetUseFieldItemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getDataSetUseFieldItemById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getDataSetUseFieldItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataSetUseFieldItemService.getDataSetUseFieldItemById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataSetUseFieldItem", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateDataSetUseFieldItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DataSetUseFieldItem dataSetUseFieldItem = JSONObject.parseObject(formData,DataSetUseFieldItem.class) ;
            dataSetUseFieldItemService.saveAndUpdateDataSetUseFieldItem(dataSetUseFieldItem, updateNull);
            return HttpResult.newCorrectResult(200, dataSetUseFieldItem);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDataSetUseFieldItem", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteDataSetUseFieldItem(String id) {
        try {
            dataSetUseFieldItemService.deleteDataSetUseFieldItemById(id);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(DataSetUseFieldItem query) {
        try {
            return dataSetUseFieldItemService.getBootstrapTableVo(query);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return dataSetUseFieldItemService.getBootstrapTableVo(new DataSetUseFieldItem());
        }
    }

    @RequestMapping(value = "/getDataSetUseFieldItemListList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult getDataSetUseFieldItemListList(DataSetUseFieldItem dataSetUseFieldItem) {
        try {
            dataSetUseFieldItemService.getDataSetUseFieldItemListByQuery(dataSetUseFieldItem);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
