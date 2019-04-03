package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dal.basis.entity.DataQualificationExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataQualificationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class DataQualificationDao {
    @Autowired
    private DataQualificationMapper dataQualificationMapper;

    public List<DataQualification> getDataQualificationList(String type){
        DataQualificationExample example = new DataQualificationExample();
        DataQualificationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(type)){
            criteria.andQualificationTypeLike(String.format("%%%s%%",type));
        }
        return dataQualificationMapper.selectByExample(example);
    }
    
    public List<DataQualification> getListObject(DataQualification dataQualification) {
        DataQualificationExample example = new DataQualificationExample();
        MybatisUtils.convertObj2Example(dataQualification, example);
        return dataQualificationMapper.selectByExample(example);
    }

    public List<DataQualification> getDataQualifications(List<Integer> ids){
        DataQualificationExample example = new DataQualificationExample();
        example.createCriteria().andIdIn(ids);
        return dataQualificationMapper.selectByExample(example);
    }

    public DataQualification getSingleObject(DataQualification dataQualification) {
        DataQualificationExample example = new DataQualificationExample();
        MybatisUtils.convertObj2Example(dataQualification, example);
        List<DataQualification> vacationTypeList = dataQualificationMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataQualification getSingleObject(Integer id) {
        return dataQualificationMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataQualification bidCostInfo) {
        return dataQualificationMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataQualification bidCostInfo) {
        return dataQualificationMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataQualificationMapper.deleteByPrimaryKey(id) == 1;
    }
}
