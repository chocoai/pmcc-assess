package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTaggingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateTaggingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:52
 * @Description:
 */
@Repository
public class CaseEstateTaggingDao {

    @Autowired
    private CaseEstateTaggingMapper caseEstateTaggingMapper;

    public CaseEstateTagging getCaseEstateTaggingById(Integer id)throws SQLException {
        return caseEstateTaggingMapper.selectByPrimaryKey(id);
    }

    public Integer saveCaseEstateTagging(CaseEstateTagging caseEstateTagging)throws SQLException{
        caseEstateTaggingMapper.insertSelective(caseEstateTagging);
        return caseEstateTagging.getId();
    }

    public boolean updateCaseEstateTagging(CaseEstateTagging caseEstateTagging)throws SQLException{
        return caseEstateTaggingMapper.updateByPrimaryKeySelective(caseEstateTagging)==1;
    }

    public void removeCaseEstateTagging(CaseEstateTagging caseEstateTagging)throws SQLException{
        CaseEstateTaggingExample example = new CaseEstateTaggingExample();
        MybatisUtils.convertObj2Example(caseEstateTagging, example);
        caseEstateTaggingMapper.deleteByExample(example);
    }

    public boolean deleteCaseEstateTagging(Integer id)throws SQLException{
        return  caseEstateTaggingMapper.deleteByPrimaryKey(id)==1;
    }

    public List<CaseEstateTagging> caseEstateTaggingList(CaseEstateTagging caseEstateTagging)throws SQLException{
        CaseEstateTaggingExample example = new CaseEstateTaggingExample();
        MybatisUtils.convertObj2Example(caseEstateTagging, example);
        return caseEstateTaggingMapper.selectByExample(example);
    }
    
}
