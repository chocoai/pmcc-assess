package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunction;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunctionExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuildingFunctionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 11:50
 * @Description:
 */
@Repository
public class ExamineBuildingFunctionDao {
    @Autowired
    private ExamineBuildingFunctionMapper examineBuildingFunctionMapper;

    public Integer addExamineBuildingFunction(ExamineBuildingFunction examineBuildingFunction){
        examineBuildingFunctionMapper.insertSelective(examineBuildingFunction);
        return examineBuildingFunction.getId();
    }

    public ExamineBuildingFunction getExamineBuildingFunctionById(Integer id){
        return examineBuildingFunctionMapper.selectByPrimaryKey(id);
    }

    public boolean updateExamineBuildingFunction(ExamineBuildingFunction examineBuildingFunction){
        return examineBuildingFunctionMapper.updateByPrimaryKeySelective(examineBuildingFunction)==1;
    }

    public void removeExamineBuildingFunction(ExamineBuildingFunction examineBuildingFunction){
        ExamineBuildingFunctionExample example = new ExamineBuildingFunctionExample();
        MybatisUtils.convertObj2Example(examineBuildingFunction, example);
        try {
            examineBuildingFunctionMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<ExamineBuildingFunction> getExamineBuildingFunctionList(ExamineBuildingFunction examineBuildingFunction){
        ExamineBuildingFunctionExample example = new ExamineBuildingFunctionExample();
        MybatisUtils.convertObj2Example(examineBuildingFunction, example);
        return examineBuildingFunctionMapper.selectByExample(example);
    }
}
