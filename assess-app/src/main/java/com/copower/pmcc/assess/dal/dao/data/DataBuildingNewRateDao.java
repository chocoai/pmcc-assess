package com.copower.pmcc.assess.dal.dao.data;

import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dal.entity.DataBuildingNewRateExample;
import com.copower.pmcc.assess.dal.mapper.DataBuildingNewRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DataBuildingNewRateDao {

    @Autowired
    private DataBuildingNewRateMapper dataBuildingNewRateMapper;

    public boolean addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) {
        int i = dataBuildingNewRateMapper.insert(dataBuildingNewRate);
        return i > 0;
    }

    public boolean editDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) {
        int i = dataBuildingNewRateMapper.updateByPrimaryKeySelective(dataBuildingNewRate);
        return i > 0;
    }

    public DataBuildingNewRate getByiDdataBuildingNewRate(Integer id) {
        DataBuildingNewRate dataBuildingNewRate = dataBuildingNewRateMapper.selectByPrimaryKey(id);
        return dataBuildingNewRate;
    }

    public boolean deleteDataBuildingNewRate(Integer id) {
        int i = dataBuildingNewRateMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<DataBuildingNewRate> getDataBuildingNewRateList(Map map) {
        List<DataBuildingNewRate> dataBuildingNewRateList = null;
        DataBuildingNewRateExample example = new DataBuildingNewRateExample();
        try {
            Object buildingStructure = map.get("buildingStructure");
            if (buildingStructure!=null&&buildingStructure.toString()!=""){
                String v = "%"+(String) map.get("buildingStructure")+"%";
                example.createCriteria().andDurableLifeIsNotNull().andBuildingStructureLike(v);
            }
            else {
                example.createCriteria().andDurableLifeIsNotNull();
            }
        }catch (Exception e){
            example.createCriteria().andDurableLifeIsNotNull();
        }
        dataBuildingNewRateList = dataBuildingNewRateMapper.selectByExample(example);
        return dataBuildingNewRateList;
    }

}
