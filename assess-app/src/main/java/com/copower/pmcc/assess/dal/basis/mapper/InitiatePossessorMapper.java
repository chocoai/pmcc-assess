package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InitiatePossessorMapper {
    long countByExample(InitiatePossessorExample example);

    int deleteByExample(InitiatePossessorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiatePossessor record);

    int insertSelective(@Param("record") InitiatePossessor record, @Param("selective") InitiatePossessor.Column ... selective);

    List<InitiatePossessor> selectByExample(InitiatePossessorExample example);

    InitiatePossessor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiatePossessor record, @Param("example") InitiatePossessorExample example, @Param("selective") InitiatePossessor.Column ... selective);

    int updateByExample(@Param("record") InitiatePossessor record, @Param("example") InitiatePossessorExample example);

    int updateByPrimaryKeySelective(@Param("record") InitiatePossessor record, @Param("selective") InitiatePossessor.Column ... selective);

    int updateByPrimaryKey(InitiatePossessor record);

    int batchInsert(@Param("list") List<InitiatePossessor> list);

    int batchInsertSelective(@Param("list") List<InitiatePossessor> list, @Param("selective") InitiatePossessor.Column ... selective);
}