package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncome;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeMapper;
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
public class MdIncomeDao {
    @Autowired
    private MdIncomeMapper mdIncomeMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdIncome getIncomeById(Integer id) {
        return mdIncomeMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdIncome
     * @return
     */
    public List<MdIncome> getIncomeList(MdIncome mdIncome) {
        MdIncomeExample example = new MdIncomeExample();
        MybatisUtils.convertObj2Example(mdIncome, example);
        return mdIncomeMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdIncome
     * @return
     */
    public int addIncome(MdIncome mdIncome) {
        mdIncomeMapper.insertSelective(mdIncome);
        return mdIncome.getId();
    }

    /**
     * 编辑
     * @param mdIncome
     * @return
     */
    public boolean updateIncome(MdIncome mdIncome) {
        return mdIncomeMapper.updateByPrimaryKeySelective(mdIncome) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteIncome(Integer id){
        return mdIncomeMapper.deleteByPrimaryKey(id) > 0;
    }

}