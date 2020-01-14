package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportTianFuBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportTianFuBankMapper {
    //查询任务
    List<CustomReportTianFuBank> getCustomReportTianFuBankList(@Param("numberValue") String numberValue, @Param("unitName") String unitName, @Param("reportType") Integer reportType,
                                                               @Param("previewsStartDate") Date previewsStartDate,@Param("previewsEndDate") Date previewsEndDate,
                                                               @Param("resultStartDate") Date resultStartDate,@Param("resultEndDate") Date resultEndDate);//查询
}
