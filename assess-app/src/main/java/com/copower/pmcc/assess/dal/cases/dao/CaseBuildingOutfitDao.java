package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfit;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfitExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingOutfitMapper;
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
public class CaseBuildingOutfitDao {
    @Autowired
    private CaseBuildingOutfitMapper caseBuildingOutfitMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuildingOutfit getBuildingOutfitById(Integer id) {
        return caseBuildingOutfitMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuildingOutfit
     * @return
     */
    public List<CaseBuildingOutfit> getBuildingOutfitList(CaseBuildingOutfit caseBuildingOutfit) {
        CaseBuildingOutfitExample example = new CaseBuildingOutfitExample();
        MybatisUtils.convertObj2Example(caseBuildingOutfit, example);
        return caseBuildingOutfitMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuildingOutfit
     * @return
     */
    public boolean addBuildingOutfit(CaseBuildingOutfit caseBuildingOutfit) {
        return caseBuildingOutfitMapper.insertSelective(caseBuildingOutfit) > 0;
    }

    /**
     * 编辑
     * @param caseBuildingOutfit
     * @return
     */
    public boolean updateBuildingOutfit(CaseBuildingOutfit caseBuildingOutfit) {
        return caseBuildingOutfitMapper.updateByPrimaryKeySelective(caseBuildingOutfit) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingOutfit(Integer id){
        return caseBuildingOutfitMapper.deleteByPrimaryKey(id) > 0;
    }

}