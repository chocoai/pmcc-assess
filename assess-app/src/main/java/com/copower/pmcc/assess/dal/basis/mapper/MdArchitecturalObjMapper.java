package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdArchitecturalObjMapper {
    int countByExample(MdArchitecturalObjExample example);

    int deleteByExample(MdArchitecturalObjExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdArchitecturalObj record);

    int insertSelective(MdArchitecturalObj record);

    List<MdArchitecturalObj> selectByExample(MdArchitecturalObjExample example);

    MdArchitecturalObj selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdArchitecturalObj record, @Param("example") MdArchitecturalObjExample example);

    int updateByExample(@Param("record") MdArchitecturalObj record, @Param("example") MdArchitecturalObjExample example);

    int updateByPrimaryKeySelective(MdArchitecturalObj record);

    int updateByPrimaryKey(MdArchitecturalObj record);
}