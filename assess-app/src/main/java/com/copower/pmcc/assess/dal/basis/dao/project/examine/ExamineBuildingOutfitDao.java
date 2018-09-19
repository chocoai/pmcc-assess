package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfit;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfitExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuildingOutfitMapper;
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
public class ExamineBuildingOutfitDao {
    @Autowired
    private ExamineBuildingOutfitMapper examineBuildingOutfitMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBuildingOutfit getBuildingOutfitById(Integer id) {
        return examineBuildingOutfitMapper.selectByPrimaryKey(id);
    }

    public void initRemove(){
        ExamineBuildingOutfitExample example = new ExamineBuildingOutfitExample();
        ExamineBuildingOutfitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andBuildingIdEqualTo(0);
        examineBuildingOutfitMapper.deleteByExample(example);
    }

    public void removeExamineBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit){
        ExamineBuildingOutfitExample example = new ExamineBuildingOutfitExample();
        MybatisUtils.convertObj2Example(examineBuildingOutfit, example);
        examineBuildingOutfitMapper.deleteByExample(example);
    }

    /**
     * 获取数据列表
     * @param examineBuildingOutfit
     * @return
     */
    public List<ExamineBuildingOutfit> getBuildingOutfitList(ExamineBuildingOutfit examineBuildingOutfit) {
        ExamineBuildingOutfitExample example = new ExamineBuildingOutfitExample();
        MybatisUtils.convertObj2Example(examineBuildingOutfit, example);
        return examineBuildingOutfitMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBuildingOutfit
     * @return
     */
    public boolean addBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit) {
        return examineBuildingOutfitMapper.insertSelective(examineBuildingOutfit) > 0;
    }

    /**
     * 编辑
     * @param examineBuildingOutfit
     * @return
     */
    public boolean updateBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit) {
        return examineBuildingOutfitMapper.updateByPrimaryKeySelective(examineBuildingOutfit) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingOutfit(Integer id){
        return examineBuildingOutfitMapper.deleteByPrimaryKey(id) > 0;
    }

}