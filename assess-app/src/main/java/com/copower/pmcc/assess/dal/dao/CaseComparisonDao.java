package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.CaseComparison;
import com.copower.pmcc.assess.dal.entity.CaseComparisonExample;
import com.copower.pmcc.assess.dal.mapper.CaseComparisonMapper;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.15	案例对比配置
 * Created by 13426 on 2018/5/3.
 */
@Repository
public class CaseComparisonDao {

    @Autowired
    private CaseComparisonMapper caseComparisonMapper;

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

    public List<CaseComparisonDto> list(String name) {
        List<CaseComparison> caseComparisons = null;
        CaseComparisonExample example = new CaseComparisonExample();
        if (name == null || name == "") {
            example.createCriteria().andIdIsNotNull();
        } else {
            example.createCriteria().andNameLike("%" + name + "%");
        }
        caseComparisons = caseComparisonMapper.selectByExample(example);
        List<CaseComparisonDto> dtos = new ArrayList<>();
        caseComparisons.parallelStream().forEach(caseComparison -> dtos.add(change(caseComparison)));
        return dtos;
    }


    public CaseComparison change(CaseComparisonDto dto) {
        CaseComparison caseComparison = new CaseComparison();
        BeanUtils.copyProperties(dto, caseComparison);
        return caseComparison;
    }

    public CaseComparisonDto change(CaseComparison caseC) {
        CaseComparisonDto dto = new CaseComparisonDto();
        BeanUtils.copyProperties(caseC, dto);
        return dto;
    }
}
