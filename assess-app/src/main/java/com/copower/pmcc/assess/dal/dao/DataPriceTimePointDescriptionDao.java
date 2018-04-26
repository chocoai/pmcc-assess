package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescriptionExample;
import com.copower.pmcc.assess.dal.mapper.DataPriceTimepointDescriptionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataPriceTimePointDescriptionDao {

    @Autowired
    private DataPriceTimepointDescriptionMapper dataPriceTimePointDescriptionMapper;


    public List<DataPriceTimepointDescription> getDataPriceTimePointDescriptionList(String name) {
        DataPriceTimepointDescriptionExample example = new DataPriceTimepointDescriptionExample();
        DataPriceTimepointDescriptionExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotEmpty(name)){
            criteria.andNameLike(String.format("%s%s%s","%s",name,"%s"));
        }
        return dataPriceTimePointDescriptionMapper.selectByExample(example);
    }

    public boolean addDataPriceTimePointDescription(DataPriceTimepointDescription dataPriceTimePointDescription){
        int i = dataPriceTimePointDescriptionMapper.insert(dataPriceTimePointDescription);
        return i > 0;
    }
}
