package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
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
import java.math.BigDecimal;

/**
 * Created by zch on 2020-4-26.
 * 楼盘
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
    public ResponseEntity<byte[]> reportData(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(houseCaseSummary.getApprover())) {
            houseCaseSummary.setApprover(null);
        }
        if (StringUtils.isBlank(houseCaseSummary.getCreator())) {
            houseCaseSummary.setCreator(null);
        }
        String path = basicHouseCaseSummaryService.reportData(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
        //使用spring 二进制流下载,这样可以解决兼容性问题,并且可以很好解决乱码问题,以及控制下载流
        ResponseEntity<byte[]> responseEntity = FileUtils.createResponse(FilenameUtils.getName(path), org.apache.commons.io.FileUtils.readFileToByteArray(new File(path)));
        return responseEntity;
    }

    @RequestMapping(value = "/reportDownload", method = {RequestMethod.GET}, name = "下载查询到的报表")
    public HttpResult reportDownload(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(houseCaseSummary.getApprover())) {
            houseCaseSummary.setApprover(null);
        }
        if (StringUtils.isBlank(houseCaseSummary.getCreator())) {
            houseCaseSummary.setCreator(null);
        }
        try {
            String path = basicHouseCaseSummaryService.reportData(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
            return HttpResult.newCorrectResult(200,basicHouseCaseSummaryService.uploadFilesToFTP(path) );
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary) {
        if (StringUtils.isBlank(houseCaseSummary.getApprover())) {
            houseCaseSummary.setApprover(null);
        }
        if (StringUtils.isBlank(houseCaseSummary.getCreator())) {
            houseCaseSummary.setCreator(null);
        }
        return basicHouseCaseSummaryService.getBootstrapTableVo(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
    }

    @GetMapping(value = "/findReportApplyStatisticsList")
    public BootstrapTableVo findReportApplyStatisticsList(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary) {
        if (StringUtils.isBlank(houseCaseSummary.getApprover())) {
            houseCaseSummary.setApprover(null);
        }
        if (StringUtils.isBlank(houseCaseSummary.getCreator())) {
            houseCaseSummary.setCreator(null);
        }
        return basicHouseCaseSummaryService.findReportApplyStatisticsList(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
    }

    @GetMapping(value = "/findReportAuditStatisticsList")
    public BootstrapTableVo findReportAuditStatisticsList(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary) {
        if (StringUtils.isBlank(houseCaseSummary.getApprover())) {
            houseCaseSummary.setApprover(null);
        }
        if (StringUtils.isBlank(houseCaseSummary.getCreator())) {
            houseCaseSummary.setCreator(null);
        }
        return basicHouseCaseSummaryService.findReportAuditStatisticsList(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
    }


    @RequestMapping(value = "/reportIndex", name = "楼盘报表 index", method = {RequestMethod.GET})
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "/case/estateReportIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

}
