package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.FuniPropertyManagement;
import com.copower.pmcc.assess.dal.basis.entity.FuniPropertyManagementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuniPropertyManagementMapper {
    int countByExample(FuniPropertyManagementExample example);

    int deleteByExample(FuniPropertyManagementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FuniPropertyManagement record);

    int insertSelective(FuniPropertyManagement record);

    List<FuniPropertyManagement> selectByExample(FuniPropertyManagementExample example);

    FuniPropertyManagement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FuniPropertyManagement record, @Param("example") FuniPropertyManagementExample example);

    int updateByExample(@Param("record") FuniPropertyManagement record, @Param("example") FuniPropertyManagementExample example);

    int updateByPrimaryKeySelective(FuniPropertyManagement record);

    int updateByPrimaryKey(FuniPropertyManagement record);
}