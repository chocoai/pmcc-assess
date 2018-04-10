package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ChksApprovalBusiness;
import com.copower.pmcc.assess.dal.entity.ChksApprovalBusinessExample;
import com.copower.pmcc.assess.dal.mapper.ChksApprovalBusinessMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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
public class ChksApprovalBusinessDao {
    @Autowired
    private ChksApprovalBusinessMapper chksApprovalBusinessMapper;

    public void addChksApprovalBusiness(ChksApprovalBusiness chksApprovalBusiness) {
        chksApprovalBusinessMapper.insertSelective(chksApprovalBusiness);
    }

    public void updateChksApprovalBusiness(ChksApprovalBusiness chksApprovalBusiness) {
        chksApprovalBusinessMapper.updateByPrimaryKeySelective(chksApprovalBusiness);
    }

    public List<ChksApprovalBusiness> getChksApprovalBusinessList(ChksApprovalBusiness chksApprovalBusiness, String search) {
        ChksApprovalBusinessExample example = new ChksApprovalBusinessExample();

        MybatisUtils.convertObj2Example(chksApprovalBusiness, example);

        if (StringUtils.isBlank(search)) {
            search = "%%";
        }
        //ChksApprovalBusinessExample.Criteria criteria = example.or().andProcessCnNameLike(search);
        //ChksApprovalBusinessExample.Criteria criteria1 = example.or().andProcessTitleLike(search);
        //MybatisUtils.convertObj2Criteria(chksApprovalBusiness, criteria);
        //MybatisUtils.convertObj2Criteria(chksApprovalBusiness, criteria1);
        return chksApprovalBusinessMapper.selectByExample(example);
    }

    public ChksApprovalBusiness getChksApprovalBusinessById(Integer id) {
        return chksApprovalBusinessMapper.selectByPrimaryKey(id);
    }
}
