package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPosition;
import com.copower.pmcc.assess.dal.basis.entity.DataPositionExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataPositionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/4 10:31
 * @Description:
 */
@Repository
public class DataPositionDao {
    @Autowired
    private DataPositionMapper dataPositionMapper;

    public Integer addDataPosition(DataPosition dataPosition) {
        dataPositionMapper.insertSelective(dataPosition);
        return dataPosition.getId();
    }

    public DataPosition getDataPositionById(Integer id) {
        return dataPositionMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataPosition(DataPosition dataPosition) {
        return dataPositionMapper.updateByPrimaryKeySelective(dataPosition) == 1;
    }

    public void removeDataPosition(DataPosition dataPosition) {
        DataPositionExample example = new DataPositionExample();
        MybatisUtils.convertObj2Example(dataPosition, example);
        dataPositionMapper.deleteByExample(example);
    }

    public List<DataPosition> getDataPositionList(DataPosition dataPosition) {
        DataPositionExample example = new DataPositionExample();
        MybatisUtils.convertObj2Example(dataPosition, example);
        return dataPositionMapper.selectByExample(example);
    }

    public List<DataPosition> getDataPositionList(String province, String city, String district, String name) {
        DataPositionExample example = new DataPositionExample();
        DataPositionExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(province)) {
            criteria.andProvinceEqualTo(province);
        }
        if (StringUtils.isNotBlank(city)) {
            criteria.andCityEqualTo(city);
        }
        if (StringUtils.isNotBlank(district)) {
            criteria.andDistrictEqualTo(district);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        example.setOrderByClause("name");
        return dataPositionMapper.selectByExample(example);
    }
}
