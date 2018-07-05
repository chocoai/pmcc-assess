package com.copower.pmcc.assess.dto.input.project;


import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/12
 * @time: 9:31
 */
public class ProjectPlanDto extends ProjectPlan {

    private String detailsSoring;

    private String nextApproval;

    private String nextApprovalName;

    private String detailsPlan;

    private String bisChildren;

    public String getDetailsSoring() {
        return detailsSoring;
    }

    public void setDetailsSoring(String detailsSoring) {
        this.detailsSoring = detailsSoring;
    }

    public String getNextApproval() {
        return nextApproval;
    }

    public void setNextApproval(String nextApproval) {
        this.nextApproval = nextApproval;
    }

    public String getNextApprovalName() {
        return nextApprovalName;
    }

    public void setNextApprovalName(String nextApprovalName) {
        this.nextApprovalName = nextApprovalName;
    }

    public String getDetailsPlan() {
        return detailsPlan;
    }

    public void setDetailsPlan(String detailsPlan) {
        this.detailsPlan = detailsPlan;
    }

    public String getBisChildren() {
        return bisChildren;
    }

    public void setBisChildren(String bisChildren) {
        this.bisChildren = bisChildren;
    }
}
