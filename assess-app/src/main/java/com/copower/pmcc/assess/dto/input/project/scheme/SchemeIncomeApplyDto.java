package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeIncomeApplyDto {
    private List<SchemeSupportInfo> supportInfoList;

    public List<SchemeSupportInfo> getSupportInfoList() {
        return supportInfoList;
    }

    public void setSupportInfoList(List<SchemeSupportInfo> supportInfoList) {
        this.supportInfoList = supportInfoList;
    }
}
