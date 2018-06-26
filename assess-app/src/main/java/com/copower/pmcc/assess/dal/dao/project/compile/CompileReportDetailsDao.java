package com.copower.pmcc.assess.dal.dao.project.compile;

import com.copower.pmcc.assess.dal.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.entity.CompileReportDetailsExample;
import com.copower.pmcc.assess.dal.mapper.CompileReportDetailsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/22.
 */
@Repository
public class CompileReportDetailsDao {

    @Autowired
    private CompileReportDetailsMapper compileReportDetailsMapper;

    public boolean add(CompileReportDetails compileReportDetails) {
        return compileReportDetailsMapper.insertSelective(compileReportDetails) > 0;
    }

    public boolean update(CompileReportDetails compileReportDetails) {
        return compileReportDetailsMapper.updateByPrimaryKeySelective(compileReportDetails) > 0;
    }

    public List<CompileReportDetails> getListByPlanDetailsId(Integer planDetailsId) {
        CompileReportDetailsExample example = new CompileReportDetailsExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return compileReportDetailsMapper.selectByExample(example);
    }

    public List<CompileReportDetails> getReportDetailsList(CompileReportDetails compileReportDetails) {
        CompileReportDetailsExample example = new CompileReportDetailsExample();
        MybatisUtils.convertObj2Example(compileReportDetails,example);
        return compileReportDetailsMapper.selectByExample(example);
    }
}
