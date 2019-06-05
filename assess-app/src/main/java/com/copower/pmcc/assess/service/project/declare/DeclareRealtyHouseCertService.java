package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.ErpOcrService;
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
import java.util.Collections;
import java.util.Comparator;
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
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePoiHelp;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ErpOcrService erpOcrService;

    /**
     * 功能描述: 导入土地证 并且和房产证关联
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 17:58
     */
    public String importLandAndHouse(MultipartFile multipartFile, Integer planDetailsId) throws Exception {
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
        //----------------------------||----------------------
        List<BaseDataDic> land_uses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareRealtyLandCert landCert = null;
            try {
                landCert = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                if (!declarePoiHelp.land(landCert, builder, row, i)) {
                    continue;
                }
                landCert.setEnable(DeclareTypeEnum.BranchData.getKey());
                landCert.setCreator(commonService.thisUserAccount());
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            //匹配关系  房产证的土地证号和土地证图号的进行匹配 || 房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
            List<DeclareRealtyHouseCert> listA = null;
            List<DeclareRealtyHouseCert> listB = null;
            if (flag) {
                //房产证的土地证号和土地证图号
                if (!org.springframework.util.StringUtils.isEmpty(landCert.getGraphNumber())) {
                    DeclareRealtyHouseCert houseCert = new DeclareRealtyHouseCert();
                    houseCert.setLandNumber(landCert.getGraphNumber());
                    houseCert.setPlanDetailsId(planDetailsId);
                    listA = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(houseCert);
                }
                //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                if (StringUtils.isNotBlank(landCert.getOwnership()) && StringUtils.isNotBlank(landCert.getBeLocated())) {
                    DeclareRealtyHouseCert houseCert = new DeclareRealtyHouseCert();
                    houseCert.setOwnership(landCert.getOwnership());
                    houseCert.setBeLocated(landCert.getBeLocated());
                    houseCert.setPlanDetailsId(planDetailsId);
                    listB = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(houseCert);
                }
                //匹配标识符
                boolean matching = false;
                //房产证的土地证号和土地证图号匹配
                if (!ObjectUtils.isEmpty(listA)) {
                    Ordering<DeclareRealtyHouseCert> ordering = Ordering.from(new Comparator<DeclareRealtyHouseCert>() {
                        @Override
                        public int compare(DeclareRealtyHouseCert o1, DeclareRealtyHouseCert o2) {
                            return o1.getId().compareTo(o2.getId());
                        }
                    }).reverse();//排序 并且反转 == > 从大到小
                    Collections.sort(listA, ordering);
                    Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(landCert);
                    successCount++;
                    DeclareRealtyHouseCert declareRealtyHouseCert = listA.get(0);
                    //说明此房产证已经关联土地证了
                    if (declareRealtyHouseCert.getPid() != null && declareRealtyHouseCert.getPid().intValue() >= 1) {
                        matching = false;
                    } else {
                        declareRealtyHouseCert.setPid(id);
                        declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
                        matching = true;
                    }
                }
                //到这说明 房产证的土地证号和土地证图号未匹配,因此继续匹配
                if (!matching) {
                    //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                    if (!ObjectUtils.isEmpty(listB)) {
                        Ordering<DeclareRealtyHouseCert> ordering = Ordering.from(new Comparator<DeclareRealtyHouseCert>() {
                            @Override
                            public int compare(DeclareRealtyHouseCert o1, DeclareRealtyHouseCert o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                        }).reverse();//排序 并且反转 == > 从大到小
                        Collections.sort(listB, ordering);
                        Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(landCert);
                        successCount++;
                        DeclareRealtyHouseCert declareRealtyHouseCert = listB.get(0);
                        //说明此房产证已经关联土地证了
                        if (declareRealtyHouseCert.getPid() != null && declareRealtyHouseCert.getPid().intValue() >= 1) {
                            matching = false;
                        } else {
                            declareRealtyHouseCert.setPid(id);
                            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
                            matching = true;
                        }
                    }
                }
                //两种匹配方式都未匹配到
                if (!matching) {
                    builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
                }
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    /**
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
            DeclareRealtyHouseCert oo = null;
            //标识符
            boolean flag = true;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                oo = new DeclareRealtyHouseCert();
                BeanUtils.copyProperties(declareRealtyHouseCert,oo);
                oo.setId(null);
                //excel 处理
                if (!declarePoiHelp.house(oo, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setEnable(DeclareTypeEnum.MasterData.getKey());
                declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert.getId() == null) {
            declareRealtyHouseCert.setCreator(commonService.thisUserAccount());
            Integer id = null;
            id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(declareRealtyHouseCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), id);
            return id;
        } else {
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            return declareRealtyHouseCert.getId();
        }
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyHouseCertListVos(DeclareRealtyHouseCert declareRealtyHouseCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyHouseCertVo> vos = lists(declareRealtyHouseCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyHouseCertVo> lists(DeclareRealtyHouseCert declareRealtyHouseCert) {
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
        if (declareRealtyHouseCert.getPid() != null) {
            DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertDao.getDeclareRealtyLandCertById(declareRealtyHouseCert.getPid());
            if (Objects.equal(DeclareTypeEnum.BranchData.getKey(), declareRealtyLandCert.getEnable())) {
                DeclareRealtyLandCert oo = new DeclareRealtyLandCert();
                oo.setId(declareRealtyHouseCert.getPid());
                declareRealtyLandCertDao.removeDeclareRealtyLandCert(oo);
            }
        }
        declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(declareRealtyHouseCert);
    }

    public DeclareRealtyHouseCertVo getDeclareRealtyHouseCertVo(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert == null) {
            return null;
        }
        DeclareRealtyHouseCertVo vo = new DeclareRealtyHouseCertVo();
        BeanUtils.copyProperties(declareRealtyHouseCert, vo);
        vo.setPlanningUseName(baseDataDicService.getNameById(declareRealtyHouseCert.getCertUse()));
        vo.setTypeName(baseDataDicService.getNameById(declareRealtyHouseCert.getType()));
        vo.setNatureName(baseDataDicService.getNameById(declareRealtyHouseCert.getNature()));
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyHouseCert.getPublicSituation()));
        vo.setLandAcquisitionName(baseDataDicService.getNameById(declareRealtyHouseCert.getLandAcquisition()));
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getProvince()));
            } else {
                //省
                vo.setProvinceName(declareRealtyHouseCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getCity())) {
                //市或者县
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getCity()));
            } else {
                vo.setCityName(declareRealtyHouseCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getDistrict()));
            } else {
                vo.setDistrictName(declareRealtyHouseCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyHouseCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

    public void eventWriteDeclareApply(DeclareApply declareApply) {
        DeclareRecord declareRecord = null;
        if (declareApply == null) {
            return;
        }
        DeclareRealtyHouseCert query = new DeclareRealtyHouseCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyHouseCert> lists = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(query);
        for (DeclareRealtyHouseCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo, declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setDataFromType("房产证");
            declareRecord.setName(oo.getCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setStreetNumber(oo.getStreetNumber());
            declareRecord.setAttachedNumber(oo.getAttachedNumber());
            declareRecord.setBuildingNumber(oo.getBuildingNumber());
            declareRecord.setUnit(oo.getUnit());
            declareRecord.setRegistrationDate(oo.getRegistrationDate());
            declareRecord.setFloor(oo.getFloor());
            declareRecord.setRoomNumber(oo.getRoomNumber());
            declareRecord.setCertUse(baseDataDicService.getNameById(oo.getCertUse()));
            declareRecord.setFloorArea(oo.getEvidenceArea());
            declareRecord.setLandUseEndDate(oo.getUseEndDate());
            declareRecord.setHousingStructure(oo.getHousingStructure());
            declareRecord.setNature(baseDataDicService.getNameById(oo.getNature()));
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
            declareRecord.setCreator(declareApply.getCreator());
            declareRecord.setBisPartIn(true);
            //写入土地证的证载用途
            DeclareRealtyLandCert realtyLandCert = declareRealtyLandCertDao.getDeclareRealtyLandCertById(oo.getPid());
            if (realtyLandCert != null) {
                declareRecord.setLandCertUse(baseDataDicService.getNameById(realtyLandCert.getCertUse()));
                declareRecord.setLandRightType(baseDataDicService.getNameById(realtyLandCert.getLandRightType()));
                declareRecord.setLandRightNature(baseDataDicService.getNameById(realtyLandCert.getLandRightNature()));
            }
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                oo.setBisRecord(true);
                declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(oo);
            } catch (Exception e1) {
                logger.error("写入失败!", e1);
            }
        }
    }


}
