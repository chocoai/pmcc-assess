package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/19 15:50
 * @Description:申报信息记录 poi
 */
@Component
public class DeclarePoiHelp {
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 不动产
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @param land_uses
     * @return
     * @throws Exception
     */
    public boolean realEstateCert(DeclareRealtyRealEstateCert oo,StringBuilder builder, Row row, int i, List<BaseDataDic> land_uses)throws Exception{
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(34)) ;
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(35)) ;
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(36)) ;
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);

        Map<String,String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))){
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))){
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))){
            oo.setDistrict(map.get("district"));
        }
        //验证 类型(省略已经在excel配置了下拉框)

        //验证基础字典中数据
        String purpose = PoiUtils.getCellValue(row.getCell(31));
        BaseDataDic typeDic = baseDataDicService.getDataDicByName(land_uses,purpose);
        if (typeDic == null) {
            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
            return false;
        }else {
            purpose = String.valueOf(typeDic.getId());
        }
        //用途
        oo.setPurpose(purpose);
        oo.setCertName(PoiUtils.getCellValue(row.getCell(0)));
        oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        oo.setNumber(PoiUtils.getCellValue(row.getCell(2)));
        //类型
        oo.setType(PoiUtils.getCellValue(row.getCell(3)));
        //土地取得方式
        oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(4)));
        //共有情况
        oo.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));
        //建筑面积
        oo.setFloorArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(6))));
        //房屋坐落
        oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
        //街道号
        oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));
        try {
            //附号
            oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));
        } catch (Exception e1) {
            //附号可以允许不填写 ==>不用抛出异常
        }
        //栋号
        oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));
        //单元
        oo.setUnit(PoiUtils.getCellValue(row.getCell(11)));
        //楼层
        oo.setFloor(Integer.parseInt(PoiUtils.getCellValue(row.getCell(12))));
        //房号
        oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));
        //登记时间
        oo.setRegistrationTime(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));
        //登记日期
        oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(15)), null));
        //规划用途
        oo.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));
        //总层数
        oo.setFloorCount(Integer.parseInt(PoiUtils.getCellValue(row.getCell(17))));
        //证载面积
        oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));
        //套内面积
        oo.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));
        //其它
        oo.setOther(PoiUtils.getCellValue(row.getCell(20)));
        //土地证号
        oo.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));
        //土地取得方式
        oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));
        //土地使用年限起
        oo.setUseStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));
        //土地使用年限止
        oo.setUseEndDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(24)), null));
        //公摊面积
        oo.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
        //附记其它
        oo.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));
        //登记机关
        oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));
        //房屋性质
        oo.setNature(PoiUtils.getCellValue(row.getCell(28)));
        //地号
        oo.setLandNumber(PoiUtils.getCellValue(row.getCell(29)));
        //图号
        oo.setGraphNumber(PoiUtils.getCellValue(row.getCell(30)));
        //取得价格
        oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(32))));
        //使用权类型
        oo.setUseRightType(PoiUtils.getCellValue(row.getCell(33)));
        return true;
    }

    /**
     * 土地证
     * @param declareRealtyLandCert
     * @param builder
     * @param row
     * @param i
     * @param land_uses
     * @return
     * @throws Exception
     */
    public boolean land(DeclareRealtyLandCert declareRealtyLandCert, StringBuilder builder, Row row, int i, List<BaseDataDic> land_uses)throws Exception{
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(25));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(26));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(27));
        declareRealtyLandCert.setProvince(provinceName);
        declareRealtyLandCert.setCity(cityName);
        declareRealtyLandCert.setDistrict(districtName);
        Map<String,String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
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
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))){
            declareRealtyLandCert.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))){
            declareRealtyLandCert.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))){
            declareRealtyLandCert.setDistrict(map.get("district"));
        }
        //验证 类型(省略已经在excel配置了下拉框)
        //土地权证号
        declareRealtyLandCert.setLandCertName(PoiUtils.getCellValue(row.getCell(0)));
        //所在地
        declareRealtyLandCert.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        String type = PoiUtils.getCellValue(row.getCell(2));
        //类型
        if (!org.springframework.util.StringUtils.isEmpty(type)) {
            declareRealtyLandCert.setType(type);
        }
        //土地使用权人
        declareRealtyLandCert.setOwnership(PoiUtils.getCellValue(row.getCell(3)));
        //年份
        declareRealtyLandCert.setYear(PoiUtils.getCellValue(row.getCell(4)));
        //编号
        declareRealtyLandCert.setNumber(PoiUtils.getCellValue(row.getCell(5)));
        //房屋坐落
        declareRealtyLandCert.setBeLocated(PoiUtils.getCellValue(row.getCell(6)));
        //街道号
        declareRealtyLandCert.setStreetNumber(PoiUtils.getCellValue(row.getCell(7)));
        try {
            //附号
            declareRealtyLandCert.setAttachedNumber(PoiUtils.getCellValue(row.getCell(8)));
        } catch (Exception e1) {
            //附号可以允许不填写 ==>不用抛出异常
        }
        //栋号
        declareRealtyLandCert.setBuildingNumber(PoiUtils.getCellValue(row.getCell(9)));
        //单元
        declareRealtyLandCert.setUnit(PoiUtils.getCellValue(row.getCell(10)));
        //楼层
        declareRealtyLandCert.setFloor(PoiUtils.getCellValue(row.getCell(11)));
        //房号
        declareRealtyLandCert.setRoomNumber(PoiUtils.getCellValue(row.getCell(12)));
        //地号
        declareRealtyLandCert.setLandNumber(PoiUtils.getCellValue(row.getCell(13)));
        //图号
        declareRealtyLandCert.setGraphNumber(PoiUtils.getCellValue(row.getCell(14)));
        //取得价格
        declareRealtyLandCert.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(16))));
        //使用权类型
        declareRealtyLandCert.setUseRightType(PoiUtils.getCellValue(row.getCell(17)));
        //终止日期
        declareRealtyLandCert.setTerminationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(18)), null));
        //使用权面积
        declareRealtyLandCert.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));
        //独用面积
        declareRealtyLandCert.setAcreage(new BigDecimal(PoiUtils.getCellValue(row.getCell(20))));
        //分摊面积
        declareRealtyLandCert.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(21))));
        //登记机关
        declareRealtyLandCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(22)));
        //登记日期
        declareRealtyLandCert.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));
        //记事
        declareRealtyLandCert.setMemo(PoiUtils.getCellValue(row.getCell(24)));
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
        //验证 类型(省略已经在excel配置了下拉框)

        declareRealtyHouseCert.setCertName(PoiUtils.getCellValue(row.getCell(0)));
        declareRealtyHouseCert.setLocation(PoiUtils.getCellValue(row.getCell(1)));
        declareRealtyHouseCert.setNumber(PoiUtils.getCellValue(row.getCell(2)));
        String type = PoiUtils.getCellValue(row.getCell(3));
        //类型
        if (!org.springframework.util.StringUtils.isEmpty(type)) {
            declareRealtyHouseCert.setType(type);
        }
        //房屋所有权人
        declareRealtyHouseCert.setOwnership(PoiUtils.getCellValue(row.getCell(4)));
        //共有情况
        declareRealtyHouseCert.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));
        //建筑面积
        declareRealtyHouseCert.setFloorArea(PoiUtils.getCellValue(row.getCell(6)));
        //房屋坐落
        declareRealtyHouseCert.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));
        //街道号
        declareRealtyHouseCert.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));
        try {
            //附号
            declareRealtyHouseCert.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));
        } catch (Exception e1) {
            //附号可以允许不填写 ==>不用抛出异常
        }
        //栋号
        declareRealtyHouseCert.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));
        //单元
        declareRealtyHouseCert.setUnit(PoiUtils.getCellValue(row.getCell(11)));
        //楼层
        declareRealtyHouseCert.setFloor(PoiUtils.getCellValue(row.getCell(12)));
        //房号
        declareRealtyHouseCert.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));
        //登记时间
        declareRealtyHouseCert.setRegistrationTime(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));
        //登记日期
        declareRealtyHouseCert.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(15)), null));
        //规划用途
        declareRealtyHouseCert.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));
        //总层数
        declareRealtyHouseCert.setFloorCount(PoiUtils.getCellValue(row.getCell(17)));
        //证载面积
        declareRealtyHouseCert.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));
        //套内面积
        declareRealtyHouseCert.setInnerArea(PoiUtils.getCellValue(row.getCell(19)));
        //其它
        declareRealtyHouseCert.setOther(PoiUtils.getCellValue(row.getCell(20)));
        //土地证号
        declareRealtyHouseCert.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));
        //土地取得方式
        declareRealtyHouseCert.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));
        //土地使用年限起
        declareRealtyHouseCert.setUseStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));
        //土地使用年限止
        declareRealtyHouseCert.setUseEndDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(24)), null));
        //公摊面积
        declareRealtyHouseCert.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));
        //附记其它
        declareRealtyHouseCert.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));
        //登记机关
        declareRealtyHouseCert.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));
        //房屋性质
        declareRealtyHouseCert.setNature(PoiUtils.getCellValue(row.getCell(28)));
        return true;
    }

    /**
     * 在建工程（土建）
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean buildEngineering(DeclareBuildEngineering oo,StringBuilder builder,Row row, int i)throws Exception{
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(14));
        //市或者区
        String cityName = PoiUtils.getCellValue(row.getCell(15));
        //县
        String districtName = PoiUtils.getCellValue(row.getCell(16));
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);
        Map<String,String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))){
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))){
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))){
            oo.setDistrict(map.get("district"));
        }
        //占有单位
        oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));
        //项目名称
        oo.setName(PoiUtils.getCellValue(row.getCell(1)));
        //坐落
        oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));
        //结构
        oo.setStructure(PoiUtils.getCellValue(row.getCell(3)));
        //建筑面积
        oo.setBuildArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));
        //开工日期
        oo.setStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(5)), null));
        //预期完成日期
        oo.setExpectedCompletionDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(6)), null));
        //形象进度
        oo.setSpeedProgress(PoiUtils.getCellValue(row.getCell(7)));
        //付款比例
        oo.setPaymentRatio(PoiUtils.getCellValue(row.getCell(8)));
        //账面价值
        oo.setBookValue(PoiUtils.getCellValue(row.getCell(9)));
        //帐面净值
        oo.setBookNetValue(PoiUtils.getCellValue(row.getCell(10)));
        //申报人
        oo.setDeclarer(PoiUtils.getCellValue(row.getCell(11)));
        //申报日期
        oo.setDeclarationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(12)), null));
        //申报人
        oo.setRemark(PoiUtils.getCellValue(row.getCell(13)));
        return true;
    }

    /**
     * 在建工程（设备安装）
     * @param oo
     * @param builder
     * @param row
     * @param i
     * @return
     * @throws Exception
     */
    public boolean buildEquipmentInstall(DeclareBuildEquipmentInstall oo,StringBuilder builder,Row row, int i)throws Exception{
        //省
        String provinceName = PoiUtils.getCellValue(row.getCell(16));
        //市
        String cityName = PoiUtils.getCellValue(row.getCell(17));
        //县或者区
        String districtName = PoiUtils.getCellValue(row.getCell(18));
        oo.setProvince(provinceName);
        oo.setCity(cityName);
        oo.setDistrict(districtName);
        Map<String,String> map = new HashMap<>();
        //验证(区域)
        if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("province"))){
            oo.setProvince(map.get("province"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("city"))){
            oo.setCity(map.get("city"));
        }
        if (!org.springframework.util.StringUtils.isEmpty(map.get("district"))){
            oo.setDistrict(map.get("district"));
        }
        //占有单位
        oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));
        //项目名称
        oo.setName(PoiUtils.getCellValue(row.getCell(1)));
        //坐落
        oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));
        //规格型号
        oo.setSpecificationModel(PoiUtils.getCellValue(row.getCell(3)));
        //生产厂家
        oo.setManufacturer(PoiUtils.getCellValue(row.getCell(4)));
        //计量单位
        oo.setMeasurementUnit(PoiUtils.getCellValue(row.getCell(5)));
        //数量
        oo.setNumber(Integer.parseInt(PoiUtils.getCellValue(row.getCell(6))));
        //开工日期
        oo.setStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(7)), null));
        //预期完成日期
        oo.setExpectedCompletionDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(8)), null));
        //帐面设备费
        oo.setBookEquipmentFee(PoiUtils.getCellValue(row.getCell(9)));
        //账面资金成本
        oo.setBookCapitalCost(PoiUtils.getCellValue(row.getCell(10)));
        //账面安装费
        oo.setBookInstallationFee(PoiUtils.getCellValue(row.getCell(11)));
        //其它
        oo.setOther(PoiUtils.getCellValue(row.getCell(12)));
        //申报人
        oo.setDeclarer(PoiUtils.getCellValue(row.getCell(13)));
        //申报日期
        oo.setDeclarationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));
        //备注
        oo.setRemark(PoiUtils.getCellValue(row.getCell(15)));
        return true;
    }
}
