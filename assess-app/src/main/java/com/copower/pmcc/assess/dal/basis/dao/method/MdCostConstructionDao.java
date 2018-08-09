package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstructionExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostConstructionMapper;
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
public class MdCostConstructionDao {
    
    @Autowired
    private MdCostConstructionMapper mdCostConstructionMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdCostConstruction getEstateNetworkById(Integer id) {
        return mdCostConstructionMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdCostConstruction
     * @return
     */
    public List<MdCostConstruction> getEstateNetworkList(MdCostConstruction mdCostConstruction) {
        MdCostConstructionExample example = new MdCostConstructionExample();
        MybatisUtils.convertObj2Example(mdCostConstruction, example);
        return mdCostConstructionMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdCostConstruction
     * @return
     */
    public boolean addEstateNetwork(MdCostConstruction mdCostConstruction) {
        return mdCostConstructionMapper.insertSelective(mdCostConstruction) > 0;
    }

    /**
     * 编辑
     * @param mdCostConstruction
     * @return
     */
    public boolean updateEstateNetwork(MdCostConstruction mdCostConstruction) {
        return mdCostConstructionMapper.updateByPrimaryKeySelective(mdCostConstruction) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return mdCostConstructionMapper.deleteByPrimaryKey(id) > 0;
    }
}
