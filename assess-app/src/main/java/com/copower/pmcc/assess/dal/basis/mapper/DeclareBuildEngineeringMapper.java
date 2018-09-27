package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineering;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEngineeringMapper {
    int countByExample(DeclareBuildEngineeringExample example);

    int deleteByExample(DeclareBuildEngineeringExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEngineering record);

    int insertSelective(DeclareBuildEngineering record);

    List<DeclareBuildEngineering> selectByExample(DeclareBuildEngineeringExample example);

    DeclareBuildEngineering selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEngineering record, @Param("example") DeclareBuildEngineeringExample example);

    int updateByExample(@Param("record") DeclareBuildEngineering record, @Param("example") DeclareBuildEngineeringExample example);

    int updateByPrimaryKeySelective(DeclareBuildEngineering record);

    int updateByPrimaryKey(DeclareBuildEngineering record);
}