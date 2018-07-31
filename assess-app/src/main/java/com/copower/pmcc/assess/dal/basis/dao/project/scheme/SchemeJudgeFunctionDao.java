package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunctionExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeFunctionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估方法
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeJudgeFunctionDao {
    @Autowired
    private SchemeJudgeFunctionMapper mapper;

    public boolean add(SchemeJudgeFunction schemeJudgeFunction) {
        return mapper.insertSelective(schemeJudgeFunction) == 1;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(SchemeJudgeFunction schemeJudgeFunction) {
        return mapper.updateByPrimaryKeySelective(schemeJudgeFunction) == 1;
    }

    public SchemeJudgeFunction get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<SchemeJudgeFunction> getSchemeJudgeFunction(SchemeJudgeFunction schemeJudgeFunction) {
        SchemeJudgeFunctionExample example = new SchemeJudgeFunctionExample();
        MybatisUtils.convertObj2Example(schemeJudgeFunction,example);
        List<SchemeJudgeFunction> schemeJudgeFunctions = mapper.selectByExample(example);
        return schemeJudgeFunctions;
    }

}
