package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEquipmentInstallDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEquipmentInstallVo;
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
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareBuildEquipmentInstallDao declareBuildEquipmentInstallDao;

    public String importData(DeclareBuildEquipmentInstall declareBuildEquipmentInstall, MultipartFile multipartFile) throws Exception {
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
            DeclareBuildEquipmentInstall oo = null;
            try {
                row = sheet.getRow(i);
                oo = new DeclareBuildEquipmentInstall();
                String provinceName = PoiUtils.getCellValue(row.getCell(16));//省
                String cityName = PoiUtils.getCellValue(row.getCell(17));//市或者区
                String districtName = PoiUtils.getCellValue(row.getCell(18));//县
                oo.setProvince(provinceName);
                oo.setCity(cityName);
                oo.setDistrict(districtName);
                //验证(区域)
                if (!erpAreaService.checkArea(provinceName, cityName, districtName, builder)) {
                    builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
                    continue;
                }
                oo.setPlanDetailsId(declareBuildEquipmentInstall.getPlanDetailsId());
                oo.setOccupancyUnit(PoiUtils.getCellValue(row.getCell(0)));//占有单位
                oo.setName(PoiUtils.getCellValue(row.getCell(1)));//项目名称
                oo.setBeLocated(PoiUtils.getCellValue(row.getCell(2)));//坐落
                oo.setSpecificationModel(PoiUtils.getCellValue(row.getCell(3)));//规格型号
                oo.setManufacturer(PoiUtils.getCellValue(row.getCell(4)));//生产厂家
                oo.setMeasurementUnit(PoiUtils.getCellValue(row.getCell(5)));//计量单位
                oo.setNumber(Integer.parseInt(PoiUtils.getCellValue(row.getCell(6))));//数量
                oo.setStartDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(7)), null));//开工日期
                oo.setExpectedCompletionDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(8)), null));//预期完成日期
                oo.setBookEquipmentFee(PoiUtils.getCellValue(row.getCell(9)));//帐面设备费
                oo.setBookCapitalCost(PoiUtils.getCellValue(row.getCell(10)));//账面资金成本
                oo.setBookInstallationFee(PoiUtils.getCellValue(row.getCell(11)));//账面安装费
                oo.setOther(PoiUtils.getCellValue(row.getCell(12)));//其它
                oo.setDeclarer(PoiUtils.getCellValue(row.getCell(13)));//申报人
                oo.setDeclarationDate(DateHelp.getDateHelp().parse(PoiUtils.getCellValue(row.getCell(14)), null));//申报日期
                oo.setRemark(PoiUtils.getCellValue(row.getCell(15)));//备注
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
        declareBuildEquipmentInstallDao.removeDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
    }

    public DeclareBuildEquipmentInstallVo getDeclareBuildEquipmentInstallVo(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        DeclareBuildEquipmentInstallVo vo = new DeclareBuildEquipmentInstallVo();
        BeanUtils.copyProperties(declareBuildEquipmentInstall, vo);
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getProvince())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getProvince())) {
                vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getProvince()));//省
            }else {
                vo.setProvinceName(declareBuildEquipmentInstall.getProvince());//省
            }
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getCity())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getCity())) {
                vo.setCityName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getCity()));//市或者县
            }else {
                vo.setCityName(declareBuildEquipmentInstall.getCity());//市或者县
            }
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getDistrict())) {
            if (NumberUtils.isNumber(declareBuildEquipmentInstall.getDistrict())) {
                vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getDistrict()));//县
            }else {
                vo.setDistrictName(declareBuildEquipmentInstall.getDistrict());//县
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
}
