package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunction;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunctionExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingFunctionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 11:56
 * @Description:
 */
@Repository
public class CaseBuildingFunctionDao {
    @Autowired
    private CaseBuildingFunctionMapper caseBuildingFunctionMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuildingFunction getBuildingOutfitById(Integer id) {
        return caseBuildingFunctionMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuildingFunction
     * @return
     */
    public List<CaseBuildingFunction> getBuildingOutfitList(CaseBuildingFunction caseBuildingFunction) {
        CaseBuildingFunctionExample example = new CaseBuildingFunctionExample();
        MybatisUtils.convertObj2Example(caseBuildingFunction, example);
        return caseBuildingFunctionMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuildingFunction
     * @return
     */
    public Integer addBuildingOutfit(CaseBuildingFunction caseBuildingFunction) {
        caseBuildingFunctionMapper.insertSelective(caseBuildingFunction);
        return caseBuildingFunction.getId();
    }

    /**
     * 编辑
     * @param caseBuildingFunction
     * @return
     */
    public boolean updateBuildingOutfit(CaseBuildingFunction caseBuildingFunction) {
        return caseBuildingFunctionMapper.updateByPrimaryKeySelective(caseBuildingFunction) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingOutfit(Integer id){
        return caseBuildingFunctionMapper.deleteByPrimaryKey(id) > 0;
    }
}
