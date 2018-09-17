package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupply;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateSupplyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/14 16:52
 * @Description:
 */
@Repository
public class CaseEstateSupplyDao {
    @Autowired
    private CaseEstateSupplyMapper caseEstateSupplyMapper;


    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateSupply getEstateNetworkById(Integer id) {
        return caseEstateSupplyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateNetwork
     * @return
     */
    public List<CaseEstateSupply> getEstateNetworkList(CaseEstateSupply caseEstateNetwork) {
        CaseEstateSupplyExample example = new CaseEstateSupplyExample();
        MybatisUtils.convertObj2Example(caseEstateNetwork, example);
        return caseEstateSupplyMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateNetwork
     * @return
     */
    public boolean addEstateNetwork(CaseEstateSupply caseEstateNetwork) {
        return caseEstateSupplyMapper.insertSelective(caseEstateNetwork) > 0;
    }

    /**
     * 编辑
     * @param caseEstateNetwork
     * @return
     */
    public boolean updateEstateNetwork(CaseEstateSupply caseEstateNetwork) {
        return caseEstateSupplyMapper.updateByPrimaryKeySelective(caseEstateNetwork) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return caseEstateSupplyMapper.deleteByPrimaryKey(id) > 0;
    }
}
