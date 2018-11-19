package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfitExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurfaceExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingSurfaceMapper;
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
public class CaseBuildingSurfaceDao {
    @Autowired
    private CaseBuildingSurfaceMapper caseBuildingSurfaceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuildingSurface getBuildingSurfaceById(Integer id) {
        return caseBuildingSurfaceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuildingSurface
     * @return
     */
    public List<CaseBuildingSurface> getBuildingSurfaceList(CaseBuildingSurface caseBuildingSurface) {
        CaseBuildingSurfaceExample example = new CaseBuildingSurfaceExample();
        MybatisUtils.convertObj2Example(caseBuildingSurface, example);
        return caseBuildingSurfaceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuildingSurface
     * @return
     */
    public boolean addBuildingSurface(CaseBuildingSurface caseBuildingSurface) {
        return caseBuildingSurfaceMapper.insertSelective(caseBuildingSurface) > 0;
    }

    /**
     * 编辑
     * @param caseBuildingSurface
     * @return
     */
    public boolean updateBuildingSurface(CaseBuildingSurface caseBuildingSurface) {
        return caseBuildingSurfaceMapper.updateByPrimaryKeySelective(caseBuildingSurface) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingSurface(Integer id){
        return caseBuildingSurfaceMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param buildingId
     * @return
     */
    public int countByBuildingId(Integer buildingId){
        CaseBuildingSurfaceExample example = new CaseBuildingSurfaceExample();
        example.createCriteria().andBuildingIdEqualTo(buildingId);
        return caseBuildingSurfaceMapper.countByExample(example);
    }
}