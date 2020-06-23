package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseCaseSummaryDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.StatisticsDto;
import com.copower.pmcc.assess.dto.input.basic.BasicHouseCaseSummaryParamsDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseCaseSummaryVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kings on 2018-12-3.
 * 楼盘
 */
@Service
public class BasicHouseCaseSummaryService {
    @Autowired
    private BasicHouseCaseSummaryDao basicHouseCaseSummaryDao;
    @Lazy
    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseService baseService;

    public BasicHouseCaseSummary getBasicHouseCaseSummaryById(Integer id) {
        return basicHouseCaseSummaryDao.getBasicHouseCaseSummaryById(id);
    }

    public BootstrapTableVo findReportAuditStatisticsList(BasicHouseCaseSummaryParamsDto paramsDto) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> list = customCaseMapper.findReportAuditStatistics(paramsDto.getEndDate(), paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(), paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        vo.setTotal(page.getTotal());
        vo.setRows(statisticsDtoList);
        return vo;
    }

    public BootstrapTableVo findReportApplyStatisticsList(BasicHouseCaseSummaryParamsDto paramsDto) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> list = customCaseMapper.findReportApplyStatistics(paramsDto.getEndDate(), paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(), paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        vo.setTotal(page.getTotal());
        vo.setRows(statisticsDtoList);
        return vo;
    }

