package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareResult;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareResultExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdMarketCompareResultMapper;
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
public class MdMarketCompareResultDao {
    @Autowired
    private MdMarketCompareResultMapper mdMarketCompareResultMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdMarketCompareResult getMarketCompareResultById(Integer id) {
        return mdMarketCompareResultMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMarketCompareResult
     * @return
     */
    public List<MdMarketCompareResult> getMarketCompareResultList(MdMarketCompareResult examineMarketCompareResult) {
        MdMarketCompareResultExample example = new MdMarketCompareResultExample();
        MybatisUtils.convertObj2Example(examineMarketCompareResult, example);
        return mdMarketCompareResultMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMarketCompareResult
     * @return
     */
    public boolean addMarketCompareResult(MdMarketCompareResult examineMarketCompareResult) {
        return mdMarketCompareResultMapper.insertSelective(examineMarketCompareResult) > 0;
    }

    /**
     * 编辑
     * @param examineMarketCompareResult
     * @return
     */
    public boolean updateMarketCompareResult(MdMarketCompareResult examineMarketCompareResult) {
        return mdMarketCompareResultMapper.updateByPrimaryKeySelective(examineMarketCompareResult) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMarketCompareResult(Integer id){
        return mdMarketCompareResultMapper.deleteByPrimaryKey(id) > 0;
    }

}