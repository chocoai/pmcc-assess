package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
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

    public boolean add(SchemeJudgeFunction dto) {
        return dao.add(dto);
    }

    public List<SchemeJudgeFunction> getSchemeJudgeFunctions(Integer areaGroupId, Integer groupNumber) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setAreaGroupId(areaGroupId);
        schemeJudgeFunction.setGroupNumber(groupNumber);
        return dao.getSchemeJudgeFunction(schemeJudgeFunction);
    }


    public boolean update(SchemeJudgeFunction dto) {
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeFunction get(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
