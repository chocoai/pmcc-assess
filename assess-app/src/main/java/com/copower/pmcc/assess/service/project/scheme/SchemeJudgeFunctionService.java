package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeFunctionApplyDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
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
     *
     * @param judgeId
     * @return
     */
    public List<SchemeJudgeFunction> getApplicableJudgeFunctions(Integer judgeId) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setJudgeObjectId(judgeId);
        schemeJudgeFunction.setBisApplicable(true);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }

    /**
     * 获取适用方法
     *
     * @param areaId
     * @return
     */
    public List<SchemeJudgeFunction> getApplicableJudgeFunctionsByAreaId(Integer areaId) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setAreaGroupId(areaId);
        schemeJudgeFunction.setBisApplicable(true);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }

    /**
     * 取得不适用方法
     *
     * @param areaId
     * @return
     */
    public List<SchemeJudgeFunction> getNotApplicableJudgeFunctionsByAreaId(Integer areaId) {
        SchemeJudgeFunction schemeJudgeFunction = new SchemeJudgeFunction();
        schemeJudgeFunction.setAreaGroupId(areaId);
        schemeJudgeFunction.setBisApplicable(false);
        return schemeJudgeFunctionDao.getSchemeJudgeFunction(schemeJudgeFunction);
    }

    @Transactional
    public void changeFunctionContent(List<SchemeJudgeFunction> functionList) {
        if (CollectionUtils.isEmpty(functionList)) return;
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

    public boolean deleteSchemeJudgeFunction(Integer id) {
        return schemeJudgeFunctionDao.deleteSchemeJudgeFunction(id);
    }

    /**
     * 获取评估方法
     *
     * @param judgeObjectId
     * @return
     */
    public SchemeJudgeFunctionApplyDto getJudgeFunction(Integer judgeObjectId) {
        SchemeJudgeFunctionApplyDto schemeJudgeFunctionApplyDto = new SchemeJudgeFunctionApplyDto();
        schemeJudgeFunctionApplyDto.setJudgeFunctions(schemeJudgeFunctionService.getSchemeJudgeFunctions(judgeObjectId));
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        schemeJudgeFunctionApplyDto.setJudgeObjectId(judgeObjectId);
        schemeJudgeFunctionApplyDto.setSingleMethodRationale(judgeObject.getSingleMethodRationale());
        return schemeJudgeFunctionApplyDto;
    }

    /**
     * 保存评估方法
     *
     * @param schemeJudgeFunctionApplyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveJudgeFunction(SchemeJudgeFunctionApplyDto schemeJudgeFunctionApplyDto) {
        //先清除原数据
        schemeJudgeFunctionDao.deleteJudgeFunctionByJudgeId(schemeJudgeFunctionApplyDto.getJudgeObjectId());
        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionApplyDto.getJudgeFunctions();
        if (CollectionUtils.isNotEmpty(judgeFunctions)) {
            Integer judgeObjectId = schemeJudgeFunctionApplyDto.getJudgeObjectId();
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            List<String> methodList = Lists.newArrayList();
            if (schemeJudgeObject != null) {
                for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                    if (judgeFunction.getId() != null && judgeFunction.getId() > 0) {
                        schemeJudgeFunctionService.updateSchemeJudgeFunction(judgeFunction);
                    } else {
                        judgeFunction.setAreaGroupId(schemeJudgeObject.getAreaGroupId());
                        judgeFunction.setCreator(commonService.thisUserAccount());
                        schemeJudgeFunctionService.addSchemeJudgeFunction(judgeFunction);
                    }
                    if (judgeFunction.getBisApplicable() == Boolean.TRUE)
                        methodList.add(String.valueOf(judgeFunction.getMethodType()));
                }
                schemeJudgeObject.setBisSetFunction(true);
                schemeJudgeObject.setSingleMethodRationale(schemeJudgeFunctionApplyDto.getSingleMethodRationale());
                schemeJudgeObject.setJudgeFunction(FormatUtils.transformListString(methodList));
                schemeJudgeObject.setNotApplicableReason(schemeJudgeFunctionApplyDto.getNotApplicableReason());
                schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
            }
        }
    }

}
