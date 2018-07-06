package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRecreation;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRecreationExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingRecreationMapper;
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
public class ExamineMatchingRecreationDao {
    @Autowired
    private ExamineMatchingRecreationMapper examineMatchingRecreationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingRecreation getMatchingRecreationById(Integer id) {
        return examineMatchingRecreationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingRecreation
     * @return
     */
    public List<ExamineMatchingRecreation> getMatchingRecreationList(ExamineMatchingRecreation examineMatchingRecreation) {
        ExamineMatchingRecreationExample example = new ExamineMatchingRecreationExample();
        MybatisUtils.convertObj2Example(examineMatchingRecreation, example);
        return examineMatchingRecreationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingRecreation
     * @return
     */
    public boolean addMatchingRecreation(ExamineMatchingRecreation examineMatchingRecreation) {
        return examineMatchingRecreationMapper.insertSelective(examineMatchingRecreation) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingRecreation
     * @return
     */
    public boolean updateMatchingRecreation(ExamineMatchingRecreation examineMatchingRecreation) {
        return examineMatchingRecreationMapper.updateByPrimaryKeySelective(examineMatchingRecreation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingRecreation(Integer id){
        return examineMatchingRecreationMapper.deleteByPrimaryKey(id) > 0;
    }

}