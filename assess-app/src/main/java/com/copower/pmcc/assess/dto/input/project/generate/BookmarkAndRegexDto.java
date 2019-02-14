package com.copower.pmcc.assess.dto.input.project.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookmarkAndRegexDto that = (BookmarkAndRegexDto) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(type, that.type)
                .append(chineseName, that.chineseName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(type)
                .append(chineseName)
                .toHashCode();
    }
}
