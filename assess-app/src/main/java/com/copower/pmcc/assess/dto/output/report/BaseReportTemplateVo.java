package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;
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

    public List<KeyValueDto> getKeyValueDtos() {
        return keyValueDtos;
    }

    public void setKeyValueDtos(List<KeyValueDto> keyValueDtos) {
        this.keyValueDtos = keyValueDtos;
    }
}
