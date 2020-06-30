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
@RequestMapping(value = "/chksCustomerAssessmentPlanDetail")
public class ChksCustomerAssessmentPlanDetailController {
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

    @GetMapping(value = "/apply")
    public ModelAndView apply(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/chksCustomize/apply");
        params(modelAndView, id);
        return modelAndView;
    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/chksCustomize/detail");
        params(modelAndView, id);
        return modelAndView;
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String formData) {
        return new BootstrapTableVo() ;
    }


    private void params(ModelAndView modelAndView, Integer id) {
        final String targetObjectInfo = "targetObjectInfo";
        if (id == null) {
            return;
        }
        AssessmentPerformanceDto assessmentProjectPerformanceDto = performanceService.getPerformanceById(id) ;
        if (assessmentProjectPerformanceDto == null) {
            return;
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), assessmentProjectPerformanceDto.getTableName())) {
            modelAndView.addObject(targetObjectInfo, declareRealtyHouseCertService.getDeclareRealtyHouseCertVo(declareRealtyHouseCertService.getDeclareRealtyHouseCertById(assessmentProjectPerformanceDto.getTableId())));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), assessmentProjectPerformanceDto.getTableName())) {
            modelAndView.addObject(targetObjectInfo, declareRealtyLandCertService.getDeclareRealtyLandCertVo(declareRealtyLandCertService.getDeclareRealtyLandCertById(assessmentProjectPerformanceDto.getTableId())));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), assessmentProjectPerformanceDto.getTableName())) {
            modelAndView.addObject(targetObjectInfo, declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertVo(declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(assessmentProjectPerformanceDto.getTableId())));
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(assessmentProjectPerformanceDto.getProjectId());
        if (projectInfo != null) {
            modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        }
        modelAndView.addObject("processInsId",assessmentProjectPerformanceDto.getProcessInsId());
        modelAndView.addObject(org.apache.commons.lang3.StringUtils.uncapitalize(SysUserDto.class.getSimpleName()),processControllerComponent.getThisUserInfo()) ;
        modelAndView.addObject(StringUtils.uncapitalize(AssessmentPerformanceDto.class.getSimpleName()), assessmentProjectPerformanceDto);

    }
}
