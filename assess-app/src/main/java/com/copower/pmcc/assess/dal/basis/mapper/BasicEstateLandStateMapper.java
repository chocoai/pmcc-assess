package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandStateMapper {
    long countByExample(BasicEstateLandStateExample example);

    int deleteByExample(BasicEstateLandStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandState record);

    int insertSelective(@Param("record") BasicEstateLandState record, @Param("selective") BasicEstateLandState.Column ... selective);

    List<BasicEstateLandState> selectByExampleWithBLOBs(BasicEstateLandStateExample example);

    List<BasicEstateLandState> selectByExample(BasicEstateLandStateExample example);

    BasicEstateLandState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandState record, @Param("example") BasicEstateLandStateExample example, @Param("selective") BasicEstateLandState.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") BasicEstateLandState record, @Param("example") BasicEstateLandStateExample example);

    int updateByExample(@Param("record") BasicEstateLandState record, @Param("example") BasicEstateLandStateExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicEstateLandState record, @Param("selective") BasicEstateLandState.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(BasicEstateLandState record);

    int updateByPrimaryKey(BasicEstateLandState record);

    int batchInsert(@Param("list") List<BasicEstateLandState> list);

    int batchInsertSelective(@Param("list") List<BasicEstateLandState> list, @Param("selective") BasicEstateLandState.Column ... selective);
}