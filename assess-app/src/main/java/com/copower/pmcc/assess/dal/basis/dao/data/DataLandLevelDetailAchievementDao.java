package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievementExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelDetailAchievementMapper;
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
public class DataLandLevelDetailAchievementDao {
    @Autowired
    private DataLandLevelDetailAchievementMapper mapper;

    public boolean saveDataLandLevelDetailAchievement(DataLandLevelDetailAchievement oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataLandLevelDetailAchievement(DataLandLevelDetailAchievement oo){
        return updateDataLandLevelDetailAchievement(oo,false) ;
    }

    public boolean updateDataLandLevelDetailAchievement(DataLandLevelDetailAchievement oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataLandLevelDetailAchievement(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataLandLevelDetailAchievement getDataLandLevelDetailAchievementById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataLandLevelDetailAchievement> getDataLandLevelDetailAchievementList(DataLandLevelDetailAchievement oo){
        DataLandLevelDetailAchievementExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataLandLevelDetailAchievementList(DataLandLevelDetailAchievement oo){
        DataLandLevelDetailAchievementExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataLandLevelDetailAchievementExample getExample(DataLandLevelDetailAchievement oo){
        DataLandLevelDetailAchievementExample example = new DataLandLevelDetailAchievementExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataLandLevelDetailAchievementExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

    public List<DataLandLevelDetailAchievement> getDataLandLevelDetailAchievement(Integer levelDetailId,String category,Integer grade,Integer type){
        DataLandLevelDetailAchievementExample example = new DataLandLevelDetailAchievementExample();
        DataLandLevelDetailAchievementExample.Criteria criteria = example.createCriteria();
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
