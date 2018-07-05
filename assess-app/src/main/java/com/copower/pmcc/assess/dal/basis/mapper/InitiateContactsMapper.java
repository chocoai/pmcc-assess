package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContactsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InitiateContactsMapper {
    int countByExample(InitiateContactsExample example);

    int deleteByExample(InitiateContactsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateContacts record);

    int insertSelective(InitiateContacts record);

    List<InitiateContacts> selectByExample(InitiateContactsExample example);

    InitiateContacts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateContacts record, @Param("example") InitiateContactsExample example);

    int updateByExample(@Param("record") InitiateContacts record, @Param("example") InitiateContactsExample example);

    int updateByPrimaryKeySelective(InitiateContacts record);

    int updateByPrimaryKey(InitiateContacts record);
}