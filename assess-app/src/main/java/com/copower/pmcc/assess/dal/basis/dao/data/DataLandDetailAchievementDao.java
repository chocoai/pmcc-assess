package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievementExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandDetailAchievementMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/5 10:19
 * @description:
 */
@Repository
public class DataLandDetailAchievementDao {
    @Autowired
    private DataLandDetailAchievementMapper mapper;

    public boolean saveDataLandDetailAchievement(DataLandDetailAchievement oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataLandDetailAchievement(DataLandDetailAchievement oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataLandDetailAchievement(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataLandDetailAchievement getDataLandDetailAchievementById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataLandDetailAchievement> getDataLandDetailAchievementList(DataLandDetailAchievement oo){
        DataLandDetailAchievementExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataLandDetailAchievementList(DataLandDetailAchievement oo){
        DataLandDetailAchievementExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataLandDetailAchievementExample getExample(DataLandDetailAchievement oo){
        DataLandDetailAchievementExample example = new DataLandDetailAchievementExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataLandDetailAchievementExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

    public List<DataLandDetailAchievement> getDataLandDetailAchievement(Integer levelDetailId,String category,Integer grade,Integer type){
        DataLandDetailAchievementExample example = new DataLandDetailAchievementExample();
        DataLandDetailAchievementExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (levelDetailId != null) {
            criteria.andLevelDetailIdEqualTo(levelDetailId);
        }
        if (StringUtils.isNotBlank(category)) {
            criteria.andCategoryEqualTo(category);
        }
        if (grade != null) {
            criteria.andGradeEqualTo(grade);
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        return mapper.selectByExample(example);
    }
}
