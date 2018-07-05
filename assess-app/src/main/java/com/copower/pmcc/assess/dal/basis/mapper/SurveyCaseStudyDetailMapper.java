package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudyDetail;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudyDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyCaseStudyDetailMapper {
    int countByExample(SurveyCaseStudyDetailExample example);

    int deleteByExample(SurveyCaseStudyDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyCaseStudyDetail record);

    int insertSelective(SurveyCaseStudyDetail record);

    List<SurveyCaseStudyDetail> selectByExample(SurveyCaseStudyDetailExample example);

    SurveyCaseStudyDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyCaseStudyDetail record, @Param("example") SurveyCaseStudyDetailExample example);

    int updateByExample(@Param("record") SurveyCaseStudyDetail record, @Param("example") SurveyCaseStudyDetailExample example);

    int updateByPrimaryKeySelective(SurveyCaseStudyDetail record);

    int updateByPrimaryKey(SurveyCaseStudyDetail record);
}