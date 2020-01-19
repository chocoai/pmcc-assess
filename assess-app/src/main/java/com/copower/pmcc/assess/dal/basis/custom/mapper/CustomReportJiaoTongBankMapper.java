package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportJiaoTongBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportJiaoTongBankMapper {
    //查询任务
    List<CustomReportJiaoTongBank> getCustomReportJiaoTongBankList(@Param("numberValue") String numberValue, @Param("unitName") String unitName, @Param("reportType") Integer reportType,@Param("consultationId") Integer consultationId,
                                                                   @Param("previewsStartDate") Date previewsStartDate,@Param("previewsEndDate") Date previewsEndDate,
                                                                   @Param("resultStartDate") Date resultStartDate,@Param("resultEndDate") Date resultEndDate);//查询
}
