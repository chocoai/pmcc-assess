package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
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
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    /**
     * 功能描述: 关联房产证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
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
                houseCert.setEnable(DeclareTypeEnum.EnableNo.getKey());
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
                    declareRealtyLandCert.setPlanDetailsId(planDetailsId);
                    listA = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
                }
                //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                if (StringUtils.isNotBlank(houseCert.getOwnership()) && StringUtils.isNotBlank(houseCert.getBeLocated())) {
                    DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
                    declareRealtyLandCert.setBeLocated(houseCert.getBeLocated());
                    declareRealtyLandCert.setOwnership(houseCert.getOwnership());
                    declareRealtyLandCert.setPlanDetailsId(planDetailsId);
                    listB = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
                }
                //匹配标识符
                boolean matching = false;
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
                    //说明此土地证已经关联房产证了
                    if (declareRealtyLandCert.getPid() != null && declareRealtyLandCert.getPid().intValue() >= 1) {
                        matching = false;
                    } else {
                        declareRealtyLandCert.setPid(id);
                        declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
                        successCount++;
                        matching = true;
                    }
                }
                //房产证的所有权人和土地证使用权人进行匹配   &&   房产证座落和土地证座落匹配
                if (!matching) {
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
                        //说明此土地证已经关联房产证了
                        if (declareRealtyLandCert.getPid() != null && declareRealtyLandCert.getPid().intValue() >= 1) {
                            matching = false;
                        } else {
                            declareRealtyLandCert.setPid(id);
                            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
                            successCount++;
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
     * 功能描述: 导入土地证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:30
     */
    public String importData(DeclareRealtyLandCert declareRealtyLandCert, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        String declareType = null;
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE);
        if (!ObjectUtils.isEmpty(baseProjectClassifies)) {
            for (BaseProjectClassify baseProjectClassify : baseProjectClassifies) {
                if (Objects.equal(baseProjectClassify.getName(), DeclareTypeEnum.LAND.getKey())) {
                    declareType = String.format("%d", baseProjectClassify.getId());
                }
            }
        }
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
                oo.setDeclareType(declareType);
                oo.setCreator(commonService.thisUserAccount());
                oo.setPid(0);
                oo.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
                //启用 (非关联数据)
                oo.setEnable(DeclareTypeEnum.Enable.getKey());
                declareRealtyLandCertDao.addDeclareRealtyLandCert(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert.getId() == null) {
            declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            Integer id = null;
            id = declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), id);
            return id;
        } else {
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
            return declareRealtyLandCert.getId();
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

    public List<DeclareRealtyLandCertVo> getList(Integer planDetailsId) {
        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getList(planDetailsId);
        List<DeclareRealtyLandCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
            for (DeclareRealtyLandCert landLevel : declareRealtyLandCerts) {
                vos.add(getDeclareRealtyLandCertVo(landLevel));
            }
        }
        return vos;
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
        if (declareRealtyLandCert.getPid() != null) {
            DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(declareRealtyLandCert.getPid());
            if (Objects.equal(DeclareTypeEnum.EnableNo.getKey(), declareRealtyHouseCert.getEnable())) {
                DeclareRealtyHouseCert oo = new DeclareRealtyHouseCert();
                oo.setId(declareRealtyLandCert.getPid());
                declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(oo);
            }
        }
        declareRealtyLandCertDao.removeDeclareRealtyLandCert(declareRealtyLandCert);
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert == null) {
            return null;
        }
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vo.setTerminationDateFmt(sdf.format(declareRealtyLandCert.getTerminationDate()));
        if (NumberUtils.isNumber(declareRealtyLandCert.getType())) {
            vo.setTypeName(baseDataDicService.getNameById(Integer.parseInt(declareRealtyLandCert.getType())));
        }
        if (NumberUtils.isNumber(declareRealtyLandCert.getPurpose())) {
            vo.setPurposeName(baseDataDicService.getNameById(Integer.parseInt(declareRealtyLandCert.getPurpose())));
        }
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

    @Transactional(rollbackFor = Exception.class)
    public void eventWriteDeclareApply(DeclareApply declareApply) {
        DeclareRecord declareRecord = null;
        if (declareApply == null) {
            return;
        }
        DeclareRealtyLandCert query = new DeclareRealtyLandCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.Enable.getKey());
        List<DeclareRealtyLandCert> lists = declareRealtyLandCertDao.getDeclareRealtyLandCertList(query);
        for (DeclareRealtyLandCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo, declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getLandCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setStreetNumber(oo.getStreetNumber());
            declareRecord.setAttachedNumber(oo.getAttachedNumber());
            declareRecord.setBuildingNumber(oo.getBuildingNumber());
            declareRecord.setUnit(oo.getUnit());
            declareRecord.setFloor(oo.getFloor());
            declareRecord.setRoomNumber(oo.getRoomNumber());
            declareRecord.setLandCertUse(baseDataDicService.getNameById(oo.getPurpose()));
            declareRecord.setFloorArea(oo.getUseRightArea());
            declareRecord.setLandUseEndDate(oo.getTerminationDate());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            //写入房产证的证载用途
            DeclareRealtyHouseCert realtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(oo.getPid());
            if (realtyHouseCert != null) {
                declareRecord.setCertUse(baseDataDicService.getNameById(realtyHouseCert.getPublicSituation()));
            }
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!", e1);
            }
        }
    }

}
