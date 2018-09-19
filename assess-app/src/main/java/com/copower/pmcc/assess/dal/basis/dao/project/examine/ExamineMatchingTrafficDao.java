package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTraffic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTrafficExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingTrafficMapper;
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
public class ExamineMatchingTrafficDao {
    @Autowired
    private ExamineMatchingTrafficMapper examineMatchingTrafficMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingTraffic getMatchingTrafficById(Integer id) {
        return examineMatchingTrafficMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingTraffic
     * @return
     */
    public List<ExamineMatchingTraffic> getMatchingTrafficList(ExamineMatchingTraffic examineMatchingTraffic) {
        ExamineMatchingTrafficExample example = new ExamineMatchingTrafficExample();
        MybatisUtils.convertObj2Example(examineMatchingTraffic, example);
        return examineMatchingTrafficMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingTraffic
     * @return
     */
    public boolean addMatchingTraffic(ExamineMatchingTraffic examineMatchingTraffic) {
        return examineMatchingTrafficMapper.insertSelective(examineMatchingTraffic) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingTraffic
     * @return
     */
    public boolean updateMatchingTraffic(ExamineMatchingTraffic examineMatchingTraffic) {
        return examineMatchingTrafficMapper.updateByPrimaryKeySelective(examineMatchingTraffic) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingTraffic(Integer id){
        return examineMatchingTrafficMapper.deleteByPrimaryKey(id) > 0;
    }

}