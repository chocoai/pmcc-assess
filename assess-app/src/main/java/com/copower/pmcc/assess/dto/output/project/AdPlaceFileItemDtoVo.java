package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.ad.api.dto.AdPlaceFileItemDto;

import java.io.Serializable;

public class AdPlaceFileItemDtoVo extends AdPlaceFileItemDto implements Serializable {

    private String number;

    private String saveLocation;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }
}
