package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CreateInsertHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.entity.CsrBorrower;
import com.copower.pmcc.assess.dto.input.project.compile.CompileReportApplyDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeMarketCompareApplyDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by kings on 2018-5-31.
 */
public class PoiTest {

    @org.junit.Test
    public void readExcel() {
        try {
            String path = "C:\\Users\\kings\\Desktop\\测试数据.xls";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gener() {

        System.out.println(extractField("sdf{fff}ff{vvv}"));
    }

    public String extractField(String template) {
        String regex = "\\{(.*?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = newArrayList();
        while (m.find()) {
            Map<String, String> map = Maps.newHashMap();
            String result = m.group();
            map.put(result.replaceAll("^\\{|\\}$", ""), "");
            maps.add(map);
        }
        return JSON.toJSONString(maps);
    }

    public void importData(String path, Integer startRowNumber, Integer csrProjectId) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            //没有在基础配置的字段中不做处理
            if (rowNum == 0) {
                row = sheet.getRow(rowNum);
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                }
                //确定单元格对应的字段
            } else if (startRowNumber > rowNum) {

            }

            System.out.print(row.getCell(15));
        }
        is.close();
    }

    @Test
    public void testReplaceWord() {
        String filePath = "C:\\Users\\kings\\Desktop\\软件设置表格\\新债权评估标准化模板（20180527）.doc";
        Map<String, String> map = Maps.newHashMap();
        map.put("PO_khxm", "张三");
        map.put("PO_ejfh", "巴中");
        map.put("PO_ejfh1", "巴中");

        try {
            AsposeUtils.replaceBookmark(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReplaceWordText() {
        String filePath = "C:\\Users\\kings\\Desktop\\软件设置表格\\新债权评估标准化模板（20180527）.doc";
        Map<String, String> map = Maps.newHashMap();
        map.put("张三", "李四");
        map.put("巴中", "南充");

        try {
            AsposeUtils.replaceText(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsonTest() {
        String str = "[{\"key\": \"PO_ejfh\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_ejfh1\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_khxm\", \"value\": \"何川\", \"explain\": \"1\"}]";

        List<KeyValueDto> ts = JSON.parseArray(str, KeyValueDto.class);


        JSONArray jsonArray = JSON.parseArray("[{\"key\": \"PO_ejfh\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_ejfh1\", \"value\": \"广安\", \"explain\": \"1\"}, {\"key\": \"PO_khxm\", \"value\": \"何川\", \"explain\": \"1\"}]");
        for (Object o : jsonArray) {
            System.out.print(o.getClass());
        }
        System.out.print(ts);
    }

    @Test
    public void insertDocument() {
        try {
            String filePath = "D:\\test\\main.doc";
            Map<String, String> map = Maps.newHashMap();
            map.put("yoyo", "D:\\test\\1.docx");
            map.put("yoyo1", "D:\\test\\2.docx");
            AsposeUtils.insertDocument(filePath, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertSqlTest() {
        CsrBorrower csrBorrower = new CsrBorrower();
        csrBorrower.setProjectId(0);
        csrBorrower.setBisImport(true);
        csrBorrower.setBorrowerId(UUID.randomUUID().toString());
        String s = new CreateInsertHelp().createInsert(csrBorrower);
        System.out.print(s);

        System.out.println(FormatUtils.camelToUnderline("borrowerId"));
    }

    @Test
    public void exportTest() {
        try {
//            String str = "C:\\Users\\13426\\Documents\\test\\A1.xlsx";
//            String str = "C:\\Users\\13426\\Documents\\test\\新客户数据.xlsx";
            String str = "D:\\copower\\doc\\评估相关\\债权\\新客户数据-全.xlsx";
            //str="D:\\IdeaProjects\\pmcc-basis\\basis-app\\target\\pmcc-basis\\pmcc-basis\\Temp\\20180620\\admin\\da3a7deda40243d69ed3c63e3afc18e620180620085617.xlsx";
//            InputStream is = new FileInputStream(str);
//            Workbook hssfWorkbook = PoiUtils.isExcel2003(str) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
//            Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
            getReportExcelOneKey(str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private Map<String, Integer> getReportExcelOneKey(String filePath) throws Exception {
        //列数以2003版为基准 最大是16384
        final Integer MAX_COLUMN = 16384;
        Map<String, Integer> integerMap = new HashMap<>();
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        Workbook workbook = null;
        String suffix = filePath.substring(filePath.length() - 4, filePath.length());
        try {
            if (Objects.equal("xlsx", suffix)) {//07版
                //XSSFWorkbook workbook = new XSSFWorkbook(inputStream) ;
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            } else {
                //HSSFWorkbook workbook = new HSSFWorkbook(inputStream) ;
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {

                throw e;
            } catch (Exception e1) {

            }
        }

        inputStream.close();
        return integerMap;
    }

    @Test
    public void test1() {
        Ordering<Integer> orderingBig = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return left - right;
            }
        };
        List<Integer> list = newArrayList(3, 5, 2, 1, 6);
        list = orderingBig.sortedCopy(list);
        System.out.println(list);
    }

    @Test
    public void test2() {
        String formData = "{\"supportInfoList\":[{\"id\":54,\"content\":\"各种影响房地产价格的因素对房地产的价格的asdad也是不尽相同的：有的因素对房地产价格的影响较大（即随着这种因素的变化而引起的房地产价格的升降幅度较大），有的因素则影响较小。但是，随着时期的不同，地区的不同，或者房地产的类型不同，那些影响较大的因素也许会变成影响较小的因素，甚至没有影响；相反，那些影响较小的因素有可能成为主要的影响因素。\\n各种影响房地产价格的因素与房地产价格之间的影响关系是不尽相同的：有的因素对房地产价格的影响是一向性的，即随着这种因素的变化而提高（或降低）房地产的价格；有的因素在某一状况下随着这种因素的变化而提高（或降低）房地产的价格，但在另一状况下却随着这种因素的的变化而降低（或提高）房地产的asdads；有的因素从某一角度看会提高房地产的价格，但从另一角度看却会降低房地产的价格，其对房地产价格的最终影响如何，是由这两方面的合理决定的。\",\"jsonContent\":[{\"key\":\"影响程度\",\"value\":\"asdad\"},{\"key\":\"价格\",\"value\":\"asdads\"}]},{\"id\":55,\"content\":\"物业抵押是指抵押人以其合法的房地产，以不转移占有的方式向抵押权人提供债务履行担保的行为。物业抵押asdasd成立后，asdasd到期不能清偿债务时，asdasd有权依法以抵押的房地产折价、拍卖或者变卖所得的价款优先受偿。在这一概念中，包括以下几层含义：\\n1)抵押人必须是对房地产享有所有权或者土地使用权的人，即抵押人对抵押物必须享有处分权。\\n2)抵押人以其合法的房地产以不转移占有的方式，依然实际控制着房地产，向抵押权人提供担保。\\n3)向抵押权人提供asdasd履行担保。抵押人拿自己合法的房地产向抵押权人设定抵押，目的是向债权人提供履行债务的一种保证。从某种角度上讲，如果没有这种保证，抵押权人对于债务人的信任度可能降低，很可能作为主债务的合同就无法签订。\",\"jsonContent\":[{\"key\":\"法律关系\",\"value\":\"asdasd\"},{\"key\":\"债务人\",\"value\":\"asdasd\"},{\"key\":\"抵押权人\",\"value\":\"asdasd\"},{\"key\":\"债务\",\"value\":\"asdasd\"}]}]}";
        SchemeMarketCompareApplyDto compileReportApplyDto = JSON.parseObject(formData, SchemeMarketCompareApplyDto.class);
        System.out.println(compileReportApplyDto.getSupportInfoList());
    }

    @Test
    public void test3() {
        String formData = "{\"compileReportDetailList\":[{\"id\":54,\"content\":\"各种影响房地产价格的因素对房地产的价格的asdad也是不尽相同的：有的因素对房地产价格的影响较大（即随着这种因素的变化而引起的房地产价格的升降幅度较大），有的因素则影响较小。但是，随着时期的不同，地区的不同，或者房地产的类型不同，那些影响较大的因素也许会变成影响较小的因素，甚至没有影响；相反，那些影响较小的因素有可能成为主要的影响因素。\\n各种影响房地产价格的因素与房地产价格之间的影响关系是不尽相同的：有的因素对房地产价格的影响是一向性的，即随着这种因素的变化而提高（或降低）房地产的价格；有的因素在某一状况下随着这种因素的变化而提高（或降低）房地产的价格，但在另一状况下却随着这种因素的的变化而降低（或提高）房地产的asdads；有的因素从某一角度看会提高房地产的价格，但从另一角度看却会降低房地产的价格，其对房地产价格的最终影响如何，是由这两方面的合理决定的。\",\"jsonContent\":[{\"key\":\"影响程度\",\"value\":\"asdad\"},{\"key\":\"价格\",\"value\":\"asdads\"}]},{\"id\":55,\"content\":\"物业抵押是指抵押人以其合法的房地产，以不转移占有的方式向抵押权人提供债务履行担保的行为。物业抵押asdasd成立后，asdasd到期不能清偿债务时，asdasd有权依法以抵押的房地产折价、拍卖或者变卖所得的价款优先受偿。在这一概念中，包括以下几层含义：\\n1)抵押人必须是对房地产享有所有权或者土地使用权的人，即抵押人对抵押物必须享有处分权。\\n2)抵押人以其合法的房地产以不转移占有的方式，依然实际控制着房地产，向抵押权人提供担保。\\n3)向抵押权人提供asdasd履行担保。抵押人拿自己合法的房地产向抵押权人设定抵押，目的是向债权人提供履行债务的一种保证。从某种角度上讲，如果没有这种保证，抵押权人对于债务人的信任度可能降低，很可能作为主债务的合同就无法签订。\",\"jsonContent\":[{\"key\":\"法律关系\",\"value\":\"asdasd\"},{\"key\":\"债务人\",\"value\":\"asdasd\"},{\"key\":\"抵押权人\",\"value\":\"asdasd\"},{\"key\":\"债务\",\"value\":\"asdasd\"}]}]}";
        CompileReportApplyDto compileReportApplyDto = JSON.parseObject(formData, CompileReportApplyDto.class);
        System.out.println(compileReportApplyDto.getCompileReportDetailList());
    }

    @Test
    public void replaceTextToFile() {
        try {
            String filePath = "C:\\Users\\kings\\Desktop\\新建 Microsoft Word 文档.docx";
            Document doc = new Document(filePath);
            doc.getRange().replace(Pattern.compile("任务安排"), new IReplacingCallback() {
                @Override
                public int replacing(ReplacingArgs e) throws Exception {
                    DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                    builder.moveTo(e.getMatchNode());
                    Document document = new Document("C:\\Users\\kings\\Desktop\\评估项目bug记录.docx");
                    builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
                    return ReplaceAction.REPLACE;
                }
            }, false);

            doc.save("C:\\Users\\kings\\Desktop\\1.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <F,T> List<T>  cacheList(String key, Class<T> t, Function<String, List<T>> function){
        List<?> apply = function.apply(key);
        List<T> ts = (List<T>) apply;
        return  ts;
    }

    @Test
    public void testFx(){
        fxInfo fxInfo=new fxInfo("");
        System.out.print(fxInfo.getData());
        //fxTest(Lists.newArrayList("张三","李四123") ,o->o.length());
        HashSet<String> ht=new HashSet<>();

    }

    private <F, T> void fxTest(List<F> list,Function<F, T> function) {
        for (F f : list) {
            T t = function.apply(f);
            System.out.print(t);
        }

    }

    public class fxInfo<T extends String>{
        private T data;

        public fxInfo(T t){
            this.data=t;
        }

        public T getData(){
            return  data;
        }

    }


}


