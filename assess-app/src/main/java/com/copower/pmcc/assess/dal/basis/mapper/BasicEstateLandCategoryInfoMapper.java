package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateLandCategoryInfoMapper {
    long countByExample(BasicEstateLandCategoryInfoExample example);

    int deleteByExample(BasicEstateLandCategoryInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateLandCategoryInfo record);

    int insertSelective(@Param("record") BasicEstateLandCategoryInfo record, @Param("selective") BasicEstateLandCategoryInfo.Column ... selective);

    List<BasicEstateLandCategoryInfo> selectByExampleWithBLOBs(BasicEstateLandCategoryInfoExample example);

    List<BasicEstateLandCategoryInfo> selectByExample(BasicEstateLandCategoryInfoExample example);

    BasicEstateLandCategoryInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateLandCategoryInfo record, @Param("example") BasicEstateLandCategoryInfoExample example, @Param("selective") BasicEstateLandCategoryInfo.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") BasicEstateLandCategoryInfo record, @Param("example") BasicEstateLandCategoryInfoExample example);

    int updateByExample(@Param("record") BasicEstateLandCategoryInfo record, @Param("example") BasicEstateLandCategoryInfoExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicEstateLandCategoryInfo record, @Param("selective") BasicEstateLandCategoryInfo.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(BasicEstateLandCategoryInfo record);

    int updateByPrimaryKey(BasicEstateLandCategoryInfo record);

    int batchInsert(@Param("list") List<BasicEstateLandCategoryInfo> list);

    int batchInsertSelective(@Param("list") List<BasicEstateLandCategoryInfo> list, @Param("selective") BasicEstateLandCategoryInfo.Column ... selective);
}