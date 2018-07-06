package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseAirConditioner;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseAirConditionerExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseAirConditionerMapper;
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
public class ExamineHouseAirConditionerDao {
    @Autowired
    private ExamineHouseAirConditionerMapper examineHouseAirConditionerMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseAirConditioner getHouseAirConditionerById(Integer id) {
        return examineHouseAirConditionerMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseAirConditioner
     * @return
     */
    public List<ExamineHouseAirConditioner> getHouseAirConditionerList(ExamineHouseAirConditioner examineHouseAirConditioner) {
        ExamineHouseAirConditionerExample example = new ExamineHouseAirConditionerExample();
        MybatisUtils.convertObj2Example(examineHouseAirConditioner, example);
        return examineHouseAirConditionerMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseAirConditioner
     * @return
     */
    public boolean addHouseAirConditioner(ExamineHouseAirConditioner examineHouseAirConditioner) {
        return examineHouseAirConditionerMapper.insertSelective(examineHouseAirConditioner) > 0;
    }

    /**
     * 编辑
     * @param examineHouseAirConditioner
     * @return
     */
    public boolean updateHouseAirConditioner(ExamineHouseAirConditioner examineHouseAirConditioner) {
        return examineHouseAirConditionerMapper.updateByPrimaryKeySelective(examineHouseAirConditioner) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseAirConditioner(Integer id){
        return examineHouseAirConditionerMapper.deleteByPrimaryKey(id) > 0;
    }

}