package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetailExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseDamagedDegreeDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:50
 * @Description:
 */
@Repository
public class CaseHouseDamagedDegreeDetailDao {
    @Autowired
    private CaseHouseDamagedDegreeDetailMapper caseHouseDamagedDegreeDetailMapper;

    public CaseHouseDamagedDegreeDetail getCaseHouseDamagedDegreeDetailById(Integer id)  {
        return caseHouseDamagedDegreeDetailMapper.selectByPrimaryKey(id);
    }

    public Integer saveCaseHouseDamagedDegreeDetail(CaseHouseDamagedDegreeDetail CaseHouseDamagedDegreeDetail)  {
        caseHouseDamagedDegreeDetailMapper.insertSelective(CaseHouseDamagedDegreeDetail);
        return CaseHouseDamagedDegreeDetail.getId();
    }

    public boolean updateCaseHouseDamagedDegreeDetail(CaseHouseDamagedDegreeDetail CaseHouseDamagedDegreeDetail)  {
        return caseHouseDamagedDegreeDetailMapper.updateByPrimaryKeySelective(CaseHouseDamagedDegreeDetail) == 1;
    }

    public boolean deleteCaseHouseDamagedDegreeDetail(Integer id)  {
        return caseHouseDamagedDegreeDetailMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<CaseHouseDamagedDegreeDetail> getDamagedDegreeDetailList(CaseHouseDamagedDegreeDetail CaseHouseDamagedDegreeDetail)  {
        CaseHouseDamagedDegreeDetailExample example = new CaseHouseDamagedDegreeDetailExample();
        MybatisUtils.convertObj2Example(CaseHouseDamagedDegreeDetail, example);
        return caseHouseDamagedDegreeDetailMapper.selectByExample(example);
    }
}
