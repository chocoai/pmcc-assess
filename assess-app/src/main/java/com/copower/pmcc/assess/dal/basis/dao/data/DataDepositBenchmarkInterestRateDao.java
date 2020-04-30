package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataDepositBenchmarkInterestRate;
import com.copower.pmcc.assess.dal.basis.entity.DataDepositBenchmarkInterestRateExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataDepositBenchmarkInterestRateMapper;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class DataDepositBenchmarkInterestRateDao {

    @Autowired
    private DataDepositBenchmarkInterestRateMapper mapper;

    public boolean updateDataDepositBenchmarkInterestRate(DataDepositBenchmarkInterestRate oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataDepositBenchmarkInterestRateById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteDataDepositBenchmarkInterestRateByIds(List<Integer> ids) {
        DataDepositBenchmarkInterestRateExample example = new DataDepositBenchmarkInterestRateExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public DataDepositBenchmarkInterestRate getDataDepositBenchmarkInterestRateById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveDataDepositBenchmarkInterestRate(DataDepositBenchmarkInterestRate oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<DataDepositBenchmarkInterestRate> getDataDepositBenchmarkInterestRateByIds(List<Integer> ids) {
        DataDepositBenchmarkInterestRateExample example = new DataDepositBenchmarkInterestRateExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<DataDepositBenchmarkInterestRate> getDataDepositBenchmarkInterestRateListByExample(DataDepositBenchmarkInterestRate oo) {
        DataDepositBenchmarkInterestRateExample example = new DataDepositBenchmarkInterestRateExample();
        DataDepositBenchmarkInterestRateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DataDepositBenchmarkInterestRate> getDataDepositBenchmarkInterestRateListLikeQuery(Date startTime, Date endTime) {
        DataDepositBenchmarkInterestRateExample example = new DataDepositBenchmarkInterestRateExample();
        DataDepositBenchmarkInterestRateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (startTime != null && endTime != null) {
            if (DateUtils.isSameDay(startTime, endTime)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTime);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                Date zero = calendar.getTime();
                endTime = zero;
            }
            criteria.andAdjustTimeBetween(startTime, endTime);
        }
        if (startTime != null && endTime == null) {
            criteria.andAdjustTimeGreaterThanOrEqualTo(startTime);
        }
        if (endTime != null && startTime == null) {
            criteria.andAdjustTimeLessThanOrEqualTo(endTime);
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }
}
