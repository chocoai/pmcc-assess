package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:
 */
@Repository
public class DataLandLevelDetailDao {
    @Autowired
    private DataLandLevelDetailMapper dataLandLevelDetailMapper;

    public Integer addDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        dataLandLevelDetailMapper.insertSelective(dataLandLevelDetail);
        return dataLandLevelDetail.getId();
    }

    public DataLandLevelDetail getDataLandLevelDetailById(Integer id) {
        return dataLandLevelDetailMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        return dataLandLevelDetailMapper.updateByPrimaryKeySelective(dataLandLevelDetail) == 1;
    }

    public void removeDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        DataLandLevelDetailExample example = getExample(dataLandLevelDetail);
        dataLandLevelDetailMapper.deleteByExample(example);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        oo.setBisDelete(false);
        DataLandLevelDetailExample example = getExample(oo);
        example.setOrderByClause("classify,type,category");
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    public int getCountByLandLevelId(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        DataLandLevelDetailExample example = getExample(oo) ;
        example.createCriteria().andLandLevelIdEqualTo(landLevelId).andBisDeleteEqualTo(false);
        return dataLandLevelDetailMapper.countByExample(example);
    }

    public List<DataLandLevelDetail> getDataByClassifyAndType(String classify, String type, Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setClassify(classify);
        oo.setType(type);
        oo.setLandLevelId(landLevelId);
        return getDataLandLevelDetailList(oo);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(DataLandLevelDetail oo){
        DataLandLevelDetailExample example = getExample(oo);
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    public List<DataLandLevelDetail> getByMasterIdInfo(List<Integer> integerList){
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        example.createCriteria().andLandLevelIdIn(integerList) ;
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    private DataLandLevelDetailExample getExample(DataLandLevelDetail oo){
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataLandLevelDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(oo.getClassify())) {
            criteria.andClassifyEqualTo(oo.getClassify());
        }
        if (StringUtils.isNotBlank(oo.getType())) {
            criteria.andTypeEqualTo(oo.getType());
        }
        if (StringUtils.isNotEmpty(oo.getCategory())){
            criteria.andCategoryEqualTo(oo.getCategory());
        }
        if (oo.getLandLevelId() != null) {
            criteria.andLandLevelIdEqualTo(oo.getLandLevelId());
        }
        if (oo.getBisDelete() != null){
            criteria.andBisDeleteEqualTo(oo.getBisDelete());
        }
        if (StringUtils.isNotEmpty(oo.getCreator())){
            criteria.andCreatorEqualTo(oo.getCreator());
        }
        return example;
    }
}
