package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupExample;
import com.copower.pmcc.assess.dal.mapper.SchemeAreaGroupMapper;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import com.copower.pmcc.assess.service.project.DeclareRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Repository
public class SchemeAreaGroupDao {

    @Autowired
    private SchemeAreaGroupMapper mapper;

    @Autowired
    private DeclareRecordService declareRecordService;

    public SchemeAreaGroup get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<SchemeAreaGroupDto> areaGroupDtoList(String groupId){
        List<SchemeAreaGroupDto> schemeAreaGroupDtoList = new ArrayList<>();
        SchemeAreaGroupExample example = new SchemeAreaGroupExample();
        example.createCriteria().andIdIsNotNull().andProjectIdIsNotNull().andGroupIdEqualTo(groupId);
        List<SchemeAreaGroup> schemeAreaGroups = mapper.selectByExample(example);
        schemeAreaGroups.parallelStream().forEach(schemeAreaGroup -> schemeAreaGroupDtoList.add(change(schemeAreaGroup)));
        return schemeAreaGroupDtoList;
    }


    public int add(SchemeAreaGroupDto dto){
        SchemeAreaGroup schemeAreaGroup = change(dto);
        mapper.insert(schemeAreaGroup);
        return schemeAreaGroup.getId();
    }

    public int getID(SchemeAreaGroupDto dto){
        SchemeAreaGroupExample example = new SchemeAreaGroupExample();
        example.createCriteria().andIdIsNotNull().andCityEqualTo(dto.getCity()).andDistrictEqualTo(dto.getDistrict()).andProvinceEqualTo(dto.getProvince())
                .andGroupIdEqualTo(dto.getGroupId()).andGmtCreatedEqualTo(dto.getGmtCreated());
        List<SchemeAreaGroup> schemeAreaGroups = mapper.selectByExample(example);
        return schemeAreaGroups.get(0).getId();
    }

    public boolean addEspecially(SchemeAreaGroupDto dto){
        mapper.insert(change(dto));
        return true;
    }

    public boolean update(SchemeAreaGroupDto dto){
        return mapper.updateByPrimaryKey(change(dto))==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public SchemeAreaGroupDto change(SchemeAreaGroup oo){
        SchemeAreaGroupDto dto = new SchemeAreaGroupDto();
        BeanUtils.copyProperties(oo,dto);
        return dto;
    }

    public SchemeAreaGroup change(SchemeAreaGroupDto dto){
        SchemeAreaGroup oo = new SchemeAreaGroupDto();
        BeanUtils.copyProperties(dto,oo);
        return oo;
    }
}
