package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryRightRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/3/18 17:31
 * @description:
 */
@Repository
public class SurveyAssetInventoryRightRecordDao {

    @Autowired
    private SurveyAssetInventoryRightRecordMapper surveyAssetInventoryRightRecordMapper;

    public boolean addSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord)throws SQLException{
        return surveyAssetInventoryRightRecordMapper.insertSelective(surveyAssetInventoryRightRecord) == 1;
    }

    public boolean updateSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord)throws SQLException{
        return surveyAssetInventoryRightRecordMapper.updateByPrimaryKeySelective(surveyAssetInventoryRightRecord)==1;
    }

    public boolean deleteSurveyAssetInventoryRightRecordById(Integer id)throws SQLException{
        return surveyAssetInventoryRightRecordMapper.deleteByPrimaryKey(id) == 1;
    }

    public SurveyAssetInventoryRightRecord getSurveyAssetInventoryRightRecordById(Integer id)throws SQLException{
        return surveyAssetInventoryRightRecordMapper.selectByPrimaryKey(id);
    }

    public List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord){
        SurveyAssetInventoryRightRecordExample example = new SurveyAssetInventoryRightRecordExample();
        example.setOrderByClause("id desc");
        MybatisUtils.convertObj2Example(surveyAssetInventoryRightRecord, example);
        return surveyAssetInventoryRightRecordMapper.selectByExample(example);
    }
}
