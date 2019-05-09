package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostApproachMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class MdCostApproachDao {
    @Autowired
    private MdCostApproachMapper mdCostApproachMapper;

    public MdCostApproach getMdCostApproach(Integer id) {
        return mdCostApproachMapper.selectByPrimaryKey(id);
    }


    public List<MdCostApproach> getObjectList(MdCostApproach mdCostApproach) {
        MdCostApproachExample example = new MdCostApproachExample();
        MybatisUtils.convertObj2Example(mdCostApproach, example);
        return mdCostApproachMapper.selectByExample(example);
    }

    public boolean addMdCostApproach(MdCostApproach mdCostApproach) {
        int i = mdCostApproachMapper.insert(mdCostApproach);
        return i > 0;
    }

    public boolean editMdCostApproach(MdCostApproach mdCostApproach) {
        int i = mdCostApproachMapper.updateByPrimaryKeySelective(mdCostApproach);
        return i > 0;
    }

    public boolean deleteMdCostApproach(Integer id) {
        int i = mdCostApproachMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public MdCostApproach getMdCostApproach(MdCostApproach mdCostApproach) {
        MdCostApproachExample example = new MdCostApproachExample();
        MybatisUtils.convertObj2Example(mdCostApproach, example);
        List<MdCostApproach> mdCostApproachs = mdCostApproachMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdCostApproachs)) return mdCostApproachs.get(0);
        return null;
    }

}
