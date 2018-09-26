package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSection;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeDateSectionMapper {
    int countByExample(MdIncomeDateSectionExample example);

    int deleteByExample(MdIncomeDateSectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeDateSection record);

    int insertSelective(MdIncomeDateSection record);

    List<MdIncomeDateSection> selectByExample(MdIncomeDateSectionExample example);

    MdIncomeDateSection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeDateSection record, @Param("example") MdIncomeDateSectionExample example);

    int updateByExample(@Param("record") MdIncomeDateSection record, @Param("example") MdIncomeDateSectionExample example);

    int updateByPrimaryKeySelective(MdIncomeDateSection record);

    int updateByPrimaryKey(MdIncomeDateSection record);
}