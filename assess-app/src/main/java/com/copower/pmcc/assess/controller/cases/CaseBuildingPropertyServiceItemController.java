package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItem;
import com.copower.pmcc.assess.service.cases.CaseBuildingPropertyServiceItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:07
 * @Description:
 */
@RequestMapping(value = "/caseBuildingPropertyServiceItem")
@Controller
public class CaseBuildingPropertyServiceItemController {
    @Autowired
    private CaseBuildingPropertyServiceItemService caseBuildingPropertyServiceItemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingPropertyServiceItemById", method = {RequestMethod.GET}, name = "获取")
    public HttpResult getById(Integer id) {
        CaseBuildingPropertyServiceItem caseBuildingPropertyServiceItem = null;
        try {
            if (id != null) {
                caseBuildingPropertyServiceItem = caseBuildingPropertyServiceItemService.getCaseBuildingPropertyServiceItemById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingPropertyServiceItem);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingPropertyServiceItemList", method = {RequestMethod.GET}, name = "列表")
    public BootstrapTableVo getCaseBuildingPropertyServiceItemList(CaseBuildingPropertyServiceItem caseBuildingPropertyServiceItem) {
        BootstrapTableVo vo = null;
        try {
            vo = caseBuildingPropertyServiceItemService.getBootstrapTableVo(caseBuildingPropertyServiceItem);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingPropertyServiceItemById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseBuildingPropertyServiceItemService.deleteCaseBuildingPropertyServiceItemById(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuildingPropertyServiceItem", method = {RequestMethod.POST}, name = "更新")
    public HttpResult save(CaseBuildingPropertyServiceItem oo) {
        try {
            caseBuildingPropertyServiceItemService.saveCaseBuildingPropertyServiceItem(oo);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
