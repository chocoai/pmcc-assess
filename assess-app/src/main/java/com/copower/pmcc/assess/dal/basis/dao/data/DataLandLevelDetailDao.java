package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
        return updateDataLandLevelDetail(dataLandLevelDetail,false) ;
    }

    public boolean updateDataLandLevelDetail(DataLandLevelDetail oo, boolean updateNull) {
        return updateNull ? dataLandLevelDetailMapper.updateByPrimaryKey(oo) == 1 : dataLandLevelDetailMapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public void removeDataLandLevelDetailById(Integer id) {
        dataLandLevelDetailMapper.deleteByPrimaryKey(id);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        oo.setBisDelete(false);
        DataLandLevelDetailExample example = getExample(oo);
        example.setOrderByClause("classify,type,category");
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    public int getCountByLandLevelId(Integer landLevelId, String classify, Integer pid) {
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        DataLandLevelDetailExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false);
        if (landLevelId != null) {
            criteria.andLandLevelIdEqualTo(landLevelId);
        }
        if (classify != null) {
            criteria.andClassifyEqualTo(classify);
        }
        if (pid != null) {
            criteria.andPidEqualTo(pid);
        }
        return dataLandLevelDetailMapper.countByExample(example);
    }

    public DataLandLevelDetail getDataLandLevelDetail(Integer landLevelId, String classify, Integer pid) {
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        DataLandLevelDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (landLevelId != null) {
            criteria.andLandLevelIdEqualTo(landLevelId);
        }
        if (StringUtils.isNotBlank(classify)) {
            criteria.andClassifyEqualTo(classify);
        }
        if (pid != null) {
            criteria.andPidEqualTo(pid);
        }
        List<DataLandLevelDetail> dataLandLevelDetails = dataLandLevelDetailMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(dataLandLevelDetails)) return null;
        return dataLandLevelDetails.get(0);
    }

    public List<DataLandLevelDetail> getDataByClassifyAndType(Integer landLevelId, String classify, String type) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        if (landLevelId != null) {
            oo.setLandLevelId(landLevelId);
        }
        if (StringUtils.isNotBlank(classify)) {
            oo.setClassify(classify);
        }
        if (StringUtils.isNotBlank(type)) {
            oo.setType(type);
        }
        return getDataLandLevelDetailList(oo);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(DataLandLevelDetail oo) {
        DataLandLevelDetailExample example = getExample(oo);
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    public List<DataLandLevelDetail> getByMasterIdInfo(List<Integer> integerList) {
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        example.createCriteria().andLandLevelIdIn(integerList);
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    private DataLandLevelDetailExample getExample(DataLandLevelDetail oo) {
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        DataLandLevelDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (oo.getClassify() != null) {
            criteria.andClassifyEqualTo(oo.getClassify());
        }
        if (oo.getType() != null) {
            criteria.andTypeEqualTo(oo.getType());
        }
        if (StringUtils.isNotEmpty(oo.getCategory())) {
            criteria.andCategoryEqualTo(oo.getCategory());
        }
        if (oo.getLandLevelId() != null) {
            criteria.andLandLevelIdEqualTo(oo.getLandLevelId());
        }
        if (oo.getBisDelete() != null) {
            criteria.andBisDeleteEqualTo(oo.getBisDelete());
        }
        if (StringUtils.isNotEmpty(oo.getCreator())) {
            criteria.andCreatorEqualTo(oo.getCreator());
        }
        if (oo.getPid() != null) {
            criteria.andPidEqualTo(oo.getPid());
        }
        example.setOrderByClause("classify,type,gmt_created ASC");
        MybatisUtils.convertObj2Criteria(oo, criteria);
        return example;
    }
}
