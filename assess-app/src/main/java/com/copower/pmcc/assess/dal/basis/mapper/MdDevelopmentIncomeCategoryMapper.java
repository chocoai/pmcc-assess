package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategory;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentIncomeCategoryMapper {
    int countByExample(MdDevelopmentIncomeCategoryExample example);

    int deleteByExample(MdDevelopmentIncomeCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentIncomeCategory record);

    int insertSelective(MdDevelopmentIncomeCategory record);

    List<MdDevelopmentIncomeCategory> selectByExample(MdDevelopmentIncomeCategoryExample example);

    MdDevelopmentIncomeCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentIncomeCategory record, @Param("example") MdDevelopmentIncomeCategoryExample example);

    int updateByExample(@Param("record") MdDevelopmentIncomeCategory record, @Param("example") MdDevelopmentIncomeCategoryExample example);

    int updateByPrimaryKeySelective(MdDevelopmentIncomeCategory record);

    int updateByPrimaryKey(MdDevelopmentIncomeCategory record);
}