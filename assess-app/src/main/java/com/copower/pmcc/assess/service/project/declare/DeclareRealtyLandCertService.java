package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:06
 * @Description:土地证
 */
@Service
public class DeclareRealtyLandCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;

    /**
     * 功能描述: 关联房产证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importDataLand(DeclareRealtyLandCert declareRealtyLandCert, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        Sheet sheet = workbook.getSheetAt(0);//只取第一个sheet
        //工作表的第一行
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getLastCellNum();
        int startRowNumber = 1;//读取数据的起始行
        int successCount = 0;//导入成功数据条数
        //总行数
        int rowLength = sheet.getLastRowNum() + 1 - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DeclareRealtyHouseCert oo = null;
            boolean flag = true;//标识符
            try {
                row = sheet.getRow(i);
                oo = new DeclareRealtyHouseCert();
                oo.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
                String provinceName = PoiUtils.getCellValue(row.getCell(29));//省
                String cityName = PoiUtils.getCellValue(row.getCell(30));//市或者区
                String districtName = PoiUtils.getCellValue(row.getCell(31));//县
                oo.setProvince(provinceName);
                oo.setCity(cityName);
                oo.setDistrict(districtName);
                Map<String,String> map = new HashMap<>();
                //验证(区域)
                if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
                    builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
                    continue;
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
                oo.setCertName(PoiUtils.getCellValue(row.getCell(0)));
                oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));
                oo.setNumber(PoiUtils.getCellValue(row.getCell(2)));
                String type = PoiUtils.getCellValue(row.getCell(3));
                if (!org.springframework.util.StringUtils.isEmpty(type)) {//类型
                    oo.setType(type);
                }
                oo.setOwnership(PoiUtils.getCellValue(row.getCell(4)));//房屋所有权人
                oo.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));//共有情况
                oo.setFloorArea(PoiUtils.getCellValue(row.getCell(6)));//建筑面积
                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));//房屋坐落
                oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));//街道号
                try {
                    oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));//附号
                } catch (Exception e1) {
                    //附号可以允许不填写 ==>不用抛出异常
                }
                oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));//栋号
                oo.setUnit(PoiUtils.getCellValue(row.getCell(11)));//单元
                oo.setFloor(PoiUtils.getCellValue(row.getCell(12)));//楼层
                oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));//房号

                oo.setRegistrationTime(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));//登记时间
                oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(15)), null));//登记日期
                oo.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));//规划用途
                oo.setFloorCount(PoiUtils.getCellValue(row.getCell(17)));//总层数
                oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));//证载面积
                oo.setInnerArea(PoiUtils.getCellValue(row.getCell(19)));//套内面积
                oo.setOther(PoiUtils.getCellValue(row.getCell(20)));//其它
                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));//土地证号
                oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));//土地取得方式

                oo.setUseStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));//土地使用年限起
                oo.setUseEndDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(24)), null));//土地使用年限止
                oo.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));//公摊面积
                oo.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));//附记其它
                oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));//登记机关
                oo.setNature(PoiUtils.getCellValue(row.getCell(28)));//房屋性质
                oo.setEnable("no");//不启用 (说明是关联数据)
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                //对匹配进行小修改 权证号匹配改为房产证的土地证号和土地证图号的进行匹配
                if (!org.springframework.util.StringUtils.isEmpty(oo.getLandNumber())) {
                    DeclareRealtyLandCert declareRealtyLandCert1 = new DeclareRealtyLandCert();
                    declareRealtyLandCert1.setGraphNumber(oo.getLandNumber());
                    declareRealtyLandCert1.setPid(0);
                    List<DeclareRealtyLandCertVo> voList = landLevels(declareRealtyLandCert1);
                    if (!ObjectUtils.isEmpty(voList)) {
                        DeclareRealtyLandCert declareRealtyLandCert2 = voList.get(0);
                        oo.setCreator(commonService.thisUserAccount());
                        oo.setPid(0);
                        Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                        declareRealtyLandCert2.setPid(id);
                        declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert2);
                        successCount++;
                    } else {
                        //所有权人如果和座落匹配那么也进行关联
                        declareRealtyLandCert1 = null;
                        declareRealtyLandCert1 = new DeclareRealtyLandCert();
                        declareRealtyLandCert1.setBeLocated(oo.getBeLocated());
                        declareRealtyLandCert1.setOwnership(oo.getOwnership());
                        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert1);
                        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
                            Ordering<DeclareRealtyLandCert> firstOrdering  = Ordering.from(new Comparator<DeclareRealtyLandCert>() {
                                @Override
                                public int compare(DeclareRealtyLandCert o1, DeclareRealtyLandCert o2) {
                                    return o1.getId().compareTo(o2.getId());
                                }
                            }).reverse();//排序 并且反转 == > 从大到小
                            Collections.sort(declareRealtyLandCerts, firstOrdering);
                            DeclareRealtyLandCert declareRealtyLandCert2 = declareRealtyLandCerts.get(0);
                            oo.setCreator(commonService.thisUserAccount());
                            oo.setPid(0);
                            Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                            declareRealtyLandCert2.setPid(id);
                            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert2);
                            successCount++;
                        } else {
                            builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
                        }
                    }
                }else {//即使土地证号为null还是会进行所有权人和座落的匹配
                    //所有权人如果和座落匹配那么也进行关联
                    DeclareRealtyLandCert declareRealtyLandCert1 = new DeclareRealtyLandCert();
                    declareRealtyLandCert1.setBeLocated(oo.getBeLocated());
                    declareRealtyLandCert1.setOwnership(oo.getOwnership());
                    List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert1);
                    if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
                        Ordering<DeclareRealtyLandCert> firstOrdering  = Ordering.from(new Comparator<DeclareRealtyLandCert>() {
                            @Override
                            public int compare(DeclareRealtyLandCert o1, DeclareRealtyLandCert o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                        }).reverse();//排序 并且反转 == > 从大到小
                        Collections.sort(declareRealtyLandCerts, firstOrdering);
                        DeclareRealtyLandCert declareRealtyLandCert2 = declareRealtyLandCerts.get(0);
                        oo.setCreator(commonService.thisUserAccount());
                        oo.setPid(0);
                        Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                        declareRealtyLandCert2.setPid(id);
                        declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert2);
                        successCount++;
                    } else {
                        builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
                    }
                }
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    /**
     * 功能描述: 导入土地证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:30
     */
    public String importData(DeclareRealtyLandCert declareRealtyLandCert, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        Sheet sheet = workbook.getSheetAt(0);//只取第一个sheet
        //工作表的第一行
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getLastCellNum();
        int startRowNumber = 1;//读取数据的起始行
        int successCount = 0;//导入成功数据条数
        //总行数
        int rowLength = sheet.getLastRowNum() + 1 - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        List<BaseDataDic> land_uses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            boolean flag = true;//标识符
            DeclareRealtyLandCert oo = null;
            try {
                oo = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                String provinceName = PoiUtils.getCellValue(row.getCell(25));//省
                String cityName = PoiUtils.getCellValue(row.getCell(26));//市或者区
                String districtName = PoiUtils.getCellValue(row.getCell(27));//县
                oo.setProvince(provinceName);
                oo.setCity(cityName);
                oo.setDistrict(districtName);
                Map<String,String> map = new HashMap<>();
                //验证(区域)
                if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder,map)) {
                    builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
                    continue;
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
                String purpose = PoiUtils.getCellValue(row.getCell(15));
                BaseDataDic typeDic = baseDataDicService.getDataDicByName(land_uses, purpose);
                if (typeDic == null) {
                    builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                    continue;
                } else {
                    purpose = String.valueOf(typeDic.getId());
                }
                oo.setPurpose(purpose);//用途

                oo.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
                oo.setLandCertName(PoiUtils.getCellValue(row.getCell(0)));//土地权证号
                oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));//所在地
                String type = PoiUtils.getCellValue(row.getCell(2));
                if (!org.springframework.util.StringUtils.isEmpty(type)) {//类型
                    oo.setType(type);
                }
                oo.setOwnership(PoiUtils.getCellValue(row.getCell(3)));//土地使用权人
                oo.setYear(PoiUtils.getCellValue(row.getCell(4)));//年份
                oo.setNumber(PoiUtils.getCellValue(row.getCell(5)));//编号
                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(6)));//房屋坐落
                oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(7)));//街道号
                try {
                    oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(8)));//附号
                } catch (Exception e1) {
                    //附号可以允许不填写 ==>不用抛出异常
                }
                oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(9)));//栋号
                oo.setUnit(PoiUtils.getCellValue(row.getCell(10)));//单元
                oo.setFloor(PoiUtils.getCellValue(row.getCell(11)));//楼层
                oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(12)));//房号
                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(13)));//地号
                oo.setGraphNumber(PoiUtils.getCellValue(row.getCell(14)));//图号
                oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(16))));//取得价格
                oo.setUseRightType(PoiUtils.getCellValue(row.getCell(17)));//使用权类型
                oo.setTerminationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(18)), null));//终止日期
                oo.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));//使用权面积
                oo.setAcreage(new BigDecimal(PoiUtils.getCellValue(row.getCell(20))));//独用面积
                oo.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(21))));//分摊面积
                oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(22)));//登记机关
                oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));//登记日期
                oo.setMemo(PoiUtils.getCellValue(row.getCell(24)));//记事

            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setPid(0);
                oo.setEnable("yes"); //启用 (说明不是关联数据)
                declareRealtyLandCertDao.addDeclareRealtyLandCert(oo);
                successCount++;
            }
        }

        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert.getId() == null) {
            declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            DeclareRealtyHouseCert oo = null;
            Integer pid = declareRealtyLandCert.getPid();
            //处理关联数据
            Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
            declareRealtyLandCert.setId(id);
            if (pid != null && pid.intValue() != 0) {
                oo = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(pid);
                if (oo != null) {
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getCity())) {
                        declareRealtyLandCert.setCity(oo.getCity());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getDistrict())) {
                        declareRealtyLandCert.setDistrict(oo.getDistrict());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getProvince())) {
                        declareRealtyLandCert.setProvince(oo.getProvince());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getFloor())) {
                        declareRealtyLandCert.setFloor(oo.getFloor());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getRoomNumber())) {
                        declareRealtyLandCert.setRoomNumber(oo.getRoomNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getUnit())) {
                        declareRealtyLandCert.setUnit(oo.getUnit());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBuildingNumber())) {
                        declareRealtyLandCert.setBuildingNumber(oo.getBuildingNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getAttachedNumber())) {
                        declareRealtyLandCert.setAttachedNumber(oo.getAttachedNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getStreetNumber())) {
                        declareRealtyLandCert.setStreetNumber(oo.getStreetNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBeLocated())) {
                        declareRealtyLandCert.setBeLocated(oo.getBeLocated());
                    }
                    oo.setPid(id);
                    declareRealtyHouseCertService.saveAndUpdateDeclareRealtyHouseCert(oo);
                    declareRealtyLandCert.setEnable("no");//不启用 (说明是关联数据)
                }
            }else {
                declareRealtyLandCert.setEnable("yes"); //启用 (说明不是关联数据)
            }
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), id);
            return id;
        } else {
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
            return null;
        }
    }

    public DeclareRealtyLandCert getDeclareRealtyLandCertById(Integer id) {
        return declareRealtyLandCertDao.getDeclareRealtyLandCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyLandCertListVos(DeclareRealtyLandCert declareRealtyLandCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyLandCertVo> vos = landLevels(declareRealtyLandCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyLandCertVo> landLevels(DeclareRealtyLandCert declareRealtyLandCert) {
        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
        List<DeclareRealtyLandCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
            for (DeclareRealtyLandCert landLevel : declareRealtyLandCerts) {
                vos.add(getDeclareRealtyLandCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert) {
        declareRealtyLandCertDao.removeDeclareRealtyLandCert(declareRealtyLandCert);
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        if (StringUtils.isNotBlank(declareRealtyLandCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyLandCert.getProvince()));//省
            } else {
                vo.setProvinceName(declareRealtyLandCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyLandCert.getCity()));//市或者县
            } else {
                vo.setCityName(declareRealtyLandCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyLandCert.getDistrict()));//县
            } else {
                vo.setDistrictName(declareRealtyLandCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyLandCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

    public void eventWriteDeclareInfo(DeclareInfo declareInfo){
        DeclareRecord declareRecord = null;
        if (declareInfo == null) {
            return;
        }
        DeclareRealtyLandCert query = new DeclareRealtyLandCert();
        query.setPlanDetailsId(declareInfo.getPlanDetailsId());
        List<DeclareRealtyLandCert> lists = declareRealtyLandCertDao.getDeclareRealtyLandCertList(query);
        for (DeclareRealtyLandCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo,declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareInfo.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getLandCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setFloorArea(oo.getUseRightArea());
            declareRecord.setLandUseEndDate(oo.getTerminationDate());
            BaseDataDic baseDataDic = null;
            if (oo.getPurpose() != null){
                if (NumberUtils.isNumber(oo.getPurpose())){
                    baseDataDic= baseDataDicService.getDataDicById(Integer.parseInt(oo.getPurpose()));
                    if (baseDataDic != null){
                        declareRecord.setCertUse(baseDataDic.getName());
                    }
                }
            }
            /**
             * cert_use` varchar(100) DEFAULT NULL COMMENT '证载用途',
             `practical_use` varchar(100) DEFAULT NULL COMMENT '实际用途',
             */
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!",e1);
            }
        }
    }
}
