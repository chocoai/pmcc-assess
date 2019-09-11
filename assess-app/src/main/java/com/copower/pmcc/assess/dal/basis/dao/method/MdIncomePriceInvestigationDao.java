package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomePriceInvestigation;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomePriceInvestigationExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomePriceInvestigationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class MdIncomePriceInvestigationDao {
    @Autowired
    private MdIncomePriceInvestigationMapper mdIncomePriceInvestigationMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomePriceInvestigation getIncomePriceInvestigationById(Integer id) {
        return mdIncomePriceInvestigationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomePriceInvestigation
     * @return
     */
    public MdIncomePriceInvestigation getIncomePriceInvestigation(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        MdIncomePriceInvestigationExample example = new MdIncomePriceInvestigationExample();
        MybatisUtils.convertObj2Example(mdIncomePriceInvestigation, example);
        List<MdIncomePriceInvestigation> mdIncomePriceInvestigations = mdIncomePriceInvestigationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomePriceInvestigations)) return mdIncomePriceInvestigations.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomePriceInvestigation
     * @return
     */
    public List<MdIncomePriceInvestigation> getIncomePriceInvestigationList(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        MdIncomePriceInvestigationExample example = new MdIncomePriceInvestigationExample();
        MybatisUtils.convertObj2Example(mdIncomePriceInvestigation, example);
        return mdIncomePriceInvestigationMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomePriceInvestigation
     * @return
     */
    public int addIncomePriceInvestigation(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        mdIncomePriceInvestigationMapper.insertSelective(mdIncomePriceInvestigation);
        return mdIncomePriceInvestigation.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomePriceInvestigation
     * @return
     */
    public boolean updateIncomePriceInvestigation(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        return mdIncomePriceInvestigationMapper.updateByPrimaryKeySelective(mdIncomePriceInvestigation) > 0;
    }

    public boolean updateIncomePriceInvestigation(MdIncomePriceInvestigation where,MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        MdIncomePriceInvestigationExample example = new MdIncomePriceInvestigationExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomePriceInvestigationMapper.updateByExampleSelective(mdIncomePriceInvestigation,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteIncomePriceInvestigation(Integer id) {
        return mdIncomePriceInvestigationMapper.deleteByPrimaryKey(id) > 0;
    }

}