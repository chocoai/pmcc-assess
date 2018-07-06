package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyWater;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyWaterExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateSupplyWaterMapper;
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
public class CaseEstateSupplyWaterDao {
    @Autowired
    private CaseEstateSupplyWaterMapper caseEstateSupplyWaterMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateSupplyWater getEstateSupplyWaterById(Integer id) {
        return caseEstateSupplyWaterMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateSupplyWater
     * @return
     */
    public List<CaseEstateSupplyWater> getEstateSupplyWaterList(CaseEstateSupplyWater caseEstateSupplyWater) {
        CaseEstateSupplyWaterExample example = new CaseEstateSupplyWaterExample();
        MybatisUtils.convertObj2Example(caseEstateSupplyWater, example);
        return caseEstateSupplyWaterMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateSupplyWater
     * @return
     */
    public boolean addEstateSupplyWater(CaseEstateSupplyWater caseEstateSupplyWater) {
        return caseEstateSupplyWaterMapper.insertSelective(caseEstateSupplyWater) > 0;
    }

    /**
     * 编辑
     * @param caseEstateSupplyWater
     * @return
     */
    public boolean updateEstateSupplyWater(CaseEstateSupplyWater caseEstateSupplyWater) {
        return caseEstateSupplyWaterMapper.updateByPrimaryKeySelective(caseEstateSupplyWater) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSupplyWater(Integer id){
        return caseEstateSupplyWaterMapper.deleteByPrimaryKey(id) > 0;
    }

}