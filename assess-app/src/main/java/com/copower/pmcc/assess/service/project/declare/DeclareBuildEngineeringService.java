package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineering;
import com.copower.pmcc.assess.dal.basis.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEngineeringVo;
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
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareBuildEngineeringDao declareBuildEngineeringDao;
    @Autowired
    private DeclareRecordService declareRecordService;

    public String importData(DeclareBuildEngineering declareBuildEngineering, MultipartFile multipartFile) throws Exception {
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
        int rowLength = sheet.getLastRowNum();
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        for (int i = startRowNumber; i <= rowLength; i++) {
            boolean flag = true;//标识符
            DeclareBuildEngineering oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareBuildEngineering();
                String provinceName = PoiUtils.getCellValue(row.getCell(14));//省
                String cityName = PoiUtils.getCellValue(row.getCell(15));//市或者区
                String districtName = PoiUtils.getCellValue(row.getCell(16));//县
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
                oo.setPlanDetailsId(declareBuildEngineering.getPlanDetailsId());
                oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));//占有单位
                oo.setName(PoiUtils.getCellValue(row.getCell(1)));//项目名称
                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));//坐落
                oo.setStructure(PoiUtils.getCellValue(row.getCell(3)));//结构
                oo.setBuildArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));//建筑面积
                oo.setStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(5)), null));//开工日期
                oo.setExpectedCompletionDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(6)), null));//预期完成日期
                oo.setSpeedProgress(PoiUtils.getCellValue(row.getCell(7)));//形象进度
                oo.setPaymentRatio(PoiUtils.getCellValue(row.getCell(8)));//付款比例
                oo.setBookValue(PoiUtils.getCellValue(row.getCell(9)));//账面价值
                oo.setBookNetValue(PoiUtils.getCellValue(row.getCell(10)));//帐面净值
                oo.setDeclarer(PoiUtils.getCellValue(row.getCell(11)));//申报人
                oo.setDeclarationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(12)), null));//申报日期
                oo.setRemark(PoiUtils.getCellValue(row.getCell(13)));//申报人
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
        declareBuildEngineeringDao.removeDeclareBuildEngineering(declareBuildEngineering);
    }

    public DeclareBuildEngineeringVo getDeclareBuildEngineeringVo(DeclareBuildEngineering declareBuildEngineering) {
        DeclareBuildEngineeringVo vo = new DeclareBuildEngineeringVo();
        BeanUtils.copyProperties(declareBuildEngineering, vo);
        if (StringUtils.isNotBlank(declareBuildEngineering.getProvince())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEngineering.getProvince()));//省
            } else {
                vo.setProvinceName(declareBuildEngineering.getProvince());//省
            }
        }
        if (StringUtils.isNotBlank(declareBuildEngineering.getCity())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareBuildEngineering.getCity()));//市或者县
            } else {
                vo.setCityName(declareBuildEngineering.getCity());//市或者县
            }
        }
        if (StringUtils.isNotBlank(declareBuildEngineering.getDistrict())) {
            if (NumberUtils.isNumber(declareBuildEngineering.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEngineering.getDistrict()));//县
            } else {
                vo.setDistrictName(declareBuildEngineering.getDistrict());//县
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareBuildEngineering.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class));
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
        DeclareBuildEngineering query = new DeclareBuildEngineering();
        query.setPlanDetailsId(declareInfo.getPlanDetailsId());
        List<DeclareBuildEngineering> lists = declareBuildEngineeringDao.getDeclareBuildEngineeringList(query);
        for (DeclareBuildEngineering oo : lists) {
            declareRecord = new DeclareRecord();
            BeanUtils.copyProperties(oo,declareRecord);
            declareRecord.setId(null);
            declareRecord.setProjectId(declareInfo.getProjectId());
            declareRecord.setOwnership(oo.getOccupancyUnit());
            declareRecord.setSeat(oo.getBeLocated());
            declareRecord.setFloorArea(oo.getBuildArea());
            declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class));
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
