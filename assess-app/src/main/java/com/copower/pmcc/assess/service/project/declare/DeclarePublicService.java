package com.copower.pmcc.assess.service.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.aspose.words.SaveFormat;
import com.copower.pmcc.assess.common.*;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.declare.AutomatedWarrants;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.survey.ProjectPlanSurveyService;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
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
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;

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
     * 确定是否为  权证
     *
     * @param id
     * @return
     */
    public boolean checkLandCertGetQuestion(Integer id) {
        if (id != null && id != 0) {
            BaseDataDic dataDicById = baseDataDicService.getDataDicById(id);
            BaseDataDic dicYes = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.projectDeclareCertificate_YES);
            if (Objects.equal(dicYes.getId(), dataDicById.getId())) {
                return true;
            }
            BaseDataDic dicNo = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.projectDeclareCertificate_NO);
            if (Objects.equal(dicNo.getId(), dataDicById.getId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不动产证导入
     *
     * @param classArrayListMultimap
     * @param target
     * @param builder
     * @param row
     * @return
     * @throws Exception
     */
    public boolean realEstateCert(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, DeclareRealtyRealEstateCert target, StringBuilder builder, Row row) throws Exception {
        Map<String, String> map = new HashMap<>();
        //必填项
        List<String> requiredList = new ArrayList<>();
        boolean getQuestion = checkLandCertGetQuestion(target.getLandCertGetQuestion());
        if (getQuestion) {
            requiredList.addAll(Arrays.asList("autoInitNumber", "province", "city", "certName", "location", "number", "year", "ownership", "publicSituation", "beLocated", "houseCertUse", "houseCertUseCategory", "nature", "evidenceArea", "landCertUse", "landCertUseCategory",
                    "landRightNature", "landRightType"));
        } else {
            requiredList.addAll(Arrays.asList("autoInitNumber", "province", "city", "publicSituation", "beLocated", "landCertUse",
                    "landRightNature", "landCertUseCategory", "landRightType", "useEndDate", "houseCertUse", "houseCertUseCategory", "evidenceArea"));
        }
        //数据字典 map
        Multimap<String, List<BaseDataDic>> baseMap = ArrayListMultimap.create();
        baseMap.put("nature", baseDataDicService.getCacheDataDicList("project.declare.room.type"));
        baseMap.put("landRightType", baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type"));
        baseMap.put("landRightNature", baseDataDicService.getCacheDataDicList("project.declare.use.right.type"));
        baseMap.put("publicSituation", baseDataDicService.getCacheDataDicList("project.declare.common.situation"));
        baseMap.put("acquisitionType", baseDataDicService.getCacheDataDicList("project.declare.acquisition.type"));
        boolean check = excelImportHelp(classArrayListMultimap, target, builder, row, baseMap, requiredList);
        if (target.getAutoInitNumber() == null || target.getAutoInitNumber() == 0){
            return false;
        }
        //验证(区域)
        if (erpAreaService.checkArea(target.getProvince(), target.getCity(), target.getDistrict(), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                target.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                target.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                target.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            excelImportWriteErrorInfo(row.getRowNum(), 0, "区域类型与系统配置的名称不一致 省市区(县)", false, builder);
            return false;
        }
        //权证号 拆分
        if (StringUtils.isNotBlank(target.getCertName())) {
            String location = StringUtils.substringBetween(target.getCertName(), ")", "不动产");
            if (StringUtils.isEmpty(location)) {
                location = StringUtils.substringBetween(target.getCertName(), "）", "不动产");
            }
            target.setLocation(location);
            String yearStr = StringUtils.substringBeforeLast(target.getCertName(), "不动产");
            target.setYear(generateCommonMethod.getNumber(yearStr));
            String numberStr = StringUtils.substringAfterLast(target.getCertName(), "不动产");
            target.setNumber(generateCommonMethod.getNumber(numberStr));
        }
        //坐落 子项
        if (org.apache.commons.lang3.StringUtils.isNotBlank(target.getBeLocated())) {
            Map<String, String> locatedMap = beLocatedSplicing(target.getBeLocated());
            if (!locatedMap.isEmpty()) {
                if (locatedMap.containsKey(STREET)) {
                    target.setStreetNumber(locatedMap.get(STREET));
                }
                if (locatedMap.containsKey(UNIT)) {
                    target.setUnit(locatedMap.get(UNIT));
                }
                if (locatedMap.containsKey(FLOOR)) {
                    target.setFloor(locatedMap.get(FLOOR));
                }
                if (locatedMap.containsKey(ATTACHED)) {
                    target.setAttachedNumber(locatedMap.get(ATTACHED));
                }
                if (locatedMap.containsKey(BUILDING)) {
                    target.setBuildingNumber(locatedMap.get(BUILDING));
                }
                if (locatedMap.containsKey(RoomNumber)) {
                    target.setRoomNumber(locatedMap.get(RoomNumber));
                }
            }
        }
        return check;
    }

    /**
     * 土地证导入
     *
     * @param classArrayListMultimap
     * @param declareRealtyLandCert
     * @param builder
     * @param row
     * @return
     * @throws Exception
     */
    public boolean land(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, DeclareRealtyLandCert declareRealtyLandCert, StringBuilder builder, Row row) throws Exception {
        Map<String, String> map = new HashMap<>();
        //必填项
        List<String> requiredList = new ArrayList<>();
        boolean getQuestion = checkLandCertGetQuestion(declareRealtyLandCert.getLandCertGetQuestion());
        requiredList.addAll(Arrays.asList("ownership", "province", "city"));
        if (getQuestion) {
            requiredList.addAll(Arrays.asList("autoInitNumber", "province", "city", "landRightType", "year", "ownership", "publicSituation", "landCertName", "beLocated", "certUse", "certUseCategory", "landRightNature", "registrationAuthority"));
//            requiredList.addAll(Arrays.asList("streetNumber", "buildingNumber", "roomNumber"));
        } else {
            requiredList.addAll(Arrays.asList("autoInitNumber", "province", "city", "publicSituation", "beLocated", "certUse", "certUseCategory", "landRightNature", "terminationDate", "registrationAuthority"));
        }
        //数据字典 map
        Multimap<String, List<BaseDataDic>> baseMap = ArrayListMultimap.create();
        baseMap.put("landRightNature", baseDataDicService.getCacheDataDicList("project.declare.use.right.type"));
        baseMap.put("landRightType", baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type"));
        baseMap.put("publicSituation", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION));
        boolean check = excelImportHelp(classArrayListMultimap, declareRealtyLandCert, builder, row, baseMap, requiredList);
        if (declareRealtyLandCert.getAutoInitNumber() == null || declareRealtyLandCert.getAutoInitNumber() == 0){
            return false;
        }
        //验证(区域)
        if (erpAreaService.checkArea(declareRealtyLandCert.getProvince(), declareRealtyLandCert.getCity(), declareRealtyLandCert.getDistrict(), builder, map)) {
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
            excelImportWriteErrorInfo(row.getRowNum(), 0, "区域类型与系统配置的名称不一致 省市区(县)", false, builder);
            return false;
        }
        //土地权证号 拆分
        if (org.apache.commons.lang3.StringUtils.isNotBlank(declareRealtyLandCert.getLandCertName())) {
            final String frequency = "第";
            String typeName = "";
            List<String> stringList = generateCommonMethod.convertNumberHelp(declareRealtyLandCert.getLandCertName());
            //设置类型
            List<BaseDataDic> types = baseMap.get("landRightType").stream().findFirst().get();
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
        }
        //坐落 子项
        if (org.apache.commons.lang3.StringUtils.isNotBlank(declareRealtyLandCert.getBeLocated())) {
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
        }
        return check;
    }

    /**
     * 房产证导入
     *
     * @param classArrayListMultimap
     * @param declareRealtyHouseCert
     * @param builder
     * @param row
     * @return
     * @throws Exception
     */
    public boolean house(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, DeclareRealtyHouseCert declareRealtyHouseCert, StringBuilder builder, Row row) throws Exception {
        Map<String, String> map = new HashMap<>();
        //必填项
        List<String> requiredList = new ArrayList<>();
        //old required
//        requiredList.addAll(Arrays.asList("province", "city", "certName", "useEndDate", "apportionmentArea", "evidenceArea", "certUse", "beLocated", "publicSituation", "ownership")); //old required
        //new required
        requiredList.addAll(Arrays.asList("autoInitNumber", "province", "city", "certName", "type", "ownership", "floorArea", "publicSituation", "beLocated", "certUse", "certUseCategory"));
        requiredList.addAll(Arrays.asList("nature", "evidenceArea", "registrationAuthority", "useEndDate"));

        //数据字典 map
        Multimap<String, List<BaseDataDic>> baseMap = ArrayListMultimap.create();
        baseMap.put("type", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE));
        baseMap.put("landAcquisition", baseDataDicService.getCacheDataDicList("project.declare.use.right.type"));
        baseMap.put("nature", baseDataDicService.getCacheDataDicList("project.declare.room.type"));
        baseMap.put("publicSituation", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION));
        boolean check = excelImportHelp(classArrayListMultimap, declareRealtyHouseCert, builder, row, baseMap, requiredList);
        if (declareRealtyHouseCert.getAutoInitNumber() == null || declareRealtyHouseCert.getAutoInitNumber() == 0){
            return false;
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getCertName())) {
            BiConsumer<String, String> biConsumer = ((certName, name) -> {
                Collection<List<BaseDataDic>> listCollection = baseMap.get("type");
                if (CollectionUtils.isEmpty(listCollection)) {
                    return;
                }
                Iterator<List<BaseDataDic>> listIterator = listCollection.iterator();
                while (listIterator.hasNext()) {
                    List<BaseDataDic> baseDataDicList = listIterator.next();
                    if (CollectionUtils.isEmpty(baseDataDicList)) {
                        continue;
                    }
                    if (certName.contains(name)) {
                        declareRealtyHouseCert.setType(String.valueOf(baseDataDicService.getDataDicIdByName(baseDataDicList, name)));
                        declareRealtyHouseCert.setLocation(StringUtils.substringBefore(certName, name));
                    }
                }
            });
            biConsumer.accept(declareRealtyHouseCert.getCertName(), String.join("", BaseConstant.ASSESS_REALTY_HOUSE_CERT_RIGHT, BaseConstant.ASSESS_REALTY_HOUSE_CERT_CHECK));
            biConsumer.accept(declareRealtyHouseCert.getCertName(), BaseConstant.ASSESS_REALTY_HOUSE_CERT_RIGHT);
            biConsumer.accept(declareRealtyHouseCert.getCertName(), BaseConstant.ASSESS_REALTY_HOUSE_CERT_CHECK);
            declareRealtyHouseCert.setNumber(generateCommonMethod.getNumber(declareRealtyHouseCert.getCertName()));//编号
        }
        //验证(区域)
        if (erpAreaService.checkArea(declareRealtyHouseCert.getProvince(), declareRealtyHouseCert.getCity(), declareRealtyHouseCert.getDistrict(), builder, map)) {
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
            excelImportWriteErrorInfo(row.getRowNum(), 0, "区域类型与系统配置的名称不一致 省市区(县)", false, builder);
            return false;
        }
        //坐落 子项
        if (org.apache.commons.lang3.StringUtils.isNotBlank(declareRealtyHouseCert.getBeLocated())) {
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
        }
        return check;
    }

    public boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row, Multimap<String, List<BaseDataDic>> baseMap) {
        return excelImportHelp(classArrayListMultimap, target, stringBuilder, row, baseMap, null);
    }

    public boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row, List<String> requiredList) {
        return excelImportHelp(classArrayListMultimap, target, stringBuilder, row, null, requiredList);
    }

    public boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row) {
        return excelImportHelp(classArrayListMultimap, target, stringBuilder, row, null, null);
    }

    public boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row, Multimap<String, List<BaseDataDic>> baseMap, List<String> requiredList) {
        return excelImportHelp(classArrayListMultimap, target, stringBuilder, row, baseMap, requiredList, true);
    }

    /**
     * excel模板导入辅助
     *
     * @param classArrayListMultimap 核心映射对象map
     * @param target                 对象
     * @param stringBuilder          信息对象工具收集
     * @param isBreak                遇到错误赋值是否立即结束 注意当这个设为false的时候这个辅助方法就不会抛出不合乎规范的错误了
     * @param row
     * @param baseMap                数据字典map
     * @param requiredList           必填项map
     * @return
     */
    private boolean excelImportHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, Object target, StringBuilder stringBuilder, Row row, Multimap<String, List<BaseDataDic>> baseMap, List<String> requiredList, boolean isBreak) {
        return ExcelImportUtils.excelImportHelp(classArrayListMultimap, target, stringBuilder, row, baseMap, requiredList, isBreak) ;
    }

    private void excelImportWriteErrorInfo(final int rowIndex, final int colIndex, String info, boolean required, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, colIndex, info, required, stringBuilder);
    }

    private void excelImportWriteErrorInfo(final int rowIndex, final int colIndex, String info, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, colIndex, info, false, stringBuilder);
    }

    public void excelImportWriteErrorInfo(final int rowIndex, String info, final StringBuilder stringBuilder) {
        excelImportWriteErrorInfo(null, rowIndex, 0, info, false, stringBuilder);
    }

    /**
     * @param rowIndex 行索引
     * @param colIndex 列索引
     * @param info     错误信息
     * @param required 是否必填
     * @return
     */
    private void excelImportWriteErrorInfo(final String key, final int rowIndex, final int colIndex, String info, boolean required, final StringBuilder stringBuilder) {
        ExcelImportUtils.excelImportWriteErrorInfo(key, rowIndex, colIndex, info, required, stringBuilder);
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

    /**
     * 获取某个自动编号在申报里的数量
     *
     * @param planDetailsId
     * @param autoInitNumber
     * @return
     */
    public int getCountByPlanDetailsIdGetAutoInitNumberSize(Integer planDetailsId, Integer autoInitNumber) {
        TreeSet<Integer> treeSet = getCountByPlanDetailsIdAndAutoInitNumberList(planDetailsId, autoInitNumber, DeclareTypeEnum.MasterData);
        return treeSet.size();
    }

    /**
     * 获取自动编号 的最大数
     *
     * @param planDetailsId
     * @return
     */
    public Integer getCountByPlanDetailsIdGetMaxAutoInitNumber(Integer planDetailsId) {
        TreeSet<Integer> treeSet = getCountByPlanDetailsIdAndAutoInitNumberList(planDetailsId, null, null);
        int size = 1;
        if (CollectionUtils.isNotEmpty(treeSet)) {
            Integer last = treeSet.last();
            size += last.intValue();
        }
        return size;
    }

    public TreeSet<Integer> getCountByPlanDetailsIdAndAutoInitNumberList(Integer planDetailsId, Integer autoInitNumber, DeclareTypeEnum declareTypeEnum) {
        TreeSet<Integer> integerTreeSet = new TreeSet<>();
        TreeSet<Map.Entry<Integer, Class<?>>> countByPlanDetailsIdAndAutoInitNumberListHelp = getCountByPlanDetailsIdAndAutoInitNumberListHelp(planDetailsId, autoInitNumber, declareTypeEnum);
        if (CollectionUtils.isNotEmpty(countByPlanDetailsIdAndAutoInitNumberListHelp)) {
            Iterator<Map.Entry<Integer, Class<?>>> entryIterator = countByPlanDetailsIdAndAutoInitNumberListHelp.iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, Class<?>> classEntry = entryIterator.next();
                integerTreeSet.add(classEntry.getKey());
            }
        }
        return integerTreeSet;
    }

    public TreeSet<Map.Entry<Integer, Class<?>>> getCountByPlanDetailsIdAndAutoInitNumberListHelp(Integer planDetailsId, Integer autoInitNumber, DeclareTypeEnum declareTypeEnum) {
        TreeSet<Map.Entry<Integer, Class<?>>> integerTreeSet = new TreeSet<>();
        TreeSet<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> entryTreeSet = getCountByPlanDetailsIdAndAutoInitNumberListTool(planDetailsId, autoInitNumber, declareTypeEnum);
        if (CollectionUtils.isNotEmpty(entryTreeSet)) {
            Iterator<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> entryIterator = entryTreeSet.iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, MyEntry<Integer, Class<?>>> myEntryEntry = entryIterator.next();
                MyEntry<Integer, Class<?>> myEntry = new MyEntry<>(myEntryEntry.getKey(), myEntryEntry.getValue().getValue());
                integerTreeSet.add(myEntry);
            }
        }
        return integerTreeSet;
    }

    public TreeSet<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> getCountByPlanDetailsIdAndAutoInitNumberListTool(Integer planDetailsId, Integer autoInitNumber, DeclareTypeEnum declareTypeEnum) {
        TreeSet<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> integerTreeSet = new TreeSet<>();
        DeclareRealtyHouseCert house = new DeclareRealtyHouseCert();
        DeclareRealtyLandCert land = new DeclareRealtyLandCert();
        DeclareRealtyRealEstateCert estateCert = new DeclareRealtyRealEstateCert();
        if (autoInitNumber != null) {
            house.setAutoInitNumber(autoInitNumber);
            land.setAutoInitNumber(autoInitNumber);
            estateCert.setAutoInitNumber(autoInitNumber);
        }
        if (declareTypeEnum != null) {
            house.setEnable(declareTypeEnum.getKey());
            land.setEnable(declareTypeEnum.getKey());
            estateCert.setEnable(declareTypeEnum.getKey());
        }
        house.setPlanDetailsId(planDetailsId);
        land.setPlanDetailsId(planDetailsId);
        estateCert.setPlanDetailsId(planDetailsId);
        List<DeclareRealtyHouseCertVo> declareRealtyHouseCertVoList = declareRealtyHouseCertService.lists(house);
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertVoList)) {
            Iterator<DeclareRealtyHouseCertVo> iterator = declareRealtyHouseCertVoList.iterator();
            while (iterator.hasNext()) {
                DeclareRealtyHouseCertVo houseCertVo = iterator.next();
                if (houseCertVo.getAutoInitNumber() == null) {
                    continue;
                }
                MyEntry<Integer, Class<?>> myEntry = new MyEntry<>(houseCertVo.getId(), DeclareRealtyHouseCert.class);
                integerTreeSet.add(new MyEntry<>(houseCertVo.getAutoInitNumber(), myEntry));
            }
        }
        List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = declareRealtyLandCertService.lists(land);
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
            Iterator<DeclareRealtyLandCertVo> iterator = declareRealtyLandCertVoList.iterator();
            while (iterator.hasNext()) {
                DeclareRealtyLandCertVo landCertVo = iterator.next();
                if (landCertVo.getAutoInitNumber() == null) {
                    continue;
                }
                MyEntry<Integer, Class<?>> myEntry = new MyEntry<>(landCertVo.getId(), DeclareRealtyLandCert.class);
                integerTreeSet.add(new MyEntry<>(landCertVo.getAutoInitNumber(), myEntry));
            }
        }
        List<DeclareRealtyRealEstateCertVo> declareRealtyRealEstateCertList = declareRealtyRealEstateCertService.landLevels(estateCert);
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
            Iterator<DeclareRealtyRealEstateCertVo> iterator = declareRealtyRealEstateCertList.iterator();
            while (iterator.hasNext()) {
                DeclareRealtyRealEstateCertVo estateCertVo = iterator.next();
                if (estateCertVo.getAutoInitNumber() == null) {
                    continue;
                }
                MyEntry<Integer, Class<?>> myEntry = new MyEntry<>(estateCertVo.getId(), DeclareRealtyRealEstateCert.class);
                integerTreeSet.add(new MyEntry<>(estateCertVo.getAutoInitNumber(), myEntry));
            }
        }
        return integerTreeSet;
    }


    /**
     * 获取主数据的从数据
     *
     * @param tableName
     * @param id
     * @param planDetailsId
     * @return
     */
    private Map.Entry<Integer, Integer> auxiliaryAttachmentAutomatedWarrants(String tableName, Integer id, Integer planDetailsId) {
        Map.Entry<Integer, Integer> entry = null;
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        query.setPlanDetailsId(planDetailsId);
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), tableName)) {
            query.setType(DeclareRealtyHouseCert.class.getSimpleName());
            query.setHouseId(id);
            List<DeclareBuildEngineeringAndEquipmentCenter> centers = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centers)) {
                Iterator<DeclareBuildEngineeringAndEquipmentCenter> iterator = centers.iterator();
                while (iterator.hasNext()) {
                    DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = iterator.next();
                    if (equipmentCenter.getLandId() == null || equipmentCenter.getLandId() == 0) {
                        continue;
                    }
                    DeclareRealtyLandCert landCertById = declareRealtyLandCertService.getDeclareRealtyLandCertById(equipmentCenter.getLandId());
                    if (landCertById == null) {
                        continue;
                    }
                    if (landCertById.getAutoInitNumber() == null) {
                        continue;
                    }
                    entry = new MyEntry<>(landCertById.getId(), landCertById.getAutoInitNumber());
                }
            }
        }
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), tableName)) {
            query.setType(DeclareRealtyLandCert.class.getSimpleName());
            query.setLandId(id);
            List<DeclareBuildEngineeringAndEquipmentCenter> centers = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
            if (CollectionUtils.isNotEmpty(centers)) {
                Iterator<DeclareBuildEngineeringAndEquipmentCenter> iterator = centers.iterator();
                while (iterator.hasNext()) {
                    DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = iterator.next();
                    if (equipmentCenter.getHouseId() == null || equipmentCenter.getHouseId() == 0) {
                        continue;
                    }
                    DeclareRealtyHouseCert houseCertById = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(equipmentCenter.getHouseId());
                    if (houseCertById == null) {
                        continue;
                    }
                    if (houseCertById.getAutoInitNumber() == null) {
                        continue;
                    }
                    entry = new MyEntry<>(houseCertById.getId(), houseCertById.getAutoInitNumber());
                }
            }
        }
        return entry;
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
            throw new Exception("传入的文档只能是doc,docx,dotx,pdf这四种格式的文档,其它的暂时不支持哦!");
        }
        if (CollectionUtils.isEmpty(linkedList)) {
            throw new Exception("传入的文档没有图片");
        }
        LinkedHashMap<Integer, Integer> linkedHashMap = Maps.newLinkedHashMap();
        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), automatedWarrants.getTableName())) {
            DeclareRealtyHouseCert query = new DeclareRealtyHouseCert();
            query.setPlanDetailsId(automatedWarrants.getPlanDetailsId());
            query.setEnable(DeclareTypeEnum.MasterData.getKey());
            List<DeclareRealtyHouseCertVo> declareRealtyHouseCertVoList = declareRealtyHouseCertService.lists(query);
            if (CollectionUtils.isNotEmpty(declareRealtyHouseCertVoList)) {
                for (DeclareRealtyHouseCertVo declareRealtyHouseCertVo : declareRealtyHouseCertVoList) {
                    if (StringUtils.isBlank(automatedWarrants.getIsSource())) {
                        linkedHashMap.put(declareRealtyHouseCertVo.getAutoInitNumber(), declareRealtyHouseCertVo.getId());
                    }
                    if (StringUtils.isNotBlank(automatedWarrants.getIsSource())) {
                        Map.Entry<Integer, Integer> entry = auxiliaryAttachmentAutomatedWarrants(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRealtyHouseCertVo.getId(), declareRealtyHouseCertVo.getPlanDetailsId());
                        if (entry != null) {
                            automatedWarrants.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
                            linkedHashMap.put(entry.getValue(), entry.getKey());
                        }
                    }
                }
            }
        }

        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), automatedWarrants.getTableName())) {
            DeclareRealtyRealEstateCert query = new DeclareRealtyRealEstateCert();
            query.setPlanDetailsId(automatedWarrants.getPlanDetailsId());
            query.setEnable(DeclareTypeEnum.MasterData.getKey());
            List<DeclareRealtyRealEstateCertVo> declareRealtyRealEstateCertList = declareRealtyRealEstateCertService.landLevels(query);
            if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
                for (DeclareRealtyRealEstateCertVo realEstateCertVo : declareRealtyRealEstateCertList) {
                    linkedHashMap.put(realEstateCertVo.getAutoInitNumber(), realEstateCertVo.getId());
                }
            }
        }

        if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), automatedWarrants.getTableName())) {
            DeclareRealtyLandCert query = new DeclareRealtyLandCert();
            query.setPlanDetailsId(automatedWarrants.getPlanDetailsId());
            query.setEnable(DeclareTypeEnum.MasterData.getKey());
            List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = declareRealtyLandCertService.lists(query);
            if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
                for (DeclareRealtyLandCertVo realtyLandCertVo : declareRealtyLandCertVoList) {
                    if (StringUtils.isBlank(automatedWarrants.getIsSource())) {
                        linkedHashMap.put(realtyLandCertVo.getAutoInitNumber(), realtyLandCertVo.getId());
                    }
                    if (StringUtils.isNotBlank(automatedWarrants.getIsSource())) {
                        Map.Entry<Integer, Integer> entry = auxiliaryAttachmentAutomatedWarrants(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), realtyLandCertVo.getId(), realtyLandCertVo.getPlanDetailsId());
                        if (entry != null) {
                            automatedWarrants.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
                            linkedHashMap.put(entry.getValue(), entry.getKey());
                        }
                    }
                }
            }
        }
        if (!linkedHashMap.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> entryIterator = linkedHashMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, Integer> integerIntegerEntry = entryIterator.next();
                if (integerIntegerEntry.getKey() == null || integerIntegerEntry.getKey() == 0 || integerIntegerEntry.getValue() == null || integerIntegerEntry.getValue() == 0) {
                    entryIterator.remove();
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
        boolean initMark = linkedList.size() == integerLinkedList.size() * automatedWarrants.getStep().intValue();//标识
        if (!initMark) {
            throw new Exception(String.join("-", "申报编号数量和!传入的文档中图片数量不匹配", String.format("%s%s", "申报图片数量", String.valueOf(linkedList.size())), String.format("%s%s", "申报编号数", String.valueOf(integerLinkedList.size())), String.format("申报步长:%s", automatedWarrants.getStep().toString())));
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
        baseAttachmentService.deleteAttachment(automatedWarrants.getAttachmentId());
    }

    /**
     * 获取筛选之后的申报编号
     *
     * @param linkedHashMap
     * @param automatedWarrants
     * @return
     */
    private LinkedList<Integer> getFilterAutomatedWarrants(LinkedHashMap<Integer, Integer> linkedHashMap, AutomatedWarrants automatedWarrants) {
        LinkedList<Integer> linkedList = Lists.newLinkedList();
        Iterator<Map.Entry<Integer, Integer>> entryIterator = linkedHashMap.entrySet().iterator();
        LinkedHashMap<Integer, Integer> integerLinkedHashMap = Maps.newLinkedHashMap();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Integer> integerEntry = entryIterator.next();
            Integer number = integerEntry.getKey();
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
       return ExcelImportUtils.splitsStringList(list, splitSize) ;
    }


    /**
     * @param c   匹配的对象模板即class
     * @param row ,传入excel导入数据的key行(大多数情况是第一行)
     * @return <publicArea,<java.math.BigDecimal,2>> 字段名称,
     */
    public Multimap<String, Map.Entry<Class<?>, Integer>> getMultimapByClass(Class<?> c, org.apache.poi.ss.usermodel.Row row) {
       return ExcelImportUtils.getMultimapByClass(c, row) ;
    }
}
