package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2019-3-6.
 */
@Service
public class MdCommonService {
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 获取所有方法
     *
     * @return
     */
    public List<BaseDataDic> getAllMethodList() {
        return baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
    }

    /**
     * 获取基本方法
     *
     * @return
     */
    public List<BaseDataDic> getBaseMethodList() {
        List<BaseDataDic> baseMethodList = Lists.newArrayList();
        List<BaseDataDic> allMethodList = getAllMethodList();
        if (CollectionUtils.isEmpty(allMethodList)) return null;
        for (BaseDataDic baseDataDic : allMethodList) {
            if (AssessDataDicKeyConstant.MD_MARKET_COMPARE.equals(baseDataDic.getFieldName())) {
                baseMethodList.add(baseDataDic);
            }
            if (AssessDataDicKeyConstant.MD_INCOME.equals(baseDataDic.getFieldName())) {
                baseMethodList.add(baseDataDic);
            }
            if (AssessDataDicKeyConstant.MD_COST.equals(baseDataDic.getFieldName())) {
                baseMethodList.add(baseDataDic);
            }
            if (AssessDataDicKeyConstant.MD_DEVELOPMENT.equals(baseDataDic.getFieldName())) {
                baseMethodList.add(baseDataDic);
            }
        }
        return baseMethodList;
    }

    /**
     * 获取其它方法
     *
     * @return
     */
    public List<BaseDataDic> getOtherMethodList() {
        List<BaseDataDic> otherMethodList = Lists.newArrayList();
        List<BaseDataDic> allMethodList = getAllMethodList();
        if(CollectionUtils.isEmpty(allMethodList)) return otherMethodList;
        for (BaseDataDic baseDataDic : allMethodList) {
            if (AssessDataDicKeyConstant.MD_MARKET_COMPARE.equals(baseDataDic.getFieldName())) {
                continue;
            }
            if (AssessDataDicKeyConstant.MD_INCOME.equals(baseDataDic.getFieldName())) {
                continue;
            }
            if (AssessDataDicKeyConstant.MD_COST.equals(baseDataDic.getFieldName())) {
                continue;
            }
            if (AssessDataDicKeyConstant.MD_DEVELOPMENT.equals(baseDataDic.getFieldName())) {
                continue;
            }
            otherMethodList.add(baseDataDic);
        }
        return otherMethodList;
    }
}
