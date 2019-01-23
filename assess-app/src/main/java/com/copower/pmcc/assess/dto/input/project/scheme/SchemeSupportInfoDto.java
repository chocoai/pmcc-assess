package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;

import java.util.List;

/**
 * Created by kings on 2019-1-23.
 */
public class SchemeSupportInfoDto {
    private List<SchemeSupportInfo> supportInfoList;

    public List<SchemeSupportInfo> getSupportInfoList() {
        return supportInfoList;
    }

    public void setSupportInfoList(List<SchemeSupportInfo> supportInfoList) {
        this.supportInfoList = supportInfoList;
    }
}
