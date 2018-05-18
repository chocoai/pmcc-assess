package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import com.copower.pmcc.assess.dto.output.project.SchemeAreaGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupService {

    @Autowired
    private SchemeAreaGroupDao dao;

    @Transactional
    public int add(SchemeAreaGroupDto dto){
        return dao.add(dto);
    }

    /**
     * 根据分组 获取数据信息
     * @param groupId
     * @return
     */
    public List<SchemeAreaGroupVo> schemeAreaGroupVoList(String groupId){
        List<SchemeAreaGroupVo> schemeAreaGroupVos = new ArrayList<>();
        List<SchemeAreaGroupDto> dtos = dao.areaGroupDtoList(groupId);
        dtos.parallelStream().forEach(s -> schemeAreaGroupVos.add(change(s)));
        return schemeAreaGroupVos;
    }

    /**
     * 初始化
     * @param dto
     * @return
     */
    @Transactional
    public boolean addEspecially(SchemeAreaGroupDto dto){
        return dao.addEspecially(dto);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeAreaGroupDto dto){
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeAreaGroup get(Integer id){
        return dao.get(id);
    }

    public SchemeAreaGroupVo change(SchemeAreaGroupDto dto){
        SchemeAreaGroupVo vo = new SchemeAreaGroupVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
