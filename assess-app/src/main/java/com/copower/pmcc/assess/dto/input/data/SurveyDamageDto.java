package com.copower.pmcc.assess.dto.input.data;

public class SurveyDamageDto {
    private String zoneProjectName;
    private String zoneProjectItem;
    private String entityProjectName;
    private String entityProjectItem;

    public String getZoneProjectName() {
        return zoneProjectName;
    }

    public void setZoneProjectName(String zoneProjectName) {
        this.zoneProjectName = zoneProjectName;
    }

    public String getZoneProjectItem() {
        return zoneProjectItem;
    }

    public void setZoneProjectItem(String zoneProjectItem) {
        this.zoneProjectItem = zoneProjectItem;
    }

    public String getEntityProjectName() {
        return entityProjectName;
    }

    public void setEntityProjectName(String entityProjectName) {
        this.entityProjectName = entityProjectName;
    }

    public String getEntityProjectItem() {
        return entityProjectItem;
    }

    public void setEntityProjectItem(String entityProjectItem) {
        this.entityProjectItem = entityProjectItem;
    }
}
