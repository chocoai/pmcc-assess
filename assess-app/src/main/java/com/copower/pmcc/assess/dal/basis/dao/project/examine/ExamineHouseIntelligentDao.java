package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligent;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligentExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseIntelligentMapper;
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
public class ExamineHouseIntelligentDao {
    @Autowired
    private ExamineHouseIntelligentMapper examineHouseIntelligentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseIntelligent getHouseIntelligentById(Integer id) {
        return examineHouseIntelligentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseIntelligent
     * @return
     */
    public List<ExamineHouseIntelligent> getHouseIntelligentList(ExamineHouseIntelligent examineHouseIntelligent) {
        ExamineHouseIntelligentExample example = new ExamineHouseIntelligentExample();
        MybatisUtils.convertObj2Example(examineHouseIntelligent, example);
        return examineHouseIntelligentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseIntelligent
     * @return
     */
    public boolean addHouseIntelligent(ExamineHouseIntelligent examineHouseIntelligent) {
        return examineHouseIntelligentMapper.insertSelective(examineHouseIntelligent) > 0;
    }

    /**
     * 编辑
     * @param examineHouseIntelligent
     * @return
     */
    public boolean updateHouseIntelligent(ExamineHouseIntelligent examineHouseIntelligent) {
        return examineHouseIntelligentMapper.updateByPrimaryKeySelective(examineHouseIntelligent) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseIntelligent(Integer id){
        return examineHouseIntelligentMapper.deleteByPrimaryKey(id) > 0;
    }

}