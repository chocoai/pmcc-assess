package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReimbursementMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class SchemeReimbursementDao {
    @Autowired
    private SchemeReimbursementMapper schemeReimbursementMapper;

    public SchemeReimbursement getSchemeReimbursement(Integer id) {
        return schemeReimbursementMapper.selectByPrimaryKey(id);
    }


    public List<SchemeReimbursement> getObjectList(SchemeReimbursement schemeReimbursement) {
        SchemeReimbursementExample example = new SchemeReimbursementExample();
        MybatisUtils.convertObj2Example(schemeReimbursement, example);
        return schemeReimbursementMapper.selectByExample(example);
    }

    public SchemeReimbursement getSchemeReimbursementByAreaIdAndByPlanDetailsId(Integer planDetailsId,Integer areaId){
        SchemeReimbursementExample example = new SchemeReimbursementExample();
        SchemeReimbursementExample.Criteria criteria = example.createCriteria();
        criteria.andAreaIdEqualTo(areaId) ;
        criteria.andPlanDetailsIdEqualTo(planDetailsId) ;
        List<SchemeReimbursement> schemeReimbursements = schemeReimbursementMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReimbursements)){
            return schemeReimbursements.get(0);
        }
        return null;
    }

    public boolean addSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        int i = schemeReimbursementMapper.insertSelective(schemeReimbursement);
        return i > 0;
    }

    public boolean editSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        int i = schemeReimbursementMapper.updateByPrimaryKeySelective(schemeReimbursement);
        return i > 0;
    }

    public boolean deleteSchemeReimbursement(Integer id) {
        int i = schemeReimbursementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SchemeReimbursement getSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        SchemeReimbursementExample example = new SchemeReimbursementExample();
        MybatisUtils.convertObj2Example(schemeReimbursement, example);
        List<SchemeReimbursement> schemeReimbursements = schemeReimbursementMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReimbursements)) return schemeReimbursements.get(0);
        return null;
    }

}
