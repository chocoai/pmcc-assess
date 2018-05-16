package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupRecord;
import com.copower.pmcc.assess.dal.mapper.SchemeAreaGroupRecordMapper;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupRecordDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 区域分组记录
 * Created by 13426 on 2018/5/14.
 */
@Repository
public class SchemeAreaGroupRecordDao {
    @Autowired
    private SchemeAreaGroupRecordMapper mapper;

    public int add(SchemeAreaGroupRecordDto dto){
        mapper.insertSelective(change(dto));
        return dto.getId();
    }

    public boolean update(SchemeAreaGroupRecordDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public SchemeAreaGroupRecord get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public SchemeAreaGroupRecordDto change(SchemeAreaGroupRecord oo){
        SchemeAreaGroupRecordDto dto = new SchemeAreaGroupRecordDto();
        BeanUtils.copyProperties(oo,dto);
        return dto;
    }

    public SchemeAreaGroupRecord change(SchemeAreaGroupRecordDto dto){
        SchemeAreaGroupRecord oo = new SchemeAreaGroupRecord();
        BeanUtils.copyProperties(dto,oo);
        return oo;
    }
}
