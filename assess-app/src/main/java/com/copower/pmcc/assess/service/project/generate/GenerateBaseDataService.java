package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CnNumberUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.compile.CompileReportService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.*;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.Reflections;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected final String errorStr = "无";
    //spring bean
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private ProjectNumberRecordService projectNumberRecordService;
    private BaseDataDicService baseDataDicService;
    private BaseAttachmentService baseAttachmentService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private DataSetUseFieldService dataSetUseFieldService;
    private ProjectPhaseService projectPhaseService;
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    private DeclareRecordService declareRecordService;
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    private SchemeSurePriceService schemeSurePriceService;
    private SchemeReimbursementService schemeReimbursementService;
    private com.copower.pmcc.assess.service.AdRpcQualificationsAppService adRpcQualificationsService;
    private PublicService publicService;
    private SchemeSupportInfoService schemeSupportInfoService;
    private CompileReportService compileReportService;
    private SchemeReportFileService schemeReportFileService;
    private DataQualificationService dataQualificationService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private SchemeInfoService schemeInfoService;
    private EvaluationMethodService evaluationMethodService;
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    private MdIncomeService mdIncomeService;
    private MdMarketCompareService mdMarketCompareService;
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    private DataHisRightInfoPublicityService dataHisRightInfoPublicityService;
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    private DataMethodFormulaService dataMethodFormulaService;
    private GenerateCommonMethod generateCommonMethod;
    /**
     * 构造器必须传入的参数
     */
    private Integer projectId;
    private Integer areaId;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo = null;
    /**
     * 中间变量
     */
    private SchemeAreaGroup schemeAreaGroup = null;
    private List<SchemeJudgeObject> schemeJudgeObjectList = null;

    //===========================================获取的值===============================

    //报告出具日期
    public Date getReportIssuanceDate() {
        return new Date();
    }

    /**
     * 获取文号
     *
     * @return
     */
    public String getWordNumber() {
        try {
            String number = projectNumberRecordService.getReportNumber(projectId, areaId, this.baseReportTemplate.getReportType());
            if (StringUtils.isNotBlank(number)) {
                return number;
            }
        } catch (BusinessException e) {
            logger.error("获取文号异常", e);
        }
        return errorStr;
    }


    /**
     * 委托人
     *
     * @return
     */
    public String getPrincipal() {
        String principalStr = StringUtils.isNotBlank(projectInfo.getConsignorVo().getCsName()) ? projectInfo.getConsignorVo().getCsName() : projectInfo.getConsignorVo().getCsEntrustmentUnit();
        if (StringUtils.isNotBlank(principalStr)) {
            return principalStr;
        } else {
            return errorStr;
        }
    }

    /**
     * 估价委托人信息
     *
     * @return
     */
    public String getPrincipalInfo() {
        StringBuilder stringBuilder = new StringBuilder(16);
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.legalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsEntrustmentUnitName();
            if (StringUtils.isEmpty(name)) {
                name = "无";
            }
            stringBuilder.append("名称:").append(name).append(";");
            String code = projectInfo.getConsignorVo().getCsSociologyCode();
            if (StringUtils.isEmpty(code)) {
                code = "无";
            }
            stringBuilder.append("统一社会信用代码:").append(code).append(";");
            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isEmpty(address)) {
                address = "无";
            }
            stringBuilder.append("住所:").append(address).append("");
            String people = projectInfo.getConsignorVo().getCsLegalRepresentative();
            if (StringUtils.isEmpty(people)) {
                people = "无";
            }
            stringBuilder.append("法定代表人:").append(people).append("");
        }
        if (Objects.equal(projectInfo.getConsignorVo().getCsType(), InitiateContactsEnum.naturalPerson.getId())) {
            String name = projectInfo.getConsignorVo().getCsName();
            if (StringUtils.isEmpty(name)) {
                name = "无";
            }
            stringBuilder.append("姓名:").append(name).append(";");
            String idCard = projectInfo.getConsignorVo().getCsIdcard();
            if (StringUtils.isEmpty(idCard)) {
                idCard = "无";
            }
            stringBuilder.append("身份证号:").append(idCard).append(";");
            String address = projectInfo.getConsignorVo().getCsAddress();
            if (StringUtils.isEmpty(address)) {
                address = "无";
            }
            stringBuilder.append("地址:").append(address).append("");
        }
        return stringBuilder.toString();
    }

    /**
     * 财产范围说明
     *
     * @return
     * @throws Exception
     */
    public String getScopePropertyExplain() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicTrading().getScopePropertyExplain())) {
                                    String temp = generateBaseExamineService.getBasicTrading().getScopePropertyExplain();
                                    if (StringUtils.isNotBlank(temp)) {
                                        List<Integer> integerList = stringListMap.get(temp);
                                        if (CollectionUtils.isNotEmpty(integerList)) {
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        } else {
                                            integerList = Lists.newArrayList();
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        }
                                        stringListMap.put(temp, integerList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 建筑面积及评估面积
     *
     * @return
     */
    public String getBuildingAndAssessArea() {
        Set<BigDecimal> bigDecimalSet = Sets.newHashSet();
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getEvaluationArea() != null).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                bigDecimalSet.add(schemeJudgeObjectList.get(i).getEvaluationArea());
            }
        }
        Map<BigDecimal, List<Integer>> bigDecimalListHashMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(bigDecimalSet)) {
            for (BigDecimal s : bigDecimalSet) {
                bigDecimalListHashMap.put(s, new ArrayList<Integer>(0));
            }
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                BigDecimal key = null;
                for (Map.Entry<BigDecimal, List<Integer>> entry : bigDecimalListHashMap.entrySet()) {
                    if (Objects.equal(entry.getKey(), schemeJudgeObject.getEvaluationArea())) {
                        key = entry.getKey();
                        break;
                    }
                }
                if (key != null) {
                    //取出列表
                    List<Integer> integerList = bigDecimalListHashMap.get(key);
                    //添加进相同证载用途的列表中
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    //更新回去
                    bigDecimalListHashMap.put(key, integerList);
                }
            }
        }
        if (!bigDecimalListHashMap.isEmpty()) {
            for (Map.Entry<BigDecimal, List<Integer>> entry : bigDecimalListHashMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey().toString()).append("㎡");
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 座落
     *
     * @return
     * @throws Exception
     */
    public String getSeat() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        StringBuilder builder = new StringBuilder(16);
        Set<String> streetNumber = Sets.newHashSet();
        Set<String> attachedNumber = Sets.newHashSet();
        Set<String> buildingNumber = Sets.newHashSet();
        Set<String> unit = Sets.newHashSet();
        Set<String> floor = Sets.newHashSet();
        Set<String> estateName = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList1 = generateCommonMethod.unionSchemeJudgeObject(schemeJudgeObjectList);
        //合并描述情况
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList1)) {
            List<Integer> roomNumber = Lists.newArrayList();
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList1) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null) {
                    if (NumberUtils.isNumber(declareRecord.getRoomNumber())) {
                        roomNumber.add(NumberUtils.toInt(declareRecord.getRoomNumber()));
                    }
                    streetNumber.add(declareRecord.getStreetNumber());
                    attachedNumber.add(declareRecord.getAttachedNumber());
                    buildingNumber.add(declareRecord.getBuildingNumber());
                    unit.add(declareRecord.getUnit());
                    floor.add(declareRecord.getFloor());
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhases.get(0).getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetailsList.get(0).getId());
                        if (generateBaseExamineService.getEstate().getId() != null) {
                            estateName.add(generateBaseExamineService.getEstate().getName());
                        }
                    }
                }
            }
            builder.append(generateCommonMethod.toSetString2(streetNumber)).append("号");
            builder.append(generateCommonMethod.toSetString2(estateName));
            builder.append("附").append(generateCommonMethod.toSetString2(attachedNumber)).append("号")
                    .append(generateCommonMethod.toSetString2(buildingNumber)).append("栋")
                    .append(generateCommonMethod.toSetString2(unit)).append("单元")
                    .append(generateCommonMethod.toSetString2(floor)).append("层");
            builder.append(generateCommonMethod.convertNumber(roomNumber)).append("号");
            stringSet.add(builder.toString());
            builder.delete(0, builder.toString().length());
        }
        Collection<SchemeJudgeObject> schemeJudgeObjectList2 = CollectionUtils.subtract(schemeJudgeObjectList, schemeJudgeObjectList1);
        //单独描述情况
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList2)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList2) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                builder.append(declareRecord.getStreetNumber()).append("号");
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                query.setProjectPhaseId(projectPhases.get(0).getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetailsList.get(0).getId());
                    if (generateBaseExamineService.getEstate().getId() != null) {
                        builder.append(generateBaseExamineService.getEstate().getName());
                    }
                }
                builder.append("附").append(declareRecord.getAttachedNumber()).append("号")
                        .append(declareRecord.getBuildingNumber()).append("栋")
                        .append(declareRecord.getUnit()).append("单元")
                        .append(declareRecord.getFloor()).append("层")
                        .append(declareRecord.getRoomNumber()).append("号");
                if (StringUtils.isNotBlank(builder.toString())) {
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString2(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 外聘专家工作概况
     *
     * @return
     */
    public String getExpertWorkOverview() {
        String s = "茅以升1916年毕业于西南交通大学（时称交通部唐山工业专门学校英文名称Tangshan Engineering College），1917年获美国康乃尔大学硕士学位，1919年获美国卡耐基理工学院（先卡耐基梅隆大学）博士学位 [1]  ，回国后历任交通大学唐山工学院教授、国立东南大学（1928年更名为国立中央大学，1949年更名为南京大学）教授、工科主任、国立河海工科大学校长、交通部唐山大学校长（今西南交通大学）、北洋工学院院长、江苏省水利厅厅长、钱塘江大桥工程处处长、交通大学唐山工学院代院长、院长、中国桥梁公司总经理、北洋大学校长、中国/北方交通大学（时含今西南交通大学和今北京交通大学）校长、铁道科学研究院院长等职。1955年选聘为中国科学院院士（学部委员）。";
        return errorStr;
    }

    /**
     * 共有权情况
     *
     * @return
     * @throws Exception
     */
    public String getCo_ownership() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getPublicSituation())) {
                    String temp = declareRecord.getPublicSituation();
                    if (StringUtils.isNotBlank(temp)) {
                        List<Integer> integerList = stringListMap.get(temp);
                        if (CollectionUtils.isNotEmpty(integerList)) {
                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                        } else {
                            integerList = Lists.newArrayList();
                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                        }
                        stringListMap.put(temp, integerList);
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 替代原则
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 11:06
     */
    public String getSubstitutionPrinciple() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = schemeSupportInfoService.getContentByName(areaId, BaseReportFieldEnum.SubstitutionPrinciple.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 未定事项假设
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 14:46
     */
    public String getUncertaintyAssumption() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = schemeSupportInfoService.getContentByName(areaId, BaseReportFieldEnum.UncertaintyAssumption.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 背离事实假设
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 14:47
     */
    public String getDeviationFromFactualAssumptions() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = schemeSupportInfoService.getContentByName(areaId, BaseReportFieldEnum.DeviationFromFactualAssumptions.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 不相一致假设
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 14:48
     */
    public String getInconsistentHypothesis() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = schemeSupportInfoService.getContentByName(areaId, BaseReportFieldEnum.InconsistentHypothesis.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 依据不足假设
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 14:51
     */
    public String getInsufficientHypothesis() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = schemeSupportInfoService.getContentByName(areaId, BaseReportFieldEnum.InsufficientHypothesis.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析通用性
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 14:58
     */
    public String getUniversalityLiquidityAnalysis() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.UniversalityLiquidityAnalysis.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析独立使用性
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getIndependentUsabilityCashFlowAnalysis() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.IndependentUsabilityCashFlowAnalysis.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析可分割转让性
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getLiquidityAnalysisSeparableTransferability() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.LiquidityAnalysisSeparableTransferability.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析房地产区位
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCashAnalysisRealEstateLocation() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.CashAnalysisRealEstateLocation.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析房地产开发程度
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCashAnalysisDegreeRealEstateDevelopment() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.CashAnalysisDegreeRealEstateDevelopment.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 功能描述: 变现分析房地产价值大小
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCashAnalysisValueRealEstate() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.CashAnalysisValueRealEstate.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析房地产市场状况
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCashAnalysisRealEstateMarket() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.CashAnalysisRealEstateMarket.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现分析其他
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCashAnalysisOthers() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.CashAnalysisOthers.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现拍卖底价规定
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getBasePriceRegulationCashAuction() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.BasePriceRegulationCashAuction.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现时间长短
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getHowLongLiquidationTime() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.HowLongLiquidationTime.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现综合分析
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getCash_inComprehensiveAnalysis() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.Cash_inComprehensiveAnalysis.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险相邻物业抵押影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getTheImpactMortgageRiskyNeighbouringProperty() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.TheImpactMortgageRiskyNeighbouringProperty.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险租赁影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getImpactRiskLeasingy() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ImpactRiskLeasing.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险整体变现影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getOverallLiquidityImpactRisk() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.OverallLiquidityImpactRisk.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险不可抗力影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getRiskForceMajeureEffect() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.RiskForceMajeureEffect.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险政策变化影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getImpactRiskPolicyChange() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ImpactRiskPolicyChange.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险抵押物特殊事件影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getImpactSpecialEventsRiskMortgage() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ImpactSpecialEventsRiskMortgage.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险抵押物使用不当
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getImproperUseRiskCollaterale() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ImproperUseRiskCollateral.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险抵押物功能及替代物
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getFunctionsSubstitutesRiskMortgage() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.FunctionsSubstitutesRiskMortgage.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险抵押物环境影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getEnvironmentalImpactRiskMortgage() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.EnvironmentalImpactRiskMortgage.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险抵押期间因抵押物原因而引起的房地产信贷风险关注点
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ConcernRealEstateCreditRiskCausedMortgageReasonsDuringRiskMortgagePeriod.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 风险区域规划、城市发展战略、城市规划、土地利用对房地产的影响
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:00
     */
    public String getThreeMajorRiskPlanning() throws Exception {
        //存在换行符必须使用模板
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String content = compileReportService.getContentByName(areaId, BaseReportFieldEnum.ThreeMajorRiskPlanning.getName());
        String localPath = getLocalPath();
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 变现比率
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:17
     */
    public String getLiquidRatios() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String temp = schemeJudgeObject.getLiquidRatio();
                if (StringUtils.isNotBlank(temp)) {
                    List<Integer> integerList = stringListMap.get(temp);
                    if (CollectionUtils.isNotEmpty(integerList)) {
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    } else {
                        integerList = Lists.newArrayList();
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    }
                    stringListMap.put(temp, integerList);
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s)) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 出具报告城市
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 15:17
     */
    public String getReportCity() throws Exception {
        String reportCity = getSchemeAreaGroup().getAreaName();
        if (StringUtils.isNotBlank(reportCity)) {
            return reportCity;
        } else {
            return errorStr;
        }
    }

    /**
     * 抵押价值总金额
     *
     * @return
     */
    public String getTotalAmountMortgageValue() {
        /**
         * 慢揾英雄泪，相离处世家 ....
         * 没缘法转眼分离栅
         */
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(projectPlanDetails.getId());
                        if (schemeReimbursement != null) {
                            if (schemeReimbursement.getMortgageTotalPrice() != null) {
                                stringSet.add(schemeReimbursement.getMortgageTotalPrice().toString());
                            }
                        }
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 抵押价值总金额大写
     *
     * @return
     */
    public String getTotalAmountMortgageValueCapitalization() {
        Set<BigDecimal> bigDecimalSet = Sets.newHashSet();
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(projectPlanDetails.getId());
                        if (schemeReimbursement != null) {
                            if (schemeReimbursement.getMortgageTotalPrice() != null) {
                                bigDecimalSet.add(schemeReimbursement.getMortgageTotalPrice());
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(bigDecimalSet)) {
            List<BigDecimal> bigDecimalList = Lists.newArrayList(bigDecimalSet);
            for (int i = 0; i < bigDecimalList.size(); i++) {
                stringSet.add(CnNumberUtils.toUppercaseSubstring(bigDecimalList.get(i).toString()));
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 估价项目名称
     *
     * @return
     */
    public String getValuationProjectName() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        StringBuilder builder = new StringBuilder(16);
        Set<String> streetNumber = Sets.newHashSet();
        Set<String> attachedNumber = Sets.newHashSet();
        Set<String> buildingNumber = Sets.newHashSet();
        Set<String> unit = Sets.newHashSet();
        Set<String> floor = Sets.newHashSet();
        Set<String> estateName = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList1 = generateCommonMethod.unionSchemeJudgeObject(schemeJudgeObjectList);
        //合并描述情况
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList1)) {
            List<Integer> roomNumber = Lists.newArrayList();
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList1) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null) {
                    if (NumberUtils.isNumber(declareRecord.getRoomNumber())) {
                        roomNumber.add(NumberUtils.toInt(declareRecord.getRoomNumber()));
                    }
                    streetNumber.add(declareRecord.getStreetNumber());
                    attachedNumber.add(declareRecord.getAttachedNumber());
                    buildingNumber.add(declareRecord.getBuildingNumber());
                    unit.add(declareRecord.getUnit());
                    floor.add(declareRecord.getFloor());
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhases.get(0).getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetailsList.get(0).getId());
                        if (generateBaseExamineService.getEstate().getId() != null) {
                            estateName.add(generateBaseExamineService.getEstate().getName());
                        }
                    }
                }
            }
            builder.append(generateCommonMethod.toSetString2(streetNumber)).append("号");
            builder.append(generateCommonMethod.toSetString2(estateName));
            builder.append("附").append(generateCommonMethod.toSetString2(attachedNumber)).append("号")
                    .append(generateCommonMethod.toSetString2(buildingNumber)).append("栋")
                    .append(generateCommonMethod.toSetString2(unit)).append("单元")
                    .append(generateCommonMethod.toSetString2(floor)).append("层");
            builder.append(generateCommonMethod.convertNumber(roomNumber)).append("号");
            if (schemeJudgeObjectList1.get(0).getSetUse() != null) {
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObjectList1.get(0).getSetUse());
                if (dataSetUseField != null) {
                    builder.append(dataSetUseField.getName()).append(";");
                }
            }
            stringSet.add(builder.toString());
            builder.delete(0, builder.toString().length());
        }
        Collection<SchemeJudgeObject> schemeJudgeObjectList2 = CollectionUtils.subtract(schemeJudgeObjectList, schemeJudgeObjectList1);
        //单独描述情况
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList2)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList2) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                builder.append(declareRecord.getStreetNumber()).append("号");
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                query.setProjectPhaseId(projectPhases.get(0).getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetailsList.get(0).getId());
                    if (generateBaseExamineService.getEstate().getId() != null) {
                        builder.append(generateBaseExamineService.getEstate().getName());
                    }
                }
                builder.append("附").append(declareRecord.getAttachedNumber()).append("号")
                        .append(declareRecord.getBuildingNumber()).append("栋")
                        .append(declareRecord.getUnit()).append("单元")
                        .append(declareRecord.getFloor()).append("层")
                        .append(declareRecord.getRoomNumber()).append("号");
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null) {
                        builder.append(dataSetUseField.getName()).append(";");
                    }
                }
                if (StringUtils.isNotBlank(builder.toString())) {
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = " ";
        if (getSchemeAreaGroup().getEntrustPurpose() != null) {
            s = String.format("%s%s", generateCommonMethod.toSetString2(stringSet), baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose()));
        } else {
            s = generateCommonMethod.toSetString2(stringSet);
        }
        return s;
    }


    /**
     * 证载用途 / 证载用途分述
     *
     * @return
     * @throws Exception
     */
    public String getSeparationCertificateUses() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getCertUse())).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                stringSet.add(String.format("%s", schemeJudgeObjectList.get(i).getCertUse()));
            }
        }
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(stringSet)) {
            for (String s : stringSet) {
                stringListMap.put(s, new ArrayList<Integer>(0));
            }
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String key = null;
                for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                    if (Objects.equal(entry.getKey(), schemeJudgeObject.getCertUse())) {
                        key = entry.getKey();
                        break;
                    }
                }
                if (StringUtils.isNotBlank(key)) {
                    //取出列表
                    List<Integer> integerList = stringListMap.get(key);
                    //添加进相同证载用途的列表中
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    //更新回去
                    stringListMap.put(key, integerList);
                }
            }
        }
        stringSet.clear();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 估价对象的总价
     *
     * @return
     */
    public String getTotalValueValuationObject() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObject), bigDecimal.toString()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 分类评估总价
     *
     * @return
     */
    public String getEvaluationPriceCateGoryTotalOne() {
        return this.getTotalValueValuationObject();
    }


    /**
     * 分类评估单价计算试
     *
     * @return
     */
    public String getEvaluationExpression() {
        return "市场比较法价格*权重+收益法价格*权重";
    }

    /**
     * 单价调整表
     *
     * @return
     * @throws Exception
     */
    public String getUnitPriceAdjustmentTable() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        List<SchemeJudgeObjectVo> schemeJudgeObjectList = getSchemeJudgeObjectList().stream().map(schemeJudgeObject -> {
            return schemeJudgeObjectService.getSchemeJudgeObjectVo(schemeJudgeObject);
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Table table = builder.startTable();
            for (int i = 0; i < 5; i++) {
                builder.insertCell();
                if (i == 0) builder.writeln("权证号");
                if (i == 1) builder.writeln("楼层");
                if (i == 2) builder.writeln("房号");
                if (i == 3) builder.writeln("价格");
                if (i == 4) builder.writeln("因素");
            }
            builder.endRow();
            for (SchemeJudgeObjectVo schemeJudgeObject : schemeJudgeObjectList) {
                DeclareRecord declareRecord = null;
                if (schemeJudgeObject.getBisMerge()) {
                    List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                        for (SchemeJudgeObject judgeObject : judgeObjectList) {
                            SchemeJudgeObjectVo judgeObjectVo = schemeJudgeObjectService.getSchemeJudgeObjectVo(judgeObject);
                            if (schemeJudgeObject.getDeclareRecordId() != null) {
                                declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                                if (declareRecord == null) {
                                    declareRecord = new DeclareRecord();
                                }
                            }
                            for (int i = 0; i < 5; i++) {
                                builder.insertCell();
                                if (i == 0) {
                                    if (StringUtils.isNotBlank(declareRecord.getName())) {
                                        builder.writeln(declareRecord.getName());
                                    }
                                }
                                if (i == 1) {
                                    if (StringUtils.isNotBlank(declareRecord.getFloor())) {
                                        builder.writeln(declareRecord.getFloor());
                                    }
                                }
                                if (i == 2) {
                                    if (StringUtils.isNotBlank(declareRecord.getRoomNumber())) {
                                        builder.writeln(declareRecord.getRoomNumber());
                                    }
                                }
                                if (i == 3) {
                                    if (declareRecord.getPrice() != null) {
                                        builder.writeln(declareRecord.getPrice().toString());
                                    }
                                }
                                if (i == 4) {
                                    if (StringUtils.isNotBlank(judgeObjectVo.getCoefficient())) {
                                        builder.writeln(judgeObjectVo.getCoefficient());
                                    }
                                }
                            }
                            builder.endRow();
                        }
                    }
                } else {
                    if (schemeJudgeObject.getDeclareRecordId() != null) {
                        declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                        if (declareRecord == null) {
                            declareRecord = new DeclareRecord();
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        builder.insertCell();
                        if (i == 0) {
                            if (StringUtils.isNotBlank(declareRecord.getName())) {
                                builder.writeln(declareRecord.getName());
                            }
                        }
                        if (i == 1) {
                            if (StringUtils.isNotBlank(declareRecord.getFloor())) {
                                builder.writeln(declareRecord.getFloor());
                            }
                        }
                        if (i == 2) {
                            if (StringUtils.isNotBlank(declareRecord.getRoomNumber())) {
                                builder.writeln(declareRecord.getRoomNumber());
                            }
                        }
                        if (i == 3) {
                            if (declareRecord.getPrice() != null) {
                                builder.writeln(declareRecord.getPrice().toString());
                            }
                        }
                        if (i == 4) {
                            if (StringUtils.isNotBlank(schemeJudgeObject.getCoefficient())) {
                                builder.writeln(schemeJudgeObject.getCoefficient());
                            }
                        }
                    }
                    builder.endRow();
                }
            }
            builder.endTable();
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 分类评估方法结果
     *
     * @return
     */
    public String getEvaluationMethodResult() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                int row = 0;
                builder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObject));
                Table table = builder.startTable();
                for (int i = 0; i < 3; i++) {
                    builder.insertCell();
                    if (i == 0) builder.writeln("方法名称");
                    if (i == 1) builder.writeln("试算价格");
                    if (i == 2) builder.writeln("权重");
                }
                builder.endRow();
                row++;
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                        for (int i = 0; i < 3; i++) {
                            builder.insertCell();
                            if (i == 0) {
                                if (StringUtils.isNotBlank(schemeSurePriceItem.getMethodName())) {
                                    builder.writeln(schemeSurePriceItem.getMethodName());
                                }
                            }
                            if (i == 1) {
                                if (schemeSurePriceItem.getTrialPrice() != null) {
                                    builder.writeln(schemeSurePriceItem.getTrialPrice().toString());
                                }
                            }
                            if (i == 2) {
                                if (schemeSurePriceItem.getWeight() != null) {
                                    builder.writeln(schemeSurePriceItem.getWeight().toString());
                                }
                            }
                        }
                        builder.endRow();
                        row++;
                    }
                }
                SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSchemeSurePriceBySchemeJudgeObjectId(schemeJudgeObject.getId());
                if (schemeSurePrice != null) {
                    for (int i = 0; i < 3; i++) {
                        builder.insertCell();
                        if (StringUtils.isNotBlank(schemeSurePrice.getWeightExplain())) {
                            if (i == 0) {
                                builder.writeln("权重说明");
                            }
                            if (i == 1) {
                                builder.writeln(schemeSurePrice.getWeightExplain());
                            }
                        }
                        mergeCellModelList.add(new MergeCellModel(row, 1, row, 2));
                    }
                    builder.endRow();
                    row++;
                    for (int i = 0; i < 3; i++) {
                        builder.insertCell();
                        if (schemeSurePrice.getPrice() != null) {
                            if (i == 0) {
                                builder.writeln("最终单价");
                            }
                            if (i == 1) {
                                builder.writeln(schemeSurePrice.getPrice().toString());
                            }
                        }
                        mergeCellModelList.add(new MergeCellModel(row, 1, row, 2));
                    }
                    builder.endRow();
                    row++;
                }
                if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                    for (MergeCellModel mergeCellModel : mergeCellModelList) {
                        Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                        Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                        AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                    }
                }
                builder.endTable();
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 权重说明
     *
     * @return
     */
    public String getWeightSpecification() {
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setDeclareRecordId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    if (schemeSurePrice != null) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeSurePrice.getWeightExplain()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 房地产总价
     *
     * @return
     */
    public String getTotalRealEstatePrice() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        temp = temp.add(bigDecimal);
                    }
                }
            }
        }
        temp = temp.divide(new BigDecimal(10000));
        return temp.toString();
    }

    /**
     * 大写金额
     *
     * @return
     */
    public String getCapitalizationAmount() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        temp = temp.add(bigDecimal);
                    }
                }
            }
        }
        temp = temp.divide(new BigDecimal(10000));
        return CnNumberUtils.toUppercaseSubstring(temp.toString());
    }

    /**
     * 法定优先受偿款
     *
     * @return
     */
    public String getStatutoryOptimumReimbursement() {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String temp = null;
                try {
                    temp = schemeReimbursementService.getFullDescription(schemeJudgeObject.getId());
                } catch (Exception e) {
                }
                if (StringUtils.isNotBlank(temp)) {
                    List<Integer> integerList = stringListMap.get(temp);
                    if (CollectionUtils.isNotEmpty(integerList)) {
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    } else {
                        integerList = Lists.newArrayList();
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    }
                    stringListMap.put(temp, integerList);
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;

    }

    /**
     * 变现分析表
     *
     * @param title 标题
     * @return
     */
    public String getLiquidationAnalysis(String title) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath(title);
        for (SchemeJudgeObject judgeObject : this.getSchemeJudgeObjectList()) {
            createLiquidationAnalysisTable(builder, judgeObject);
        }
        doc.save(localPath);
        return localPath;
    }

    public void createLiquidationAnalysisTable(DocumentBuilder builder, SchemeJudgeObject judgeObject) throws Exception {
        builder.writeln(getSchemeJudgeObjectShowName(judgeObject));
        //表头
        builder.insertCell();
        builder.writeln("物业类型");
        builder.insertCell();
        builder.writeln("税率");
        builder.insertCell();
        builder.writeln("备注");
        builder.insertCell();
        builder.writeln("商业");
        builder.endRow();

        builder.insertCell();
        builder.writeln("面积");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(judgeObject.getEvaluationArea().toString());
        builder.endRow();

        builder.insertCell();
        builder.writeln("评估价");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(judgeObject.getPrice().toString());
        builder.endRow();
        SchemeLiquidationAnalysis object = new SchemeLiquidationAnalysis();
        object.setJudgeObjectId(judgeObject.getId());
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByJudgeObjectId(judgeObject.getId());
        List<SchemeLiquidationAnalysisItem> itemList = schemeLiquidationAnalysisService.getAnalysisItemList(schemeLiquidationAnalysis.getPlanDetailsId());
        for (SchemeLiquidationAnalysisItem item : itemList) {
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getTaxRateName())) {
                builder.writeln(item.getTaxRateName());
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (item.getCalculationMethod() == 1 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                builder.writeln(new BigDecimal(item.getTaxRateValue()).multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
            } else if (item.getCalculationMethod() == 0 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                builder.writeln(item.getTaxRateValue() + "元/㎡");
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getRemark())) {
                builder.writeln(item.getRemark());
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getPrice().toString())) {
                builder.writeln(item.getPrice().toString());
            } else {
                builder.writeln("空");
            }
            builder.endRow();
        }
        builder.insertCell();
        builder.writeln("合计费用");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(schemeLiquidationAnalysis.getTotal().toString());
        builder.endRow();
    }


    /**
     * 评估方法 , 估价对象评估方法
     *
     * @return
     * @throws Exception
     */
    public String getEvaluationMethodValuationObject() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            StringBuilder builder = new StringBuilder(24);
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (int i = 0; i < schemeJudgeFunctionList.size(); i++) {
                        builder.append(schemeJudgeFunctionList.get(i).getName());
                        if (i != schemeJudgeFunctionList.size() - 1) {
                            builder.append(",");
                        }
                    }
                }
                stringSet.add(builder.toString());
                builder.delete(0, builder.toString().length());
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 功能描述: 技术思路通用模板
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 15:20
     */
    public String getGeneralTemplateTechnicalIdea() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append("");
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getThinking()) && schemeJudgeFunction.getBisApplicable()) {
                            builder.append(schemeJudgeFunction.getName()).append("思路:");
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 估价对象选择估价方法
     *
     * @return
     */
    public String getSelectionValuationMethod() {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getApplicableReason())) {
                            builder.append(schemeJudgeFunction.getName()).append("估价方法:");
                            builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 委托目的表述
     *
     * @return
     */
    public String getStatementPurposeEntrustment() {
        String statementPurposeEntrustment = getSchemeAreaGroup().getRemarkEntrustPurpose();
        if (StringUtils.isNotBlank(statementPurposeEntrustment)) {
            return statementPurposeEntrustment;
        } else {
            return " ";
        }
    }

    //委托目的
    public String getDelegatePurpose() {
        String str = baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose());
        if (StringUtils.isNotBlank(str)) {
            return str;
        }
        return " ";
    }

    /**
     * get 价值类型
     *
     * @return
     */
    public String getValueType() {
        if (getSchemeAreaGroup() != null) {
            String value = baseDataDicService.getNameById(getSchemeAreaGroup().getValueDefinition());
            if (StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        return errorStr;
    }

    //价值类型描述
    public String getValueTypeDesc() {
        String value = getSchemeAreaGroup().getValueDefinitionDesc();
        if (StringUtils.isNotBlank(value)) {
            return value;
        } else {
            return errorStr;
        }
    }


    /**
     * 注册房产估价师
     *
     * @param str
     * @return
     */
    public String getRegisteredRealEstateValuer(String str) {
        String[] strings = str.split(",");
        StringBuilder builder = new StringBuilder();
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccountName())) {
                    builder.append(dataQualificationVo.getUserAccountName()).append(" ");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            return errorStr;
        }
        return builder.toString();
    }

    /**
     * 注册房产估价师 编号
     *
     * @param str
     * @return
     * @throws Exception
     */
    public String getRegistrationNumber(String str) throws Exception {
        StringBuilder builder = new StringBuilder(24);
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            builder.append(adPersonalQualificationDtoList.get(0).getCertificateNo()).append(" ");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 房地产估价机构信息
     *
     * @return
     * @throws Exception
     */
    public String getXIEHE_organizationInfo(AdCompanyQualificationDto qualificationDto) throws Exception {
        StringBuilder builder = new StringBuilder(24);
        builder.append("机构名称:").append(qualificationDto.getOrganizationName()).append(";");
        builder.append("住所:").append(qualificationDto.getOrganizationAddress()).append(";");
        builder.append("法定代表人:").append(qualificationDto.getLegalRepresentative()).append(";");
        builder.append("工商注册号:").append(qualificationDto.getRegisteredNo()).append(";");
        builder.append("资质等级:").append(qualificationDto.getOrganizationRank()).append(";");
        builder.append("资质证书编号:").append(qualificationDto.getCertificateNo()).append(";");
        builder.append("资质证书有效期:").append(qualificationDto.getCertificateEffectiveDate()).append(";");
        builder.append("经营范围:").append("评估房产").append("");
        return builder.toString();
    }

    /**
     * 根据公司编号取得公司营业执照
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForLicense() {
        return adRpcQualificationsService.getCompanyQualificationForLicense(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 根据公司编号取得公司执业资质
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForPractising() {
        return adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 经营范围
     *
     * @param qualificationDto
     * @return
     * @throws Exception
     */
    public String getBusinessScope(AdCompanyQualificationDto qualificationDto) throws Exception {
        return "评估房地产";
    }

    /**
     * 现场查勘期
     *
     * @param start
     * @param end
     * @return
     */
    public String getSurveyExamineDate(Date start, Date end) {
        if (start == null) {
            start = new Date();
        }
        if (end == null) {
            end = new Date();
        }
        return String.format("%s%s", DateUtils.format(start, DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN));
    }

    /**
     * 现场查勘人员
     *
     * @return
     * @throws Exception
     */
    public String getSurveyExamineCreate() throws Exception {
        List<ProjectPhaseVo> projectPhaseVoList = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(),
                projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            //查勘通过
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            //案例同样通过
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(projectPhaseVoList)) {
            projectPhaseVoList.stream().forEach(projectPhaseVo -> {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setProjectPhaseId(projectPhaseVo.getId());
                List<ProjectPlanDetails> projectPlanDetailss = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailss)) {
                    projectPlanDetailsList.addAll(projectPlanDetailss);
                }
            });
        }
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder builder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
            projectPlanDetailsList.stream().forEach(projectPlanDetails -> stringSet.add(projectPlanDetails.getExecuteUserAccount()));
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 作业结束时间
     *
     * @param end
     * @return
     */
    public String getHomeWorkEndTime(Date end) {
        if (end == null) {
            end = new Date();
        }
        return DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 作业开始时间
     *
     * @return
     */
    public String getHomeWorkStartTime() {
        return DateUtils.format(projectInfo.getGmtCreated(), DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 评估依据假设原则
     *
     * @param schemeSupportTypeEnum
     * @return
     * @throws Exception
     */
    public String getPrincipleBasisHypothesis(SchemeSupportTypeEnum schemeSupportTypeEnum) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeSupportInfo> schemeSupportInfoList = schemeSupportInfoService.getSupportInfoListByAreaId(areaId, schemeSupportTypeEnum);
        if (CollectionUtils.isNotEmpty(schemeSupportInfoList)) {
            int i = 1;
            for (SchemeSupportInfo schemeSupportInfo : schemeSupportInfoList) {
                if (StringUtils.isNotBlank(schemeSupportInfo.getContent())) {
                    builder.insertHtml(String.format("<h5>%s.%s</h5>", i, schemeSupportInfo.getName()));
                    builder.writeln(schemeSupportInfo.getContent());
                    i++;
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 报告分析
     *
     * @return
     */
    public String getReportAnalysis(String type) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(type);
        List<CompileReportDetail> compileReportDetailList = compileReportService.getCompileReportDetailList(areaId, baseDataDic.getId());
        if (CollectionUtils.isNotEmpty(compileReportDetailList)) {
            int i = 1;
            for (CompileReportDetail compileReportDetail : compileReportDetailList) {
                if (StringUtils.isNotBlank(compileReportDetail.getContent())) {
                    builder.insertHtml(String.format("<h5>%s.%s</h5>", i, compileReportDetail.getName()));
                    builder.writeln(compileReportDetail.getContent());
                    i++;
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 协助工作人员
     *
     * @return
     */
    public String getAssistanceStaff(String str) {
        String[] ids = str.split(",");
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(projectInfo.getProjectTypeId(),
                projectInfo.getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        for (String id : ids) {
            if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
                projectPhaseVos.stream().forEach(projectPhaseScene -> {
                    DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                                if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
                                    if (dataQualificationVo != null) {
                                        if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                                            for (String account : dataQualificationVo.getUserAccount().split(",")) {
                                                if (Objects.equal(account, projectPlanDetails.getExecuteUserAccount())) {

                                                } else {
                                                    stringSet.add(publicService.getUserNameByAccount(projectPlanDetails.getExecuteUserAccount()));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * get 价值内涵
     *
     * @return
     */
    public String getValueImplication() {
        List<String> list = JSON.parseArray(getSchemeAreaGroup().getValueConnotation(), String.class);
        if (CollectionUtils.isEmpty(list)) return null;
        List<Integer> integerList = FormatUtils.ListStringToListInteger(list);
        String result = new String();
        for (String s : list) {
            result += baseDataDicService.getNameById(s) + ",";
        }
        return StringUtils.strip(result, ",");
    }

    //价值内涵描述
    public String getValueImplicationDesc() {
        String value = getSchemeAreaGroup().getValueConnotationDesc();
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return errorStr;
    }

    /**
     * 权利人
     *
     * @return
     */
    public String getPowerPerson() {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), true);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getCertUse())).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObjectList.get(i).getDeclareRecordId());
                if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getOwnership())) {
                    stringSet.add(String.format("%s", declareRecord.getOwnership()));
                }
            }
        }
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(stringSet)) {
            for (String s : stringSet) {
                stringListMap.put(s, new ArrayList<Integer>(0));
            }
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String key = null;
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getOwnership())) {
                    for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                        if (Objects.equal(entry.getKey(), declareRecord.getOwnership())) {
                            key = entry.getKey();
                            break;
                        }
                    }
                    if (StringUtils.isNotBlank(key)) {
                        //取出列表
                        List<Integer> integerList = stringListMap.get(key);
                        //添加进相同证载用途的列表中
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                        //更新回去
                        stringListMap.put(key, integerList);
                    }
                }
            }
        }
        stringSet.clear();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 设定用途
     *
     * @return
     */
    public String getSetUse() {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getSetUse() != null).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObjectList.get(i).getSetUse());
                    if (dataSetUseField != null && StringUtils.isNotBlank(dataSetUseField.getName())) {
                        stringSet.add(String.format("%s", dataSetUseField.getName()));
                    }
                }
            }
        }
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(stringSet)) {
            for (String s : stringSet) {
                stringListMap.put(s, new ArrayList<Integer>(0));
            }
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String key = null;
                for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null && StringUtils.isNotBlank(dataSetUseField.getName())) {
                        if (Objects.equal(entry.getKey(), dataSetUseField.getName())) {
                            key = entry.getKey();
                            break;
                        }
                    }
                }
                if (StringUtils.isNotBlank(key)) {
                    //取出列表
                    List<Integer> integerList = stringListMap.get(key);
                    //添加进相同证载用途的列表中
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    //更新回去
                    stringListMap.put(key, integerList);
                }
            }
        }
        stringSet.clear();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 实际用途
     *
     * @return
     */
    public String getPracticalUse() {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getPracticalUse())).collect(Collectors.toList());
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPracticalUse())) {
                    String s = baseDataDicService.getNameById(schemeJudgeObjectList.get(i).getPracticalUse());
                    if (StringUtils.isNotBlank(s)) {
                        stringSet.add(s);
                    }
                } else {
                    stringSet.add(schemeJudgeObjectList.get(i).getPracticalUse());
                }
            }
        }
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(stringSet)) {
            for (String s : stringSet) {
                stringListMap.put(s, new ArrayList<Integer>(0));
            }
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                String key = null;
                for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                    String s = null;
                    if (NumberUtils.isNumber(schemeJudgeObject.getPracticalUse())) {
                        s = baseDataDicService.getNameById(schemeJudgeObject.getPracticalUse());
                    } else {
                        s = schemeJudgeObject.getPracticalUse();
                    }
                    if (Objects.equal(entry.getKey(), s)) {
                        key = entry.getKey();
                        break;
                    }
                }
                if (StringUtils.isNotBlank(key)) {
                    //取出列表
                    List<Integer> integerList = stringListMap.get(key);
                    //添加进相同证载用途的列表中
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    //更新回去
                    stringListMap.put(key, integerList);
                }
            }
        }
        stringSet.clear();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 建筑结构类别
     *
     * @return
     * @throws Exception
     */
    public String getBuildingStructureCategory() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory() != null) {
                                    String temp = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                    if (StringUtils.isNotBlank(temp)) {
                                        List<Integer> integerList = stringListMap.get(temp);
                                        if (CollectionUtils.isNotEmpty(integerList)) {
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        } else {
                                            integerList = Lists.newArrayList();
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        }
                                        stringListMap.put(temp, integerList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 税费负担
     *
     * @return
     * @throws Exception
     */
    public String getTaxBurden() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getTaxBurden() != null) {
                                    String temp = baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getTaxBurden());
                                    if (StringUtils.isNotBlank(temp)) {
                                        List<Integer> integerList = stringListMap.get(temp);
                                        if (CollectionUtils.isNotEmpty(integerList)) {
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        } else {
                                            integerList = Lists.newArrayList();
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        }
                                        stringListMap.put(temp, integerList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 付款方式
     *
     * @return
     * @throws Exception
     */
    public String getPaymentMethod() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getPaymentMethod() != null) {
                                    String temp = baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getPaymentMethod());
                                    if (StringUtils.isNotBlank(temp)) {
                                        List<Integer> integerList = stringListMap.get(temp);
                                        if (CollectionUtils.isNotEmpty(integerList)) {
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        } else {
                                            integerList = Lists.newArrayList();
                                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                        }
                                        stringListMap.put(temp, integerList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }


    /**
     * 使用权类型
     *
     * @return
     */
    public String getUseRightType() {
        String value = getPracticalUse();
        if (StringUtils.isNotBlank(value)) {
            return value;
        } else {
            return " ";
        }
    }

    /**
     * 价值时点
     *
     * @return
     */
    public String getValueTimePoint() {
        if (getSchemeAreaGroup().getValueTimePoint() != null) {
            return DateUtils.format(getSchemeAreaGroup().getValueTimePoint(), DateUtils.DATE_CHINESE_PATTERN);
        }
        return " ";
    }

    /**
     * 价值时点说明
     *
     * @return
     */
    public String getValueTimePointRemark() {
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getTimePointExplain())) {
            return getSchemeAreaGroup().getTimePointExplain();
        }
        return " ";
    }


    /**
     * 出租或占用情况
     *
     * @return
     */
    public String getRentalPossessionDesc() {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (StringUtils.isNotBlank(schemeJudgeObject.getRentalPossessionDesc())) {
                    List<Integer> integerList = stringListMap.get(schemeJudgeObject.getRentalPossessionDesc());
                    if (CollectionUtils.isNotEmpty(integerList)) {
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    } else {
                        integerList = Lists.newArrayList();
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    }
                    stringListMap.put(schemeJudgeObject.getRentalPossessionDesc(), integerList);
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 他权有无租赁权
     *
     * @return
     * @throws Exception
     */
    public String getHisRightHasLease() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        final String temp = "他权有无租赁权:";
        stringBuilder.append(temp);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_LEASEHOLD);
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                inner:
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (Objects.equal(inventoryRight.getCategory(), baseDataDic.getId())) {
                                        stringBuilder.append("有");
                                        break inner;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Objects.equal(stringBuilder.toString(), temp)) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 功能描述: 有无他项权
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 15:10
     */
    public String getThereAnyOtherRight() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    boolean flag = false;
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (StringUtils.isNotBlank(inventoryRight.getNumber())) {
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                    if (flag) {
                        stringSet.add(String.format("%s%s", schemeJudgeObject.getName(), ":有他项权"));
                    } else {
                        stringSet.add(String.format("%s%s", schemeJudgeObject.getName(), ":无他项权"));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 他权类别
     *
     * @return
     * @throws Exception
     */
    public String getHisRightType() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.ASSET_INVENTORY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            String temp = null;
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (inventoryRight.getCategory() != null) {
                                        stringBuilder.append(baseDataDicService.getNameById(inventoryRight.getCategory()));
                                        temp = stringBuilder.toString();
                                        stringBuilder.delete(0, stringBuilder.toString().length());
                                    }
                                }
                            }
                            if (StringUtils.isNotBlank(temp)) {
                                List<Integer> integerList = stringListMap.get(temp);
                                if (CollectionUtils.isNotEmpty(integerList)) {
                                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                } else {
                                    integerList = Lists.newArrayList();
                                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                }
                                stringListMap.put(temp, integerList);
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s)) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 他权其它
     *
     * @return
     * @throws Exception
     */
    public String getRightOther() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.ASSET_INVENTORY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            String temp = null;
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (inventoryRight.getCategory() != null) {
                                        stringBuilder.append(baseDataDicService.getNameById(inventoryRight.getCategory()));
                                        stringBuilder.append("-").append(inventoryRight.getNumber());
                                        stringBuilder.append("-").append(inventoryRight.getCertName());
                                        stringBuilder.append("-").append(inventoryRight.getObligee());
                                        stringBuilder.append("-").append(inventoryRight.getObligor());
                                        temp = stringBuilder.toString();
                                        stringBuilder.delete(0, stringBuilder.toString().length());
                                    }
                                }
                            }
                            if (StringUtils.isNotBlank(temp)) {
                                List<Integer> integerList = stringListMap.get(temp);
                                if (CollectionUtils.isNotEmpty(integerList)) {
                                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                } else {
                                    integerList = Lists.newArrayList();
                                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                                }
                                stringListMap.put(temp, integerList);
                            }
                        }
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s)) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 功能描述: 资产清查实际地址
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:02
     */
    public String getActualAddressAssetInventory() throws Exception {
        //getActual == > actual
        String s = getAssetInventoryCommon("actual");
        return s;
    }

    /**
     * 功能描述: 资产清查证明人
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:10
     */
    public String getCertificateAssetInventory() throws Exception {
        //getVoucher == > voucher
        String s = getAssetInventoryCommon("voucher");
        return s;
    }

    /**
     * 功能描述: 资产清查确认一致
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:16
     */
    public String getAssetInventoryConfirmConsistency() throws Exception {
        //getSureConsistent == > sureConsistent
        String s = getAssetInventoryCommon("sureConsistent");
        return s;
    }

    /**
     * 功能描述: 资产清查一致说明
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:22
     */
    public String getAssetInventoryAgreement() throws Exception {
        //getDifferenceReason == > differenceReason
        String s = getAssetInventoryCommon("differenceReason");
        return s;
    }

    /**
     * 资产清查 提取的公共方法
     *
     * @param fieldName
     * @return
     * @throws Exception
     */
    private String getAssetInventoryCommon(String fieldName) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringBuilder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append(":");
                                            String value = (String) Reflections.getFieldValue(surveyAssetInventoryContent, fieldName);
                                            stringBuilder.append(value);
                                            stringSet.add(stringBuilder.toString());
                                            stringBuilder.delete(0, stringBuilder.toString().length());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 担保物权设立情况
     *
     * @return
     * @throws Exception
     */
    public String getCollateralFound() throws Exception {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(16);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (StringUtils.isNotBlank(schemeJudgeObject.getCollateralFound())) {
                    List<Integer> integerList = stringListMap.get(schemeJudgeObject.getCollateralFound());
                    if (CollectionUtils.isNotEmpty(integerList)) {
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    } else {
                        integerList = Lists.newArrayList();
                        integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                    }
                    stringListMap.put(schemeJudgeObject.getCollateralFound(), integerList);
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 最佳利用描述
     *
     * @return
     * @throws Exception
     */
    public String getOptimumUtilizationDescription() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(16);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateCommonMethod.getByRootAndChildSchemeJudgeObjectList(getSchemeJudgeObjectList(), false);
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getBestUse() != null) {
                    String temp = baseDataDicService.getNameById(schemeJudgeObject.getBestUse());
                    if (StringUtils.isNotBlank(temp)) {
                        List<Integer> integerList = stringListMap.get(temp);
                        if (CollectionUtils.isNotEmpty(integerList)) {
                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                        } else {
                            integerList = Lists.newArrayList();
                            integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                        }
                        stringListMap.put(temp, integerList);
                    }
                }
            }
        }
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append("号").append(generateCommonMethod.SchemeJudgeObjectName).append(":").append(entry.getKey());
                    stringSet.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        if (StringUtils.isEmpty(s)) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 法定优先受偿款总金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmountTotal() throws Exception {
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        query.setProjectPhaseId(projectPhase.getId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                            }
                        }
                    }
                }
            }
        }
        java.math.BigDecimal bigDecimal = new BigDecimal(0);
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                if (schemeReimbursement != null && schemeReimbursement.getKnowTotalPrice() != null) {
                    bigDecimal = bigDecimal.add(schemeReimbursement.getKnowTotalPrice());
                }
            }
        }
        return bigDecimal.toString();
    }


    /**
     * 法定优先受偿款金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmount() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        query.setProjectPhaseId(projectPhase.getId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                            }
                        }
                    }
                }
            }
        }
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                if (schemeReimbursement != null && schemeReimbursement.getKnowTotalPrice() != null) {
                    stringSet.add(String.format("%s:%s、", integerEntry.getKey().getName(), schemeReimbursement.getKnowTotalPrice().toString()));
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 假设开发法适用原因
     *
     * @return
     */
    public String getDevelopmentAssistApplyReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 假设开发法不适用原因
     *
     * @return
     */
    public String getDevelopmentAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (!schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getNotApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 假设开发法不适用原因
     *
     * @return
     */
    public String getDevelopmentAssistThink() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getThinking()).append("-");
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 收益法适用原因
     *
     * @return
     */
    public String getIncomeAssistApplyReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 收益法不适用原因
     *
     * @return
     */
    public String getIncomeAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (!schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getNotApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 收益法评估思路
     *
     * @return
     */
    public String getIncomeAssistThink() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getThinking()).append("-");
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 市场比较法适用原因
     *
     * @return
     */
    public String getCompareAssistApplyReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 市场比较法不适用原因
     *
     * @return
     */
    public String getCompareAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (!schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getNotApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 市场比较法评估思路
     *
     * @return
     */
    public String getCompareAssistThink() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getThinking()).append("-");
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 成本法适用原因
     *
     * @return
     */
    public String getCostAssistApplyReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 成本法不适用原因
     *
     * @return
     */
    public String getCostAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (!schemeJudgeFunction.getBisApplicable()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                            if (baseDataDic != null) {
                                if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                    builder.append(schemeJudgeFunction.getNotApplicableReason()).append("-");
                                }
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 成本法评估思路
     *
     * @return
     */
    public String getCostAssistThink() {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getThinking()).append("-");
                            }
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }


    /**
     * 计算过程
     *
     * @return
     * @throws Exception
     */
    public String getComputationProcess(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 功能描述: 估价对象市场价值的确定
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 16:25
     */
    public String getDeterminationMarketValueValuationObject() throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.writeln(String.format("%s:", getSchemeJudgeObjectShowName(schemeJudgeObject)));
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("测算结果相近，通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素，");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("的试算结果与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("试算结果均能反映估价对象市场价值，");
                    //权重说明
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectId);
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
                            if (schemeSurePrice != null) {
                                stringBuilder.append(schemeSurePrice.getWeightExplain());
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());

                    stringBuilder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append("的单价=").append(getEvaluationExpression());
                    List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                    if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                        for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                                if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                    stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                                    stringBuilder.append("+");
                                }
                            }
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                                stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 相关参数选取与应用
     *
     * @return
     * @throws Exception
     */
    public String getSelectionApplicationParameters() throws Exception {
        Document doc = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();

        DataEvaluationMethod dataEvaluationMethodMdCompare = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
            if (Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), dataEvaluation.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        DataEvaluationMethod dataEvaluationMethodMdIncome = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
            if (Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), dataEvaluation.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        DataMethodFormula compareFormula = null;
        DataMethodFormula mdIncomeFormula = null;

        if (dataEvaluationMethodMdCompare != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethodMdCompare.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                compareFormula = dataMethodFormulaList.get(0);
            }
        }
        if (dataEvaluationMethodMdIncome != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethodMdIncome.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                mdIncomeFormula = dataMethodFormulaList.get(0);
            }
        }

        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                documentBuilder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObject));
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            documentBuilder.writeln(CalculationMethodNameEnum.MdCompare.getName());
                            if (compareFormula != null && StringUtils.isNotBlank(compareFormula.getFormula())) {
                                documentBuilder.writeln(String.format("%s:%s", "公式", compareFormula.getFormula()));
                            } else {
                                documentBuilder.writeln(String.format("%s:%s", "公式", "无"));
                            }
                        }
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            documentBuilder.writeln(CalculationMethodNameEnum.MdIncome.getName());
                            if (mdIncomeFormula != null && StringUtils.isNotBlank(mdIncomeFormula.getFormula())) {
                                documentBuilder.writeln(String.format("%s:%s", "公式", mdIncomeFormula.getFormula()));
                            } else {
                                documentBuilder.writeln(String.format("%s:%s", "公式", "无"));
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象权属状况表
     *
     * @return
     * @throws Exception
     */
    public String getEquityStatusValuatedObjects() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        Map<SchemeJudgeObject, DeclareRecord> objectDeclareRealtyRealEstateCertVoMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getBisMerge()) {
                    List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                        for (SchemeJudgeObject judgeObject : judgeObjectList) {
                            if (judgeObject.getDeclareRecordId() != null && schemeJudgeObject.getDeclareRecordId().intValue() != 0) {
                                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                                if (declareRecord != null) {
                                    objectDeclareRealtyRealEstateCertVoMap.put(judgeObject, declareRecord);
                                }
                            }
                        }
                    }
                } else {
                    if (schemeJudgeObject.getDeclareRecordId() != null && schemeJudgeObject.getDeclareRecordId().intValue() != 0) {
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                        if (declareRecord != null) {
                            objectDeclareRealtyRealEstateCertVoMap.put(schemeJudgeObject, declareRecord);
                        }
                    }
                }
            }
        }
        if (!objectDeclareRealtyRealEstateCertVoMap.isEmpty()) {
            final int colMax = 11;
            Table table = builder.startTable();
            for (int j = 0; j < colMax; j++) {
                builder.insertCell();
                if (j == 0) builder.writeln("估价对象");
                if (j == 1) builder.writeln("产权证号");
                if (j == 2) builder.writeln("权利人");
                if (j == 3) builder.writeln("共有情况");
                if (j == 4) builder.writeln("坐落");
                if (j == 5) builder.writeln("用途");
                if (j == 6) builder.writeln("土地终止日期");
                if (j == 7) builder.writeln("房屋总层数");
                if (j == 8) builder.writeln("所在层数");
                if (j == 9) builder.writeln("分摊土地使用权面积");
                if (j == 10) builder.writeln("建筑面积（㎡）");
            }
            builder.endRow();
            for (Map.Entry<SchemeJudgeObject, DeclareRecord> realEstateCertVoEntry : objectDeclareRealtyRealEstateCertVoMap.entrySet()) {
                for (int j = 0; j < colMax; j++) {
                    builder.insertCell();
                    if (j == 0) {
                        builder.writeln(getSchemeJudgeObjectShowName(realEstateCertVoEntry.getKey()));
                    }
                    if (j == 1) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getName())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getName()) ? realEstateCertVoEntry.getValue().getName() : errorStr));
                        }
                    }
                    if (j == 2) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getOwnership())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getOwnership()) ? realEstateCertVoEntry.getValue().getOwnership() : errorStr));
                        }
                    }
                    if (j == 3) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPublicSituation())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPublicSituation()) ? realEstateCertVoEntry.getValue().getPublicSituation() : errorStr));
                        }
                    }
                    if (j == 4) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getSeat())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getSeat()) ? realEstateCertVoEntry.getValue().getSeat() : errorStr));
                        }
                    }
                    if (j == 5) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getCertUse())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getCertUse()) ? baseDataDicService.getNameById(realEstateCertVoEntry.getValue().getCertUse()) : errorStr));
                        }
                    }
                    if (j == 6) {
                        if (realEstateCertVoEntry.getValue().getLandUseEndDate() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getLandUseEndDate().toString()) ? DateUtils.format(realEstateCertVoEntry.getValue().getLandUseEndDate(), DateUtils.DATE_CHINESE_PATTERN) : errorStr));
                        }
                    }
                    if (j == 7) builder.writeln(String.format("%s", "无"));
                    if (j == 8) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getFloor())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getFloor()) ? realEstateCertVoEntry.getValue().getFloor() : errorStr));
                        }
                    }
                    if (j == 9) {
                        builder.writeln(errorStr);
                    }
                    if (j == 10) {
                        if (realEstateCertVoEntry.getValue().getPracticalArea() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPracticalArea().toString()) ? realEstateCertVoEntry.getValue().getPracticalArea().toString() : errorStr));
                        }
                    }
                }
                builder.endRow();
            }
            //设置表格边框的宽度
            builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
            //设置具体宽度
            builder.getCellFormat().setWidth(100);
            //水平居中
            builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
            //上下居中
            builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
            builder.endTable();
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 本次估价的总体思路和评估方法的选取
     *
     * @return
     * @throws Exception
     */
    public String gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 土地使用权登记状况表
     *
     * @return
     * @throws Exception
     */
    public String getjudgeObjectLandUseCertificateSheet() throws Exception {
        List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = Lists.newArrayList();
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            List<DeclareRecord> declareRecords = declareRecordList.stream().filter(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                declareRecords.stream().forEach(declareRecord -> {
                    if (declareRecord.getDataTableId() != null) {
                        DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                        if (declareRealtyLandCert != null) {
                            DeclareRealtyLandCertVo declareRealtyLandCertVo = declareRealtyLandCertService.getDeclareRealtyLandCertVo(declareRealtyLandCert);
                            declareRealtyLandCertVoList.add(declareRealtyLandCertVo);
                        }
                    }
                });
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        int num = 2;
        int length = 12;
        for (int j = 0; j < num; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < length; k++) {
                        if (k == 0) {
                            builder.insertCell();
                            mergeCellModelList.add(new MergeCellModel(0, 0, 0, 11));
                            builder.writeln("估价对象《土地使用证》登记状况一览表");
                        } else {
                            builder.insertCell();
                            builder.writeln("");
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < length; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("土地证号");
                                break;
                            case 1:
                                builder.insertCell();
                                builder.writeln("座落");
                                break;
                            case 2:
                                builder.insertCell();
                                builder.writeln("土地使用权人");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("地号");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("图号");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("终止日期");
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("使用权类型");
                                break;
                            case 7:
                                builder.insertCell();
                                builder.writeln("用途");
                                break;
                            case 8:
                                builder.insertCell();
                                builder.writeln("使用权面积（㎡）");
                                break;
                            case 9:
                                builder.insertCell();
                                builder.writeln("其中：分摊面积（㎡）");
                                break;
                            case 10:
                                builder.insertCell();
                                builder.writeln("取得价格（万元）");
                                break;
                            case 11:
                                builder.insertCell();
                                builder.writeln("记   事");
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
            for (int i = num; i < declareRealtyLandCertVoList.size() + num; i++) {
                DeclareRealtyLandCertVo declareRealtyLandCertVo = declareRealtyLandCertVoList.get(i - num);
                for (int k = 0; k < length; k++) {
                    switch (k) {
                        case 0:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getNumber()) ? declareRealtyLandCertVo.getNumber() : "");
                            break;
                        case 1:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getLandCertName()) ? declareRealtyLandCertVo.getLandCertName() : "");
                            break;
                        case 2:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getOwnership()) ? declareRealtyLandCertVo.getOwnership() : "");
                            break;
                        case 3:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getLandNumber()) ? declareRealtyLandCertVo.getLandNumber() : "");
                            break;
                        case 4:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getGraphNumber()) ? declareRealtyLandCertVo.getGraphNumber() : "");
                            break;
                        case 5:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getTerminationDate() != null) {
                                builder.writeln(DateUtils.format(declareRealtyLandCertVo.getTerminationDate(), DateUtils.DATE_CHINESE_PATTERN));
                            } else {
                                builder.writeln("");
                            }
                            break;
                        case 6:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getUseRightType()) ? declareRealtyLandCertVo.getUseRightType() : "");
                            break;
                        case 7:
                            builder.insertCell();
                            String s = baseDataDicService.getNameById(declareRealtyLandCertVo.getPurpose());
                            s = StringUtils.isNotBlank(s) ? s : "";
                            builder.writeln(s);
                            break;
                        case 8:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getUseRightArea() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getUseRightArea().toString()) ? declareRealtyLandCertVo.getUseRightArea().toString() : "");
                            }
                            break;
                        case 9:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getApportionmentArea() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getApportionmentArea().toString()) ? declareRealtyLandCertVo.getApportionmentArea().toString() : "");
                            }
                            break;
                        case 10:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getAcquisitionPrice() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getAcquisitionPrice().toString()) ? declareRealtyLandCertVo.getAcquisitionPrice().toString() : "");
                            }
                            break;
                        case 11:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getMemo()) ? declareRealtyLandCertVo.getMemo() : "");
                            break;
                        default:
                            builder.insertCell();
                            break;
                    }
                }
                builder.endRow();
            }
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价结果一览表
     *
     * @return
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet() throws Exception {
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        if (schemeJudgeObject.getBisMerge()) {
                            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                                    ProjectPlanDetails query = new ProjectPlanDetails();
                                    query.setProjectId(projectId);
                                    query.setDeclareRecordId(judgeObject.getDeclareRecordId());
                                    query.setProjectPhaseId(projectPhase.getId());
                                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                            judgeObjectIntegerMap.put(judgeObject, projectPlanDetails.getId());
                                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
//                                                judgeObjectIntegerMap.put(judgeObject, projectPlanDetails.getId());
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            ProjectPlanDetails query = new ProjectPlanDetails();
                            query.setProjectId(projectId);
                            query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                            query.setProjectPhaseId(projectPhase.getId());
                            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                                for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                    judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                                    GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                    if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
//                                        judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath("报告模板1");
        builder.writeln("估价结果一览表");
        Table table = builder.startTable();
        final int colMax = 11;
        for (int j = 0; j < colMax; j++) {
            builder.insertCell();
            if (j == 0) builder.writeln("估价对象");
            if (j == 1) builder.writeln("坐落");
            if (j == 2) builder.writeln("用途(证载)");
            if (j == 3) builder.writeln("用途(实际)");
            if (j == 4) builder.writeln("房屋总层数");
            if (j == 5) builder.writeln("所在层数");
            if (j == 6) builder.writeln("建筑面积");
            if (j == 7) builder.writeln("单价（元/㎡）");
            if (j == 8) builder.writeln("评估总价（万元）");
            if (j == 9) builder.writeln("法定优先受偿款(万元)");
            if (j == 10) builder.writeln("抵押价值(万元)");
        }
        builder.endRow();
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                for (int j = 0; j < colMax; j++) {
                    DeclareRecord declareRecord = null;
                    if (integerEntry.getKey().getDeclareRecordId() != null) {
                        declareRecord = declareRecordService.getDeclareRecordById(integerEntry.getKey().getDeclareRecordId());
                    }
                    builder.insertCell();
                    if (j == 0) {
                        builder.writeln(getSchemeJudgeObjectShowName(integerEntry.getKey()));
                    }
                    SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                    if (Objects.equal(getSchemeAreaGroup().getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId()) && schemeReimbursement != null) {
                        //抵押=总价-法定
                        if (schemeReimbursement.getKnowTotalPrice() != null) {
                            if (j == 9) builder.writeln(schemeReimbursement.getKnowTotalPrice().toString());
                        }
                        if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                            BigDecimal totol = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
                            if (totol != null && schemeReimbursement.getKnowTotalPrice() != null) {
                                BigDecimal mortgage = totol.subtract(schemeReimbursement.getKnowTotalPrice());
                                if (j == 10) builder.writeln(mortgage.toString());
                            }
                        }

                    } else {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(integerEntry.getValue());
                        if (j == 1) {
                            if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getSeat())) {
                                builder.writeln(declareRecord.getSeat());
                            }
                        }
                        if (j == 2) {
                            if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getCertUse())) {
                                builder.writeln(declareRecord.getCertUse());
                            }
                        }
                        if (j == 3) {
                            if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getPracticalUse())) {
                                builder.writeln(declareRecord.getPracticalUse());
                            }
                        }
                        if (j == 4) {
                            try {
                                builder.writeln(generateBaseExamineService.getBasicBuilding().getFloorCount().toString());
                            } catch (Exception e) {
                            }
                        }

                        if (j == 5) {
                            if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getFloor())) {
                                builder.writeln(declareRecord.getFloor());
                            }
                        }
                        if (j == 6) {
                            if (declareRecord != null && declareRecord.getPracticalArea() != null) {
                                builder.writeln(declareRecord.getPracticalArea().toString());
                            }
                        }
                        if (j == 7) {
                            if (declareRecord != null && declareRecord.getPrice() != null) {
                                builder.writeln(declareRecord.getPrice().toString());
                            }
                        }
                        if (j == 8) {
                            if (declareRecord.getPrice() != null && declareRecord.getPracticalArea() != null) {
                                BigDecimal total = declareRecord.getPrice().multiply(declareRecord.getPracticalArea());
                                builder.writeln(total.toString());
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }
        //设置表格边框的宽度
        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
        //设置具体宽度
        builder.getCellFormat().setWidth(100);
        //水平居中
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        //上下居中
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 房屋所有权登记状况表
     *
     * @return
     * @throws Exception
     */
    public String getHousingOwnershipRegistrationStatementSheet() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> a_0_3 = Sets.newHashSet();
        Set<String> a_1_3 = Sets.newHashSet();
        Set<String> a_2_3 = Sets.newHashSet();
        Set<String> a_3_3 = Sets.newHashSet();
        Set<String> a_4_3 = Sets.newHashSet();
        Set<String> a_5_3 = Sets.newHashSet();
        Set<String> a_6_3 = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectId);
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                        try {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                            declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
                            for (int j = 0; j < 7; j++) {
                                switch (j) {
                                    case 0:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 0:
                                                    //权益状况
                                                    break;
                                                case 1:
                                                    //土地权益类型
                                                    break;
                                                case 3:
                                                    a_0_3.add(errorStr);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //土地管制情况
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        //证载用途
                                                        if (generateBaseExamineService.getBasicHouse().getCertUse() != null) {
                                                            stringBuilder.append(String.format("证载用途:%s；", baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getCertUse())));
                                                        }
                                                        if (generateBaseExamineService.getEstate().getFloorArea() != null) {
                                                            stringBuilder.append(String.format("建筑面积:%s；", generateBaseExamineService.getEstate().getFloorArea()));
                                                        }
                                                        if (generateBaseExamineService.getEstate().getCoverAnArea() != null) {
                                                            stringBuilder.append(String.format("占地面积:%s；", generateBaseExamineService.getEstate().getCoverAnArea()));
                                                        }
                                                        if (generateBaseExamineService.getEstate().getVolumetricRate() != null) {
                                                            stringBuilder.append(String.format("容积率:%s；", generateBaseExamineService.getEstate().getVolumetricRate()));
                                                        }
                                                        if (generateBaseExamineService.getEstate().getGreeningRate() != null) {
                                                            stringBuilder.append(String.format("绿化率:%s；", generateBaseExamineService.getEstate().getGreeningRate()));
                                                        }
                                                        if (generateBaseExamineService.getBasicBuilding().getBuildingHeight() != null) {
                                                            stringBuilder.append(String.format("建筑高度:%s；", generateBaseExamineService.getBasicBuilding().getBuildingHeight()));
                                                        }
                                                        a_1_3.add(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //土地他项权力
                                                    break;
                                                case 3:
                                                    a_2_3.add(getInventoryRight());
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //其他特殊情况
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        SurveyAssetInventory assetInventory = surveyAssetInventoryDao.getDataByPlanDetailsId(projectPlanDetails.getId());
                                                        if (assetInventory == null) {
                                                            assetInventory = new SurveyAssetInventory();
                                                        }
                                                        a_3_3.add(assetInventory.getSpecialCase());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //房屋所有权
                                                    break;
                                                case 3:
                                                    a_4_3.add(declareRecord.getPublicSituation());
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //出租或占用情况
                                                    break;
                                                case 3:
                                                    a_5_3.add(String.format("%s估价对象:%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getRentalPossessionDesc()));
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 6:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //物业管理情况
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        if (generateBaseExamineService.getBasicBuilding().getPropertyType() != null) {
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicBuilding().getProperty()) ? errorStr : generateBaseExamineService.getBasicBuilding().getProperty()).append("；");
                                                            stringBuilder.append(String.format("物业费:%s；", generateBaseExamineService.getBasicBuilding().getPropertyFee()));
                                                        }
                                                        a_6_3.add(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } catch (Exception e) {

                        }
                    });
                }
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        builder.writeln("房屋所有权登记状况表");
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        for (int j = 0; j < 7; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("权益状况");
                                break;
                            case 1:
                                builder.insertCell();
                                builder.writeln("土地权益类型");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_0_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("土地管制情况");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_1_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 2:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("土地他项权力");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_2_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 3:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("其他特殊情况");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_3_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 4:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("房屋所有权");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_4_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 5:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("出租或占用情况");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_5_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 6:
                    for (int k = 0; k < 4; k++) {
                        switch (k) {
                            case 1:
                                builder.insertCell();
                                builder.writeln("物业管理情况");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln(generateCommonMethod.toSetString(a_6_3));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
            mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
            mergeCellModelList.add(new MergeCellModel(0, 0, 6, 0));
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象区位状况表
     *
     * @return
     */
    public String getJudgeObjectAreaStatusSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        builder.writeln("估价对象区位状况表");
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        DeclareRecord declareRecord = null;
                        if (schemeJudgeObject.getDeclareRecordId() != null) {
                            declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                        }
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null) {
                                    try {
                                        for (int j = 0; j < 5; j++) {
                                            switch (j) {
                                                case 0:
                                                    builder.writeln(String.format("%d%s", (j + 1), "位置状况包括:"));
                                                    if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getSeat())) {
                                                        builder.writeln(String.format("%s%s", "坐落:", declareRecord.getSeat()));
                                                    } else {
                                                        builder.writeln(String.format("%s%s", "坐落:", "无"));
                                                    }
                                                    String position = "";
                                                    try {
                                                        if (generateBaseExamineService.getByDataBlock() != null && generateBaseExamineService.getByDataBlock().getId() != null) {
                                                            position = generateBaseExamineService.getByDataBlock().getPosition();
                                                        }
                                                    } catch (Exception e) {
                                                        //未知异常
                                                    }
                                                    if (StringUtils.isNotBlank(position)) {
                                                        builder.writeln(String.format("%s%s", "方位:", position));
                                                    } else {
                                                        builder.writeln(String.format("%s%s", "方位:", "无"));
                                                    }
                                                    builder.writeln(String.format("%s%s", "与重要场所的距离:", "0"));
                                                    if (true) {
                                                        List<BasicHouseFaceStreetVo> list = generateBaseExamineService.getBasicHouseFaceStreetList();
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        if (CollectionUtils.isNotEmpty(list)) {
                                                            for (BasicHouseFaceStreetVo faceStreet : list) {
                                                                stringBuilder.append(faceStreet.getStreetName())
                                                                        .append(faceStreet.getStreetLevelName())
                                                                        .append(faceStreet.getTrafficFlowName()).append(faceStreet.getVisitorsFlowrateName());
                                                            }
                                                            //临街（路）状况
                                                            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                                builder.writeln(String.format("%s%s", "临街（路）状况:", stringBuilder.toString()));
                                                            } else {
                                                                builder.writeln(String.format("%s%s", "临街（路）状况:", "无"));
                                                            }
                                                            stringBuilder.delete(0, stringBuilder.toString().length());
                                                            //人流量
                                                            list.stream().forEach(faceStreet -> {
                                                                if (faceStreet.getVisitorsFlowrate() != null) {
                                                                    stringBuilder.append(baseDataDicService.getNameById(faceStreet.getVisitorsFlowrate()));
                                                                }
                                                            });
                                                            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                                builder.writeln(String.format("%s%s", "人流量:", stringBuilder.toString()));
                                                            } else {
                                                                builder.writeln(String.format("%s%s", "人流量:", "无"));
                                                            }
                                                        }
                                                    }
                                                    if (generateBaseExamineService.getBasicBuilding() != null && StringUtils.isNotBlank(generateBaseExamineService.getBasicBuilding().getBuildingNumber())) {
                                                        builder.writeln(String.format("%s%s", "楼栋:", generateBaseExamineService.getBasicBuilding().getBuildingNumber()));
                                                    } else {
                                                        builder.writeln(String.format("%s%s", "楼栋:", "无"));
                                                    }
                                                    if (generateBaseExamineService.getBasicBuilding() != null && generateBaseExamineService.getBasicBuilding().getFloorCount() != null) {
                                                        builder.writeln(String.format("%s%s", "楼层:", generateBaseExamineService.getBasicBuilding().getFloorCount().toString()));
                                                    } else {
                                                        builder.writeln(String.format("%s%s", "楼层:", "无"));
                                                    }
                                                    if (generateBaseExamineService.getBasicBuilding() != null && generateBaseExamineService.getBasicHouse().getOrientation() != null) {
                                                        builder.writeln(String.format("%s%s", "朝向:", baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getOrientation())));
                                                    } else {
                                                        builder.writeln(String.format("%s%s", "朝向:", "无"));
                                                    }
                                                    builder.writeln(String.format("%s%s", "商业繁华度:", "估价对象所在区域为规划新城区，区域商业待发展，目前以超市、零售商店为主，商业繁华度一般"));
                                                    break;
                                                case 1:
                                                    builder.writeln(String.format("%d%s", (j + 1), "交通状况包括:"));
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingTrafficVo> voList = generateBaseExamineService.getBasicMatchingTrafficList();
                                                        if (CollectionUtils.isNotEmpty(voList)) {
                                                            voList.stream().forEach(basicMatchingTrafficVo -> {
                                                                stringBuilder.append(basicMatchingTrafficVo.getName())
                                                                        .append(basicMatchingTrafficVo.getNatureName())
                                                                        .append(basicMatchingTrafficVo.getDistanceName());
                                                            });
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "道路状况:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "道路状况:", "无"));
                                                        }
                                                    }
                                                    if (true) {
                                                        List<BasicMatchingTrafficVo> matchingTrafficVoList = generateBaseExamineService.getBasicMatchingTrafficList();
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        if (CollectionUtils.isNotEmpty(matchingTrafficVoList)) {
                                                            long a = matchingTrafficVoList.stream().filter(basicMatchingTrafficVo -> {
                                                                if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.METRO.getName())) {
                                                                    return true;
                                                                }
                                                                return false;
                                                            }).count();
                                                            long b = matchingTrafficVoList.stream().filter(basicMatchingTrafficVo -> {
                                                                if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TRANSIT.getName())) {
                                                                    return true;
                                                                }
                                                                return false;
                                                            }).count();
                                                            if (a > 0) {
                                                                stringBuilder.append("地铁 ");
                                                            }
                                                            if (b > 0) {
                                                                stringBuilder.append("公交 ");
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "出入可利用的交通工具:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "出入可利用的交通工具:", "无"));
                                                        }
                                                    }
                                                    builder.writeln(String.format("%s%s", "交通管制情况:", "无"));
                                                    builder.writeln(String.format("%s%s", "停车方便度:", "无"));
                                                    builder.writeln(String.format("%s%s", "交通收费情况:", "无"));
                                                    break;
                                                case 2:
                                                    builder.writeln(String.format("%d%s", (j + 1), "外部配套设施:"));
                                                    if (true) {
                                                        List<BasicEstateSupply> estateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        //楼盘下供电
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        //楼盘下供水
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        //楼盘下排水
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        //楼盘下采暖供热
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        //楼盘下供气
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "外部基础设施:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "外部基础设施:", "无"));
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    builder.writeln(String.format("%d%s", (j + 1), "外部公共服务设施:"));
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "购物:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "购物:", "无"));
                                                        }
                                                    }
                                                    //银行 | 金融机构
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingFinanceVo> financeList = generateBaseExamineService.getBasicMatchingFinanceList();
                                                        if (CollectionUtils.isNotEmpty(financeList)) {
                                                            for (BasicMatchingFinance finance : financeList) {
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("名称:%s；", finance.getName()));
                                                                stringBuilder.append(finance.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(finance.getCategory())));
                                                                stringBuilder.append(finance.getNature() == null ? "" : String.format("金融机构性质:%s；", baseDataDicService.getNameById(finance.getNature())));
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("服务内容:%s；", finance.getName()));
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "银行:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "银行:", "无"));
                                                        }
                                                    }
                                                    //教育文化
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingEducation> educationList = generateBaseExamineService.getBasicMatchingEducatioListn();
                                                        if (CollectionUtils.isNotEmpty(educationList)) {
                                                            for (BasicMatchingEducation matchingEducation : educationList) {
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(matchingEducation.getSchoolName()) ? "" : String.format("学校名称:%s；", matchingEducation.getSchoolName()));
                                                                stringBuilder.append(matchingEducation.getSchoolNature() == null ? "" : String.format("学校性质:%s；", baseDataDicService.getNameById(matchingEducation.getSchoolNature())));
                                                                stringBuilder.append(matchingEducation.getSchoolGradation() == null ? "" : String.format("学校级次:%s；", baseDataDicService.getNameById(matchingEducation.getSchoolGradation())));
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(matchingEducation.getSchoolLevel()) ? "" : String.format("学校等级:%s；", baseDataDicService.getNameById(Integer.valueOf(matchingEducation.getSchoolLevel()))));
                                                                stringBuilder.append(matchingEducation.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(matchingEducation.getDistance())));
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "教育文化:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "教育文化:", "无"));
                                                        }
                                                    }
                                                    //医疗康养
                                                    if (true) {
                                                        List<BasicMatchingMedical> matchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
                                                        if (CollectionUtils.isNotEmpty(matchingMedicalList)) {
                                                            StringBuilder stringBuilder = new StringBuilder(16);
                                                            matchingMedicalList.stream().forEach(basicMatchingMedical -> {
                                                                stringBuilder.append(basicMatchingMedical.getOrganizationName());
                                                                stringBuilder.append(" ");
                                                                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel())).append(" ");
                                                                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getBedNumber())).append(" ");
                                                                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getDistance())).append(" ");
                                                            });
                                                            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                                builder.writeln(String.format("%s%s", "医疗康养:", stringBuilder.toString()));
                                                            } else {
                                                                builder.writeln(String.format("%s%s", "医疗康养:", "无"));
                                                            }
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "医疗康养:", "无"));
                                                        }
                                                    }
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                            //娱乐休闲
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                                builder.writeln(String.format("%s%s", "娱乐休闲:", stringBuilder.toString()));
                                                            } else {
                                                                builder.writeln(String.format("%s%s", "娱乐休闲:", "无"));
                                                            }
                                                            stringBuilder.delete(0, stringBuilder.toString().length());
                                                            //餐饮文化
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                                builder.writeln(String.format("%s%s", "餐饮文化:", stringBuilder.toString()));
                                                            } else {
                                                                builder.writeln(String.format("%s%s", "餐饮文化:", "无"));
                                                            }
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "娱乐休闲:", "无"));
                                                            builder.writeln(String.format("%s%s", "餐饮文化:", "无"));
                                                        }
                                                    }
                                                    break;
                                                case 4:
                                                    builder.writeln(String.format("%d%s", (j + 1), "周围环境:"));
                                                    //自然环境
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "自然环境:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "自然环境:", "无"));
                                                        }
                                                    }
                                                    //人文环境
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "人文环境:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "人文环境:", "无"));
                                                        }
                                                    }
                                                    //景观
                                                    if (true) {
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        StringBuilder stringBuilder = new StringBuilder(16);
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%s%s", "景观:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%s%s", "景观:", "无"));
                                                        }
                                                    }
                                                    builder.writeln(String.format("%s%s", "区位综述:", "无"));
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        builder.writeln();
                                    } catch (Exception e1) {
                                        //允许exception
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价土地实体状况表
     *
     * @return
     */
    public String getJudgeObjectLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        builder.writeln("估价土地实体状况表");
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getPlanDetailsId() != null) {
                                    try {
                                        for (int j = 0; j < 10; j++) {
                                            switch (j) {
                                                case 0:
                                                    String name = String.format("%s", generateBaseExamineService.getBasicEstateLandState().getName());
                                                    if (StringUtils.isNotBlank(name)) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "名称:", name));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "名称:", "无"));
                                                    }
                                                    break;
                                                case 1:
                                                    //"东至,南至,西至,北至"
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(10);
                                                        stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getEastTo()) ? generateBaseExamineService.getBasicEstateLandState().getEastTo() : errorStr);
                                                        stringBuilder.append(",");
                                                        stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getSouthTo()) ? generateBaseExamineService.getBasicEstateLandState().getSouthTo() : errorStr);
                                                        stringBuilder.append(",");
                                                        stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getWestTo()) ? generateBaseExamineService.getBasicEstateLandState().getWestTo() : errorStr);
                                                        stringBuilder.append(",");
                                                        stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getNorthTo()) ? generateBaseExamineService.getBasicEstateLandState().getNorthTo() : errorStr);
                                                        stringBuilder.append(",");
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%d%s%s", (j + 1), "东至,南至,西至,北至:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%d%s%s", (j + 1), "东至,南至,西至,北至:", "无"));
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandArea())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "土地面积:", generateBaseExamineService.getBasicEstateLandState().getLandArea()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "土地面积:", "无"));
                                                    }
                                                    break;
                                                case 3:
                                                    //用途
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(10);
                                                        if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName())) {
                                                            stringBuilder.append("土地类型:");
                                                            stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName()) ? generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName() : "无");
                                                            stringBuilder.append(";");
                                                            stringBuilder.append("土地级别:");
                                                            stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandLevelName()) ? generateBaseExamineService.getBasicEstateLandState().getLandLevelName() : "无");
                                                        }
                                                        if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                            builder.writeln(String.format("%d%s%s", (j + 1), "用途:", stringBuilder.toString()));
                                                        } else {
                                                            builder.writeln(String.format("%d%s%s", (j + 1), "用途:", "无"));
                                                        }
                                                    }
                                                    break;
                                                case 4:
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getShapeStateName())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "形状:", generateBaseExamineService.getBasicEstateLandState().getShapeStateName()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "形状:", "无"));
                                                    }
                                                    break;
                                                case 5:
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getPlanenessName())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "地形:", generateBaseExamineService.getBasicEstateLandState().getPlanenessName()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "地形:", "无"));
                                                    }
                                                    break;
                                                case 6:
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "地势:", generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "地势:", "无"));
                                                    }
                                                    break;
                                                case 7:
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "地质:", "无"));
                                                    break;
                                                case 8:
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "开发程度:", generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "开发程度:", "无"));
                                                    }
                                                    break;
                                                case 9:
                                                    builder.writeln(String.format("%s%s", "综上所述:", "无"));
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        builder.writeln();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象建筑实体状况表
     *
     * @return
     */
    public String getJudgeBuildLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        String localPath = getLocalPath();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        builder.writeln("估价对象建筑实体状况表");
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(projectId);
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null) {
                                    for (int j = 0; j < 13; j++) {
                                        switch (j) {
                                            case 0:
                                                //楼盘名称
                                                String name = "";
                                                if (generateBaseExamineService.getEstate().getId() != null) {
                                                    name = String.format("%s", generateBaseExamineService.getEstate().getName());
                                                }
                                                if (StringUtils.isNotBlank(name)) {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "楼盘名称:", name));
                                                } else {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "楼盘名称:", "无"));
                                                }
                                                break;
                                            case 1:
                                                String buildingStructure = "";
                                                if (generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory() != null) {
                                                    buildingStructure = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                                }
                                                if (StringUtils.isNotBlank(buildingStructure)) {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑结构:", buildingStructure));
                                                } else {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑结构:", "无"));
                                                }
                                                break;
                                            case 2:
                                                builder.writeln(String.format("%d%s%s", (j + 1), "建筑规模:", "无"));
                                                break;
                                            case 3:
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    List<BasicHouseEquipment> equipmentList = generateBaseExamineService.getBasicHouseEquipmentList();
                                                    if (CollectionUtils.isNotEmpty(equipmentList)) {
                                                        for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                                            if (org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()) ||
                                                                    org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                                                                stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                                                stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("；");
                                                            }
                                                        }
                                                    }
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "设施设备:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "设施设备:", "无"));
                                                    }
                                                }
                                                break;
                                            case 4:
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    List<BasicUnitDecorate> decorateList = generateBaseExamineService.getBasicUnitDecorateList();
                                                    if (CollectionUtils.isNotEmpty(decorateList)) {
                                                        for (BasicUnitDecorate unitDecorate : decorateList) {
                                                            stringBuilder.append(unitDecorate.getDecorationPart() == null ? errorStr : String.format("装修部位:%s；", baseDataDicService.getNameById(unitDecorate.getDecorationPart())));
                                                            stringBuilder.append(unitDecorate.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(unitDecorate.getDecoratingMaterial())));
                                                            stringBuilder.append(unitDecorate.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(unitDecorate.getConstructionTechnology())));
                                                            stringBuilder.append(unitDecorate.getMaterialPriceRange() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(unitDecorate.getMaterialPriceRange())));
                                                        }
                                                    }
                                                    stringBuilder.append("室内净高和层高");
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorHeight()));
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getNetHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getNetHeight()));
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "装饰装修:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "装饰装修:", "无"));
                                                    }
                                                }
                                                break;
                                            case 5:
                                                String storeyHeight = "";
                                                if (generateBaseExamineService.getBasicBuilding().getId() != null && generateBaseExamineService.getBasicBuilding() != null) {
                                                    storeyHeight = String.format("%s", generateBaseExamineService.getBasicBuilding().getFloorHeight().toString());
                                                }
                                                if (StringUtils.isNotBlank(storeyHeight)) {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "层高:", storeyHeight));
                                                } else {
                                                    builder.writeln(String.format("%d%s%s", (j + 1), "层高:", "无"));
                                                }
                                                break;
                                            case 6:
                                                //空间布局
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    BaseDataDic practicalUseDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE_HOUSE);
                                                    if (practicalUseDic != null) {
                                                        String huxing = org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicHouse().getNewHuxingName()) ? generateBaseExamineService.getBasicHouse().getHuxingName() : generateBaseExamineService.getBasicHouse().getNewHuxingName();
                                                        if (StringUtils.isNotBlank(huxing)) {
                                                            stringBuilder.append(huxing).append("；");
                                                        }
                                                        if (!practicalUseDic.getId().equals(generateBaseExamineService.getBasicHouse().getPracticalUse())) {
                                                            List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
                                                            if (CollectionUtils.isNotEmpty(roomList)) {
                                                                for (BasicHouseRoom room : roomList) {
                                                                    stringBuilder.append(baseDataDicService.getNameById(room.getRoomType()))
                                                                            .append(String.format("开间:%s米；", room.getOpening())).append(String.format("进深:%s米；", room.getDepth())).append("；");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "空间布局:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "空间布局:", "无"));
                                                    }
                                                }
                                                break;
                                            case 7:
                                                //建筑功能-防水、保温、隔热、隔声、通风、采光、日照
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
                                                    if (CollectionUtils.isNotEmpty(functionList)) {
                                                        functionList.stream().forEach(basicBuildingFunction -> {
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getType()));
                                                            stringBuilder.append(",");
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getDecorationPart()));
                                                        });
                                                    }
                                                    List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
                                                    if (CollectionUtils.isNotEmpty(roomList)) {
                                                        for (BasicHouseRoom room : roomList) {
                                                            //日照
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getSunshine()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getSunshine()));
                                                            //采光
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getLighting()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getLighting()));
                                                            //通风
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getAeration()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getAeration()));
                                                            //隔音
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getSoundInsulation()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getSoundInsulation()));
                                                        }
                                                        //保温
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, heatInsulationDic);
                                                        }
                                                        //隔热
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, heatInsulationDic);
                                                        }
                                                        //防水
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, waterproofDic);
                                                        }
                                                    }
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "建筑功能:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "建筑功能:", "无"));
                                                    }
                                                }
                                                break;
                                            case 8:
                                                builder.writeln(String.format("%d%s%s", (j + 1), "工程质量:", "无"));
                                                break;
                                            case 9:
                                                //外观
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    List<BasicBuildingOutfit> outfitList = generateBaseExamineService.getBasicBuildingOutfitList();
                                                    if (CollectionUtils.isNotEmpty(outfitList)) {
                                                        for (BasicBuildingOutfit outfit : outfitList) {
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(outfit.getDecorationPart()) ? errorStr : String.format("装修部位:%s；", outfit.getDecorationPart()));
                                                            stringBuilder.append(outfit.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(outfit.getDecoratingMaterial())));
                                                            stringBuilder.append(outfit.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(outfit.getConstructionTechnology())));
                                                            stringBuilder.append(outfit.getMaterialPrice() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(outfit.getMaterialPrice())));
                                                        }
                                                    }
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "外观:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "外观:", "无"));
                                                    }
                                                }
                                                break;
                                            case 10:
                                                //新旧程度及维护使用情况
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(16);
                                                    List<BasicUnitElevator> elevatorList = generateBaseExamineService.getBasicUnitElevatorList();
                                                    if (CollectionUtils.isNotEmpty(elevatorList)) {
                                                        for (BasicUnitElevator elevator : elevatorList) {
                                                            stringBuilder.append(elevator.getMaintenance() == null ? errorStr : String.format("电梯维护情况:%s；", baseDataDicService.getNameById(elevator.getMaintenance())));
                                                            stringBuilder.append(elevator.getType() == null ? errorStr : String.format("电梯类型:%s；", baseDataDicService.getNameById(elevator.getType())));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getBrand()) ? errorStr : String.format("电梯品牌:%s；", elevator.getBrand()));
                                                            stringBuilder.append(elevator.getNumber() == null ? errorStr : String.format("电梯数量:%s；", elevator.getNumber()));
                                                            stringBuilder.append(elevator.getQuasiLoadNumber() == null ? errorStr : String.format("准载人数:%s；", elevator.getQuasiLoadNumber()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getQuasiLoadWeight()) ? "" : String.format("准载重量:%s；", elevator.getQuasiLoadWeight()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getRunningSpeed()) ? "" : String.format("运行速度:%s；", elevator.getRunningSpeed()));
                                                        }
                                                    }
                                                    List<BasicHouseCorollaryEquipment> corollaryEquipmentList = generateBaseExamineService.getBasicHouseCorollaryEquipmentList();
                                                    if (CollectionUtils.isNotEmpty(corollaryEquipmentList)) {
                                                        for (BasicHouseCorollaryEquipment equipment : corollaryEquipmentList) {
                                                            stringBuilder.append(equipment.getType() == null ? errorStr : String.format("类型:%s；", baseDataDicService.getNameById(equipment.getType())));
                                                            stringBuilder.append(equipment.getCategory() == null ? errorStr : String.format("类别:%s；", baseDataDicService.getNameById(equipment.getCategory())));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getName()) ? errorStr : String.format("名称:%s；", equipment.getName()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getParameterIndex()) ? errorStr : String.format("参数指标:%s；", equipment.getParameterIndex()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getMaintenanceStatus()) ? errorStr : String.format("维护状况:%s；", equipment.getMaintenanceStatus()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getEquipmentUse()) ? errorStr : String.format("设备用途:%s；", equipment.getEquipmentUse()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getPrice()) ? errorStr : String.format("价格:%s；", equipment.getPrice()));
                                                        }
                                                    }
                                                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "新旧程度及维护使用情况:", stringBuilder.toString()));
                                                    } else {
                                                        builder.writeln(String.format("%d%s%s", (j + 1), "新旧程度及维护使用情况:", "无"));
                                                    }
                                                }
                                                break;
                                            case 11:
                                                builder.writeln(String.format("%d%s%s", (j + 1), "物业管理:", "较好"));
                                                break;
                                            case 12:
                                                builder.writeln(String.format("%d%s%s", (j + 1), "建筑实体分析:", "无"));
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    builder.writeln();
                                }
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价结果汇总表
     *
     * @return
     * @throws Exception
     */
    public String getJudgeSummarySheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(doc);
        builder.writeln("汇总表");
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        builder.writeln("估价结果汇总表");
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        for (int j = 0; j < 2; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("估价对象及结果\\估价方法及结果");
                                mergeCellModelList.add(new MergeCellModel(0, 0, 1, 2));
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("测算结果");
                                mergeCellModelList.add(new MergeCellModel(0, 3, 0, 5));
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("估价结果");
                                //未处理
                                mergeCellModelList.add(new MergeCellModel(j, 6, j + 1, 7));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 3:
                                builder.insertCell();
                                builder.writeln("市场比较法");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("收益法");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("");
                                break;
                            case 6:
                                builder.insertCell();
                                break;

                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        BigDecimal totolCompare = new BigDecimal(0);
        BigDecimal priceCompare = new BigDecimal(0);
        BigDecimal totolIncome = new BigDecimal(0);
        BigDecimal priceIncome = new BigDecimal(0);
        for (int j = 2; j < 2 + schemeJudgeObjectList.size(); j++) {
            MdMarketCompare mdMarketCompare = null;
            MdIncome mdIncome = null;
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObjectList.get(j - 2));
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    mdMarketCompare = mdMarketCompareService.getMdMarketCompare(schemeInfo.getMethodDataId());
                }
            }
            if (true) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObjectList.get(j - 2));
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    mdIncome = mdIncomeService.getIncomeById(schemeInfo.getMethodDataId());
                }
            }
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(j - 2)));
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdMarketCompare.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolCompare = totolCompare.add(temp);
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdIncome.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolIncome = totolIncome.add(temp);
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.writeln("单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null) {
                                        builder.writeln(mdMarketCompare.getPrice().toString());
                                        priceCompare = priceCompare.add(mdMarketCompare.getPrice());
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null) {
                                        builder.writeln(mdIncome.getPrice().toString());
                                        priceIncome = priceIncome.add(mdIncome.getPrice());
                                    }
                                } else {
                                    builder.writeln("");
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        for (int j = 2 + schemeJudgeObjectList.size(); j < 2 + schemeJudgeObjectList.size() + 2; j++) {
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.writeln("汇总平均价值");
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(totolCompare.toString());
                                break;
                            case 4:
                                builder.writeln(totolIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.writeln("平均单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(priceCompare.toString());
                                break;
                            case 4:
                                builder.writeln(priceIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 收益法租赁限制说明
     *
     * @return
     * @throws Exception
     */
    public String getTenancyrestrictionRemark() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                SchemeInfo schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdIncome, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObject), generateMdIncomeService.getTenancyrestrictionReamrk()));
                }
            }
        }
        return generateCommonMethod.toSetString(stringSet);
    }

    /**
     * 功能描述: 估价对象详细测算过程
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/4 10:30
     */
    public String getDetailedCalculationProcessValuationObject() throws Exception {
        String localPath = getLocalPath();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        Map<String, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                builder.writeln(getSchemeJudgeObjectShowName(schemeJudgeObject));
                SchemeInfo schemeInfo = null;
                //市场比较法
                builder.writeln(CalculationMethodNameEnum.MdCompare.getName());
                schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeInfo.getMethodDataId(), new Date(), areaId);
                    try {
                        String temp = generateMdCompareService.generateCompareFile();
                        File file = new File(temp);
                        if (file.isFile()) {
                            String key = String.format("%s%s%s", getSchemeJudgeObjectShowName(schemeJudgeObject), CalculationMethodNameEnum.MdCompare.getName(), UUID.randomUUID().toString());
                            map.put(key, temp);
                            builder.writeln(key);
                        }
                    } catch (Exception e) {
                    }
                }
                //收益法
                builder.writeln(CalculationMethodNameEnum.MdIncome.getName());
                schemeInfo = getSchemeInfoId(CalculationMethodNameEnum.MdIncome, schemeJudgeObject);
                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                    GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfo, projectId, areaId);
                    String temp = generateMdIncomeService.generateCompareFile();
                    File file = new File(temp);
                    if (file.isFile()) {
                        String key = String.format("%s%s%s", getSchemeJudgeObjectShowName(schemeJudgeObject), CalculationMethodNameEnum.MdIncome.getName(), UUID.randomUUID().toString());
                        map.put(key, temp);
                        builder.writeln(key);
                    }
                }
            }
        }
        document.save(localPath, SaveFormat.DOC);
        if (!map.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, map);
        }
        return localPath;
    }


    /**
     * 估价委托书复印件
     *
     * @return
     * @throws Exception
     */
    public String getJUDGEOBJECTPRINCIPALCOPYSHEET() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        SysAttachmentDto sysAttachmentDto = schemeReportFileService.getProjectProxyFileList(projectId);
        if (sysAttachmentDto != null) {
            String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
            List<String> images = Lists.newArrayList();
            if (FileUtils.checkImgSuffix(imgPath)) {
                images.add(imgPath);
            }
            if (CollectionUtils.isNotEmpty(images)) {
                AsposeUtils.insertImage(localPath, images, 200, 100);
            }
        }
        return localPath;
    }

    /**
     * 估计对象位置示意图
     *
     * @return
     */
    public String getEstimatedObjectLocationDiagram() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getJudgeObjectPositionFileList(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价对象实况照片
     *
     * @return
     */
    public String getValuation_Target_Live_Photos() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeJudgeObject> schemeJudgeObjectFullList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(this.areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectFullList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectFullList) {
                List<SchemeReportFileItem> sysAttachmentDtoList = schemeReportFileService.getLiveSituationSelect(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SchemeReportFileItem sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getAttachmentId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }


    /**
     * 估价对象权属证明复印件
     *
     * @return
     */
    public String getCopies_the_Ownership_Certificate_the_Valuation_Object() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<Integer, List<SysAttachmentDto>> ownershipCertFileList = schemeReportFileService.getOwnershipCertFileList(areaId);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SysAttachmentDto> sysAttachmentDtoList = ownershipCertFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价中引用的专用文件资料
     *
     * @return
     * @throws Exception
     */
    public String getSpecial_documentation_referenced_in_valuation() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Map<Integer, List<SysAttachmentDto>> inventoryAddressFileList = schemeReportFileService.getInventoryAddressFileList(areaId);
            Map<Integer, List<SysAttachmentDto>> reimbursementFileList = schemeReportFileService.getReimbursementFileList(areaId);
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                //1.先取地址不一致附件与法定优先受偿款附件
                List<SysAttachmentDto> addressFileList = inventoryAddressFileList.get(schemeJudgeObject.getId());
                List<SysAttachmentDto> reimFileList = reimbursementFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isEmpty(addressFileList) && CollectionUtils.isEmpty(reimFileList)) continue;
                builder.writeln(schemeJudgeObject.getName());
                if (CollectionUtils.isNotEmpty(addressFileList)) {
                    builder.writeln("地址不一致附件");
                    for (SysAttachmentDto sysAttachmentDto : addressFileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
                if (CollectionUtils.isNotEmpty(reimFileList)) {
                    builder.writeln("法定优先受偿款附件");
                    for (SysAttachmentDto sysAttachmentDto : reimFileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        //2.取得自定义的附件
        List<SchemeReportFileCustom> reportFileCustomList = schemeReportFileService.getReportFileCustomList(areaId);
        if (CollectionUtils.isNotEmpty(reportFileCustomList)) {
            for (SchemeReportFileCustom schemeReportFileCustom : reportFileCustomList) {
                List<SysAttachmentDto> fileList = schemeReportFileService.getCustomFileList(schemeReportFileCustom.getId());
                if (CollectionUtils.isNotEmpty(fileList)) {
                    builder.writeln(schemeReportFileCustom.getName());
                    for (SysAttachmentDto sysAttachmentDto : fileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        generateCommonMethod.builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 房地产估价机构营业执照复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyBusinessLicenseRealEstateValuationAgency() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForLicense();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * '房地产估价机构资质证书复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     *
     * @param str
     * @return
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerValuationInstitution(String str) throws Exception {
        String localPath = getLocalPath();
        new Document().save(localPath);
        List<String> images = Lists.newArrayList();
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            adPersonalQualificationDtoList.stream().forEach(adCompanyQualificationDto -> {
                                if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                                    List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                                    if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                                        for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                                            try {
                                                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                                                if (StringUtils.isNotBlank(imgPath)) {
                                                    if (FileUtils.checkImgSuffix(imgPath)) {
                                                        images.add(imgPath);
                                                    }
                                                }
                                            } catch (Exception e1) {
                                            }
                                        }
                                    }

                                }
                            });
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(images)) {
            AsposeUtils.insertImage(localPath, images, 200, 100);
        }
        return localPath;
    }


    /**
     * 获取如收益法,市场比较法，假设开发法，成本法等的id
     *
     * @param methodNameEnum
     * @param schemeJudgeObject
     * @return SchemeInfo
     */
    private SchemeInfo getSchemeInfoId(CalculationMethodNameEnum methodNameEnum, SchemeJudgeObject schemeJudgeObject) {
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(oo -> {
            if (oo.getName().equals(methodNameEnum.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (dataEvaluationMethod != null) {
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), dataEvaluationMethod.getMethod());
            if (schemeInfo != null) {
                return schemeInfo;
            }
        }
        return null;
    }

    /**
     * 他权信息公示
     *
     * @return
     * @throws Exception
     */
    public String getHisRightInfoPublicity() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        DataHisRightInfoPublicity dataHisRightInfoPublicity = dataHisRightInfoPublicityService.getDataHisRightInfoPublicity(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
        if (dataHisRightInfoPublicity != null) {
            return dataHisRightInfoPublicity.getContent();
        }
        return " ";
    }

    /**
     * 功能描述: 申报所启用表单类型
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/25 10:09
     */
    public String getTypesFormEnabledDeclarationOffice() throws Exception {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        List<DeclareRealtyLandCert> declareRealtyLandCertList = Lists.newArrayList();
        List<DeclareRealtyHouseCert> declareRealtyHouseCertList = Lists.newArrayList();
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCertList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            List<DeclareRecord> declareRecords = declareRecordList.stream().filter(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    return true;
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    return true;
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                declareRecords.stream().forEach(declareRecord -> {
                    if (declareRecord.getDataTableId() != null) {
                        DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                        if (declareRealtyLandCert != null) {
                            declareRealtyLandCertList.add(declareRealtyLandCert);
                        }
                        DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                        if (declareRealtyHouseCert != null) {
                            declareRealtyHouseCertList.add(declareRealtyHouseCert);
                        }
                        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                        if (declareRealtyRealEstateCert != null) {
                            declareRealtyRealEstateCertList.add(declareRealtyRealEstateCert);
                        }
                    }
                });
            }
        }
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.HOUSE.getKey()));
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.LAND.getKey()));
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
            stringSet.add(String.format("《%s》", DeclareTypeEnum.RealEstate.getKey()));
        }
        String s = generateCommonMethod.toSetString2(stringSet);
        return s;
    }


    //------------------------------------------------------------||待删除方法 start -------------------------------------------------------//

    /**
     * 区位(2019-01-28 修改之后)
     *
     * @return
     * @throws Exception
     */
    @Deprecated
    public String getLocation_() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        try {
            SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                builder.append(getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(0))).append(":");
                builder.append(schemeJudgeObjectList.get(0).getSeat());
                if (schemeJudgeObjectList.size() > 1) {
                    builder.append("等");
                }
            }
        } catch (Exception e) {
            logger.error("(区位)拼接异常!");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }


    /**
     * 土地他项权利
     *
     * @return
     */
    @Deprecated
    public String getInventoryRight() throws Exception {
        return getHisRightType();
    }

    /**
     * 土地使用管制
     *
     * @return
     */
    @Deprecated
    public String getLandUseControl() throws Exception {
        String s = getSeparationCertificateUses();
        return s;
    }

    /**
     * 估价技术思路
     *
     * @return
     */
    @Deprecated
    public String getEvaluationThink() {
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject));
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getThinking())) {
                            builder.append("-思路:").append(schemeJudgeFunction.getThinking());
                            builder.append(schemeJudgeFunction.getThinking());
                        }
                    }
                }
                stringSet.add(builder.toString());
                builder.delete(0, builder.toString().length());
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 评估方法总括
     *
     * @return
     */
    @Deprecated
    public String getSummaryEvaluationMethod() {
        StringBuilder builder = new StringBuilder(128);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject));
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                builder.append(":").append(schemeJudgeFunctionList.get(0).getName());
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return " ";
    }

    /**
     * 评估方法分述
     *
     * @return
     */
    @Deprecated
    public String getAssessmentMethods() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            StringBuilder builder = new StringBuilder(24);
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(getSchemeJudgeObjectShowName(schemeJudgeObject)).append(":");
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        builder.append("评估方法-").append(schemeJudgeFunction.getName());
                    }
                }
                stringSet.add(builder.toString());
                builder.delete(0, builder.toString().length());
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 价值表达结果
     *
     * @return
     */
    @Deprecated
    public String getValueExpressionResult() {
        return "抵押价值特殊处理";
    }

    /**
     * 分类评估面积
     *
     * @return
     */
    @Deprecated
    public String getEvaluationAreaCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getEvaluationArea().toString())) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeJudgeObjectList.get(i).getEvaluationArea().toString()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 功能描述: 估价对象的单价
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 17:15
     */
    @Deprecated
    public String getUnitPriceValuator() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    stringBuilder.append(schemeJudgeObject.getName()).append("的单价=").append(getEvaluationExpression());
                    for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                                stringBuilder.append("+");
                            }
                        }
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 分类评估单价
     *
     * @return
     */
    @Deprecated
    public String getEvaluationPriceCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPrice().toString())) {
                        stringSet.add(String.format("%s:%s", getSchemeJudgeObjectShowName(schemeJudgeObjectList.get(i)), schemeJudgeObjectList.get(i).getPrice().toString()));
                    }
                }
            }
        }
        String s = generateCommonMethod.toSetString(stringSet);
        return s;
    }

    /**
     * 证载用途总括
     *
     * @return
     * @throws Exception
     */
    @Deprecated
    public String getSummaryCertificateUses() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            if (StringUtils.isNotBlank(schemeJudgeObjectList.get(0).getCertUse())) {
                builder.append(schemeJudgeObjectList.get(0).getCertUse());
            }
            if (schemeJudgeObjectList.size() > 1) {
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return "";
    }


    //评估面积
    @Deprecated
    public String getAssessArea() {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null) {
                    stringSet.add(String.format("%s:%s", schemeJudgeObject.getName(), schemeJudgeObject.getEvaluationArea().toString()));
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 委托人地址
     *
     * @return
     */
    @Deprecated
    public String getPrincipalAddress() {
        String str = projectInfo.getConsignorVo().getCsAddress();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return errorStr;
        }
    }

    /**
     * 委托人法定代表人
     *
     * @return
     */
    @Deprecated
    public String getPrincipalLegalRepresentative() {
        String str = projectInfo.getConsignorVo().getCsLegalRepresentative();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return errorStr;
        }
    }


    //------------------------------------------------------------||待删除方法 end -------------------------------------------------------//


    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(ProjectInfoVo projectInfoVo, Integer areaId, BaseReportTemplate baseReportTemplate, ProjectPlan projectPlan) {
        this.projectId = projectInfoVo.getId();
        this.projectInfo = projectInfoVo;
        this.areaId = areaId;
        this.baseReportTemplate = baseReportTemplate;
        //注入bean
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.projectNumberRecordService = SpringContextUtils.getBean(ProjectNumberRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeJudgeFunctionService = SpringContextUtils.getBean(SchemeJudgeFunctionService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.projectPhaseService = SpringContextUtils.getBean(ProjectPhaseService.class);
        this.surveyAssetInventoryRightService = SpringContextUtils.getBean(SurveyAssetInventoryRightService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.surveyAssetInventoryDao = SpringContextUtils.getBean(SurveyAssetInventoryDao.class);
        this.schemeSurePriceService = SpringContextUtils.getBean(SchemeSurePriceService.class);
        this.schemeReimbursementService = SpringContextUtils.getBean(SchemeReimbursementService.class);
        this.publicService = SpringContextUtils.getBean(PublicService.class);
        this.schemeSupportInfoService = SpringContextUtils.getBean(SchemeSupportInfoService.class);
        this.adRpcQualificationsService = SpringContextUtils.getBean(com.copower.pmcc.assess.service.AdRpcQualificationsAppService.class);
        this.compileReportService = SpringContextUtils.getBean(CompileReportService.class);
        this.schemeReportFileService = SpringContextUtils.getBean(SchemeReportFileService.class);
        this.dataQualificationService = SpringContextUtils.getBean(DataQualificationService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.evaluationMethodService = SpringContextUtils.getBean(EvaluationMethodService.class);
        this.schemeLiquidationAnalysisService = SpringContextUtils.getBean(SchemeLiquidationAnalysisService.class);
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.declareRealtyHouseCertService = SpringContextUtils.getBean(DeclareRealtyHouseCertService.class);
        this.declareRealtyRealEstateCertService = SpringContextUtils.getBean(DeclareRealtyRealEstateCertService.class);
        this.dataHisRightInfoPublicityService = SpringContextUtils.getBean(DataHisRightInfoPublicityService.class);
        this.surveyAssetInventoryContentService = SpringContextUtils.getBean(SurveyAssetInventoryContentService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);

        //必须在bean之后
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(areaId);
        if (areaGroup == null) {
            areaGroup = new SchemeAreaGroup();
        }
        this.schemeAreaGroup = areaGroup;
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            judgeObjectList = new ArrayList<SchemeJudgeObject>(0);
        }
        this.schemeJudgeObjectList = judgeObjectList;
    }


    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }

    private String getLocalPath(String title) {
        return generateCommonMethod.getLocalPath(title);
    }

    /**
     * 功能描述: 委估对象名称显示
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/1 10:34
     */
    private String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject) {
        return generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject);
    }

    /**
     * 功能描述: 设置默认字体
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/1 14:32
     */
    private DocumentBuilder getDefaultDocumentBuilderSetting(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        AsposeUtils.setDefaultFontSettings(builder);
        return builder;
    }


    /**
     * 获取区域信息(组)
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        return schemeAreaGroup;
    }

    /**
     * 委估对象列表
     *
     * @return
     */
    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        return this.schemeJudgeObjectList;
    }

    public GenerateBaseExamineService getGenerateBaseExamineService(Integer planDetailsId) {
        return new GenerateBaseExamineService(planDetailsId);
    }
}
