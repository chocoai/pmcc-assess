package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoAssignTaskMapper {
    long countByExample(NetInfoAssignTaskExample example);

    int deleteByExample(NetInfoAssignTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoAssignTask record);

    int insertSelective(NetInfoAssignTask record);

    List<NetInfoAssignTask> selectByExample(NetInfoAssignTaskExample example);

    NetInfoAssignTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoAssignTask record, @Param("example") NetInfoAssignTaskExample example);

    int updateByExample(@Param("record") NetInfoAssignTask record, @Param("example") NetInfoAssignTaskExample example);

    int updateByPrimaryKeySelective(NetInfoAssignTask record);

    int updateByPrimaryKey(NetInfoAssignTask record);
}