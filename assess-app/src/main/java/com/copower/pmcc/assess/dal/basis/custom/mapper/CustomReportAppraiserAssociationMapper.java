package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAppraiserAssociation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportAppraiserAssociationMapper {
    //查询任务
    List<CustomReportAppraiserAssociation> getCustomReportAppraiserAssociationList(@Param("projectName") String projectName, @Param("reportType") Integer reportType, @Param("numberValue") String numberValue, @Param("unitName") String unitName,
                                                                                   @Param("previewsStartDate") Date previewsStartDate,@Param("previewsEndDate") Date previewsEndDate,
                                                                                   @Param("resultStartDate") Date resultStartDate,@Param("resultEndDate") Date resultEndDate);//查询
}
