package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-4-12.
 */
public class ZhaiQuanTest {
    public static void main(String[] args) throws Exception {
        String wordPath = "C:\\Users\\kings\\Desktop\\zhaiquan\\新客户模板2019-04-12.doc";
        String excelPath = "C:\\Users\\kings\\Desktop\\zhaiquan\\新客户原始报告数据-雅安.xlsx";
        //读取excel 生成对应替换内容
        InputStream is = new FileInputStream(excelPath);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(excelPath) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        Row row;
        Cell cell;
        Map<Integer, String> fieldIndex = Maps.newHashMap();
        List<ZhaiQuanDto> list = Lists.newArrayList();
        List<String> dywFieldList = Lists.newArrayList();
        ZhaiQuanDto preZhaiQuanDto = null;//用于只处理抵押物
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            row = sheet.getRow(rowNum);
            if (row == null)
                return;
            if (rowNum == 0) {
                //确定单元格对应的字段 将每一列与配置的字段对应
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    if (cell == null)
                        continue;
                    String value = PoiUtils.getCellValue(cell);
                    if (StringUtils.isBlank(value))
                        continue;
                    fieldIndex.put(i, value);
                }
            } else if (rowNum > 4) {
                ZhaiQuanDto zhaiQuanDto = new ZhaiQuanDto();
                KeyValueDto customerInfo = new KeyValueDto();
                List<KeyValueDto> customerInfoFields = Lists.newArrayList();
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    if (cell == null)
                        continue;
                    if (fieldIndex.get(i) == null)
                        continue;
                    String value = PoiUtils.getCellValue(cell);
                    if (dywFieldList.contains(fieldIndex.get(i))) {

                    } else {

                    }
                }
                list.add(zhaiQuanDto);
            }
        }

        for (ZhaiQuanDto zhaiQuanDto : list) {//开始替换生成文件
            String newWordPath = String.format("D:\\zhaiquan\\%s%s%s.doc", zhaiQuanDto.getEjfh(), zhaiQuanDto.getKhmc(), zhaiQuanDto.getNumber());
            FileUtils.copyFile(wordPath, newWordPath);
            Map<String, String> map = Maps.newHashMap();
            AsposeUtils.replaceText(newWordPath, map);
        }
    }
}
