package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class MdDevelopmentHypothesis {
    private Integer id;

    private String estimateunitpricelandc33;

    private String landpricecorrecting;

    private String investmentprofit;

    private String constructioncostsubtotal;

    private String totalgrossfloorarea;

    private String estimatesaletotal;

    private String jsonContent;

    private Integer engineeringId;

    private Integer pid;

    private String creator;

    private Date gmtModified;

    private Date gmtCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstimateunitpricelandc33() {
        return estimateunitpricelandc33;
    }

    public void setEstimateunitpricelandc33(String estimateunitpricelandc33) {
        this.estimateunitpricelandc33 = estimateunitpricelandc33 == null ? null : estimateunitpricelandc33.trim();
    }

    public String getLandpricecorrecting() {
        return landpricecorrecting;
    }

    public void setLandpricecorrecting(String landpricecorrecting) {
        this.landpricecorrecting = landpricecorrecting == null ? null : landpricecorrecting.trim();
    }

    public String getInvestmentprofit() {
        return investmentprofit;
    }

    public void setInvestmentprofit(String investmentprofit) {
        this.investmentprofit = investmentprofit == null ? null : investmentprofit.trim();
    }

    public String getConstructioncostsubtotal() {
        return constructioncostsubtotal;
    }

    public void setConstructioncostsubtotal(String constructioncostsubtotal) {
        this.constructioncostsubtotal = constructioncostsubtotal == null ? null : constructioncostsubtotal.trim();
    }

    public String getTotalgrossfloorarea() {
        return totalgrossfloorarea;
    }

    public void setTotalgrossfloorarea(String totalgrossfloorarea) {
        this.totalgrossfloorarea = totalgrossfloorarea == null ? null : totalgrossfloorarea.trim();
    }

    public String getEstimatesaletotal() {
        return estimatesaletotal;
    }

    public void setEstimatesaletotal(String estimatesaletotal) {
        this.estimatesaletotal = estimatesaletotal == null ? null : estimatesaletotal.trim();
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public Integer getEngineeringId() {
        return engineeringId;
    }

    public void setEngineeringId(Integer engineeringId) {
        this.engineeringId = engineeringId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}