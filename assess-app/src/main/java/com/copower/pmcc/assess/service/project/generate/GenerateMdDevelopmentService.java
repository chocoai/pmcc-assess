package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    private MdArchitecturalObjService mdArchitecturalObjService;
    private ProjectPlanDetailsService projectPlanDetailsService;

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
        this.mdArchitecturalObjService = SpringContextUtils.getBean(MdArchitecturalObjService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
    }


    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.DEVELOPMENT_LAND_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<BaseReportFieldEnum, String> map = Maps.newHashMap();
        final ConcurrentHashMap<String, String> textMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> bookmarkMap = new ConcurrentHashMap<String, String>(0);
        final ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsById(schemeInfo.getPlanDetailsId());
        Map<String, BaseReportFieldEnum> reportFieldEnumMap = new HashMap<String, BaseReportFieldEnum>(0);
        for (BaseReportFieldEnum reportFieldEnum : BaseReportFieldEnum.values()) {
            reportFieldEnumMap.put(reportFieldEnum.getName(), reportFieldEnum);
        }
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
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (!map.isEmpty()) {
            //发起线程组
            for (Map.Entry<BaseReportFieldEnum, String> enumStringEntry : map.entrySet()) {
                try {
                    setFieldObjectValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, getMdDevelopmentVo());
                    setMdDevelopmentCommonValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, getMdDevelopmentVo(), schemeJudgeObject, schemeAreaGroup, projectPlanDetails);
                } catch (Exception e) {
                    String error = e.getMessage();
                    throw e;
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

    private void setMdDevelopmentCommonValue(BaseReportFieldEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdDevelopment target, SchemeJudgeObject schemeJudgeObject, SchemeAreaGroup schemeAreaGroup, ProjectPlanDetails projectPlanDetails) {
        switch (key) {
            case Development_region: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), schemeAreaGroup.getAreaName());
                break;
            }
            case Development_Land_SetUse: {
                if (schemeJudgeObject.getSetUse() == null) {
                    break;
                }
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField == null || StringUtils.isEmpty(dataSetUseField.getName())) {
                    break;
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), dataSetUseField.getName());
                break;
            }
            case Development_SetUse: {
                if (schemeJudgeObject.getSetUse() == null) {
                    break;
                }
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField == null || StringUtils.isEmpty(dataSetUseField.getName())) {
                    break;
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), dataSetUseField.getName());
                break;
            }
            case Development_EconomicIndicators: {//经济指标
                try {
                    Document doc = new Document();
                    DocumentBuilder builder = new DocumentBuilder(doc);
                    generateCommonMethod.settingBuildingTable(builder);
                    String localPath = generateCommonMethod.getLocalPath();
                    doc.save(localPath);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), localPath);
                } catch (Exception e) {
                }
                break;
            }
            case Development_constructionSubtotal_ComputationalProcess: {
                LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap = Maps.newLinkedHashMap();
                mdArchitecturalObjService.setMdCalculatingMethodEngineeringCostMapData(costJSONObjectMap, projectPlanDetails, projectPlanDetails.getProjectId());
                if (costJSONObjectMap.isEmpty()) {
                    break;
                }
                String path = generateCommonMethod.getLocalPath(RandomStringUtils.randomAlphanumeric(4));
                LinkedList<String> linkedList = Lists.newLinkedList();
                Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                try {
                    Document document = new Document();
                    DocumentBuilder documentBuilder = new DocumentBuilder(document);
                    //设置具体宽度自动适应
                    PreferredWidth preferredWidth = PreferredWidth.AUTO;
                    documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    documentBuilder.getCellFormat().setPreferredWidth(preferredWidth);
                    documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setTopPadding(0);
                    documentBuilder.getCellFormat().setBottomPadding(0);
                    documentBuilder.getCellFormat().setLeftPadding(0);
                    documentBuilder.getCellFormat().setRightPadding(0);
                    documentBuilder.getFont().setSize(10.5);
                    documentBuilder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);

                    final AtomicInteger atomicInteger = new AtomicInteger(0);
                    Table table = documentBuilder.startTable();
                    mdArchitecturalObjService.writeCalculatingMethodEngineeringCostSheet(documentBuilder, linkedList, costJSONObjectMap, mergeCellModelList, atomicInteger);
                    if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
                    }
                    documentBuilder.endTable();
                    AsposeUtils.saveWord(path, document);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), path);
                } catch (Exception e) {
                }

                break;
            }
        }
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


    private synchronized void setFieldObjectValue(BaseReportFieldEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdDevelopment target) {
        String value = mdDevelopmentService.getFieldObjectValue(key, target);
        switch (key) {
            case Development_SalesTaxAndAdditional: {
                value = ArithmeticUtils.getBigDecimalString(target.getSalesTaxAndAdditional());
                break;
            }
            case Development_reconnaissanceDesignRate:
                value = ArithmeticUtils.getBigDecimalString(target.getReconnaissanceDesign());
                break;
            case Development_unforeseenExpensesTax:
                value = ArithmeticUtils.getBigDecimalString(target.getUnforeseenExpenses());
                break;
            case Development_deedCorrecting:
                value = ArithmeticUtils.getBigDecimalString(target.getDeedTaxRate());
                break;
            case Development_transactionCostCorrecting:
                value = ArithmeticUtils.getBigDecimalString(target.getTransactionTaxRate());
                break;
            case Development_LandAcquisitionCost: {
                value = mdDevelopmentService.getFieldObjectValue(BaseReportFieldEnum.Development_LandGetCost, target);
                break;
            }
            case Development_LandAcquisitionCostTax: {
                value = ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
                break;
            }
            case Development_managementExpenseRate: {
                value = ArithmeticUtils.getBigDecimalString(target.getManagementExpense());
                break;
            }
            case Development_constructionSubtotal2: {
                value = mdDevelopmentService.getFieldObjectValue(BaseReportFieldEnum.Development_constructionSubtotal, target);
                break;
            }
            case Development_constructionSubtotal3: {
                value = mdDevelopmentService.getFieldObjectValue(BaseReportFieldEnum.Development_constructionSubtotal, target);
                break;
            }
            case Development_salesFeeTax: {
                value = ArithmeticUtils.getBigDecimalString(target.getSalesFee());
                break;
            }
            case Development_interestInvestmentRate: {
                value = ArithmeticUtils.getBigDecimalString(target.getInterestInvestmentTax());
                break;
            }
            case Development_investmentProfitRate: {
                value = ArithmeticUtils.getBigDecimalString(target.getInvestmentProfitTax());
                break;
            }
            case Development_landIncrementTax: {
                value = ArithmeticUtils.getBigDecimalString(target.getLandValueAddedTax());
                break;
            }
            case Development_AmendmentStatusRights: {
                value = ArithmeticUtils.getBigDecimalString(target.getAmendmentStatusRights());
                break;
            }
            case Development_OtherAmendments: {
                value = ArithmeticUtils.getBigDecimalString(target.getOtherAmendments());
                break;
            }
            case Development_DevelopmentDegreeCorrectionValue: {
                value = ArithmeticUtils.getBigDecimalString(target.getDevelopmentDegreeRevision());
                break;
            }
            case Development_AmendmentStatusRightsRemark: {
                value = target.getAmendmentStatusRightsExplain();
                break;
            }
            case Development_OtherAmendmentsRemark: {
                value = target.getOtherAmendmentsExplain();
                break;
            }
        }
        if (StringUtils.isNotBlank(value)) {
            if (NumberUtils.isNumber(value)) {
                int length = ArithmeticUtils.getDecimalLength(value);
                if (length > 4) {
                    value = ArithmeticUtils.round(value, 2);
                }
            }
        }
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (com.google.common.base.Objects.equal("0", value)) {
            return;
        }
        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), value);
    }

    private ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsService.getProjectPlanDetailsById(id);
    }


}
