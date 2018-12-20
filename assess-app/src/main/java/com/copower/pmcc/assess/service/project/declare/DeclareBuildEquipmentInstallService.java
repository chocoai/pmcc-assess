package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEquipmentInstallDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEquipmentInstallVo;
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
 * @Description:在建工程（设备安装）
 */
@Service
public class DeclareBuildEquipmentInstallService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEquipmentInstallDao declareBuildEquipmentInstallDao;
    @Autowired
    private DeclarePublicService declarePoiHelp;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;

    public String importData(DeclareBuildEquipmentInstall declareBuildEquipmentInstall, MultipartFile multipartFile) throws Exception {
        String declareType = null;
        List<BaseProjectClassify> baseProjectClassifies = baseProjectClassifyService.getCacheProjectClassifyListByKey(AssessProjectClassifyConstant.SINGLE_DECLARE_BUILDING_CERTIFICATE_TYPE);
        if (!ObjectUtils.isEmpty(baseProjectClassifies)){
            for (BaseProjectClassify baseProjectClassify:baseProjectClassifies){
                if (Objects.equal(baseProjectClassify.getName(), DeclareTypeEnum.DeclareBuildEquipmentInstall.getKey())){
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
            DeclareBuildEquipmentInstall oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareBuildEquipmentInstall();
                oo.setPlanDetailsId(declareBuildEquipmentInstall.getPlanDetailsId());
                oo.setDeclareType(declareType);
                if (!declarePoiHelp.buildEquipmentInstall(oo,builder,row,i)){
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                declareBuildEquipmentInstallDao.addDeclareBuildEquipmentInstall(oo);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        if (declareBuildEquipmentInstall.getId() == null) {
            declareBuildEquipmentInstall.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEquipmentInstallDao.addDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class), id);
            DeclareBuildEngineeringAndEquipmentCenter oo = new DeclareBuildEngineeringAndEquipmentCenter();
            oo.setPlanDetailsId(declareBuildEquipmentInstall.getPlanDetailsId());
            oo.setBuildEquipmentId(id);
            oo.setCreator(commonService.thisUserAccount());
            oo.setType(DeclareBuildEquipmentInstall.class.getSimpleName());
            declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(oo);
            return id;
        } else {
            declareBuildEquipmentInstallDao.updateDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
            return null;
        }
    }

    public DeclareBuildEquipmentInstall getDeclareBuildEquipmentInstallById(Integer id) {
        return declareBuildEquipmentInstallDao.getDeclareBuildEquipmentInstallById(id);
    }

    public BootstrapTableVo getDeclareBuildEquipmentInstallListVos(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEquipmentInstallVo> vos = declareBuildEquipmentInstallVos(declareBuildEquipmentInstall);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildEquipmentInstallVo> declareBuildEquipmentInstallVos(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        List<DeclareBuildEquipmentInstall> declareBuildEquipmentInstalls = declareBuildEquipmentInstallDao.getDeclareBuildEquipmentInstallList(declareBuildEquipmentInstall);
        List<DeclareBuildEquipmentInstallVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareBuildEquipmentInstalls)) {
            for (DeclareBuildEquipmentInstall landLevel : declareBuildEquipmentInstalls) {
                vos.add(getDeclareBuildEquipmentInstallVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        DeclareBuildEngineeringAndEquipmentCenter oo = new DeclareBuildEngineeringAndEquipmentCenter();
        oo.setBuildEquipmentId(declareBuildEquipmentInstall.getId());
        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(oo);
        if (!ObjectUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)){
            for (DeclareBuildEngineeringAndEquipmentCenter engineeringAndEquipmentCenter:declareBuildEngineeringAndEquipmentCenterList){
                if (engineeringAndEquipmentCenter.getBuildEquipmentId().equals(declareBuildEquipmentInstall.getId())){
                    declareBuildEngineeringAndEquipmentCenterService.removeDeclareBuildEngineeringAndEquipmentCenter(engineeringAndEquipmentCenter);
                }
            }
        }
        declareBuildEquipmentInstallDao.removeDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
    }

    public DeclareBuildEquipmentInstallVo getDeclareBuildEquipmentInstallVo(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        DeclareBuildEquipmentInstallVo vo = new DeclareBuildEquipmentInstallVo();
        BeanUtils.copyProperties(declareBuildEquipmentInstall, vo);
        DeclareBuildEngineeringAndEquipmentCenter andEquipmentCenter = new DeclareBuildEngineeringAndEquipmentCenter();
        andEquipmentCenter.setBuildEquipmentId(declareBuildEquipmentInstall.getId());
        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(andEquipmentCenter);
        if (!ObjectUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)){
            for (DeclareBuildEngineeringAndEquipmentCenter oo:declareBuildEngineeringAndEquipmentCenterList){
                if (oo.getBuildEquipmentId().equals(declareBuildEquipmentInstall.getId())){
                    vo.setCenterId(oo.getId());
                }
            }
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getProvince())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getProvince()));
            }else {
                vo.setProvinceName(declareBuildEquipmentInstall.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getCity())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getCity()));
            }else {
                //市,区
                vo.setCityName(declareBuildEquipmentInstall.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getDistrict())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getDistrict()));
            }else {
                vo.setDistrictName(declareBuildEquipmentInstall.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareBuildEquipmentInstall.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class));
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

    public void eventWriteDeclareApply(DeclareApply declareApply){
        DeclareRecord declareRecord = null;
        if (declareApply == null) {
            return;
        }
        DeclareBuildEquipmentInstall query = new DeclareBuildEquipmentInstall();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        List<DeclareBuildEquipmentInstall> lists = declareBuildEquipmentInstallDao.getDeclareBuildEquipmentInstallList(query);
        for (DeclareBuildEquipmentInstall oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo,declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareApply.getProjectId());
            declareRecord.setOwnership(oo.getOccupancyUnit());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class));
            declareRecord.setDataTableId(oo.getId());
            declareRecord.setFloorArea(new BigDecimal("0"));
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (Exception e1) {
                logger.error("写入失败!",e1);
            }
        }
    }
}
