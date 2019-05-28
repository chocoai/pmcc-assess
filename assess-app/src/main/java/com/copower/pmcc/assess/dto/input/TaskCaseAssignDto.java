package com.copower.pmcc.assess.dto.input;

import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;

public class TaskCaseAssignDto extends TaskCaseAssign {
    private Integer lpbh;
    private String name;

    public Integer getLpbh() {
        return lpbh;
    }

    public void setLpbh(Integer lpbh) {
        this.lpbh = lpbh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
