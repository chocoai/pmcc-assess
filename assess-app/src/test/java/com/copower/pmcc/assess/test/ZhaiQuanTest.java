package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-4-12.
 */
public class ZhaiQuanTest {
    public static void main(String[] args) throws Exception {
        //绵阳 成都74
        String area = "成都";
        String wordPath = "C:\\Users\\kings\\Desktop\\zhaiquan\\新客户原始报告模板定稿.doc";
        String excelPath = "";
        excelPath = "C:\\Users\\kings\\Desktop\\报告生成\\新客户原始报告数据-" + area + ".xlsx";
        excelPath = "E:\\报告生成\\新客户原始报告数据-" + area + ".xlsx";

        String newPath = "";
        newPath = "D:\\zhaiquan\\";
        newPath = "E:\\报告生成\\";
        //读取excel 生成对应替换内容
        InputStream is = new FileInputStream(excelPath);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(excelPath) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells() + 2;//总列数
        Row row;
        Cell cell;
        Map<Integer, String> fieldIndex = Maps.newHashMap();
        List<ZhaiQuanDto> list = Lists.newArrayList();
        ZhaiQuanDto preZhaiQuanDto = null;//用于只处理抵押物
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            row = sheet.getRow(rowNum);
            if (row == null)
                continue;
            if (rowNum == 0) {
                //确定单元格对应的字段 将每一列与配置的字段对应
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    if (cell == null) {
                        fieldIndex.put(i, "");
                        continue;
                    }
                    String value = PoiUtils.getCellValue(cell);
                    if (StringUtils.isBlank(value)) {
                        fieldIndex.put(i, "");
                        continue;
                    }
                    fieldIndex.put(i, value);
                }
            } else if (rowNum > 4) {
                String mutiDywFlag = PoiUtils.getCellValue(row.getCell(0));
                if (StringUtils.equals(mutiDywFlag, "1") && StringUtils.equals(preZhaiQuanDto.getMutiDywFlag(), "1")) {
                    List<ZhaiQuanDywDto> dywInfo = preZhaiQuanDto.getDywInfo();
                    ZhaiQuanDywDto zhaiQuanDywDto = new ZhaiQuanDywDto();
                    for (int i = 0; i < coloumNum; i++) {
                        cell = row.getCell(i);
                        if (cell == null)
                            continue;
                        if (StringUtils.isBlank(fieldIndex.get(i)))
                            continue;
                        String value = PoiUtils.getCellValue(cell);
                        switch (fieldIndex.get(i)) {
                            case "dywdz":
                                zhaiQuanDywDto.setDywdz(value);
                                break;
                            case "fcxz":
                                zhaiQuanDywDto.setFcxz(value);
                                break;
                            case "fcmj":
                                zhaiQuanDywDto.setFcmj(value);
                                break;
                            case "xbjye":
                                zhaiQuanDywDto.setXbjye(value);
                                break;
                            case "dywxz":
                                zhaiQuanDywDto.setDywxz(value);
                                break;
                        }
                    }
                    dywInfo.add(zhaiQuanDywDto);
                } else {

                    ZhaiQuanDto zhaiQuanDto = new ZhaiQuanDto();
                    zhaiQuanDto.setMutiDywFlag(mutiDywFlag);
                    KeyValueDto customerInfo = new KeyValueDto();
                    List<KeyValueDto> customerInfoFields = Lists.newArrayList();
                    ZhaiQuanDywDto zhaiQuanDywDto = new ZhaiQuanDywDto();
                    List<ZhaiQuanDywDto> zhaiQuanDywDtoList = Lists.newArrayList();
                    for (int i = 0; i < coloumNum; i++) {
                        cell = row.getCell(i);
                        if (cell == null)
                            continue;
                        if (StringUtils.isBlank(fieldIndex.get(i)))
                            continue;
                        String value = PoiUtils.getCellValue(cell);
                        switch (fieldIndex.get(i)) {
                            case "dywdz":
                                zhaiQuanDywDto.setDywdz(value);

                                KeyValueDto dto1 = new KeyValueDto();
                                dto1.setKey(fieldIndex.get(i));
                                dto1.setValue(value);
                                customerInfoFields.add(dto1);
                                break;
                            case "fcxz":
                                zhaiQuanDywDto.setFcxz(value);

                                KeyValueDto dto2 = new KeyValueDto();
                                dto2.setKey(fieldIndex.get(i));
                                dto2.setValue(value);
                                customerInfoFields.add(dto2);
                                break;
                            case "fcmj":
                                zhaiQuanDywDto.setFcmj(value);

                                KeyValueDto dto3 = new KeyValueDto();
                                dto3.setKey(fieldIndex.get(i));
                                dto3.setValue(value);
                                customerInfoFields.add(dto3);
                                break;
                            case "xbjye":
                                zhaiQuanDywDto.setXbjye(value);
                                break;
                            case "dywxz":
                                zhaiQuanDywDto.setDywxz(value);
                                break;
                            default:
                                KeyValueDto dto = new KeyValueDto();
                                dto.setKey(fieldIndex.get(i));
                                dto.setValue(value);
                                if (StringUtils.equals(fieldIndex.get(i), "ejfh")) {
                                    zhaiQuanDto.setEjfh(value);
                                }
                                if (StringUtils.equals(fieldIndex.get(i), "khmc")) {
                                    zhaiQuanDto.setKhmc(value);
                                }
                                if (StringUtils.equals(fieldIndex.get(i), "number")) {
                                    zhaiQuanDto.setNumber(value);
                                }
                                if (StringUtils.equals(fieldIndex.get(i), "role")) {
                                    zhaiQuanDto.setRole(value);
                                }
                                if (StringUtils.equals(fieldIndex.get(i), "version")) {
                                    zhaiQuanDto.setVersion(value);
                                }
                                customerInfoFields.add(dto);
                                break;
                        }
                    }
                    customerInfo.setKeyValueDtos(customerInfoFields);
                    zhaiQuanDto.setCustomerInfo(customerInfo);
                    zhaiQuanDywDtoList.add(zhaiQuanDywDto);
                    zhaiQuanDto.setDywInfo(zhaiQuanDywDtoList);
                    if (StringUtils.isBlank(zhaiQuanDto.getKhmc()))
                        break;
                    list.add(zhaiQuanDto);
                    preZhaiQuanDto = zhaiQuanDto;
                }
            }
        }
        for (int k = 0; k < list.size(); k++) {
            ZhaiQuanDto zhaiQuanDto = list.get(k);
            String newWordPath = String.format(newPath + area + "\\%s%s号-%s-%s%s%s.doc",
                    zhaiQuanDto.getEjfh(), zhaiQuanDto.getNumber(), zhaiQuanDto.getKhmc(),
                    zhaiQuanDto.getRole(), zhaiQuanDto.getVersion(), zhaiQuanDto.getNumber());
            copyFile(wordPath, newWordPath);
            Map<String, String> map = Maps.newHashMap();
            for (KeyValueDto dto : zhaiQuanDto.getCustomerInfo().getKeyValueDtos()) {
                switch (dto.getKey()) {
                    case "dkffsj":
                    case "htqdr":
                        map.put(String.format("${%s}", dto.getKey()), DateUtils.formatDate(DateUtils.convertDate(dto.getValue()), DateUtils.DATE_CHINESE_PATTERN));
                        break;
                    case "fxjzr":
                    case "bgcjsj":
                        map.put(String.format("${source_%s}", dto.getKey()), dto.getValue());
                        map.put(String.format("${%s}", dto.getKey()), DateUtils.formatDate(DateUtils.convertDate(dto.getValue()), DateUtils.DATE_CHINESE_PATTERN));
                        break;
                    case "bjscbl":
                    case "bxscbl":
                    case "dywbxl":
                    case "pmfbl":
                    case "ssfbl":
                    case "zxfbl":
                    case "sfjdfbl":
                        BigDecimal bigDecimal = new BigDecimal(dto.getValue());
                        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                        map.put(String.format("${%s}", dto.getKey()), bigDecimal.multiply(new BigDecimal("100")) + "%");
                        break;
                    case "bxhj":
                    case "bj":
                    case "lx":
                    case "scje":
                    case "xgsfhj":
                        BigDecimal decimal = new BigDecimal(dto.getValue());
                        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                        map.put(String.format("${%s}", dto.getKey()), decimal.toString());
                        break;
                    default:
                        map.put(String.format("${%s}", dto.getKey()), dto.getValue());
                        break;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < zhaiQuanDto.getDywInfo().size(); i++) {
                ZhaiQuanDywDto zhaiQuanDywDto = zhaiQuanDto.getDywInfo().get(i);
                stringBuilder.append(String.format("抵押物为借款人拥有位于%s房产。房产性质为%s，面积%s平方米。" +
                                "已办理抵押登记手续。现对应贷款本金余额%s元。"
                        , zhaiQuanDywDto.getDywdz(), zhaiQuanDywDto.getFcxz()
                        , zhaiQuanDywDto.getFcmj(), zhaiQuanDywDto.getXbjye()));
            }
            map.put("${bgyxq}", DateUtils.formatDate(DateUtils.addDay(DateUtils.convertDate(String.valueOf(map.get("${source_fxjzr}"))), 364), DateUtils.DATE_CHINESE_PATTERN));
            map.put("${number}", zhaiQuanDto.getNumber());
            map.put("${dywInfo}", stringBuilder.toString());
            AsposeUtils.replaceText(newWordPath, map);
        }
    }

    public static void copyFile(String oldPath, String newPath) throws Exception {
        try {
            int bytesum = 0;
            File oldfile = new File(oldPath);
            if (!oldfile.exists()) {
                throw new Exception("原文件不存在");
            } else {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];

                int byteread;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }

                inStream.close();
            }
        } catch (Exception var9) {
            throw new Exception(var9);
        }
    }
}
