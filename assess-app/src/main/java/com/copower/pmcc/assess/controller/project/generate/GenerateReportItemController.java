package com.copower.pmcc.assess.controller.project.generate;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItem;
import com.copower.pmcc.assess.service.project.generate.GenerateReportItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:委估对象item 属于估价对象分组
 */
@RequestMapping(value = "/generateReportItem")
@RestController
public class GenerateReportItemController {
    @Autowired
    private GenerateReportItemService generateReportItemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getGenerateReportItemById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getGenerateReportItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, generateReportItemService.getGenerateReportItemById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateGenerateReportItem", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateGenerateReportItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            GenerateReportItem generateReportItem = JSONObject.parseObject(formData, GenerateReportItem.class);
            generateReportItemService.saveAndUpdateGenerateReportItem(generateReportItem, updateNull);
            return HttpResult.newCorrectResult(200, generateReportItem);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateGenerateReportItemArray", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateGenerateReportItemArray(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            List<GenerateReportItem> generateReportItems = JSONObject.parseArray(formData, GenerateReportItem.class);
            for (GenerateReportItem generateReportItem : generateReportItems) {
                generateReportItemService.saveAndUpdateGenerateReportItem(generateReportItem, updateNull);
            }
            return HttpResult.newCorrectResult(200, generateReportItems);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteGenerateReportItem", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteGenerateReportItem(String id) {
        try {
            generateReportItemService.deleteGenerateReportItemById(id);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(GenerateReportItem query) {
        try {
            return generateReportItemService.getBootstrapTableVo(query);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return generateReportItemService.getBootstrapTableVo(new GenerateReportItem());
        }
    }

    @GetMapping(value = "/getGenerateReportItemByJudgeObjectIds")
    public HttpResult getGenerateReportItemByJudgeObjectIds(Integer masterId,String ids){
        try {
            return HttpResult.newCorrectResult(200, generateReportItemService.getGenerateReportItemByJudgeObjectIds(masterId, FormatUtils.transformString2Integer(ids)));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getGenerateReportItemList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult getGenerateReportItemList(GenerateReportItem generateReportItem) {
        try {
            return HttpResult.newCorrectResult(200, generateReportItemService.getGenerateReportItemListByQuery(generateReportItem));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


}