    public BootstrapTableVo getBootstrapTableVo(BigDecimal areaStart, BigDecimal areaEnd, Date tradingTimeStart, Date tradingTimeEnd, BasicHouseCaseSummary basicHouseCaseSummary) throws Exception {
        BasicHouseCaseSummaryParamsDto paramsDto = new BasicHouseCaseSummaryParamsDto() ;
        paramsDto.setAreaStart(areaStart);
        paramsDto.setAreaEnd(areaEnd);
        paramsDto.setTradingTimeEnd(tradingTimeEnd);
        paramsDto.setTradingTimeStart(tradingTimeStart);
        paramsDto.setHouseCaseSummary(basicHouseCaseSummary);
        return getBootstrapTableVo(paramsDto);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseCaseSummaryParamsDto paramsDto) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseCaseSummary> list = customCaseMapper.findCaseBaseHouseList(paramsDto.getEndDate(), paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(), paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        List<BasicHouseCaseSummaryVo> vos = LangUtils.transform(list, o -> getBasicHouseCaseSummaryVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public BasicHouseCaseSummaryVo getBasicHouseCaseSummaryVo(BasicHouseCaseSummary houseSummary) {
        if (houseSummary == null) return null;
        BasicHouseCaseSummaryVo houseCaseSummaryVo = new BasicHouseCaseSummaryVo();
        BeanUtils.copyProperties(houseSummary, houseCaseSummaryVo);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(houseSummary.getProvince())) {
            houseCaseSummaryVo.setProvinceName(erpAreaService.getSysAreaName(houseSummary.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(houseSummary.getCity())) {
            houseCaseSummaryVo.setCityName(erpAreaService.getSysAreaName(houseSummary.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(houseSummary.getDistrict())) {
            houseCaseSummaryVo.setDistrictName(erpAreaService.getSysAreaName(houseSummary.getDistrict()));
        }
        if (houseSummary.getPracticalUse() != null) {
            houseCaseSummaryVo.setPracticalUseName(baseDataDicService.getNameById(houseSummary.getPracticalUse()));
        }
        if (houseSummary.getTradingType() != null) {
            houseCaseSummaryVo.setTradingTypeName(baseDataDicService.getNameById(houseSummary.getTradingType()));
        }
        if (houseSummary.getDealType() != null) {
            houseCaseSummaryVo.setDealTypeName(baseDataDicService.getNameById(houseSummary.getDealType()));
        }
        if (StringUtils.isNotBlank(houseSummary.getApprover())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(houseSummary.getApprover());
            if (sysUser != null && StringUtils.isNotBlank(sysUser.getUserName())) {
                houseCaseSummaryVo.setApproverName(sysUser.getUserName());
            }
        }
        if (StringUtils.isNotBlank(houseSummary.getCreator())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(houseSummary.getCreator());
            if (sysUser != null && StringUtils.isNotBlank(sysUser.getUserName())) {
                houseCaseSummaryVo.setCreatorName(sysUser.getUserName());
            }
        }
        return houseCaseSummaryVo;
    }

    /**
     * 新增数据
     *
     * @param BasicHouseCaseSummary
     */
    public void addBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        basicHouseCaseSummaryDao.addBaseHouseSummary(BasicHouseCaseSummary);
    }

    /**
     * 更新数据
     *
     * @param BasicHouseCaseSummary
     */
    public void updateBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        basicHouseCaseSummaryDao.updateBaseHouseSummary(BasicHouseCaseSummary);
    }

    /**
     * 获取数据列表
     *
     * @param BasicHouseCaseSummary
     * @return
     */
    public List<BasicHouseCaseSummary> getBaseHouseSummaryList(BasicHouseCaseSummary BasicHouseCaseSummary) {
        return basicHouseCaseSummaryDao.getBaseHouseSummaryList(BasicHouseCaseSummary);
    }

    /**
     * 删除数据by caseHouseId
     *
     * @param id
     */
    public void deleteBaseHouseSummaryById(Integer id) {
        basicHouseCaseSummaryDao.deleteBaseHouseSummaryById(id);
    }

    public Integer getCountByFullName(String fullName) {
        return basicHouseCaseSummaryDao.getCountByFullName(fullName);
    }

    private HSSFWorkbook writeBaseExcelData(List<BasicHouseCaseSummary> baseHouseList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = getCellStyle(wb) ;
        List<BasicHouseCaseSummaryVo> vos = LangUtils.transform(baseHouseList, o -> getBasicHouseCaseSummaryVo(o));
        HSSFSheet sheet = wb.createSheet("基础数据");
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,3500);
        sheet.setColumnWidth(4,3500);
        sheet.setColumnWidth(5,3500);
        sheet.setColumnWidth(6,3500);
        sheet.setColumnWidth(7,6500);
        sheet.setColumnWidth(8,6500);
        sheet.setColumnWidth(9,3500);
        sheet.setColumnWidth(10,3500);
        int rowNum = 0;
        Row rowOne = sheet.createRow(rowNum);
        rowNum++;
        for (int i = 0; i < 11; i++) {
            Cell cell = rowOne.createCell(i);
            switch (i) {
                case 0: cell.setCellValue("序号");break;
                case 1: cell.setCellValue("地址");break;
                case 2: cell.setCellValue("街道");break;
                case 3: cell.setCellValue("成交单价");break;
                case 4: cell.setCellValue("类型");break;
                case 5: cell.setCellValue("面积");break;
                case 6: cell.setCellValue("成交价");break;
                case 7: cell.setCellValue("成交时间");break;
                case 8: cell.setCellValue("创建时间");break;
                case 9: cell.setCellValue("申请人");break;
                case 10: cell.setCellValue("审批人");break;
                default:
                    break;
            }
            cell.setCellStyle(style);
        }
        if (CollectionUtils.isNotEmpty(vos)){
            Iterator<BasicHouseCaseSummaryVo> iterator = vos.iterator();
            while (iterator.hasNext()){
                BasicHouseCaseSummaryVo summaryVo = iterator.next();
                Row row = sheet.createRow(rowNum);
                rowNum++;
                for (int i = 0; i < 11; i++) {
                    Cell cell = row.createCell(i);
                    switch (i) {
                        case 0: cell.setCellValue(rowNum);break;
                        case 1: cell.setCellValue(StringUtils.isNotBlank(summaryVo.getFullName()) ?summaryVo.getFullName():"");break;
                        case 2: cell.setCellValue(StringUtils.isNotBlank(summaryVo.getStreet()) ?summaryVo.getStreet():"");break;
                        case 3: cell.setCellValue(summaryVo.getTradingUnitPrice() != null?summaryVo.getTradingUnitPrice().toString():"");break;
                        case 4: cell.setCellValue(StringUtils.isNotBlank(summaryVo.getHouseType()) ?summaryVo.getHouseType():"");break;
                        case 5: cell.setCellValue(summaryVo.getArea() != null?summaryVo.getArea().toString():"");break;
                        case 6: cell.setCellValue(summaryVo.getCurrentPrice() != null?summaryVo.getCurrentPrice().toString():"");break;
                        case 7: cell.setCellValue(summaryVo.getTradingTime() != null?DateUtils.format(summaryVo.getTradingTime(),DateUtils.DATE_CHINESE_PATTERN):"");break;
                        case 8: cell.setCellValue(summaryVo.getGmtCreated() != null?DateUtils.format(summaryVo.getGmtCreated(),DateUtils.DATE_CHINESE_PATTERN):"");break;
                        case 9: cell.setCellValue(StringUtils.isNotBlank(summaryVo.getCreatorName()) ?summaryVo.getCreatorName():summaryVo.getCreator());break;
                        case 10: cell.setCellValue(StringUtils.isNotBlank(summaryVo.getApproverName()) ?summaryVo.getApproverName():summaryVo.getApprover());break;
                        default:
                            break;
                    }
                    cell.setCellStyle(style);
                }
            }
        }
        return wb;
    }

    private HSSFWorkbook writeApplyExcelData(List<com.copower.pmcc.assess.dto.input.StatisticsDto> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        HSSFSheet wbSheet = wb.createSheet("申请人统计");
        commonWriteStatisticsDtoExcel(statisticsDtoList, wbSheet,getCellStyle(wb));
        return wb;
    }

    private CellStyle getCellStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 13); //字体高度
        font.setFontName("微软雅黑"); //字体
        style.setFont(font);
        return style;
    }

