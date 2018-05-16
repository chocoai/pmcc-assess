package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
