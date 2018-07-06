package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetwork;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateNetworkMapper {
    int countByExample(ExamineEstateNetworkExample example);

    int deleteByExample(ExamineEstateNetworkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateNetwork record);

    int insertSelective(ExamineEstateNetwork record);

    List<ExamineEstateNetwork> selectByExample(ExamineEstateNetworkExample example);

    ExamineEstateNetwork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateNetwork record, @Param("example") ExamineEstateNetworkExample example);

    int updateByExample(@Param("record") ExamineEstateNetwork record, @Param("example") ExamineEstateNetworkExample example);

    int updateByPrimaryKeySelective(ExamineEstateNetwork record);

    int updateByPrimaryKey(ExamineEstateNetwork record);
}