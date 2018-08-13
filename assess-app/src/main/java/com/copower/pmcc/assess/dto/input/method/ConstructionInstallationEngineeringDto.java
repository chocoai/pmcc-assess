package com.copower.pmcc.assess.dto.input.method;

import java.io.Serializable;

/**
 * @Auther: zch
 * @Date: 2018/8/13 14:49
 * @Description:建筑安装工程费 easyui tree
 */
public class ConstructionInstallationEngineeringDto implements Serializable{
    private Integer id;
    private Integer pid;
    private String name;
    private String key;
    private Integer _parentId;
    private Integer area;
    private String number;
    private Boolean isParent;
    private  double totalCost;
    private double currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer get_parentId() {
        return _parentId;
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }
}
