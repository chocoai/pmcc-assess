package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructure;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 */
@Repository
public class DataInfrastructureDao {
    @Autowired
    private DataInfrastructureMapper infrastructureMapper;

    public List<DataInfrastructure> getDataInfrastructureListA(DataInfrastructure infrastructure){
        if (infrastructure == null){
            return new ArrayList<DataInfrastructure>() ;
        }
        DataInfrastructureExample example = new DataInfrastructureExample();
        DataInfrastructureExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(infrastructure.getCity())){
            criteria.andCityEqualTo(infrastructure.getCity());
        }
        if (StringUtils.isNotBlank(infrastructure.getDistrict())){
            criteria.andDistrictEqualTo(infrastructure.getDistrict());
        }
        if (StringUtils.isNotBlank(infrastructure.getProvince())){
            criteria.andProvinceEqualTo(infrastructure.getProvince());
        }
        if (infrastructure.getEndDate()!= null && infrastructure.getStartDate() != null){
            criteria.andEndDateLessThanOrEqualTo(infrastructure.getEndDate());
            criteria.andStartDateGreaterThanOrEqualTo(infrastructure.getStartDate());
        }
        if (infrastructure.getEndDate()!= null && infrastructure.getStartDate() == null){
            criteria.andEndDateLessThanOrEqualTo(infrastructure.getEndDate());
        }
        if (infrastructure.getEndDate()== null && infrastructure.getStartDate() != null){
            criteria.andStartDateGreaterThanOrEqualTo(infrastructure.getStartDate());
        }
        if (StringUtils.isNotBlank(infrastructure.getProjectType())){
            criteria.andProjectTypeEqualTo(infrastructure.getProjectType());
        }
        if (StringUtils.isNotBlank(infrastructure.getType())){
            criteria.andTypeEqualTo(infrastructure.getType());
        }
        return infrastructureMapper.selectByExample(example) ;
    }

    public List<DataInfrastructure> getDataInfrastructureList(DataInfrastructure infrastructure){
        DataInfrastructureExample example = new DataInfrastructureExample();
        MybatisUtils.convertObj2Example(infrastructure, example);
        return infrastructureMapper.selectByExample(example) ;
    }


    public DataInfrastructure getDataInfrastructure(Integer id){
        return infrastructureMapper.selectByPrimaryKey(id);
    }

    public boolean update(DataInfrastructure infrastructure){
        return infrastructureMapper.updateByPrimaryKeySelective(infrastructure)==1;
    }

    public boolean addDataInfrastructure(DataInfrastructure infrastructure){
        return infrastructureMapper.insertSelective(infrastructure)==1;
    }

    public boolean editDataInfrastructure(DataInfrastructure infrastructure){
        int result = infrastructureMapper.updateByPrimaryKeySelective(infrastructure);
        return result > 0;
    }

    public boolean deleteDataInfrastructure(Integer id){
        return infrastructureMapper.deleteByPrimaryKey(id)==1;
    }
}
