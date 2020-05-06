package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployees;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAverageWageUrbanEmployeesMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class DataAverageWageUrbanEmployeesDao {

    @Autowired
    private DataAverageWageUrbanEmployeesMapper mapper;

    public boolean updateDataAverageWageUrbanEmployees(DataAverageWageUrbanEmployees oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataAverageWageUrbanEmployeesById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteDataAverageWageUrbanEmployeesByIds(List<Integer> ids) {
        DataAverageWageUrbanEmployeesExample example = new DataAverageWageUrbanEmployeesExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public DataAverageWageUrbanEmployees getDataAverageWageUrbanEmployeesById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveDataAverageWageUrbanEmployees(DataAverageWageUrbanEmployees oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<DataAverageWageUrbanEmployees> getDataAverageWageUrbanEmployeesByIds(List<Integer> ids) {
        DataAverageWageUrbanEmployeesExample example = new DataAverageWageUrbanEmployeesExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<DataAverageWageUrbanEmployees> getDataAverageWageUrbanEmployeesListByExample(DataAverageWageUrbanEmployees oo) {
        DataAverageWageUrbanEmployeesExample example = new DataAverageWageUrbanEmployeesExample();
        DataAverageWageUrbanEmployeesExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


}
