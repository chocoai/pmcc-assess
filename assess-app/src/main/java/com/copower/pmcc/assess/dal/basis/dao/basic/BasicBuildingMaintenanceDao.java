package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenance;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenanceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingMaintenanceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:41
 * @Description:
 */
@Repository
public class BasicBuildingMaintenanceDao {
    @Autowired
    private BasicBuildingMaintenanceMapper basicBuildingMaintenanceMapper;

    public BasicBuildingMaintenance getBasicBuildingMaintenanceById(Integer id) {
        return basicBuildingMaintenanceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance) {
        basicBuildingMaintenanceMapper.insertSelective(basicBuildingMaintenance);
        return basicBuildingMaintenance.getId();
    }

    public boolean updateBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance, boolean updateNull) {
        basicBuildingMaintenance.setBisDelete(false);
        return updateNull ? basicBuildingMaintenanceMapper.updateByPrimaryKey(basicBuildingMaintenance) == 1 : basicBuildingMaintenanceMapper.updateByPrimaryKeySelective(basicBuildingMaintenance) == 1;
    }

    public void removeBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance) {
        BasicBuildingMaintenanceExample example = new BasicBuildingMaintenanceExample();
        BasicBuildingMaintenanceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingMaintenance, criteria);
        basicBuildingMaintenanceMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingMaintenance(Integer id) {
        BasicBuildingMaintenance basicBuildingMaintenance = getBasicBuildingMaintenanceById(id);
        if (basicBuildingMaintenance == null) return false;
        basicBuildingMaintenance.setBisDelete(true);
        return basicBuildingMaintenanceMapper.updateByPrimaryKeySelective(basicBuildingMaintenance) == 1;
    }

    public List<BasicBuildingMaintenance> basicBuildingMaintenanceList(BasicBuildingMaintenance basicBuildingMaintenance) {
        BasicBuildingMaintenanceExample example = new BasicBuildingMaintenanceExample();
        BasicBuildingMaintenanceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingMaintenance, criteria);
        return basicBuildingMaintenanceMapper.selectByExample(example);
    }
}
