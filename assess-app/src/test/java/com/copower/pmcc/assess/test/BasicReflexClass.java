package com.copower.pmcc.assess.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: zch
 * @date: 2019/4/28 18:17
 * @description:
 */
public class BasicReflexClass implements Serializable {
    private String tableName;
    private Class sourceClass;
    Map<String, Class> typeString = new HashMap<String, Class>(0);

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }

    public Map<String, Class> getTypeString() {
        return typeString;
    }

    public void setTypeString(Map<String, Class> typeString) {
        this.typeString = typeString;
    }

    public BasicReflexClass(String tableName, Class sourceClass) {
        this.tableName = tableName;
        this.sourceClass = sourceClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicReflexClass that = (BasicReflexClass) o;
        return Objects.equals(tableName, that.tableName) &&
                Objects.equals(sourceClass, that.sourceClass) &&
                Objects.equals(typeString, that.typeString);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tableName, sourceClass, typeString);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasicReflexClass{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", sourceClass=").append(sourceClass);
        sb.append(", typeString=").append(typeString);
        sb.append('}');
        return sb.toString();
    }
}
