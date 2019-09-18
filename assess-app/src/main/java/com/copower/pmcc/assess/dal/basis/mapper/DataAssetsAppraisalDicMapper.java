package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;
import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAssetsAppraisalDicMapper {
    int countByExample(DataAssetsAppraisalDicExample example);

    int deleteByExample(DataAssetsAppraisalDicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAssetsAppraisalDic record);

    int insertSelective(DataAssetsAppraisalDic record);

    List<DataAssetsAppraisalDic> selectByExample(DataAssetsAppraisalDicExample example);

    DataAssetsAppraisalDic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAssetsAppraisalDic record, @Param("example") DataAssetsAppraisalDicExample example);

    int updateByExample(@Param("record") DataAssetsAppraisalDic record, @Param("example") DataAssetsAppraisalDicExample example);

    int updateByPrimaryKeySelective(DataAssetsAppraisalDic record);

    int updateByPrimaryKey(DataAssetsAppraisalDic record);
}