package com.copower.pmcc.assess.service.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.DeclareRealtyEstateCertEvent;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.base.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-9.
 */
@Service
public class DeclarePublicService {
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
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
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_HOUSE_CATEGORY);

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

        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(0));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(1));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(2));
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder, map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            oo.setDistrict(map.get("district"));
        }
        //权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            oo.setCertName(PoiUtils.getCellValue(row.getCell(3)));
        }

        String cerName = PoiUtils.getCellValue(row.getCell(3));
        if (StringUtils.isNotBlank(cerName)) {
            String[] strings = cerName.replace("不动产权第", ",").replace("号", ",").split(",");
            oo.setLocation(strings[0]);
            oo.setNumber(strings[1]);
        }

        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        }


        //验证基础字典中数据
        String publicSituation = PoiUtils.getCellValue(row.getCell(5));
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
            return false;
        } else {
            publicSituation = String.valueOf(typeDic.getId());
        }
        //共有情况
        oo.setPublicSituation(publicSituation);

        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            oo.setRegistrationTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(6))));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
        }
        String located = PoiUtils.getCellValue(row.getCell(7));
        if (StringUtils.isNotBlank(located)) {
            String reg = "^.*?号";//街道号
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(located);
            while (m.find()) {
                oo.setStreetNumber(m.group().substring(0, m.group().length() - 1));
            }

            reg = "(?<=附)(\\d+)";//匹配附后面的数字
            p = Pattern.compile(reg);
            m = p.matcher(located);
            while (m.find()) {
                oo.setAttachedNumber(m.group());
            }

            reg = "(\\d+)(?=栋)";//匹配栋前面的数字
            p = Pattern.compile(reg);
            m = p.matcher(located);
            while (m.find()) {
                oo.setBuildingNumber(m.group());
            }

            reg = "(\\d+)(?=单元)";//匹配单元前面的数字
            p = Pattern.compile(reg);
            m = p.matcher(located);
            while (m.find()) {
                oo.setUnit(m.group());
            }

            reg = "(\\d+)(?=层)";//匹配层前面的数字
            p = Pattern.compile(reg);
            m = p.matcher(located);
            while (m.find()) {
                oo.setFloor(m.group());
            }

            reg = "(\\d+)(?=号)";//匹配号前面的数字
            p = Pattern.compile(reg);
            m = p.matcher(located);
            while (m.find()) {
                oo.setRoomNumber(m.group(m.groupCount()));
            }
        }

        //验证基础字典中数据
        String planningUse = PoiUtils.getCellValue(row.getCell(8));
        typeDic = baseDataDicService.getDataDicByName(planningUses, planningUse);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋用途)", i));
            return false;
        } else {
            planningUse = String.valueOf(typeDic.getId());
        }
        //房屋用途
        oo.setPlanningUse(planningUse);

        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setHousingStructure(PoiUtils.getCellValue(row.getCell(9)));
        }
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(10));
        typeDic = baseDataDicService.getDataDicByName(natures, nature);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋性质)", i));
            return false;
        } else {
            nature = String.valueOf(typeDic.getId());
        }
        //房屋性质
        oo.setNature(nature);
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
        typeDic = baseDataDicService.getDataDicByName(purposes, purpose);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(土地用途)", i));
            return false;
        } else {
            purpose = String.valueOf(typeDic.getId());
        }
        //土地用途
        oo.setPurpose(purpose);


        //验证基础字典中数据
        String useRightType = PoiUtils.getCellValue(row.getCell(19));
        typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利性质)", i));
            return false;
        } else {
            useRightType = String.valueOf(typeDic.getId());
        }
        //权利性质
        oo.setUseRightType(useRightType);

        //验证基础字典中数据
        String type = PoiUtils.getCellValue(row.getCell(20));
        typeDic = baseDataDicService.getDataDicByName(types, type);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(权利类型)", i));
            return false;
        } else {
            type = String.valueOf(typeDic.getId());
        }
        //权利类型
        oo.setType(type);

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
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_LAND_PROPERTY_CERTIFICATE_TYPE_LAND_CATEGORY);

        //土地用途
        List<BaseDataDic> purposes = baseDataDicService.getCacheDataDicList("estate.land_use");
        //使用权类型
        List<BaseDataDic> useRightTypes = baseDataDicService.getCacheDataDicList("project.declare.use.right.type");
        //类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type");
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION);

        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(0));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(1));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(2));
        declareRealtyLandCert.setProvince(provinceName);
        declareRealtyLandCert.setCity(cityName);
        declareRealtyLandCert.setDistrict(districtName);
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder, map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        //验证基础字典中数据

        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            declareRealtyLandCert.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            declareRealtyLandCert.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            declareRealtyLandCert.setDistrict(map.get("district"));
        }
        //土地权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            String landCertName= PoiUtils.getCellValue(row.getCell(3));
            declareRealtyLandCert.setLandCertName(landCertName);
            String typeName = "";
            //设置类型
            for (BaseDataDic type: types) {
                if(landCertName.contains(type.getName())){
                    typeName = type.getName();
                    BaseDataDic dataDicByName = baseDataDicService.getDataDicByName(types, type.getName());
                    declareRealtyLandCert.setType(String.valueOf(dataDicByName.getId()));
                }
            }
            //编号
            String reg = "(\\d+)(?=号)";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(landCertName);
            while (m.find()) {
                declareRealtyLandCert.setNumber(m.group(m.groupCount()));
            }
            //年份
            reg = "(\\d+)(?=第)";//匹配第前面的数字
            p = Pattern.compile(reg);
            m = p.matcher(landCertName);
            while (m.find()) {
                declareRealtyLandCert.setYear(m.group());
            }
            //所在地
            reg = "(\\S+)(?="+typeName+")";//匹配类型前的内容
            p = Pattern.compile(reg);
            m = p.matcher(landCertName);
            while (m.find()) {
                declareRealtyLandCert.setLocation(m.group());
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
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
            return false;
        } else {
            publicSituation = String.valueOf(typeDic.getId());
        }
        //共有情况
        declareRealtyLandCert.setPublicSituation(publicSituation);

        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            declareRealtyLandCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(6))));
        }

        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyLandCert.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
            String located = PoiUtils.getCellValue(row.getCell(7));
            if (StringUtils.isNotBlank(located)) {
                String reg = "^.*?号";//街道号
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setStreetNumber(m.group().substring(0, m.group().length() - 1));
                }

                reg = "(?<=附)(\\d+)";//匹配附后面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setAttachedNumber(m.group());
                }

                reg = "(\\d+)(?=栋)";//匹配栋前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setBuildingNumber(m.group());
                }

                reg = "(\\d+)(?=单元)";//匹配单元前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setUnit(m.group());
                }

                reg = "(\\d+)(?=层)";//匹配层前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setFloor(m.group());
                }

                reg = "(\\d+)(?=号)";//匹配号前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyLandCert.setRoomNumber(m.group(m.groupCount()));
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
        } else {
            builder.append(String.format("\n第%s行异常：图号必须填写", i));
            return false;
        }
        //土地用途
        String purpose = PoiUtils.getCellValue(row.getCell(10));
        typeDic = baseDataDicService.getDataDicByName(purposes, purpose);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：土地用途与系统配置的名称不一致", i));
            return false;
        } else {
            purpose = String.valueOf(typeDic.getId());
        }
        declareRealtyLandCert.setPurpose(purpose);

        //使用权类型
        String useRightType = PoiUtils.getCellValue(row.getCell(11));
        typeDic = baseDataDicService.getDataDicByName(useRightTypes, useRightType);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：使用权类型与系统配置的名称不一致", i));
            return false;
        } else {
            useRightType = String.valueOf(typeDic.getId());
        }
        declareRealtyLandCert.setUseRightType(useRightType);

        //终止日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyLandCert.setTerminationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(12))));
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            declareRealtyLandCert.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(13))));
        }
        //使用权面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            declareRealtyLandCert.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
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
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_HOUSE_CATEGORY);
        //规划用途
        List<BaseDataDic> planningUses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
        //房产证类型
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
        //共有情况
        List<BaseDataDic> publicSituations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_COMMON_SITUATION);
        //房屋性质
        List<BaseDataDic> natures = baseDataDicService.getCacheDataDicList("project.declare.room.type");

        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(0));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(1));
        //区 县
        String districtName = PoiUtils.getCellValue(row.getCell(2));
        declareRealtyHouseCert.setProvince(provinceName);
        declareRealtyHouseCert.setCity(cityName);
        declareRealtyHouseCert.setDistrict(districtName);
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder, map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            declareRealtyHouseCert.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            declareRealtyHouseCert.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            declareRealtyHouseCert.setDistrict(map.get("district"));
        }
        //房产权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            String certName = PoiUtils.getCellValue(row.getCell(3));
            declareRealtyHouseCert.setCertName(certName);
            String typeName = "";
            //设置类型
            for (BaseDataDic type: types) {
                if(certName.contains(type.getName())){
                    typeName = type.getName();
                    BaseDataDic dataDicByName = baseDataDicService.getDataDicByName(types, type.getName());
                    declareRealtyHouseCert.setType(String.valueOf(dataDicByName.getId()));
                }
            }
            //编号
            String reg = "(\\d+)(?=号)";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(certName);
            while (m.find()) {
                declareRealtyHouseCert.setNumber(m.group(m.groupCount()));
            }
            //所在地
            reg = "(\\S+)(?="+typeName+")";//匹配类型前的内容
            p = Pattern.compile(reg);
            m = p.matcher(certName);
            while (m.find()) {
                declareRealtyHouseCert.setLocation(m.group());
            }
        }else {
            builder.append(String.format("\n第%s行异常：房产权证号必须填写", i));
            return false;
        }

        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            declareRealtyHouseCert.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            declareRealtyHouseCert.setFloorArea(PoiUtils.getCellValue(row.getCell(5)));
        }
        //验证基础字典中数据
        String publicSituation = PoiUtils.getCellValue(row.getCell(6));
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(publicSituations, publicSituation);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
            return false;
        } else {
            publicSituation = String.valueOf(typeDic.getId());
        }
        //共有情况
        declareRealtyHouseCert.setPublicSituation(publicSituation);
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyHouseCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(7))));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyHouseCert.setBeLocated(PoiUtils.getCellValue(row.getCell(8)));
            String located = PoiUtils.getCellValue(row.getCell(8));
            if (StringUtils.isNotBlank(located)) {
                String reg = "^.*?号";//街道号
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setStreetNumber(m.group().substring(0, m.group().length() - 1));
                }

                reg = "(?<=附)(\\d+)";//匹配附后面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setAttachedNumber(m.group());
                }

                reg = "(\\d+)(?=栋)";//匹配栋前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setBuildingNumber(m.group());
                }

                reg = "(\\d+)(?=单元)";//匹配单元前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setUnit(m.group());
                }

                reg = "(\\d+)(?=层)";//匹配层前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setFloor(m.group());
                }

                reg = "(\\d+)(?=号)";//匹配号前面的数字
                p = Pattern.compile(reg);
                m = p.matcher(located);
                while (m.find()) {
                    declareRealtyHouseCert.setRoomNumber(m.group(m.groupCount()));
                }
            }
        }else {
            builder.append(String.format("\n第%s行异常：房屋坐落必须填写", i));
            return false;
        }
        //验证基础字典中数据
        String planningUse = PoiUtils.getCellValue(row.getCell(9));
        typeDic = baseDataDicService.getDataDicByName(planningUses, planningUse);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(规划用途)", i));
            return false;
        } else {
            planningUse = String.valueOf(typeDic.getId());
        }
        //规划用途
        declareRealtyHouseCert.setPlanningUse(planningUse);
        //房屋结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyHouseCert.setHousingStructure(PoiUtils.getCellValue(row.getCell(10)));
        }
        //验证基础字典中数据
        String nature = PoiUtils.getCellValue(row.getCell(11));
        typeDic = baseDataDicService.getDataDicByName(natures, nature);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(房屋性质)", i));
            return false;
        } else {
            nature = String.valueOf(typeDic.getId());
        }
        //房屋性质
        declareRealtyHouseCert.setNature(nature);

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
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(14));
        //市或者区
        String cityName = PoiUtils.getCellValue(row.getCell(15));
        //县
        String districtName = PoiUtils.getCellValue(row.getCell(16));
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder, map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            oo.setDistrict(map.get("district"));
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
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(16));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(17));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(18));
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);
        Map<String, String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder, map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            oo.setDistrict(map.get("district"));
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
        DeclareApply declare =this.getDeclareApplyByProjectId(projectPlanDetails.getProjectId());
        if(declare !=null){
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
        DeclareApply declare =this.getDeclareApplyByProjectId(projectPlanDetails.getProjectId());
        if(declare !=null){
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
