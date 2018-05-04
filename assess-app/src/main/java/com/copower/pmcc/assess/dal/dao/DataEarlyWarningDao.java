package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.EarlyWarning;
import com.copower.pmcc.assess.dal.entity.EarlyWarningExample;
import com.copower.pmcc.assess.dal.mapper.DataEarlyWarningMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataEarlyWarningDao {

    @Autowired
    private DataEarlyWarningMapper dataEarlyWarningMapper;

    public boolean addEarlyWarning(EarlyWarning earlyWarning) {
    int flag = dataEarlyWarningMapper.insert(earlyWarning);
        return flag > 0;
    }

    public List<EarlyWarning> getEarlyWarningList(String keyWord) {
        EarlyWarningExample earlyWarningExample = new EarlyWarningExample();
        EarlyWarningExample.Criteria criteria = earlyWarningExample.createCriteria();
        if (StringUtils.isNotEmpty(keyWord)){
           criteria.andEntrustPurposeLike(String.format("%s%s%s","%",keyWord,"%"));
        }
        return dataEarlyWarningMapper.selectByExample(earlyWarningExample);
    }

    public boolean deleteEarlyWarning(Integer id) {
        int flag = dataEarlyWarningMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }

    public boolean editEarlyWarning(EarlyWarning earlyWarning) {
        int flag = dataEarlyWarningMapper.updateByPrimaryKeySelective(earlyWarning);
        return flag > 0;
    }
}
