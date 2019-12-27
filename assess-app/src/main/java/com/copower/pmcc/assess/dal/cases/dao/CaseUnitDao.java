package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseUnitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
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
public class CaseUnitDao {
    @Autowired
    private CaseUnitMapper caseUnitMapper;
    @Autowired
    private CustomCaseMapper customCaseMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseUnit getUnitById(Integer id) {
        return caseUnitMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseUnit
     * @return
     */
    public List<CaseUnit> getUnitList(CaseUnit caseUnit) {
        CaseUnitExample example = new CaseUnitExample();
        MybatisUtils.convertObj2Example(caseUnit, example);
        example.setOrderByClause("unit_number,version");
        return caseUnitMapper.selectByExample(example);
    }

    public List<CaseUnit> getUnitList(Integer caseBuildingId,String unitName) {
        CaseUnitExample example = new CaseUnitExample();
        CaseUnitExample.Criteria criteria = example.createCriteria();
        if (caseBuildingId!=null) {
            criteria.andBuildingIdEqualTo(caseBuildingId);
        }
        if (!StringUtils.isEmpty(unitName)) {
            criteria.andUnitNumberLike(String.format("%s%s%s", "%", unitName, "%"));
        }
        return caseUnitMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param caseUnit
     * @return
     */
    public Integer addUnit(CaseUnit caseUnit) {
        caseUnitMapper.insertSelective(caseUnit);
        return caseUnit.getId();
    }

    /**
     * 编辑
     *
     * @param caseUnit
     * @return
     */
    public boolean updateUnit(CaseUnit caseUnit) {
        return caseUnitMapper.updateByPrimaryKeySelective(caseUnit) > 0;
    }

    public int updateBuildingMainId(Integer oldBuildingId, Integer newBuildingId) {
        CaseUnitExample example = new CaseUnitExample();
        example.createCriteria().andBuildingIdEqualTo(oldBuildingId);

        CaseUnit caseUnit = new CaseUnit();
        caseUnit.setBuildingId(newBuildingId);
        return caseUnitMapper.updateByExampleSelective(caseUnit, example);
    }

    /**
     * 获取最新半单元信息
     *
     * @param buildingId
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionUnitList(String unitNumber, Integer buildingId) {
        return customCaseMapper.getCaseUnitList(unitNumber, buildingId);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteUnit(Integer id) {
        return caseUnitMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<CaseUnit> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingId) {
        CaseUnitExample example = new CaseUnitExample();
        CaseUnitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotEmpty(unitNumber)) {
            criteria.andUnitNumberLike(String.format("%s%s%s", "%", unitNumber, "%"));
        }
        if (caseBuildingId != null) {
            criteria.andBuildingIdEqualTo(caseBuildingId);
        }
        return caseUnitMapper.selectByExample(example);
    }


    public int getUnitCount(String unitNumber, Integer caseBuildingId) {
        CaseUnitExample example = new CaseUnitExample();
        CaseUnitExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(unitNumber)) {
            criteria.andUnitNumberEqualTo(unitNumber);
        }
        if (caseBuildingId != null) {
            criteria.andBuildingIdEqualTo(caseBuildingId);
        }
        return caseUnitMapper.countByExample(example);
    }
}