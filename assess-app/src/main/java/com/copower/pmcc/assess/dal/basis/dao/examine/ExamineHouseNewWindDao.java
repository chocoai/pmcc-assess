package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseNewWind;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseNewWindExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseNewWindMapper;
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
public class ExamineHouseNewWindDao {
    @Autowired
    private ExamineHouseNewWindMapper examineHouseNewWindMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseNewWind getHouseNewWindById(Integer id) {
        return examineHouseNewWindMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseNewWind
     * @return
     */
    public List<ExamineHouseNewWind> getHouseNewWindList(ExamineHouseNewWind examineHouseNewWind) {
        ExamineHouseNewWindExample example = new ExamineHouseNewWindExample();
        MybatisUtils.convertObj2Example(examineHouseNewWind, example);
        return examineHouseNewWindMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseNewWind
     * @return
     */
    public boolean addHouseNewWind(ExamineHouseNewWind examineHouseNewWind) {
        return examineHouseNewWindMapper.insertSelective(examineHouseNewWind) > 0;
    }

    /**
     * 编辑
     * @param examineHouseNewWind
     * @return
     */
    public boolean updateHouseNewWind(ExamineHouseNewWind examineHouseNewWind) {
        return examineHouseNewWindMapper.updateByPrimaryKeySelective(examineHouseNewWind) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseNewWind(Integer id){
        return examineHouseNewWindMapper.deleteByPrimaryKey(id) > 0;
    }

}