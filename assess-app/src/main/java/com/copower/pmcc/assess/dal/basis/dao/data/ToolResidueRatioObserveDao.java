package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserve;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserveExample;
import com.copower.pmcc.assess.dal.basis.mapper.ToolResidueRatioObserveMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/03 10:29
 */

@Repository
public class ToolResidueRatioObserveDao {
    @Autowired
    private ToolResidueRatioObserveMapper toolResidueRatioObserveMapper;

    public void batchInset(List<ToolResidueRatioObserve> list){
        toolResidueRatioObserveMapper.batchInsert(list) ;
    }

    /**
     * @param where
     * @return
     */
    public List<ToolResidueRatioObserve> getToolResidueRatioObserve(ToolResidueRatioObserve where) {
        ToolResidueRatioObserveExample example = new ToolResidueRatioObserveExample();
        MybatisUtils.convertObj2Example(where, example);
        return toolResidueRatioObserveMapper.selectByExample(example);
    }


    /**
     * 新增数据
     *
     * @param data
     * @return
     */
    public boolean addToolResidueRatioObserve(ToolResidueRatioObserve data) {
        return toolResidueRatioObserveMapper.insertSelective(data) == 1;
    }

    /**
     * 更新数据
     *
     * @param data
     * @return
     */
    public boolean modifyToolResidueRatioObserve(ToolResidueRatioObserve data) {
        return toolResidueRatioObserveMapper.updateByPrimaryKeySelective(data) == 1;
    }

    /**
     * 根据条件更新
     *
     * @param data
     * @param where
     * @return
     */
    public int modifyToolResidueRatioObserve(ToolResidueRatioObserve data, ToolResidueRatioObserve where) {
        ToolResidueRatioObserveExample example = new ToolResidueRatioObserveExample();
        MybatisUtils.convertObj2Example(where, example);
        return toolResidueRatioObserveMapper.updateByExampleSelective(data, example);
    }
}
