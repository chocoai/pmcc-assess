package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;
import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetsCustomizeDataFieldMapper {
    int countByExample(AssetsCustomizeDataFieldExample example);

    int deleteByExample(AssetsCustomizeDataFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetsCustomizeDataField record);

    int insertSelective(AssetsCustomizeDataField record);

    List<AssetsCustomizeDataField> selectByExample(AssetsCustomizeDataFieldExample example);

    AssetsCustomizeDataField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetsCustomizeDataField record, @Param("example") AssetsCustomizeDataFieldExample example);

    int updateByExample(@Param("record") AssetsCustomizeDataField record, @Param("example") AssetsCustomizeDataFieldExample example);

    int updateByPrimaryKeySelective(AssetsCustomizeDataField record);

    int updateByPrimaryKey(AssetsCustomizeDataField record);
}