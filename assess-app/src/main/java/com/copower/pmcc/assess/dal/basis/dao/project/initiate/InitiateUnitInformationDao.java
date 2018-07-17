package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;
import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformationExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiateUnitInformationMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报告使用单位信息
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateUnitInformationDao {
    @Autowired
    private InitiateUnitInformationMapper mapper;

    public int add(InitiateUnitInformation dto){
        mapper.insertSelective(dto);
        return dto.getId();
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public boolean update(InitiateUnitInformation dto){
        return mapper.updateByPrimaryKeySelective(dto)==1;
    }

    public InitiateUnitInformation get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public InitiateUnitInformation getDataByProjectId(Integer projectId){
        InitiateUnitInformationExample example = new InitiateUnitInformationExample();
        example.createCriteria().andIdIsNotNull().andProjectIdEqualTo(projectId);
        List<InitiateUnitInformation> initiateUnitInformations = mapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(initiateUnitInformations))
            return initiateUnitInformations.get(0);
        return null;
    }

    public List<InitiateUnitInformation> getList(){
        InitiateUnitInformationExample example = new InitiateUnitInformationExample();
        return mapper.selectByExample(example);
    }
}
