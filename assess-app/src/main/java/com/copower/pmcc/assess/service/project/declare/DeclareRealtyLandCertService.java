package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
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
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePoiHelp;

    /**
     * 功能描述: 关联房产证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importLandAndHouse(MultipartFile multipartFile) throws Exception {
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
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DeclareRealtyHouseCert houseCert = null;
            //标识符
            boolean flag = true;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                houseCert = new DeclareRealtyHouseCert();
                if (!declarePoiHelp.house(houseCert, builder, row, i)) {
                    continue;
                }
                //不启用 (关联数据)
                houseCert.setEnable("no");
                houseCert.setCreator(commonService.thisUserAccount());
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            //匹配关系  房产证的土地证号和土地证图号的进行匹配 || 房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
            List<DeclareRealtyLandCert> listA = null;
            List<DeclareRealtyLandCert> listB = null;
            if (flag) {
                //房产证的土地证号和土地证图号
                if (StringUtils.isNotBlank(houseCert.getLandNumber())) {
                    DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
                    declareRealtyLandCert.setGraphNumber(houseCert.getLandNumber());
                    listA = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
                }
                //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                if (StringUtils.isNotBlank(houseCert.getOwnership()) && StringUtils.isNotBlank(houseCert.getBeLocated())) {
                    DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
                    declareRealtyLandCert.setBeLocated(houseCert.getBeLocated());
                    declareRealtyLandCert.setOwnership(houseCert.getOwnership());
                    listB = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
                }
                //房产证的土地证号和土地证图号匹配
                if (!ObjectUtils.isEmpty(listA)) {
                    Ordering<DeclareRealtyLandCert> firstOrdering = Ordering.from(new Comparator<DeclareRealtyLandCert>() {
                        @Override
                        public int compare(DeclareRealtyLandCert o1, DeclareRealtyLandCert o2) {
                            return o1.getId().compareTo(o2.getId());
                        }
                    }).reverse();//排序 并且反转 == > 从大到小
                    Collections.sort(listA, firstOrdering);
                    Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(houseCert);
                    DeclareRealtyLandCert declareRealtyLandCert = listA.get(0);
                    declareRealtyLandCert.setPid(id);
                    declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
                    successCount++;
                }
                //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                if (!ObjectUtils.isEmpty(listB)) {
                    Ordering<DeclareRealtyLandCert> firstOrdering = Ordering.from(new Comparator<DeclareRealtyLandCert>() {
                        @Override
                        public int compare(DeclareRealtyLandCert o1, DeclareRealtyLandCert o2) {
                            return o1.getId().compareTo(o2.getId());
                        }
                    }).reverse();//排序 并且反转 == > 从大到小
                    Collections.sort(listB, firstOrdering);
                    Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(houseCert);
                    DeclareRealtyLandCert declareRealtyLandCert = listB.get(0);
                    declareRealtyLandCert.setPid(id);
                    declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
                    successCount++;
                }
                //两种匹配方式都未匹配到
                if (ObjectUtils.isEmpty(listB) && ObjectUtils.isEmpty(listA)) {
                    builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
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
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        List<BaseDataDic> land_uses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareRealtyLandCert oo = null;
            try {
                oo = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                if (!declarePoiHelp.land(oo, builder, row, i, land_uses)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setPid(0);
                oo.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
                //启用 (非关联数据)
                oo.setEnable("yes");
                declareRealtyLandCertDao.addDeclareRealtyLandCert(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    /**
     * 处理关联数据
     *
     * @param declareRealtyLandCert
     */
    private void relation(DeclareRealtyLandCert declareRealtyLandCert) {
        DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(declareRealtyLandCert.getPid());
        if (declareRealtyHouseCert != null) {
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getCity())) {
                declareRealtyLandCert.setCity(declareRealtyHouseCert.getCity());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getDistrict())) {
                declareRealtyLandCert.setDistrict(declareRealtyHouseCert.getDistrict());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getProvince())) {
                declareRealtyLandCert.setProvince(declareRealtyHouseCert.getProvince());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getFloor())) {
                declareRealtyLandCert.setFloor(declareRealtyHouseCert.getFloor());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getRoomNumber())) {
                declareRealtyLandCert.setRoomNumber(declareRealtyHouseCert.getRoomNumber());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getUnit())) {
                declareRealtyLandCert.setUnit(declareRealtyHouseCert.getUnit());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getBuildingNumber())) {
                declareRealtyLandCert.setBuildingNumber(declareRealtyHouseCert.getBuildingNumber());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getAttachedNumber())) {
                declareRealtyLandCert.setAttachedNumber(declareRealtyHouseCert.getAttachedNumber());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getStreetNumber())) {
                declareRealtyLandCert.setStreetNumber(declareRealtyHouseCert.getStreetNumber());
            }
            if (!org.springframework.util.StringUtils.isEmpty(declareRealtyHouseCert.getBeLocated())) {
                declareRealtyLandCert.setBeLocated(declareRealtyHouseCert.getBeLocated());
            }
            declareRealtyHouseCert.setPid(declareRealtyLandCert.getId());
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
        }
    }

    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert.getId() == null) {
            declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            Integer pid = declareRealtyLandCert.getPid();
            Integer id = null;
            if (pid != null && pid.intValue() != 0) {
                //不启用 (关联数据)
                declareRealtyLandCert.setEnable("no");
                //处理从表数据写入 tb_declare_record 表问题
                if (declareRealtyLandCert.getPlanDetailsId() != null){
                    declareRealtyLandCert.setPlanDetailsId(0);
                }
                id = declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
                declareRealtyLandCert.setId(id);
                this.relation(declareRealtyLandCert);
            } else {
                //启用 (非关联数据)
                declareRealtyLandCert.setEnable("yes");
                id = declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
            }
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
        List<DeclareRealtyLandCertVo> vos = lists(declareRealtyLandCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyLandCertVo> lists(DeclareRealtyLandCert declareRealtyLandCert) {
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
        if (declareRealtyLandCert.getPid() != null){
            DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(declareRealtyLandCert.getPid());
            if (Objects.equal("no",declareRealtyHouseCert.getEnable())){
                DeclareRealtyHouseCert oo = new DeclareRealtyHouseCert();
                oo.setId(declareRealtyLandCert.getPid());
                declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(oo);
            }
        }
        declareRealtyLandCertDao.removeDeclareRealtyLandCert(declareRealtyLandCert);
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        if (StringUtils.isNotBlank(declareRealtyLandCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyLandCert.getProvince()));
            } else {
                vo.setProvinceName(declareRealtyLandCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getCity())) {
                //市
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyLandCert.getCity()));
            } else {
                vo.setCityName(declareRealtyLandCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getDistrict())) {
                //县或者区
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyLandCert.getDistrict()));
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

    public void eventWriteDeclareInfo(DeclareInfo declareInfo) {
        DeclareRecord declareRecord = null;
        if (declareInfo == null) {
            return;
        }
        DeclareRealtyLandCert query = new DeclareRealtyLandCert();
        query.setPlanDetailsId(declareInfo.getPlanDetailsId());
        List<DeclareRealtyLandCert> lists = declareRealtyLandCertDao.getDeclareRealtyLandCertList(query);
        for (DeclareRealtyLandCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo, declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareInfo.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getLandCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setFloorArea(oo.getUseRightArea());
            declareRecord.setLandUseEndDate(oo.getTerminationDate());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            BaseDataDic baseDataDic = null;
            if (oo.getPurpose() != null) {
                if (NumberUtils.isNumber(oo.getPurpose())) {
                    baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(oo.getPurpose()));
                    if (baseDataDic != null) {
                        declareRecord.setCertUse(baseDataDic.getName());
                    }
                }
            }
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!", e1);
            }
        }
    }
}
