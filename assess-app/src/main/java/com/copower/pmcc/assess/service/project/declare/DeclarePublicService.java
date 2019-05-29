package com.copower.pmcc.assess.service.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final String UNIT = "单元";
    private final String FLOOR = "层";
    private final String ATTACHED = "附";
    private final String BUILDING = "栋";
    private final String STREET = "STREET";
    private final String RoomNumber = "RoomNumber";
    private final String NUMBER = "号";

    /**
     * 专门处理坐落问题
     *
     * @param text
     * @return
     */
    private Map<String, String> beLocatedSplicing(String text) {
        Map<String, String> stringMap = Maps.newHashMap();
        if (StringUtils.isEmpty(text)) {
            return stringMap;
        }
        String streetName = org.apache.commons.lang3.StringUtils.substringBetween(text, "", NUMBER);
        stringMap.put(STREET, streetName);
        final String value = StringUtils.removeStart(text, streetName);
        List<String> stringList = generateCommonMethod.convertNumberHelp(value);
        if (CollectionUtils.isNotEmpty(stringList)) {
            stringList = stringList.stream().filter(s -> NumberUtils.isNumber(s)).collect(Collectors.toList());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            stringList.stream().forEachOrdered(s -> {
                //附
                if (StringUtils.contains(value, String.format("%s%d%s", ATTACHED, Integer.parseInt(s), NUMBER))) {
                    stringMap.put(ATTACHED, s);
                }
                //栋
                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), BUILDING))) {
                    stringMap.put(BUILDING, s);
                }
                //单元
                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), UNIT))) {
                    stringMap.put(UNIT, s);
                }
                //层
                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), FLOOR))) {
                    stringMap.put(FLOOR, s);
                }
                //房
                if (StringUtils.contains(value, String.format("%d%s", Integer.parseInt(s), NUMBER))) {
                    stringMap.put(RoomNumber, s);
                }

            });
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
            oo.setNumber(generateCommonMethod.getNumber(cerName));
            oo.setLocation(StringUtils.substringBeforeLast(cerName, "不动产"));
        }

        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        }


        //验证基础字典中数据
        String publicSituation = PoiUtils.getCellValue(row.getCell(5));

        if (StringUtils.isNotBlank(publicSituation)) {
            BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                return false;
            } else {
                //共有情况
                oo.setPublicSituation(typeDic.getId());
            }
        }
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            oo.setRegistrationTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(6))));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
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
        }
        BaseDataDic typeDic = null;
        //验证基础字典中数据
        String planningUse = PoiUtils.getCellValue(row.getCell(8));
        if (StringUtils.isNotBlank(planningUse)) {
            typeDic = baseDataDicService.getDataDicByName(planningUses, planningUse);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋用途)", i));
                return false;
            } else {
                //房屋用途
                oo.setHouseCertUse(typeDic.getId());
            }
        }


        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setHousingStructure(PoiUtils.getCellValue(row.getCell(9)));
        }
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(10));
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

        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            oo.setFloorArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(11))));
        }
        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(12))));
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            oo.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(13))));
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            oo.setFloorCount(Integer.parseInt(PoiUtils.getCellValue(row.getCell(14))));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(15)));
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            oo.setOther(PoiUtils.getCellValue(row.getCell(16)));
        }
        //附件其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            oo.setOtherNote(PoiUtils.getCellValue(row.getCell(17)));
        }

        //验证基础字典中数据
        String purpose = PoiUtils.getCellValue(row.getCell(18));
        if (StringUtils.isNotBlank(purpose)) {
            typeDic = baseDataDicService.getDataDicByName(purposes, purpose);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(土地用途)", i));
                return false;
            } else {
                //土地用途
                oo.setLandCertUse(typeDic.getId());
            }
        }

        //验证基础字典中数据
        String useRightType = PoiUtils.getCellValue(row.getCell(19));
        if (StringUtils.isNotBlank(useRightType)) {
            typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利性质)", i));
                return false;
            } else {
                //权利性质
                oo.setLandRightNature(typeDic.getId());
            }
        }


        //验证基础字典中数据
        String type = PoiUtils.getCellValue(row.getCell(20));
        if (StringUtils.isNotBlank(type)) {
            typeDic = baseDataDicService.getDataDicByName(types, type);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利类型)", i));
                return false;
            } else {
                //权利类型
                oo.setLandRightType(typeDic.getId());
            }
        }


        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            oo.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(21))));
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            oo.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(22))));
        }
        //共用宗地面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            oo.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(23))));
        }
        //公摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(24)))) {
            oo.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(24))));
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(25)))) {
            oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(26)))) {
            oo.setMemo(PoiUtils.getCellValue(row.getCell(26)));
        }
        //不动产单元号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(27)))) {
            oo.setRealEstateUnitNumber(PoiUtils.getCellValue(row.getCell(27)));
        }
        //批文文号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(28)))) {
