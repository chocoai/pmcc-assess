package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniPropertyManagement;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniPropertyManagementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseFuniPropertyManagementMapper {
    int countByExample(CaseFuniPropertyManagementExample example);

    int deleteByExample(CaseFuniPropertyManagementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseFuniPropertyManagement record);

    int insertSelective(CaseFuniPropertyManagement record);

    List<CaseFuniPropertyManagement> selectByExample(CaseFuniPropertyManagementExample example);

    CaseFuniPropertyManagement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseFuniPropertyManagement record, @Param("example") CaseFuniPropertyManagementExample example);

    int updateByExample(@Param("record") CaseFuniPropertyManagement record, @Param("example") CaseFuniPropertyManagementExample example);

    int updateByPrimaryKeySelective(CaseFuniPropertyManagement record);

    int updateByPrimaryKey(CaseFuniPropertyManagement record);
}