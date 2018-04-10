package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/29
 * @time: 11:17
 */
public class ChksApprovalAssessListVo {
    private ChksApprovalInfoVo chksApprovalInfoVos;

    private List<AssessmentItemDto> assessmentItemDtos;

    private Double scoreRatio;

    public ChksApprovalInfoVo getChksApprovalInfoVos() {
        return chksApprovalInfoVos;
    }

    public void setChksApprovalInfoVos(ChksApprovalInfoVo chksApprovalInfoVos) {
        this.chksApprovalInfoVos = chksApprovalInfoVos;
    }

    public List<AssessmentItemDto> getAssessmentItemDtos() {
        return assessmentItemDtos;
    }

    public void setAssessmentItemDtos(List<AssessmentItemDto> assessmentItemDtos) {
        this.assessmentItemDtos = assessmentItemDtos;
    }

    public Double getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(Double scoreRatio) {
        this.scoreRatio = scoreRatio;
    }
}
