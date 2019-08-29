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
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MdCommonService mdCommonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSurePriceFactorService schemeSurePriceFactorService;

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
     * 获取数据by planDetailsId
     *
     * @param planDetailsId
     * @return
     */
    public SchemeSurePrice getSurePriceByPlanDetailsId(Integer planDetailsId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setPlanDetailsId(planDetailsId);
        return schemeSurePriceDao.getSchemeSurePrice(where);
    }

    public SchemeSurePrice getSchemeSurePriceBySchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setJudgeObjectId(schemeJudgeObjectId);
        List<SchemeSurePrice> schemeSurePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(schemeSurePriceList)) {
            return schemeSurePriceList.get(0);
        } else {
            return null;
        }
    }

    public void deleteSurePriceAll(Integer projectId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setProjectId(projectId);
        List<SchemeSurePrice> schemeSurePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(schemeSurePriceList)) {
            for (SchemeSurePrice schemeSurePrice : schemeSurePriceList) {
                SchemeSurePriceItem whereItem = new SchemeSurePriceItem();
                where.setJudgeObjectId(schemeSurePrice.getJudgeObjectId());
                List<SchemeSurePriceItem> surePriceItemList = schemeSurePriceItemDao.getSurePriceItemList(whereItem);
                if (CollectionUtils.isNotEmpty(surePriceItemList))
                    surePriceItemList.forEach(o -> schemeSurePriceItemDao.deleteSurePriceItem(o.getId()));
                schemeSurePriceDao.deleteSurePrice(schemeSurePrice.getId());
            }
        }
    }

    /**
     * 获取单价确定明细数据
     *
     * @param judgeObjectId
     * @return
     */
    @Transactional
    public List<SchemeSurePriceItem> getSchemeSurePriceItemList(Integer judgeObjectId, Boolean isUpdatePrice) throws BusinessException {
        SchemeSurePriceItem where = new SchemeSurePriceItem();
        where.setJudgeObjectId(judgeObjectId);
        List<SchemeSurePriceItem> surePriceItemList = schemeSurePriceItemDao.getSurePriceItemList(where);
        SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(judgeObjectId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(judgeObject.getProjectId());
        if (CollectionUtils.isNotEmpty(surePriceItemList)) {
            if(Boolean.TRUE == isUpdatePrice){//更新试算价格
                for (SchemeSurePriceItem schemeSurePriceItem : surePriceItemList) {
                    schemeSurePriceItem.setTrialPrice(getPrice(judgeObjectId, schemeSurePriceItem.getMethodType()));
                    schemeSurePriceItemDao.updateSurePriceItem(schemeSurePriceItem);
                }
            }
        } else {//初始化数据
            SchemeJudgeFunction functionWhere = new SchemeJudgeFunction();
            functionWhere.setBisApplicable(true);
            functionWhere.setJudgeObjectId(judgeObjectId);
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionDao.getSchemeJudgeFunction(functionWhere);
            if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                List<BaseDataDic> baseMethodList = mdCommonService.getBaseMethodList(projectInfo.getProjectCategoryId());
                List<Integer> methodTypeList = LangUtils.transform(baseMethodList, o -> o.getId());
                List<SchemeJudgeFunction> filter = LangUtils.filter(judgeFunctions, o -> methodTypeList.contains(o.getMethodType()));
                for (SchemeJudgeFunction judgeFunction : filter) {
                    SchemeSurePriceItem schemeSurePriceItem = new SchemeSurePriceItem();
                    schemeSurePriceItem.setJudgeObjectId(judgeObjectId);
                    schemeSurePriceItem.setMethodType(judgeFunction.getMethodType());
                    schemeSurePriceItem.setMethodName(baseDataDicService.getNameById(judgeFunction.getMethodType()));
                    schemeSurePriceItem.setTrialPrice(getPrice(judgeObjectId, judgeFunction.getMethodType()));
                    schemeSurePriceItem.setCreator(commonService.thisUserAccount());
                    schemeSurePriceItemDao.addSurePriceItem(schemeSurePriceItem);
                }
            }
        }

        List<SchemeSurePriceItem> priceItemList = schemeSurePriceItemDao.getSurePriceItemList(where);
        if (CollectionUtils.isEmpty(priceItemList)) {
            return null;
        }
        //如果价格差异小于等于10% 自动设置对应权重 求取平均价
        List<BigDecimal> decimalList = LangUtils.transform(priceItemList, o -> o.getTrialPrice());
        if (CollectionUtils.isEmpty(decimalList)) {
            throw new BusinessException("未获取到方法试算价格");
        }
        Collections.sort(decimalList);
        int equ = publicService.computeDifference(decimalList.stream().findFirst().get(), decimalList.get(decimalList.size() - 1));
        if (equ <= 10) {
            BigDecimal averageWeight = new BigDecimal("1").divide(new BigDecimal(priceItemList.size()), 4, BigDecimal.ROUND_HALF_DOWN);
            for (int i = 0; i < priceItemList.size(); i++) {
                if (i == priceItemList.size() - 1) {
                    priceItemList.get(i).setWeight(new BigDecimal("1").subtract(averageWeight.multiply(new BigDecimal(priceItemList.size() - 1))));
                } else {
                    priceItemList.get(i).setWeight(averageWeight);
                }
                schemeSurePriceItemDao.updateSurePriceItem(priceItemList.get(i));
            }
            return schemeSurePriceItemDao.getSurePriceItemList(where);
        }
        return priceItemList;
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
     * 获取方法测算价格
     *
     * @param methodType
     * @param judgeObjectId
     * @return
     */
    private BigDecimal getPrice(Integer judgeObjectId, Integer methodType) {
        SchemeInfo where = new SchemeInfo();
        where.setJudgeObjectId(judgeObjectId);
        where.setMethodType(methodType);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(where);
        if (schemeInfo == null) return new BigDecimal("0");
        Integer methodDataId = schemeInfo.getMethodDataId();
        String methTypeKey = baseDataDicService.getDataDicById(methodType).getFieldName();
        BigDecimal price = null;
        switch (methTypeKey) {
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
            case AssessDataDicKeyConstant.MD_DEVELOPMENT:
                MdDevelopment mdDevelopment = mdDevelopmentDao.getMdDevelopmentById(methodDataId);
                if (mdDevelopment != null)
                    price = mdDevelopment.getPrice();
                break;
        }
        return price == null ? new BigDecimal("0") : price;
    }

    /**
     * 提交对应单价
     *
     * @param schemeSurePriceApplyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitSurePrice(SchemeSurePriceApplyDto schemeSurePriceApplyDto, ProjectPlanDetails projectPlanDetails, String processInsId) {
        SchemeSurePrice schemeSurePrice = schemeSurePriceDao.getSurePriceById(schemeSurePriceApplyDto.getId());
        schemeSurePrice.setProcessInsId(processInsId);
        schemeSurePrice.setWeightExplain(schemeSurePriceApplyDto.getWeightExplain());
        schemeSurePrice.setPrice(schemeSurePriceApplyDto.getPrice());
        schemeSurePriceDao.updateSurePrice(schemeSurePrice);

        List<SchemeSurePriceItem> surePriceItems = schemeSurePriceApplyDto.getSurePriceItemList();
        if (CollectionUtils.isNotEmpty(surePriceItems)) {
            for (SchemeSurePriceItem surePriceItem : surePriceItems) {
                schemeSurePriceItemDao.updateSurePriceItem(surePriceItem);
            }
        }
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(schemeSurePriceApplyDto.getJudgeObjectId());
        if (schemeJudgeObject != null) {
            schemeJudgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
        List<SchemeJudgeObject> childrenList = Lists.newArrayList();
        //如果是合并对象，则找出子项并更新子项的单价
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                childrenList.addAll(judgeObjectList);
            }
        } else {
            DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
            declareRecordDao.updateDeclareRecord(declareRecord);
        }
        if (CollectionUtils.isNotEmpty(childrenList)) {
            boolean bisRestart = false;
            //重启之后对合并的子估价对象进行更新
            if (projectPlanDetails.getBisRestart() != null) {
                if (projectPlanDetails.getBisRestart()) {
                    bisRestart = projectPlanDetails.getBisRestart();
                }
            }
            for (SchemeJudgeObject judgeObject : childrenList) {
                //先检查是否调整过单价，如果调整过则不做更新处理
                if (judgeObject.getPrice() == null) {
                    judgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
                    judgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
                    schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);

                    //同步更新申报记录单价
                    DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(judgeObject.getDeclareRecordId());
                    declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
                    declareRecordDao.updateDeclareRecord(declareRecord);
                }
                final Integer one = 1;
                final Integer zero = 0;
                Map<Integer, List<BigDecimal>> integerBigDecimalMap = Maps.newHashMap();
                if (bisRestart) {
                    //不更新作为标准的哪个估价对象
                    if (!Objects.equal(judgeObject.getId(), schemeJudgeObject.getId()) && judgeObject.getDeclareRecordId() != null) {
                        List<SchemeSurePriceFactor> schemeSurePriceFactorList = schemeSurePriceFactorService.getSurePriceFactors(judgeObject.getDeclareRecordId());
                        if (CollectionUtils.isNotEmpty(schemeSurePriceFactorList)) {
                            for (SchemeSurePriceFactor priceFactor : schemeSurePriceFactorList) {
                                if (priceFactor.getType() == null || priceFactor.getCoefficient() == null) {
                                    continue;
                                }
                                List<BigDecimal> bigDecimalList = integerBigDecimalMap.get(priceFactor.getType());
                                if (CollectionUtils.isEmpty(bigDecimalList)) {
                                    bigDecimalList = Lists.newArrayList();
                                }
                                bigDecimalList.add(priceFactor.getCoefficient());
                                integerBigDecimalMap.put(priceFactor.getType(), bigDecimalList);
                            }
                        }
                    }
                }
                if (!integerBigDecimalMap.isEmpty()) {
                    List<BigDecimal> bigDecimalListA = Lists.newArrayList();
                    List<BigDecimal> bigDecimalListB = Lists.newArrayList();
                    for (Map.Entry<Integer, List<BigDecimal>> integerListEntry : integerBigDecimalMap.entrySet()) {
                        if (Objects.equal(zero, integerListEntry.getKey())) {
                            bigDecimalListA.addAll(integerListEntry.getValue());
                        }
                        if (Objects.equal(one, integerListEntry.getKey())) {
                            bigDecimalListB.addAll(integerListEntry.getValue());
                        }
                    }
                    BigDecimal temp = new BigDecimal(0);
                    if (CollectionUtils.isNotEmpty(bigDecimalListA)) {
                        double sum = bigDecimalListA.stream().mapToDouble(BigDecimal::doubleValue).sum();
                        temp = temp.add(new BigDecimal(sum));
                    }
                    if (CollectionUtils.isNotEmpty(bigDecimalListB)) {
                        double sum = bigDecimalListB.stream().mapToDouble(BigDecimal::doubleValue).sum();
                        temp = temp.add(new BigDecimal(sum).multiply(schemeSurePriceApplyDto.getPrice()));
                    }
                    temp = temp.add(schemeSurePriceApplyDto.getPrice());
                    judgeObject.setPrice(temp);
                    judgeObject.setOriginalPrice(temp);
                    schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
                }
            }
        }
    }
}
