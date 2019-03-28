package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItem;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataPropertyServiceItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/dataPropertyServiceItem")
public class DataPropertyServiceItemController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @RequestMapping(value = "/getDataPropertyServiceItemById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataPropertyServiceItemVo dataPropertyServiceItemVo = null;
        try {
            if (id != null) {
                dataPropertyServiceItemVo = dataPropertyServiceItemService.getByDataPropertyServiceItemId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataPropertyServiceItemVo);
    }

    @RequestMapping(value = "/getDataPropertyServiceItemList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getDataPropertyServiceItemList(Integer masterId) {
        BootstrapTableVo vo = null;
        try {
            if (masterId != null) {
                vo = dataPropertyServiceItemService.getListVos(masterId);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteDataPropertyServiceItemById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataPropertyServiceItemService.deleteDataPropertyServiceItem(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataPropertyServiceItem", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(String formData) {
        DataPropertyServiceItem dataPropertyServiceItem = JSON.parseObject(formData, DataPropertyServiceItem.class);
        try {
            if (dataPropertyServiceItem.getId() == null || dataPropertyServiceItem.getId().equals(0)) {
                dataPropertyServiceItemService.addDataPropertyServiceItemReturnId(dataPropertyServiceItem);
            } else {
                dataPropertyServiceItemService.updateDataPropertyServiceItem(dataPropertyServiceItem);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @RequestMapping(value = "/getServiceContentList", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getServiceContentList(Integer pid) {
        try {
            List<BaseDataDic> cacheDataDicListByPid = baseDataDicService.getCacheDataDicListByPid(pid);
            return HttpResult.newCorrectResult(cacheDataDicListByPid);
        } catch (Exception e) {
            logger.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
