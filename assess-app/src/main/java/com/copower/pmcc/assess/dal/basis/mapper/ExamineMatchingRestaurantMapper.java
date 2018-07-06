package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRestaurant;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRestaurantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingRestaurantMapper {
    int countByExample(ExamineMatchingRestaurantExample example);

    int deleteByExample(ExamineMatchingRestaurantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingRestaurant record);

    int insertSelective(ExamineMatchingRestaurant record);

    List<ExamineMatchingRestaurant> selectByExample(ExamineMatchingRestaurantExample example);

    ExamineMatchingRestaurant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingRestaurant record, @Param("example") ExamineMatchingRestaurantExample example);

    int updateByExample(@Param("record") ExamineMatchingRestaurant record, @Param("example") ExamineMatchingRestaurantExample example);

    int updateByPrimaryKeySelective(ExamineMatchingRestaurant record);

    int updateByPrimaryKey(ExamineMatchingRestaurant record);
}