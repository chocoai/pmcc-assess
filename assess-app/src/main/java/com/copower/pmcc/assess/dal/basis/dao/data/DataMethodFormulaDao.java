package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormula;
import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormulaExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataMethodFormulaMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class DataMethodFormulaDao {
    @Autowired
    private DataMethodFormulaMapper dataMethodFormulaMapper;

    public List<DataMethodFormula> getDataMethodFormulaList(Integer type) {
        DataMethodFormulaExample example = new DataMethodFormulaExample();
        DataMethodFormulaExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (type != null) {
            criteria.andMethodEqualTo(type);
        }
        return dataMethodFormulaMapper.selectByExample(example);
    }

    public List<DataMethodFormula> getListObject(DataMethodFormula dataMethodFormula) {
        DataMethodFormulaExample example = new DataMethodFormulaExample();
        MybatisUtils.convertObj2Example(dataMethodFormula, example);
        return dataMethodFormulaMapper.selectByExample(example);
    }

    public DataMethodFormula getSingleObject(DataMethodFormula dataMethodFormula) {
        DataMethodFormulaExample example = new DataMethodFormulaExample();
        MybatisUtils.convertObj2Example(dataMethodFormula, example);
        List<DataMethodFormula> vacationTypeList = dataMethodFormulaMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataMethodFormula getSingleObject(Integer id) {
        return dataMethodFormulaMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataMethodFormula bidCostInfo) {
        return dataMethodFormulaMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataMethodFormula bidCostInfo) {
        return dataMethodFormulaMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataMethodFormulaMapper.deleteByPrimaryKey(id) == 1;
    }
}
