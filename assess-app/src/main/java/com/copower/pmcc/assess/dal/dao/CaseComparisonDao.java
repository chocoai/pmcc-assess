package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataCaseComparison;
import com.copower.pmcc.assess.dal.entity.DataCaseComparisonExample;
import com.copower.pmcc.assess.dal.mapper.DataCaseComparisonMapper;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 3.1.2.15	案例对比配置
 * Created by 13426 on 2018/5/3.
 */
@Repository
public class CaseComparisonDao {

    @Autowired
    private DataCaseComparisonMapper caseComparisonMapper;

    public boolean add(CaseComparisonDto dto) {
        return caseComparisonMapper.insertSelective(change(dto)) == 1;
    }

    public boolean remove(Integer id) {
        return caseComparisonMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(CaseComparisonDto dto) {
        return caseComparisonMapper.updateByPrimaryKey(change(dto)) == 1;
    }

    public CaseComparisonDto get(Integer id) {
        return change(caseComparisonMapper.selectByPrimaryKey(id));
    }

    public List<DataCaseComparison> list(String name) {
        List<DataCaseComparison> caseComparisons = null;
        DataCaseComparisonExample example = new DataCaseComparisonExample();
        if(StringUtils.isNotBlank(name)){
            example.createCriteria().andNameLike("%" + name + "%");
        }
        caseComparisons = caseComparisonMapper.selectByExample(example);
        return caseComparisons;
    }


    public DataCaseComparison change(CaseComparisonDto dto) {
        DataCaseComparison caseComparison = new DataCaseComparison();
        BeanUtils.copyProperties(dto, caseComparison);
        return caseComparison;
    }

    public CaseComparisonDto change(DataCaseComparison caseC) {
        CaseComparisonDto dto = new CaseComparisonDto();
        BeanUtils.copyProperties(caseC, dto);
        return dto;
    }

    public List<DataCaseComparison> getAll() {
        DataCaseComparisonExample example = new DataCaseComparisonExample();
        example.createCriteria().andIdIsNotNull();
        return caseComparisonMapper.selectByExample(example);
    }

    public List<DataCaseComparison> getDataByExploreFormType(Integer exploreFormType) {
        DataCaseComparisonExample example = new DataCaseComparisonExample();
        example.createCriteria().andExploreFormTypeEqualTo(exploreFormType);
        return caseComparisonMapper.selectByExample(example);
    }
}
