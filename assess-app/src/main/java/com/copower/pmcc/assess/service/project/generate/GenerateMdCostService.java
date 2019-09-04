package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.task.TaskExecutor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zch on 2019-8-26.
 * 成本法报告模板
 */
public class GenerateMdCostService implements Serializable {
    private final String errorStr = "无";

    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
    private MdCostVo costVo;

    private Integer areaId;
    private SchemeInfo schemeInfo;
    private Integer projectId;

    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private MdMarketCostService mdMarketCostService;
    private BaseService baseService;
    private TaskExecutor taskExecutor;


    protected String generateCompareFile() throws Exception {
        MdCostVo costVo = getMdCostVo();
        String key = Objects.equal("1", costVo.getType()) ? AssessReportFieldConstant.COST_TEMPLATE_BUILDING : AssessReportFieldConstant.COST_TEMPLATE_CONSTRUCTION;
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(key);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<String, BaseReportFieldEnum> reportFieldEnumMap = new HashMap<String, BaseReportFieldEnum>(0);
        for (BaseReportFieldEnum reportFieldEnum : BaseReportFieldEnum.values()) {
            reportFieldEnumMap.put(reportFieldEnum.getName(), reportFieldEnum);
        }
        Map<BaseReportFieldEnum, String> map = Maps.newHashMap();
        //获取待替换文本的集合
        List<String> stringList = generateCommonMethod.specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                if (reportFieldEnumMap.keySet().contains(bookmarkCollection.get(i).getName())) {
                    map.put(reportFieldEnumMap.get(bookmarkCollection.get(i).getName()), bookmarkCollection.get(i).getName());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (String name : stringList) {
                if (reportFieldEnumMap.keySet().contains(name)) {
                    map.put(reportFieldEnumMap.get(name), name);
                }
            }
        }

        final ConcurrentHashMap<String, String> textMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> bookmarkMap = new ConcurrentHashMap<String, String>(0);
        final long time = 70;//最多阻塞70秒
        //设置必要的数据
        final MdCostVo mdCostVo = getMdCostVo();
        final SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        final SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        JSONObject jsonObject = null;
        try {
            MdCostConstruction target = mdMarketCostService.getMdCostConstruction(mdCostVo.getMdCostConstruction().getId());
            jsonObject = JSON.parseObject(target.getJsonContent());
        } catch (Exception e) {
        }
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        final JSONObject jsonObject1 = jsonObject;
        synchronized (this) {
            if (!map.isEmpty()) {
                //发起线程组
                final CountDownLatch countDownLatch = new CountDownLatch(map.size());
                for (Map.Entry<BaseReportFieldEnum, String> enumStringEntry : map.entrySet()) {
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                setFieldObjectValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, mdCostVo, schemeAreaGroup, schemeJudgeObject, jsonObject1);
                            } catch (Exception e) {
                                baseService.writeExceptionInfo(e);
                            } finally {
                                countDownLatch.countDown();
                            }
                        }
                    });
                }
                //能够阻塞线程 直到调用N次end.countDown() 方法才释放线程
                countDownLatch.await(time, TimeUnit.SECONDS);
            }
            //替换
            if (!textMap.isEmpty()) {
                AsposeUtils.replaceText(localPath, textMap);
            }
            if (!bookmarkMap.isEmpty()) {
                AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
            }
            if (!fileMap.isEmpty()) {
                AsposeUtils.replaceTextToFile(localPath, fileMap);
                AsposeUtils.replaceBookmarkToFile(localPath, fileMap);
            }
        }
        return localPath;
    }

    /**
     * 传入的待组装对象最好是不可变的对象或者副本对象
     *
     * @param key
     * @param textMap
     * @param fileMap
     * @param bookmarkMap
     * @param mdCostVo
     * @param schemeAreaGroup
     * @param schemeJudgeObject
     * @param jsonObject
     */
    private void setFieldObjectValue(BaseReportFieldEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdCostVo mdCostVo, SchemeAreaGroup schemeAreaGroup, SchemeJudgeObject schemeJudgeObject, JSONObject jsonObject) {
        final String defaultValue = "";
        MdCostConstructionVo target = mdCostVo.getMdCostConstruction();
        //计算类数据
        String value = mdMarketCostService.getFieldObjectValue(key, target);
        if (StringUtils.isNotBlank(value)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), value);
        }
        //非计算类数据
        switch (key) {
            case MarketCost_Method: {
                if (mdCostVo.getMdCostConstruction().getMcId() != null) {
                    try {
                        GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeJudgeObject.getId(), mdCostVo.getMdCostConstruction().getMcId(), schemeAreaGroup.getId());
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), generateMdCompareService.generateCompareFile());
                    } catch (Exception e) {
                        baseService.writeExceptionInfo(e);
                    }
                } else {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "市场比较法");
                }
            }
            break;
            case MarketCost_extraterritorial_reality: {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            }
            break;
            case MarketCost_intra_territorial_reality: {
            }
            break;
            case MarketCost_region: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), schemeAreaGroup.getAreaName());
            }
            break;
            case MarketCost_extraterritorial_setting: {
            }
            break;
            case MarketCost_intra_territorial_setting: {
            }
            break;
            case MarketCost_GroundFloor_AreaCounted_volume_ratio: {
            }
            break;
            case MarketCost_constructionInstallationEngineeringFee: {
                BigDecimal start = ArithmeticUtils.divide(ArithmeticUtils.multiply(target.getConstructionAssessmentPriceCorrecting(), target.getDevelopLandAreaTax(), 2), ArithmeticUtils.createBigDecimal(10000), 2);
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getBigDecimalString(start));
            }
            break;
            case MarketCost_constructionInstallationEngineeringFee_Sheet: {
            }
            break;
            case MarketCost_constructionInstallationEngineeringFee_Basis: {
            }
            break;
            case MarketCost_Planning_land_area_construction: {
            }
            break;
            case MarketCost_AssessBuildArea: {
            }
            break;
            case MarketCost_landPurchasePriceExplain: {
                if (StringUtils.isNotBlank(target.getLandPurchasePriceExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getLandPurchasePriceExplain());
                }
            }
            break;
            case MarketCost_landGetRelevantExplain: {
                if (StringUtils.isNotBlank(target.getLandGetRelevantExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getLandGetRelevantExplain());
                }
            }
            break;
            case MarketCost_additionalCostLandAcquisitionExplain: {
                if (StringUtils.isNotBlank(target.getAdditionalCostLandAcquisitionExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getAdditionalCostLandAcquisitionExplain());
                }
            }
            break;
            case MarketCost_infrastructureCostBasis: {
            }
            break;
            case MarketCost_unforeseenExpensesExplain: {
                if (StringUtils.isNotBlank(target.getUnforeseenExpensesExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getUnforeseenExpensesExplain());
                }
            }
            break;
            case MarketCost_managementExpenseExplain: {
                if (StringUtils.isNotBlank(target.getManagementExpenseExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getManagementExpenseExplain());
                }
            }
            break;
            case MarketCost_salesFeeExplain: {
                if (StringUtils.isNotBlank(target.getSalesFeeExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getSalesFeeExplain());
                }
            }
            break;
            case MarketCost_interestInvestmentTaxExplain: {
                if (StringUtils.isNotBlank(target.getInterestInvestmentTaxExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getInterestInvestmentTaxExplain());
                }
            }
            break;
            case MarketCost_salesTaxAndAdditionalExplain: {
                if (StringUtils.isNotBlank(target.getSalesTaxAndAdditionalExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getSalesTaxAndAdditionalExplain());
                }
            }
            break;
            case MarketCost_investmentProfitTaxExplain: {
                if (StringUtils.isNotBlank(target.getInvestmentProfitTaxExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getInvestmentProfitTaxExplain());
                }
            }
            break;
            default: {
            }
            break;
        }
    }


    private MdCostVo getMdCostVo() {
        if (this.costVo == null) {
            synchronized (this) {
                this.costVo = mdMarketCostService.getMdCostVo(mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId()));
            }
        }
        return this.costVo;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            synchronized (this) {
                this.schemeAreaGroup = schemeAreaGroupService.get(areaId);
            }
        }
        return this.schemeAreaGroup;
    }

    private SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject == null) {
            synchronized (this) {
                if (schemeInfo.getJudgeObjectId() != null) {
                    schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeInfo.getJudgeObjectId());
                }
                if (schemeJudgeObject == null) {
                    schemeJudgeObject = new SchemeJudgeObject();
                }
            }
        }
        return schemeJudgeObject;
    }

    public GenerateMdCostService(Integer projectId, SchemeInfo schemeInfo, Integer areaId) {
        this.projectId = projectId;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;

        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.mdMarketCostService = SpringContextUtils.getBean(MdMarketCostService.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.taskExecutor = SpringContextUtils.getBean(TaskExecutor.class);
    }

}
