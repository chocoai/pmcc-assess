package com.copower.pmcc.assess.dto.input;

import java.io.Serializable;

public class StatisticsDto implements Serializable {
    private  long number;
    private String title;
    private String name;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
