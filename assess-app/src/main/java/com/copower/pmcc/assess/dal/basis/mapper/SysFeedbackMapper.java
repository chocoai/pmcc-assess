package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;
import com.copower.pmcc.assess.dal.basis.entity.SysFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFeedbackMapper {
    int countByExample(SysFeedbackExample example);

    int deleteByExample(SysFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFeedback record);

    int insertSelective(SysFeedback record);

    List<SysFeedback> selectByExample(SysFeedbackExample example);

    SysFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysFeedback record, @Param("example") SysFeedbackExample example);

    int updateByExample(@Param("record") SysFeedback record, @Param("example") SysFeedbackExample example);

    int updateByPrimaryKeySelective(SysFeedback record);

    int updateByPrimaryKey(SysFeedback record);
}