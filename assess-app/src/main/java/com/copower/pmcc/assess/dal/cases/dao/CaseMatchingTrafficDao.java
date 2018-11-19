package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRestaurantExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTraffic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTrafficExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingTrafficMapper;
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
public class CaseMatchingTrafficDao {
    @Autowired
    private CaseMatchingTrafficMapper caseMatchingTrafficMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingTraffic getMatchingTrafficById(Integer id) {
        return caseMatchingTrafficMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingTraffic
     * @return
     */
    public List<CaseMatchingTraffic> getMatchingTrafficList(CaseMatchingTraffic caseMatchingTraffic) {
        CaseMatchingTrafficExample example = new CaseMatchingTrafficExample();
        MybatisUtils.convertObj2Example(caseMatchingTraffic, example);
        return caseMatchingTrafficMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingTraffic
     * @return
     */
    public boolean addMatchingTraffic(CaseMatchingTraffic caseMatchingTraffic) {
        return caseMatchingTrafficMapper.insertSelective(caseMatchingTraffic) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingTraffic
     * @return
     */
    public boolean updateMatchingTraffic(CaseMatchingTraffic caseMatchingTraffic) {
        return caseMatchingTrafficMapper.updateByPrimaryKeySelective(caseMatchingTraffic) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingTraffic(Integer id){
        return caseMatchingTrafficMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param estateId
     * @return
     */
    public int countByEstateId(Integer estateId,String type){
        CaseMatchingTrafficExample example = new CaseMatchingTrafficExample();
        example.createCriteria().andEstateIdEqualTo(estateId).andTypeEqualTo(type);
        return caseMatchingTrafficMapper.countByExample(example);
    }
}