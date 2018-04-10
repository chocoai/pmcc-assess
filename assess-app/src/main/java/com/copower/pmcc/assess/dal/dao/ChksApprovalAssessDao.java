package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ChksApprovalAssess;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetails;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetailsExample;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessExample;
import com.copower.pmcc.assess.dal.mapper.ChksApprovalAssessDetailsMapper;
import com.copower.pmcc.assess.dal.mapper.ChksApprovalAssessMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/29
 * @time: 10:44
 */
@Repository
public class ChksApprovalAssessDao {
    @Autowired
    private ChksApprovalAssessMapper chksApprovalAssessMapper;
    @Autowired
    private ChksApprovalAssessDetailsMapper chksApprovalAssessDetailsMapper;

    public void addChksApprovalAssess(ChksApprovalAssess chksApprovalAssess) {
        chksApprovalAssessMapper.insertSelective(chksApprovalAssess);
    }

    public void updateChksApprovalAssess(ChksApprovalAssess chksApprovalAssess) {
        chksApprovalAssessMapper.updateByPrimaryKeySelective(chksApprovalAssess);
    }

    public void updateAssessByActivityId(Integer activitId, ChksApprovalAssess chksApprovalAssess) {
        ChksApprovalAssessExample example = new ChksApprovalAssessExample();
        example.createCriteria().andChksApprovalInfoIdEqualTo(activitId);
        chksApprovalAssessMapper.updateByExampleSelective(chksApprovalAssess, example);
    }

    public List<ChksApprovalAssess> getChksApprovalAssessList(ChksApprovalAssess chksApprovalAssess) {
        ChksApprovalAssessExample example = new ChksApprovalAssessExample();
        MybatisUtils.convertObj2Example(chksApprovalAssess, example);
        return chksApprovalAssessMapper.selectByExample(example);
    }

    public void addChksApprovalAssessDetails(ChksApprovalAssessDetails chksApprovalAssessDetails) {
        chksApprovalAssessDetailsMapper.insertSelective(chksApprovalAssessDetails);
    }

    public void updateChksApprovalAssessDetails(ChksApprovalAssessDetails chksApprovalAssessDetails) {
        chksApprovalAssessDetailsMapper.updateByPrimaryKeySelective(chksApprovalAssessDetails);
    }

    public List<ChksApprovalAssessDetails> getChksApprovalAssessDetailsList(ChksApprovalAssessDetails chksApprovalAssessDetails) {
        ChksApprovalAssessDetailsExample example = new ChksApprovalAssessDetailsExample();
        MybatisUtils.convertObj2Example(chksApprovalAssessDetails, example);
        return chksApprovalAssessDetailsMapper.selectByExample(example);
    }

}
