package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyPower;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyPowerExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateSupplyPowerMapper;
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
public class CaseEstateSupplyPowerDao {
    @Autowired
    private CaseEstateSupplyPowerMapper caseEstateSupplyPowerMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateSupplyPower getEstateSupplyPowerById(Integer id) {
        return caseEstateSupplyPowerMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateSupplyPower
     * @return
     */
    public List<CaseEstateSupplyPower> getEstateSupplyPowerList(CaseEstateSupplyPower caseEstateSupplyPower) {
        CaseEstateSupplyPowerExample example = new CaseEstateSupplyPowerExample();
        MybatisUtils.convertObj2Example(caseEstateSupplyPower, example);
        return caseEstateSupplyPowerMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateSupplyPower
     * @return
     */
    public boolean addEstateSupplyPower(CaseEstateSupplyPower caseEstateSupplyPower) {
        return caseEstateSupplyPowerMapper.insertSelective(caseEstateSupplyPower) > 0;
    }

    /**
     * 编辑
     * @param caseEstateSupplyPower
     * @return
     */
    public boolean updateEstateSupplyPower(CaseEstateSupplyPower caseEstateSupplyPower) {
        return caseEstateSupplyPowerMapper.updateByPrimaryKeySelective(caseEstateSupplyPower) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSupplyPower(Integer id){
        return caseEstateSupplyPowerMapper.deleteByPrimaryKey(id) > 0;
    }

}