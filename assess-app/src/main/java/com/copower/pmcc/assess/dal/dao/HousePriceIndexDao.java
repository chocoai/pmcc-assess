package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dal.entity.HousePriceIndexExample;
import com.copower.pmcc.assess.dal.mapper.HousePriceIndexMapper;
import com.copower.pmcc.assess.dto.input.data.HousePriceIndexDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class HousePriceIndexDao {

    @Autowired
    private HousePriceIndexMapper housePriceIndexMapper;

    public boolean add(HousePriceIndex housePriceIndex) {
        if (housePriceIndex.getGmtCreated() == null)
            housePriceIndex.setGmtCreated(new Date(System.currentTimeMillis()));
        if ((housePriceIndex.getCreator() == null)) housePriceIndex.setCreator("root");
        int j = housePriceIndexMapper.insertSelective(housePriceIndex);
        return j == 1;
    }

    public boolean remove(Integer id) {
        return housePriceIndexMapper.deleteByPrimaryKey(id) == 1;
    }

    public HousePriceIndex getById(Integer id) {
        return housePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateHousePriceIndex(HousePriceIndex housePriceIndex) {
        if (housePriceIndex.getCreator() == null) housePriceIndex.setCreator("root");
        int j = housePriceIndexMapper.updateByPrimaryKey(housePriceIndex);
        return j == 1;
    }

    public List<HousePriceIndex> list(Map<String, Object> map) {
        List<HousePriceIndex> housePriceIndices = null;
        HousePriceIndexExample housePriceIndexExample = new HousePriceIndexExample();

        Date end = null;
        String INDEX_CALENDAR_KEY = null;
        Date start = null;
        if (map!=null){
            if (map.get(HousePriceIndexDto.END_TIME) != null) end = (Date) map.get(HousePriceIndexDto.END_TIME);
            if (map.get(HousePriceIndexDto.START_TIME) != null) start = (Date) map.get(HousePriceIndexDto.START_TIME);
            if (map.get(HousePriceIndexDto.INDEX_CALENDAR_KEY) != null) INDEX_CALENDAR_KEY = (String) map.get(HousePriceIndexDto.INDEX_CALENDAR_KEY);
        }

        if (INDEX_CALENDAR_KEY != null) {
            String v = "%" + map.get(INDEX_CALENDAR_KEY) + "%";
            housePriceIndexExample.createCriteria().andIdIsNotNull().andIndexCalendarLike(v);
        } else if (start != null) {
            housePriceIndexExample.createCriteria().andYearMonthCalendarGreaterThanOrEqualTo(start);
        } else if (end != null) {
            housePriceIndexExample.createCriteria().andYearMonthCalendarLessThan(end);
        } else if (end != null && start != null) {
            housePriceIndexExample.createCriteria().andYearMonthCalendarBetween(start, end);
        } else {
            housePriceIndexExample.createCriteria().andIdIsNotNull();//不为null
        }


        housePriceIndices = housePriceIndexMapper.selectByExample(housePriceIndexExample);
        return housePriceIndices;
    }
}
