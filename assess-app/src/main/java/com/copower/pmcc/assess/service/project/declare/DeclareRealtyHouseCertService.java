package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:04
 * @Description:房产证
 */
@Service
public class DeclareRealtyHouseCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;

    /**
     *
     * 功能描述: 导入土地证 并且和房产证关联
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 17:58
     */
    public String importDataLand(DeclareRealtyHouseCert declareRealtyHouseCert, MultipartFile multipartFile)throws Exception{
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
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            boolean flag = true;//标识符
            DeclareRealtyLandCert oo = null;
            try {
                oo = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                oo.setPlanDetailsId(declareRealtyHouseCert.getPlanDetailsId());
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
                oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(8)));//附号
                oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(9)));//栋号
                oo.setUnit(PoiUtils.getCellValue(row.getCell(10)));//单元
                oo.setFloor(PoiUtils.getCellValue(row.getCell(11)));//楼层
                oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(12)));//房号
                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(13)));//地号
                oo.setGraphNumber(PoiUtils.getCellValue(row.getCell(14)));//图号
                oo.setPurpose(PoiUtils.getCellValue(row.getCell(15)));//用途
                oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(16))));//取得价格
                oo.setUseRightType(PoiUtils.getCellValue(row.getCell(17)));//使用权类型
                oo.setTerminationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(18)), null));//终止日期
                oo.setUseRightArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));//使用权面积
                oo.setAcreage(new BigDecimal(PoiUtils.getCellValue(row.getCell(20))));//独用面积
                oo.setApportionmentArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(21))));//分摊面积
                oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(22)));//登记机关
                oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));//登记日期
                oo.setMemo(PoiUtils.getCellValue(row.getCell(24)));//记事

                oo.setProvince(PoiUtils.getCellValue(row.getCell(25)));//省
                oo.setCity(PoiUtils.getCellValue(row.getCell(26)));//市
                oo.setDistrict(PoiUtils.getCellValue(row.getCell(27)));//区/县
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                if (!org.springframework.util.StringUtils.isEmpty(oo.getLandCertName())){
                    //当导入土地证的时候首先与房屋权证号匹配 然后在于土地使用权人匹配 如果都匹配不成功那么提示导入失败 并提示原因
                    DeclareRealtyHouseCert declareRealtyHouseCert1 = new DeclareRealtyHouseCert();
                    declareRealtyHouseCert1.setCertName(oo.getLandCertName());
                    declareRealtyHouseCert1.setPid(0);
                    List<DeclareRealtyHouseCertVo> voList = landLevels(declareRealtyHouseCert1);
                    if (!ObjectUtils.isEmpty(voList)){
                        oo.setCreator(commonService.thisUserAccount());
                        Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(oo);
                        successCount++;
                        DeclareRealtyHouseCertVo declareRealtyHouseCertVo = voList.get(0);
                        declareRealtyHouseCertVo.setPid(id);
                        declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCertVo);
                    }else {
                        builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
                    }
                }
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    /**
     *
     * 功能描述: 导入房产证数据
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 17:57
     */
    public String importData(DeclareRealtyHouseCert declareRealtyHouseCert, MultipartFile multipartFile) throws Exception {
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
                oo.setPlanDetailsId(declareRealtyHouseCert.getPlanDetailsId());
                oo.setCertName(PoiUtils.getCellValue(row.getCell(0)));
                oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));
                oo.setNumber(PoiUtils.getCellValue(row.getCell(2)));
                String type = PoiUtils.getCellValue(row.getCell(3));
                if (!org.springframework.util.StringUtils.isEmpty(type)) {//类型
                    oo.setType(type);
                }
                oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(4)));//土地取得方式
                oo.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));//共有情况
                oo.setFloorArea(PoiUtils.getCellValue(row.getCell(6)));//建筑面积
                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));//房屋坐落
                oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));//街道号
                oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));//附号
                oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));//栋号
                oo.setUnit(PoiUtils.getCellValue(row.getCell(11)));//单元
                oo.setFloor(PoiUtils.getCellValue(row.getCell(12)));//楼层
                oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));//房号

                oo.setRegistrationTime(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)),null));//登记时间
                oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(15)),null));//登记日期
                oo.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));//规划用途
                oo.setFloorCount(PoiUtils.getCellValue(row.getCell(17)));//总层数
                oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));//证载面积
                oo.setInnerArea(PoiUtils.getCellValue(row.getCell(19)));//套内面积
                oo.setOther(PoiUtils.getCellValue(row.getCell(20)));//其它
                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));//土地证号
                oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));//土地取得方式

                oo.setUseStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)),null));//土地使用年限起
                oo.setUseEndDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(24)),null));//土地使用年限止
                oo.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));//公摊面积
                oo.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));//附记其它
                oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));//登记机关
                oo.setNature(PoiUtils.getCellValue(row.getCell(28)));//房屋性质


                oo.setProvince(PoiUtils.getCellValue(row.getCell(29)));//省
                oo.setCity(PoiUtils.getCellValue(row.getCell(30)));//市
                oo.setDistrict(PoiUtils.getCellValue(row.getCell(31)));//区/县
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setPid(0);
                declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert.getId() == null) {
            declareRealtyHouseCert.setCreator(commonService.thisUserAccount());
            Integer pid = declareRealtyHouseCert.getPid();
            DeclareRealtyLandCert oo = null;
            //处理关联数据
            if (pid != null && pid.intValue() != 0) {
                oo = declareRealtyLandCertService.getDeclareRealtyLandCertById(pid);
                if (oo != null) {
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getCity())) {
                        declareRealtyHouseCert.setCity(oo.getCity());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getDistrict())) {
                        declareRealtyHouseCert.setDistrict(oo.getDistrict());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getProvince())) {
                        declareRealtyHouseCert.setProvince(oo.getProvince());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getFloor())) {
                        declareRealtyHouseCert.setFloor(oo.getFloor());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getRoomNumber())) {
                        declareRealtyHouseCert.setRoomNumber(oo.getRoomNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getUnit())) {
                        declareRealtyHouseCert.setUnit(oo.getUnit());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBuildingNumber())) {
                        declareRealtyHouseCert.setBuildingNumber(oo.getBuildingNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getAttachedNumber())) {
                        declareRealtyHouseCert.setAttachedNumber(oo.getAttachedNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getStreetNumber())) {
                        declareRealtyHouseCert.setStreetNumber(oo.getStreetNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBeLocated())) {
                        declareRealtyHouseCert.setBeLocated(oo.getBeLocated());
                    }
                }
            }
            Integer id = null;
            id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(declareRealtyHouseCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), id);
            return id;
        } else {
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            return null;
        }
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyHouseCertListVos(DeclareRealtyHouseCert declareRealtyHouseCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyHouseCertVo> vos = landLevels(declareRealtyHouseCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyHouseCertVo> landLevels(DeclareRealtyHouseCert declareRealtyHouseCert) {
        List<DeclareRealtyHouseCert> declareRealtyHouseCerts = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(declareRealtyHouseCert);
        List<DeclareRealtyHouseCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyHouseCerts)) {
            for (DeclareRealtyHouseCert landLevel : declareRealtyHouseCerts) {
                vos.add(getDeclareRealtyHouseCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        try {
            declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(declareRealtyHouseCert);
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyHouseCertVo getDeclareRealtyHouseCertVo(DeclareRealtyHouseCert declareRealtyHouseCert) {
        DeclareRealtyHouseCertVo vo = new DeclareRealtyHouseCertVo();
        BeanUtils.copyProperties(declareRealtyHouseCert, vo);
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getProvince()));//省
            }else {
                vo.setProvinceName(declareRealtyHouseCert.getProvince());//省
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getCity()));//市或者县
            }else {
                vo.setCityName(declareRealtyHouseCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getDistrict()));//县
            }else {
                vo.setDistrictName(declareRealtyHouseCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyHouseCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
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

}
