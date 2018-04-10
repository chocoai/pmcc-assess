package com.copower.pmcc.assess.dto.input;

import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/2
 * @time: 16:05
 */
public class ProcessUserDto {
    private List<Integer> skipActivity;

    private List<String> userAccounts;

    private Integer StepCount;

    private Integer currActivityId;

    private String processInsId;

    private ProcessInfo processInfo;

    public List<Integer> getSkipActivity() {
        return skipActivity;
    }

    public void setSkipActivity(List<Integer> skipActivity) {
        this.skipActivity = skipActivity;
    }

    public List<String> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<String> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public Integer getStepCount() {
        return StepCount;
    }

    public void setStepCount(Integer stepCount) {
        StepCount = stepCount;
    }

    public Integer getCurrActivityId() {
        return currActivityId;
    }

    public void setCurrActivityId(Integer currActivityId) {
        this.currActivityId = currActivityId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public ProcessInfo getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(ProcessInfo processInfo) {
        this.processInfo = processInfo;
    }
}
