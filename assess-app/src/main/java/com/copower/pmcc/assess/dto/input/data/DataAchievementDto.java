package com.copower.pmcc.assess.dto.input.data;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.Serializable;

/**
 * Created by zch on 2020-4-20.
 */
public class DataAchievementDto implements Serializable {
    private Integer startRow;
    private int oneRow;
    private Integer twoRow;
    private int endRow;


    private Sheet a;
    private Sheet b;
    private String aName;
    private String BName;
    private Integer type;



    public int getOneRow() {
        return oneRow;
    }

    public void setOneRow(int oneRow) {
        this.oneRow = oneRow;
    }

    public Integer getTwoRow() {
        return twoRow;
    }

    public void setTwoRow(Integer twoRow) {
        this.twoRow = twoRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }


    public Sheet getA() {
        return a;
    }

    public void setA(Sheet a) {
        this.a = a;
    }

    public Sheet getB() {
        return b;
    }

    public void setB(Sheet b) {
        this.b = b;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
