package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareInfoExample;
import com.copower.pmcc.assess.dal.mapper.DeclareInfoMapper;
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

    public boolean addDeclareInfo(DeclareInfo declareInfo) {
        int i = declareInfoMapper.insert(declareInfo);
        return i > 0;
    }

    public boolean editDeclareInfo(DeclareInfo declareInfo) {
        int i = declareInfoMapper.updateByPrimaryKeySelective(declareInfo);
        return i > 0;
    }

    public boolean deleteDeclareInfo(Integer id) {
        int i = declareInfoMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
