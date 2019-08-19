package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

/**
 * Created by zch on 2019/7/9.
 * 假设开发法
 */
public class GenerateMdDevelopmentService {
    private final String errorStr = "无";

    private MdDevelopmentVo mdDevelopmentVo;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;

    private Integer areaId;
    private SchemeInfo schemeInfo;
    private Integer projectId;

    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private SchemeInfoService schemeInfoService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private MdDevelopmentService mdDevelopmentService;
    private DataSetUseFieldService dataSetUseFieldService;

    public GenerateMdDevelopmentService(Integer projectId, SchemeInfo schemeInfo, Integer areaId) {
        this.projectId = projectId;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;

        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdDevelopmentService = SpringContextUtils.getBean(MdDevelopmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
    }


    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.DEVELOPMENT_LAND_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        LinkedHashSet<KeyValueDto> dtoLinkedHashSet = new LinkedHashSet<>(10);
        //获取待替换文本的集合
        List<String> regexS = generateCommonMethod.specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                dtoLinkedHashSet.add(new KeyValueDto(bookmarkCollection.get(i).getName(), bookmarkCollection.get(i).getName()));
            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
                dtoLinkedHashSet.add(new KeyValueDto(name, name));
            }
        }
        HashMap<String, String> textMap = Maps.newHashMap();
        HashMap<String, String> fileMap = Maps.newHashMap();
        HashMap<String, String> bookmarkMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(dtoLinkedHashSet)) {
            for (KeyValueDto keyValueDto : dtoLinkedHashSet) {
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_Land_SetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getLand_SetUse(BaseReportFieldEnum.Development_Land_SetUse));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_SetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getLand_SetUse(BaseReportFieldEnum.Development_SetUse));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_Price.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_Price));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_SalesTaxAndAdditional.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_SalesTaxAndAdditional));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_assessPrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_assessPrice));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_investmentProfit.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_investmentProfit));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_investmentProfitTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_investmentProfitTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_interestInvestment.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_interestInvestment));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_interestInvestmentTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_interestInvestmentTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionSubtotal.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_constructionSubtotal));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionSubtotal3.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_constructionSubtotal));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionSubtotal2.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_constructionSubtotal2));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_reconnaissanceDesign.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_reconnaissanceDesign));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionInstallationEngineeringFee.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_constructionInstallationEngineeringFee));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_infrastructureCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_infrastructureCost));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_infrastructureMatchingCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_infrastructureMatchingCost));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_devDuring.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_devDuring));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_otherEngineeringCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_otherEngineeringCost));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_unforeseenExpensesTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_unforeseenExpensesTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_unforeseenExpenses.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_unforeseenExpenses));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_deedCorrecting.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_deedCorrecting));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_transactionCostCorrecting.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_transactionCostCorrecting));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_LandAcquisitionCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_LandAcquisitionCost));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_LandGetCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_LandAcquisitionCost));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_LandAcquisitionCostTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_LandAcquisitionCostTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_salesFeeTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_salesFeeTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_salesFee.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_salesFee));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_projectConstructionPeriod.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_projectConstructionPeriod));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_total_area.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_total_area));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_managementExpense.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_managementExpense));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_managementExpenseTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_managementExpenseTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_DevelopmentDegreeCorrectionValue.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_DevelopmentDegreeCorrectionValue));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_OtherAmendments.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_OtherAmendments));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_OtherAmendmentsRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_OtherAmendmentsRemark));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_AmendmentStatusRights.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_AmendmentStatusRights));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_AmendmentStatusRightsRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_AmendmentStatusRightsRemark));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_landIncrementTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getMdDevelopmentCommonValue(BaseReportFieldEnum.Development_landIncrementTax));
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionSubtotal_ComputationalProcess.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getDevelopment_constructionSubtotal_ComputationalProcess());
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_region.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getDevelopment_region());
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_EconomicIndicators.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getDevelopment_EconomicIndicators());
                }
                if (com.google.common.base.Objects.equal(keyValueDto.getKey(), BaseReportFieldEnum.Development_constructionSubtotalContent.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, keyValueDto.getKey(), getDevelopment_constructionSubtotalContent());
                }
            }
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
        return localPath;
    }

    private String getDevelopment_region(){
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (schemeAreaGroup != null){
            if (StringUtils.isNotBlank(schemeAreaGroup.getAreaName())){
                return schemeAreaGroup.getAreaName() ;
            }
        }
        return errorStr;
    }

    private String getMdDevelopmentCommonValue(BaseReportFieldEnum fieldEnum) {
        MdDevelopmentVo vo = getMdDevelopmentVo();
        MdDevelopmentVo format = mdDevelopmentService.getMdDevelopmentVo(vo, true);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(vo.getContent());
        } catch (Exception e) {
            jsonObject = new JSONObject();
        }
        switch (fieldEnum) {
            case Development_Price: {
                if (vo.getPrice() != null) {
                    return vo.getPrice().toString();
                }
            }
            break;
            case Development_assessPrice: {
                if (vo.getAssessPrice() != null) {
                    return vo.getAssessPrice().toString();
                }
            }
            break;
            case Development_investmentProfit: {
                if (vo.getInvestmentProfit() != null) {
                    return vo.getInvestmentProfit().toString();
                }
            }
            break;
            case Development_investmentProfitTax: {
                if (vo.getInvestmentProfitTax() != null) {
                    return vo.getInvestmentProfitTax().toString();
                }
            }
            break;
            case Development_interestInvestment: {
                if (vo.getInterestInvestment() != null) {
                    return vo.getInterestInvestment().toString();
                }
            }
            break;
            case Development_interestInvestmentTax: {
                if (vo.getInterestInvestmentTax() != null) {
                    return vo.getInterestInvestmentTax().toString();
                }
            }
            break;
            case Development_DevelopmentDegreeCorrectionValue: {
                if (vo.getDevelopmentDegreeRevision() != null) {
                    return vo.getDevelopmentDegreeRevision().toString();
                }
            }
            break;
            case Development_AmendmentStatusRights: {
                if (vo.getAmendmentStatusRights() != null) {
                    return vo.getAmendmentStatusRights().toString();
                }
            }
            break;
            case Development_AmendmentStatusRightsRemark: {
                if (StringUtils.isNotBlank(vo.getAmendmentStatusRightsExplain())) {
                    return vo.getAmendmentStatusRightsExplain();
                }
            }
            break;
            case Development_OtherAmendments: {
                if (vo.getOtherAmendments() != null) {
                    return vo.getOtherAmendments().toString();
                }
            }
            break;
            case Development_OtherAmendmentsRemark: {
                if (StringUtils.isNotBlank(vo.getOtherAmendmentsExplain())) {
                    return vo.getOtherAmendmentsExplain();
                }
            }
            break;
            case Development_constructionSubtotal: {
                if (vo.getConstructionCostSubtotal() != null) {
                    return vo.getConstructionCostSubtotal().toString();
                }
            }
            break;
            case Development_constructionSubtotal2: {
                if (vo.getConstructionCostSubtotal() != null) {
                    return vo.getConstructionCostSubtotal().toString();
                }
            }
            break;
            case Development_reconnaissanceDesign: {
                if (vo.getReconnaissanceDesign() != null) {
                    return vo.getReconnaissanceDesign().toString();
                }
            }
            break;
            case Development_constructionInstallationEngineeringFee: {
                if (vo.getConstructionInstallationEngineeringFee() != null) {
                    return vo.getConstructionInstallationEngineeringFee().toString();
                }
            }
            break;
            case Development_infrastructureCost: {
                if (vo.getInterestInvestment() != null) {
                    return vo.getInterestInvestment().toString();
                }
            }
            break;
            case Development_infrastructureMatchingCost: {
                if (vo.getInfrastructureMatchingCost() != null) {
                    return vo.getInfrastructureMatchingCost().toString();
                }
            }
            break;
            case Development_devDuring: {
                if (vo.getDevDuring() != null) {
                    return vo.getDevDuring().toString();
                }
            }
            break;
            case Development_otherEngineeringCost: {
                if (vo.getOtherEngineeringCost() != null) {
                    return vo.getOtherEngineeringCost().toString();
                }
            }
            break;
            case Development_unforeseenExpensesTax: {
                if (vo.getUnforeseenExpenses() != null) {
                    return vo.getUnforeseenExpenses().toString();
                }
            }
            break;
            case Development_managementExpenseTax: {
                if (vo.getManagementExpense() != null) {
                    return vo.getManagementExpense().toString();
                }
            }
            break;
            case Development_landIncrementTax: {
                if (vo.getLandValueAddedTax() != null) {
                    return vo.getLandValueAddedTax().toString();
                }
            }
            break;
            case Development_managementExpense: {
                String key = "d32";
                if (jsonObject.get(key) != null) {
                    return (String) jsonObject.get(key);
                }
            }
            break;
            case Development_unforeseenExpenses: {
                String key = "d27";
                if (jsonObject.get(key) != null) {
                    return (String) jsonObject.get(key);
                }
            }
            break;
            case Development_deedCorrecting: {
                if (vo.getDeedTaxRate() != null) {
                    return vo.getDeedTaxRate().toString();
                }
            }
            break;
            case Development_transactionCostCorrecting: {
                if (vo.getTransactionTaxRate() != null) {
                    return vo.getTransactionTaxRate().toString();
                }
            }
            break;
            case Development_LandAcquisitionCost: {
                if (vo.getLandGetRelevant() != null) {
                    return vo.getLandGetRelevant().toString();
                }
            }
            break;
            case Development_LandAcquisitionCostTax: {
                String key = "d28";
                if (jsonObject.get(key) != null) {
                    return (String) jsonObject.get(key);
                }
            }
            break;
            case Development_total_area: {
                String key = "f18";
                if (jsonObject.get(key) != null) {
                    return (String) jsonObject.get(key);
                }
            }
            break;
            case Development_salesFeeTax: {
                if (vo.getSalesFee() != null) {
                    return vo.getSalesFee().toString();
                }
            }
            break;
            case Development_salesFee: {
                String key = "f33";
                if (jsonObject.get(key) != null) {
                    return (String) jsonObject.get(key);
                }
            }
            break;
            case Development_projectConstructionPeriod: {
                if (vo.getProjectConstructionPeriod() != null) {
                    return vo.getProjectConstructionPeriod().toString();
                }
            }
            break;
            case Development_SalesTaxAndAdditional:
                if (vo.getSalesTaxAndAdditional() != null) {
                    return vo.getSalesTaxAndAdditional().toString();
                }
                return errorStr;
            default: {
            }
            break;
        }
        return errorStr;
    }

    private String getDevelopment_constructionSubtotal_ComputationalProcess() {
        //=勘察设计和前期工程费 + 建筑安装工程费 + 基础设施费用 + 公共配套设施建设费 + 开发期间税费 + 其它工程费
        StringBuilder stringBuilder = new StringBuilder(8);
        MdDevelopmentVo vo = getMdDevelopmentVo();
        MdDevelopmentVo format = mdDevelopmentService.getMdDevelopmentVo(vo, true);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(vo.getContent());
            stringBuilder.append((String) jsonObject.get("d20")).append(" + ");
            stringBuilder.append((String) jsonObject.get("d21")).append(" + ");
            stringBuilder.append((String) jsonObject.get("d22")).append(" + ");
            stringBuilder.append((String) jsonObject.get("d23")).append(" + ");
            stringBuilder.append((String) jsonObject.get("d24")).append(" + ");
            stringBuilder.append((String) jsonObject.get("d25")).append(" + ");
        } catch (Exception e) {
        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return errorStr;
    }

    private String getLand_SetUse(BaseReportFieldEnum fieldEnum) {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        switch (fieldEnum) {
            case Development_Land_SetUse: {
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null && StringUtils.isNotBlank(dataSetUseField.getName())) {
                        return dataSetUseField.getName();
                    }
                }
            }
            case Development_SetUse: {
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null && StringUtils.isNotBlank(dataSetUseField.getName())) {
                        return dataSetUseField.getName();
                    }
                }
            }
            break;
            default: {
            }
            break;
        }
        return errorStr;
    }

    private SchemeInfo getSchemeInfo() {
        return schemeInfo;
    }

    private MdDevelopmentVo getMdDevelopmentVo() {
        if (this.mdDevelopmentVo == null) {
            MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            if (mdDevelopment != null) {
                this.mdDevelopmentVo = mdDevelopmentService.getMdDevelopmentVo(mdDevelopment, false);
            } else {
                this.mdDevelopmentVo = new MdDevelopmentVo();
            }
        }
        return this.mdDevelopmentVo;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            this.schemeAreaGroup = schemeAreaGroupService.get(areaId);
        }
        return this.schemeAreaGroup;
    }

    private SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject == null) {
            if (getSchemeInfo().getJudgeObjectId() != null) {
                schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(getSchemeInfo().getJudgeObjectId());
            }
            if (schemeJudgeObject == null) {
                schemeJudgeObject = new SchemeJudgeObject();
            }
        }
        return schemeJudgeObject;
    }

    private String getDevelopment_constructionSubtotalContent(){
        return errorStr;
    }


    /**
     * 经济指标
     * @return
     * @throws Exception
     */
    private String getDevelopment_EconomicIndicators()throws Exception{
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath =  generateCommonMethod.getLocalPath();

        doc.save(localPath) ;
        return localPath;
    }
}
