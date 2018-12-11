package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandLevelDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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

    public Integer addDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail){
         dataLandLevelDetailMapper.insertSelective(dataLandLevelDetail);
         return dataLandLevelDetail.getId();
    }

    public DataLandLevelDetail getDataLandLevelDetailById(Integer id){
        return dataLandLevelDetailMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail){
        return dataLandLevelDetailMapper.updateByPrimaryKeySelective(dataLandLevelDetail)==1;
    }

    public void removeDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail){
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        MybatisUtils.convertObj2Example(dataLandLevelDetail, example);
        dataLandLevelDetailMapper.deleteByExample(example);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(Integer landLevelId){
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        example.createCriteria().andLandLevelIdEqualTo(landLevelId).andBisDeleteEqualTo(false);
        example.setOrderByClause("classify,type,category");
        return dataLandLevelDetailMapper.selectByExample(example);
    }

    public int getCountByLandLevelId(Integer landLevelId){
        DataLandLevelDetailExample example = new DataLandLevelDetailExample();
        example.createCriteria().andLandLevelIdEqualTo(landLevelId).andBisDeleteEqualTo(false);
        return dataLandLevelDetailMapper.countByExample(example);
    }
}
