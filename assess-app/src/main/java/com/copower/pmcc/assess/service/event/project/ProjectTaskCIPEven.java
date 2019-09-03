package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveySceneExplore;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveySceneExploreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectTaskCIPEven extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicHouseService basicHouseService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processExecution.getProcessInstanceId());
            BasicHouse house = basicHouseService.getBasicHouseById(basicApply.getBasicHouseId());
            //查勘直接时实际用途保存到申报记录
            SurveySceneExplore surveySceneExplore = surveySceneExploreService.getSurveySceneExplore(processExecution.getProcessInstanceId());
            if (surveySceneExplore != null) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveySceneExplore.getDeclareId());
                if (house.getPracticalUse() != null) {
                    declareRecord.setPracticalUse(baseDataDicService.getNameById(house.getPracticalUse()));
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                }
            }
        }
    }
}
