package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonusExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAreaAssessmentBonusMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class DataAreaAssessmentBonusDao {
    @Autowired
    private DataAreaAssessmentBonusMapper dataAreaAssessmentBonusMapper;

    public Integer addDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        dataAreaAssessmentBonusMapper.insertSelective(dataAreaAssessmentBonus);
        return dataAreaAssessmentBonus.getId();
    }

    public DataAreaAssessmentBonus getDataAreaAssessmentBonusById(Integer id) {
        return dataAreaAssessmentBonusMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        return dataAreaAssessmentBonusMapper.updateByPrimaryKeySelective(dataAreaAssessmentBonus) == 1;
    }

    public void removeDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        DataAreaAssessmentBonusExample example = new DataAreaAssessmentBonusExample();
        MybatisUtils.convertObj2Example(dataAreaAssessmentBonus, example);
        dataAreaAssessmentBonusMapper.deleteByExample(example);
    }

    public List<DataAreaAssessmentBonus> getDataAreaAssessmentBonusList(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        DataAreaAssessmentBonusExample example = new DataAreaAssessmentBonusExample();
        MybatisUtils.convertObj2Example(dataAreaAssessmentBonus, example);
        return dataAreaAssessmentBonusMapper.selectByExample(example);
    }

    public List<DataAreaAssessmentBonus> getDataAreaAssessmentBonusList(String province, String city, String district) {
        DataAreaAssessmentBonusExample example = new DataAreaAssessmentBonusExample();
        DataAreaAssessmentBonusExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(province)){
            criteria.andProvinceEqualTo(province);
        }
        if(!StringUtils.isEmpty(city)){
            criteria.andCityEqualTo(city);
        }
        if(!StringUtils.isEmpty(district)){
            criteria.andDistrictEqualTo(district);
        }
        example.setOrderByClause("province,city,district");
        return dataAreaAssessmentBonusMapper.selectByExample(example);
    }
}
