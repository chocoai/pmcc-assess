package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupport;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeSelfSupportMapper {
    int countByExample(MdIncomeSelfSupportExample example);

    int deleteByExample(MdIncomeSelfSupportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeSelfSupport record);

    int insertSelective(MdIncomeSelfSupport record);

    List<MdIncomeSelfSupport> selectByExample(MdIncomeSelfSupportExample example);

    MdIncomeSelfSupport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeSelfSupport record, @Param("example") MdIncomeSelfSupportExample example);

    int updateByExample(@Param("record") MdIncomeSelfSupport record, @Param("example") MdIncomeSelfSupportExample example);

    int updateByPrimaryKeySelective(MdIncomeSelfSupport record);

    int updateByPrimaryKey(MdIncomeSelfSupport record);
}