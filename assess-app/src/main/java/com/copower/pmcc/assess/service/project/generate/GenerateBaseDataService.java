package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.ExamineEstateSupplyEnumType;
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
import com.copower.pmcc.erp.common.exception.BusinessException;
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
                                                    builder.writeln(StringUtils.isNotBlank(generateBaseExamineService.getByDataBlock().getPosition())?generateBaseExamineService.getByDataBlock().getPosition():"方位：暂无");
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
                                                    if (true){
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
    }

}
