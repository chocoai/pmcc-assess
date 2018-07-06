package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenance;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenanceExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingMaintenanceMapper;
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
public class CaseBuildingMaintenanceDao {
    @Autowired
    private CaseBuildingMaintenanceMapper caseBuildingMaintenanceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuildingMaintenance getBuildingMaintenanceById(Integer id) {
        return caseBuildingMaintenanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuildingMaintenance
     * @return
     */
    public List<CaseBuildingMaintenance> getBuildingMaintenanceList(CaseBuildingMaintenance caseBuildingMaintenance) {
        CaseBuildingMaintenanceExample example = new CaseBuildingMaintenanceExample();
        MybatisUtils.convertObj2Example(caseBuildingMaintenance, example);
        return caseBuildingMaintenanceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuildingMaintenance
     * @return
     */
    public boolean addBuildingMaintenance(CaseBuildingMaintenance caseBuildingMaintenance) {
        return caseBuildingMaintenanceMapper.insertSelective(caseBuildingMaintenance) > 0;
    }

    /**
     * 编辑
     * @param caseBuildingMaintenance
     * @return
     */
    public boolean updateBuildingMaintenance(CaseBuildingMaintenance caseBuildingMaintenance) {
        return caseBuildingMaintenanceMapper.updateByPrimaryKeySelective(caseBuildingMaintenance) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingMaintenance(Integer id){
        return caseBuildingMaintenanceMapper.deleteByPrimaryKey(id) > 0;
    }

}