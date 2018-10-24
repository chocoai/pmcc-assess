package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyAssetInventoryEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        //反写申报记录数据的证载用途与实际用途
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        if (surveyAssetInventory != null) {
            List<SurveyAssetInventoryContent> contentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE);
            if (CollectionUtils.isNotEmpty(contentList) && baseDataDic != null) {
                for (SurveyAssetInventoryContent content : contentList) {
                    if (content.getInventoryContent().equals(baseDataDic.getId())) {
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveyAssetInventory.getDeclareRecordId());
                        if (declareRecord != null) {
                            declareRecord.setCertUse(content.getRegistration());
                            declareRecord.setPracticalUse(content.getActual());
                            try {
                                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                            } catch (BusinessException e) {
                                logger.error(e.getMessage(), e);
                            }
                        }
                    }
                }
            }
        }
    }

}
