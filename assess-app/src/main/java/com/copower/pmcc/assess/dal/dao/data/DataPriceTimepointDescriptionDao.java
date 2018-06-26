package com.copower.pmcc.assess.dal.dao.data;

import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescriptionExample;
import com.copower.pmcc.assess.dal.mapper.DataPriceTimepointDescriptionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataPriceTimepointDescriptionDao {

    @Autowired
    private DataPriceTimepointDescriptionMapper dataPriceTimepointDescriptionMapper;


    public List<DataPriceTimepointDescription> getDataPriceTimepointDescriptionList(String name) {
        DataPriceTimepointDescriptionExample example = new DataPriceTimepointDescriptionExample();
        DataPriceTimepointDescriptionExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        return dataPriceTimepointDescriptionMapper.selectByExample(example);
    }

    public boolean addDataPriceTimepointDescription(DataPriceTimepointDescription dataPriceTimepointDescription) {
        DataPriceTimepointDescriptionExample example = new DataPriceTimepointDescriptionExample();
        example.createCriteria().andNameEqualTo(dataPriceTimepointDescription.getName()).andDescriptionEqualTo(dataPriceTimepointDescription.getDescription());
        List<DataPriceTimepointDescription> dataPriceTimepointDescriptions = dataPriceTimepointDescriptionMapper.selectByExample(example);
        if(dataPriceTimepointDescriptions.size()>0){
            return  false;
        }else{
            int i = dataPriceTimepointDescriptionMapper.insert(dataPriceTimepointDescription);
            return i > 0;
        }

    }

    public boolean editDataPriceTimepointDescription(DataPriceTimepointDescription dataPriceTimepointDescription) {
        int i = dataPriceTimepointDescriptionMapper.updateByPrimaryKeySelective(dataPriceTimepointDescription);
        return i > 0;
    }

    public boolean deleteDataPriceTimepointDescription(Integer id) {
        int i = dataPriceTimepointDescriptionMapper.deleteByPrimaryKey(id);
        return i > 0;
    }
}
