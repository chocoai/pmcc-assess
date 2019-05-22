package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandDetailAchievementDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.output.data.DataLandDetailAchievementVo;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/21 17:53
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class WordSheetTest {

    @Autowired
    private DataLandLevelDetailDao dataLandLevelDetailDao;
    @Autowired
    private DataLandDetailAchievementDao dataLandDetailAchievementDao;

    @Test
    public void clear(){
        DataLandDetailAchievement query = new DataLandDetailAchievement();
        List<DataLandDetailAchievement> landDetailAchievements = dataLandDetailAchievementDao.getDataLandDetailAchievementList(query);

        System.out.println("======>"+landDetailAchievements.size());
    }

    private int id1989 = 1989;
    private int id1992 = 1992;
    private final List<String> gradeLists = Arrays.asList("1955", "1956", "1957", "1958", "1959");//"优","较优","一般","较劣","劣"
    private final List<String> gradeLists2 = Arrays.asList("优", "较优", "一般", "较劣", "劣");//"优","较优","一般","较劣","劣"
    private final List<String> romanNumeralList = Arrays.asList("Ⅰ级", "Ⅱ级", "Ⅲ级", "IV级", "Ⅴ级", "Ⅵ级", "Ⅶ级");
    final String path = "D:\\temp\\成都中心城区基准地价\\";
    private final List<String> asList = Arrays.asList("工业用地", "公共管理与公共服务用地", "商服用地", "住宅用地");
    private final String suffix = ".xlsx";
    private final String centerName = "成都中心城区基准地价";
    private final String centerSheetName = "成都市中心城区各类用地基准地价一览表";
    private final String A = "A";
    private final String B = "B";

    @Test
    public void firstTest() throws Exception {
        List<ExampleDDD> exampleDDDList = centerTable();
        sonHandle(exampleDDDList);
    }

    private void sonHandle(List<ExampleDDD> exampleDDDList) throws Exception {
        if (CollectionUtils.isEmpty(exampleDDDList)) {
            return;
        }
        for (int i = 0; i < asList.size(); i++) {
            List<Sheet> sheetList = PoiUtils.getSheet(String.format("%s%s%s", path, asList.get(i), suffix));
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
            for (Map.Entry<Integer, Sheet> sheetEntry : bSheet.entrySet()) {
                Sheet targetSheet = null;//
                for (Map.Entry<List<Integer>, Sheet> listSheetEntry : aSheet.entrySet()) {
                    List<Integer> integerList = listSheetEntry.getKey();
                    if (integerList.contains(sheetEntry.getKey())) {
                        targetSheet = listSheetEntry.getValue();
                        continue;
                    }
                }
                if (targetSheet != null) {
                    Sheet sheet = sheetEntry.getValue();
                    Row rowA = null;
                    Row rowB = null;
                    ExampleDDD exampleDDD = null;
                    final int temp = i;
                    try {
                        exampleDDD = exampleDDDList.stream().filter(oo -> {
                            if (Objects.equal(oo.getType(), asList.get(temp))) {
                                if (oo.getNumber_().equals(sheetEntry.getKey())) {
                                    return true;
                                }
                            }
                            return false;
                        }).findFirst().get();
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage() + "    ===" + temp);
                    }
                    int rowLength = 0;
                    int c1 = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
                    int c2 = targetSheet.getPhysicalNumberOfRows() != 0 ? targetSheet.getPhysicalNumberOfRows() : targetSheet.getLastRowNum();
                    if (c1 >= c2) {
                        rowLength = c2;
                    } else {
                        rowLength = c1;
                    }
                    System.out.println("rowLength" + rowLength);
                    //工业用地
                    if (i == 0) {
                        setValue( rowA, rowB, rowLength, exampleDDD, sheet, targetSheet);
                        if (true) {
                            save(exampleDDD);
                        }
                    }
                    //公共管理与公共服务用地
                    if (i == 1) {
                        setValue( rowA, rowB, rowLength, exampleDDD, sheet, targetSheet);
                        if (true) {
                            save(exampleDDD);
                        }
                    }
                    //商服用地
                    if (i == 2) {
                        setValue( rowA, rowB, rowLength, exampleDDD, sheet, targetSheet);
                        if (true) {
                            save(exampleDDD);
                        }
                    }
                    //住宅用地
                    if (i == 3) {
                        setValue( rowA, rowB, rowLength, exampleDDD, sheet, targetSheet);
                        if (true) {
                            save(exampleDDD);
                        }
                    }
                    System.out.println(exampleDDD);
                    System.out.println(sheet.getSheetName() + " " + targetSheet.getSheetName());
                }
            }

        }
    }

    private void setValue( Row rowA, Row rowB, int rowLength, ExampleDDD exampleDDD, Sheet sheet, Sheet targetSheet) {
        for (int k = 3; k < rowLength; k++) {
            rowB = sheet.getRow(k);
            rowA = targetSheet.getRow(k);
            for (int j = 0; j < gradeLists.size(); j++) {
                DataLandDetailAchievementVo  land = new DataLandDetailAchievementVo();
                land.setCategoryName(PoiUtils.getCellValue(rowA.getCell(2)));
                land.setCategory(PoiUtils.getCellValue(rowB.getCell(2)));
                land.setGradeName(gradeLists2.get(j));
                land.setGrade(Integer.parseInt(gradeLists.get(j)));
                land.setReamark(PoiUtils.getCellValue(rowA.getCell(3 + j)));
                String value = PoiUtils.getCellValue(rowB.getCell(3 + j));
                if (StringUtils.isNotEmpty(value)) {
                    land.setAchievement(new BigDecimal(value));
                }
                if (k >= 10) {
                    //个别因素
                    land.setType(id1989);
                } else {
                    //区域因素
                    land.setType(id1992);
                }
                if (exampleDDD != null) {
                    exampleDDD.getDataLandDetailAchievementVos().add(land);
                }
            }
        }
    }

    private void save(ExampleDDD exampleDDD) {
        if (exampleDDD != null) {
            DataLandLevelDetail dataLandLevelDetail = new DataLandLevelDetail();
            dataLandLevelDetail.setType(exampleDDD.getNumber());
            dataLandLevelDetail.setClassify(exampleDDD.getType());
            dataLandLevelDetail.setLandLevelId(332);
            dataLandLevelDetail.setPrice(new BigDecimal(exampleDDD.getPrice()));
            dataLandLevelDetailDao.addDataLandLevelDetail(dataLandLevelDetail);
            exampleDDD.getDataLandDetailAchievementVos().stream().filter(po -> {
                if (po.getAchievement() == null) {
                    return false;
                }
                if (StringUtils.isEmpty(po.getReamark())) {
                    return false;
                }
                if (StringUtils.isEmpty(po.getCategory())) {
                    return false;
                }
                return true;
            }).forEachOrdered(po -> {
                po.setLevelDetailId(dataLandLevelDetail.getId());
                dataLandDetailAchievementDao.saveDataLandDetailAchievement(po);
            });
        }
    }


    private List<ExampleDDD> centerTable() throws Exception {
        Workbook workbook = PoiUtils.getWorkbook(String.format("%s%s%s", path, centerName, suffix));
        Row row = null;
        Cell cell = null;
        Sheet sheet = workbook.getSheet(centerSheetName);
        //总列数
        int colLength = sheet.getRow(0).getPhysicalNumberOfCells() != 0 ? sheet.getRow(0).getPhysicalNumberOfCells() : sheet.getRow(0).getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        List<ExampleDDD> exampleDDDList = Lists.newArrayList();

        int[] rows = new int[]{2, 4, 6, 8};
        for (int i = 0; i < rows.length; i++) {
            int k = rows[i];
            int cellL = 2;
            row = sheet.getRow(k);
            List<String> stringList = Lists.newArrayList();
            for (int j = 0; j < romanNumeralList.size(); j++) {
                stringList.add(PoiUtils.getCellValue(row.getCell(cellL++)));
            }
            stringList = stringList.stream().filter(s -> NumberUtils.isNumber(s)).collect(Collectors.toList());
            for (int j = 0; j < stringList.size(); j++) {
                String s = stringList.get(j);
                ExampleDDD example = new ExampleDDD();
                example.setPrice(Integer.parseInt(s)).setType(asList.get(i)).setNumber_(j + 1).setNumber(romanNumeralList.get(j));
                exampleDDDList.add(example);
            }
        }

        return exampleDDDList;

    }


}
