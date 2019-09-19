package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;

/**
 * Created by zch on 2019-9-19.
 */
public class DataAssetsAppraisalDicVo extends DataAssetsAppraisalDic {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
