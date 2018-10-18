package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:
 */
@Repository
public class DataLandLevelDao {
    @Autowired
    private DataLandLevelMapper dataLandLevelMapper;

    public Integer addDataLandLevel(DataLandLevel dataLandLevel){
         dataLandLevelMapper.insertSelective(dataLandLevel);
         return dataLandLevel.getId();
    }

    public DataLandLevel getDataLandLevelById(Integer id){
        return dataLandLevelMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLandLevel(DataLandLevel dataLandLevel){
        return dataLandLevelMapper.updateByPrimaryKeySelective(dataLandLevel)==1;
    }

    public void removeDataLandLevel(DataLandLevel dataLandLevel){
        DataLandLevelExample example = new DataLandLevelExample();
        MybatisUtils.convertObj2Example(dataLandLevel, example);
        dataLandLevelMapper.deleteByExample(example);
    }

    public List<DataLandLevel> getDataLandLevelList(DataLandLevel dataLandLevel){
        DataLandLevelExample example = new DataLandLevelExample();
        MybatisUtils.convertObj2Example(dataLandLevel, example);
        return dataLandLevelMapper.selectByExample(example);
    }
}
