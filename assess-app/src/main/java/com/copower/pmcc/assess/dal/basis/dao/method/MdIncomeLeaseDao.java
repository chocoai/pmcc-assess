package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastExample;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeLeaseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class MdIncomeLeaseDao {
    @Autowired
    private MdIncomeLeaseMapper mdIncomeLeaseMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeLease getIncomeLeaseById(Integer id) {
        return mdIncomeLeaseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeLease
     * @return
     */
    public List<MdIncomeLease> getIncomeLeaseList(MdIncomeLease mdIncomeLease) {
        MdIncomeLeaseExample example = new MdIncomeLeaseExample();
        MybatisUtils.convertObj2Example(mdIncomeLease, example);
        return mdIncomeLeaseMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeLease
     * @return
     */
    public int addIncomeLease(MdIncomeLease mdIncomeLease) {
        mdIncomeLeaseMapper.insertSelective(mdIncomeLease);
        return mdIncomeLease.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomeLease
     * @return
     */
    public boolean updateIncomeLease(MdIncomeLease mdIncomeLease) {
        return mdIncomeLeaseMapper.updateByPrimaryKeySelective(mdIncomeLease) > 0;
    }

    /**
     * 更新
     *
     * @param mdIncomeLease
     * @return
     */
    public boolean updateIncomeLease(MdIncomeLease where, MdIncomeLease mdIncomeLease) {
        MdIncomeLeaseExample example = new MdIncomeLeaseExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeLeaseMapper.updateByExampleSelective(mdIncomeLease, example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteLease(Integer id) {
        return mdIncomeLeaseMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteLeaseBySectionId(Integer sectionId) {
        MdIncomeLeaseExample example = new MdIncomeLeaseExample();
        example.createCriteria().andSectionIdEqualTo(sectionId);
        return mdIncomeLeaseMapper.countByExample(example) > 0;
    }

    /**
     * 获取数量
     *
     * @param sectionId
     * @return
     */
    public int getCountBySectionId(Integer sectionId) {
        MdIncomeLeaseExample example = new MdIncomeLeaseExample();
        example.createCriteria().andSectionIdEqualTo(sectionId);
        return mdIncomeLeaseMapper.countByExample(example);
    }

}