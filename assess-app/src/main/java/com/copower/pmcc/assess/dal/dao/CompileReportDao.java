package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.CompileReport;
import com.copower.pmcc.assess.dal.entity.CompileReportExample;
import com.copower.pmcc.assess.dal.mapper.CompileReportMapper;
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
    public void save(CompileReport compileReport) {
        compileReportMapper.insertSelective(compileReport);
    }

    public List<CompileReport> getByProcessInsId(String processInsId) {
        CompileReportExample example = new CompileReportExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        return compileReportMapper.selectByExample(example);
    }
}
