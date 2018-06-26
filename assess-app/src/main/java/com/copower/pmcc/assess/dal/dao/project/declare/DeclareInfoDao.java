package com.copower.pmcc.assess.dal.dao.project.declare;

import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareInfoExample;
import com.copower.pmcc.assess.dal.mapper.DeclareInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclareInfoDao {
    @Autowired
    private DeclareInfoMapper declareInfoMapper;

    public DeclareInfo getDeclareInfo(Integer id){
        return declareInfoMapper.selectByPrimaryKey(id);
    }
    
    public DeclareInfo getDeclareInfoByProcessInsId(String processInsId){
        if(StringUtils.isBlank(processInsId)) return null;
        DeclareInfoExample example = new DeclareInfoExample();
        DeclareInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInsIdEqualTo(processInsId);
        List<DeclareInfo> declareInfos = declareInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(declareInfos))
            return declareInfos.get(0);
        return null;
    }

    public boolean addDeclareInfo(DeclareInfo declareInfo) {
        int i = declareInfoMapper.insert(declareInfo);
        return i > 0;
    }

    public boolean updateDeclareInfo(DeclareInfo declareInfo) {
        int i = declareInfoMapper.updateByPrimaryKeySelective(declareInfo);
        return i > 0;
    }

    public boolean deleteDeclareInfo(Integer id) {
        int i = declareInfoMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