    private void commonWriteStatisticsDtoExcel(List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList, HSSFSheet sheet ,CellStyle style) {
        sheet.setColumnWidth(0,6000);
        sheet.setColumnWidth(1,6000);
        int rowNum = 0;
        Row rowOne = sheet.createRow(rowNum);
        rowNum++;
        for (int i = 0; i < 2; i++) {
            Cell cell = rowOne.createCell(i);
            if (i == 0) {
                cell.setCellValue("账户名称");
            }
            if (i == 1) {
                cell.setCellValue("统计数量");
            }
            cell.setCellStyle(style);
        }
        if (CollectionUtils.isEmpty(statisticsDtoList)){
            return;
        }
        Iterator<StatisticsDto> iterator = statisticsDtoList.iterator();
        while (iterator.hasNext()) {
            StatisticsDto statisticsDto = iterator.next();
            Row row = sheet.createRow(rowNum);
            rowNum++;
            for (int i = 0; i < 2; i++) {
                Cell cell = row.createCell(i);
                if (i == 0) {
                    cell.setCellValue(StringUtils.isNotBlank(statisticsDto.getName()) ? statisticsDto.getName() : "");
                }
                if (i == 1) {
                    cell.setCellValue(statisticsDto.getNumber());
                }
                cell.setCellStyle(style);
            }
        }
    }

    private HSSFWorkbook writeAuditExcelData(List<com.copower.pmcc.assess.dto.input.StatisticsDto> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        HSSFSheet wbSheet = wb.createSheet("审核人统计");
        commonWriteStatisticsDtoExcel(statisticsDtoList, wbSheet ,getCellStyle(wb));
        return wb;
    }

