package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEngineeringVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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
 * @Date: 2018/9/27 11:27
 * @Description:在建工程（土建）
 */
@Service
public class DeclareBuildEngineeringService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareBuildEngineeringDao declareBuildEngineeringDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePoiHelp;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;

    public String importData(DeclareBuildEngineering declareBuildEngineering, MultipartFile multipartFile) throws Exception {
        String declareType = null;
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_DECLARE_BUILDING_CERTIFICATE_TYPE);
        if (!ObjectUtils.isEmpty(baseProjectClassifies)){
            for (BaseProjectClassify baseProjectClassify:baseProjectClassifies){
                if (Objects.equal(baseProjectClassify.getName(), DeclareTypeEnum.DeclareBuildEngineering.getKey())){
                    declareType = String.format("%d",baseProjectClassify.getId());
                }
            }
        }
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
        int rowLength = sheet.getLastRowNum();
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i <= rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareBuildEngineering oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareBuildEngineering();
                oo.setPlanDetailsId(declareBuildEngineering.getPlanDetailsId());
                oo.setDeclareType(declareType);
                if (!declarePoiHelp.buildEngineering(oo,builder,row,i)){
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                declareBuildEngineeringDao.addDeclareBuildEngineering(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering) {
        if (declareBuildEngineering.getId() == null) {
            declareBuildEngineering.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEngineeringDao.addDeclareBuildEngineering(declareBuildEngineering);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class), id);
            DeclareBuildEngineeringAndEquipmentCenter oo = new DeclareBuildEngineeringAndEquipmentCenter();
            oo.setPlanDetailsId(declareBuildEngineering.getPlanDetailsId());
            oo.setBuildEngineeringId(id);
            oo.setCreator(commonService.thisUserAccount());
            oo.setType(DeclareBuildEngineering.class.getSimpleName());
            declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(oo);
            return id;
        } else {
            declareBuildEngineeringDao.updateDeclareBuildEngineering(declareBuildEngineering);
            return null;
        }
    }

    public DeclareBuildEngineering getDeclareBuildEngineeringById(Integer id) {
        return declareBuildEngineeringDao.getDeclareBuildEngineeringById(id);
    }

    public BootstrapTableVo getDeclareBuildEngineeringListVos(DeclareBuildEngineering declareBuildEngineering) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEngineeringVo> vos = declareBuildEngineeringVoList(declareBuildEngineering);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildEngineeringVo> declareBuildEngineeringVoList(DeclareBuildEngineering declareBuildEngineering) {
        List<DeclareBuildEngineering> declareBuildEngineerings = declareBuildEngineeringDao.getDeclareBuildEngineeringList(declareBuildEngineering);
        List<DeclareBuildEngineeringVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareBuildEngineerings)) {
            for (DeclareBuildEngineering landLevel : declareBuildEngineerings) {
                vos.add(getDeclareBuildEngineeringVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering) {
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        query.setBuildEngineeringId(declareBuildEngineering.getId());
        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
        if (!ObjectUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)){
            for (DeclareBuildEngineeringAndEquipmentCenter engineeringAndEquipmentCenter:declareBuildEngineeringAndEquipmentCenterList){
                if (engineeringAndEquipmentCenter.getBuildEngineeringId().equals(declareBuildEngineering.getId())){
                    declareBuildEngineeringAndEquipmentCenterService.removeDeclareBuildEngineeringAndEquipmentCenter(engineeringAndEquipmentCenter);
                }
            }
        }
        declareBuildEngineeringDao.removeDeclareBuildEngineering(declareBuildEngineering);
    }

    public DeclareBuildEngineeringVo getDeclareBuildEngineeringVo(DeclareBuildEngineering declareBuildEngineering) {
        if (declareBuildEngineering == null){
            return null;
        }
        DeclareBuildEngineeringVo vo = new DeclareBuildEngineeringVo();
        BeanUtils.copyProperties(declareBuildEngineering, vo);
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        query.setBuildEngineeringId(declareBuildEngineering.getId());
        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
        if (!ObjectUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)){
            for (DeclareBuildEngineeringAndEquipmentCenter oo:declareBuildEngineeringAndEquipmentCenterList){
                if (oo.getBuildEngineeringId().equals(declareBuildEngineering.getId())){
                    vo.setCenterId(oo.getId());
                    vo.setDeclareBuildEngineeringAndEquipmentCenter(oo);
                }
            }
        }
        //省
        if (StringUtils.isNotBlank(declareBuildEngineering.getProvince())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEngineering.getProvince()));
            } else {
                vo.setProvinceName(declareBuildEngineering.getProvince());
            }
        }
        //市或者区
        if (StringUtils.isNotBlank(declareBuildEngineering.getCity())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareBuildEngineering.getCity()));
            } else {
                vo.setCityName(declareBuildEngineering.getCity());
            }
        }
        //县
        if (StringUtils.isNotBlank(declareBuildEngineering.getDistrict())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEngineering.getDistrict()));
            } else {
                vo.setDistrictName(declareBuildEngineering.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareBuildEngineering.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

    public void eventWriteDeclareApply(DeclareApply declareApply){
        DeclareRecord declareRecord = null;
        if (declareApply == null) {
            return;
        }
        DeclareBuildEngineering query = new DeclareBuildEngineering();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        List<DeclareBuildEngineering> lists = declareBuildEngineeringDao.getDeclareBuildEngineeringList(query);
        for (DeclareBuildEngineering oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo,declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setOwnership(oo.getOccupancyUnit());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setFloorArea(oo.getBuildArea());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setFloorArea(new BigDecimal("0"));
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!",e1);
            }
        }
    }
}
