package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:14
 * @Description:
 */
public class BasicUnitHuxingVo extends BasicUnitHuxing {
    private String fileViewName;
    private String houseLayoutName;
    private String orientationName;
    private String tableName;
    private String creatorName;
    private String utilitiesMeasureName;
    private String utilitiesTypeName;
    private String spatialDistributionName;
    private Boolean canManage;

    public String getUtilitiesTypeName() {
        return utilitiesTypeName;
    }

    public void setUtilitiesTypeName(String utilitiesTypeName) {
        this.utilitiesTypeName = utilitiesTypeName;
    }

    public String getUtilitiesMeasureName() {
        return utilitiesMeasureName;
    }

    public void setUtilitiesMeasureName(String utilitiesMeasureName) {
        this.utilitiesMeasureName = utilitiesMeasureName;
    }

    public String getSpatialDistributionName() {
        return spatialDistributionName;
    }

    public void setSpatialDistributionName(String spatialDistributionName) {
        this.spatialDistributionName = spatialDistributionName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getHouseLayoutName() {
        return houseLayoutName;
    }

    public void setHouseLayoutName(String houseLayoutName) {
        this.houseLayoutName = houseLayoutName;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Boolean getCanManage() {
        return canManage;
    }

    public void setCanManage(Boolean canManage) {
        this.canManage = canManage;
    }
}
