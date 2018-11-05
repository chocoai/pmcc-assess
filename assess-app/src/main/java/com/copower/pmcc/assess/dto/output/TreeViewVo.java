package com.copower.pmcc.assess.dto.output;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/20
 * @time: 9:26
 */
public class TreeViewVo {
    private Integer id;

    private String text;

    private Integer pId;

    private String pName;

    private String level;

    private List<TreeViewVo> nodes;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<TreeViewVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeViewVo> nodes) {
        this.nodes = nodes;
    }
}
