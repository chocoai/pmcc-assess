package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegree;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseDamagedDegreeMapper;
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
public class CaseHouseDamagedDegreeDao {
    @Autowired
    private CaseHouseDamagedDegreeMapper caseHouseDamagedDegreeMapper;

    public CaseHouseDamagedDegree getCaseHouseDamagedDegreeById(Integer id)  {
        return caseHouseDamagedDegreeMapper.selectByPrimaryKey(id);
    }

    public Integer addCaseHouseDamagedDegree(CaseHouseDamagedDegree CaseHouseDamagedDegree)  {
        caseHouseDamagedDegreeMapper.insertSelective(CaseHouseDamagedDegree);
        return CaseHouseDamagedDegree.getId();
    }

    public boolean updateCaseHouseDamagedDegree(CaseHouseDamagedDegree CaseHouseDamagedDegree)  {
        return caseHouseDamagedDegreeMapper.updateByPrimaryKeySelective(CaseHouseDamagedDegree) == 1;
    }

    public boolean deleteCaseHouseDamagedDegree(Integer id)  {
        return caseHouseDamagedDegreeMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<CaseHouseDamagedDegree> getDamagedDegreeList(CaseHouseDamagedDegree CaseHouseDamagedDegree)  {
        CaseHouseDamagedDegreeExample example = new CaseHouseDamagedDegreeExample();
        MybatisUtils.convertObj2Example(CaseHouseDamagedDegree, example);
        return caseHouseDamagedDegreeMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        CaseHouseDamagedDegreeExample example = new CaseHouseDamagedDegreeExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return caseHouseDamagedDegreeMapper.countByExample(example);
    }
}
