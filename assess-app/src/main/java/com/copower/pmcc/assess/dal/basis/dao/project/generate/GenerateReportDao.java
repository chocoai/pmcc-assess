package com.copower.pmcc.assess.dal.basis.dao.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReport;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportRecord;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportMapper;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenerateReportDao {
    @Autowired
    private GenerateReportMapper generateReportMapper;
    @Autowired
    private GenerateReportRecordMapper generateReportRecordMapper;

    //-----------生成的报告


    public GenerateReport getGenerateReport(Integer id) {
        return generateReportMapper.selectByPrimaryKey(id);
    }

    public boolean addGenerateReport(GenerateReport declareInfo) {
        int i = generateReportMapper.insert(declareInfo);
        return i > 0;
    }

    public boolean editGenerateReport(GenerateReport declareInfo) {
        int i = generateReportMapper.updateByPrimaryKeySelective(declareInfo);
        return i > 0;
    }

    public boolean deleteGenerateReport(Integer id) {
        int i = generateReportMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    //-----------生成的报告记录
    public int getGenerateReportRecordCount(Integer projectId, Integer planId) {
        GenerateReportRecordExample example = new GenerateReportRecordExample();
        GenerateReportRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId).andPlanIdEqualTo(planId);
        return generateReportRecordMapper.countByExample(example);
    }

    public List<GenerateReportRecord> getGenerateReportRecordList(Integer projectId, Integer planId) {
        GenerateReportRecordExample example = new GenerateReportRecordExample();
        GenerateReportRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        if (planId != null)
            criteria.andPlanIdEqualTo(planId);
        return generateReportRecordMapper.selectByExample(example);
    }

    public GenerateReportRecord getGenerateReportRecord(Integer id) {
        return generateReportRecordMapper.selectByPrimaryKey(id);
    }

    public boolean addGenerateReportRecord(GenerateReportRecord generateReportRecord) {
        int i = generateReportRecordMapper.insert(generateReportRecord);
        return i > 0;
    }

    public boolean editGenerateReportRecord(GenerateReportRecord generateReportRecord) {
        int i = generateReportRecordMapper.updateByPrimaryKeySelective(generateReportRecord);
        return i > 0;
    }

    public boolean deleteGenerateReportRecord(Integer id) {
        int i = generateReportRecordMapper.deleteByPrimaryKey(id);
        return i > 0;
    }
}