    /**
     * 导出数据
     *
     * @param paramsDto
     * @return
     * @throws Exception
     */
    public String reportData(BasicHouseCaseSummaryParamsDto paramsDto) throws Exception {
        List<BasicHouseCaseSummary> baseHouseList = customCaseMapper.findCaseBaseHouseList(paramsDto.getEndDate(), paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(), paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> reportApplyStatistics = customCaseMapper.findReportApplyStatistics(paramsDto.getEndDate(),  paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(),  paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> reportAuditStatistics = customCaseMapper.findReportAuditStatistics(paramsDto.getEndDate(),  paramsDto.getStartDate(), paramsDto.getAreaStart(), paramsDto.getAreaEnd(),  paramsDto.getTradingTimeStart(), paramsDto.getTradingTimeEnd(), paramsDto.getHouseCaseSummary());
        HSSFWorkbook baseWorkBook = writeBaseExcelData(baseHouseList);
        HSSFWorkbook applyWorkBook = writeApplyExcelData(reportApplyStatistics);
        HSSFWorkbook auditWorkBook = writeAuditExcelData(reportAuditStatistics);
        String basePath = String.join(File.separator, FileUtils.getTempDirectoryPath(), "基础数据.xls");
        String applyPath = String.join(File.separator, FileUtils.getTempDirectoryPath(), "申请人统计.xls");
        String approverPath = String.join(File.separator, FileUtils.getTempDirectoryPath(), "审核人统计.xls");
        String path = null;
        if (paramsDto.getStartDate() != null && paramsDto.getEndDate() != null) {
            String time = String.join("至", DateUtils.format(paramsDto.getStartDate(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(paramsDto.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
            path = String.join(File.separator, FileUtils.getTempDirectoryPath(), "房产案例统计表" + time + ".zip");
        } else if (paramsDto.getStartDate() != null && paramsDto.getEndDate() == null) {
            path = String.join(File.separator, FileUtils.getTempDirectoryPath(), "房产案例统计表" + DateUtils.format(paramsDto.getStartDate(), DateUtils.DATE_CHINESE_PATTERN) + ".zip");
        } else if (paramsDto.getStartDate() == null && paramsDto.getEndDate() != null) {
            path = String.join(File.separator, FileUtils.getTempDirectoryPath(), "房产案例统计表" + DateUtils.format(paramsDto.getEndDate(), DateUtils.DATE_CHINESE_PATTERN) + ".zip");
        } else {
            path = String.join(File.separator, FileUtils.getTempDirectoryPath(), "房产案例统计表" + DateUtils.format(DateUtils.now(), DateUtils.DATE_CHINESE_PATTERN) + ".zip");
        }
        reportHandle(basePath, baseWorkBook);
        reportHandle(applyPath, applyWorkBook);
        reportHandle(approverPath, auditWorkBook);
        //形成压缩包
        File[] srcFile = new File[]{new File(basePath), new File(applyPath), new File(approverPath)};
        com.copower.pmcc.assess.common.FileUtils.zipFiles(srcFile, new File(path));
        return path;
    }

    public String uploadFilesToFTP(String path){
        File file = new File(path) ;
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        sysAttachmentDto.setFieldsName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setFileName(FilenameUtils.getName(path));
        sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setFileSize(org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file).toString());
        //注意这里因为是linux 路径所以采用/ 或者使用Java自带的判断符号 windows下 WinNTFileSystem linux 下UnixFileSystem
        String ftpBasePath = baseAttachmentService.createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension()));
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file.getPath()), sysAttachmentDto.getFtpFileName());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "erp上传文件出错");
        }
        baseAttachmentService.addAttachment(sysAttachmentDto);
        //完毕之后删除临时文件
        FileUtils.deleteQuietly(file);
        return sysAttachmentDto.getId().toString();
    }

    private String reportHandle(String path, HSSFWorkbook wb) throws Exception {
        OutputStream fileOut = null;
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        return path;
    }

    private com.copower.pmcc.assess.dto.input.StatisticsDto getStatisticsDto(com.copower.pmcc.assess.dto.input.StatisticsDto statisticsDto) {
        if (StringUtils.isNotBlank(statisticsDto.getName())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(statisticsDto.getName());
            if (sysUser != null && StringUtils.isNotBlank(sysUser.getUserName())) {
                statisticsDto.setName(sysUser.getUserName());
            }
        }
        return statisticsDto;
    }

    /**
     * 引用数据
     *
     * @param id
     * @return
     */
    public BasicApplyBatch referenceDataById(Integer id, Integer projectId, Integer planDetailsId) {
        //通过id引用数据结构及关联的数据信息
        BasicHouseCaseSummary houseCaseSummary = getBasicHouseCaseSummaryById(id);
        if (houseCaseSummary == null) return null;
        return basicApplyBatchService.referenceDataByDetailId(houseCaseSummary.getCaseHouseId(),projectId,planDetailsId);
    }
}
