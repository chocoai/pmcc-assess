package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyDetail;
import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyDetailExample;
import com.copower.pmcc.assess.dal.mapper.SurveyCaseStudyDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/17.
 */
@Repository
public class SurveyCaseStudyDetailDao {

    @Autowired
    private SurveyCaseStudyDetailMapper surveyCaseStudyDetailMapper;

    public List<SurveyCaseStudyDetail> getSurveyCaseStudyDetail(Integer planDetailsId) {
        SurveyCaseStudyDetailExample example = new SurveyCaseStudyDetailExample();
        if (planDetailsId != null) {
            example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        }
        List<SurveyCaseStudyDetail> surveyCaseStudyDetails = surveyCaseStudyDetailMapper.selectByExample(example);
        return surveyCaseStudyDetails;

    }

    public SurveyCaseStudyDetail getSingelDetail(Integer id) {
        return surveyCaseStudyDetailMapper.selectByPrimaryKey(id);
    }

    public boolean update(SurveyCaseStudyDetail surveyCaseStudyDetail) {
        int i = surveyCaseStudyDetailMapper.updateByPrimaryKeySelective(surveyCaseStudyDetail);
        return i > 0;
    }

    public boolean save(SurveyCaseStudyDetail surveyCaseStudyDetail) {
        int i = surveyCaseStudyDetailMapper.insertSelective(surveyCaseStudyDetail);
        return i > 0;
    }

    public boolean delete(Integer id) {
        int i = surveyCaseStudyDetailMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<SurveyCaseStudyDetail> getSurveyCaseStudyDetailById(Integer id) {
        SurveyCaseStudyDetailExample example = new SurveyCaseStudyDetailExample();
        example.createCriteria().andIdEqualTo(id);
        List<SurveyCaseStudyDetail> surveyCaseStudyDetails = surveyCaseStudyDetailMapper.selectByExample(example);
        return surveyCaseStudyDetails;
    }
}
