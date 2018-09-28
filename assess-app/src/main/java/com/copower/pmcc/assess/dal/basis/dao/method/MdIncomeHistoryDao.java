package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeHistory;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeHistoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeHistoryMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class MdIncomeHistoryDao {
    @Autowired
    private MdIncomeHistoryMapper mdIncomeHistoryMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeHistory getHistoryById(Integer id) {
        return mdIncomeHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeHistory
     * @return
     */
    public MdIncomeHistory getHistory(MdIncomeHistory mdIncomeHistory) {
        MdIncomeHistoryExample example = new MdIncomeHistoryExample();
        MybatisUtils.convertObj2Example(mdIncomeHistory, example);
        List<MdIncomeHistory> mdIncomeHistorys = mdIncomeHistoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeHistorys)) return mdIncomeHistorys.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeHistory
     * @return
     */
    public List<MdIncomeHistory> getHistoryList(MdIncomeHistory mdIncomeHistory) {
        MdIncomeHistoryExample example = new MdIncomeHistoryExample();
        MybatisUtils.convertObj2Example(mdIncomeHistory, example);
        return mdIncomeHistoryMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeHistory
     * @return
     */
    public int addHistory(MdIncomeHistory mdIncomeHistory) {
        mdIncomeHistoryMapper.insertSelective(mdIncomeHistory);
        return mdIncomeHistory.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomeHistory
     * @return
     */
    public boolean updateHistory(MdIncomeHistory mdIncomeHistory) {
        return mdIncomeHistoryMapper.updateByPrimaryKeySelective(mdIncomeHistory) > 0;
    }

    public boolean updateHistory(MdIncomeHistory where, MdIncomeHistory mdIncomeHistory) {
        MdIncomeHistoryExample example = new MdIncomeHistoryExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeHistoryMapper.updateByExampleSelective(mdIncomeHistory, example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteHistory(Integer id) {
        return mdIncomeHistoryMapper.deleteByPrimaryKey(id) > 0;
    }

}