package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.ocr.house.AnalysisUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyRealEstateCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.ocr.RealtyRealEstateCertOcrDto;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
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
    private DeclarePoiHelp declarePoiHelp;
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
        //工作表的第一行
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getLastCellNum();
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总行数
        int rowLength = sheet.getLastRowNum()  - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        List<BaseDataDic> land_uses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
        for (int i = startRowNumber; i <= rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareRealtyRealEstateCert oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareRealtyRealEstateCert();
                oo.setPlanDetailsId(declareRealtyRealEstateCert.getPlanDetailsId());
                //excel处理
                if (!declarePoiHelp.realEstateCert(oo,builder,row,i,land_uses)){
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
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
        DeclareRealtyRealEstateCertVo vo = new DeclareRealtyRealEstateCertVo();
        BeanUtils.copyProperties(declareRealtyRealEstateCert, vo);
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
            }else {
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
        return vo;
    }

    public void eventWriteDeclareInfo(DeclareInfo declareInfo){
        DeclareRecord declareRecord = null;
        if (declareInfo == null) {
            return;
        }
        DeclareRealtyRealEstateCert query = new DeclareRealtyRealEstateCert();
        query.setPlanDetailsId(declareInfo.getPlanDetailsId());
        List<DeclareRealtyRealEstateCert> lists = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(query);
        for (DeclareRealtyRealEstateCert oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo,declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareInfo.getProjectId());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setName(oo.getCertName());
            declareRecord.setOwnership(oo.getOwnership());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setFloorArea(oo.getEvidenceArea());
            declareRecord.setLandUseEndDate(oo.getUseEndDate());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            BaseDataDic baseDataDic = null;
            if (oo.getPurpose() != null){
                if (NumberUtils.isNumber(oo.getPurpose())){
                    baseDataDic= baseDataDicService.getDataDicById(Integer.parseInt(oo.getPurpose()));
                    if (baseDataDic != null){
                        declareRecord.setCertUse(baseDataDic.getName());
                    }
                }
            }
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!",e1);
            }
        }
    }

    public DeclareRealtyRealEstateCert parseRealtyEstateCert(String startPath)throws Exception{
        DeclareRealtyRealEstateCert oo = new DeclareRealtyRealEstateCert();
        RealtyRealEstateCertOcrDto vo = AnalysisUtils.parseRealtyEstateCert(startPath);
        if (vo == null){
            return null;
        }
        if (StringUtils.isNotBlank(vo.getNumber())){
            oo.setNumber(vo.getNumber());
        }
        if (StringUtils.isNotBlank(vo.getBeLocated())){
            oo.setBeLocated(vo.getBeLocated());
        }
        if (StringUtils.isNotBlank(vo.getOwnership())){
            oo.setOwnership(vo.getOwnership());
        }
        if (StringUtils.isNotBlank(vo.getUseRightType())){
            oo.setUseRightType(vo.getUseRightType());
        }
        if (StringUtils.isNotBlank(vo.getNature())){
            oo.setNature(vo.getNature());
        }
        if (StringUtils.isNotBlank(vo.getPublicSituation())){
            oo.setPublicSituation(vo.getPublicSituation());
        }
        if (StringUtils.isNotBlank(vo.getRealEstateUnitNumber())){
            oo.setRealEstateUnitNumber(vo.getRealEstateUnitNumber());
        }
        if (StringUtils.isNotBlank(vo.getPurpose())){
            oo.setPurpose(vo.getPurpose());
        }
        if (vo.getFloorArea() != null){
            oo.setFloorArea(vo.getFloorArea());
        }
        if (vo.getTerminationDate() != null){
            oo.setTerminationDate(vo.getTerminationDate());
        }
        return oo;
    }

}
