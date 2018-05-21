package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.mapper.SchemeEvaluationObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 委估对象 （合并的记录）
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeEvaluationObjectDao {

    @Autowired
    private SchemeEvaluationObjectMapper mapper;
}
