package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetail;
import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChksCustomerAssessmentPlanDetailMapper {
    int countByExample(ChksCustomerAssessmentPlanDetailExample example);

    int deleteByExample(ChksCustomerAssessmentPlanDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChksCustomerAssessmentPlanDetail record);

    int insertSelective(ChksCustomerAssessmentPlanDetail record);

    List<ChksCustomerAssessmentPlanDetail> selectByExample(ChksCustomerAssessmentPlanDetailExample example);

    ChksCustomerAssessmentPlanDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChksCustomerAssessmentPlanDetail record, @Param("example") ChksCustomerAssessmentPlanDetailExample example);

    int updateByExample(@Param("record") ChksCustomerAssessmentPlanDetail record, @Param("example") ChksCustomerAssessmentPlanDetailExample example);

    int updateByPrimaryKeySelective(ChksCustomerAssessmentPlanDetail record);

    int updateByPrimaryKey(ChksCustomerAssessmentPlanDetail record);
}