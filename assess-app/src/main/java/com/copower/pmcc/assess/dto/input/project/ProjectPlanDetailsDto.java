package com.copower.pmcc.assess.dto.input.project;


import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/9
 * @time: 16:21
 */
public class ProjectPlanDetailsDto extends ProjectPlanDetails {

    private String detailsSoring;

    public String getDetailsSoring() {
        return detailsSoring;
    }

    public void setDetailsSoring(String detailsSoring) {
        this.detailsSoring = detailsSoring;
    }
}
