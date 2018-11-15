package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;

/**
 * @Auther: zch
 * @Date: 2018/11/6 20:56
 * @Description:
 */
public class CaseHouseVo extends CaseHouse {
    private String useEnvironmentName;
    private String certUseName;
    private String practicalUseName;
    private String huxingName;
    private String newsHuxingName;

    public String getUseEnvironmentName() {
        return useEnvironmentName;
    }

    public void setUseEnvironmentName(String useEnvironmentName) {
        this.useEnvironmentName = useEnvironmentName;
    }

    public String getCertUseName() {
        return certUseName;
    }

    public void setCertUseName(String certUseName) {
        this.certUseName = certUseName;
    }

    public String getPracticalUseName() {
        return practicalUseName;
    }

    public void setPracticalUseName(String practicalUseName) {
        this.practicalUseName = practicalUseName;
    }

    public String getHuxingName() {
        return huxingName;
    }

    public void setHuxingName(String huxingName) {
        this.huxingName = huxingName;
    }

    public String getNewsHuxingName() {
        return newsHuxingName;
    }

    public void setNewsHuxingName(String newsHuxingName) {
        this.newsHuxingName = newsHuxingName;
    }
}
