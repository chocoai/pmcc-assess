package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

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
    public List<BaseDataDic> getBaseMethodList(Integer projectCategory) {
        List<BaseDataDic> baseMethodList = Lists.newArrayList();
        List<BaseDataDic> allMethodList = getAllMethodList();
        if (CollectionUtils.isEmpty(allMethodList)) return null;
        BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectCategory);
        String classifyFieldName = projectClassify.getFieldName();
        for (BaseDataDic baseDataDic : allMethodList) {
            if (StringUtils.isBlank(baseDataDic.getFieldName())) continue;
            if (StringUtils.isNotBlank(classifyFieldName) && classifyFieldName.contains(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE)) {
                switch (baseDataDic.getFieldName()) {
                    case AssessDataDicKeyConstant.MD_MARKET_COMPARE:
                    case AssessDataDicKeyConstant.MD_INCOME:
                    case AssessDataDicKeyConstant.MD_COST:
                    case AssessDataDicKeyConstant.MD_DEVELOPMENT:
                        baseMethodList.add(baseDataDic);
                        break;
                }
            }
            if (StringUtils.isNotBlank(classifyFieldName) && classifyFieldName.contains(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE)) {
                switch (baseDataDic.getFieldName()) {
                    case AssessDataDicKeyConstant.MD_LAND_COMPARE:
                    case AssessDataDicKeyConstant.MD_INCOME:
                    case AssessDataDicKeyConstant.MD_COST_APPROACH:
                    case AssessDataDicKeyConstant.MD_DEVELOPMENT:
                    case AssessDataDicKeyConstant.MD_BASE_LAND_PRICE:
                        baseMethodList.add(baseDataDic);
                        break;
                }
            }
        }
        baseDataDicService.useDataDicAlias(baseMethodList, AssessDataDicKeyConstant.EXTEND_PROP_METHOD_LAND_ALIAS);
        return baseMethodList;
    }

    /**
     * 获取其它方法
     *
     * @return
     */
    public List<BaseDataDic> getOtherMethodList(Integer projectCategory) {
        List<BaseDataDic> otherMethodList = Lists.newArrayList();
        List<BaseDataDic> allMethodList = getAllMethodList();
        if (CollectionUtils.isEmpty(allMethodList)) return otherMethodList;
        for (BaseDataDic baseDataDic : allMethodList) {
            if (!isBaseMethod(projectCategory, baseDataDic.getId())) {
                otherMethodList.add(baseDataDic);
            }
        }
        return otherMethodList;
    }

    /**
     * 是否为基本方法
     *
     * @param method
     * @return
     */
    public Boolean isBaseMethod(Integer projectCategory, Integer method) {
        List<BaseDataDic> methodList = getBaseMethodList(projectCategory);
        for (BaseDataDic baseDataDic : methodList) {
            if (method.equals(baseDataDic.getId()))
                return true;
        }
        return false;
    }

    /**
     * 是否为其它方法
     *
     * @param method
     * @return
     */
    public Boolean isOtherMethod(Integer projectCategory, Integer method) {
        List<BaseDataDic> methodList = getOtherMethodList(projectCategory);
        for (BaseDataDic baseDataDic : methodList) {
            if (method.equals(baseDataDic.getId()))
                return true;
        }
        return false;
    }

    /**
     * 是否为市场比较法
     *
     * @param method
     * @return
     */
    public Boolean isCompareMethod(Integer method) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        if (baseDataDic != null && baseDataDic.getId().equals(method)) {
            return true;
        }
        return false;
    }

    /**
     * 是否为收益法
     *
     * @param method
     * @return
     */
    public Boolean isIncomeMethod(Integer method) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
        if (baseDataDic != null && baseDataDic.getId().equals(method)) {
            return true;
        }
        return false;
    }

    /**
     * 是否为成本法
     *
     * @param method
     * @return
     */
    public Boolean isCostMethod(Integer method) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
        if (baseDataDic != null && baseDataDic.getId().equals(method)) {
            return true;
        }
        return false;
    }

    /**
     * 是否为假设开发法
     *
     * @param method
     * @return
     */
    public Boolean isDevelopmentMethod(Integer method) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
        if (baseDataDic != null && baseDataDic.getId().equals(method)) {
            return true;
        }
        return false;
    }
}
