package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilding;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuildingMapper;
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
public class ExamineBuildingDao {
    @Autowired
    private ExamineBuildingMapper examineBuildingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBuilding getBuildingById(Integer id) {
        return examineBuildingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineBuilding
     * @return
     */
    public List<ExamineBuilding> getBuildingList(ExamineBuilding examineBuilding) {
        ExamineBuildingExample example = new ExamineBuildingExample();
        MybatisUtils.convertObj2Example(examineBuilding, example);
        return examineBuildingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBuilding
     * @return
     */
    public boolean addBuilding(ExamineBuilding examineBuilding) {
        return examineBuildingMapper.insertSelective(examineBuilding) > 0;
    }

    /**
     * 编辑
     * @param examineBuilding
     * @return
     */
    public boolean updateBuilding(ExamineBuilding examineBuilding) {
        return examineBuildingMapper.updateByPrimaryKeySelective(examineBuilding) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuilding(Integer id){
        return examineBuildingMapper.deleteByPrimaryKey(id) > 0;
    }

}