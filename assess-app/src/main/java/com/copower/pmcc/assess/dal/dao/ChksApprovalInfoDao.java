package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfoExample;
import com.copower.pmcc.assess.dal.mapper.ChksApprovalInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/21
 * @time: 16:59
 */
@Repository
public class ChksApprovalInfoDao {
    @Autowired
    private ChksApprovalInfoMapper chksApprovalInfoMapper;

    public void addChksApprovalInfo(ChksApprovalInfo chksApprovalInfo) {
        chksApprovalInfoMapper.insertSelective(chksApprovalInfo);
    }
    public void updateChksApprovalInfo(ChksApprovalInfo chksApprovalInfo) {
        chksApprovalInfoMapper.updateByPrimaryKeySelective(chksApprovalInfo);
    }
    public void updateChksApprovalInfo(Integer id, String userAccount) {
        ChksApprovalInfo chksApprovalInfo = chksApprovalInfoMapper.selectByPrimaryKey(id);
        String personString = chksApprovalInfo.getPersonString();
        if(!personString.contains(userAccount)) {
            chksApprovalInfo.setPersonString(String.format("%s,%s", chksApprovalInfo.getPersonString(), userAccount));
            chksApprovalInfoMapper.updateByPrimaryKeySelective(chksApprovalInfo);
        }
    }

    public List<ChksApprovalInfo> getChksApprovalInfoList(ChksApprovalInfo chksApprovalInfo) {
        ChksApprovalInfoExample example = new ChksApprovalInfoExample();
        MybatisUtils.convertObj2Example(chksApprovalInfo, example);
        example.setOrderByClause(" id ");
        return chksApprovalInfoMapper.selectByExample(example);
    }
    public ChksApprovalInfo getChksApprovalInfoById(Integer id) {
        return chksApprovalInfoMapper.selectByPrimaryKey(id);
    }
}
