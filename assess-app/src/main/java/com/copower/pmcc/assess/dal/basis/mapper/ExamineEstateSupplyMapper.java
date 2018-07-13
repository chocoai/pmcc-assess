package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupply;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateSupplyMapper {
    int countByExample(ExamineEstateSupplyExample example);

    int deleteByExample(ExamineEstateSupplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateSupply record);

    int insertSelective(ExamineEstateSupply record);

    List<ExamineEstateSupply> selectByExample(ExamineEstateSupplyExample example);

    ExamineEstateSupply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateSupply record, @Param("example") ExamineEstateSupplyExample example);

    int updateByExample(@Param("record") ExamineEstateSupply record, @Param("example") ExamineEstateSupplyExample example);

    int updateByPrimaryKeySelective(ExamineEstateSupply record);

    int updateByPrimaryKey(ExamineEstateSupply record);
}