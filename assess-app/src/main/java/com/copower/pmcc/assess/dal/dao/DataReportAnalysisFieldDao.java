package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisFieldExample;
import com.copower.pmcc.assess.dal.mapper.DataReportAnalysisFieldMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataReportAnalysisFieldDao {

    @Autowired
    private DataReportAnalysisFieldMapper dataReportAnalysisFieldMapper;


    public  List<DataReportAnalysisField> getFieldList(Integer pid,String name) {
        DataReportAnalysisFieldExample dataReportAnalysisFieldExample = new DataReportAnalysisFieldExample();
        DataReportAnalysisFieldExample.Criteria criteria = dataReportAnalysisFieldExample.createCriteria();
        if (pid > -1){
            criteria.andAnalysisIdEqualTo(pid);
        }
        if(StringUtils.isNotEmpty(name)){
            criteria.andNameLike(String.format("%s%s%s", "%",name,"%"));
        }
        return dataReportAnalysisFieldMapper.selectByExample(dataReportAnalysisFieldExample);
    }

    public Boolean addField(DataReportAnalysisField field) {
        int flag = dataReportAnalysisFieldMapper.insert(field);
        return flag > 0;
    }

    public boolean deleteField(Integer id) {
        int flag = dataReportAnalysisFieldMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }


    public  boolean deleteFields(Integer id){
        DataReportAnalysisFieldExample dataReportAnalysisFieldExample = new DataReportAnalysisFieldExample();
        DataReportAnalysisFieldExample.Criteria criteria = dataReportAnalysisFieldExample.createCriteria();
        criteria.andAnalysisIdEqualTo(id);
        int flag = dataReportAnalysisFieldMapper.deleteByExample(dataReportAnalysisFieldExample);
        return flag > 0;
    }

    public List<DataReportAnalysisField> getDataReportAnalysisField(Integer analysisId) {
        DataReportAnalysisFieldExample example = new DataReportAnalysisFieldExample();
        example.createCriteria().andAnalysisIdEqualTo(analysisId);
        return dataReportAnalysisFieldMapper.selectByExample(example);
    }

    public List<DataReportAnalysisField> getAllList() {
        DataReportAnalysisFieldExample example = new DataReportAnalysisFieldExample();
        example.createCriteria().andIdIsNotNull();
        return dataReportAnalysisFieldMapper.selectByExample(example);
    }
}
