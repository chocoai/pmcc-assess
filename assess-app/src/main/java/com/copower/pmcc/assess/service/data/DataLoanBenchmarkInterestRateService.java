package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.ExcelImportUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLoanBenchmarkInterestRateDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLoanBenchmarkInterestRate;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * Created by zch on 2020-3-20.
 * 贷款基准利率
 */
@Service
public class DataLoanBenchmarkInterestRateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DataLoanBenchmarkInterestRateDao dataLoanBenchmarkInterestRateDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;

    public String importDataLoanBenchmarkInterestRate(MultipartFile multipartFile) throws Exception {
        StringBuilder builder = new StringBuilder(10);
        Workbook workbook = null;
        Row row = null;
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "贷款基准利率 ");
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 3;
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
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ExcelImportUtils.getMultimapByClass(DataLoanBenchmarkInterestRate.class, row);
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                DataLoanBenchmarkInterestRate target = new DataLoanBenchmarkInterestRate();
                //excel 导入处理
                if (!ExcelImportUtils.excelImportHelp(classArrayListMultimap, target, builder, row, null, Arrays.asList("adjustTime"), false)) {
                    continue;
                }
                handelExcel(target);
                saveAndUpdateDataLoanBenchmarkInterestRate(target, false);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    private void handelExcel(DataLoanBenchmarkInterestRate target) {
        Function<BigDecimal, BigDecimal> function = (bigDecimal -> {
            if (bigDecimal == null) {
                return null;
            }
            return ArithmeticUtils.createBigDecimal(ArithmeticUtils.div(bigDecimal.toString(), "100"));
//            if (bigDecimal.toBigInteger().intValue() > 1) {
//            }
//            return bigDecimal;
        });
        target.setLessThanSixMonthLoanTermTax(function.apply(target.getLessThanSixMonthLoanTermTax()));
        target.setBetweenSixMonthAndOneYearLoanTermTax(function.apply(target.getBetweenSixMonthAndOneYearLoanTermTax()));
        target.setBetweenOneAndThreeYearLoanTermTax(function.apply(target.getBetweenOneAndThreeYearLoanTermTax()));
        target.setBetweenThreeAndFiveYearLoanTermTax(function.apply(target.getBetweenThreeAndFiveYearLoanTermTax()));
        target.setMoreThanFiveYearLoanTermTax(function.apply(target.getMoreThanFiveYearLoanTermTax()));
    }

    public boolean updateDataLoanBenchmarkInterestRate(DataLoanBenchmarkInterestRate oo, boolean updateNull) {
        return dataLoanBenchmarkInterestRateDao.updateDataLoanBenchmarkInterestRate(oo, updateNull);
    }

    public boolean saveDataLoanBenchmarkInterestRate(DataLoanBenchmarkInterestRate oo) throws BusinessException {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        if (oo.getAdjustTime() != null) {
            DataLoanBenchmarkInterestRate query = new DataLoanBenchmarkInterestRate();
            query.setAdjustTime(oo.getAdjustTime());
            List<DataLoanBenchmarkInterestRate> loanBenchmarkInterestRates = getDataLoanBenchmarkInterestRateListByQuery(query);
            if (CollectionUtils.isNotEmpty(loanBenchmarkInterestRates)) {
                throw new BusinessException("贷款基准利率 年份已经有了");
            }

        }
        boolean b = dataLoanBenchmarkInterestRateDao.saveDataLoanBenchmarkInterestRate(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataLoanBenchmarkInterestRate.class), oo.getId());
        return b;

    }

    public void saveAndUpdateDataLoanBenchmarkInterestRate(DataLoanBenchmarkInterestRate oo, boolean updateNull) throws BusinessException {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateDataLoanBenchmarkInterestRate(oo, updateNull);
        } else {
            saveDataLoanBenchmarkInterestRate(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DataLoanBenchmarkInterestRate.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDataLoanBenchmarkInterestRateById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                dataLoanBenchmarkInterestRateDao.deleteDataLoanBenchmarkInterestRateById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                dataLoanBenchmarkInterestRateDao.deleteDataLoanBenchmarkInterestRateByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(Date startTime, Date endTime) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLoanBenchmarkInterestRate> loanBenchmarkInterestRates = getDataLoanBenchmarkInterestRateListLikeQuery(startTime, endTime);
        vo.setTotal(page.getTotal());
        vo.setRows(loanBenchmarkInterestRates);
        return vo;
    }

    public List<DataLoanBenchmarkInterestRate> getDataLoanBenchmarkInterestRateByIds(List<Integer> ids) {
        return dataLoanBenchmarkInterestRateDao.getDataLoanBenchmarkInterestRateByIds(ids);
    }

    public DataLoanBenchmarkInterestRate getDataLoanBenchmarkInterestRateById(Integer id) {
        return dataLoanBenchmarkInterestRateDao.getDataLoanBenchmarkInterestRateById(id);
    }


    public List<DataLoanBenchmarkInterestRate> getDataLoanBenchmarkInterestRateListByQuery(DataLoanBenchmarkInterestRate oo) {
        return dataLoanBenchmarkInterestRateDao.getDataLoanBenchmarkInterestRateListByExample(oo);
    }

    public List<DataLoanBenchmarkInterestRate> getDataLoanBenchmarkInterestRateListLikeQuery(Date startTime, Date endTime) {
        return dataLoanBenchmarkInterestRateDao.getDataLoanBenchmarkInterestRateListLikeQuery(startTime, endTime);
    }


}
