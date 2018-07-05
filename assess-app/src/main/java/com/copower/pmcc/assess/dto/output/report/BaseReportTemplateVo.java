package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/22
 * @time: 9:53
 */
public class BaseReportTemplateVo extends BaseReportTemplate {
    private String typeName;//数据类型名称

    private String dataPoolTypename;//数据来源
    private String tableName;
    private String columnName;

    private List<KeyValueDto> keyValueDtos;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDataPoolTypename() {
        return dataPoolTypename;
    }

    public void setDataPoolTypename(String dataPoolTypename) {
        this.dataPoolTypename = dataPoolTypename;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<KeyValueDto> getKeyValueDtos() {
        return keyValueDtos;
    }

    public void setKeyValueDtos(List<KeyValueDto> keyValueDtos) {
        this.keyValueDtos = keyValueDtos;
    }
}
