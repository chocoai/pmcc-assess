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
    private double area;//建筑面积
    private String number;
    private boolean parent = false;
    private  double totalCost;//总造价
    private double currency;//单方造价

    private String valuationDateDegreeCompletion;//估价时点完工程度
    private double valuationDateTotal;//估价时点总价（万元）
    private double valuationDateCurrency;//估价时点单价(元/㎡)

    private double continuedConstructionInvestmentTotal;//续建投入总价（万元）
    private double continuedConstructionInvestmentCurrency;//续建投入单价（元/㎡）

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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public String getValuationDateDegreeCompletion() {
        return valuationDateDegreeCompletion;
    }

    public void setValuationDateDegreeCompletion(String valuationDateDegreeCompletion) {
        this.valuationDateDegreeCompletion = valuationDateDegreeCompletion;
    }

    public double getValuationDateTotal() {
        return valuationDateTotal;
    }

    public void setValuationDateTotal(double valuationDateTotal) {
        this.valuationDateTotal = valuationDateTotal;
    }

    public double getValuationDateCurrency() {
        return valuationDateCurrency;
    }

    public void setValuationDateCurrency(double valuationDateCurrency) {
        this.valuationDateCurrency = valuationDateCurrency;
    }

    public double getContinuedConstructionInvestmentTotal() {
        return continuedConstructionInvestmentTotal;
    }

    public void setContinuedConstructionInvestmentTotal(double continuedConstructionInvestmentTotal) {
        this.continuedConstructionInvestmentTotal = continuedConstructionInvestmentTotal;
    }

    public double getContinuedConstructionInvestmentCurrency() {
        return continuedConstructionInvestmentCurrency;
    }

    public void setContinuedConstructionInvestmentCurrency(double continuedConstructionInvestmentCurrency) {
        this.continuedConstructionInvestmentCurrency = continuedConstructionInvestmentCurrency;
    }
}
