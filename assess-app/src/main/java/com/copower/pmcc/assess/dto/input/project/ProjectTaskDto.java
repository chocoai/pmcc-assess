package com.copower.pmcc.assess.dto.input.project;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/13
 * @time: 18:18
 */
public class ProjectTaskDto {
    private BigDecimal actualHours;
    private String taskRemarks;

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public String getTaskRemarks() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks = taskRemarks;
    }
}
