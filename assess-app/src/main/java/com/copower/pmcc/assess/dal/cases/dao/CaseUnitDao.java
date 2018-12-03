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

    public int updateBuildingMainId(Integer oldBuildingMainId, Integer newBuildingMainId) {
        CaseUnitExample example = new CaseUnitExample();
        example.createCriteria().andBuildingMainIdEqualTo(oldBuildingMainId);

        CaseUnit caseUnit = new CaseUnit();
        caseUnit.setBuildingMainId(newBuildingMainId);
        return caseUnitMapper.updateByExampleSelective(caseUnit, example);
    }

    /**
     * 获取最新半单元信息
     *
     * @param buildingMainId
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionUnitList(String unitNumber, Integer buildingMainId) {
        return customCaseMapper.getCaseUnitList(unitNumber, buildingMainId);
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

    public List<CaseUnit> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingMainId) {
        CaseUnitExample example = new CaseUnitExample();
        CaseUnitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotEmpty(unitNumber)) {
            criteria.andUnitNumberLike(String.format("%s%s%s", "%", unitNumber, "%"));
        }
        if (caseBuildingMainId != null) {
            criteria.andBuildingMainIdEqualTo(caseBuildingMainId);
        }
        return caseUnitMapper.selectByExample(example);
    }


    public int getUnitCount(String unitNumber, Integer caseBuildingMainId) {
        CaseUnitExample example = new CaseUnitExample();
        CaseUnitExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(unitNumber)) {
            criteria.andUnitNumberEqualTo(unitNumber);
        }
        if (caseBuildingMainId != null) {
            criteria.andBuildingMainIdEqualTo(caseBuildingMainId);
        }
        return caseUnitMapper.countByExample(example);
    }
}