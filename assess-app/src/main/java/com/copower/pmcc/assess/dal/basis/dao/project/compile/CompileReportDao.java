package com.copower.pmcc.assess.dal.basis.dao.project.compile;

import com.copower.pmcc.assess.dal.basis.entity.CompileReport;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportExample;
import com.copower.pmcc.assess.dal.basis.mapper.CompileReportMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/22.
 */
@Repository
public class CompileReportDao {
    @Autowired
    private CompileReportMapper compileReportMapper;

    public void addCompileReport(CompileReport compileReport) {
        compileReportMapper.insertSelective(compileReport);
    }

    public void updateCompileReport(CompileReport compileReport) {
        compileReportMapper.updateByPrimaryKeySelective(compileReport);
    }

    public CompileReport getCompileReportByPlanDetailsId(Integer planDetailsId) {
        CompileReportExample example = new CompileReportExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        List<CompileReport> compileReports = compileReportMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(compileReports)) return null;
        return compileReports.get(0);
    }


}
