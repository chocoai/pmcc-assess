package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;
import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InitiateConsignorMapper {
    long countByExample(InitiateConsignorExample example);

    int deleteByExample(InitiateConsignorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiateConsignor record);

    int insertSelective(@Param("record") InitiateConsignor record, @Param("selective") InitiateConsignor.Column ... selective);

    List<InitiateConsignor> selectByExample(InitiateConsignorExample example);

    InitiateConsignor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiateConsignor record, @Param("example") InitiateConsignorExample example, @Param("selective") InitiateConsignor.Column ... selective);

    int updateByExample(@Param("record") InitiateConsignor record, @Param("example") InitiateConsignorExample example);

    int updateByPrimaryKeySelective(@Param("record") InitiateConsignor record, @Param("selective") InitiateConsignor.Column ... selective);

    int updateByPrimaryKey(InitiateConsignor record);

    int batchInsert(@Param("list") List<InitiateConsignor> list);

    int batchInsertSelective(@Param("list") List<InitiateConsignor> list, @Param("selective") InitiateConsignor.Column ... selective);
}