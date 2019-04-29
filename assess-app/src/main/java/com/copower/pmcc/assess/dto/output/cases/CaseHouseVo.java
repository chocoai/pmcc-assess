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
    private String orientationName;
    private String researchTypeName;
    private String spatialDistributionName;

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

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }

    public String getResearchTypeName() {
        return researchTypeName;
    }

    public void setResearchTypeName(String researchTypeName) {
        this.researchTypeName = researchTypeName;
    }

    public String getSpatialDistributionName() {
        return spatialDistributionName;
    }

    public void setSpatialDistributionName(String spatialDistributionName) {
        this.spatialDistributionName = spatialDistributionName;
    }
}
