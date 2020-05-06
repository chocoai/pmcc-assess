package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesItem;
import com.copower.pmcc.assess.service.data.DataAverageWageUrbanEmployeesItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:城镇就业人员平均工资
 */
@RequestMapping(value = "/dataAverageWageUrbanEmployeesItem")
@RestController
public class DataAverageWageUrbanEmployeesItemController {
    @Autowired
    private DataAverageWageUrbanEmployeesItemService dataAverageWageUrbanEmployeesItemService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getDataAverageWageUrbanEmployeesItemById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getDataAverageWageUrbanEmployeesItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataAverageWageUrbanEmployeesItemService.getDataAverageWageUrbanEmployeesItemById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataAverageWageUrbanEmployeesItem", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateDataAverageWageUrbanEmployeesItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DataAverageWageUrbanEmployeesItem dataAverageWageUrbanEmployeesItem = JSONObject.parseObject(formData,DataAverageWageUrbanEmployeesItem.class) ;
            dataAverageWageUrbanEmployeesItemService.saveAndUpdateDataAverageWageUrbanEmployeesItem(dataAverageWageUrbanEmployeesItem, updateNull);
            return HttpResult.newCorrectResult(200, dataAverageWageUrbanEmployeesItem);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDataAverageWageUrbanEmployeesItem", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteDataAverageWageUrbanEmployeesItem(String id) {
        try {
            dataAverageWageUrbanEmployeesItemService.deleteDataAverageWageUrbanEmployeesItemById(id);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(DataAverageWageUrbanEmployeesItem query) {
        try {
            return dataAverageWageUrbanEmployeesItemService.getBootstrapTableVo(query);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return dataAverageWageUrbanEmployeesItemService.getBootstrapTableVo(new DataAverageWageUrbanEmployeesItem());
        }
    }

    @RequestMapping(value = "/getLoanBenchmarkInterestRateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult dataAverageWageUrbanEmployeesItemList(DataAverageWageUrbanEmployeesItem dataAverageWageUrbanEmployeesItem) {
        try {
            dataAverageWageUrbanEmployeesItemService.getDataAverageWageUrbanEmployeesItemListByQuery(dataAverageWageUrbanEmployeesItem);
            ;
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/importDataAverageWageUrbanEmployeesItem")
    public HttpResult importDataAverageWageUrbanEmployeesItem(HttpServletRequest request ,DataAverageWageUrbanEmployeesItem dataAverageWageUrbanEmployeesItem){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = dataAverageWageUrbanEmployeesItemService.importDataAverageWageUrbanEmployeesItem(multipartFile ,dataAverageWageUrbanEmployeesItem);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
