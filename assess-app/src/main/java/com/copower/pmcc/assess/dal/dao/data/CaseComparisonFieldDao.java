package com.copower.pmcc.assess.dal.dao.data;

import com.copower.pmcc.assess.dal.entity.DataCaseComparisonField;
import com.copower.pmcc.assess.dal.entity.DataCaseComparisonFieldExample;
import com.copower.pmcc.assess.dal.mapper.DataCaseComparisonFieldMapper;
import org.apache.commons.lang3.StringUtils;
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
    private DataCaseComparisonFieldMapper mapper;

    public boolean add(DataCaseComparisonField dto) {
        boolean flag = true;
        DataCaseComparisonFieldExample example = new DataCaseComparisonFieldExample();
        example.createCriteria().andUNameEqualTo(dto.getuName()).andCaseIdEqualTo(dto.getCaseId());
        List<DataCaseComparisonField> dataCaseComparisonFields = mapper.selectByExample(example);
        if (dataCaseComparisonFields.size() > 0) flag = false;
        if (flag) return mapper.insertSelective(change(dto)) == 1;
        return flag;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(DataCaseComparisonField dto) {
        return mapper.updateByPrimaryKeySelective(change(dto)) == 1;
    }

    @Deprecated
    public List<DataCaseComparisonField> list(String name) {
        List<DataCaseComparisonField> dtos = new ArrayList<>();
        List<DataCaseComparisonField> list = null;
        DataCaseComparisonFieldExample example = new DataCaseComparisonFieldExample();
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().andUNameLike("%" + name + "%");
        }
        list = mapper.selectByExample(example);
        list.parallelStream().forEach(c -> dtos.add(change(c)));
        return dtos;
    }

    public List<DataCaseComparisonField> list(Integer caseId, String name) {
        List<DataCaseComparisonField> dtos = new ArrayList<>();
        List<DataCaseComparisonField> list = null;
        DataCaseComparisonFieldExample example = new DataCaseComparisonFieldExample();
        DataCaseComparisonFieldExample.Criteria criteria = example.createCriteria();
        criteria.andCaseIdEqualTo(caseId);
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().andUNameLike("%" + name + "%");
        }
        example.setOrderByClause("type DESC");
        list = mapper.selectByExample(example);
        list.parallelStream().forEach(c -> dtos.add(change(c)));
        return dtos;
    }


    public DataCaseComparisonField change(DataCaseComparisonField field) {
        DataCaseComparisonField fieldDto = new DataCaseComparisonField();
        BeanUtils.copyProperties(field, fieldDto);
        return fieldDto;
    }

    public List<DataCaseComparisonField> getData(Integer caseId) {
        DataCaseComparisonFieldExample example = new DataCaseComparisonFieldExample();
        example.createCriteria().andCaseIdEqualTo(caseId);
        return mapper.selectByExample(example);
    }
}
