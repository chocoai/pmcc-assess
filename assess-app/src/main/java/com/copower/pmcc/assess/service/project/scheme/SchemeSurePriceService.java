package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeFunctionDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-10-15.
 */
@Service
public class SchemeSurePriceService {
    @Autowired
    private SchemeSurePriceDao schemeSurePriceDao;
    @Autowired
    private SchemeJudgeFunctionDao schemeJudgeFunctionDao;
    @Autowired
    private SchemeInfoDao schemeInfoDao;

    /**
     * 获取委估对象单价确定信息
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeSurePrice> getSchemeSurePriceList(Integer judgeObjectId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setJudgeObjectId(judgeObjectId);
        List<SchemeSurePrice> surePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(surePriceList)) return surePriceList;

        SchemeInfo schemeInfoWhere = new SchemeInfo();
        schemeInfoWhere.setJudgeObjectId(judgeObjectId);
        List<SchemeInfo> infoList = schemeInfoDao.getInfoList(schemeInfoWhere);
        if(CollectionUtils.isNotEmpty(infoList)){
            for (SchemeInfo schemeInfo : infoList) {

            }
        }

        SchemeJudgeFunction functionWhere = new SchemeJudgeFunction();
        functionWhere.setJudgeObjectId(judgeObjectId);
        functionWhere.setBisApplicable(true);
        List<SchemeJudgeFunction> functionList = schemeJudgeFunctionDao.getSchemeJudgeFunction(functionWhere);
        if (CollectionUtils.isNotEmpty(functionList)) {
            for (SchemeJudgeFunction schemeJudgeFunction : functionList) {

            }
        }
        return null;
    }
}
