package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentLand;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentLandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdDevelopmentLandMapper {
    int countByExample(MdDevelopmentLandExample example);

    int deleteByExample(MdDevelopmentLandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdDevelopmentLand record);

    int insertSelective(MdDevelopmentLand record);

    List<MdDevelopmentLand> selectByExampleWithBLOBs(MdDevelopmentLandExample example);

    List<MdDevelopmentLand> selectByExample(MdDevelopmentLandExample example);

    MdDevelopmentLand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdDevelopmentLand record, @Param("example") MdDevelopmentLandExample example);

    int updateByExampleWithBLOBs(@Param("record") MdDevelopmentLand record, @Param("example") MdDevelopmentLandExample example);

    int updateByExample(@Param("record") MdDevelopmentLand record, @Param("example") MdDevelopmentLandExample example);

    int updateByPrimaryKeySelective(MdDevelopmentLand record);

    int updateByPrimaryKeyWithBLOBs(MdDevelopmentLand record);

    int updateByPrimaryKey(MdDevelopmentLand record);
}