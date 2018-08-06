package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportAnalysisMapper;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportAnalysisMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataReportAnalysisDao {
    @Autowired
    private DataReportAnalysisMapper dataReportAnalysisMapper;


    public boolean addReportAnalysis(DataReportAnalysis evaluationReportAnalysis) {
        return dataReportAnalysisMapper.insertSelective(evaluationReportAnalysis) == 1;
    }


    public boolean updateReportAnalysis(DataReportAnalysis evaluationReportAnalysis) {
        return dataReportAnalysisMapper.updateByPrimaryKey(evaluationReportAnalysis) == 1;
    }

    public List<DataReportAnalysis> getReportAnalysisList(String name,Integer reportAnalysisType) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if(reportAnalysisType!=null){
            criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        }
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public boolean removeReportAnalysis(Integer id) {
        return dataReportAnalysisMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisMapper.selectByPrimaryKey(id);
    }

}
