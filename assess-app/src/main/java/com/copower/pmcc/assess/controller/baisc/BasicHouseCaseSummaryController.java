package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dto.input.basic.BasicHouseCaseSummaryParamsDto;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.basic.BasicHouseCaseSummaryService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zch on 2020-4-26.
 * 房产案例统计表
 */
@RequestMapping(value = "/basicHouseCaseSummary")
@RestController
public class BasicHouseCaseSummaryController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/reportDownloadData", method = {RequestMethod.GET}, name = "下载查询到的报表")
    public ResponseEntity<byte[]> reportData(BasicHouseCaseSummaryParamsDto paramsDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        handleParam(paramsDto);
        String path = basicHouseCaseSummaryService.reportData(paramsDto);
        //使用spring 二进制流下载,这样可以解决兼容性问题,并且可以很好解决乱码问题,以及控制下载流
        ResponseEntity<byte[]> responseEntity = FileUtils.createResponse(FilenameUtils.getName(path), org.apache.commons.io.FileUtils.readFileToByteArray(new File(path)));
        return responseEntity;
    }

    @RequestMapping(value = "/reportDownload", method = {RequestMethod.GET}, name = "下载查询到的报表")
    public HttpResult reportDownload(BasicHouseCaseSummaryParamsDto paramsDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        handleParam(paramsDto);
        try {
            String path = basicHouseCaseSummaryService.reportData(paramsDto);
            return HttpResult.newCorrectResult(200, basicHouseCaseSummaryService.uploadFilesToFTP(path));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(BasicHouseCaseSummaryParamsDto paramsDto) {
        handleParam(paramsDto);
        return basicHouseCaseSummaryService.getBootstrapTableVo(paramsDto);
    }

    @GetMapping(value = "/findReportApplyStatisticsList")
    public BootstrapTableVo findReportApplyStatisticsList(BasicHouseCaseSummaryParamsDto basicHouseCaseSummaryParamsDto) {
        handleParam(basicHouseCaseSummaryParamsDto);
        return basicHouseCaseSummaryService.findReportApplyStatisticsList(basicHouseCaseSummaryParamsDto);
    }

    @GetMapping(value = "/findReportAuditStatisticsList")
    public BootstrapTableVo findReportAuditStatisticsList(BasicHouseCaseSummaryParamsDto basicHouseCaseSummaryParamsDto) {
        handleParam(basicHouseCaseSummaryParamsDto);
        return basicHouseCaseSummaryService.findReportAuditStatisticsList(basicHouseCaseSummaryParamsDto);
    }


    @RequestMapping(value = "/reportIndex", name = "房产案例统计表 index", method = {RequestMethod.GET})
    public ModelAndView reportIndex() throws Exception {
        String view = "/case/estateReportIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    /**
     * 参数处理
     * @param basicHouseCaseSummaryParamsDto
     */
    private void handleParam(BasicHouseCaseSummaryParamsDto basicHouseCaseSummaryParamsDto) {
        if (StringUtils.isBlank(basicHouseCaseSummaryParamsDto.getHouseCaseSummary().getApprover())) {
            basicHouseCaseSummaryParamsDto.getHouseCaseSummary().setApprover(null);
        }
        if (StringUtils.isBlank(basicHouseCaseSummaryParamsDto.getHouseCaseSummary().getCreator())) {
            basicHouseCaseSummaryParamsDto.getHouseCaseSummary().setCreator(null);
        }
        //解决在当天查询的情况
        if (basicHouseCaseSummaryParamsDto.getStartDate() != null && basicHouseCaseSummaryParamsDto.getEndDate() != null){
            if (DateUtils.isSameDay(basicHouseCaseSummaryParamsDto.getStartDate(), basicHouseCaseSummaryParamsDto.getEndDate())) {
                Date startDate = basicHouseCaseSummaryParamsDto.getStartDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                Date zero = calendar.getTime();
                basicHouseCaseSummaryParamsDto.setEndDate(zero);
            }
        }
    }

}
