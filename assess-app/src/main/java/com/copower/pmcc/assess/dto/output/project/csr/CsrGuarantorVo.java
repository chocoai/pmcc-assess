package com.copower.pmcc.assess.dto.output.project.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrGuarantor;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/4
 * @time: 18:55
 */
public class CsrGuarantorVo extends CsrGuarantor {
    private String taskRemarks;

    private String actualHours;

    public String getTaskRemarks() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks = taskRemarks;
    }

    public String getActualHours() {
        return actualHours;
    }

    public void setActualHours(String actualHours) {
        this.actualHours = actualHours;
    }
}
