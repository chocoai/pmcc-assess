package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;
import com.copower.pmcc.assess.service.basic.BasicBuildingPropertyServiceItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zch on 2019/7/24.
 */
@RequestMapping(value = "/basicBuildingPropertyServiceItem")
@Controller
public class BasicBuildingPropertyServiceItemController {

    @Autowired
    private BasicBuildingPropertyServiceItemService basicBuildingPropertyServiceItemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingPropertyServiceItemById", method = {RequestMethod.GET}, name = "获取")
    public HttpResult getById(Integer id) {
        BasicBuildingPropertyServiceItem basicBuildingPropertyServiceItem = null;
        try {
            if (id != null) {
                basicBuildingPropertyServiceItem = basicBuildingPropertyServiceItemService.getBasicBuildingPropertyServiceItemById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(basicBuildingPropertyServiceItem);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingPropertyServiceItemList", method = {RequestMethod.GET}, name = "列表")
    public BootstrapTableVo getBasicBuildingPropertyServiceItemList(BasicBuildingPropertyServiceItem caseBuildingPropertyServiceItem) {
        BootstrapTableVo vo = null;
        try {

            vo = basicBuildingPropertyServiceItemService.getBootstrapTableVo(caseBuildingPropertyServiceItem);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuildingPropertyServiceItemById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(String id) {
        try {
            basicBuildingPropertyServiceItemService.removeIds(FormatUtils.transformString2Integer(id));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(500, String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(200, "success");
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuildingPropertyServiceItem", method = {RequestMethod.POST}, name = "更新")
    public HttpResult save(String formData) {
        try {
            List<BasicBuildingPropertyServiceItem> itemList = JSONObject.parseArray(formData, BasicBuildingPropertyServiceItem.class);
            if (CollectionUtils.isNotEmpty(itemList)) {
                for (BasicBuildingPropertyServiceItem oo : itemList) {
                    basicBuildingPropertyServiceItemService.saveBasicBuildingPropertyServiceItem(oo);
                }
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
