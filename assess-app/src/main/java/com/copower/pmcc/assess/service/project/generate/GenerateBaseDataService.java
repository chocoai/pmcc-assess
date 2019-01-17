package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.method.MdMarketCompareFieldService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.copower.pmcc.assess.common.AsposeUtils.mergeCells;

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
    private MdMarketCompareFieldService mdMarketCompareFieldService;

    //构造器必须传入的参数
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;

    //中间变量
    private SchemeAreaGroup schemeAreaGroup;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo;

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
    private String judgeObjectAreaStatusSheet2;

    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(Integer projectId, Integer areaId, Integer baseReportTemplateId) {
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
        this.mdMarketCompareFieldService = SpringContextUtils.getBean(MdMarketCompareFieldService.class);
    }

    /**
     * 获取区域信息(组)
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        if (schemeAreaGroup != null) {
            return schemeAreaGroup;
        }
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(areaId);
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
        SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(schemeAreaGroup.getId());
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
        SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
        StringBuilder builder = new StringBuilder("");
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(schemeAreaGroup.getId());
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
            List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(schemeAreaGroup.getId());
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
            List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(schemeAreaGroup.getId());
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
        String s1 = "";
        String s2 = "";
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.WORK_PROGRAMME_SET_USE);
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                if (baseDataDic.getName().contains("住宅")) {
                    s1 = baseDataDic.getName();
                }
                if (baseDataDic.getName().contains("商业")) {
                    s2 = baseDataDic.getName();
                }
            }
        }
        this.setUse = moreJudgeObject(s1, s2);
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
        if (StringUtils.isBlank(str)) {
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
        if (StringUtils.isNotBlank(this.useRightType)) {
            return useRightType;
        } else {
            return errorStr;
        }
    }

    /**
     * 估价对象区位状况表(临时)
     *
     * @return
     */
    public String getJudgeObjectAreaStatusSheet2() {
        Document doc = null;
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString().substring(2, 9)), UUID.randomUUID().toString().substring(1, 7), ".doc");
        try {
            doc = new Document();
            DocumentBuilder builder = new DocumentBuilder(doc);

            Table table = builder.startTable();

            //自动计算行数(第一行)
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.endRow();

            //第二行
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.endRow();

            //第三行
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.insertCell();
            builder.writeln(String.format("%s%s%s", UUID.randomUUID().toString(), ",", UUID.randomUUID().toString()));
            builder.endRow();


            // We want to merge the range of cells found in between these two cells.
            Cell cellStartRange = table.getRows().get(0).getCells().get(0); //第1行第1列
            Cell cellEndRange = table.getRows().get(1).getCells().get(0); //第2行第1列
            // Merge all the cells between the two specified cells into one.
            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
            builder.endTable();
            doc.save(localPath);
        } catch (Exception e) {

        }
        this.judgeObjectAreaStatusSheet2 = localPath;
        return judgeObjectAreaStatusSheet2;
    }

    /**
     * 估价对象区位状况表
     *
     * @return
     */
    public String getJudgeObjectAreaStatusSheet() {
        if (StringUtils.isNotBlank(this.judgeObjectAreaStatusSheet)) {
            return judgeObjectAreaStatusSheet;
        } else {
            return errorStr;
        }
    }
}
