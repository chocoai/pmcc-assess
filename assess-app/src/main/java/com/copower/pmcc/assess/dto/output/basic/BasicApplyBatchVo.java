package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;

/**
 * @Auther: zch
 * @Date: 2018/12/4 10:35
 * @Description:
 */
public class BasicApplyBatchVo extends BasicApplyBatch {
    private String provinceName;
    private String cityName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
