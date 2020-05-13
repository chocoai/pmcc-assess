package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:
 */
@Repository
public class DataLandLevelDao {

    @Autowired
    private DataLandLevelMapper dataLandLevelMapper;

    public List<DataLandLevel> getByIds(List<Integer> integerList) {
        DataLandLevelExample example = new DataLandLevelExample();
        DataLandLevelExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(integerList);
        return dataLandLevelMapper.selectByExample(example);
    }

    public Integer addDataLandLevel(DataLandLevel dataLandLevel) {
        dataLandLevelMapper.insertSelective(dataLandLevel);
        return dataLandLevel.getId();
    }

    public DataLandLevel getDataLandLevelById(Integer id) {
        return dataLandLevelMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLandLevel(DataLandLevel dataLandLevel) {
        return updateDataLandLevel(dataLandLevel,false) ;
    }

    public boolean updateDataLandLevel(DataLandLevel oo, boolean updateNull) {
        return updateNull ? dataLandLevelMapper.updateByPrimaryKey(oo) == 1 : dataLandLevelMapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public void removeDataLandLevel(DataLandLevel dataLandLevel) {
        DataLandLevelExample example = new DataLandLevelExample();
        MybatisUtils.convertObj2Example(dataLandLevel, example);
        dataLandLevelMapper.deleteByExample(example);
    }

    public List<DataLandLevel> getByProcessInsIdList(String processInsId) {
        DataLandLevelExample example = new DataLandLevelExample();
        DataLandLevelExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInsIdEqualTo(processInsId);
        example.setOrderByClause("release_date desc");
        return dataLandLevelMapper.selectByExample(example);
    }

    public List<DataLandLevel> getDataLandLevelList(DataLandLevel dataLandLevel) {
        DataLandLevelExample example = new DataLandLevelExample();
        DataLandLevelExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(dataLandLevel.getProvince())) {
            criteria.andProvinceEqualTo(dataLandLevel.getProvince());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getCity())) {
            criteria.andCityEqualTo(dataLandLevel.getCity());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getDistrict())) {
            criteria.andDistrictEqualTo(dataLandLevel.getDistrict());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getTownShipName())) {
            criteria.andTownShipNameLike(String.format("%%%s%%", dataLandLevel.getTownShipName()));
        }

        if (dataLandLevel.getLandRightType() != null) {
            criteria.andLandRightTypeEqualTo(dataLandLevel.getLandRightType());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getWordSymbol())) {
            criteria.andWordSymbolEqualTo(dataLandLevel.getWordSymbol());
        }

        if (dataLandLevel.getValuationDate() != null) {
            criteria.andValuationDateEqualTo(dataLandLevel.getValuationDate());
        }

        if (dataLandLevel.getExecutionTime() != null) {
            criteria.andExecutionTimeEqualTo(dataLandLevel.getExecutionTime());
        }

        if (dataLandLevel.getReleaseDate() != null) {
            criteria.andReleaseDateEqualTo(dataLandLevel.getReleaseDate());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getTitle())) {
            criteria.andTitleEqualTo(dataLandLevel.getTitle());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getCreator())) {
            criteria.andCreatorEqualTo(dataLandLevel.getCreator());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getProcessInsId())) {
            criteria.andProcessInsIdEqualTo(dataLandLevel.getProcessInsId());
        }

        if (StringUtils.isNotBlank(dataLandLevel.getStatus())) {
            criteria.andStatusEqualTo(dataLandLevel.getStatus());
        }

        example.setOrderByClause("release_date desc");
        return dataLandLevelMapper.selectByExample(example);
    }
}
