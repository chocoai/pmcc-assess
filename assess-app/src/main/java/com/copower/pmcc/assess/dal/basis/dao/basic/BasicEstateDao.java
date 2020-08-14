package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicEstateDao {

    @Autowired
    private BasicEstateMapper basicEstateMapper;

    public BasicEstate getBasicEstateById(Integer id) {
        return basicEstateMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstate(BasicEstate basicEstate) {
        basicEstateMapper.insertSelective(basicEstate);
        return basicEstate.getId();
    }

    public boolean updateBasicEstate(BasicEstate basicEstate, boolean updateNull) {
        return updateNull ? basicEstateMapper.updateByPrimaryKey(basicEstate) == 1 : basicEstateMapper.updateByPrimaryKeySelective(basicEstate) == 1;
    }

    public boolean deleteBasicEstate(Integer id) {
        BasicEstate basicEstate = getBasicEstateById(id);
        if (basicEstate == null) return false;
        basicEstate.setBisDelete(true);
        return basicEstateMapper.updateByPrimaryKeySelective(basicEstate) == 1;
    }

    public List<BasicEstate> getCaseEstateList(String name, String province, String city, String district) {
        BasicEstateExample example = new BasicEstateExample();
        BasicEstateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull().andBisDeleteEqualTo(false).andBisCaseEqualTo(true);
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        if (!StringUtils.isEmpty(province)) {
            criteria.andProvinceEqualTo(province);
        }
        if (!StringUtils.isEmpty(city)) {
            criteria.andCityEqualTo(city);
        }
        if (!StringUtils.isEmpty(district)) {
            criteria.andDistrictEqualTo(district);
        }
        example.setOrderByClause("id desc");
        return basicEstateMapper.selectByExample(example);
    }


    public List<BasicEstate> getBasicEstateList(BasicEstate basicEstate) {
        BasicEstateExample example = new BasicEstateExample();
        BasicEstateExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstate, criteria);
        return basicEstateMapper.selectByExample(example);
    }
}
