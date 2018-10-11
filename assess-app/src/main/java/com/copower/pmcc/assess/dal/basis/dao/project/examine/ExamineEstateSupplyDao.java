package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupply;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateSupplyMapper;
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
public class ExamineEstateSupplyDao {

    @Autowired
    private ExamineEstateSupplyMapper examineEstateSupplyMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineEstateSupply getExamineEstateSupplyById(Integer id) {
        return examineEstateSupplyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineEstateSupply
     * @return
     */
    public List<ExamineEstateSupply> getExamineEstateSupplyList(ExamineEstateSupply examineEstateSupply) {
        ExamineEstateSupplyExample example = new ExamineEstateSupplyExample();
        MybatisUtils.convertObj2Example(examineEstateSupply, example);
        return examineEstateSupplyMapper.selectByExample(example);
    }

    public List<ExamineEstateSupply> getExamineEstateSupplyList(Integer planDetailsId) {
        ExamineEstateSupplyExample example = new ExamineEstateSupplyExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return examineEstateSupplyMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineEstateSupply
     * @return
     */
    public boolean addExamineEstateSupply(ExamineEstateSupply examineEstateSupply) {
        return examineEstateSupplyMapper.insertSelective(examineEstateSupply) > 0;
    }

    /**
     * 编辑
     *
     * @param examineEstateSupply
     * @return
     */
    public boolean updateExamineEstateSupply(ExamineEstateSupply examineEstateSupply) {
        return examineEstateSupplyMapper.updateByPrimaryKeySelective(examineEstateSupply) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineEstateSupply(Integer id) {
        return examineEstateSupplyMapper.deleteByPrimaryKey(id) > 0;
    }
}