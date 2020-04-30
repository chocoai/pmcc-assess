package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataLoanBenchmarkInterestRate;
import com.copower.pmcc.assess.service.data.DataLoanBenchmarkInterestRateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @Description:贷款基准利率
 */
@RequestMapping(value = "/dataLoanBenchmarkInterestRate")
@RestController
public class DataLoanBenchmarkInterestRateController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLoanBenchmarkInterestRateService dataLoanBenchmarkInterestRateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/index", name = "视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataLoanBenchmarkInterestRateView");
        return modelAndView;
    }

    @RequestMapping(value = "/getDataLoanBenchmarkInterestRateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getDataLoanBenchmarkInterestRateById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataLoanBenchmarkInterestRateService.getDataLoanBenchmarkInterestRateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataLoanBenchmarkInterestRate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateDataLoanBenchmarkInterestRate(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DataLoanBenchmarkInterestRate dataLoanBenchmarkInterestRate = JSONObject.parseObject(formData,DataLoanBenchmarkInterestRate.class) ;
            dataLoanBenchmarkInterestRateService.saveAndUpdateDataLoanBenchmarkInterestRate(dataLoanBenchmarkInterestRate, updateNull);
            return HttpResult.newCorrectResult(200, dataLoanBenchmarkInterestRate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDataLoanBenchmarkInterestRate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteDataLoanBenchmarkInterestRate(String id) {
        try {
            dataLoanBenchmarkInterestRateService.deleteDataLoanBenchmarkInterestRateById(id);
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(String startTime, String endTime) {
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        if (StringUtils.isNotBlank(startTime)) {
            startDate = DateUtils.convertDate(startTime, DateUtils.DATE_PATTERN);
        }
        if (StringUtils.isNotBlank(endTime)) {
            endDate = DateUtils.convertDate(endTime, DateUtils.DATE_PATTERN);
        }
        try {
            return dataLoanBenchmarkInterestRateService.getBootstrapTableVo(startDate, endDate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @RequestMapping(value = "/getLoanBenchmarkInterestRateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult dataLoanBenchmarkInterestRateList(DataLoanBenchmarkInterestRate dataLoanBenchmarkInterestRate) {
        try {
            dataLoanBenchmarkInterestRateService.getDataLoanBenchmarkInterestRateListByQuery(dataLoanBenchmarkInterestRate);
            ;
            return HttpResult.newCorrectResult(200, "");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/importDataLoanBenchmarkInterestRate")
    public HttpResult importDataLoanBenchmarkInterestRate(HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = dataLoanBenchmarkInterestRateService.importDataLoanBenchmarkInterestRate(multipartFile);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
