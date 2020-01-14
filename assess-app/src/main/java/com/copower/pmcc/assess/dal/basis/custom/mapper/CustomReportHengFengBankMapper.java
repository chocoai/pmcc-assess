package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportHengFengBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportHengFengBankMapper {
    //查询任务
    List<CustomReportHengFengBank> getCustomReportHengFengBankList(@Param("numberValue") String numberValue, @Param("unitName") String unitName, @Param("reportType") Integer reportType);//查询

}
