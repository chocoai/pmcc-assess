package com.copower.pmcc.assess.test;

/**
 * Created by zch on 2020-4-20.
 */

import java.awt.*;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.hssf.usermodel.EscherGraphics;
import org.apache.poi.hssf.usermodel.EscherGraphics2d;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.Test;

public class TestExcel {

    public static final int PERCENT_WIDTH = 50;
    public static final int PERCENT_HEIGHT = 20;

    public static final float PXTOPT = 0.75f;

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("D:/line.xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("line");

        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(77 * PXTOPT);

        final String text = "                       AB\n\n\n CD";
        HSSFCell cell = row.createCell(0);
        HSSFCellStyle cellStyle = getCellFormat(wb);
        int x1 = 61, y1 = 77;
        int x2 = 132, y2 = 76;
        int x3 = 144, y3 = 31;
        int[] xys = {x1, y1, x2, y2, x3, y3};
        drawLine(sheet, row, 0, 0, 144, 77, xys);
        cell.setCellValue(text);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        row.setHeightInPoints(83 * PXTOPT);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);

        int[] xys1 = {112, 83};
        drawLine(sheet, row, 1, 3, 110, 83, xys1);

        wb.write(fos);
    }

    // draw cell line
    private static void drawLine(HSSFSheet sheet, HSSFRow row, int i, int j, int width, int height,
                                 int[] xys) {
        int cellWidth = (int) (PERCENT_WIDTH * PXTOPT * width);
        short cellHeight = (short) (PERCENT_HEIGHT * PXTOPT * height);
        sheet.setColumnWidth(j, cellWidth);
        row.setHeight(cellHeight);

        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short) j, i, (short) (j), i);
        HSSFShapeGroup group = patriarch.createGroup(a);
        float verticalPointsPerPixel = a.getAnchorHeightInPoints(sheet);
        EscherGraphics g = new EscherGraphics(group, sheet.getWorkbook(), Color.black,
                verticalPointsPerPixel);
        EscherGraphics2d g2d = new EscherGraphics2d(g);

        for (int l = 0; l < xys.length; l += 2) {
            int x = (int) ((PERCENT_WIDTH * 0.75 * xys[l] / cellWidth) * 1023);
            int y = (int) ((PERCENT_HEIGHT * 0.75 * xys[l + 1] / cellHeight) * 255);
            g2d.drawLine(0, 0, x, y);
        }
    }

    public static HSSFCellStyle getCellFormat(HSSFWorkbook wb) {
        HSSFCellStyle cellStyle = wb.createCellStyle();

        if (cellStyle.getBorderBottom() != BorderStyle.THIN.getCode()) {
            cellStyle.setBorderBottom(BorderStyle.THIN);
        }
        if (cellStyle.getBorderLeft() != BorderStyle.THIN.getCode()) {
            cellStyle.setBorderLeft(BorderStyle.THIN);
        }
        if (cellStyle.getBorderTop() != BorderStyle.THIN.getCode()) {
            cellStyle.setBorderTop(BorderStyle.THIN);
        }
        if (cellStyle.getBorderRight() != BorderStyle.THIN.getCode()) {
            cellStyle.setBorderRight(BorderStyle.THIN);
        }
        cellStyle.setBottomBorderColor(createPette(wb));
        cellStyle.setLeftBorderColor(createPette(wb));
        cellStyle.setRightBorderColor(createPette(wb));
        cellStyle.setTopBorderColor(createPette(wb));
        return cellStyle;
    }

    public static short createPette(HSSFWorkbook wb) {
        short petteIndex = 0;
        Color rgb = new Color(0x00, 0x00, 0x00);
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(petteIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb
                .getBlue());
        return petteIndex;
    }

    @Test
    public void test2() {
        String format = String.format("%s%s%s", "/", "aaa", "/");
        System.out.print("/aaa/hhh/nnn".replaceAll(format, "/bbbbb/"));
    }

    public static <T> Boolean equalsObjectWithField(T obj1, T obj2, List<String> fieldNames) {
        try {
            Field[] fields = obj1.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (fieldNames.contains(f.getName())) {
                    f.setAccessible(true);
                    Object o1 = f.get(obj1);
                    Object o2 = f.get(obj2);
                    if (!ObjectUtils.equals(o1, o2)) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static <T> Boolean equalsObjectExcludeField(T obj1, T obj2, List<String> fieldNames) {
        try {
            Field[] fields = obj1.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (fieldNames.contains(f.getName())) continue;
                f.setAccessible(true);
                Object o1 = f.get(obj1);
                Object o2 = f.get(obj2);
                if (!ObjectUtils.equals(o1, o2)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Test
    public void Test10() {
        BasicEstate obj1 = new BasicEstate();
        obj1.setProvince("四川1");
        obj1.setBisCase(false);

        BasicEstate obj2 = new BasicEstate();
        obj2.setProvince("四川1");
        obj2.setBisCase(true);

        List<String> fieldNames = Lists.newArrayList("province", "city");
        System.out.print(equalsObjectWithField(obj1, obj2, fieldNames));
    }

    @Test
    public void test11() {
        List<SurveyAssetRightDeclare> rightDeclareList = Lists.newArrayList();
        SurveyAssetRightDeclare rightDeclare=new SurveyAssetRightDeclare();
        rightDeclare.setGroupId(5);
        rightDeclare.setSeat("aaaa");
        rightDeclareList.add(rightDeclare);
        rightDeclare=new SurveyAssetRightDeclare();
        rightDeclare.setGroupId(5);
        rightDeclare.setSeat("aaaabbbbb");
        rightDeclareList.add(rightDeclare);

        rightDeclare=new SurveyAssetRightDeclare();
        rightDeclare.setGroupId(6);
        rightDeclare.setSeat("cccc");
        rightDeclareList.add(rightDeclare);
        Multimap<Integer, SurveyAssetRightDeclare> multimap = FormatUtils.mappingMultiEntity(rightDeclareList, o -> o.getGroupId());
        for (Map.Entry<Integer, Collection<SurveyAssetRightDeclare>> entry : multimap.asMap().entrySet()) {
            System.out.print(entry.getKey());
            System.out.print("/");
            Collection<SurveyAssetRightDeclare> surveyAssetRightDeclares = entry.getValue();
            for (SurveyAssetRightDeclare surveyAssetRightDeclare : surveyAssetRightDeclares) {
                System.out.print("\\");
                System.out.print(surveyAssetRightDeclare.getSeat());
            }
        }
    }
}