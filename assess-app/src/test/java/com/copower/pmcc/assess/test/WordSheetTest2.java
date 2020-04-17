package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.RomanNumeral;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseDataDicDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailAchievementDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * @author: zch
 * @date: 2019/5/21 17:53
 * @description: 土地因素  方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class WordSheetTest2 {
    @Autowired
    private DataLandLevelDetailDao dataLandLevelDetailDao;
    @Autowired
    private DataLandLevelDetailAchievementDao dataLandLevelDetailAchievementDao;
    @Autowired
    private BaseDataDicDao cmsBaseDataDicDao;
    private final String classA = "工业用地";
    private final String classB = "公共管理与公共服务用地";
    private final String classC = "商服用地";
    private final String classD = "住宅用地";
    private final List<String> asList = Arrays.asList(classA, classB, classC, classD);
    private final String A = "A";
    private final String B = "B";
    private final List<BaseDataDic> grades = new ArrayList<>(5);
    private final List<BaseDataDic> types = new ArrayList<>(2);
    private final List<Integer> integerList = new ArrayList<>() ;

    @Test
    public void run() throws Exception {
        final StringBuffer stringBuffer = new StringBuffer(128);
        List<DataLandLevelDetailVo> dataLandLevelDetailVos = getDataLandLevelDetailList();
        if (CollectionUtils.isEmpty(dataLandLevelDetailVos)) {
            return;
        }
        Map<String, List<DataLandLevelDetailVo>> map = new HashMap<>();
        asList.forEach(s -> {
            List<DataLandLevelDetailVo> landLevelDetailVos = new ArrayList<>();
            dataLandLevelDetailVos.forEach(vo -> {
                if (Objects.equal(s, vo.getClassifyName())) {
                    landLevelDetailVos.add(vo);
                }
            });
            map.put(s, landLevelDetailVos);
        });
        if (map.isEmpty()) {
            return;
        }
        Set<Map.Entry<String, List<DataLandLevelDetailVo>>> set = map.entrySet();
        Iterator<Map.Entry<String, List<DataLandLevelDetailVo>>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<DataLandLevelDetailVo>> entry = it.next();
            String className = entry.getKey();
            List<DataLandLevelDetailVo> list = entry.getValue();
            List<Sheet> sheetList = PoiUtils.getSheet(String.join("", "D:\\temp\\成都中心城区基准地价\\", "成都中心城区基准地价-", className, ".xlsx"));
            Map<List<Integer>, Sheet> aSheet = Maps.newHashMap();
            Map<Integer, Sheet> bSheet = Maps.newHashMap();
            sheetList.forEach(sheet -> {
                if (StringUtils.containsAny(sheet.getSheetName(), A)) {
                    List<Integer> integerList = FormatUtils.transformString2Integer(StringUtils.remove(sheet.getSheetName(), A));
                    aSheet.put(integerList, sheet);
                }
                if (StringUtils.containsAny(sheet.getSheetName(), B)) {
                    String s2 = StringUtils.remove(sheet.getSheetName(), B);
                    bSheet.put(Integer.parseInt(s2), sheet);
                }
            });
            List<SheetDemo> sheetDemoList = new ArrayList<>();
            for (Map.Entry<Integer, Sheet> sheetEntry : bSheet.entrySet()) {
                Sheet a = null;
                for (Map.Entry<List<Integer>, Sheet> listSheetEntry : aSheet.entrySet()) {
                    List<Integer> integerList = listSheetEntry.getKey();
                    if (integerList.contains(sheetEntry.getKey())) {
                        a = listSheetEntry.getValue();
                        break;
                    }
                }
                if (a == null) {
                    continue;
                }
                sheetDemoList.add(new SheetDemo(a, sheetEntry.getValue(), sheetEntry.getKey(), RomanNumeral.stringValue(sheetEntry.getKey())));
            }
            if (CollectionUtils.isEmpty(sheetDemoList)) {
                continue;
            }
            switch (className) {
                case classA: {
                    Iterator<SheetDemo> iterator = sheetDemoList.iterator();
                    while (iterator.hasNext()) {
                        SheetDemo sheetDemo = iterator.next();
                        Integer key = sheetDemo.getNum();
                        DataLandLevelDetailVo dataLandLevelDetailVo = list.stream().filter(landLevelDetail -> Objects.equal(landLevelDetail.getTypeName(), sheetDemo.getRoman())).findFirst().get();
                        switch (key) {
                            case 1: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                }
                case classB: {
                    Iterator<SheetDemo> iterator = sheetDemoList.iterator();
                    while (iterator.hasNext()) {
                        SheetDemo sheetDemo = iterator.next();
                        Integer key = sheetDemo.getNum();
                        DataLandLevelDetailVo dataLandLevelDetailVo = list.stream().filter(landLevelDetail -> Objects.equal(landLevelDetail.getTypeName(), sheetDemo.getRoman())).findFirst().get();
                        switch (key) {
                            case 1: {
                                try {
                                    handleSheet(sheetDemo, 7, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    handleSheet(sheetDemo, 7, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    handleSheet(sheetDemo, 7, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    handleSheet(sheetDemo, 8, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 5: {
                                try {
                                    handleSheet(sheetDemo, 8, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 6: {
                                try {
                                    handleSheet(sheetDemo, 8, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                }
                case classC: {
                    Iterator<SheetDemo> iterator = sheetDemoList.iterator();
                    while (iterator.hasNext()) {
                        SheetDemo sheetDemo = iterator.next();
                        Integer key = sheetDemo.getNum();
                        DataLandLevelDetailVo dataLandLevelDetailVo = list.stream().filter(landLevelDetail -> Objects.equal(landLevelDetail.getTypeName(), sheetDemo.getRoman())).findFirst().get();
                        switch (key) {
                            case 1: {
                                try {
                                    handleSheet(sheetDemo, 12, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    handleSheet(sheetDemo, 8, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                    e.printStackTrace();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    handleSheet(sheetDemo, 8, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    handleSheet(sheetDemo, 9, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 5: {
                                try {
                                    handleSheet(sheetDemo, 9, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 6: {
                                try {
                                    handleSheet(sheetDemo, 9, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 7: {
                                try {
                                    handleSheet(sheetDemo, 9, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                }
                case classD: {
                    Iterator<SheetDemo> iterator = sheetDemoList.iterator();
                    while (iterator.hasNext()) {
                        SheetDemo sheetDemo = iterator.next();
                        Integer key = sheetDemo.getNum();
                        DataLandLevelDetailVo dataLandLevelDetailVo = list.stream().filter(landLevelDetail -> Objects.equal(landLevelDetail.getTypeName(), sheetDemo.getRoman())).findFirst().get();
                        switch (key) {
                            case 1: {
                                try {
                                    handleSheet(sheetDemo, 5, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    handleSheet(sheetDemo, 5, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 5: {
                                try {
                                    handleSheet(sheetDemo, 10, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            case 6: {
                                try {
                                    handleSheet(sheetDemo, 9, dataLandLevelDetailVo);
                                } catch (Exception e) {
                                    errorHandle(stringBuffer, e, className, key);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        System.out.println(StringUtils.repeat("_", 100));
        System.out.println(stringBuffer.toString());
    }

    private void errorHandle(StringBuffer stringBuffer, Exception e, String className, Integer key) throws Exception{
        stringBuffer.append("\r").append("[").append(className).append(StringUtils.repeat("-", 5)).append(key).append(e.getMessage()).append("]");
        throw e;
    }

    private void handleSheet(SheetDemo sheetDemo, final int targetRow, DataLandLevelDetailVo dataLandLevelDetailVo) {
        Sheet aSheet = sheetDemo.getA();
        Sheet bSheet = sheetDemo.getB();
        final int cellLength = 8;//固定列数
//        int aLength = aSheet.getPhysicalNumberOfRows() != 0 ? aSheet.getPhysicalNumberOfRows() : aSheet.getLastRowNum();
//        int bLength = bSheet.getPhysicalNumberOfRows() != 0 ? bSheet.getPhysicalNumberOfRows() : bSheet.getLastRowNum();

        int aLength = aSheet.getLastRowNum() != 0 ? aSheet.getLastRowNum() : aSheet.getPhysicalNumberOfRows();
        int bLength = bSheet.getLastRowNum() != 0 ? bSheet.getLastRowNum() : bSheet.getPhysicalNumberOfRows();
        int rowLength = Arrays.stream(new int[]{aLength, bLength}).reduce(Integer::max).getAsInt();//两个表中的最大行数
        integerList.add(rowLength) ;
        for (int k = 3; k < rowLength; k++) {
            Row aRow = null, bRow = null;
            if (aLength != 0) {
                aRow = aSheet.getRow(k);
                aLength--;
            }
            if (bLength != 0) {
                bRow = bSheet.getRow(k);
                bLength--;
            }
            if (aRow == null && bRow == null) {
                continue;
            }
            for (int i = 0; i < cellLength; i++) {
                DataLandLevelDetailAchievement landAchievement = new DataLandLevelDetailAchievement();
                if (k >= targetRow) {
                    landAchievement.setType(types.stream().filter(baseDataDic -> "个别因素".equals(baseDataDic.getName())).findFirst().get().getId());
                } else {
                    landAchievement.setType(types.stream().filter(baseDataDic -> "区域因素".equals(baseDataDic.getName())).findFirst().get().getId());
                }
                switch (i) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        fiveHandle(i, "优", aRow, bRow, landAchievement);
                        break;
                    }
                    case 4: {
                        fiveHandle(i, "较优", aRow, bRow, landAchievement);
                        break;
                    }
                    case 5: {
                        fiveHandle(i, "一般", aRow, bRow, landAchievement);
                        break;
                    }
                    case 6: {
                        fiveHandle(i, "较劣", aRow, bRow, landAchievement);
                        break;
                    }
                    case 7: {
                        fiveHandle(i, "劣", aRow, bRow, landAchievement);
                        break;
                    }
                    default:
                        break;
                }
                landAchievement.setCreator("admin");
                landAchievement.setLevelDetailId(dataLandLevelDetailVo.getId());
                if (landAchievement.getAchievement() != null || StringUtils.isNotEmpty(landAchievement.getReamark())) {
                    dataLandLevelDetailAchievementDao.saveDataLandLevelDetailAchievement(landAchievement);
                }
            }
        }
    }

    private void fiveHandle(final int i, final String name, Row aRow, Row bRow, DataLandLevelDetailAchievement landAchievement) {
        if (aRow != null) {
            if (aRow.getCell(i) != null) {
                if (StringUtils.isNotEmpty(PoiUtils.getCellValue(aRow.getCell(i)))) {
                    landAchievement.setReamark(PoiUtils.getCellValue(aRow.getCell(i)));
                }
            }

//            if (aRow.getCell(1) != null) {
//                if (StringUtils.isNotBlank(PoiUtils.getCellValue(aRow.getCell(1)))) {
//                    landAchievement.setCategory(PoiUtils.getCellValue(aRow.getCell(1)));
//                }
//            }
            if (aRow.getCell(2) != null) {
                if (StringUtils.isNotBlank(PoiUtils.getCellValue(aRow.getCell(2)))) {
                    landAchievement.setCategory(PoiUtils.getCellValue(aRow.getCell(2)));
                }
            }
        }
        if (bRow != null) {
            if (bRow.getCell(i) != null) {
                String achievement = PoiUtils.getCellValue(bRow.getCell(i));
                if (StringUtils.isNotEmpty(achievement)) {
                    if (NumberUtils.isNumber(achievement)) {
                        if ("0".equals(achievement)) {
                            landAchievement.setAchievement(ArithmeticUtils.createBigDecimal(achievement));
                        } else {
                            landAchievement.setAchievement(ArithmeticUtils.createBigDecimal(ArithmeticUtils.div(achievement, "100")));
                        }
                    }
                }
            }


//            if (bRow.getCell(1) != null) {
//                if (StringUtils.isNotBlank(PoiUtils.getCellValue(bRow.getCell(1)))) {
//                    landAchievement.setCategory(PoiUtils.getCellValue(bRow.getCell(1)));
//                }
//            }
            if (bRow.getCell(2) != null) {
                if (StringUtils.isNotBlank(PoiUtils.getCellValue(bRow.getCell(2)))) {
                    landAchievement.setCategory(PoiUtils.getCellValue(bRow.getCell(2)));
                }
            }
        }


        landAchievement.setGrade(grades.stream().filter(baseDataDic -> name.equals(baseDataDic.getName())).findFirst().get().getId());
    }

    class SheetDemo implements Serializable {
        private Sheet a;
        private Sheet b;
        private Integer num;
        private String roman;

        public Sheet getA() {
            return a;
        }

        public void setA(Sheet a) {
            this.a = a;
        }

        public Sheet getB() {
            return b;
        }

        public void setB(Sheet b) {
            this.b = b;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getRoman() {
            return roman;
        }

        public void setRoman(String roman) {
            this.roman = roman;
        }

        public SheetDemo(Sheet a, Sheet b, Integer num, String roman) {
            this.a = a;
            this.b = b;
            this.num = num;
            this.roman = roman;
        }

        @Override
        public String toString() {
            return "SheetDemo{" +
                    "a=" + a.getSheetName() +
                    ", b=" + b.getSheetName() +
                    ", num=" + num +
                    ", roman='" + roman + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SheetDemo sheetDemo = (SheetDemo) o;

            if (!a.equals(sheetDemo.a)) return false;
            if (!b.equals(sheetDemo.b)) return false;
            if (!num.equals(sheetDemo.num)) return false;
            return roman.equals(sheetDemo.roman);
        }

        @Override
        public int hashCode() {
            int result = a.hashCode();
            result = 31 * result + b.hashCode();
            result = 31 * result + num.hashCode();
            result = 31 * result + roman.hashCode();
            return result;
        }
    }

    public DataLandLevelDetailVo getDataLandLevelDetailVo(DataLandLevelDetail oo) {
        DataLandLevelDetailVo vo = new DataLandLevelDetailVo();
        if (oo == null) {
            return vo;
        }
        BeanUtils.copyProperties(oo, vo);
        if (oo.getPid() != null) {
            DataLandLevelDetail parent = dataLandLevelDetailDao.getDataLandLevelDetailById(oo.getPid());
            if (parent != null) {
                if (parent.getClassify() != null) {
                    oo.setClassify(parent.getClassify());
                }
            }
        }
        if (oo.getClassify() != null) {
            vo.setClassifyName(cmsBaseDataDicDao.getSingleObject(oo.getClassify()).getName());
        }
        if (oo.getType() != null) {
            vo.setTypeName(cmsBaseDataDicDao.getSingleObject(oo.getType()).getName());
        }
        return vo;
    }

    public List<DataLandLevelDetailVo> getDataLandLevelDetailList() {
        List<DataLandLevelDetailVo> voList = new ArrayList<>();
        DataLandLevelDetail select = new DataLandLevelDetail();
        select.setLandLevelId(332);//区域id
        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailDao.getDataLandLevelDetailList(select);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            Iterator<DataLandLevelDetail> it = dataLandLevelDetailList.iterator();
            while (it.hasNext()) {
                DataLandLevelDetail oo = it.next();
                if (oo.getPid() == 0) {
                    continue;
                }
                voList.add(getDataLandLevelDetailVo(oo));
            }
        }
        return voList;
    }

    @Before
    public void before() {
        DataLandLevelDetailAchievement query = new DataLandLevelDetailAchievement();
        List<DataLandLevelDetailAchievement> LandLevelDetailAchievements = dataLandLevelDetailAchievementDao.getDataLandLevelDetailAchievementList(query);
        if (CollectionUtils.isNotEmpty(LandLevelDetailAchievements)) {
            LandLevelDetailAchievements.forEach(dataLandLevelDetailAchievement -> dataLandLevelDetailAchievementDao.deleteDataLandLevelDetailAchievement(dataLandLevelDetailAchievement.getId()));
        }
        grades.addAll(cmsBaseDataDicDao.getEnableList("programme.market.costApproach.grade"));
        types.addAll(cmsBaseDataDicDao.getEnableList("programme.market.costApproach.factor"));
    }

    @Test
    public void createExcel()throws Exception{
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row headerRow = sheet.createRow(0); // 创建表头行
        headerRow.createCell(0).setCellValue("姓名");
        headerRow.createCell(1).setCellValue("性别");
        headerRow.createCell(2).setCellValue("年龄");
        Row row = null;
        for (int i = 1; i < 10; i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue("张三");
            row.createCell(1).setCellValue("男");
            row.createCell(2).setCellValue(20);
        }
        OutputStream fileOut = null;
        File file = new File("D:/poitest/workbook.xls");
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
    }





}
