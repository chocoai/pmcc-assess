package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexDetailVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: zch
 * @date: 2019/4/29 18:17
 * @description:房价指数 详情(从表)
 */
@Service
public class DataHousePriceIndexDetailService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataHousePriceIndexDetailDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean saveDataHousePriceIndexDetail(DataHousePriceIndexDetail oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataHousePriceIndexDetail(oo);
        } else {
            return dataLandDetailAchievementDao.editDataHousePriceIndexDetail(oo);
        }
    }

    public boolean deleteDataHousePriceIndexDetail(Integer id) {
        return dataLandDetailAchievementDao.deleteDataHousePriceIndexDetail(id);
    }

    public DataHousePriceIndexDetail getDataHousePriceIndexDetailById(Integer id) {
        return dataLandDetailAchievementDao.getDataHousePriceIndexDetailById(id);
    }

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(DataHousePriceIndexDetail oo) {
        return dataLandDetailAchievementDao.getDataHousePriceIndexDetailList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataHousePriceIndexDetail oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHousePriceIndexDetail> list = getDataHousePriceIndexDetailList(oo);
        List<DataHousePriceIndexDetailVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataHousePriceIndexDetailVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataHousePriceIndexDetailVo getDataHousePriceIndexDetailVo(DataHousePriceIndexDetail oo) {
        if (oo == null) {
            return null;
        }
        DataHousePriceIndexDetailVo vo = new DataHousePriceIndexDetailVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        return vo;
    }

    /**
     * 导出月份模板
     *
     * @param response
     */
    public void generateMonthTemplate(HttpServletResponse response) throws BusinessException, IOException, Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"年份", "月份", "单位地价", "楼面地价", "指数"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }
        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "指数详情模板.XLS");
            wb.write(os);
        } catch (Exception e) {
            throw new BusinessException("导出Excel出错:" + e);
        } finally {
            os.flush();
            os.close();
        }
    }


    /**
     * 导出季度模板
     *
     * @param response
     */
    public void generateQuarterTemplate(HttpServletResponse response) throws BusinessException, IOException, Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"年份", "季度", "单位地价", "楼面地价", "指数"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }
        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "指数详情模板.XLS");
            wb.write(os);
        } catch (Exception e) {
            throw new BusinessException("导出Excel出错:" + e);
        } finally {
            os.flush();
            os.close();
        }
    }


    /**
     * 响应流
     *
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther:
     * @date: 2018/9/25 17:58
     */
    public String importData(MultipartFile multipartFile, Integer housePriceId) throws Exception {
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
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        Row firstRow = sheet.getRow(0);
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            DataHousePriceIndexDetail housePriceIndexDetail = null;
            try {
                housePriceIndexDetail = new DataHousePriceIndexDetail();
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                if (!importDataHousePriceIndexDetail(housePriceIndexDetail, builder, row, i, firstRow)) {
                    continue;
                }
                housePriceIndexDetail.setHousePriceId(housePriceId);
                housePriceIndexDetail.setCreator(commonService.thisUser().getUserName());
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
                continue;
            }
            this.saveDataHousePriceIndexDetail(housePriceIndexDetail);
            successCount++;
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean importDataHousePriceIndexDetail(DataHousePriceIndexDetail housePriceIndexDetail, StringBuilder builder, Row row, int i, Row firstRow) throws Exception {
        Calendar cal1 = Calendar.getInstance();
        //年份
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(0)))) {
                String year = PoiUtils.getCellValue(row.getCell(0));
                cal1.set(Calendar.YEAR, Integer.valueOf(year));
            } else {
                builder.append(String.format("\n第%s行异常：年份应填写数字", i));
                return false;
            }
        } else {
            builder.append(String.format("\n第%s行异常：年份需要填写", i));
            return false;
        }
        //判断月份还是季度
        String value = PoiUtils.getCellValue(firstRow.getCell(1));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(1)))) {
                if ("月份".equals(value)) {
                    String month = PoiUtils.getCellValue(row.getCell(1));
                    cal1.set(Calendar.MONTH,Integer.valueOf(month)-1);
                    //将时分秒清零
                    cal1.set(Calendar.DAY_OF_MONTH, 1);
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.set(Calendar.SECOND, 0);
                    Date result = cal1.getTime();
                    housePriceIndexDetail.setStartDate(result);
                    housePriceIndexDetail.setEndDate(result);
                }else {
                    Integer quarter = Integer.valueOf(PoiUtils.getCellValue(row.getCell(1)));
                    //开始时间
                    cal1.set(Calendar.MONTH,(quarter-1)*3);
                    cal1.set(Calendar.DAY_OF_MONTH, 1);
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.set(Calendar.SECOND, 0);
                    Date startDate = cal1.getTime();
                    housePriceIndexDetail.setStartDate(startDate);
                    //结束时间
                    cal1.set(Calendar.MONTH,quarter*3);
                    cal1.set(Calendar.DAY_OF_MONTH, 0);
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.set(Calendar.SECOND, 0);
                    Date endData = cal1.getTime();
                    housePriceIndexDetail.setEndDate(endData);
                }
            } else {
                builder.append(String.format("\n第%s行异常：月份或季度应填写数字", i));
                return false;
            }

        }else {
            builder.append(String.format("\n第%s行异常：月份或季度需要填写", i));
            return false;
        }


        //单位地价
        if (!StringUtils.isEmpty(PoiUtils.getCellValue(row.getCell(2)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(2)))) {
                housePriceIndexDetail.setUnitPremium(new BigDecimal(PoiUtils.getCellValue(row.getCell(2))));
            } else {
                builder.append(String.format("\n第%s行异常：单位地价应填写数字", i));
                return false;
            }
        }
        //楼面地价
        if (!StringUtils.isEmpty(PoiUtils.getCellValue(row.getCell(3)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(3)))) {
                housePriceIndexDetail.setFloorPremium(new BigDecimal(PoiUtils.getCellValue(row.getCell(3))));
            } else {
                builder.append(String.format("\n第%s行异常：楼面地价应填写数字", i));
                return false;
            }
        }
        //指数
        if (!StringUtils.isEmpty(PoiUtils.getCellValue(row.getCell(4)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(4)))) {
                housePriceIndexDetail.setIndexNumber(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));
            } else {
                builder.append(String.format("\n第%s行异常：指数应填写数字", i));
                return false;
            }
        }

        return true;
    }

    //验证是否是数字或小数
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) { //判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }

    }
}
