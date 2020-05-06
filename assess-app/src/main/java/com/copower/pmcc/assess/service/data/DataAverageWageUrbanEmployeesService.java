package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.ExcelImportUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAverageWageUrbanEmployeesDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployees;
import com.copower.pmcc.assess.dto.output.data.DataAverageWageUrbanEmployeesVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
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
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by zch on 2020-3-20.
 * 城镇就业人员平均工资
 */
@Service
public class DataAverageWageUrbanEmployeesService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DataAverageWageUrbanEmployeesDao dataAverageWageUrbanEmployeesDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ErpAreaService erpAreaService;

    public String importDataAverageWageUrbanEmployees(MultipartFile multipartFile) throws Exception {
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
            baseService.writeExceptionInfo(e, "城镇就业人员平均工资 ");
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 2;
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
        Map<String, String> map = new HashMap<>();
        //必填项
        List<String> requiredList = Arrays.asList("province","city") ;
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ExcelImportUtils.getMultimapByClass(DataAverageWageUrbanEmployees.class, row);
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                DataAverageWageUrbanEmployees target = new DataAverageWageUrbanEmployees();
                //excel 导入处理
                if (!ExcelImportUtils.excelImportHelp(classArrayListMultimap, target, builder, row, null, requiredList, false)) {
                    continue;
                }
                //验证(区域)
                if (erpAreaService.checkArea(target.getProvince(), target.getCity(), target.getDistrict(), builder, map)) {
                    if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                        target.setProvince(map.get(erpAreaService.PROVINCE));
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                        target.setCity(map.get(erpAreaService.CITY));
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                        target.setDistrict(map.get(erpAreaService.DISTRICT));
                    }
                } else {
                    ExcelImportUtils.excelImportWriteErrorInfo(row.getRowNum(), 0, "区域类型与系统配置的名称不一致 省市区(县)", false, builder);
                   continue;
                }
                handelExcel(target);
                saveAndUpdateDataAverageWageUrbanEmployees(target, false);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    private void handelExcel(DataAverageWageUrbanEmployees target) {
        List<BigDecimal> bigDecimalList = new ArrayList<>() ;
        Consumer<BigDecimal> consumer = ((bigDecimal -> {
            if (bigDecimal != null){
                bigDecimalList.add(bigDecimal);
            }
        })) ;
        consumer.accept(target.getCollectiveEconomy());
        consumer.accept(target.getOtherEconomy());
        consumer.accept(target.getPrivateEconomy());
        consumer.accept(target.getStateOwnedEconomy());
        if (CollectionUtils.isNotEmpty(bigDecimalList) && target.getTotal() == null){
            target.setTotal(ArithmeticUtils.add(bigDecimalList));
        }
    }

    public boolean updateDataAverageWageUrbanEmployees(DataAverageWageUrbanEmployees oo, boolean updateNull) {
        return dataAverageWageUrbanEmployeesDao.updateDataAverageWageUrbanEmployees(oo, updateNull);
    }

    public boolean saveDataAverageWageUrbanEmployees(DataAverageWageUrbanEmployees oo) throws BusinessException {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }

        boolean b = dataAverageWageUrbanEmployeesDao.saveDataAverageWageUrbanEmployees(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataAverageWageUrbanEmployees.class), oo.getId());
        return b;

    }

    public void saveAndUpdateDataAverageWageUrbanEmployees(DataAverageWageUrbanEmployees oo, boolean updateNull) throws BusinessException {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateDataAverageWageUrbanEmployees(oo, updateNull);
        } else {
            saveDataAverageWageUrbanEmployees(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DataAverageWageUrbanEmployees.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDataAverageWageUrbanEmployeesById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                dataAverageWageUrbanEmployeesDao.deleteDataAverageWageUrbanEmployeesById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                dataAverageWageUrbanEmployeesDao.deleteDataAverageWageUrbanEmployeesByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DataAverageWageUrbanEmployees query) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAverageWageUrbanEmployees> loanBenchmarkInterestRates = getDataAverageWageUrbanEmployeesListByQuery(query);
        List<DataAverageWageUrbanEmployeesVo> vos  = new ArrayList<>() ;
        if (CollectionUtils.isNotEmpty(loanBenchmarkInterestRates)){
            loanBenchmarkInterestRates.forEach(po -> vos.add(getDataAverageWageUrbanEmployeesVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataAverageWageUrbanEmployees> getDataAverageWageUrbanEmployeesByIds(List<Integer> ids) {
        return dataAverageWageUrbanEmployeesDao.getDataAverageWageUrbanEmployeesByIds(ids);
    }

    public DataAverageWageUrbanEmployees getDataAverageWageUrbanEmployeesById(Integer id) {
        return dataAverageWageUrbanEmployeesDao.getDataAverageWageUrbanEmployeesById(id);
    }


    public List<DataAverageWageUrbanEmployees> getDataAverageWageUrbanEmployeesListByQuery(DataAverageWageUrbanEmployees oo) {
        return dataAverageWageUrbanEmployeesDao.getDataAverageWageUrbanEmployeesListByExample(oo);
    }

    public DataAverageWageUrbanEmployeesVo getDataAverageWageUrbanEmployeesVo(DataAverageWageUrbanEmployees oo){
        if (oo == null){
            return null;
        }
        DataAverageWageUrbanEmployeesVo vo = new DataAverageWageUrbanEmployeesVo();
        BeanUtils.copyProperties(oo,vo);
        if (StringUtils.isNotBlank(oo.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(oo.getProvince()));
        }
        if (StringUtils.isNotBlank(oo.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(oo.getCity()));
        }
        if (StringUtils.isNotBlank(oo.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(oo.getDistrict()));
        }
        return vo;
    }


}
