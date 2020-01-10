package com.copower.pmcc.assess.service.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.aspose.words.SaveFormat;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PDFUtil;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-9.
 */
@Service
public class DeclarePublicService {
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareApplyService declareApplyService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private DeclareApplyDao declareApplyDao;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanSurveyService projectPlanSurveyService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;

    private static final String UNIT = "单元";
    private static final String FLOOR = "层";
    private static final String ATTACHED = "附";
    private static final String BUILDING = "幢";
    private static final String BUILDING2 = "栋";
    private static final String STREET = "STREET";
    private static final String RoomNumber = "RoomNumber";
    private static final String NUMBER = "号";

    /**
     * 专门处理坐落问题
     * 优化后可以处理负数以及下划线等的连接如2-4栋
     *
     * @param text
     * @return
     */
    private Map<String, String> beLocatedSplicing(String text) {
        Map<String, String> stringMap = Maps.newHashMap();
        if (StringUtils.isEmpty(text)) {
            return stringMap;
        }
        final String numberRegex = "-?\\d";//负整数或者正整数
        Function<Matcher, String> function = matcher -> {
            while (matcher.find()) {
                if (StringUtils.isNotEmpty(matcher.group())) {
                    return matcher.group();
                }
            }
            return null;
        };
        BiFunction<String, String, String> replaceFun = (s2, s) -> Pattern.compile(String.join("", "[", "^", numberRegex, "]", "*")).matcher(s2).replaceAll(s);
        String streetName = String.join("", org.apache.commons.lang3.StringUtils.substringBetween(text, "", NUMBER), NUMBER);
        stringMap.put(STREET, streetName);

//        final String value = StringUtils.removeStart(text, streetName);
//        List<String> stringList = generateCommonMethod.convertNumberHelp(value);
//        if (CollectionUtils.isNotEmpty(stringList)) {
//            stringList = stringList.stream().filter(s -> NumberUtils.isNumber(s)).collect(Collectors.toList());
//        }
//        if (CollectionUtils.isNotEmpty(stringList)) {
//            stringList.stream().forEachOrdered(s -> {
//                //附
//                if (StringUtils.contains(value, String.format("%s%d%s", ATTACHED, Integer.parseInt(s), NUMBER))) {
//                    stringMap.put(ATTACHED, s);
//                }
//                //栋
//                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), BUILDING))) {
//                    stringMap.put(BUILDING, s);
//                }
//                //单元
//                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), UNIT))) {
//                    stringMap.put(UNIT, s);
//                }
//                //层
//                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), FLOOR))) {
//                    stringMap.put(FLOOR, s);
//                }
//                //房
//                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), NUMBER))) {
//                    stringMap.put(RoomNumber, s);
//                }
//
//            });
//        }


        String value = "";
        value = StringUtils.removeStart(text, streetName);
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, ATTACHED)) {
                String attached = function.apply(Pattern.compile(String.join("", "^", ATTACHED, numberRegex, "*")).matcher(value));
                if (StringUtils.isNotEmpty(attached)) {
                    String number = replaceFun.apply(attached, "");//提取出整个字符中的数字
                    stringMap.put(ATTACHED, number);
                    value = StringUtils.replaceOnce(value, String.join("", attached, NUMBER), "");
                }
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, BUILDING)) {
                String building = org.apache.commons.lang3.StringUtils.substringBetween(value, "", BUILDING);
                value = StringUtils.replaceOnce(value, String.join("", building, BUILDING), "");
                stringMap.put(BUILDING, replaceFun.apply(building, ""));
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, BUILDING2)) {
                String building = org.apache.commons.lang3.StringUtils.substringBetween(value, "", BUILDING2);
                value = StringUtils.replaceOnce(value, String.join("", building, BUILDING2), "");
                stringMap.put(BUILDING, replaceFun.apply(building, ""));
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, UNIT)) {
                String unit = org.apache.commons.lang3.StringUtils.substringBetween(value, "", UNIT);
                value = StringUtils.replaceOnce(value, String.join("", unit, UNIT), "");
                stringMap.put(UNIT, replaceFun.apply(unit, ""));
            }
        }

        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, FLOOR)) {
                String floor = org.apache.commons.lang3.StringUtils.substringBetween(value, "", FLOOR);
                value = StringUtils.replaceOnce(value, String.join("", floor, FLOOR), "");
                stringMap.put(FLOOR, replaceFun.apply(floor, ""));
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            if (StringUtils.contains(value, NUMBER)) {//最后剩余的就是房号
                stringMap.put(RoomNumber, replaceFun.apply(value, ""));
            }
        }
        return stringMap;
    }


    /**
     * 不动产
     *
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean realEstateCert(DeclareRealtyRealEstateCert oo, StringBuilder builder, Row row, int i) throws Exception {
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList("project.declare.common.situation");
        //房屋用途
        List<BaseDataDic> planningUses = baseDataDicService.getCacheDataDicList("examine.house.load.utility");
        //土地用途
        List<BaseDataDic> purposes = baseDataDicService.getCacheDataDicList("estate.land_use");
        //权利性质
        List<BaseDataDic> useRightTypes = baseDataDicService.getCacheDataDicList("project.declare.use.right.type");
        //权利类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type");
        //房屋性质
        List<BaseDataDic> natures = baseDataDicService.getCacheDataDicList("project.declare.room.type");
        Map<String, String> map = new HashMap<>();
        //验证(区域) 省市区
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(0)), PoiUtils.getCellValue(row.getCell(1)), PoiUtils.getCellValue(row.getCell(2)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                oo.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                oo.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                oo.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //权证号
        String cerName = PoiUtils.getCellValue(row.getCell(3));
        if (StringUtils.isNotBlank(cerName)) {
            oo.setCertName(cerName);
            String location = StringUtils.substringBetween(cerName, ")", "不动产");
            if (StringUtils.isEmpty(location)) {
                location = StringUtils.substringBetween(cerName, "）", "不动产");
            }
            oo.setLocation(location);
            String yearStr = StringUtils.substringBeforeLast(cerName, "不动产");
            oo.setYear(generateCommonMethod.getNumber(yearStr));
            String numberStr = StringUtils.substringAfterLast(cerName, "不动产");
            oo.setNumber(generateCommonMethod.getNumber(numberStr));
        }
        //不动产单元号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setRealEstateUnitNumber(PoiUtils.getCellValue(row.getCell(4)));
        }

        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            oo.setOwnership(PoiUtils.getCellValue(row.getCell(5)));
        }


        //验证基础字典中数据
        String publicSituation = PoiUtils.getCellValue(row.getCell(6));

        if (StringUtils.isNotBlank(publicSituation)) {
            BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                return false;
            } else {
                //共有情况
                oo.setPublicSituation(typeDic.getId());
            }
        } else {
            builder.append(String.format("\n第%s行异常：共有情况必须填写", i));
            return false;
        }
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setRegistrationTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(7))));
        }
        //坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(8)));
            Map<String, String> locatedMap = beLocatedSplicing(oo.getBeLocated());
            if (!locatedMap.isEmpty()) {
                if (locatedMap.containsKey(STREET)) {
                    oo.setStreetNumber(locatedMap.get(STREET));
                }
                if (locatedMap.containsKey(UNIT)) {
                    oo.setUnit(locatedMap.get(UNIT));
                }
                if (locatedMap.containsKey(FLOOR)) {
                    oo.setFloor(locatedMap.get(FLOOR));
                }
                if (locatedMap.containsKey(ATTACHED)) {
                    oo.setAttachedNumber(locatedMap.get(ATTACHED));
                }
                if (locatedMap.containsKey(BUILDING)) {
                    oo.setBuildingNumber(locatedMap.get(BUILDING));
                }
                if (locatedMap.containsKey(RoomNumber)) {
                    oo.setRoomNumber(locatedMap.get(RoomNumber));
                }
            }
        } else {
            builder.append(String.format("\n第%s行异常：坐落必须填写", i));
            return false;
        }
        //房屋用途类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setHouseCertUse(PoiUtils.getCellValue(row.getCell(9)));
        }
        //房屋用途类别
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            oo.setHouseCertUseCategory(PoiUtils.getCellValue(row.getCell(10)));
        }
        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            oo.setHousingStructure(PoiUtils.getCellValue(row.getCell(11)));
        }
        BaseDataDic typeDic = null;
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(12));
        if (StringUtils.isNotBlank(nature)) {
            typeDic = baseDataDicService.getDataDicByName(natures, nature);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋性质)", i));
                return false;
            } else {
                nature = String.valueOf(typeDic.getId());
            }
            if (NumberUtils.isNumber(nature)) {
                //房屋性质
                oo.setNature(Integer.valueOf(nature));
            }
        }


        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13))) && this.isNumeric(PoiUtils.getCellValue(row.getCell(13)))) {
            oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(13))));
        } else {
            builder.append(String.format("\n第%s行异常：证载面积必须填写数字", i));
            return false;
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(14)))) {
                oo.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
            } else {
                builder.append(String.format("\n第%s行异常：套内面积应填写数字", i));
                return false;
            }
        }

        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(15)))) {
                oo.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(15))));
            } else {
                builder.append(String.format("\n第%s行异常：公摊面积应填写数字", i));
                return false;
            }
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(16)));
        }
        //其它(房屋)
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            oo.setOther(PoiUtils.getCellValue(row.getCell(17)));
        }

        //附件(其他)
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            oo.setOtherNote(PoiUtils.getCellValue(row.getCell(18)));
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            oo.setFloorCount(PoiUtils.getCellValue(row.getCell(19)));
        }
        //土地取得方式
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            oo.setAcquisitionType(PoiUtils.getCellValue(row.getCell(20)));
        }

        //土地取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(21)))) {
                oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(21))));
            } else {
                builder.append(String.format("\n第%s行异常：土地取得价格应填写数字", i));
                return false;
            }
        }
        //土地用途类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            oo.setLandCertUse(PoiUtils.getCellValue(row.getCell(22)));
        }
        //土地用途类别
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            oo.setLandCertUseCategory(PoiUtils.getCellValue(row.getCell(23)));
        }


        //验证基础字典中数据
        String useRightType = PoiUtils.getCellValue(row.getCell(24));
        if (StringUtils.isNotBlank(useRightType)) {
            typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利性质)", i));
                return false;
            } else {
                //权利性质
                oo.setLandRightNature(typeDic.getId());
            }
        } else {
            builder.append(String.format("\n第%s行异常：权利性质必须填写", i));
            return false;
        }

        //共用宗地面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(25)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(25)))) {
                oo.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
            } else {
                builder.append(String.format("\n第%s行异常：共用宗地面积应填写数字", i));
                return false;
            }
        }
        //土地分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(26)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(26)))) {
                oo.setLandApportionArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(26))));
            } else {
                builder.append(String.format("\n第%s行异常：土地分摊面积应填写数字", i));
                return false;
            }
        }

        //验证基础字典中数据
        String type = PoiUtils.getCellValue(row.getCell(27));
        if (StringUtils.isNotBlank(type)) {
            typeDic = baseDataDicService.getDataDicByName(types, type);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利类型)", i));
                return false;
            } else {
                //权利类型
                oo.setLandRightType(typeDic.getId());
            }
        } else {
            builder.append(String.format("\n第%s行异常：权利类型必须填写", i));
            return false;
        }
        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(28)))) {
            oo.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(28))));
        } else {
            builder.append(String.format("\n第%s行异常：土地使用年限起必须填写", i));
            return false;
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(29)))) {
            oo.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(29))));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(30)))) {
            oo.setMemo(PoiUtils.getCellValue(row.getCell(30)));
        }

        //批文文号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(31)))) {
//            oo.setApprovalReferenceNumber(PoiUtils.getCellValue(row.getCell(28)));
            oo.setCertName(PoiUtils.getCellValue(row.getCell(31)));
        }
        //批文时间
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(32)))) {
            oo.setApprovalTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(32))));
        }
        //批文名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(33)))) {
            oo.setApprovalName(PoiUtils.getCellValue(row.getCell(33)));
        }
        //批文机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(34)))) {
            oo.setApprovalMechanism(PoiUtils.getCellValue(row.getCell(34)));
        }
        //关联附件编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(35)))) {
            oo.setAutoInitNumber(PoiUtils.getCellValue(row.getCell(35)));
        }
        return true;
    }

    /**
     * 土地证
     *
     * @param declareRealtyLandCert
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean land(DeclareRealtyLandCert declareRealtyLandCert, StringBuilder builder, Row row, int i) throws Exception {
        //土地用途
        List<BaseDataDic> purposes = baseDataDicService.getCacheDataDicList("estate.land_use");
        //使用权类型
        List<BaseDataDic> useRightTypes = baseDataDicService.getCacheDataDicList("project.declare.use.right.type");
        //土地证类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type");
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION);
        Map<String, String> map = new HashMap<>();
        //验证(区域) 省市区(县)
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(0)), PoiUtils.getCellValue(row.getCell(1)), PoiUtils.getCellValue(row.getCell(2)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                declareRealtyLandCert.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                declareRealtyLandCert.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                declareRealtyLandCert.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //土地权证号
        String landCertName = PoiUtils.getCellValue(row.getCell(3));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(landCertName)) {
            final String frequency = "第";
            String typeName = "";
            declareRealtyLandCert.setLandCertName(landCertName);
            List<String> stringList = generateCommonMethod.convertNumberHelp(declareRealtyLandCert.getLandCertName());
            //设置类型
            for (BaseDataDic type : types) {
                if (StringUtils.contains(declareRealtyLandCert.getLandCertName(), type.getName())) {
                    declareRealtyLandCert.setLandRightType(type.getId());
                    typeName = type.getName();
                }
            }
            if (CollectionUtils.isNotEmpty(stringList)) {
                stringList.stream().forEachOrdered(s -> {
                    if (NumberUtils.isNumber(s)) {
                        if (StringUtils.contains(declareRealtyLandCert.getLandCertName(), String.format("%d%s", Integer.parseInt(s), frequency))) {
                            declareRealtyLandCert.setYear(s);
                        }
                        if (StringUtils.contains(declareRealtyLandCert.getLandCertName(), String.format("%d%s", Integer.parseInt(s), NUMBER))) {
                            declareRealtyLandCert.setNumber(s);
                        }
                    }
                });
                String str = StringUtils.join(stringList, "");
                str = StringUtils.remove(str, typeName);
                str = StringUtils.remove(str, declareRealtyLandCert.getNumber());
                str = StringUtils.remove(str, declareRealtyLandCert.getYear());
                str = StringUtils.remove(str, NUMBER);
                str = StringUtils.remove(str, frequency);
                declareRealtyLandCert.setLocation(str);
            }
        } else {
            builder.append(String.format("\n第%s行异常：土地权证号必须填写", i));
            return false;
        }

        //土地使用权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            declareRealtyLandCert.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        } else {
            builder.append(String.format("\n第%s行异常：土地使用权人必须填写", i));
            return false;
        }

        String publicSituation = PoiUtils.getCellValue(row.getCell(5));
        if (StringUtils.isNotBlank(publicSituation)) {
            BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                return false;
            } else {
                //共有情况
                declareRealtyLandCert.setPublicSituation(typeDic.getId());
            }
        }

        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            declareRealtyLandCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(6))));
        }

        //坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyLandCert.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
            Map<String, String> locatedMap = beLocatedSplicing(declareRealtyLandCert.getBeLocated());
            if (!locatedMap.isEmpty()) {
                if (locatedMap.containsKey(STREET)) {
                    declareRealtyLandCert.setStreetNumber(locatedMap.get(STREET));
                }
                if (locatedMap.containsKey(UNIT)) {
                    declareRealtyLandCert.setUnit(locatedMap.get(UNIT));
                }
                if (locatedMap.containsKey(FLOOR)) {
                    declareRealtyLandCert.setFloor(locatedMap.get(FLOOR));
                }
                if (locatedMap.containsKey(ATTACHED)) {
                    declareRealtyLandCert.setAttachedNumber(locatedMap.get(ATTACHED));
                }
                if (locatedMap.containsKey(BUILDING)) {
                    declareRealtyLandCert.setBuildingNumber(locatedMap.get(BUILDING));
                }
                if (locatedMap.containsKey(RoomNumber)) {
                    declareRealtyLandCert.setRoomNumber(locatedMap.get(RoomNumber));
                }
            }
        } else {
            builder.append(String.format("\n第%s行异常：坐落必须填写", i));
            return false;
        }

        //地号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyLandCert.setLandNumber(PoiUtils.getCellValue(row.getCell(8)));
        }
        //图号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            declareRealtyLandCert.setGraphNumber(PoiUtils.getCellValue(row.getCell(9)));
        }
        BaseDataDic typeDic = null;
        //土地用途类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyLandCert.setCertUse(PoiUtils.getCellValue(row.getCell(10)));
        }
        //土地用途类别
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            declareRealtyLandCert.setCertUseCategory(PoiUtils.getCellValue(row.getCell(11)));
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(12)))) {
                declareRealtyLandCert.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(12))));
            } else {
                builder.append(String.format("\n第%s行异常：取得价格应填写数字", i));
                return false;
            }
        }

        //权利性质
        String useRightType = PoiUtils.getCellValue(row.getCell(13));
        if (StringUtils.isNotBlank(useRightType)) {
            typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：使用权类型与系统配置的名称不一致", i));
                return false;
            } else {
                declareRealtyLandCert.setLandRightNature(typeDic.getId());
            }
        } else {
            builder.append(String.format("\n第%s行异常：使用权类型必须填写", i));
            return false;
        }


        //使用权面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(14)))) {
                declareRealtyLandCert.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
            } else {
                builder.append(String.format("\n第%s行异常：使用权面积应填写数字", i));
                return false;
            }
        }
        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(15)))) {
                declareRealtyLandCert.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(15))));
            } else {
                builder.append(String.format("\n第%s行异常：分摊面积应填写数字", i));
                return false;
            }
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            declareRealtyLandCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(16)));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyLandCert.setMemo(PoiUtils.getCellValue(row.getCell(17)));
        }
        //关联附件编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            declareRealtyLandCert.setAutoInitNumber(PoiUtils.getCellValue(row.getCell(18)));
        }
        return true;
    }

    /**
     * 房产证导入
     *
     * @param declareRealtyHouseCert
     * @param builder
     * @param row
     */
    public boolean house(DeclareRealtyHouseCert declareRealtyHouseCert, StringBuilder builder, Row row, int i) throws Exception {
        //房屋用途类型
        List<BaseDataDic> planningUses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
        //房产证类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION);
        //房屋性质
        List<BaseDataDic> natures = baseDataDicService.getCacheDataDicList("project.declare.room.type");
        //取得方式
        List<BaseDataDic> useRightTypes = baseDataDicService.getCacheDataDicList("project.declare.use.right.type");
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(0)), PoiUtils.getCellValue(row.getCell(1)), PoiUtils.getCellValue(row.getCell(2)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                declareRealtyHouseCert.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                declareRealtyHouseCert.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                declareRealtyHouseCert.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //房产权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            String certName = PoiUtils.getCellValue(row.getCell(3));
            declareRealtyHouseCert.setCertName(certName);
            //编号
            String numberStr = StringUtils.substringAfterLast(declareRealtyHouseCert.getCertName(), "第");
            declareRealtyHouseCert.setNumber(generateCommonMethod.getNumber(numberStr));
            for (BaseDataDic type : types) {
                if (certName.contains(type.getName())) {
                    declareRealtyHouseCert.setType(type.getId().toString());
                    declareRealtyHouseCert.setLocation(StringUtils.substringBetween(declareRealtyHouseCert.getCertName(), "", type.getName()));
                    break;
                } else {
                    declareRealtyHouseCert.setLocation(StringUtils.substringBetween(declareRealtyHouseCert.getCertName(), "", "第"));
                }
            }
        } else {
            builder.append(String.format("\n第%s行异常：房产权证号必须填写", i));
            return false;
        }

        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            declareRealtyHouseCert.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        } else {
            builder.append(String.format("\n第%s行异常：房屋所有权人必须填写", i));
            return false;
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5))) && this.isNumeric(PoiUtils.getCellValue(row.getCell(5)))) {
            declareRealtyHouseCert.setFloorArea(PoiUtils.getCellValue(row.getCell(5)));
        } else {
            builder.append(String.format("\n第%s行异常：建筑面积必须填写数字", i));
            return false;
        }
        //验证基础字典中数据
        String publicSituation = PoiUtils.getCellValue(row.getCell(6));
        if (StringUtils.isNotBlank(publicSituation)) {
            BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                return false;
            } else {
                //共有情况
                declareRealtyHouseCert.setPublicSituation(typeDic.getId());
            }
        } else {
            builder.append(String.format("\n第%s行异常：共有情况必须填写", i));
            return false;
        }

        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyHouseCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(7))));
        }
        //丘地号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyHouseCert.setGroundNum(PoiUtils.getCellValue(row.getCell(8)));
        }
        //坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            declareRealtyHouseCert.setBeLocated(PoiUtils.getCellValue(row.getCell(9)));
            Map<String, String> locatedMap = beLocatedSplicing(declareRealtyHouseCert.getBeLocated());
            if (!locatedMap.isEmpty()) {
                if (locatedMap.containsKey(STREET)) {
                    declareRealtyHouseCert.setStreetNumber(locatedMap.get(STREET));
                }
                if (locatedMap.containsKey(UNIT)) {
                    declareRealtyHouseCert.setUnit(locatedMap.get(UNIT));
                }
                if (locatedMap.containsKey(FLOOR)) {
                    declareRealtyHouseCert.setFloor(locatedMap.get(FLOOR));
                }
                if (locatedMap.containsKey(ATTACHED)) {
                    declareRealtyHouseCert.setAttachedNumber(locatedMap.get(ATTACHED));
                }
                if (locatedMap.containsKey(BUILDING)) {
                    declareRealtyHouseCert.setBuildingNumber(locatedMap.get(BUILDING));
                }
                if (locatedMap.containsKey(RoomNumber)) {
                    declareRealtyHouseCert.setRoomNumber(locatedMap.get(RoomNumber));
                }
            }
        } else {
            builder.append(String.format("\n第%s行异常：坐落必须填写", i));
            return false;
        }
        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyHouseCert.setHousingStructure(PoiUtils.getCellValue(row.getCell(10)));
        }
        //房屋用途类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            declareRealtyHouseCert.setCertUse(PoiUtils.getCellValue(row.getCell(11)));
        } else {
            builder.append(String.format("\n第%s行异常：房屋用途类型必须填写", i));
            return false;
        }
        //房屋用途类别
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyHouseCert.setCertUseCategory(PoiUtils.getCellValue(row.getCell(12)));
        }
        BaseDataDic typeDic = null;
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(13));
        if (StringUtils.isNotBlank(nature)) {
            typeDic = baseDataDicService.getDataDicByName(natures, nature);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋性质)", i));
                return false;
            } else {
                //房屋性质
                declareRealtyHouseCert.setNature(typeDic.getId());
            }
        }
        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(14)))) {
                declareRealtyHouseCert.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
            } else {
                builder.append(String.format("\n第%s行异常：证载面积应填写数字", i));
                return false;
            }
        } else {
            builder.append(String.format("\n第%s行异常：证载面积必须填写", i));
            return false;
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(15)))) {
                declareRealtyHouseCert.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(15))));
            } else {
                builder.append(String.format("\n第%s行异常：套内面积应填写数字", i));
                return false;
            }
        }
        //公摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(16)))) {
                declareRealtyHouseCert.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(16))));
            } else {
                builder.append(String.format("\n第%s行异常：套内面积应填写数字", i));
                return false;
            }
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyHouseCert.setFloorCount(PoiUtils.getCellValue(row.getCell(17)));
        }
        //验证基础字典中数据
        String landAcquisition = PoiUtils.getCellValue(row.getCell(18));
        if (StringUtils.isNotBlank(landAcquisition)) {
            typeDic = baseDataDicService.getDataDicByName(useRightTypes, landAcquisition);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(土地取得方式)", i));
                return false;
            } else {
                //土地取得方式
                declareRealtyHouseCert.setLandAcquisition(String.valueOf(typeDic.getId()));
            }
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            declareRealtyHouseCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(19)));
        }
        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(20)))) {
                declareRealtyHouseCert.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(20))));
            } else {
                builder.append(String.format("\n第%s行异常：分摊面积应填写数字", i));
                return false;
            }
        }
        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            declareRealtyHouseCert.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(21))));
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            declareRealtyHouseCert.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(22))));
        } else {
            builder.append(String.format("\n第%s行异常：土地使用年限止必须填写", i));
            return false;
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            declareRealtyHouseCert.setOther(PoiUtils.getCellValue(row.getCell(23)));
        }
        //关联附件编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(24)))) {
            declareRealtyHouseCert.setAutoInitNumber(PoiUtils.getCellValue(row.getCell(24)));
        }
        return true;
    }

    /**
     * 在建工程（土建）
     *
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean buildEngineering(DeclareBuildEngineering oo, StringBuilder builder, Row row, int i) throws Exception {
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(14)), PoiUtils.getCellValue(row.getCell(15)), PoiUtils.getCellValue(row.getCell(16)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                oo.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                oo.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                oo.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //占有单位
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));
        }
        //项目名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            oo.setName(PoiUtils.getCellValue(row.getCell(1)));
        }
        //坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));
        }
        //结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            oo.setStructure(PoiUtils.getCellValue(row.getCell(3)));
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setBuildArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));
        }
        //开工日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            oo.setStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(5))));
        }
        //预期完成日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            oo.setExpectedCompletionDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(6))));
        }
        //形象进度
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setSpeedProgress(PoiUtils.getCellValue(row.getCell(7)));
        }
        //付款比例
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            oo.setPaymentRatio(PoiUtils.getCellValue(row.getCell(8)));
        }
        //账面价值
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setBookValue(PoiUtils.getCellValue(row.getCell(9)));
        }
        //帐面净值
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            oo.setBookNetValue(PoiUtils.getCellValue(row.getCell(10)));
        }
        //申报人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            oo.setDeclarer(PoiUtils.getCellValue(row.getCell(11)));
        }
        //申报日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            oo.setDeclarationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(12))));
        }
        //备注
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            oo.setRemark(PoiUtils.getCellValue(row.getCell(13)));
        }
        return true;
    }

    /**
     * 在建工程（设备安装）
     *
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean buildEquipmentInstall(DeclareBuildEquipmentInstall oo, StringBuilder builder, Row row, int i) throws Exception {
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(16)), PoiUtils.getCellValue(row.getCell(17)), PoiUtils.getCellValue(row.getCell(18)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                oo.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                oo.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                oo.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //占有单位
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));
        }
        //项目名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            oo.setName(PoiUtils.getCellValue(row.getCell(1)));
        }
        //坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));
        }
        //规格型号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            oo.setSpecificationModel(PoiUtils.getCellValue(row.getCell(3)));
        }
        //生产厂家
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setManufacturer(PoiUtils.getCellValue(row.getCell(4)));
        }
        //计量单位
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            oo.setMeasurementUnit(PoiUtils.getCellValue(row.getCell(5)));
        }
        //数量
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            oo.setNumber(Integer.parseInt(PoiUtils.getCellValue(row.getCell(6))));
        }
        //开工日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(7))));
        }
        //预期完成日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            oo.setExpectedCompletionDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(8))));
        }
        //帐面设备费
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setBookEquipmentFee(PoiUtils.getCellValue(row.getCell(9)));
        }
        //账面资金成本
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            oo.setBookCapitalCost(PoiUtils.getCellValue(row.getCell(10)));
        }
        //账面安装费
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            oo.setBookInstallationFee(PoiUtils.getCellValue(row.getCell(11)));
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            oo.setOther(PoiUtils.getCellValue(row.getCell(12)));
        }
        //申报人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            oo.setDeclarer(PoiUtils.getCellValue(row.getCell(13)));
        }
        //申报日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            oo.setDeclarationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(14))));
        }
        //备注
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            oo.setRemark(PoiUtils.getCellValue(row.getCell(15)));
        }
        return true;
    }


    /**
     * 提交申报任务
     *
     * @param projectPlanDetails
     * @param processInsId
     * @throws BusinessException
     * @throws BpmException
     */
    public void applyCommitTask(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        DeclareApply declareApply = JSONObject.parseObject(formData, DeclareApply.class);
        declareApply.setProjectId(projectPlanDetails.getProjectId());
        declareApply.setPlanDetailsId(projectPlanDetails.getId());
        declareApply.setProcessInsId(processInsId);
        DeclareApply declare = this.getDeclareApplyByProjectId(projectPlanDetails.getProjectId());
        if (declare != null) {
            declareApply.setId(declare.getId());
        }
        declareApplyService.saveDeclareApply(declareApply);
        if (StringUtils.isBlank(processInsId)) {
            declareApplyService.writeToDeclareRecord(declareApply);
            if (projectPlanDetails.getBisRestart() == Boolean.TRUE) {
                ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
                projectPlanSurveyService.appendSurveyPlanDetails(projectPlan.getProjectId(), projectPlan.getStageSort());
            }
        } else {
            //修改监听器
            publicService.updateProcessEventExecutor(processInsId, DeclareRealtyEstateCertEvent.class.getSimpleName());
        }
    }

    public void editCommitTask(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        DeclareApply declareApply = JSONObject.parseObject(formData, DeclareApply.class);
        declareApply.setProjectId(projectPlanDetails.getProjectId());
        declareApply.setPlanDetailsId(projectPlanDetails.getId());
        declareApply.setProcessInsId(processInsId);
        DeclareApply declare = this.getDeclareApplyByProjectId(projectPlanDetails.getProjectId());
        if (declare != null) {
            declareApply.setId(declare.getId());
        }
        declareApplyService.saveDeclareApply(declareApply);
    }

    public DeclareApply getDeclareApplyByProcessInsId(String processInsId) {
        return declareApplyDao.getDeclareApplyByProcessInsId(processInsId);
    }

    public DeclareApply getDeclareApplyByProjectId(Integer projectId) {
        return declareApplyDao.getDeclareApplyByProjectId(projectId);
    }

    //验证是否是数字或小数
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) { //判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }

    }

    /**
     * 申报图片自动关联
     *
     * @param automatedWarrants
     */
    public void attachmentAutomatedWarrants(AutomatedWarrants automatedWarrants) throws Exception {
        List<String> filterSuffix = Arrays.asList(com.aspose.words.SaveFormat.getName(SaveFormat.DOC), com.aspose.words.SaveFormat.getName(SaveFormat.DOCX), com.aspose.words.SaveFormat.getName(SaveFormat.DOTX));
        String localPath = baseAttachmentService.downloadFtpFileToLocal(automatedWarrants.getAttachmentId());
        String fileExtension = FilenameUtils.getExtension(localPath);
        LinkedList<String> linkedList = new LinkedList<>();
        if (PDFUtil.PDF.equals(fileExtension)) {
            PDFUtil.extractImages(localPath, linkedList);
        } else if (filterSuffix.stream().anyMatch(s -> s.equalsIgnoreCase(fileExtension))) {
            AsposeUtils.extractImages(localPath, linkedList);
        } else {
            throw new Exception("传入的文档只能说doc,docx,dotx,pdf这四种格式的文档,其它的暂时不支持哦!");
        }
        if (CollectionUtils.isEmpty(linkedList)) {
            throw new Exception("传入的文档没有图片");
        }
        LinkedHashMap<String, Integer> linkedHashMap = Maps.newLinkedHashMap();
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), automatedWarrants.getTableName())) {
            DeclareRealtyHouseCert query = new DeclareRealtyHouseCert();
            query.setPlanDetailsId(automatedWarrants.getPlanDetailsId());
            query.setEnable(DeclareTypeEnum.MasterData.getKey());
            List<DeclareRealtyHouseCertVo> declareRealtyHouseCertVoList = declareRealtyHouseCertService.lists(query);
            if (CollectionUtils.isNotEmpty(declareRealtyHouseCertVoList)) {
                for (DeclareRealtyHouseCertVo declareRealtyHouseCertVo : declareRealtyHouseCertVoList) {
                    if (StringUtils.isEmpty(declareRealtyHouseCertVo.getAutoInitNumber())) {
                        continue;
                    }
                    linkedHashMap.put(declareRealtyHouseCertVo.getAutoInitNumber(), declareRealtyHouseCertVo.getId());
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            throw new Exception("没有找到申报编号!");
        }
        LinkedList<Integer> integerLinkedList = getFilterAutomatedWarrants(linkedHashMap, automatedWarrants);
        if (CollectionUtils.isEmpty(integerLinkedList)) {
            throw new Exception("没有找到匹配的申报编号!");
        }
        //处理规则问题
        boolean initMark = linkedList.size()  == integerLinkedList.size() *  automatedWarrants.getStep().intValue();//标识
        if (!initMark) {
            throw new Exception(String.join("-", "申报编号数量和!传入的文档中图片数量不匹配", String.format("%s%s", "申报图片数量", String.valueOf(linkedList.size())), String.format("%s%s", "申报编号数", String.valueOf(integerLinkedList.size())),String.format("申报步长:%s",automatedWarrants.getStep().toString())));
        }
        //根据步长进行分组
        List<List<String>> listList = splitsList(linkedList, automatedWarrants.getStep().intValue());
        int length = listList.size();
        SysAttachmentDto params = new SysAttachmentDto();
        params.setTableName(automatedWarrants.getTableName());
        params.setFieldsName(automatedWarrants.getFieldsName());
        for (int i = 0; i < length; i++) {
            Integer id = integerLinkedList.get(i);
            params.setTableId(id);
            List<String> stringList = listList.get(i);
            for (String path : stringList) {
                baseAttachmentService.importAjaxFile(path, params);
            }
        }
        //处理结束后删除附件
        baseAttachmentService.deleteAttachment(automatedWarrants.getAttachmentId()) ;
    }

    /**
     * 获取筛选之后的申报编号
     *
     * @param linkedHashMap
     * @param automatedWarrants
     * @return
     */
    private LinkedList<Integer> getFilterAutomatedWarrants(LinkedHashMap<String, Integer> linkedHashMap, AutomatedWarrants automatedWarrants) {
        LinkedList<Integer> linkedList = Lists.newLinkedList();
        Iterator<Map.Entry<String, Integer>> entryIterator = linkedHashMap.entrySet().iterator();
        LinkedHashMap<Integer, Integer> integerLinkedHashMap = Maps.newLinkedHashMap();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> integerEntry = entryIterator.next();
            String key = integerEntry.getKey();
            if (StringUtils.isNotBlank(automatedWarrants.getPrefixNumber())) {
                key = StringUtils.remove(integerEntry.getKey(), automatedWarrants.getPrefixNumber());
            }
            int number = Integer.parseInt(key);
            if (number > automatedWarrants.getEndNumber().intValue() || number < automatedWarrants.getStartNumber().intValue()) {
                continue;
            }
            integerLinkedHashMap.put(Integer.valueOf(number), integerEntry.getValue());
        }
        List<Map.Entry<Integer, Integer>> tempList = Lists.newArrayList();
        if (!integerLinkedHashMap.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> iterator = integerLinkedHashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                tempList.add(iterator.next());
            }
        }
        Ordering<Map.Entry<Integer, Integer>> ordering = Ordering.from(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        Collections.sort(tempList, ordering);
        if (CollectionUtils.isNotEmpty(tempList)) {
            Iterator<Map.Entry<Integer, Integer>> iterator = tempList.iterator();
            while (iterator.hasNext()) {
                linkedList.add(iterator.next().getValue());
            }
        }
        return linkedList;
    }

    private List<List<String>> splitsList(List<String> list, int splitSize) {
        if (null == list) {
            return null;
        }
        int listSize = list.size();
        List<List<String>> newList = new ArrayList<List<String>>();
        if (listSize < splitSize) {
            newList.add(list);
            return newList;
        }
        int addLength = splitSize;
        int times = listSize / splitSize;
        if (listSize % splitSize != 0) {
            times += 1;
        }
        int start = 0;
        int end = 0;
        int last = times - 1;
        for (int i = 0; i < times; i++) {
            start = i * splitSize;
            if (i < last) {
                end = start + addLength;
            } else {
                end = listSize;
            }
            newList.add(list.subList(start, end));
        }
        return newList;
    }

    public static class AutomatedWarrants implements Serializable {
        private String prefixNumber;
        private Integer startNumber;
        private Integer endNumber;
        private Integer step;
        Integer attachmentId;
        private String tableName;
        private String fieldsName;
        private Integer planDetailsId;

        public String getPrefixNumber() {
            return prefixNumber;
        }

        public void setPrefixNumber(String prefixNumber) {
            this.prefixNumber = prefixNumber;
        }

        public Integer getStartNumber() {
            return startNumber;
        }

        public void setStartNumber(Integer startNumber) {
            this.startNumber = startNumber;
        }

        public Integer getEndNumber() {
            return endNumber;
        }

        public void setEndNumber(Integer endNumber) {
            this.endNumber = endNumber;
        }

        public Integer getStep() {
            return step;
        }

        public void setStep(Integer step) {
            this.step = step;
        }

        public Integer getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(Integer attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public Integer getPlanDetailsId() {
            return planDetailsId;
        }

        public void setPlanDetailsId(Integer planDetailsId) {
            this.planDetailsId = planDetailsId;
        }

        public String getFieldsName() {
            return fieldsName;
        }

        public void setFieldsName(String fieldsName) {
            this.fieldsName = fieldsName;
        }
    }
}
