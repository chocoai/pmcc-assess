package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPicture;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPictureExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLocaleSurveyPictureMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class DataLocaleSurveyPictureDao {
    @Autowired
    private DataLocaleSurveyPictureMapper dataHousePriceIndexMapper;

    public Integer addDataLocaleSurveyPicture(DataLocaleSurveyPicture dataHousePriceIndex) {
        dataHousePriceIndexMapper.insertSelective(dataHousePriceIndex);
        return dataHousePriceIndex.getId();
    }

    public DataLocaleSurveyPicture getDataLocaleSurveyPictureById(Integer id) {
        return dataHousePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLocaleSurveyPicture(DataLocaleSurveyPicture dataHousePriceIndex) {
        return dataHousePriceIndexMapper.updateByPrimaryKeySelective(dataHousePriceIndex) == 1;
    }


    public void removeDataLocaleSurveyPicture(DataLocaleSurveyPicture dataHousePriceIndex) {
        DataLocaleSurveyPictureExample example = new DataLocaleSurveyPictureExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        dataHousePriceIndexMapper.deleteByExample(example);
    }

    public List<DataLocaleSurveyPicture> getDataLocaleSurveyPictureList(DataLocaleSurveyPicture dataHousePriceIndex) {
        DataLocaleSurveyPictureExample example = new DataLocaleSurveyPictureExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        example.setOrderByClause("type,sorting");
        return dataHousePriceIndexMapper.selectByExample(example);
    }

}
