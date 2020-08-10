package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSurveyRecord;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSurveyRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateSurveyRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicEstateSurveyRecordDao {

    @Autowired
    private BasicEstateSurveyRecordMapper basicEstateSurveyRecordMapper;

    public BasicEstateSurveyRecord getBasicEstateSurveyRecordById(Integer id) {
        return basicEstateSurveyRecordMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstateSurveyRecord(BasicEstateSurveyRecord basicEstateSurveyRecord) {
        basicEstateSurveyRecordMapper.insertSelective(basicEstateSurveyRecord);
        return basicEstateSurveyRecord.getId();
    }

    public boolean updateBasicEstateSurveyRecord(BasicEstateSurveyRecord basicEstateSurveyRecord, boolean updateNull) {
        return updateNull ? basicEstateSurveyRecordMapper.updateByPrimaryKey(basicEstateSurveyRecord) == 1 : basicEstateSurveyRecordMapper.updateByPrimaryKeySelective(basicEstateSurveyRecord) == 1;
    }

    public boolean deleteBasicEstateSurveyRecord(Integer id) {
        BasicEstateSurveyRecord basicEstateSurveyRecord = getBasicEstateSurveyRecordById(id);
        if (basicEstateSurveyRecord == null) return false;
        basicEstateSurveyRecord.setBisDelete(false);
        return basicEstateSurveyRecordMapper.updateByPrimaryKeySelective(basicEstateSurveyRecord) == 1;
    }

    public List<BasicEstateSurveyRecord> getBasicEstateSurveyRecordIds(List<Integer> ids) {
        BasicEstateSurveyRecordExample example = new BasicEstateSurveyRecordExample();
        BasicEstateSurveyRecordExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        criteria.andIdIn(ids);
        return basicEstateSurveyRecordMapper.selectByExample(example);
    }

    public List<BasicEstateSurveyRecord> getBasicEstateSurveyRecordList(BasicEstateSurveyRecord basicEstateSurveyRecord) {
        BasicEstateSurveyRecordExample example = new BasicEstateSurveyRecordExample();
        BasicEstateSurveyRecordExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateSurveyRecord, criteria);
        return basicEstateSurveyRecordMapper.selectByExample(example);
    }

    public void deleteBasicEstateSurveyRecordByHouseId(Integer houseId){
        BasicEstateSurveyRecord basicEstateSurveyRecord = new BasicEstateSurveyRecord();
        basicEstateSurveyRecord.setBisDelete(true);
        BasicEstateSurveyRecordExample example = new BasicEstateSurveyRecordExample();
        BasicEstateSurveyRecordExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        basicEstateSurveyRecordMapper.updateByExample(basicEstateSurveyRecord,example) ;
    }

    public Long getCountByHouseId(Integer houseId){
        BasicEstateSurveyRecordExample example = new BasicEstateSurveyRecordExample();
        BasicEstateSurveyRecordExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        return basicEstateSurveyRecordMapper.countByExample(example);
    }


}
