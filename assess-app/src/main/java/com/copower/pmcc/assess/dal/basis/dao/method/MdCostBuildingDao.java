package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostBuilding;
import com.copower.pmcc.assess.dal.basis.entity.MdCostBuildingExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostBuildingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:45
 * @Description:
 */

@Repository
public class MdCostBuildingDao {

    @Autowired
    private MdCostBuildingMapper mdCostBuildingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdCostBuilding getEstateNetworkById(Integer id) {
        return mdCostBuildingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdCostBuilding
     * @return
     */
    public List<MdCostBuilding> getEstateNetworkList(MdCostBuilding mdCostBuilding) {
        MdCostBuildingExample example = new MdCostBuildingExample();
        MybatisUtils.convertObj2Example(mdCostBuilding, example);
        return mdCostBuildingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdCostBuilding
     * @return
     */
    public boolean addEstateNetwork(MdCostBuilding mdCostBuilding) {
        return mdCostBuildingMapper.insertSelective(mdCostBuilding) > 0;
    }

    /**
     * 编辑
     * @param mdCostBuilding
     * @return
     */
    public boolean updateEstateNetwork(MdCostBuilding mdCostBuilding) {
        return mdCostBuildingMapper.updateByPrimaryKeySelective(mdCostBuilding) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return mdCostBuildingMapper.deleteByPrimaryKey(id) > 0;
    }

}
