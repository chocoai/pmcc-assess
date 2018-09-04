package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenance;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenanceExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuildingMaintenanceMapper;
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
public class ExamineBuildingMaintenanceDao {
    @Autowired
    private ExamineBuildingMaintenanceMapper examineBuildingMaintenanceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBuildingMaintenance getBuildingMaintenanceById(Integer id) {
        return examineBuildingMaintenanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineBuildingMaintenance
     * @return
     */
    public List<ExamineBuildingMaintenance> getBuildingMaintenanceList(ExamineBuildingMaintenance examineBuildingMaintenance) {
        ExamineBuildingMaintenanceExample example = new ExamineBuildingMaintenanceExample();
        MybatisUtils.convertObj2Example(examineBuildingMaintenance, example);
        return examineBuildingMaintenanceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBuildingMaintenance
     * @return
     */
    public boolean addBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance) {
        return examineBuildingMaintenanceMapper.insertSelective(examineBuildingMaintenance) > 0;
    }

    /**
     * 编辑
     * @param examineBuildingMaintenance
     * @return
     */
    public boolean updateBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance) {
        return examineBuildingMaintenanceMapper.updateByPrimaryKeySelective(examineBuildingMaintenance) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingMaintenance(Integer id){
        return examineBuildingMaintenanceMapper.deleteByPrimaryKey(id) > 0;
    }

    public void removeExamineBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance){
        ExamineBuildingMaintenanceExample example = new ExamineBuildingMaintenanceExample();
        MybatisUtils.convertObj2Example(examineBuildingMaintenance, example);
        examineBuildingMaintenanceMapper.deleteByExample(example);
    }

}