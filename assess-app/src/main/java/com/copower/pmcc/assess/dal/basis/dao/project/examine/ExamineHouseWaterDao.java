package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWater;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWaterExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseWaterMapper;
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
public class ExamineHouseWaterDao {
    @Autowired
    private ExamineHouseWaterMapper examineHouseWaterMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseWater getHouseWaterById(Integer id) {
        return examineHouseWaterMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseWater
     * @return
     */
    public List<ExamineHouseWater> getHouseWaterList(ExamineHouseWater examineHouseWater) {
        ExamineHouseWaterExample example = new ExamineHouseWaterExample();
        MybatisUtils.convertObj2Example(examineHouseWater, example);
        return examineHouseWaterMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseWater
     * @return
     */
    public boolean addHouseWater(ExamineHouseWater examineHouseWater) {
        return examineHouseWaterMapper.insertSelective(examineHouseWater) > 0;
    }

    /**
     * 编辑
     * @param examineHouseWater
     * @return
     */
    public boolean updateHouseWater(ExamineHouseWater examineHouseWater) {
        return examineHouseWaterMapper.updateByPrimaryKeySelective(examineHouseWater) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseWater(Integer id){
        return examineHouseWaterMapper.deleteByPrimaryKey(id) > 0;
    }

}