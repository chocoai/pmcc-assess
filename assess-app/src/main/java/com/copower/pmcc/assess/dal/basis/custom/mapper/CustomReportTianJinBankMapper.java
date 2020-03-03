package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportTianJinBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportTianJinBankMapper {
    //查询任务
    List<CustomReportTianJinBank> getCustomReportTianJinBankList(@Param("numberValue") String numberValue, @Param("unitName") String unitName, @Param("reportType") Integer reportType,@Param("consultationId") Integer consultationId,
                                                                 @Param("startDate") Date startDate,@Param("endDate") Date endDate);//查询
}
