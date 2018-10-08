package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyRealEstateCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;

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
        Sheet sheet = workbook.getSheetAt(0);//只取第一个sheet
        //工作表的第一行
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getLastCellNum();
        int startRowNumber = 1;//读取数据的起始行
        int successCount = 0;//导入成功数据条数
        //总行数
        int rowLength = sheet.getLastRowNum()  - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        List<BaseProjectClassify> houses = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_HOUSE_CATEGORY);
        List<BaseProjectClassify> lands = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_LAND_PROPERTY_CERTIFICATE_TYPE_LAND_CATEGORY);
        List<BaseDataDic> land_uses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_TOTAL_LAND_USE);
        for (int i = startRowNumber; i <= rowLength; i++) {
            boolean flag = true;//标识符
            DeclareRealtyRealEstateCert oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareRealtyRealEstateCert();
                String provinceName = PoiUtils.getCellValue(row.getCell(34)) ;//省
                String cityName = PoiUtils.getCellValue(row.getCell(35)) ;//市或者区
                String districtName = PoiUtils.getCellValue(row.getCell(36)) ;//县
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
                String purpose = PoiUtils.getCellValue(row.getCell(31));
                BaseDataDic typeDic = baseDataDicService.getDataDicByName(land_uses,purpose);
                if (typeDic == null) {
                    builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                    continue;
                }else {
                    purpose = String.valueOf(typeDic.getId());
                }
                oo.setPurpose(purpose);//用途

                oo.setPlanDetailsId(declareRealtyRealEstateCert.getPlanDetailsId());
                oo.setCertName(PoiUtils.getCellValue(row.getCell(0)));
                oo.setLocation(PoiUtils.getCellValue(row.getCell(1)));
                oo.setNumber(PoiUtils.getCellValue(row.getCell(2)));
                oo.setType(PoiUtils.getCellValue(row.getCell(3)));//类型
                oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(4)));//土地取得方式
                oo.setPublicSituation(PoiUtils.getCellValue(row.getCell(5)));//共有情况
                oo.setFloorArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(6))));//建筑面积

                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(7)));//房屋坐落
                oo.setStreetNumber(PoiUtils.getCellValue(row.getCell(8)));//街道号
                try {
                    oo.setAttachedNumber(PoiUtils.getCellValue(row.getCell(9)));//附号
                } catch (Exception e1) {
                    //附号可以允许不填写 ==>不用抛出异常
                }
                oo.setBuildingNumber(PoiUtils.getCellValue(row.getCell(10)));//栋号
                oo.setUnit(PoiUtils.getCellValue(row.getCell(11)));//单元
                oo.setFloor(Integer.parseInt(PoiUtils.getCellValue(row.getCell(12))));//楼层
                oo.setRoomNumber(PoiUtils.getCellValue(row.getCell(13)));//房号

                oo.setRegistrationTime(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));//登记时间
                oo.setRegistrationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(15)), null));//登记日期
                oo.setPlanningUse(PoiUtils.getCellValue(row.getCell(16)));//规划用途
                oo.setFloorCount(Integer.parseInt(PoiUtils.getCellValue(row.getCell(17))));//总层数
                oo.setEvidenceArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(18))));//证载面积
                oo.setInnerArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(19))));//套内面积
                oo.setOther(PoiUtils.getCellValue(row.getCell(20)));//其它
                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(21)));//土地证号
                oo.setLandAcquisition(PoiUtils.getCellValue(row.getCell(22)));//土地取得方式

                oo.setUseStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(23)), null));//土地使用年限起
                oo.setUseEndDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(24)), null));//土地使用年限止
                oo.setPublicArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(25))));//公摊面积
                oo.setOtherNote(PoiUtils.getCellValue(row.getCell(26)));//附记其它
                oo.setRegistrationAuthority(PoiUtils.getCellValue(row.getCell(27)));//登记机关
                oo.setNature(PoiUtils.getCellValue(row.getCell(28)));//房屋性质

                oo.setLandNumber(PoiUtils.getCellValue(row.getCell(29)));//地号
                oo.setGraphNumber(PoiUtils.getCellValue(row.getCell(30)));//图号
                oo.setAcquisitionPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(32))));//取得价格
                oo.setUseRightType(PoiUtils.getCellValue(row.getCell(33)));//使用权类型


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
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getProvince()));//省
            } else {
                vo.setProvinceName(declareRealtyRealEstateCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getCity()));//市或者县
            } else {
                vo.setCityName(declareRealtyRealEstateCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getDistrict()));//县
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
