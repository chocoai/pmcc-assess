package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRestaurant;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRestaurantExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingRestaurantMapper;
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
public class ExamineMatchingRestaurantDao {
    @Autowired
    private ExamineMatchingRestaurantMapper examineMatchingRestaurantMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingRestaurant getMatchingRestaurantById(Integer id) {
        return examineMatchingRestaurantMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingRestaurant
     * @return
     */
    public List<ExamineMatchingRestaurant> getMatchingRestaurantList(ExamineMatchingRestaurant examineMatchingRestaurant) {
        ExamineMatchingRestaurantExample example = new ExamineMatchingRestaurantExample();
        MybatisUtils.convertObj2Example(examineMatchingRestaurant, example);
        return examineMatchingRestaurantMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingRestaurant
     * @return
     */
    public boolean addMatchingRestaurant(ExamineMatchingRestaurant examineMatchingRestaurant) {
        return examineMatchingRestaurantMapper.insertSelective(examineMatchingRestaurant) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingRestaurant
     * @return
     */
    public boolean updateMatchingRestaurant(ExamineMatchingRestaurant examineMatchingRestaurant) {
        return examineMatchingRestaurantMapper.updateByPrimaryKeySelective(examineMatchingRestaurant) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingRestaurant(Integer id){
        return examineMatchingRestaurantMapper.deleteByPrimaryKey(id) > 0;
    }

}