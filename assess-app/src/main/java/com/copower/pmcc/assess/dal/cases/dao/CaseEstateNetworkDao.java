package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandStateExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetworkExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateNetworkMapper;
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
public class CaseEstateNetworkDao {
    @Autowired
    private CaseEstateNetworkMapper caseEstateNetworkMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateNetwork getEstateNetworkById(Integer id) {
        return caseEstateNetworkMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateNetwork
     * @return
     */
    public List<CaseEstateNetwork> getEstateNetworkList(CaseEstateNetwork caseEstateNetwork) {
        CaseEstateNetworkExample example = new CaseEstateNetworkExample();
        MybatisUtils.convertObj2Example(caseEstateNetwork, example);
        return caseEstateNetworkMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateNetwork
     * @return
     */
    public boolean addEstateNetwork(CaseEstateNetwork caseEstateNetwork) {
        return caseEstateNetworkMapper.insertSelective(caseEstateNetwork) > 0;
    }

    /**
     * 编辑
     * @param caseEstateNetwork
     * @return
     */
    public boolean updateEstateNetwork(CaseEstateNetwork caseEstateNetwork) {
        return caseEstateNetworkMapper.updateByPrimaryKeySelective(caseEstateNetwork) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return caseEstateNetworkMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param estateId
     * @return
     */
    public int countByEstateId(Integer estateId){
        CaseEstateNetworkExample example = new CaseEstateNetworkExample();
        example.createCriteria().andEstateIdEqualTo(estateId);
        return caseEstateNetworkMapper.countByExample(example);
    }
}