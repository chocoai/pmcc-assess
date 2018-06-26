package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dto.input.project.scheme.SchemeAreaGroupDto;

import java.math.BigDecimal;

/**
 * Created by 13426 on 2018/5/18.
 */
public class SchemeAreaGroupVo extends SchemeAreaGroupDto {
    private String recordName;
    private BigDecimal floorArea;

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }
}
