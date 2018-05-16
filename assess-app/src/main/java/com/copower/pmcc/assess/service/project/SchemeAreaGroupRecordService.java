package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupRecordDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupRecord;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域分组记录
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupRecordService {

    @Autowired
    private SchemeAreaGroupRecordDao dao;

    @Transactional
    public int add(SchemeAreaGroupRecordDto dto){
       return dao.add(dto);
    }

    @Transactional
    public boolean update(SchemeAreaGroupRecordDto dto){
        return dao.update(dto);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional(readOnly = true)
    public SchemeAreaGroupRecord get(Integer id){
        return dao.get(id);
    }

}
