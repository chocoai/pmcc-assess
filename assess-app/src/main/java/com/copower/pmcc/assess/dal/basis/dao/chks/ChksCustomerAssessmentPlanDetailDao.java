package com.copower.pmcc.assess.dal.basis.dao.chks;

import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetail;
import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.ChksCustomerAssessmentPlanDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-16.
 */
@Repository
public class ChksCustomerAssessmentPlanDetailDao {

    @Autowired
    private ChksCustomerAssessmentPlanDetailMapper mapper;

    public boolean updateChksCustomerAssessmentPlanDetail(ChksCustomerAssessmentPlanDetail oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteChksCustomerAssessmentPlanDetailById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteChksCustomerAssessmentPlanDetailByIds(List<Integer> ids){
        ChksCustomerAssessmentPlanDetailExample example = new ChksCustomerAssessmentPlanDetailExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public ChksCustomerAssessmentPlanDetail getChksCustomerAssessmentPlanDetailById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveChksCustomerAssessmentPlanDetail(ChksCustomerAssessmentPlanDetail oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<ChksCustomerAssessmentPlanDetail> getChksCustomerAssessmentPlanDetailByIds(List<Integer> ids){
        ChksCustomerAssessmentPlanDetailExample example = new ChksCustomerAssessmentPlanDetailExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<ChksCustomerAssessmentPlanDetail> getChksCustomerAssessmentPlanDetailListByExample(ChksCustomerAssessmentPlanDetail oo){
        ChksCustomerAssessmentPlanDetailExample example = new ChksCustomerAssessmentPlanDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
    
}
