package com.copower.pmcc.assess.dal.dao.data;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisExample;
import com.copower.pmcc.assess.dal.mapper.DataReportAnalysisMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataReportAnalysisDao {

    @Autowired
    private DataReportAnalysisMapper dataReportAnalysisMapper;

    public boolean addDataReportAnalysis(DataReportAnalysis dataReportAnalysis) {
        int flag = dataReportAnalysisMapper.insert(dataReportAnalysis);
        return flag > 0;
    }

    public int save(DataReportAnalysis obj){
        dataReportAnalysisMapper.insertSelective(obj);
        return obj.getId();
    }

    public boolean update(DataReportAnalysis dataReportAnalysis){
        return dataReportAnalysisMapper.updateByPrimaryKey(dataReportAnalysis)==1;
    }

    public List<DataReportAnalysis> getList(String keyWord) {
        DataReportAnalysisExample dataReportAnalysisExample = new DataReportAnalysisExample();
        DataReportAnalysisExample.Criteria criteria = dataReportAnalysisExample.createCriteria();
        if (StringUtils.isNotEmpty(keyWord)){
            criteria.andCategoryEqualTo(Integer.parseInt(keyWord));
        }
        return dataReportAnalysisMapper.selectByExample(dataReportAnalysisExample);
    }

    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisMapper.selectByPrimaryKey(id);
    }

    public boolean deleteReportAnalysis(Integer id) {
        int flag = dataReportAnalysisMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }

    public List<DataReportAnalysis> getDataReportAnalysisByCategory(Integer category){
        DataReportAnalysisExample example = new DataReportAnalysisExample();
        example.createCriteria().andCategoryEqualTo(category);
        return dataReportAnalysisMapper.selectByExample(example);
    }

}
