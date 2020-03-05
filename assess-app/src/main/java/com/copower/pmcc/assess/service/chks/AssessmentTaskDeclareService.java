package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
import com.copower.pmcc.assess.proxy.face.AssessmentTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 生成申报考核任务
 * Created by wangpc on 2020/2/4.
 */
@Component(value = "assessmentTaskDeclareService")
public class AssessmentTaskDeclareService implements AssessmentTaskInterface {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ChksRpcAssessmentService chksRpcAssessmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private ProjectPlanService projectPlanService;
    private final String applyUrl = "/chksCustomerAssessmentPlanDetail/apply";

    @Override
    public void createAssessmentTask(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails) {
        if (activityId == null) {
            return;
        }
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(activityDto.getBoxId());
        DeclareRealtyHouseCert realtyHouseCert = new DeclareRealtyHouseCert();
        realtyHouseCert.setPlanDetailsId(projectPlanDetails.getId());
        realtyHouseCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyHouseCertVo> declareRealtyHouseCertVoList = declareRealtyHouseCertService.lists(realtyHouseCert);
        DeclareRealtyLandCert realtyLandCert = new DeclareRealtyLandCert();
        realtyLandCert.setPlanDetailsId(projectPlanDetails.getId());
        realtyLandCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = declareRealtyLandCertService.lists(realtyLandCert);
        DeclareRealtyRealEstateCert realtyRealEstateCert = new DeclareRealtyRealEstateCert();
        realtyRealEstateCert.setPlanDetailsId(projectPlanDetails.getId());
        realtyRealEstateCert.setEnable(DeclareTypeEnum.MasterData.getKey());
        List<DeclareRealtyRealEstateCertVo> declareRealtyRealEstateCertVoList = declareRealtyRealEstateCertService.landLevels(realtyRealEstateCert);
        int sizeTotal = declareRealtyHouseCertVoList.size() + declareRealtyLandCertVoList.size() + declareRealtyRealEstateCertVoList.size();
        if (sizeTotal == 0) {
            return;
        }
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertVoList)) {
            Iterator<DeclareRealtyHouseCertVo> houseCertVoIterator = declareRealtyHouseCertVoList.iterator();
            while (houseCertVoIterator.hasNext()) {
                DeclareRealtyHouseCertVo realtyHouseCertVo = houseCertVoIterator.next();
                saveAssessmentProjectPerformanceDto(processInsId, activityId, taskId, byExamineUser, projectInfo, projectPlanDetails, boxReDto, FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), realtyHouseCertVo.getId(), realtyHouseCertVo.getCertName());
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
            Iterator<DeclareRealtyLandCertVo> declareRealtyLandCertVoIterator = declareRealtyLandCertVoList.iterator();
            while (declareRealtyLandCertVoIterator.hasNext()) {
                DeclareRealtyLandCertVo realtyLandCertVo = declareRealtyLandCertVoIterator.next();
                saveAssessmentProjectPerformanceDto(processInsId, activityId, taskId, byExamineUser, projectInfo, projectPlanDetails, boxReDto, FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), realtyLandCertVo.getId(), realtyLandCertVo.getLandCertName());
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertVoList)) {
            ListIterator<DeclareRealtyRealEstateCertVo> realtyRealEstateCertVoListIterator = declareRealtyRealEstateCertVoList.listIterator();
            while (realtyRealEstateCertVoListIterator.hasNext()) {
                DeclareRealtyRealEstateCertVo realEstateCertVo = realtyRealEstateCertVoListIterator.next();
                saveAssessmentProjectPerformanceDto(processInsId, activityId, taskId, byExamineUser, projectInfo, projectPlanDetails, boxReDto, FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), realEstateCertVo.getId(), realEstateCertVo.getCertName());
            }
        }
    }

    private void saveAssessmentProjectPerformanceDto(String processInsId, Integer activityId, String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails, BoxReDto boxReDto, String tableName, Integer tableId, String businessKey) {
        AssessmentProjectPerformanceDto dto = new AssessmentProjectPerformanceDto();
        dto.setProcessInsId(processInsId);
        dto.setAppKey(applicationConstant.getAppKey());
        if (projectInfo != null) {
            dto.setProjectId(projectInfo.getId());
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setTaskId(taskId);
        dto.setBoxId(boxReDto.getId());
        BoxReActivityDto activityDto = bpmRpcBoxService.getBoxreActivityInfoById(activityId);
        dto.setActivityId(activityId);
        dto.setBusinessKey(businessKey);
        dto.setActivityName(activityDto.getCnName());
        dto.setSorting(activityDto.getSortMultilevel());
        dto.setByExaminePeople(byExamineUser);

        dto.setExaminePeople(commonService.thisUserAccount());

        dto.setExamineStatus(ProjectStatusEnum.RUNING.getKey());
        dto.setTableId(tableId);
        dto.setTableName(tableName);
        if (projectPlanDetails != null) {
            dto.setPlanId(projectPlanDetails.getPlanId());
            dto.setPlanDetailsId(projectPlanDetails.getId());
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            if (projectPlan != null && StringUtils.isNotBlank(projectPlan.getPlanName())) {
                dto.setPlanName(String.join("-", projectPlan.getPlanName(), projectPlanDetails.getProjectPhaseName()));
            } else {
                dto.setPlanName(projectPlanDetails.getProjectPhaseName());
            }
        }
        dto.setCreator(commonService.thisUserAccount());
        dto.setValidScore(new BigDecimal(0));
        dto.setExamineUrl(applyUrl);
        Integer id = chksRpcAssessmentService.saveAndUpdateAssessmentProjectPerformanceDto(dto, true);
        if (id != null) {
            dto.setExamineUrl(String.join("", applyUrl, "?id=", id.toString()));
            dto.setId(id);
            chksRpcAssessmentService.saveAndUpdateAssessmentProjectPerformanceDto(dto, false);
        }
    }
}
