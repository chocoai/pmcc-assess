package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCost;
import com.copower.pmcc.assess.dal.basis.entity.MdCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:44
 * @Description:
 */
@Repository
public class MdCostDao {
    @Autowired
    private MdCostMapper mdCostMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdCost getMdCostById(Integer id) {
        return mdCostMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdCost
     * @return
     */
    public List<MdCost> getMdCostList(MdCost mdCost) {
        MdCostExample example = new MdCostExample();
        MybatisUtils.convertObj2Example(mdCost, example);
        return mdCostMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdCost
     * @return
     */
    public int addMdCost(MdCost mdCost) {
        mdCostMapper.insertSelective(mdCost);
        return mdCost.getId();
    }

    /**
     * 编辑
     * @param mdCost
     * @return
     */
    public boolean updateMdCost(MdCost mdCost) {
        return mdCostMapper.updateByPrimaryKeySelective(mdCost) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return mdCostMapper.deleteByPrimaryKey(id) > 0;
    }
}
