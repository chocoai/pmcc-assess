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
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
    @Autowired
    private MdCommonService mdCommonService;

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

    public SchemeSurePrice getSchemeSurePriceBySchemeJudgeObjectId(Integer schemeJudgeObjectId){
        SchemeSurePrice where = new SchemeSurePrice();
        where.setJudgeObjectId(schemeJudgeObjectId);
        List<SchemeSurePrice> schemeSurePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(schemeSurePriceList)){
            return schemeSurePriceList.get(0);
        }else {
            return null;
        }
    }


    /**
     * 获取单价确定明细数据
     *
     * @param judgeObjectId
     * @return
     */
    @Transactional
    public List<SchemeSurePriceItem> getSchemeSurePriceItemList(Integer judgeObjectId, boolean isUpdatePrice) {
        SchemeSurePriceItem where = new SchemeSurePriceItem();
        where.setJudgeObjectId(judgeObjectId);
        List<SchemeSurePriceItem> surePriceItemList = schemeSurePriceItemDao.getSurePriceItemList(where);
        if (CollectionUtils.isNotEmpty(surePriceItemList)) {//更新试算价格
            if (isUpdatePrice) {
                for (SchemeSurePriceItem schemeSurePriceItem : surePriceItemList) {
                    schemeSurePriceItem.setTrialPrice(getPrice(judgeObjectId, schemeSurePriceItem.getMethodType()));
                    schemeSurePriceItemDao.updateSurePriceItem(schemeSurePriceItem);
                }
            } else {
                return surePriceItemList;
            }

        } else {//初始化数据
            SchemeJudgeFunction functionWhere = new SchemeJudgeFunction();
            functionWhere.setBisApplicable(true);
            functionWhere.setJudgeObjectId(judgeObjectId);
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionDao.getSchemeJudgeFunction(functionWhere);
            if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                List<BaseDataDic> baseMethodList = mdCommonService.getBaseMethodList();
                List<Integer> methodTypeList=LangUtils.transform(baseMethodList,o->o.getId());
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
        if (schemeInfo == null) return null;
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
        return price;
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

        //如果是合并对象，则找出子项并更新子项的单价
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
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
                }
            }
        } else {
            DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
            declareRecordDao.updateDeclareRecord(declareRecord);
        }
    }
}
