package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.stereotype.Component;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveySceneExploreEvent extends ProjectTaskEvent {

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws  Exception{
        super.processFinishExecute(processExecution);
    }


}
