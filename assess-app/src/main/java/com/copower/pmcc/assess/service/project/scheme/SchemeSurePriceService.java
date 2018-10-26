package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-10-15.
 */
@Service
public class SchemeSurePriceService {
    @Autowired
    private SchemeSurePriceDao schemeSurePriceDao;
    @Autowired
    private SchemeSurePriceItemDao schemeSurePriceItemDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private SchemeInfoDao schemeInfoDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;
    @Autowired
    private MdIncomeDao mdIncomeDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeFunctionDao schemeJudgeFunctionDao;

    /**
     * 保存确定单价信息
     *
     * @param schemeSurePrice
     */
    public void saveSchemeSurePrice(SchemeSurePrice schemeSurePrice) {
        if (schemeSurePrice.getId() == null || schemeSurePrice.getId() == 0) {
            schemeSurePrice.setCreator(commonService.thisUserAccount());
            schemeSurePriceDao.addSurePrice(schemeSurePrice);
        } else {
            schemeSurePriceDao.updateSurePrice(schemeSurePrice);
        }
    }


    /**
     * 获取单价确定明细数据
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeSurePriceItem> getSchemeSurePriceItemList(Integer judgeObjectId) {
        SchemeSurePriceItem where = new SchemeSurePriceItem();
        where.setJudgeObjectId(judgeObjectId);
        List<SchemeSurePriceItem> surePriceItemList = schemeSurePriceItemDao.getSurePriceItemList(where);
        if (CollectionUtils.isNotEmpty(surePriceItemList)) {//更新试算价格

        } else {//初始化数据
            SchemeJudgeFunction functionWhere = new SchemeJudgeFunction();
            functionWhere.setBisApplicable(true);
            functionWhere.setJudgeObjectId(judgeObjectId);
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionDao.getSchemeJudgeFunction(functionWhere);
            if(CollectionUtils.isNotEmpty(judgeFunctions)){
                for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                    SchemeSurePriceItem schemeSurePriceItem = new SchemeSurePriceItem();
                    schemeSurePriceItem.setJudgeObjectId(judgeObjectId);
                    schemeSurePriceItem.setMethodName(baseDataDicService.getNameById(judgeFunction.getMethodType()));

                    //schemeSurePriceItem.setTrialPrice(getPrice(schemeInfo.getMethodType(), schemeInfo.getMethodDataId()));
                    schemeSurePriceItem.setCreator(commonService.thisUserAccount());
                    schemeSurePriceItemDao.addSurePriceItem(schemeSurePriceItem);
                }
            }
        }

        //应该取得该委估对象所适用的方法，并取出该适用方法所测试出来的单价


        SchemeInfo schemeInfoWhere = new SchemeInfo();
        schemeInfoWhere.setJudgeObjectId(judgeObjectId);
        List<SchemeInfo> infoList = schemeInfoDao.getInfoList(schemeInfoWhere);
        if (CollectionUtils.isNotEmpty(infoList)) {
            for (SchemeInfo schemeInfo : infoList) {
                if (StringUtils.isEmpty(schemeInfo.getMethodType())) continue;
                SchemeSurePriceItem schemeSurePriceItem = new SchemeSurePriceItem();
                schemeSurePriceItem.setJudgeObjectId(judgeObjectId);
//                schemeSurePriceItem.setMethodName(getMethodName(schemeInfo.getMethodType()));
//                schemeSurePriceItem.setTrialPrice(getPrice(schemeInfo.getMethodType(), schemeInfo.getMethodDataId()));
                schemeSurePriceItem.setCreator(commonService.thisUserAccount());
                schemeSurePriceItemDao.addSurePriceItem(schemeSurePriceItem);
            }
        }
        return schemeSurePriceItemDao.getSurePriceItemList(where);
    }

    /**
     * 获取方法名称
     *
     * @param methodType
     * @return
     */
    private String getMethodName(String methodType) {
        if (StringUtils.isEmpty(methodType)) return null;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(methodType);
        if (baseDataDic == null) return null;
        return baseDataDic.getName();
    }

    /**
     * 获取方法测试价格
     *
     * @param methodType
     * @param methodDataId
     * @return
     */
    private BigDecimal getPrice(String methodType, Integer methodDataId) {
        if (StringUtils.isEmpty(methodType)) return null;
        BigDecimal price = new BigDecimal("0");
        switch (methodType) {
            case AssessDataDicKeyConstant.MD_MARKET_COMPARE:
                MdMarketCompare marketCompare = mdMarketCompareDao.getMarketCompareById(methodDataId);
                if (marketCompare != null)
                    price = marketCompare.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_INCOME:
                MdIncome mdIncome = mdIncomeDao.getIncomeById(methodDataId);
                if (mdIncome != null)
                    price = mdIncome.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_COST:
                MdCost mdCost = mdCostDao.getEstateNetworkById(methodDataId);
                if (mdCost != null)
                    price = mdCost.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_HYPOTHESIS:
                MdDevelopment mdDevelopment = mdDevelopmentDao.getMdDevelopmentById(methodDataId);
                if (mdDevelopment != null)
                    price = mdDevelopment.getPrice();
                break;
        }
        return price;
    }

    /**
     * 保存确定后单价
     *
     * @param schemeSurePriceApplyDto
     */
    @Transactional
    public void saveSurePrice(SchemeSurePriceApplyDto schemeSurePriceApplyDto) {
        List<SchemeSurePrice> priceList = schemeSurePriceApplyDto.getSurePriceList();
        if (CollectionUtils.isNotEmpty(priceList)) {
            for (SchemeSurePrice schemeSurePrice : priceList) {
                schemeSurePriceDao.updateSurePrice(schemeSurePrice);
            }
        }
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(schemeSurePriceApplyDto.getJudgeObjectId());
        if (schemeJudgeObject != null) {
            schemeJudgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }

        //如果是合并对象，则找出子项并更新子项的单价
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    judgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
                    judgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
                    schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);

                    DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(judgeObject.getDeclareRecordId());
                    declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
                    declareRecordDao.updateDeclareRecord(declareRecord);
                }
            }
        } else {
            DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
            declareRecordDao.updateDeclareRecord(declareRecord);
        }
    }
}
