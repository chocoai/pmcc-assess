package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeFunctionService {
    @Autowired
    private SchemeJudgeFunctionDao dao;
}
