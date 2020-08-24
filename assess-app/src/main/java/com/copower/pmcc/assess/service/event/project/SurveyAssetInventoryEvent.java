package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyAssetInventoryEvent extends ProjectTaskEvent {
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws  Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        //反写申报记录数据的证载用途与实际用途
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        surveyAssetInventoryService.writeBackDeclareRecord(surveyAssetInventory);
    }
}
