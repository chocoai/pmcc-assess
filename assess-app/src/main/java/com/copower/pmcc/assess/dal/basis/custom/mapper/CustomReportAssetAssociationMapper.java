package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAssetAssociation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CustomReportAssetAssociationMapper {
    //查询任务
    List<CustomReportAssetAssociation> getCustomReportAssetAssociationList(@Param("projectName") String projectName, @Param("projectCategoryId") Integer projectCategoryId, @Param("resultId") Integer resultId, @Param("numberValue") String numberValue, @Param("unitName") String unitName,
                                                                         @Param("startDate") Date startDate, @Param("endDate") Date endDate);//查询
}
