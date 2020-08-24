package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyAssetInfoEvent extends ProjectTaskEvent {
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws  Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        //反写申报记录数据的证载用途与实际用途
        surveyAssetInfoService.writeBackDeclareRecord(processExecution.getProcessInstanceId());
    }

}
