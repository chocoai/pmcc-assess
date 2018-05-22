package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.entity.CompileReportDetailsExample;
import com.copower.pmcc.assess.dal.mapper.CompileReportDetailsMapper;
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

    public void save(CompileReportDetails compileReportDetails) {
        compileReportDetailsMapper.insertSelective(compileReportDetails);
    }

    public List<CompileReportDetails> getByPid(Integer pid) {
        CompileReportDetailsExample example = new CompileReportDetailsExample();
        example.createCriteria().andPidEqualTo(pid);
        return compileReportDetailsMapper.selectByExample(example);
    }
}
