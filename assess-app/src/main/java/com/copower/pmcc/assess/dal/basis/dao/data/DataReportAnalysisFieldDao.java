package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportAnalysisFieldMapper;
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
        boolean flag = true;
        DataReportAnalysisFieldExample example = new DataReportAnalysisFieldExample();
        example.createCriteria().andIdIsNotNull().andAnalysisIdEqualTo(field.getAnalysisId()).andNameEqualTo(field.getName());
        List<DataReportAnalysisField> analysisFields = dataReportAnalysisFieldMapper.selectByExample(example);
        if (analysisFields.size() >0)flag=false;
        if (flag){
            dataReportAnalysisFieldMapper.insertSelective(field);
        }
        return flag;
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

    public void delete(String name,Integer id){
        DataReportAnalysisFieldExample example = new DataReportAnalysisFieldExample();
        example.createCriteria().andAnalysisIdEqualTo(id).andNameEqualTo(name);
        List<DataReportAnalysisField> analysisFields = dataReportAnalysisFieldMapper.selectByExample(example);
        for (DataReportAnalysisField field:analysisFields){
            dataReportAnalysisFieldMapper.deleteByPrimaryKey(field.getId());
        }
    }
}
