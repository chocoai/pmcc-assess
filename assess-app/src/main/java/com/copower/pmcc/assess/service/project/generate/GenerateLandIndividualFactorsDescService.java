package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.basic.*;
import com.copower.pmcc.assess.common.enums.report.ReportFieldLandEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * 土地报告 个别因素描述
 */
@Service
public class GenerateLandIndividualFactorsDescService {
    @Autowired
    private BaseService baseService;
    @Autowired
    private BasicApplyService basicApplyService;
    private static final String defaultString = "无";
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private GenerateLoactionService generateLoactionService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;

    /**
     * 位置
     *
     * @return
     * @throws Exception
     */
    public String getLocation(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), declareRecord.getSeat());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("估价对象位于").append(value);

        return stringBuilder.toString();
    }

    /**
     * 获取面积描述
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getAreaDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("证载面积").append(ArithmeticUtils.getBigDecimalString(declareRecord.getFloorArea())).append("㎡");
            builder.append("评估面积为").append(ArithmeticUtils.getBigDecimalString(schemeJudgeObject.getEvaluationArea())).append("㎡");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("估价对象位于").append(value);

        return stringBuilder.toString();
    }

    /**
     * 获取用途描述
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getUseDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("证载用途为").append(declareRecord.getCertUse()).append("用地");
            builder.append("实际用途为").append(declareRecord.getPracticalUse()).append("用地");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 地质描述
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getGeologyDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo basicEstateLandState = generateBaseExamineService.getBasicEstateLandState();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("形状、地质、地形、地势 :");
            stringBuilder.append("酸碱度:").append(basicEstateLandState.getPhName());
            stringBuilder.append("稳定性:").append(basicEstateLandState.getHoldOnName());
            stringBuilder.append("承载力:").append(basicEstateLandState.getBearingCapacityName());
            stringBuilder.append("地形:").append(basicEstateLandState.getPlanenessName());
            stringBuilder.append("地势:").append(basicEstateLandState.getTopographicTerrainName());
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 宗地开发程度
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getSupplyInfoContainerDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo basicEstateLandState = generateBaseExamineService.getBasicEstateLandState();
            BasicEstateVo basicEstateVo = generateBaseExamineService.getEstate();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("截止估价基准日，根据估价人员现场查看，估价对象宗地内 ");
            stringBuilder.append("土地开发程度:").append(basicEstateLandState.getDevelopmentDegreeName());
            stringBuilder.append("基础设施完备度:").append(basicEstateLandState.getInfrastructureCompletenessName());
            stringBuilder.append("宗地外:").append(basicEstateVo.getInfrastructureName());
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 使用年限
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getUseYearDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouseVo basicHouse = generateBaseExamineService.getBasicHouse();
            BasicEstateLandCategoryInfo landCategoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicHouse.getId());
            if (landCategoryInfo == null) {
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("根据委托方提供的${").append(ReportFieldLandEnum.LAND_ENUM_USE_MATERIAL.getName()).append("}");
            if (landCategoryInfo.getTerminationData() != null) {
                stringBuilder.append("土地使用权终止日期为:").append(landCategoryInfo.getTerminationData());
            }
            stringBuilder.append("，自估价基准日起剩余使用年期为:").append(ArithmeticUtils.getBigDecimalString(schemeJudgeObject.getLandRemainingYear()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 土地权利状况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getLandRightsStatusDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            DeclareRealtyLandCert declareRealtyLandCert = null;
            DeclareRealtyRealEstateCert declareRealtyRealEstateCert = null;
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
            }
            if (declareRealtyLandCert == null) {
                declareRealtyLandCert = new DeclareRealtyLandCert();
            }
            if (declareRealtyRealEstateCert == null) {
                declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("土地权利性质");
            if (declareRealtyLandCert.getLandRightType() != null) {
                stringBuilder.append(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightType()));
            }
            if (declareRealtyRealEstateCert.getLandRightType() != null) {
                stringBuilder.append(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightType()));
            }
            if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getAcquisitionType())) {
                stringBuilder.append("土地取得方式").append(baseDataDicService.getNameById(declareRealtyRealEstateCert.getAcquisitionType()));
            }
            stringBuilder.append(declareRecord.getCertUse()).append("用地使用……");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }


    /**
     * 其它
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getOtherDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            DeclareRealtyLandCert declareRealtyLandCert = null;
            DeclareRealtyRealEstateCert declareRealtyRealEstateCert = null;
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
            }
            if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
            }
            if (declareRealtyLandCert == null) {
                declareRealtyLandCert = new DeclareRealtyLandCert();
            }
            if (declareRealtyRealEstateCert == null) {
                declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("宗地最有效利用方式为");
            if (declareRealtyLandCert.getLandRightType() != null) {
                stringBuilder.append(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightType()));
            }
            if (declareRealtyRealEstateCert.getLandRightType() != null) {
                stringBuilder.append(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightType()));
            }
            if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getAcquisitionType())) {
                stringBuilder.append("设定取得方式").append(baseDataDicService.getNameById(declareRealtyRealEstateCert.getAcquisitionType()));
            }
            stringBuilder.append(declareRecord.getCertUse()).append("用途开发用地");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 容积率 描述
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getPlotRatioDesc(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo basicEstateLandState = generateBaseExamineService.getBasicEstateLandState();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("根据委托方提供的:");
            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                List<String> stringList = LangUtils.transform(sysAttachmentDtoList, obj -> obj.getFileName());
                stringBuilder.append(StringUtils.join(stringList, "，"));
            } else {
                stringBuilder.append("资料");
            }
            stringBuilder.append("显示，其规划容积率:").append(basicEstateLandState.getPlotRatio());
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        String value = defaultString;
        if (!map.isEmpty()) {
            value = generateCommonMethod.judgeEachDesc2(map, "", "", false);
        }
        return value;
    }

    /**
     * 临街路状况
     *
     * @param judgeObjectList
     * @return
     */
    public String getFaceStreet(List<SchemeJudgeObject> judgeObjectList) {
        return generateLoactionService.getFaceStreet(judgeObjectList);
    }

    /**
     * 总结
     *
     * @param judgeObjectList
     * @return
     */
    public String getSummaryDesc(List<SchemeJudgeObject> judgeObjectList) {
        return "通过以上影响估价对象价格个别因素的描述，我们认为，估价对象临街位置好、宗地面积适中、地质条件好、地形平坦、宗地形状规则、个别开发程度较高、" +
                "土地权利状况完善、容积率符合规划，对地价无不良影响；由于假设开发土地使用年期未达法定最高使用年限，对地价有一定的减价影响。综合来看，其个别因素对地价的影响一般。";
    }
}
