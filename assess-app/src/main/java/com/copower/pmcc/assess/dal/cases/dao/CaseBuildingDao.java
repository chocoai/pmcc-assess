package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class CaseBuildingDao {

    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private CaseBuildingMapper caseBuildingMapper;


    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuilding getBuildingById(Integer id) {
        return caseBuildingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuilding
     * @return
     */
    public List<CaseBuilding> getBuildingList(CaseBuilding caseBuilding) {
        CaseBuildingExample example = new CaseBuildingExample();
        MybatisUtils.convertObj2Example(caseBuilding, example);
        return caseBuildingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuilding
     * @return
     */
    public Integer addBuilding(CaseBuilding caseBuilding) {
        caseBuildingMapper.insertSelective(caseBuilding);
        return caseBuilding.getId();
    }

    public int updateEstateId(Integer oldEstateId, Integer newEstateId) {
        CaseBuildingExample example = new CaseBuildingExample();
        example.createCriteria().andEstateIdEqualTo(oldEstateId);

        CaseBuilding caseBuilding = new CaseBuilding();
        caseBuilding.setEstateId(newEstateId);
        return caseBuildingMapper.updateByExampleSelective(caseBuilding, example);
    }

    /**
     * 编辑
     * @param caseBuilding
     * @return
     */
    public boolean updateBuilding(CaseBuilding caseBuilding) {
        return caseBuildingMapper.updateByPrimaryKeySelective(caseBuilding) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuilding(Integer id){
        return caseBuildingMapper.deleteByPrimaryKey(id) > 0;
    }


    /**
     * 获取最新版本楼栋主信息
     *
     * @param estateId
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionBuildingList(String buildingNumber, Integer estateId) {
        return customCaseMapper.getCaseBuildingList(buildingNumber, estateId);
    }

    public int getBuildingCount(String buildingNumber, Integer estateId) {
        CaseBuildingExample example = new CaseBuildingExample();
        CaseBuildingExample.Criteria criteria = example.createCriteria();
        if (!org.springframework.util.StringUtils.isEmpty(buildingNumber)) {
            criteria.andBuildingNumberLike(String.format("%s%s%s", "%", buildingNumber, "%"));
        }
        if (estateId != null) {
            criteria.andEstateIdEqualTo(estateId);
        }
        return caseBuildingMapper.countByExample(example);
    }
}