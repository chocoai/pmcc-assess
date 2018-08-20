package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudyExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyCaseStudyMapper;
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

    public boolean updateSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) {
        int i = surveyCaseStudyMapper.updateByPrimaryKeySelective(surveyCaseStudy);
        return i > 0;
    }

    public boolean addSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) {
        int i = surveyCaseStudyMapper.insertSelective(surveyCaseStudy);
        return i > 0;
    }

    public boolean deleteSurveyCaseStudy(Integer id) {
        int i = surveyCaseStudyMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudyExample example = new SurveyCaseStudyExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<SurveyCaseStudy> surveyCaseStudys = surveyCaseStudyMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(surveyCaseStudys)){
            return surveyCaseStudys.get(0);
        }
        return null;
    }

}
