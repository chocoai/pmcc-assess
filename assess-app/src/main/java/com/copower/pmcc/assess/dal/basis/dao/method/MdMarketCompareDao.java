package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdMarketCompareMapper;
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
public class MdMarketCompareDao {
    @Autowired
    private MdMarketCompareMapper mdMarketCompareMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdMarketCompare getMarketCompareById(Integer id) {
        return mdMarketCompareMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMarketCompare
     * @return
     */
    public List<MdMarketCompare> getMarketCompareList(MdMarketCompare examineMarketCompare) {
        MdMarketCompareExample example = new MdMarketCompareExample();
        MybatisUtils.convertObj2Example(examineMarketCompare, example);
        return mdMarketCompareMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMarketCompare
     * @return
     */
    public boolean addMarketCompare(MdMarketCompare examineMarketCompare) {
        return mdMarketCompareMapper.insertSelective(examineMarketCompare) > 0;
    }

    /**
     * 编辑
     * @param examineMarketCompare
     * @return
     */
    public boolean updateMarketCompare(MdMarketCompare examineMarketCompare) {
        return mdMarketCompareMapper.updateByPrimaryKeySelective(examineMarketCompare) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMarketCompare(Integer id){
        return mdMarketCompareMapper.deleteByPrimaryKey(id) > 0;
    }

}