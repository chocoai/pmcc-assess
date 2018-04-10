package com.copower.pmcc.assess.dal.custom.entity;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/12/15
 * @time: 17:00
 */
public class CustomDdlTable {
    private Integer id;

    private String ddlSql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDdlSql() {
        return ddlSql;
    }

    public void setDdlSql(String ddlSql) {
        this.ddlSql = ddlSql;
    }
}
