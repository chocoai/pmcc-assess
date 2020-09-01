package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.ad.api.dto.AdPlaceFileItemDto;

import java.io.Serializable;

public class AdPlaceFileItemDtoVo extends AdPlaceFileItemDto implements Serializable {

    private String number;

    private String saveLocation;

    private String fileViewName;
    private long count;

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

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
