package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRestaurant;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRestaurantExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingRestaurantMapper;
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
public class CaseMatchingRestaurantDao {
    @Autowired
    private CaseMatchingRestaurantMapper caseMatchingRestaurantMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingRestaurant getMatchingRestaurantById(Integer id) {
        return caseMatchingRestaurantMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingRestaurant
     * @return
     */
    public List<CaseMatchingRestaurant> getMatchingRestaurantList(CaseMatchingRestaurant caseMatchingRestaurant) {
        CaseMatchingRestaurantExample example = new CaseMatchingRestaurantExample();
        MybatisUtils.convertObj2Example(caseMatchingRestaurant, example);
        return caseMatchingRestaurantMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingRestaurant
     * @return
     */
    public boolean addMatchingRestaurant(CaseMatchingRestaurant caseMatchingRestaurant) {
        return caseMatchingRestaurantMapper.insertSelective(caseMatchingRestaurant) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingRestaurant
     * @return
     */
    public boolean updateMatchingRestaurant(CaseMatchingRestaurant caseMatchingRestaurant) {
        return caseMatchingRestaurantMapper.updateByPrimaryKeySelective(caseMatchingRestaurant) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingRestaurant(Integer id){
        return caseMatchingRestaurantMapper.deleteByPrimaryKey(id) > 0;
    }

}