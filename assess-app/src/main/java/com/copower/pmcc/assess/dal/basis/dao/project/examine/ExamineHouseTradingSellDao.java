package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSellExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseTradingSellMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/27 18:39
 * @Description:
 */
@Repository
public class ExamineHouseTradingSellDao {
    @Autowired
    private ExamineHouseTradingSellMapper examineHouseTradingSellMapper;

    public boolean addExamineHouseTradingSell(ExamineHouseTradingSell examineHouseTradingSell){
        return examineHouseTradingSellMapper.insertSelective(examineHouseTradingSell)==1;
    }

    public List<ExamineHouseTradingSell> examineHouseTradingSells(ExamineHouseTradingSell examineHouseTradingSell){
        ExamineHouseTradingSellExample example = new ExamineHouseTradingSellExample();
        MybatisUtils.convertObj2Example(examineHouseTradingSell, example);
        return examineHouseTradingSellMapper.selectByExample(example);
    }

    public boolean removeExamineHouseTradingSell(Integer id){
        return examineHouseTradingSellMapper.deleteByPrimaryKey(id)==1;
    }

    public boolean updateExamineHouseTradingSell(ExamineHouseTradingSell examineHouseTradingSell){
        return examineHouseTradingSellMapper.updateByPrimaryKeySelective(examineHouseTradingSell)==1;
    }
}
