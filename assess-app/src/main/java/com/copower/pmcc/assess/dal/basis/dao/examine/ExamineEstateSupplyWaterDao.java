package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyWater;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyWaterExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateSupplyWaterMapper;
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
public class ExamineEstateSupplyWaterDao {
    @Autowired
    private ExamineEstateSupplyWaterMapper examineEstateSupplyWaterMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateSupplyWater getEstateSupplyWaterById(Integer id) {
        return examineEstateSupplyWaterMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateSupplyWater
     * @return
     */
    public List<ExamineEstateSupplyWater> getEstateSupplyWaterList(ExamineEstateSupplyWater examineEstateSupplyWater) {
        ExamineEstateSupplyWaterExample example = new ExamineEstateSupplyWaterExample();
        MybatisUtils.convertObj2Example(examineEstateSupplyWater, example);
        return examineEstateSupplyWaterMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateSupplyWater
     * @return
     */
    public boolean addEstateSupplyWater(ExamineEstateSupplyWater examineEstateSupplyWater) {
        return examineEstateSupplyWaterMapper.insertSelective(examineEstateSupplyWater) > 0;
    }

    /**
     * 编辑
     * @param examineEstateSupplyWater
     * @return
     */
    public boolean updateEstateSupplyWater(ExamineEstateSupplyWater examineEstateSupplyWater) {
        return examineEstateSupplyWaterMapper.updateByPrimaryKeySelective(examineEstateSupplyWater) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSupplyWater(Integer id){
        return examineEstateSupplyWaterMapper.deleteByPrimaryKey(id) > 0;
    }

}