package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinance;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinanceExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingFinanceMapper;
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
public class ExamineMatchingFinanceDao {
    @Autowired
    private ExamineMatchingFinanceMapper examineMatchingFinanceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingFinance getMatchingFinanceById(Integer id) {
        return examineMatchingFinanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingFinance
     * @return
     */
    public List<ExamineMatchingFinance> getMatchingFinanceList(ExamineMatchingFinance examineMatchingFinance) {
        ExamineMatchingFinanceExample example = new ExamineMatchingFinanceExample();
        MybatisUtils.convertObj2Example(examineMatchingFinance, example);
        return examineMatchingFinanceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingFinance
     * @return
     */
    public boolean addMatchingFinance(ExamineMatchingFinance examineMatchingFinance) {
        return examineMatchingFinanceMapper.insertSelective(examineMatchingFinance) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingFinance
     * @return
     */
    public boolean updateMatchingFinance(ExamineMatchingFinance examineMatchingFinance) {
        return examineMatchingFinanceMapper.updateByPrimaryKeySelective(examineMatchingFinance) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingFinance(Integer id){
        return examineMatchingFinanceMapper.deleteByPrimaryKey(id) > 0;
    }

}