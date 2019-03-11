package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationBasisExample;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportAnalysisMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(evaluationReportAnalysis.getId());
        return dataReportAnalysisMapper.updateByExampleSelective(evaluationReportAnalysis,example) == 1;
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

    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType,String entrustmentPurpose) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        if(reportAnalysisType!=null){
            criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        }
        if (StringUtils.isNotBlank(entrustmentPurpose)) {
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%", entrustmentPurpose));
        }
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getDataReportAnalysisList(DataReportAnalysis dataReportAnalysis) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        MybatisUtils.convertObj2Example(dataReportAnalysis, example);
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public boolean removeReportAnalysis(Integer id) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return dataReportAnalysisMapper.deleteByExample(example) == 1;
    }

    public DataReportAnalysis getReportAnalysis(Integer id) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<DataReportAnalysis> reportAnalyses = dataReportAnalysisMapper.selectByExample(example);
        return reportAnalyses.get(0);
    }

}
