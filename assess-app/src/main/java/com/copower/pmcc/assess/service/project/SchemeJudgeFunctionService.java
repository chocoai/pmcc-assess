package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeFunctionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeFunctionService {
    @Autowired
    private SchemeJudgeFunctionDao dao;

    public boolean add(SchemeJudgeFunctionDto dto){
        return dao.add(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeFunctionDto get(String creator,Integer methodType,Integer judgeObjectId){
        return dao.get(creator,methodType,judgeObjectId);
    }

    public boolean update(SchemeJudgeFunctionDto dto){
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeFunctionDto get(Integer id){
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }
}
