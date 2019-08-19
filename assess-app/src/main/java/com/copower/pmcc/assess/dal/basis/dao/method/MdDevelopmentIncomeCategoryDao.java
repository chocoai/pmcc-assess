package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategory;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentIncomeCategoryMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/8/15.
 */
@Repository
public class MdDevelopmentIncomeCategoryDao {

    @Autowired
    private MdDevelopmentIncomeCategoryMapper mapper;

    public boolean addMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public MdDevelopmentIncomeCategory getMdDevelopmentIncomeCategoryById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteMdDevelopmentIncomeCategoryById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }


    public void removeMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory oo){
        MdDevelopmentIncomeCategoryExample example = new MdDevelopmentIncomeCategoryExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<MdDevelopmentIncomeCategory> getMdDevelopmentIncomeCategoryListByExample(MdDevelopmentIncomeCategory oo){
        MdDevelopmentIncomeCategoryExample example = new MdDevelopmentIncomeCategoryExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
    
}
