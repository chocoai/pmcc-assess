package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReimbursementItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class SchemeReimbursementItemDao {
    @Autowired
    private SchemeReimbursementItemMapper schemeReimbursementItemMapper;

    public List<SchemeReimbursementItem> getListObject(SchemeReimbursementItem schemeReimbursementItem) {
        SchemeReimbursementItemExample example = new SchemeReimbursementItemExample();
        MybatisUtils.convertObj2Example(schemeReimbursementItem, example);
        return schemeReimbursementItemMapper.selectByExample(example);
    }
    public List<SchemeReimbursementItem> getListObject2(SchemeReimbursementItem schemeReimbursementItem) {
        SchemeReimbursementItemExample example = new SchemeReimbursementItemExample();
        SchemeReimbursementItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (schemeReimbursementItem.getId() != null){
            criteria.andIdEqualTo(schemeReimbursementItem.getId());
        }
        if (schemeReimbursementItem.getJudgeObjectId() != null){
            criteria.andJudgeObjectIdEqualTo(schemeReimbursementItem.getJudgeObjectId());
        }
        if (schemeReimbursementItem.getPlanDetailsId() != null){
            criteria.andPlanDetailsIdEqualTo(schemeReimbursementItem.getPlanDetailsId());
        }
        if (schemeReimbursementItem.getMasterId() != null){
            criteria.andMasterIdEqualTo(schemeReimbursementItem.getMasterId());
        }
        if (schemeReimbursementItem.getProjectId() != null){
            criteria.andProjectIdEqualTo(schemeReimbursementItem.getProjectId());
        }
        if (schemeReimbursementItem.getNotSetUpUnitPrice() != null){
            criteria.andNotSetUpUnitPriceEqualTo(schemeReimbursementItem.getNotSetUpUnitPrice());
        }
        if (schemeReimbursementItem.getKnowTotalPrice() != null){
            criteria.andKnowTotalPriceEqualTo(schemeReimbursementItem.getKnowTotalPrice());
        }
        return schemeReimbursementItemMapper.selectByExample(example);
    }

    public SchemeReimbursementItem getSingleObject(SchemeReimbursementItem schemeReimbursementItem) {
        SchemeReimbursementItemExample example = new SchemeReimbursementItemExample();
        MybatisUtils.convertObj2Example(schemeReimbursementItem, example);
        List<SchemeReimbursementItem> vacationTypeList = schemeReimbursementItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public SchemeReimbursementItem getSingleObject(Integer id) {
        return schemeReimbursementItemMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(SchemeReimbursementItem bidCostInfo) {
        return schemeReimbursementItemMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(SchemeReimbursementItem bidCostInfo) {
        return schemeReimbursementItemMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return schemeReimbursementItemMapper.deleteByPrimaryKey(id) == 1;
    }
}