//            oo.setApprovalReferenceNumber(PoiUtils.getCellValue(row.getCell(28)));
            oo.setCertName(PoiUtils.getCellValue(row.getCell(28)));
        }
        //批文时间
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(29)))) {
            oo.setApprovalTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(29))));
        }
        //批文名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(30)))) {
            oo.setApprovalName(PoiUtils.getCellValue(row.getCell(30)));
        }
        //批文机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(31)))) {
            oo.setApprovalMechanism(PoiUtils.getCellValue(row.getCell(31)));
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

        //房屋坐落
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
            builder.append(String.format("\n第%s行异常：房屋坐落必须填写", i));
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
        //土地用途
        String purpose = PoiUtils.getCellValue(row.getCell(10));
        if (StringUtils.isNotBlank(purpose)) {
            typeDic = baseDataDicService.getDataDicByName(purposes, purpose);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：土地用途与系统配置的名称不一致", i));
                return false;
            } else {
                declareRealtyLandCert.setCertUse(typeDic.getId());
            }
        }

        //使用权类型
        String useRightType = PoiUtils.getCellValue(row.getCell(11));
        if (StringUtils.isNotBlank(useRightType)) {
            typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：使用权类型与系统配置的名称不一致", i));
                return false;
            } else {
                declareRealtyLandCert.setLandRightNature(typeDic.getId());
            }
        }


        //终止日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyLandCert.setTerminationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(12))));
        } else {
            builder.append(String.format("\n第%s行异常：终止日期必须填写", i));
            return false;
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            declareRealtyLandCert.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(13))));
        }
        //使用权面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            declareRealtyLandCert.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
        } else {
            builder.append(String.format("\n第%s行异常：使用权面积必须填写", i));
            return false;
        }
        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            declareRealtyLandCert.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(15))));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            declareRealtyLandCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(16)));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyLandCert.setMemo(PoiUtils.getCellValue(row.getCell(17)));
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
        //规划用途
        List<BaseDataDic> planningUses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
        //房产证类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION);
        //房屋性质
        List<BaseDataDic> natures = baseDataDicService.getCacheDataDicList("project.declare.room.type");
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
            declareRealtyHouseCert.setNumber(generateCommonMethod.getNumber(declareRealtyHouseCert.getCertName()));
            for (BaseDataDic type : types) {
                if (certName.contains(type.getName())) {
                    declareRealtyHouseCert.setType(type.getId().toString());
                    declareRealtyHouseCert.setLocation(StringUtils.substringBetween(declareRealtyHouseCert.getCertName(), "", type.getName()));
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
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))&&StringUtils.isNumeric(PoiUtils.getCellValue(row.getCell(5)))) {
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
        }

        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyHouseCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(7))));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyHouseCert.setBeLocated(PoiUtils.getCellValue(row.getCell(8)));
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
            builder.append(String.format("\n第%s行异常：房屋坐落必须填写", i));
            return false;
        }
        BaseDataDic typeDic = null;
        //验证基础字典中数据
        String planningUse = PoiUtils.getCellValue(row.getCell(9));
        if (StringUtils.isNotBlank(planningUse)) {
            typeDic = baseDataDicService.getDataDicByName(planningUses, planningUse);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(规划用途)", i));
                return false;
            } else {
                //规划用途
                declareRealtyHouseCert.setCertUse(typeDic.getId());
            }
        }

        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyHouseCert.setHousingStructure(PoiUtils.getCellValue(row.getCell(10)));
        }
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(11));
        if (StringUtils.isNotBlank(nature)) {
            typeDic = baseDataDicService.getDataDicByName(natures, nature);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋性质)", i));
                return false;
            } else {
                nature = String.valueOf(typeDic.getId());
                //房屋性质
                declareRealtyHouseCert.setNature(Integer.valueOf(nature));
            }
        }
        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyHouseCert.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(12))));
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            declareRealtyHouseCert.setInnerArea(PoiUtils.getCellValue(row.getCell(13)));
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            declareRealtyHouseCert.setFloorCount(PoiUtils.getCellValue(row.getCell(14)));
        }
        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            declareRealtyHouseCert.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(15))));
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            declareRealtyHouseCert.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(16))));
        } else {
            builder.append(String.format("\n第%s行异常：土地使用年限止必须填写", i));
            return false;
        }
        //公摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyHouseCert.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(17))));
        }
        //土地取得方式
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            declareRealtyHouseCert.setLandAcquisition(PoiUtils.getCellValue(row.getCell(18)));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            declareRealtyHouseCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(19)));
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            declareRealtyHouseCert.setOther(PoiUtils.getCellValue(row.getCell(20)));
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
        if (org.apache.commons.lang3.StringUtils.isBlank(processInsId)) {
            declareApplyService.writeToDeclareRecord(declareApply);
        } else {
            //修改监听器
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, DeclareRealtyEstateCertEvent.class.getSimpleName());
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
}
