package com.copower.pmcc.assess.dto.input;

import com.copower.pmcc.assess.dal.entity.ChksApprovalAssess;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetails;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/29
 * @time: 10:39
 */
public class ChksApprovalAssessDto extends ChksApprovalAssess {

    private List<ChksApprovalAssessDetails> chksApprovalAssessDetails;

    private Integer boxId;

    public List<ChksApprovalAssessDetails> getChksApprovalAssessDetails() {
        return chksApprovalAssessDetails;
    }

    public void setChksApprovalAssessDetails(List<ChksApprovalAssessDetails> chksApprovalAssessDetails) {
        this.chksApprovalAssessDetails = chksApprovalAssessDetails;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
