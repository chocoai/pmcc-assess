package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCommonService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BaseService baseService;


    /**
     * 获取房产所有调查表单
     *
     * @return
     */
    public List<KeyValueDto> getExamineFormTypeList() {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        KeyValueDto keyValueDto = new KeyValueDto();
        keyValueDto.setKey(String.valueOf(BasicApplyTypeEnum.RESIDENCE.getId()));
        keyValueDto.setValue(BasicApplyTypeEnum.RESIDENCE.getName());
        keyValueDtoList.add(keyValueDto);

        keyValueDto = new KeyValueDto();
        keyValueDto.setKey(String.valueOf(BasicApplyTypeEnum.INDUSTRY.getId()));
        keyValueDto.setValue(BasicApplyTypeEnum.INDUSTRY.getName());
        keyValueDtoList.add(keyValueDto);
        return keyValueDtoList;
    }

    /**
     * 获取查勘过程申请表信息
     *
     * @param declareId
     * @return
     */
    public List<BasicApply> getSceneExploreBasicApplyList(Integer declareId) {
        try {
            List<BasicApply> applyList = basicApplyService.getListByDeclareRecordId(declareId);
            return applyList;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return null;
        }
    }

    public BasicApplyBatch getBasicApplyBatchByApplyId(Integer applyId) {
        BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
        if (basicApply == null) return null;
        return basicApplyBatchService.getBasicApplyBatchById(basicApply.getApplyBatchId());
    }

    /**
     * 获取房产经济耐用年限
     *
     * @param basicApply
     * @param basicBuilding
     * @return
     */
    public Integer getBuildingUsableYear(BasicApply basicApply, BasicBuilding basicBuilding) {
        Integer buildingUsableYear = 0;
        if (basicApply == null || basicBuilding == null) return buildingUsableYear;
        if (BasicApplyTypeEnum.RESIDENCE.getId().equals(basicApply.getType())) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(basicBuilding.getResidenceUseYear());
            if (baseDataDic != null)
                buildingUsableYear = Integer.valueOf(baseDataDic.getRemark());
        } else if (BasicApplyTypeEnum.INDUSTRY.getId().equals(basicApply.getType())) {
            DataBuildingNewRate buildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear());
            buildingUsableYear = buildingNewRate.getDurableLife();
        }
        return buildingUsableYear;
    }

    /**
     * 修改申报记录实际用途
     *
     * @param projectPlanDetails
     */
    public void updateDeclarePracticalUse(ProjectPlanDetails projectPlanDetails) {
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(projectPlanDetails.getId());
        if (CollectionUtils.isNotEmpty(basicApplyList)) {
            for (BasicApply basicApply : basicApplyList) {
                BasicHouse house = basicHouseService.getHouseByBasicApply(basicApply);
                if (house != null && house.getPracticalUse() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(basicApply.getDeclareRecordId());
                    if (declareRecord != null) {
                        declareRecord.setPracticalUse(house.getPracticalUse());
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                    }
                }
            }
        }
    }
}
