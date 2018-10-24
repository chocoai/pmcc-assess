package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyAssetInventoryEvent extends ProjectTaskEvent {
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private DeclareRecordService declareRecordService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        //反写申报记录数据的证载用途与实际用途
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        if (surveyAssetInventory != null) {
            List<SurveyAssetInventoryContent> contentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
            if(CollectionUtils.isNotEmpty(contentList)){
                for (SurveyAssetInventoryContent content : contentList) {
                    if(content.getInventoryContent().equals(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE)){
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveyAssetInventory.getDeclareRecordId());
                        if ((declareRecord) != null) {

                        }
                    }
                }
            }
        }
    }

}
