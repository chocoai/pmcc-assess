package com.copower.pmcc.assess.dto.input;

/**
 * Created by kings on 2018-4-10.
 */
public class ZtreeDto {
    private Integer id;
    private Integer pid;
    private String name;
    private String key;
    private Integer _parentId;
    private String number;
    private Boolean isParent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }


    public Integer get_parentId() {
        return _parentId;
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
