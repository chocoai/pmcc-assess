package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateSurveyRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSurveyRecord;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查勘记录表
 */
@Service
public class BasicEstateSurveyRecordService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateSurveyRecordDao basicEstateSurveyRecordDao;

    public BasicEstateSurveyRecord initBasicEstateSurveyRecord(Integer houseId){
        BasicEstateSurveyRecord query = new BasicEstateSurveyRecord();
        query.setHouseId(houseId);
        List<BasicEstateSurveyRecord> basicEstateSurveyRecordList = getBasicEstateSurveyRecordList(query);
        if (CollectionUtils.isNotEmpty(basicEstateSurveyRecordList)){
            return basicEstateSurveyRecordList.get(0) ;
        }else {
            query.setCreator(commonService.thisUserAccount());
            addBasicEstateSurveyRecord(query) ;
            return query;
        }
    }

    public BasicEstateSurveyRecord getEstateSurveyRecordByHouseId(Integer houseId){
        if (houseId == null){
            return null;
        }
        BasicEstateSurveyRecord query = new BasicEstateSurveyRecord();
        query.setHouseId(houseId);
        List<BasicEstateSurveyRecord> basicEstateSurveyRecordList = getBasicEstateSurveyRecordList(query);
        if (CollectionUtils.isNotEmpty(basicEstateSurveyRecordList)){
            return basicEstateSurveyRecordList.get(0) ;
        }
        return null;
    }

    public void deleteBasicEstateSurveyRecordByHouseId(Integer houseId){
        basicEstateSurveyRecordDao.deleteBasicEstateSurveyRecordByHouseId(houseId);
    }

    public BasicEstateSurveyRecord getBasicEstateSurveyRecordById(Integer id){
        return basicEstateSurveyRecordDao.getBasicEstateSurveyRecordById(id) ;
    }

    public Integer addBasicEstateSurveyRecord(BasicEstateSurveyRecord basicEstateSurveyRecord){
        return basicEstateSurveyRecordDao.addBasicEstateSurveyRecord(basicEstateSurveyRecord) ;
    }

    public boolean updateBasicEstateSurveyRecord(BasicEstateSurveyRecord basicEstateSurveyRecord, boolean updateNull){
        return basicEstateSurveyRecordDao.updateBasicEstateSurveyRecord(basicEstateSurveyRecord, updateNull) ;
    }

    public boolean deleteBasicEstateSurveyRecord(Integer id){
        return basicEstateSurveyRecordDao.deleteBasicEstateSurveyRecord(id) ;
    }

    public List<BasicEstateSurveyRecord> getBasicEstateSurveyRecordList(BasicEstateSurveyRecord basicEstateSurveyRecord){
        return basicEstateSurveyRecordDao.getBasicEstateSurveyRecordList(basicEstateSurveyRecord) ;
    }

    public Long getCountByHouseId(Integer houseId){
        return basicEstateSurveyRecordDao.getCountByHouseId(houseId) ;
    }

}
