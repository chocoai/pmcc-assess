package com.copower.pmcc.assess.dto.output.base;

import com.copower.pmcc.erp.common.utils.FormatUtils;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/28
 * @time: 14:50
 */
public class BaseBussinessVo {
    private Integer tableId;

    private String tableName;

    private String description;

    public BaseBussinessVo(Integer tableId, Class clazz) {
        this.tableId = tableId;
        convertClazzToTableName(clazz);
    }
    public BaseBussinessVo(Integer tableId, String tableName) {
        this.tableId = tableId;
        this.tableName = tableName;
    }

    public BaseBussinessVo(Integer tableId, Class clazz, String description) {
        this.tableId = tableId;
        convertClazzToTableName(clazz);
        this.description = description;
    }
    public BaseBussinessVo(Integer tableId, String tableName, String description) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.description = description;
    }


    private void convertClazzToTableName(Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuilder builder = new StringBuilder("tb_");

        //驼峰转下划线,到真实表
        builder.append(FormatUtils.camelToUnderline(simpleName));

        this.tableName = builder.toString();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
