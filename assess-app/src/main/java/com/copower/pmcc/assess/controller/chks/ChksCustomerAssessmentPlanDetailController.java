package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.assess.service.chks.ChksCustomerAssessmentPlanDetailService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
    private BaseService baseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ChksCustomerAssessmentPlanDetailService chksCustomerAssessmentPlanDetailService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;

    @GetMapping(value = "/apply")
    public ModelAndView apply(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/chksCustomize/apply");
        params(modelAndView, id);
        return modelAndView;
    }


    @PostMapping(value = "/saveChksCustomerAssessmentPlanDetailAndUpdate")
    public HttpResult saveChksCustomerAssessmentPlanDetailAndUpdate(String formData, @RequestParam(name = "updateNull", defaultValue = "false") boolean updateNull) {
        try {
            ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
            chksCustomerAssessmentPlanDetailService.saveAndUpdateChksCustomerAssessmentPlanDetail(chksCustomerAssessmentPlanDetail, updateNull);
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetail);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteChksCustomerAssessmentPlanDetailById")
    public HttpResult deleteChksCustomerAssessmentPlanDetailById(String id) {
        try {
            chksCustomerAssessmentPlanDetailService.deleteChksCustomerAssessmentPlanDetailById(id);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getChksCustomerAssessmentPlanDetailById")
    public HttpResult getChksCustomerAssessmentPlanDetailById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetailService.getChksCustomerAssessmentPlanDetailById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String formData) {
        ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
        return chksCustomerAssessmentPlanDetailService.getBootstrapTableVo(chksCustomerAssessmentPlanDetail);
    }

    @GetMapping(value = "/getChksCustomerAssessmentPlanDetailListByQuery")
    public HttpResult getChksCustomerAssessmentPlanDetailListByQuery(String formData) {
        try {
            ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetailService.getChksCustomerAssessmentPlanDetailListByQuery(chksCustomerAssessmentPlanDetail));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    private void params(ModelAndView modelAndView, Integer id) {
        final String targetObjectInfo = "targetObjectInfo";
        if (id == null) {
            return;
        }
        ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = chksCustomerAssessmentPlanDetailService.getChksCustomerAssessmentPlanDetailById(id);
        if (chksCustomerAssessmentPlanDetail == null) {
            return;
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), chksCustomerAssessmentPlanDetail.getTableName())) {
            chksCustomerAssessmentPlanDetail.setTypeName("房产证考核");
            modelAndView.addObject(targetObjectInfo, declareRealtyHouseCertService.getDeclareRealtyHouseCertVo(declareRealtyHouseCertService.getDeclareRealtyHouseCertById(chksCustomerAssessmentPlanDetail.getTableId())));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), chksCustomerAssessmentPlanDetail.getTableName())) {
            chksCustomerAssessmentPlanDetail.setTypeName("土地证考核");
            modelAndView.addObject(targetObjectInfo, declareRealtyLandCertService.getDeclareRealtyLandCertVo(declareRealtyLandCertService.getDeclareRealtyLandCertById(chksCustomerAssessmentPlanDetail.getTableId())));
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), chksCustomerAssessmentPlanDetail.getTableName())) {
            chksCustomerAssessmentPlanDetail.setTypeName("不动产证考核");
            modelAndView.addObject(targetObjectInfo, declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertVo(declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(chksCustomerAssessmentPlanDetail.getTableId())));
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(chksCustomerAssessmentPlanDetail.getProjectId());
        if (projectInfo != null) {
            modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfo));
        }
        boolean spotCheck = chksAssessmentProjectPerformanceService.getSpotCheck(chksCustomerAssessmentPlanDetail.getBoxId(), processControllerComponent.getThisUser());
        BoxReActivityDto spotReActivityDto = chksAssessmentProjectPerformanceService.getSpotBoxReActivityDto(chksCustomerAssessmentPlanDetail.getBoxId());
        modelAndView.addObject("spotReActivityDto", spotReActivityDto);//抽查节点
        //抽查或者巡查标识符
        modelAndView.addObject("spotCheck", spotCheck);
        modelAndView.addObject(org.apache.commons.lang3.StringUtils.uncapitalize(SysUserDto.class.getSimpleName()),processControllerComponent.getThisUserInfo()) ;
        if (spotCheck) {
            modelAndView.addObject("spotAssessmentProjectPerformanceList", chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceDtoMap(chksCustomerAssessmentPlanDetail.getBoxId(), chksCustomerAssessmentPlanDetail.getProcessInsId()));
        }
        modelAndView.addObject(StringUtils.uncapitalize(ChksCustomerAssessmentPlanDetail.class.getSimpleName()), chksCustomerAssessmentPlanDetail);
        modelAndView.addObject("boxReActivityDto", bpmRpcBoxService.getBoxreActivityInfoById(chksCustomerAssessmentPlanDetail.getActivityId()));//普通考核节点 审批
        modelAndView.addObject(StringUtils.uncapitalize(BoxReDto.class.getSimpleName()), bpmRpcBoxService.getBoxReInfoByBoxId(chksCustomerAssessmentPlanDetail.getBoxId()));
        //当前节点  可以查看的权限节点信息列表
        modelAndView.addObject("activityDtoList", chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceNext(chksCustomerAssessmentPlanDetail.getBoxId(), chksCustomerAssessmentPlanDetail.getActivityId(), null, chksAssessmentProjectPerformanceService.getSpotCheck(chksCustomerAssessmentPlanDetail.getBoxId(), processControllerComponent.getThisUser())));
    }
}
