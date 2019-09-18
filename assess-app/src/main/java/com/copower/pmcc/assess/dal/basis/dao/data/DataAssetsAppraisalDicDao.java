package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;
import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDicExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAssetsAppraisalDicMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-9-18.
 */
@Repository
public class DataAssetsAppraisalDicDao {

    @Autowired
    private DataAssetsAppraisalDicMapper mapper;

    public boolean addDataAssetsAppraisalDic(DataAssetsAppraisalDic oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateDataAssetsAppraisalDic(DataAssetsAppraisalDic oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public DataAssetsAppraisalDic getDataAssetsAppraisalDicById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public void deleteDataAssetsAppraisalDic(List<Integer> ids){
        DataAssetsAppraisalDicExample example = new DataAssetsAppraisalDicExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example) ;
    }

    public boolean deleteDataAssetsAppraisalDicById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeDataAssetsAppraisalDic(DataAssetsAppraisalDic oo){
        DataAssetsAppraisalDicExample example = new DataAssetsAppraisalDicExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByExample(DataAssetsAppraisalDic oo){
        DataAssetsAppraisalDicExample example = new DataAssetsAppraisalDicExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(Integer planDetailsId,String type){
        DataAssetsAppraisalDicExample example = new DataAssetsAppraisalDicExample();
        example.createCriteria().andTypeEqualTo(type).andPlanDetailsIdEqualTo(planDetailsId);
        return mapper.selectByExample(example) ;
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByType(String type){
        DataAssetsAppraisalDicExample example = new DataAssetsAppraisalDicExample();
        example.createCriteria().andTypeEqualTo(type);
        return mapper.selectByExample(example) ;
    }
    
}
