package com.copower.pmcc.assess.dto.output.basic;

import java.math.BigDecimal;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:14
 * @Description:
 */
public class BasicSelectUnitHuxingVo{
    private Integer id;
    private String name;
    private String tableName;
    private String fileViewName;
    private BigDecimal area;
    private String orientationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }
}
