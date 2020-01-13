package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportJianSheBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportJianSheBankMapper {
    //查询任务
    List<CustomReportJianSheBank> getCustomReportJianSheBankList(@Param("numberValue") String numberValue, @Param("unitName") String unitName, @Param("reportType") Integer reportType);//查询

}
