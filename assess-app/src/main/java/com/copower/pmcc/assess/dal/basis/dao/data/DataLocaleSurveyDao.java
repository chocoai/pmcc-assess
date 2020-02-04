package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurvey;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLocaleSurveyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class DataLocaleSurveyDao {
    @Autowired
    private DataLocaleSurveyMapper dataHousePriceIndexMapper;

    public Integer addDataLocaleSurvey(DataLocaleSurvey dataHousePriceIndex) {
        dataHousePriceIndexMapper.insertSelective(dataHousePriceIndex);
        return dataHousePriceIndex.getId();
    }

    public DataLocaleSurvey getDataLocaleSurveyById(Integer id) {
        return dataHousePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLocaleSurvey(DataLocaleSurvey dataHousePriceIndex) {
        return dataHousePriceIndexMapper.updateByPrimaryKeySelective(dataHousePriceIndex) == 1;
    }


    public void removeDataLocaleSurvey(DataLocaleSurvey dataHousePriceIndex) {
        DataLocaleSurveyExample example = new DataLocaleSurveyExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        dataHousePriceIndexMapper.deleteByExample(example);
    }

    public List<DataLocaleSurvey> getDataLocaleSurveyList(Integer type,String name,String account) {
        DataLocaleSurveyExample example = new DataLocaleSurveyExample();
        DataLocaleSurveyExample.Criteria criteria = example.createCriteria();
        if (type!=null) {
            criteria.andTypeEqualTo(type);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (StringUtils.isNotBlank(account)) {
            criteria.andCreatorLike("%" + account + "%");
        }
        example.setOrderByClause("type,sorting");
        List<DataLocaleSurvey> netLandTransactions = dataHousePriceIndexMapper.selectByExample(example);
        return netLandTransactions;
    }


}
