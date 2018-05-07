package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataEarlyWarning;
import com.copower.pmcc.assess.dal.entity.DataEarlyWarningExample;
import com.copower.pmcc.assess.dal.entity.EarlyWarning;
import com.copower.pmcc.assess.dal.mapper.DataEarlyWarningMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataEarlyWarningDao {

    @Autowired
    private DataEarlyWarningMapper dataEarlyWarningMapper;

    public boolean addEarlyWarning(DataEarlyWarning earlyWarning) {
    int flag = dataEarlyWarningMapper.insert(earlyWarning);
        return flag > 0;
    }

    public List<DataEarlyWarning> getEarlyWarningList(String keyWord) {
        DataEarlyWarningExample earlyWarningExample = new DataEarlyWarningExample();
        DataEarlyWarningExample.Criteria criteria = earlyWarningExample.createCriteria();
        if (StringUtils.isNotEmpty(keyWord)){
            criteria.andEntrustPurposeEqualTo(Integer.parseInt(keyWord));
        }
        return dataEarlyWarningMapper.selectByExample(earlyWarningExample);
    }

    public boolean deleteEarlyWarning(Integer id) {
        int flag = dataEarlyWarningMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }

    public boolean editEarlyWarning(DataEarlyWarning earlyWarning) {
        int flag = dataEarlyWarningMapper.updateByPrimaryKeySelective(earlyWarning);
        return flag > 0;
    }
}
