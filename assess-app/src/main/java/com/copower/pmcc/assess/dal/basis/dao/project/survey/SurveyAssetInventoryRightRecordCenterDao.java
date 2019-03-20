package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordCenter;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordCenterExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyAssetInventoryRightRecordCenterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/3/20 14:49
 * @description:
 */
@Repository
public class SurveyAssetInventoryRightRecordCenterDao {
    
    @Autowired
    private SurveyAssetInventoryRightRecordCenterMapper surveyAssetInventoryRightRecordCenterMapper;

    public boolean addSurveyAssetInventoryRightRecordCenter(SurveyAssetInventoryRightRecordCenter surveyAssetInventoryRightRecordCenter)throws SQLException {
        return surveyAssetInventoryRightRecordCenterMapper.insertSelective(surveyAssetInventoryRightRecordCenter) == 1;
    }

    public boolean updateSurveyAssetInventoryRightRecordCenter(SurveyAssetInventoryRightRecordCenter surveyAssetInventoryRightRecordCenter)throws SQLException{
        return surveyAssetInventoryRightRecordCenterMapper.updateByPrimaryKeySelective(surveyAssetInventoryRightRecordCenter)==1;
    }

    public boolean deleteSurveyAssetInventoryRightRecordCenterById(Integer id)throws SQLException{
        return surveyAssetInventoryRightRecordCenterMapper.deleteByPrimaryKey(id) == 1;
    }

    public SurveyAssetInventoryRightRecordCenter getSurveyAssetInventoryRightRecordCenterById(Integer id)throws SQLException{
        return surveyAssetInventoryRightRecordCenterMapper.selectByPrimaryKey(id);
    }

    public List<SurveyAssetInventoryRightRecordCenter> surveyAssetInventoryRightRecordCenterList(SurveyAssetInventoryRightRecordCenter surveyAssetInventoryRightRecordCenter){
        SurveyAssetInventoryRightRecordCenterExample example = new SurveyAssetInventoryRightRecordCenterExample();
        example.setOrderByClause("id desc");
        MybatisUtils.convertObj2Example(surveyAssetInventoryRightRecordCenter, example);
        return surveyAssetInventoryRightRecordCenterMapper.selectByExample(example);
    }
    
    
}
