package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyRealEstateCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
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
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:03
 * @Description:不动产证
 */
@Service
public class DeclareRealtyRealEstateCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyRealEstateCertDao declareRealtyRealEstateCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
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
    private DeclareRecordExtendService declareRecordExtendService;

    public String importData(DeclareRealtyRealEstateCert declareRealtyRealEstateCert, MultipartFile multipartFile) throws Exception {
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
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //工作表的第一行
        row = sheet.getRow(0);
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
        for (int i = startRowNumber; i <= rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareRealtyRealEstateCert oo = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                oo = new DeclareRealtyRealEstateCert();
                BeanUtils.copyProperties(declareRealtyRealEstateCert,oo);
                oo.setEnable(DeclareTypeEnum.MasterData.getKey());
                oo.setId(null);
                //excel处理
                if (!declarePoiHelp.realEstateCert(oo, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                declareRealtyRealEstateCertDao.addDeclareRealtyRealEstateCert(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        if (declareRealtyRealEstateCert.getId() == null) {
            declareRealtyRealEstateCert.setCreator(commonService.thisUserAccount());
            Integer id = declareRealtyRealEstateCertDao.addDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), id);
            return id;
        } else {
            declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            return null;
        }
    }

    public DeclareRealtyRealEstateCert getDeclareRealtyRealEstateCertById(Integer id) {
        return declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyRealEstateCertListVos(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyRealEstateCertVo> vos = landLevels(declareRealtyRealEstateCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyRealEstateCertVo> landLevels(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCerts = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(declareRealtyRealEstateCert);
        List<DeclareRealtyRealEstateCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyRealEstateCerts)) {
            for (DeclareRealtyRealEstateCert landLevel : declareRealtyRealEstateCerts) {
                vos.add(getDeclareRealtyRealEstateCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        declareRealtyRealEstateCertDao.removeDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
    }

    public DeclareRealtyRealEstateCertVo getDeclareRealtyRealEstateCertVo(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        if (declareRealtyRealEstateCert == null) {
            return null;
        }
        DeclareRealtyRealEstateCertVo vo = new DeclareRealtyRealEstateCertVo();
        BeanUtils.copyProperties(declareRealtyRealEstateCert, vo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (declareRealtyRealEstateCert.getUseEndDate() != null) {
            vo.setUseEndDateFmt(sdf.format(declareRealtyRealEstateCert.getUseEndDate()));
        }
        if (declareRealtyRealEstateCert.getRegistrationTime() != null) {
            vo.setRegistrationTimeFmt(sdf.format(declareRealtyRealEstateCert.getRegistrationTime()));
        }
        if (declareRealtyRealEstateCert.getUseStartDate() != null) {
            vo.setUseStartDateFmt(sdf.format(declareRealtyRealEstateCert.getUseStartDate()));
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getProvince()));
            } else {
                vo.setProvinceName(declareRealtyRealEstateCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getCity())) {
                //市
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getCity()));
            } else {
                vo.setCityName(declareRealtyRealEstateCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getDistrict())) {
                //县或者县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getDistrict()));
            } else {
                vo.setDistrictName(declareRealtyRealEstateCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyRealEstateCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
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
        vo.setPlanningUseName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getHouseCertUse()));
        vo.setPurposeName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandCertUse()));
        vo.setTypeName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightType()));
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getPublicSituation()));
        vo.setUseRightTypeName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightNature()));
        vo.setNatureName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getNature()));
        return vo;
    }

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
        if (CollectionUtils.isNotEmpty(baseProjectClassifyList)){
            typeFlag = baseProjectClassifyList.stream().anyMatch(baseProjectClassify -> Objects.equal(baseProjectClassify.getId(), projectInfo.getProjectCategoryId()));
        }
        DeclareRealtyRealEstateCert query = new DeclareRealtyRealEstateCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyRealEstateCert> lists = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(query);
        for (DeclareRealtyRealEstateCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo, declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
            declareRecord.setRegistrationDate(oo.getRegistrationDate());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setStreetNumber(oo.getStreetNumber());
            declareRecord.setAttachedNumber(oo.getAttachedNumber());
            declareRecord.setBuildingNumber(oo.getBuildingNumber());
            declareRecord.setUnit(oo.getUnit());
            declareRecord.setFloor(oo.getFloor());
            declareRecord.setRoomNumber(oo.getRoomNumber());
            declareRecord.setCertUse(baseDataDicService.getNameById(oo.getHouseCertUse()));
            declareRecord.setFloorArea(oo.getEvidenceArea());
            declareRecord.setHousingStructure(oo.getHousingStructure());
            declareRecord.setNature(baseDataDicService.getNameById(oo.getNature()));
            declareRecord.setLandCertUse(baseDataDicService.getNameById(oo.getLandCertUse()));
            declareRecord.setLandRightType(baseDataDicService.getNameById(oo.getLandRightType()));//权利类型
            declareRecord.setLandRightNature(baseDataDicService.getNameById(oo.getLandRightNature()));//权利性质
            declareRecord.setLandUseEndDate(oo.getUseEndDate());
            declareRecord.setLandUseRightArea(oo.getUseRightArea());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            declareRecord.setBisPartIn(true);
            if (oo.getLandCertGetQuestion() != null) {
                String name = baseDataDicService.getNameById(oo.getLandCertGetQuestion());
                if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_YES_NAME)){
                    declareRecord.setHasCert(true);
                    declareRecord.setDataFromType("不动产证");
                }
                if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_NO_NAME)){
                    declareRecord.setHasCert(false);
                    declareRecord.setDataFromType(oo.getCertName());
                }
            }
            if (typeFlag){
                declareRecord.setType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.PROJECT_DECLARE_LAND_BASE_TRANSACTION).getId().toString());
            }
            try {
                int declareId = declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                DeclareRecordExtend declareRecordExtend = new DeclareRecordExtend();
                declareRecordExtend.setCreator(commonService.thisUserAccount());
                declareRecordExtend.setRegistrationAuthority(oo.getRegistrationAuthority());
                declareRecordExtend.setDeclareId(declareId);
                declareRecordExtendService.addDeclareRecord(declareRecordExtend) ;
                oo.setBisRecord(true);
                declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(oo);
            } catch (Exception e1) {
                logger.error("写入失败!", e1);
            }
        }
    }


}
