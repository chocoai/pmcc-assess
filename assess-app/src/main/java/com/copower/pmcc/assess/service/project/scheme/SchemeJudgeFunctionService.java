package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
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
    private SchemeJudgeFunctionDao schemeJudgeFunctionDao;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;


    public boolean addSchemeJudgeFunction(SchemeJudgeFunction schemeJudgeFunction) {
        return schemeJudgeFunctionDao.addSchemeJudgeFunction(schemeJudgeFunction);
    }

    public List<SchemeJudgeFunction> getSchemeJudgeFunctions(Integer judgeId) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setJudgeObjectId(judgeId);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }

    /**
     * 获取委估对象适用方法
     * @param judgeId
     * @return
     */
    public List<SchemeJudgeFunction> getApplicableJudgeFunctions(Integer judgeId) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setJudgeObjectId(judgeId);
        schemeJudgeFunction.setBisApplicable(true);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }

    @Transactional
    public void changeFunctionContent(List<SchemeJudgeFunction> functionList){
        if(CollectionUtils.isEmpty(functionList)) return;
        for (SchemeJudgeFunction schemeJudgeFunction : functionList) {
            schemeJudgeFunctionDao.updateSchemeJudgeFunction(schemeJudgeFunction);
        }
    }


    public boolean updateSchemeJudgeFunction(SchemeJudgeFunction schemeJudgeFunction) {
        return schemeJudgeFunctionDao.updateSchemeJudgeFunction(schemeJudgeFunction);
    }

    public SchemeJudgeFunction getSchemeJudgeFunction(Integer id) {
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(id);
    }

    public boolean removeSchemeJudgeFunction(Integer id) {
        return schemeJudgeFunctionDao.removeSchemeJudgeFunction(id);
    }

    /**
     * 保存评估方法
     *
     * @param judgeFunctionList
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveJudgeFunction(List<SchemeJudgeFunction> judgeFunctionList) {
        if (CollectionUtils.isNotEmpty(judgeFunctionList)) {
            judgeFunctionList.forEach(p -> {
                if (p.getId() != null && p.getId() > 0) {
                    schemeJudgeFunctionService.updateSchemeJudgeFunction(p);
                } else {
                    p.setCreator(commonService.thisUserAccount());
                    schemeJudgeFunctionService.addSchemeJudgeFunction(p);
                }
            });
            Integer judgeObjectId = judgeFunctionList.get(0).getJudgeObjectId();
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            if (schemeJudgeObject != null) {
                schemeJudgeObject.setBisSetFunction(true);
                schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
            }
        }
    }

}
