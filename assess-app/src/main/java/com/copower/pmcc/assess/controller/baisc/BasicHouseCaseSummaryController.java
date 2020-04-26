package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.basic.BasicHouseCaseSummaryService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
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

    @RequestMapping(value = "/reportDownloadData", method = {RequestMethod.GET}, name = "下载查询到的报表")
    public ResponseEntity<byte[]> reportData(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = basicHouseCaseSummaryService.reportData();
        //使用spring 二进制流下载,这样可以解决兼容性问题,并且可以很好解决乱码问题,以及控制下载流
        ResponseEntity<byte[]> responseEntity = FileUtils.createResponse(FilenameUtils.getName(path), org.apache.commons.io.FileUtils.readFileToByteArray(new File(path)));
        return responseEntity;
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String startDate, String endDate, BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary houseCaseSummary) {
        return basicHouseCaseSummaryService.getBootstrapTableVo(DateUtils.convertDate(endDate), DateUtils.convertDate(startDate), areaStart, areaEnd, DateUtils.convertDate(tradingTimeStart), DateUtils.convertDate(tradingTimeEnd), houseCaseSummary);
    }


    @RequestMapping(value = "/reportIndex", name = "楼盘报表 index", method = {RequestMethod.GET})
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "/case/estateReportIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

}
