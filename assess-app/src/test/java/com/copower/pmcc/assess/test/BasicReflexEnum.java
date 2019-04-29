package com.copower.pmcc.assess.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: zch
 * @date: 2019/4/29 09:07
 * @description:映射类型
 */
public enum BasicReflexEnum implements Serializable {
    VARCHAR("varchar(255)",String.class),
    DECIMAL("decimal(13,2)",BigDecimal.class),
    INT("int(11)",Integer.class),
    DOUBLE("double(11)",Double.class),
    DATE("date",Date.class),
    ;
    private String jdbcType;
    private Class javaType;
    BasicReflexEnum(String jdbcType,Class javaType){
        this.javaType = javaType;
        this.jdbcType = jdbcType;
    }

    public Class getJavaType() {
        return javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }
}
