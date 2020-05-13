package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolumeExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelDetailVolumeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:25
 * @description:
 */
@Repository
public class DataLandLevelDetailVolumeDao {

    @Autowired
    private DataLandLevelDetailVolumeMapper mapper;

    public boolean saveDataLandLevelDetailVolume(DataLandLevelDetailVolume oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataLandLevelDetailVolume(DataLandLevelDetailVolume oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean updateDataLandLevelDetailVolume(DataLandLevelDetailVolume oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataLandLevelDetailVolume(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataLandLevelDetailVolume getDataLandLevelDetailVolumeById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataLandLevelDetailVolume> getDataLandLevelDetailVolumeList(DataLandLevelDetailVolume oo){
        DataLandLevelDetailVolumeExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataLandLevelDetailVolumeList(DataLandLevelDetailVolume oo){
        DataLandLevelDetailVolumeExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataLandLevelDetailVolumeExample getExample(DataLandLevelDetailVolume oo){
        DataLandLevelDetailVolumeExample example = new DataLandLevelDetailVolumeExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataLandLevelDetailVolumeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

}
