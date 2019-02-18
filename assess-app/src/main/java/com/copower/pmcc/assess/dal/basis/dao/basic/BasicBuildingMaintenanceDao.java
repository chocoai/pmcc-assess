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

    public BasicBuildingMaintenance getBasicBuildingMaintenanceById(Integer id)throws SQLException {
        return basicBuildingMaintenanceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance)throws SQLException{
        basicBuildingMaintenanceMapper.insertSelective(basicBuildingMaintenance);
        return basicBuildingMaintenance.getId();
    }

    public boolean updateBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance)throws SQLException{
        return basicBuildingMaintenanceMapper.updateByPrimaryKeySelective(basicBuildingMaintenance)==1;
    }

    public void removeBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance)throws SQLException{
        BasicBuildingMaintenanceExample example = new BasicBuildingMaintenanceExample();
        MybatisUtils.convertObj2Example(basicBuildingMaintenance, example);
        basicBuildingMaintenanceMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingMaintenance(Integer id)throws SQLException{
        return  basicBuildingMaintenanceMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicBuildingMaintenance> basicBuildingMaintenanceList(BasicBuildingMaintenance basicBuildingMaintenance)throws SQLException{
        BasicBuildingMaintenanceExample example = new BasicBuildingMaintenanceExample();
        MybatisUtils.convertObj2Example(basicBuildingMaintenance, example);
        return basicBuildingMaintenanceMapper.selectByExample(example);
    }
}
