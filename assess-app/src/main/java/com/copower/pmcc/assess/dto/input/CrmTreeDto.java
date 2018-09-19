package com.copower.pmcc.assess.dto.input;

import java.util.List;

/**
 * 描述:CRM客户关系数
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 15:00
 */
public class CrmTreeDto {
    private Integer id;

    private String text;

    private Integer pId;

    private String pName;

    private String cnName;

    private List<CrmTreeDto> nodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public List<CrmTreeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<CrmTreeDto> nodes) {
        this.nodes = nodes;
    }
}
