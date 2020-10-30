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
        return dataReportAnalysisMapper.updateByPrimaryKeySelective(evaluationReportAnalysis) == 1;
    }

    public List<DataReportAnalysis> getReportAnalysisList(String name, Integer type, Integer reportAnalysisType) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (type != null) {
            criteria.andMarketBackgroundTypeEqualTo(type);
        }
        if (reportAnalysisType != null) {
            criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        }
        example.setOrderByClause("sorting");
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getReportAnalysisList(String province, String city, String district, Integer reportAnalysisType, String entrustmentPurpose) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(province)) {
            criteria.andProvinceEqualTo(province);
        }
        if (StringUtils.isNotBlank(city)) {
            criteria.andCityEqualTo(city);
        }
        if (StringUtils.isNotBlank(district)) {
            criteria.andDistrictEqualTo(district);
        }
        if (reportAnalysisType != null) {
            criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        }
        if (StringUtils.isNotBlank(entrustmentPurpose)) {
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%", entrustmentPurpose));
        }
        example.setOrderByClause("sorting");
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        example.setOrderByClause("sorting");
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType, String type, String category,String entrustmentPurpose) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andReportAnalysisTypeEqualTo(reportAnalysisType);
        if(StringUtils.isNotBlank(type)){
            criteria.andTypeLike(String.format("%%%s%%",type));
        }
        if(StringUtils.isNotBlank(category)){
            criteria.andCategoryLike(String.format("%%%s%%",category));
        }
        if(StringUtils.isNotBlank(entrustmentPurpose)){
            criteria.andEntrustmentPurposeLike(String.format("%%%s%%",entrustmentPurpose));
        }
        example.setOrderByClause("sorting");
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public List<DataReportAnalysis> getDataReportAnalysisList(DataReportAnalysis dataReportAnalysis) {
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        MybatisUtils.convertObj2Example(dataReportAnalysis, example);
        example.setOrderByClause("sorting");
        return dataReportAnalysisMapper.selectByExample(example);
    }

    public boolean removeReportAnalysis(Integer id) {
        return dataReportAnalysisMapper.deleteByPrimaryKey(id) == 1;
    }

    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisMapper.selectByPrimaryKey(id);
    }

}
