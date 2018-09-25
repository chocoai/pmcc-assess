package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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
import java.util.List;

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
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            boolean flag = true;//标识符
            DeclareRealtyLandCert oo = null;
            try {
                oo = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
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
                }
            }
            Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
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
        try {
            declareRealtyLandCertDao.removeDeclareRealtyLandCert(declareRealtyLandCert);
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        if (StringUtils.isNotBlank(declareRealtyLandCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyLandCert.getProvince()));//省
            }else {
                vo.setProvinceName(declareRealtyLandCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyLandCert.getCity()));//市或者县
            }else {
                vo.setCityName(declareRealtyLandCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getDistrict())){
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyLandCert.getDistrict()));//县
            }else {
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
}
