package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.erp.common.CommonService;
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
    
    @Autowired
    private SurveyAssetInventoryRightRecordDao surveyAssetInventoryRightRecordDao;

    @Autowired
    private CommonService commonService;

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
