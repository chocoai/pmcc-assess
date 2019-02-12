package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
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

    /**
     * 不动产
     *
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @param land_uses
     * @return
     * @throws Exception
     */
    public boolean realEstateCert(DeclareRealtyRealEstateCert oo, StringBuilder builder, Row row, int i, List<BaseDataDic> land_uses) throws Exception {
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_HOUSE_CATEGORY);
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(40));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(41));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(42));
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
        //验证基础字典中数据
        String purpose = PoiUtils.getCellValue(row.getCell(31));
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(land_uses, purpose);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(用途)", i));
            return false;
        } else {
            purpose = String.valueOf(typeDic.getId());
        }
        //用途
        oo.setPurpose(purpose);
        //权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            oo.setCertName(PoiUtils.getCellValue(row.getCell(0)));
        }
        //所在地
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        }
        //编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            oo.setNumber(PoiUtils.getCellValue(row.getCell(2)));
        }
        //类型
        String type = PoiUtils.getCellValue(row.getCell(3));
        inner:
        if (StringUtils.isNotBlank(type)) {
            if (!ObjectUtils.isEmpty(baseProjectClassifies)) {
                for (BaseProjectClassify baseProjectClassify : baseProjectClassifies) {
                    if (Objects.equal(type, baseProjectClassify.getName())) {
                        oo.setType(baseProjectClassify.getId().toString());
                        break inner;
                    }
                }
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                return false;
            }
        }
        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            oo.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        }
        //共有情况
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            oo.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            oo.setFloorArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(6))));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
        }
        //街道号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));
        }
        //附号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));
        }
        //栋号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));
        }
        //单元
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            oo.setUnit(PoiUtils.getCellValue(row.getCell(11)));
        }
        //楼层
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            oo.setFloor(PoiUtils.getCellValue(row.getCell(12)));
        }
        //房号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));
        }
        //登记时间
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            oo.setRegistrationTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(14))));
        }
        //房屋性质
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            oo.setNature(PoiUtils.getCellValue(row.getCell(15)));
        }
        //规划用途
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            oo.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            oo.setFloorCount(Integer.parseInt(PoiUtils.getCellValue(row.getCell(17))));
        }
        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            oo.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            oo.setOther(PoiUtils.getCellValue(row.getCell(20)));
        }
        //土地证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            oo.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));
        }
        //土地取得方式
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));
        }
        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            oo.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(23))));
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(24)))) {
            oo.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(24))));
        }
        //公摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(25)))) {
            oo.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(26)))) {
            oo.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(27)))) {
            oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));
        }
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(28)))) {
            oo.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(28))));
        }
        //地号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(29)))) {
            oo.setLandNumber(PoiUtils.getCellValue(row.getCell(29)));
        }
        //图号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(30)))) {
            oo.setGraphNumber(PoiUtils.getCellValue(row.getCell(30)));
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(32)))) {
            oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(32))));
        }
        //使用权类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(33)))) {
            oo.setUseRightType(PoiUtils.getCellValue(row.getCell(33)));
        }
        //终止日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(34)))) {
            oo.setTerminationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(34))));
        }
        //使用权面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(35)))) {
            oo.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(35))));
        }
        //面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(36)))) {
            oo.setAcreage(new BigDecimal(PoiUtils.getCellValue(row.getCell(36))));
        }
        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(37)))) {
            oo.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(37))));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(38)))) {
            oo.setMemo(PoiUtils.getCellValue(row.getCell(38)));
        }
        //不动产单元号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(39)))) {
            oo.setRealEstateUnitNumber(PoiUtils.getCellValue(row.getCell(39)));
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
     * @param land_uses
     * @return
     * @throws Exception
     */
    public boolean land(DeclareRealtyLandCert declareRealtyLandCert, StringBuilder builder, Row row, int i, List<BaseDataDic> land_uses) throws Exception {
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_LAND_PROPERTY_CERTIFICATE_TYPE_LAND_CATEGORY);
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(25));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(26));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(27));
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
        String purpose = PoiUtils.getCellValue(row.getCell(15));
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(land_uses, purpose);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
            return false;
        } else {
            purpose = String.valueOf(typeDic.getId());
        }
        //用途
        declareRealtyLandCert.setPurpose(purpose);
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))) {
            declareRealtyLandCert.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))) {
            declareRealtyLandCert.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))) {
            declareRealtyLandCert.setDistrict(map.get("district"));
        }
        //验证 类型(省略已经在excel配置了下拉框)
        //土地权证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            declareRealtyLandCert.setLandCertName(PoiUtils.getCellValue(row.getCell(0)));
        }
        //所在地
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            declareRealtyLandCert.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        }
        //类型
        String type = PoiUtils.getCellValue(row.getCell(2));
        inner:
        if (StringUtils.isNotBlank(type)) {
            if (!ObjectUtils.isEmpty(baseProjectClassifies)) {
                for (BaseProjectClassify baseProjectClassify : baseProjectClassifies) {
                    if (Objects.equal(type, baseProjectClassify.getName())) {
                        declareRealtyLandCert.setType(baseProjectClassify.getId().toString());
                        break inner;
                    }
                }
                builder.append(String.format("\n第%s行异常：类型(国用/集用)不一致", i));
                return false;
            }
        }else {
            builder.append(String.format("\n第%s行异常：类型(国用/集用)不一致", i));
        }
        //土地使用权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            declareRealtyLandCert.setOwnership(PoiUtils.getCellValue(row.getCell(3)));
        }
        //年份
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            declareRealtyLandCert.setYear(PoiUtils.getCellValue(row.getCell(4)));
        }
        //编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            declareRealtyLandCert.setNumber(PoiUtils.getCellValue(row.getCell(5)));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            declareRealtyLandCert.setBeLocated(PoiUtils.getCellValue(row.getCell(6)));
        }
        //街道号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyLandCert.setStreetNumber(PoiUtils.getCellValue(row.getCell(7)));
        }
        //附号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyLandCert.setAttachedNumber(PoiUtils.getCellValue(row.getCell(8)));
        }
        //栋号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            declareRealtyLandCert.setBuildingNumber(PoiUtils.getCellValue(row.getCell(9)));
        }
        //单元
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyLandCert.setUnit(PoiUtils.getCellValue(row.getCell(10)));
        }
        //楼层
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            declareRealtyLandCert.setFloor(PoiUtils.getCellValue(row.getCell(11)));
        }
        //房号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyLandCert.setRoomNumber(PoiUtils.getCellValue(row.getCell(12)));
        }
        //地号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            declareRealtyLandCert.setLandNumber(PoiUtils.getCellValue(row.getCell(13)));
        }
        //图号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            declareRealtyLandCert.setGraphNumber(PoiUtils.getCellValue(row.getCell(14)));
        } else {
            builder.append(String.format("\n第%s行异常：图号必须填写", i));
            return false;
        }
        //取得价格
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            declareRealtyLandCert.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(16))));
        }
        //使用权类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyLandCert.setUseRightType(PoiUtils.getCellValue(row.getCell(17)));
        }
        //终止日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            declareRealtyLandCert.setTerminationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(18))));
        }
        //使用权面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            declareRealtyLandCert.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));
        }
        //独用面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            declareRealtyLandCert.setAcreage(new BigDecimal(PoiUtils.getCellValue(row.getCell(20))));
        }
        //分摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            declareRealtyLandCert.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(21))));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            declareRealtyLandCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(22)));
        }
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            declareRealtyLandCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(23))));
        }
        //记事
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(24)))) {
            declareRealtyLandCert.setMemo(PoiUtils.getCellValue(row.getCell(24)));
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
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(29));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(30));
        //区 县
        String districtName = PoiUtils.getCellValue(row.getCell(31));
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
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            declareRealtyHouseCert.setCertName(PoiUtils.getCellValue(row.getCell(0)));
        }
        //所在地
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            declareRealtyHouseCert.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        }
        //编号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            declareRealtyHouseCert.setNumber(PoiUtils.getCellValue(row.getCell(2)));
        }
        //类型
        String type = PoiUtils.getCellValue(row.getCell(3));
        inner:
        if (StringUtils.isNotBlank(type)) {
            if (!ObjectUtils.isEmpty(baseProjectClassifies)) {
                for (BaseProjectClassify baseProjectClassify : baseProjectClassifies) {
                    if (Objects.equal(type, baseProjectClassify.getName())) {
                        declareRealtyHouseCert.setType(baseProjectClassify.getId().toString());
                        break inner;
                    }
                }
                builder.append(String.format("\n第%s行异常：类型(房权证/监证) 不能与之匹配", i));
                return false;
            }
        }
        //房屋所有权人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            declareRealtyHouseCert.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        }
        //共有情况
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            declareRealtyHouseCert.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            declareRealtyHouseCert.setFloorArea(PoiUtils.getCellValue(row.getCell(6)));
        }
        //房屋坐落
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            declareRealtyHouseCert.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
        }
        //街道号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            declareRealtyHouseCert.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));
        }
        //附号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            declareRealtyHouseCert.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));
        }
        //栋号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            declareRealtyHouseCert.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));
        }
        //单元
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            declareRealtyHouseCert.setUnit(PoiUtils.getCellValue(row.getCell(11)));
        }
        //楼层
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            declareRealtyHouseCert.setFloor(PoiUtils.getCellValue(row.getCell(12)));
        }
        //房号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            declareRealtyHouseCert.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));
        }
        //登记时间
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            declareRealtyHouseCert.setRegistrationTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(14))));
        }
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            declareRealtyHouseCert.setRegistrationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(15))));
        }
        //规划用途
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(16)))) {
            declareRealtyHouseCert.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));
        }
        //总层数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(17)))) {
            declareRealtyHouseCert.setFloorCount(PoiUtils.getCellValue(row.getCell(17)));
        }
        //证载面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(18)))) {
            declareRealtyHouseCert.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));
        }
        //套内面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(19)))) {
            declareRealtyHouseCert.setInnerArea(PoiUtils.getCellValue(row.getCell(19)));
        }
        //其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(20)))) {
            declareRealtyHouseCert.setOther(PoiUtils.getCellValue(row.getCell(20)));
        }
        //土地证号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(21)))) {
            declareRealtyHouseCert.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));
        } else {
            builder.append(String.format("\n第%s行异常：土地证号必须填写 不能与之匹配", i));
            return false;
        }
        //土地取得方式
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(22)))) {
            declareRealtyHouseCert.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));
        }
        //土地使用年限起
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(23)))) {
            declareRealtyHouseCert.setUseStartDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(23))));
        }
        //土地使用年限止
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(24)))) {
            declareRealtyHouseCert.setUseEndDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(24))));
        }
        //公摊面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(25)))) {
            declareRealtyHouseCert.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
        }
        //附记其它
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(26)))) {
            declareRealtyHouseCert.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));
        }
        //登记机关
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(27)))) {
            declareRealtyHouseCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));
        }
        //房屋性质
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(28)))) {
            declareRealtyHouseCert.setNature(PoiUtils.getCellValue(row.getCell(28)));
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
    public void applyCommitTask(ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException, BpmException {
        DeclareApply declareApply = new DeclareApply();
        declareApply.setProjectId(projectPlanDetails.getProjectId());
        declareApply.setPlanDetailsId(projectPlanDetails.getId());
        declareApply.setProcessInsId(processInsId);
        declareApplyService.saveDeclareApply(declareApply);
        if (org.apache.commons.lang3.StringUtils.isBlank(processInsId)) {
            declareApplyService.writeToDeclareRecord(declareApply);
        } else {
            //修改监听器
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, DeclareRealtyEstateCertEvent.class.getSimpleName());
        }
    }
}
