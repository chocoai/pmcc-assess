package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.CaseComparisonField;
import com.copower.pmcc.assess.dal.entity.CaseComparisonFieldExample;
import com.copower.pmcc.assess.dal.mapper.CaseComparisonFieldMapper;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/3.
 */
@Repository
public class CaseComparisonFieldDao {

    @Autowired
    private CaseComparisonFieldMapper mapper;

    public boolean add(CaseComparisonFieldDto dto) {
        boolean flag = true;
        CaseComparisonFieldExample example = new CaseComparisonFieldExample();
        example.createCriteria().andUNameEqualTo(dto.getuName());
        if (mapper.selectByExample(example).size() > 0) flag = false;
        if (flag) return mapper.insertSelective(change(dto)) == 1;
        return flag;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(CaseComparisonFieldDto dto) {
        return mapper.updateByPrimaryKey(change(dto)) == 1;
    }

    @Deprecated
    public List<CaseComparisonFieldDto> list(String name) {
        List<CaseComparisonFieldDto> dtos = new ArrayList<>();
        List<CaseComparisonField> list = null;
        CaseComparisonFieldExample example = new CaseComparisonFieldExample();
        if (name == null || name == "") {
            example.createCriteria().andIdIsNotNull();
        } else {
            example.createCriteria().andNameLike("%" + name + "%");
        }
        list = mapper.selectByExample(example);
        list.parallelStream().forEach(c -> dtos.add(change(c)));
        return dtos;
    }

    public List<CaseComparisonFieldDto> list(Integer caseId, String name) {
        List<CaseComparisonFieldDto> dtos = new ArrayList<>();
        List<CaseComparisonField> list = null;
        CaseComparisonFieldExample example = new CaseComparisonFieldExample();
        if (name == null || name == "") {
            example.createCriteria().andIdIsNotNull();
        } else if (name != null && name != "" && caseId == null) {
            example.createCriteria().andNameLike("%" + name + "%");
        } else if (caseId != null && (name == null || name == "")) {
            example.createCriteria().andCaseIdEqualTo(caseId);
        }else if (caseId != null && (name != null && name != "")){
            example.createCriteria().andCaseIdEqualTo(caseId).andNameLike("%" + name + "%");
        }
        list = mapper.selectByExample(example);
        list.parallelStream().forEach(c -> dtos.add(change(c)));
        return dtos;
    }

    public CaseComparisonField change(CaseComparisonFieldDto dto) {
        CaseComparisonField field = new CaseComparisonField();
        BeanUtils.copyProperties(dto, field);
        return field;
    }

    public CaseComparisonFieldDto change(CaseComparisonField field) {
        CaseComparisonFieldDto fieldDto = new CaseComparisonFieldDto();
        BeanUtils.copyProperties(field, fieldDto);
        return fieldDto;
    }
}
