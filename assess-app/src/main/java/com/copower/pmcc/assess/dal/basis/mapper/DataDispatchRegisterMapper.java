package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegister;
import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegisterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDispatchRegisterMapper {
    int countByExample(DataDispatchRegisterExample example);

    int deleteByExample(DataDispatchRegisterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDispatchRegister record);

    int insertSelective(DataDispatchRegister record);

    List<DataDispatchRegister> selectByExample(DataDispatchRegisterExample example);

    DataDispatchRegister selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDispatchRegister record, @Param("example") DataDispatchRegisterExample example);

    int updateByExample(@Param("record") DataDispatchRegister record, @Param("example") DataDispatchRegisterExample example);

    int updateByPrimaryKeySelective(DataDispatchRegister record);

    int updateByPrimaryKey(DataDispatchRegister record);
}