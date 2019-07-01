package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentEngineering;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentEngineeringExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentEngineeringMapper {
    int countByExample(MdDevelopmentEngineeringExample example);

    int deleteByExample(MdDevelopmentEngineeringExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentEngineering record);

    int insertSelective(MdDevelopmentEngineering record);

    List<MdDevelopmentEngineering> selectByExampleWithBLOBs(MdDevelopmentEngineeringExample example);

    List<MdDevelopmentEngineering> selectByExample(MdDevelopmentEngineeringExample example);

    MdDevelopmentEngineering selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentEngineering record, @Param("example") MdDevelopmentEngineeringExample example);

    int updateByExampleWithBLOBs(@Param("record") MdDevelopmentEngineering record, @Param("example") MdDevelopmentEngineeringExample example);

    int updateByExample(@Param("record") MdDevelopmentEngineering record, @Param("example") MdDevelopmentEngineeringExample example);

    int updateByPrimaryKeySelective(MdDevelopmentEngineering record);

    int updateByPrimaryKeyWithBLOBs(MdDevelopmentEngineering record);

    int updateByPrimaryKey(MdDevelopmentEngineering record);
}