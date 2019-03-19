package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/3/18 17:29
 * @description:
 */
@Service
public class SurveyAssetInventoryRightRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryRightRecordDao surveyAssetInventoryRightRecordDao;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;

    @Autowired
    private CommonService commonService;

    public void clear(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord){
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = this.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)){
            surveyAssetInventoryRightRecordList.stream().forEach(inventoryRightRecord -> {
                SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
                select.setInventoryRightRecordId(inventoryRightRecord.getId());
                try {
                    surveyAssetInventoryRightService.removeSurveyAssetInventoryRight(select);
                    this.deleteSurveyAssetInventoryRightRecordById(inventoryRightRecord.getId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(),e1);
                }
            });
        }
    }

    public boolean addSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord)throws Exception {
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        return surveyAssetInventoryRightRecordDao.addSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean updateSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord)throws Exception{
        return surveyAssetInventoryRightRecordDao.updateSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean deleteSurveyAssetInventoryRightRecordById(Integer id)throws Exception{
        return surveyAssetInventoryRightRecordDao.deleteSurveyAssetInventoryRightRecordById(id);
    }

    public SurveyAssetInventoryRightRecord getSurveyAssetInventoryRightRecordById(Integer id)throws Exception{
        return surveyAssetInventoryRightRecordDao.getSurveyAssetInventoryRightRecordById(id);
    }

    public List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord){
        return surveyAssetInventoryRightRecordDao.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
    }
    
}
