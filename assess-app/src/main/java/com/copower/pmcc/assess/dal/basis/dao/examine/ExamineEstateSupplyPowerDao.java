package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyPower;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyPowerExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateSupplyPowerMapper;
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
public class ExamineEstateSupplyPowerDao {
    @Autowired
    private ExamineEstateSupplyPowerMapper examineEstateSupplyPowerMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateSupplyPower getEstateSupplyPowerById(Integer id) {
        return examineEstateSupplyPowerMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateSupplyPower
     * @return
     */
    public List<ExamineEstateSupplyPower> getEstateSupplyPowerList(ExamineEstateSupplyPower examineEstateSupplyPower) {
        ExamineEstateSupplyPowerExample example = new ExamineEstateSupplyPowerExample();
        MybatisUtils.convertObj2Example(examineEstateSupplyPower, example);
        return examineEstateSupplyPowerMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateSupplyPower
     * @return
     */
    public boolean addEstateSupplyPower(ExamineEstateSupplyPower examineEstateSupplyPower) {
        return examineEstateSupplyPowerMapper.insertSelective(examineEstateSupplyPower) > 0;
    }

    /**
     * 编辑
     * @param examineEstateSupplyPower
     * @return
     */
    public boolean updateEstateSupplyPower(ExamineEstateSupplyPower examineEstateSupplyPower) {
        return examineEstateSupplyPowerMapper.updateByPrimaryKeySelective(examineEstateSupplyPower) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSupplyPower(Integer id){
        return examineEstateSupplyPowerMapper.deleteByPrimaryKey(id) > 0;
    }

}