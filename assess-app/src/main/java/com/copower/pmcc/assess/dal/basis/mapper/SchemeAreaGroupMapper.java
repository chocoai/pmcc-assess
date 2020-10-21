package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeAreaGroupMapper {
    long countByExample(SchemeAreaGroupExample example);

    int deleteByExample(SchemeAreaGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeAreaGroup record);

    int insertSelective(SchemeAreaGroup record);

    List<SchemeAreaGroup> selectByExample(SchemeAreaGroupExample example);

    SchemeAreaGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeAreaGroup record, @Param("example") SchemeAreaGroupExample example);

    int updateByExample(@Param("record") SchemeAreaGroup record, @Param("example") SchemeAreaGroupExample example);

    int updateByPrimaryKeySelective(SchemeAreaGroup record);

    int updateByPrimaryKey(SchemeAreaGroup record);
}