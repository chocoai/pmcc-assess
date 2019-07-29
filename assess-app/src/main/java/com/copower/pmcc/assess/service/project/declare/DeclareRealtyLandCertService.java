package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePoiHelp;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareRecordExtendService declareRecordExtendService;

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
                houseCert.setEnable(DeclareTypeEnum.BranchData.getKey());
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
                BeanUtils.copyProperties(declareRealtyLandCert, oo);
                oo.setId(null);
                if (!declarePoiHelp.land(oo, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setPid(0);
                oo.setEnable(DeclareTypeEnum.MasterData.getKey());
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


    public boolean deleteDeclareRealtyLandCertById(Integer id){
        return declareRealtyLandCertDao.deleteDeclareRealtyLandCertById(id) ;
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert == null) {
            return null;
        }
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        if (declareRealtyLandCert.getTerminationDate() != null) {
            vo.setTerminationDateFmt(DateUtils.formatDate(declareRealtyLandCert.getTerminationDate()));
        }
        vo.setLandRightTypeName(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightType()));
        vo.setLandRightNatureName(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightNature()));
        vo.setPurposeName(baseDataDicService.getNameById(declareRealtyLandCert.getCertUse()));
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyLandCert.getPublicSituation()));
        if(declareRealtyLandCert.getCertUseCategory()!=null){
            vo.setCertUseCategoryName(baseDataDicService.getNameById(declareRealtyLandCert.getCertUseCategory()));
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
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(declareApply.getProjectId());
        List<BaseProjectClassify> baseProjectClassifyList = Lists.newArrayList();
        Arrays.stream(new String[]{AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE, AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE}).forEach(s -> {
            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName(s);
            if (baseProjectClassify != null) {
                baseProjectClassifyList.add(baseProjectClassify);
            }
        });
        boolean typeFlag = false;
        if (CollectionUtils.isNotEmpty(baseProjectClassifyList)) {
            typeFlag = baseProjectClassifyList.stream().anyMatch(baseProjectClassify -> Objects.equal(baseProjectClassify.getId(), projectInfo.getProjectCategoryId()));
        }
        DeclareRealtyLandCert query = new DeclareRealtyLandCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyLandCert> lists = declareRealtyLandCertDao.getDeclareRealtyLandCertList(query);
        for (DeclareRealtyLandCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo, declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
            declareRecord.setRegistrationDate(oo.getRegistrationDate());
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getLandCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setStreetNumber(oo.getStreetNumber());
            declareRecord.setAttachedNumber(oo.getAttachedNumber());
            declareRecord.setBuildingNumber(oo.getBuildingNumber());
            declareRecord.setUnit(oo.getUnit());
            declareRecord.setFloor(oo.getFloor());
            declareRecord.setRoomNumber(oo.getRoomNumber());
            declareRecord.setLandCertUse(baseDataDicService.getNameById(oo.getCertUse()));
            declareRecord.setLandRightType(baseDataDicService.getNameById(oo.getLandRightType()));//权利类型
            declareRecord.setLandRightNature(baseDataDicService.getNameById(oo.getLandRightNature()));//权利性质
            declareRecord.setFloorArea(oo.getUseRightArea());
            declareRecord.setLandUseEndDate(oo.getTerminationDate());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            declareRecord.setBisPartIn(true);
            if (oo.getLandCertGetQuestion() != null) {
                String name = baseDataDicService.getNameById(oo.getLandCertGetQuestion());
                if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_YES_NAME)) {
                    declareRecord.setHasCert(true);
                    declareRecord.setDataFromType("土地证");
                }
                if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_NO_NAME)) {
                    declareRecord.setHasCert(false);
                    declareRecord.setDataFromType(oo.getLandCertName());
                }
            }
            if (typeFlag) {
                if (oo.getPid() != null && oo.getPid().intValue() != 0) {
                    declareRecord.setType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_INCLUDE_HOUSE_TRANSACTION).getId().toString());
                } else {
                    declareRecord.setType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_TRANSACTION).getId().toString());
                }
            }
            //写入房产证的证载用途
            DeclareRealtyHouseCert realtyHouseCert = null;
            if (oo.getPid() != null && oo.getPid() != 0) {
                realtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(oo.getPid());
            }
            DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
            centerQuery.setLandId(oo.getId());
            centerQuery.setPlanDetailsId(oo.getPlanDetailsId());
            centerQuery.setType(DeclareRealtyHouseCert.class.getSimpleName());
            List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(centerQuery);
            if (CollectionUtils.isNotEmpty(centerList)){
                if (centerList.stream().anyMatch(obj -> obj.getHouseId() != null)){
                    realtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(centerList.stream().filter(obj -> obj.getHouseId() != null).findFirst().get().getHouseId());
                }
            }
            if (realtyHouseCert != null) {
                declareRecord.setCertUse(baseDataDicService.getNameById(realtyHouseCert.getPublicSituation()));
                declareRecord.setHousingStructure(realtyHouseCert.getHousingStructure());
                declareRecord.setNature(baseDataDicService.getNameById(realtyHouseCert.getNature()));
            }
            try {
                int declareId = declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                DeclareRecordExtend declareRecordExtend = new DeclareRecordExtend();
                declareRecordExtend.setCreator(commonService.thisUserAccount());
                declareRecordExtend.setRegistrationAuthority(oo.getRegistrationAuthority());
                declareRecordExtend.setDeclareId(declareId);
                declareRecordExtendService.addDeclareRecord(declareRecordExtend) ;
                oo.setBisRecord(true);
                declareRealtyLandCertDao.updateDeclareRealtyLandCert(oo);
            } catch (Exception e1) {
                logger.error("写入失败!", e1);
            }
        }
    }

}
