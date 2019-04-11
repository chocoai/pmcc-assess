package com.copower.pmcc.assess.dto.input.project.generate;

/**
 * Created by kings on 2019-4-11.
 */
public class EstateLiquidityAnalysisDto {
    private String estateName;
    private String generality;
    private String independence;
    private String propertyRight;

    public EstateLiquidityAnalysisDto() {
        this.estateName = "";
        this.generality = "";
        this.independence = "";
        this.propertyRight = "";
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getGenerality() {
        return generality;
    }

    public void setGenerality(String generality) {
        this.generality = generality;
    }

    public String getIndependence() {
        return independence;
    }

    public void setIndependence(String independence) {
        this.independence = independence;
    }

    public String getPropertyRight() {
        return propertyRight;
    }

    public void setPropertyRight(String propertyRight) {
        this.propertyRight = propertyRight;
    }
}
