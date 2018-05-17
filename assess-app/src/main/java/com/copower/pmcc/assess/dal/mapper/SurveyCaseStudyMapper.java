package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyCaseStudyMapper {
    int countByExample(SurveyCaseStudyExample example);

    int deleteByExample(SurveyCaseStudyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyCaseStudy record);

    int insertSelective(SurveyCaseStudy record);

    List<SurveyCaseStudy> selectByExample(SurveyCaseStudyExample example);

    SurveyCaseStudy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyCaseStudy record, @Param("example") SurveyCaseStudyExample example);

    int updateByExample(@Param("record") SurveyCaseStudy record, @Param("example") SurveyCaseStudyExample example);

    int updateByPrimaryKeySelective(SurveyCaseStudy record);

    int updateByPrimaryKey(SurveyCaseStudy record);
}