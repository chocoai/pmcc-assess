package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dal.entity.HousePriceIndexExample;
import com.copower.pmcc.assess.dal.mapper.HousePriceIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class HousePriceIndexDao {

    @Autowired
    private HousePriceIndexMapper housePriceIndexMapper;

    public boolean add(HousePriceIndex housePriceIndex){
        if (housePriceIndex.getGmtCreated()==null)housePriceIndex.setGmtCreated(new Date(System.currentTimeMillis()));
        if ((housePriceIndex.getCreator()==null))housePriceIndex.setCreator("root");
        int j = housePriceIndexMapper.insertSelective(housePriceIndex);
        return j ==1;
    }

    public boolean remove(Integer id){
        int j = housePriceIndexMapper.deleteByPrimaryKey(id);
        return j==1;
    }

    public HousePriceIndex getById(Integer id){
        return housePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateHousePriceIndex(HousePriceIndex housePriceIndex){
        if (housePriceIndex.getCreator()==null)housePriceIndex.setCreator("root");
        int j = housePriceIndexMapper.updateByPrimaryKey(housePriceIndex);
        return  j==1;
    }

    public List<HousePriceIndex> list(Map<String,Object> map){
        List<HousePriceIndex> housePriceIndices = null;
        HousePriceIndexExample housePriceIndexExample = new HousePriceIndexExample();
        try {
            if (map.get(HousePriceIndex.INDEX_CALENDAR_KEY)!=null){
                String v = "%"  + map.get(HousePriceIndex.INDEX_CALENDAR_KEY) +"%";
                housePriceIndexExample.createCriteria().andIdIsNotNull().andIndexCalendarLike(v);
            }else {
                housePriceIndexExample.createCriteria().andIdIsNotNull();//不为null
            }
            housePriceIndices = housePriceIndexMapper.selectByExample(housePriceIndexExample);
        }catch (Exception e){

        }
        return housePriceIndices;
    }
}
