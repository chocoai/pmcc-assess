package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataBestUseDescription;
import com.copower.pmcc.assess.dal.entity.DataBestUseDescriptionExample;
import com.copower.pmcc.assess.dal.mapper.DataBestUseDescriptionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataBestUseDescriptionDao {

    @Autowired
    private DataBestUseDescriptionMapper dataBestUseDescriptionMapper;


    public List<DataBestUseDescription> getDataBestUseDescriptionList(String name) {
        DataBestUseDescriptionExample example = new DataBestUseDescriptionExample();
        DataBestUseDescriptionExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        return dataBestUseDescriptionMapper.selectByExample(example);
    }

    public boolean addDataBestUseDescription(DataBestUseDescription dataBestUseDescription) {
        int i = dataBestUseDescriptionMapper.insert(dataBestUseDescription);
        return i > 0;
    }

    public boolean editDataBestUseDescription(DataBestUseDescription dataBestUseDescription) {
        int i = dataBestUseDescriptionMapper.updateByPrimaryKeySelective(dataBestUseDescription);
        return i > 0;
    }

    public boolean deleteDataBestUseDescription(Integer id) {
        int i = dataBestUseDescriptionMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
