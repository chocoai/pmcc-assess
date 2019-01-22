package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseFaceStreetVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEnvironmentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingFinanceVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String errorStr = "暂无数据";

    //spring bean
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private ProjectNumberRecordService projectNumberRecordService;
    private BaseReportService baseReportService;
    private ProjectInfoService projectInfoService;
    private BaseDataDicService baseDataDicService;
    private BaseAttachmentService baseAttachmentService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private DataSetUseFieldService dataSetUseFieldService;
    private ProjectPhaseService projectPhaseService;
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;

    //构造器必须传入的参数
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;
    private ProjectPlan projectPlan;

    //中间变量
    private SchemeAreaGroup schemeAreaGroup;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo;
    List<SchemeJudgeObject> schemeJudgeObjectList;

    //===========================================获取的值===============================
    //文号
    private String wordNumber;
    //委托人
    private String principal;
    //评估面积
    private BigDecimal assessArea = null;
    //评估方法
    private String evaluationMethod;
    //委托目的表述
    private String statementPurposeEntrustment;
    //价值类型
    private String valueType;
    //价值定义
    private String definitionValue;
    //价值含义
    private String valueImplication;
    //权利人(区位)
    private String powerPerson;
    //非权利人(区位)
    private String notPowerPerson;
    //设定用途
    private String setUse;
    //土地实际用途
    private String landPracticalUse;
    //使用权类型
    private String useRightType;

    //估价对象区位状况表
    private String judgeObjectAreaStatusSheet;

    //估价土地实体状况表
    private String judgeObjectLandStateSheet;

    //估价对象建筑实体状况表
    private String judgeBuildLandStateSheet;


    /**
     * 获取区域信息(组)
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        if (schemeAreaGroup != null) {
            return schemeAreaGroup;
        }
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(getAreaId());
        if (areaGroup == null) {
            areaGroup = new SchemeAreaGroup();
        }
        this.schemeAreaGroup = areaGroup;
        return schemeAreaGroup;
    }

    /**
     * 获取文号
     *
     * @return
     */
    public String getWordNumber() {
        if (StringUtils.isEmpty(wordNumber)) {
            try {
                String number = projectNumberRecordService.getReportNumber(this.getProjectId(), areaId, this.getBaseReportTemplate().getReportType());
                this.wordNumber = number;
            } catch (BusinessException e) {
                logger.error("获取文号异常", e);
            }

        }
        if (StringUtils.isNotBlank(this.wordNumber)) {
            return wordNumber;
        } else {
            return errorStr;
        }
    }

    public BaseReportTemplate getBaseReportTemplate() {
        if (baseReportTemplate == null) {
            BaseReportTemplate reportTemplate = baseReportService.getBaseReportTemplateById(baseReportTemplateId);
            this.baseReportTemplate = reportTemplate;
        }
        return baseReportTemplate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public ProjectPlan getProjectPlan() {
        return projectPlan;
    }

    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 委托人
     *
     * @return
     */
    public String getPrincipal() {
        if (StringUtils.isBlank(principal)) {
            String principalStr = StringUtils.isNotBlank(getProjectInfo().getConsignorVo().getCsName()) ? getProjectInfo().getConsignorVo().getCsName() : getProjectInfo().getConsignorVo().getCsEntrustmentUnit();
            this.principal = principalStr;
        }
        if (StringUtils.isNotBlank(this.principal)) {
            return principal;
        } else {
            return errorStr;
        }
    }

    /**
     * 获取项目info
     *
     * @return
     */
    public ProjectInfoVo getProjectInfo() {
        if (projectInfo == null) {
            ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(getProjectId()));
            this.projectInfo = projectInfoVo;
        }
        return projectInfo;
    }

    public BigDecimal getAssessArea() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            double temp = 0.0;
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                temp += schemeJudgeObject.getEvaluationArea().doubleValue();
            }
            assessArea = new BigDecimal(temp);
        } else {
            return new BigDecimal(0.0d);
        }
        return assessArea;
    }

    /**
     * 评估方法
     *
     * @return
     */
    public String getEvaluationMethod() {
        StringBuilder builder = new StringBuilder("");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    builder.append(schemeJudgeFunction.getName());
                }
            }
        }
        this.evaluationMethod = moreJudgeObject(builder.toString(), builder.toString());
        if (StringUtils.isNotBlank(this.evaluationMethod)) {
            return evaluationMethod;
        } else {
            return errorStr;
        }
    }

    /**
     * 委托目的表述
     *
     * @return
     */
    public String getStatementPurposeEntrustment() {
        if (getProjectInfo() != null) {
            this.statementPurposeEntrustment = getProjectInfo().getRemarkEntrustPurpose();
        }
        if (StringUtils.isNotBlank(this.statementPurposeEntrustment)) {
            return statementPurposeEntrustment;
        } else {
            return errorStr;
        }
    }

    /**
     * get 价值类型
     *
     * @return
     */
    public String getValueType() {
        if (getSchemeAreaGroup() != null) {
            this.valueType = baseDataDicService.getNameById(getSchemeAreaGroup().getValueDefinition());
        }
        if (StringUtils.isNotBlank(this.valueType)) {
            return valueType;
        } else {
            return errorStr;
        }
    }

    /**
     * get 价值定义
     *
     * @return
     */
    public String getDefinitionValue() {
        if (getSchemeAreaGroup() != null) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(getSchemeAreaGroup().getValueDefinition());
            if (baseDataDic != null) {
                this.definitionValue = baseDataDic.getRemark();
            }
        }
        if (StringUtils.isNotBlank(this.definitionValue)) {
            return definitionValue;
        } else {
            return errorStr;
        }
    }

    /**
     * get 价值含义
     *
     * @return
     */
    public String getValueImplication() {
        String temp = String.format("%s%s%s", getValueType(), getDefinitionValue(), getSchemeAreaGroup().getValueConnotation());
        this.valueImplication = temp;
        if (StringUtils.isNotBlank(this.valueImplication)) {
            return valueImplication;
        } else {
            return errorStr;
        }
    }

    public String getPowerPerson() {
        StringBuilder builder = new StringBuilder(1024);
        try {
            SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                String text = String.format("%s%s", schemeJudgeObjectList.get(0).getSeat(), "");
                Matcher m = Pattern.compile("[\\d]+号").matcher(text);
                while (m.find()) {
                    builder.append(text.substring(0, text.lastIndexOf(m.group())));
                }
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                    m = Pattern.compile("[\\d]+号").matcher(schemeJudgeObject.getSeat());
                    while (m.find()) {
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(m.group());
                        } else {
                            builder.append(m.group()).append(",");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("权利人(区位)拼接异常!");
        }
        this.powerPerson = builder.toString();
        if (StringUtils.isNotBlank(this.powerPerson)) {
            return powerPerson;
        } else {
            return errorStr;
        }
    }

    public String getNotPowerPerson() {
        StringBuilder builder = new StringBuilder(1024);
        try {
            SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                String text = String.format("%s%s", schemeJudgeObjectList.get(0).getSeat(), "");
                Matcher m = Pattern.compile("[\\d]+楼[\\d]+号").matcher(text);
                while (m.find()) {
                    builder.append(text.substring(0, text.lastIndexOf(m.group())));
                }
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                    m = Pattern.compile("[\\d]+楼[\\d]+号").matcher(schemeJudgeObject.getSeat());
                    while (m.find()) {
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(m.group());
                        } else {
                            builder.append(m.group()).append(",");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("不是权利人(区位)拼接异常!");
        }
        this.notPowerPerson = builder.toString();
        if (StringUtils.isNotBlank(this.notPowerPerson)) {
            return notPowerPerson;
        } else {
            return errorStr;
        }
    }

    /**
     * 设定用途
     *
     * @return
     */
    public String getSetUse() {
        StringBuilder stringBuilder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null) {
                        stringBuilder.append(dataSetUseField.getName());
                    }
                }
            }
            this.setUse = moreJudgeObject(stringBuilder.toString(), stringBuilder.toString());
        }
        if (StringUtils.isNotBlank(this.setUse)) {
            return setUse;
        } else {
            return errorStr;
        }
    }

    /**
     * 多个委估对象 显示
     *
     * @param str
     * @return
     */
    public String moreJudgeObject(String str, String s) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(s)) {
            return "";
        } else {
            return String.format("1,3号委估对象%s2号委估对象%s", str, s);
        }
    }

    /**
     * 土地实际用途
     *
     * @return
     */
    public String getLandPracticalUse() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.parallelStream().forEach(schemeJudgeObject -> {
                if (NumberUtils.isNumber(schemeJudgeObject.getCertUse())) {
                    builder.append(baseDataDicService.getNameById(schemeJudgeObject.getCertUse()));
                } else {
                    builder.append(schemeJudgeObject.getCertUse());
                }
            });
            this.landPracticalUse = moreJudgeObject(builder.toString(), builder.toString());
        }
        if (StringUtils.isNotBlank(this.landPracticalUse)) {
            return landPracticalUse;
        } else {
            return errorStr;
        }
    }

    /**
     * 使用权类型
     *
     * @return
     */
    public String getUseRightType() {
        this.useRightType = getLandPracticalUse();
        if (StringUtils.isNotBlank(this.useRightType)) {
            return useRightType;
        } else {
            return errorStr;
        }
    }

    /**
     * 土地他项权利
     *
     * @return
     */
    public String getInventoryRight() {
        try {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(null);
        } catch (Exception e1) {

        }
        return errorStr;
    }

    /**
     * 估价对象区位状况表
     *
     * @return
     */
    public String getJudgeObjectAreaStatusSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                        List<DataSetUseField> setUseFields = Lists.newArrayList();
                        if (dataSetUseField != null) {
                            setUseFields.add(dataSetUseField);
                        }
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        builder.writeln(schemeJudgeObject.getName());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 20; j++) {
                            if (0 <= j && j <= 5) {
                                //列
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 0) {
                                        builder.insertCell();
                                        builder.writeln("区域位置");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 0:
                                                    builder.writeln("座落");
                                                    break;
                                                case 1:
                                                    builder.writeln("方位");
                                                    break;
                                                case 2:
                                                    builder.writeln("商业繁华程度");
                                                    break;
                                                case 3:
                                                    builder.writeln("临街（路状况）");
                                                    break;
                                                case 4:
                                                    builder.writeln("楼层");
                                                    break;
                                                case 5:
                                                    builder.writeln("人文景观");
                                                    break;
                                                default:
                                                    builder.writeln(String.format("%d-%d", j, k));
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 0:
                                                    builder.writeln(String.format("%s%s", getSchemeAreaGroup().getAreaName(), schemeJudgeObject.getSeat()));
                                                    break;
                                                case 1:
                                                    builder.writeln(StringUtils.isNotBlank(generateBaseExamineService.getByDataBlock().getPosition()) ? generateBaseExamineService.getByDataBlock().getPosition() : "方位：暂无");
                                                    break;
                                                case 2:
                                                    builder.writeln("估价对象所在区域为规划新城区，区域商业待发展，目前以超市、零售商店为主，商业繁华度一般");
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        List<BasicHouseFaceStreetVo> list = generateBaseExamineService.getBasicHouseFaceStreetList();
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        if (CollectionUtils.isNotEmpty(list)) {
                                                            for (BasicHouseFaceStreetVo faceStreet : list) {
                                                                stringBuilder.append(faceStreet.getStreetName())
                                                                        .append(faceStreet.getStreetLevelName())
                                                                        .append(faceStreet.getTrafficFlowName()).append(faceStreet.getVisitorsFlowrateName());
                                                                stringBuilder.append("\r\n");
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 4:
                                                    builder.writeln(generateBaseExamineService.getBasicHouse().getFloor());
                                                    break;
                                                case 5:
                                                    builder.writeln("人文景观：分布有汇融悉尼湾达令港、盛世名城、炬星幸福里等众多住宅区，居民多为中档收入人群、素质较高，社会治安良好。");
                                                    break;
                                                default:
                                                    builder.writeln("");
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(0, 0, 5, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                            if (5 < j && j <= 10) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 6) {
                                        builder.insertCell();
                                        builder.writeln("交通状况");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 6:
                                                    builder.writeln("道路状况");
                                                    break;
                                                case 7:
                                                    builder.writeln("公共交通");
                                                    break;
                                                case 8:
                                                    builder.writeln("对外交通");
                                                    break;
                                                case 9:
                                                    builder.writeln("交通管制情况");
                                                    break;
                                                case 10:
                                                    builder.writeln("停车方便度");
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 6:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingTrafficVo> voList = generateBaseExamineService.getBasicMatchingTrafficList();
                                                        if (CollectionUtils.isNotEmpty(voList)) {
                                                            voList.parallelStream().forEach(basicMatchingTrafficVo -> {
                                                                stringBuilder.append(basicMatchingTrafficVo.getName())
                                                                        .append(basicMatchingTrafficVo.getNatureName())
                                                                        .append(basicMatchingTrafficVo.getDistanceName());
                                                                stringBuilder.append("\r\n");
                                                            });
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 7:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 8:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 9:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 10:
                                                    builder.writeln("暂无");
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(6, 0, 10, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                            if (10 < j && j <= 15) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 11) {
                                        builder.insertCell();
                                        builder.writeln("外部配套设施");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 11:
                                                    builder.writeln("城市基础设施");
                                                    break;
                                                case 12:
                                                    builder.writeln("公共服务设施");
                                                    break;
                                            }
                                        } else if (k == 2) {
                                            switch (j) {
                                                case 12:
                                                    builder.writeln("银行");
                                                    break;
                                                case 13:
                                                    builder.writeln("购物");
                                                    break;
                                                case 14:
                                                    builder.writeln("教育");
                                                    break;
                                                case 15:
                                                    builder.writeln("娱乐休闲");
                                                    break;
                                            }
                                        } else if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                //城市基础设施
                                                case 11:
                                                    List<BasicEstateSupply> estateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(128);
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
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 12:
                                                    if (true) {//金融机构
                                                        if (true) {
                                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                                            List<BasicMatchingFinanceVo> financeList = generateBaseExamineService.getBasicMatchingFinanceList();
                                                            if (CollectionUtils.isNotEmpty(financeList)) {
                                                                for (BasicMatchingFinance finance : financeList) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("名称:%s；", finance.getName()));
                                                                    stringBuilder.append(finance.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(finance.getCategory())));
                                                                    stringBuilder.append(finance.getNature() == null ? "" : String.format("金融机构性质:%s；", baseDataDicService.getNameById(finance.getNature())));
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("服务内容:%s；", finance.getName()));
                                                                }
                                                            }
                                                            builder.writeln(stringBuilder.toString());
                                                        }
                                                    }
                                                    break;

                                                case 13: //购物
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
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
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 14://教育
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEducation> educationList = generateBaseExamineService.getBasicMatchingEducatioListn();
                                                        if (CollectionUtils.isNotEmpty(educationList)) {
                                                            for (BasicMatchingEducation education : educationList) {
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(education.getSchoolName()) ? "" : String.format("学校名称:%s；", education.getSchoolName()));
                                                                stringBuilder.append(education.getSchoolNature() == null ? "" : String.format("学校性质:%s；", baseDataDicService.getNameById(education.getSchoolNature())));
                                                                stringBuilder.append(education.getSchoolGradation() == null ? "" : String.format("学校级次:%s；", baseDataDicService.getNameById(education.getSchoolGradation())));
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(education.getSchoolLevel()) ? "" : String.format("学校等级:%s；", baseDataDicService.getNameById(Integer.valueOf(education.getSchoolLevel()))));
                                                                stringBuilder.append(education.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(education.getDistance())));
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 15://娱乐休闲
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(11, 0, 15, 0));
                                mergeCellModelList.add(new MergeCellModel(12, 1, 15, 1));
                                mergeCellModelList.add(new MergeCellModel(11, 1, 11, 2));
                            }
                            if (15 < j && j <= 18) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 16) {
                                        builder.insertCell();
                                        builder.writeln("周围环境和景观");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 16:
                                                    builder.writeln("自然环境");
                                                    break;
                                                case 17:
                                                    builder.writeln("人文环境");
                                                    break;
                                                case 18:
                                                    builder.writeln("景观");
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 16:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 17:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 18:
                                                    if (true) {
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(16, 0, 18, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                        }
                        for (MergeCellModel mergeCellModel : mergeCellModelList) {
                            Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                            Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                        builder.endTable();
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeObjectAreaStatusSheet = localPath;
        return judgeObjectAreaStatusSheet;
    }

    /**
     * 估价土地实体状况表
     *
     * @return
     */
    public String getJudgeObjectLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        builder.writeln("估价土地实体状况表");
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 8; j++) {
                            switch (j) {
                                case 0:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("估价对象名称");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getName());
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("备注");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 1:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("用途及级别");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(10);
                                                    stringBuilder.append("土地类型:");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName()) ? generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName() : errorStr);
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("土地级别:");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandLevelName()) ? generateBaseExamineService.getBasicEstateLandState().getLandLevelName() : errorStr);
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 2:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("东至,南至,西至,北至");
                                                break;
                                            case 1:
                                                builder.insertCell();
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
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 3:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("面积");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandArea())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getLandArea());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 4:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("形状");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getShapeStateName())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getShapeStateName());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getShapeStateRemark())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getShapeStateRemark());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 5:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("地形、地势、工程地质");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    stringBuilder.append("地形:");
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getPlanenessName())) {
                                                        stringBuilder.append(generateBaseExamineService.getBasicEstateLandState().getPlanenessName());

                                                    } else {
                                                        stringBuilder.append(errorStr);
                                                    }
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("地势:");
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName())) {
                                                        stringBuilder.append(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName());
                                                    } else {
                                                        stringBuilder.append(errorStr);
                                                    }
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("工程地质:");
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 6:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("开发程度");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 7:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("其它");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
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
                            mergeCellModelList.add(new MergeCellModel(j, 1, j, 3));
                        }
                        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                            }
                        }
                        builder.endTable();
                        builder.writeln("");
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeObjectLandStateSheet = localPath;
        return judgeObjectLandStateSheet;
    }

    /**
     * 估价对象建筑实体状况表
     *
     * @return
     */
    public String getJudgeBuildLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        builder.writeln("估价对象建筑实体状况表");
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 18; j++) {
                            switch (j) {
                                case 0:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("估价对象名称");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getName());
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("备注");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 1:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑规模");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 2:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑功能");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
                                                    if (CollectionUtils.isNotEmpty(functionList)) {
                                                        functionList.parallelStream().forEach(basicBuildingFunction -> {
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getType()));
                                                            stringBuilder.append(",");
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getDecorationPart()));
                                                        });
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 3:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("结构");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                String temp = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                                builder.writeln(StringUtils.isNotBlank(temp) ? temp : temp);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 4:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("层数");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorCount() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorCount()));
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 5:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("外观");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    List<BasicBuildingOutfit> outfitList = generateBaseExamineService.getBasicBuildingOutfitList();
                                                    if (CollectionUtils.isNotEmpty(outfitList)) {
                                                        for (BasicBuildingOutfit outfit : outfitList) {
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(outfit.getDecorationPart()) ? errorStr : String.format("装修部位:%s；", outfit.getDecorationPart()));
                                                            stringBuilder.append(outfit.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(outfit.getDecoratingMaterial())));
                                                            stringBuilder.append(outfit.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(outfit.getConstructionTechnology())));
                                                            stringBuilder.append(outfit.getMaterialPrice() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(outfit.getMaterialPrice())));
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 6:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("室内净高或层高");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorHeight()));
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getNetHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getNetHeight()));
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 7:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("装饰装修");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicUnitDecorate> decorateList = generateBaseExamineService.getBasicUnitDecorateList();
                                                    if (CollectionUtils.isNotEmpty(decorateList)) {
                                                        for (BasicUnitDecorate unitDecorate : decorateList) {
                                                            stringBuilder.append(unitDecorate.getDecorationPart() == null ? errorStr : String.format("装修部位:%s；", baseDataDicService.getNameById(unitDecorate.getDecorationPart())));
                                                            stringBuilder.append(unitDecorate.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(unitDecorate.getDecoratingMaterial())));
                                                            stringBuilder.append(unitDecorate.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(unitDecorate.getConstructionTechnology())));
                                                            stringBuilder.append(unitDecorate.getMaterialPriceRange() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(unitDecorate.getMaterialPriceRange())));
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 8:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("设施设备");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicHouseEquipment> equipmentList = generateBaseExamineService.getBasicHouseEquipmentList();
                                                    if (CollectionUtils.isNotEmpty(equipmentList)) {
                                                        stringBuilder = new StringBuilder();
                                                        for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                                            if (org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()) ||
                                                                    org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                                                                stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                                                stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("；");
                                                            }
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 9:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("空间布局");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    BaseDataDic practicalUseDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE_HOUSE);
                                                    if (practicalUseDic != null) {
                                                        String huxing = org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicHouse().getNewHuxingName()) ? generateBaseExamineService.getBasicHouse().getHuxingName() : generateBaseExamineService.getBasicHouse().getNewHuxingName();
                                                        stringBuilder.append(huxing).append("；");
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
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 10:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("竣工日期和设计使用年限");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    builder.writeln(DateUtils.format(generateBaseExamineService.getBasicBuilding().getBeCompletedTime()));
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 11:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("维护保养和完损状况");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
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
                                                    stringBuilder.append("\r\n");
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
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 12:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("工程质量");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 13:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("日照、采光、通风、保温、隔热、隔声、防水");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
                                                    List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
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
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 14:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("其它");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 15:
                                    for (int k = 0; k < 5; k++) {
                                        builder.insertCell();
                                    }
                                    builder.endRow();
                                    break;
                                case 16:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("委估对象序号");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getNumber());
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 17:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑实体分析");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln("综上所述，影响估价对象商业用房地产价值的实体状况中，其建筑物较新，相关配套设施齐全，维护保养较好，商铺开间、进深比一般，对估价对象商业用房地产价值的保值有积极的影响。");
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
                            if (j == 17 || j == 16) {
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 4));
                            } else if (j == 15) {
                                mergeCellModelList.add(new MergeCellModel(j, 0, j, 4));
                            } else {
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 3));
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
                        builder.writeln("");
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeBuildLandStateSheet = localPath;
        return judgeBuildLandStateSheet;
    }


    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        this.schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(this.getSchemeAreaGroup().getId());
        return this.schemeJudgeObjectList;
    }

    public GenerateBaseExamineService getGenerateBaseExamineService(Integer planDetailsId) {
        return new GenerateBaseExamineService(planDetailsId);
    }

    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(Integer projectId, Integer areaId, Integer baseReportTemplateId, ProjectPlan projectPlan) {
        this.projectPlan = projectPlan;
        this.projectId = projectId;
        this.areaId = areaId;
        this.baseReportTemplateId = baseReportTemplateId;
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.projectNumberRecordService = SpringContextUtils.getBean(ProjectNumberRecordService.class);
        this.baseReportService = SpringContextUtils.getBean(BaseReportService.class);
        this.projectInfoService = SpringContextUtils.getBean(ProjectInfoService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeJudgeFunctionService = SpringContextUtils.getBean(SchemeJudgeFunctionService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.projectPhaseService = SpringContextUtils.getBean(ProjectPhaseService.class);
        this.surveyAssetInventoryRightService = SpringContextUtils.getBean(SurveyAssetInventoryRightService.class);
    }


}
