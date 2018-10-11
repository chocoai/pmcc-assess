package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnit;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineUnitMapper;
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
public class ExamineUnitDao {
    @Autowired
    private ExamineUnitMapper examineUnitMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineUnit getUnitById(Integer id) {
        return examineUnitMapper.selectByPrimaryKey(id);
    }


    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineUnit getUnitByDeclareId(Integer declareId,Integer planDetailsId,Integer examineType) {
        ExamineUnitExample example = new ExamineUnitExample();
        example.createCriteria().andDeclareIdEqualTo(declareId).andPlanDetailsIdEqualTo(planDetailsId).andExamineTypeEqualTo(examineType);
        List<ExamineUnit> blockList = examineUnitMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    public ExamineUnit getUnitByPlanDetailsId(Integer planDetailsId) {
        ExamineUnitExample example = new ExamineUnitExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        List<ExamineUnit> blockList = examineUnitMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineUnit
     * @return
     */
    public List<ExamineUnit> getUnitList(ExamineUnit examineUnit) {
        ExamineUnitExample example = new ExamineUnitExample();
        MybatisUtils.convertObj2Example(examineUnit, example);
        return examineUnitMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineUnit
     * @return
     */
    public boolean addUnit(ExamineUnit examineUnit) {
        return examineUnitMapper.insertSelective(examineUnit) > 0;
    }

    /**
     * 编辑
     * @param examineUnit
     * @return
     */
    public boolean updateUnit(ExamineUnit examineUnit) {
        return examineUnitMapper.updateByPrimaryKeySelective(examineUnit) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnit(Integer id){
        return examineUnitMapper.deleteByPrimaryKey(id) > 0;
    }

}