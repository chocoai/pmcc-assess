package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployees;
import com.copower.pmcc.assess.service.data.DataAverageWageUrbanEmployeesService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:城镇就业人员平均工资
 */
@RequestMapping(value = "/dataAverageWageUrbanEmployees")
@RestController
public class DataAverageWageUrbanEmployeesController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataAverageWageUrbanEmployeesService dataAverageWageUrbanEmployeesService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/index", name = "视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataAverageWageUrbanEmployeesView");
        return modelAndView;
    }

    @RequestMapping(value = "/getDataAverageWageUrbanEmployeesById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getDataAverageWageUrbanEmployeesById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataAverageWageUrbanEmployeesService.getDataAverageWageUrbanEmployeesById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataAverageWageUrbanEmployees", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateDataAverageWageUrbanEmployees(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DataAverageWageUrbanEmployees dataAverageWageUrbanEmployees = JSONObject.parseObject(formData,DataAverageWageUrbanEmployees.class) ;
            dataAverageWageUrbanEmployeesService.saveAndUpdateDataAverageWageUrbanEmployees(dataAverageWageUrbanEmployees, updateNull);
            return HttpResult.newCorrectResult(200, dataAverageWageUrbanEmployees);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDataAverageWageUrbanEmployees", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteDataAverageWageUrbanEmployees(String id) {
        try {
            dataAverageWageUrbanEmployeesService.deleteDataAverageWageUrbanEmployeesById(id);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(String formData) {
        try {
            return dataAverageWageUrbanEmployeesService.getBootstrapTableVo(JSONObject.parseObject(formData,DataAverageWageUrbanEmployees.class));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return dataAverageWageUrbanEmployeesService.getBootstrapTableVo(new DataAverageWageUrbanEmployees());
        }
    }

    @RequestMapping(value = "/getLoanBenchmarkInterestRateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult dataAverageWageUrbanEmployeesList(DataAverageWageUrbanEmployees dataAverageWageUrbanEmployees) {
        try {
            dataAverageWageUrbanEmployeesService.getDataAverageWageUrbanEmployeesListByQuery(dataAverageWageUrbanEmployees);
            ;
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/importDataAverageWageUrbanEmployees")
    public HttpResult importDataAverageWageUrbanEmployees(HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = dataAverageWageUrbanEmployeesService.importDataAverageWageUrbanEmployees(multipartFile);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
