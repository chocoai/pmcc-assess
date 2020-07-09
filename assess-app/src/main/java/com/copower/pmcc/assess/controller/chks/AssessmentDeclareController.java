package com.copower.pmcc.assess.controller.chks;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zch on 2020-1-16.
 * 评估中对考核计划的Controller
 */
@RestController
@RequestMapping(value = "/assessmentDeclare")
public class AssessmentDeclareController {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;

    @GetMapping(value = "/index")
    public ModelAndView index(Integer performanceId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/chks/assessmentDeclare");
        setModelParams(modelAndView, performanceId);
        return modelAndView;
    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(Integer performanceId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/chks/assessmentDeclare");
        setModelParams(modelAndView, performanceId);
        modelAndView.addObject("assessmentReadonly", true);
        return modelAndView;
    }

    private void setModelParams(ModelAndView modelAndView, Integer id) {
        final String targetObjectInfo = "targetObjectInfo";
        if (id == null) {
            return;
        }
        AssessmentPerformanceDto assessmentPerformanceDto = performanceService.getPerformanceById(id);
        if (assessmentPerformanceDto == null) return;

        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), assessmentPerformanceDto.getTableName())) {
            DeclareRealtyHouseCert realtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(assessmentPerformanceDto.getTableId());
            modelAndView.addObject(targetObjectInfo, declareRealtyHouseCertService.getDeclareRealtyHouseCertVo(realtyHouseCert));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), assessmentPerformanceDto.getTableName())) {
            DeclareRealtyLandCert realtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(assessmentPerformanceDto.getTableId());
            modelAndView.addObject(targetObjectInfo, declareRealtyLandCertService.getDeclareRealtyLandCertVo(realtyLandCert));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), assessmentPerformanceDto.getTableName())) {
            DeclareRealtyRealEstateCert realtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(assessmentPerformanceDto.getTableId());
            modelAndView.addObject(targetObjectInfo, declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertVo(realtyRealEstateCert));
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(assessmentPerformanceDto.getProjectId());
        if (projectInfo != null) {
            modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        }
        modelAndView.addObject("processInsId", assessmentPerformanceDto.getProcessInsId());
        modelAndView.addObject(StringUtils.uncapitalize(AssessmentPerformanceDto.class.getSimpleName()), assessmentPerformanceDto);
    }
}
