package com.copower.pmcc.assess.dal.dao.project.suvey;

import com.copower.pmcc.assess.dal.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyExample;
import com.copower.pmcc.assess.dal.mapper.SurveyCaseStudyMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Repository
public class SurveyCaseStudyDao {
    @Autowired
    private SurveyCaseStudyMapper surveyCaseStudyMapper;

    public boolean update(SurveyCaseStudy surveyCaseStudyDto) {
        int i = surveyCaseStudyMapper.updateByPrimaryKeySelective(surveyCaseStudyDto);
        return i > 0;
    }

    public boolean save(SurveyCaseStudy surveyCaseStudyDto) {
        int i = surveyCaseStudyMapper.insertSelective(surveyCaseStudyDto);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyCaseStudyMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudyExample example = new SurveyCaseStudyExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        example.setOrderByClause(" id ASC");
        List<SurveyCaseStudy> surveyCaseStudys = surveyCaseStudyMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyCaseStudys)){
            return surveyCaseStudys.get(0);
        }
        return null;
    }

    public List<SurveyCaseStudy> getByDeclareRecordId(Integer declareRecordId) {
        SurveyCaseStudyExample example = new SurveyCaseStudyExample();
        example.createCriteria().andDeclareRecordIdEqualTo(declareRecordId);
        return surveyCaseStudyMapper.selectByExample(example);
    }
}
