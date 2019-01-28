package com.copower.pmcc.assess.dto.input.project.generate;

import java.io.Serializable;

/**
 * @Auther: zch
 * @Date: 2019/1/28 18:30
 * @Description:
 */
public class BookmarkAndRegexDto implements Serializable {
    private String name;
    private String type;
    private String chineseName;

    public String getName() {
        return name;
    }

    public BookmarkAndRegexDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public BookmarkAndRegexDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getChineseName() {
        return chineseName;
    }

    public BookmarkAndRegexDto setChineseName(String chineseName) {
        this.chineseName = chineseName;
        return this;
    }
}
