package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeFunctionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeFunctionService {
    @Autowired
    private SchemeJudgeFunctionDao dao;

    public boolean add(SchemeJudgeFunction dto){
        return dao.add(dto);
    }

    public List<SchemeJudgeFunction> getListByJudgeObjectId(Integer judgeObjectId){
        return dao.getListByJudgeObjectId(judgeObjectId);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeFunction get(String creator,Integer methodType,Integer judgeObjectId){
        return dao.get(creator,methodType,judgeObjectId);
    }

    public boolean update(SchemeJudgeFunction dto){
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeFunction get(Integer id){
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }
}
