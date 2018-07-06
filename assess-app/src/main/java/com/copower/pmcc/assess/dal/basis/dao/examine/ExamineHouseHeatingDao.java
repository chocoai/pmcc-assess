package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseHeating;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseHeatingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseHeatingMapper;
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
public class ExamineHouseHeatingDao {
    @Autowired
    private ExamineHouseHeatingMapper examineHouseHeatingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseHeating getHouseHeatingById(Integer id) {
        return examineHouseHeatingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseHeating
     * @return
     */
    public List<ExamineHouseHeating> getHouseHeatingList(ExamineHouseHeating examineHouseHeating) {
        ExamineHouseHeatingExample example = new ExamineHouseHeatingExample();
        MybatisUtils.convertObj2Example(examineHouseHeating, example);
        return examineHouseHeatingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseHeating
     * @return
     */
    public boolean addHouseHeating(ExamineHouseHeating examineHouseHeating) {
        return examineHouseHeatingMapper.insertSelective(examineHouseHeating) > 0;
    }

    /**
     * 编辑
     * @param examineHouseHeating
     * @return
     */
    public boolean updateHouseHeating(ExamineHouseHeating examineHouseHeating) {
        return examineHouseHeatingMapper.updateByPrimaryKeySelective(examineHouseHeating) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseHeating(Integer id){
        return examineHouseHeatingMapper.deleteByPrimaryKey(id) > 0;
    }

}