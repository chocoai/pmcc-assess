package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandLevelDetailMapper {
    int countByExample(DataLandLevelDetailExample example);

    int deleteByExample(DataLandLevelDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandLevelDetail record);

    int insertSelective(DataLandLevelDetail record);

    List<DataLandLevelDetail> selectByExample(DataLandLevelDetailExample example);

    DataLandLevelDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandLevelDetail record, @Param("example") DataLandLevelDetailExample example);

    int updateByExample(@Param("record") DataLandLevelDetail record, @Param("example") DataLandLevelDetailExample example);

    int updateByPrimaryKeySelective(DataLandLevelDetail record);

    int updateByPrimaryKey(DataLandLevelDetail record);
}