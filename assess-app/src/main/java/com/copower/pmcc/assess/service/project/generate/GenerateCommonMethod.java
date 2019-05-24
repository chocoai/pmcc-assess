package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/3/7 16:32
 * @description:提取一些方法(报告生成)
 */
@Component
public class GenerateCommonMethod {

    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private PublicService publicService;

    public final String SchemeJudgeObjectName = "委估对象";
    public final String errorStr = "无";


    //房地产总价
    public BigDecimal getTotalRealEstate(Integer areId) {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areId);
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        temp = temp.add(bigDecimal);
                    }
                }
            }
        }
        return temp;
    }


    /**
     * 实际用途
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPracticalUse(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getPracticalUse())).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            String value = null;
            if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPracticalUse())) {
                value = baseDataDicService.getNameById(schemeJudgeObjectList.get(i).getPracticalUse());
            } else {
                value = schemeJudgeObjectList.get(i).getPracticalUse();
            }
            if (StringUtils.isNotBlank(value)) {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), value);
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        return s;
    }


    /**
     * 获取区域下楼盘的分组
     *
     * @param areaId
     * @return
     */
    public LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> getEstateGroupByAreaId(Integer areaId) throws Exception {
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> listLinkedHashMap = Maps.newLinkedHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordListByIds(LangUtils.transform(schemeJudgeObjectList, o -> o.getDeclareRecordId()));
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            Map<String, List<Integer>> map = Maps.newHashMap();
            for (DeclareRecord declareRecord : declareRecords) {
                if (map.containsKey(declareRecord.getStreetNumber())) {
                    List<Integer> list = map.get(declareRecord.getStreetNumber());
                    list.add(declareRecord.getId());
                    map.put(declareRecord.getStreetNumber(), list);
                } else {
                    map.put(declareRecord.getStreetNumber(), Lists.newArrayList(declareRecord.getId()));
                }
            }
            for (Map.Entry<String, List<Integer>> stringListEntry : map.entrySet()) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(stringListEntry.getValue().get(0));
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstate basicEstate = generateBaseExamineService.getEstate();
                listLinkedHashMap.put(basicEstate, schemeJudgeObjectService.getListByDeclareIds(stringListEntry.getValue()));
            }
        }
        return listLinkedHashMap;
    }

    /**
     * 基本排序
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public List<SchemeJudgeObject> getSortSchemeJudgeObject(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Ordering<SchemeJudgeObject> ordering = Ordering.from(new Comparator<SchemeJudgeObject>() {
            @Override
            public int compare(SchemeJudgeObject o1, SchemeJudgeObject o2) {
                Integer a = parseIntJudgeNumber(o1.getNumber());
                Integer b = parseIntJudgeNumber(o2.getNumber());
                return a.compareTo(b);
            }
        });
        schemeJudgeObjectList.sort(ordering);
        return schemeJudgeObjectList;
    }

    /**
     * 填充
     *
     * @param stringListMap
     * @param schemeJudgeObject
     * @param key
     */
    public void putStringListMap(Map<String, List<Integer>> stringListMap, SchemeJudgeObject schemeJudgeObject, String key) {
        if (StringUtils.isNotBlank(key)) {
            if (NumberUtils.isNumber(schemeJudgeObject.getNumber())) {
                List<Integer> integerList = stringListMap.get(key);
                if (CollectionUtils.isNotEmpty(integerList)) {
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getId().toString()));
                } else {
                    integerList = Lists.newArrayList();
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getId().toString()));
                }
                stringListMap.put(key, integerList);
            }
        }
    }

    public String getSchemeJudgeObjectListShowName(Map<String, List<Integer>> stringListMap, String suffix) {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(8);
        Set<String> stringSetTemp = Sets.newHashSet();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringSet.add(this.convertNumber(entry.getValue()));
                    stringSetTemp.add(entry.getKey());
                }
            }
            List<String> stringList = Lists.newArrayList(stringSet);
            //排一次序
            Ordering<String> ordering = Ordering.from((o1, o2) -> {
                int x = Integer.parseInt(o1.substring(0, 1));
                int y = Integer.parseInt(o2.substring(0, 1));
                return (x > y) ? -1 : ((x == y) ? 0 : 1);
            });
            stringList.sort(ordering);
            stringSet = Sets.newHashSet(stringList);
            if (stringList.size() > 1) {
                stringBuilder.append(this.toSetStringMerge(stringSet, ","));
                stringBuilder.append(this.SchemeJudgeObjectName);
            }
            stringBuilder.append(this.toSetStringSplitCommaSuffix(stringSetTemp, ",", suffix));
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (schemeJudgeObject == null) {
            return "";
        }
        if (StringUtils.isNotBlank(schemeJudgeObject.getNumber())) {
            Integer num = this.parseIntJudgeNumber(schemeJudgeObject.getNumber());
            String val = this.convertNumber(Lists.newArrayList(num));
            stringBuilder.append(val);
            stringBuilder.append("号");
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            if (StringUtils.isNotBlank(schemeJudgeObject.getName())) {
                stringBuilder.append(schemeJudgeObject.getName());
            }
        }
        return stringBuilder.toString();
    }

    public String getPercentileSystem(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.format("%s%s", bigDecimal.toString(), "%");
    }

    /**
     * 按照报告类型
     *
     * @param bigDecimal
     * @return
     */
    public String getBigDecimalRound(BigDecimal bigDecimal, boolean tenThousand) {
        //四舍五入,并且取到0
        bigDecimal = bigDecimal.setScale(0, BigDecimal.ROUND_UP);
        if (tenThousand) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10000));
        }
        if (bigDecimal.doubleValue() < 0) {
            //取绝对值
            bigDecimal = new BigDecimal(Math.abs(bigDecimal.doubleValue()));
            String s = bigDecimal.toString();
            s = s.substring(1, bigDecimal.toString().length());
//            bigDecimal = new BigDecimal(s);
        }
        return bigDecimal.toString();
    }

    /**
     * 获取合并的估价对象
     *
     * @param schemeJudgeObjectsA
     * @return
     */
    public List<SchemeJudgeObject> getByRootAndChildSchemeJudgeObjectList(List<SchemeJudgeObject> schemeJudgeObjectsA, boolean declareRecordFilter) {
        Set<SchemeJudgeObject> schemeJudgeObjectHashSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectsA)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectsA) {
                if (schemeJudgeObject.getBisMerge()) {
                    List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
                        schemeJudgeObjectHashSet.addAll(schemeJudgeObjects);
                    }
                }
                schemeJudgeObjectHashSet.add(schemeJudgeObject);
            }
        }
        List<SchemeJudgeObject> objectList = Lists.newArrayList(schemeJudgeObjectHashSet);
        if (declareRecordFilter) {
            objectList = objectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getDeclareRecordId() != null).collect(Collectors.toList());
        }
        if (CollectionUtils.isNotEmpty(objectList)) {
            objectList = getSortSchemeJudgeObject(objectList);
            objectList = this.removeDuplicate(objectList);
        }
        return objectList;
    }


    /**
     * 类似于这样的1=2-7-20-32-1-fhd-6364转换为 List形式并且里面的数字如20和32等会是一个元素而像'='或者'-'或者'f'等这样的非数字则单独是一个元素
     *
     * @param text
     * @return
     */
    public List<String> convertNumberHelp(String text) {
        List<String> stringList = Lists.newArrayList();
        if (StringUtils.isEmpty(text.trim())) {
            return stringList;
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append(text);
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        while (m.find()) {
            String string = stringBuilder.toString();
            int index = string.indexOf(m.group());
            String number = string.substring(0, index);
            if (NumberUtils.isNumber(number)) {
                stringList.add(number);
            }
            stringList.add(m.group());
            string = string.substring(index + 1, stringBuilder.toString().length());
            stringBuilder.delete(0, stringBuilder.toString().length());
            stringBuilder.append(string);
        }
        if (NumberUtils.isNumber(stringBuilder.toString())) {
            stringList.add(stringBuilder.toString());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            //去重复 (不改变顺序)
            stringList = stringList.stream().distinct().collect(Collectors.toList());
        }
        return stringList;
    }

    /**
     * 数字转换
     *
     * @param numbers
     * @return
     */
    public String convertNumber(List<Integer> numbers) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(numbers)) {
            numbers = numbers.stream().distinct().sorted().collect(Collectors.toList());
            Integer[] ints = new Integer[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                ints[i] = numbers.get(i);
            }
            String text = null;
            if (ints.length > 1) {
                text = this.convert(ints, 0);
                LinkedList<String> stringList = Lists.newLinkedList();
                for (char c : text.toCharArray()) {
                    stringList.add(String.valueOf(c));
                }
                for (int i = 0; i < stringList.size(); i++) {
                    if (i == stringList.size() - 1) {
                        if (NumberUtils.isNumber(stringList.get(i))) {
                            stringBuilder.append(stringList.get(i));
                        }
                    } else {
                        stringBuilder.append(stringList.get(i));
                    }
                }
                text = stringBuilder.toString();
                stringBuilder.delete(0, stringBuilder.toString().length());
            } else {
                text = ints[0].toString();
            }
            List<String> stringList = convertNumberHelp(text);
            if (CollectionUtils.isNotEmpty(stringList)) {
                stringList.stream().forEach(s -> {
                    if (NumberUtils.isNumber(s)) {
                        stringBuilder.append(this.parseToCircleNumber(Integer.parseInt(s)));
                    } else {
                        stringBuilder.append(s);
                    }
                });
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                text = stringBuilder.toString();
            }
            return text;
        }
        return "";
    }

    /**
     * 获取连续的数字组合
     *
     * @param ints
     * @param index
     * @return
     */
    public String convert(Integer[] ints, int index) {
        int end = index;
        //结束条件，遍历完数组
        if (ints.length == index) {
            return "";
        } else {
            for (int i = index; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    if (ints[i] + 1 == ints[i + 1]) {
                        end = i;
                    } else {
                        if (i > index)
                            end = end + 1;
                        break;
                    }
                } else {
                    if (end == ints.length - 2) {
                        end = ints.length - 1;
                        break;
                    }
                }
            }
            //相等说明不连续
            if (index == end)
                return ints[index] + "," + convert(ints, end + 1);
                //连续
            else
                return ints[index] + "-" + ints[end] + "," + convert(ints, end + 1);

        }
    }

    public String toSetStringSplitSpace(Set<String> stringSet) {
        return toSetStringMerge(stringSet, " ");
    }

    public String toSetStringSplitComma(Set<String> stringSet) {
        return toSetStringMerge(stringSet, ",");
    }


    public String toSetStringMerge(Set<String> stringSet, String split) {
        return this.toSetStringSplitCommaSuffix(stringSet, split, null);
    }

    public String toSetStringSplitCommaSuffix(Set<String> stringSet, String split, String suffix) {
        StringBuilder builder = new StringBuilder(16);
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (StringUtils.isNotBlank(suffix)) {
                        builder.append(suffix);
                    }
                    if (i != stringList.size() - 1 && stringList.size() != 1) {
                        if (StringUtils.isNotBlank(split)) {
                            builder.append(split);
                        }
                    }
                }
            }
        } else {
            return " ";
        }
        return builder.toString();
    }

    public String getLocalPath() {
        return getLocalPath(null);
    }

    public String getLocalPath(String title) {
        if (StringUtils.isEmpty(title)) {
            title = String.format("%s%s", "报告模板", DateUtils.format(new Date(), DateUtils.DATE_CHINESE_PATTERN));
        }
        return String.format("%s\\%s%s%s", baseAttachmentService.createTempDirPath(), title, UUID.randomUUID().toString(), ".doc");
    }

    /**
     * 填充值
     *
     * @param text
     * @param bookmark
     * @param textMap
     * @param bookmarkMap
     * @param key
     * @param value
     */
    public void putValue(boolean text, boolean bookmark, boolean fileFlag, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String key, String value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (text) {
            textMap.put(String.format("${%s}", key), value);
        }
        if (bookmark) {
            bookmarkMap.put(key, value);
        }
        if (fileFlag) {
            fileMap.put(String.format("${%s}", key), value);
        }
    }

    public List<String> specialTreatment(List<String> strings) {
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(strings)) {
            for (String s : strings) {
                String temp = s.substring(2, s.length() - 1);
                stringList.add(temp);
            }
        }
        return stringList;
    }

    /**
     * list去重复元素
     *
     * @param list
     * @return
     */
    public List removeDuplicate(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 为图片设置间隔
     *
     * @param builder
     * @param imgPath
     * @throws Exception
     */
    public void builderInsertImage(DocumentBuilder builder, String imgPath) throws Exception {
        if (StringUtils.isNotBlank(imgPath) && FileUtils.checkImgSuffix(imgPath)) {
            File file = new File(imgPath);
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
            int targetWidth = sourceImg.getWidth() > 400 ? 400 : sourceImg.getWidth();
            builder.insertImage(imgPath, targetWidth, getImageTargeHeight(sourceImg.getWidth(), targetWidth, sourceImg.getHeight()));
        }
    }

    public int getImageTargeHeight(int sourceWidth, int targeWidth, int sourceHeight) {
        int targetHeight = sourceHeight / (sourceWidth / targeWidth);
        return targetHeight;
    }

    /**
     * 设置表格属性
     *
     * @param builder
     * @throws Exception
     */
    public void settingBuildingTable(DocumentBuilder builder) throws Exception {
        builder.getFont().setSize(9);
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        //设置表格边框的宽度
        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
        //设置具体宽度
        builder.getCellFormat().setWidth(100);
        Style style = builder.getParagraphFormat().getStyle();
        style.setName(AsposeUtils.ImitationSongGB2312FontName);
//        builder.getParagraphFormat().setStyle(style);
        //水平居中
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

    }


    public void setDefaultDocumentBuilderSetting(DocumentBuilder builder) throws Exception {
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        builder.getFont().setSize(14);
//        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
    }

    /**
     * 合并表格
     *
     * @param mergeCellModelList
     * @param table
     */
    public void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                Cell cellStartRange = null;
                Cell cellEndRange = null;
                if (mergeCellModel.getCellEndRange() == null && mergeCellModel.getCellStartRange() == null) {
                    cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                    cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                } else {
                    cellStartRange = mergeCellModel.getCellStartRange();
                    cellEndRange = mergeCellModel.getCellEndRange();
                }
                if (cellStartRange != null && cellEndRange != null) {
                    if (table != null) {
                        AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                    }
                }
            }
        }
    }

    /**
     * 获取包装后的html，与当前word字体格式一致
     *
     * @param html
     * @return
     */
    public String getWarpCssHtml(String html) {
        return String.format("<div style='font-family:仿宋_GB2312;line-height:150%%;font-size:14.0pt'>%s</div>", html);
    }

    /**
     * 获取缩进后html
     *
     * @param html
     * @return
     */
    public String getIndentHtml(String html) {
        return String.format("<div style='text-indent:2em'>%s</div>", html);
    }

    /**
     * 估价对象合并描述
     * map 1:商业  2:商业  3:商业  4:住宅  explain:证载用途为
     * 1-3、4证载用途为商业、住宅
     *
     * @param map
     * @return
     */
    public String judgeSummaryDesc(Map<Integer, String> map, String explain, Boolean isShowNumber) {
        if (map == null || map.size() <= 0) return "";
        //map按key值排序
        Map<Integer, String> sortMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(map);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);
        StringBuilder judgeBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            judgeBuilder.append(convertNumber(stringListEntry.getValue())).append("、");
            contentBuilder.append(stringListEntry.getKey()).append("、");
        }
        String judgeString = StringUtils.strip(judgeBuilder.toString(), "、");
        String contentStrig = StringUtils.strip(contentBuilder.toString().replaceAll("^<[^>]+>|<[^>]+>$", ""), "、");
        if (listMap.size() <= 1 && isShowNumber == Boolean.FALSE) {
            return explain + contentStrig;
        }
        return String.format("%s号%s%s", judgeString, StringUtils.defaultString(explain), contentStrig);
    }

    private Map<String, List<Integer>> getStringListMap(Map<Integer, String> map) {
        Map<String, List<Integer>> listMap = Maps.newLinkedHashMap();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (listMap.containsKey(entry.getValue())) {
                List<Integer> list = listMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                listMap.put(entry.getValue(), Lists.newArrayList(entry.getKey()));
            }
        }
        return listMap;
    }

    /**
     * 估价对象分别描述
     * map 1:商业  2:商业  3:商业  4:住宅  explain:证载用途为 symbol:,
     * 1号估价对象证载用途为商业,2号估价对象证载用途为商业,3号估价对象证载用途为商业,4号估价对象证载用途为住宅
     *
     * @param map
     * @return
     */
    public String judgeEachDesc(Map<Integer, String> map, String explain, String symbol, Boolean isShowJudgeNumner) {
        if (map == null || map.size() <= 0) return "";
        //map按key值排序
        Map<Integer, String> sortMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(map);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            if (StringUtils.isBlank(stringListEntry.getKey())) continue;
            String content = stringListEntry.getKey().replaceAll("^<[^>]+>|<[^>]+>$", "");
            if (listMap.size() <= 1 && isShowJudgeNumner == Boolean.FALSE) {
                return explain + content;
            }
            builder.append(String.format("%s号", convertNumber(stringListEntry.getValue()))).append(StringUtils.defaultString(explain)).append(content).append(symbol);
        }
        return builder.toString();
    }

    /**
     * 估价对象分别描述
     *
     * @param map               合并拆分对象map
     * @param explain
     * @param symbol
     * @param isShowJudgeNumner
     * @return
     */
    public String judgeEachDescExtend(Map<String, String> map, String explain, String symbol, Boolean isShowJudgeNumner) {
        //先处理map key,先处理连续未拆分的估价对象，再将拆分的估价对象搁置一边，最后将拆分的对象插入到描述一致的分组中
        if (map == null || map.size() <= 0) return "";
        Map<Integer, String> standardMap = Maps.newHashMap();//标准值map
        Map<String, String> splitMap = Maps.newHashMap();//拆分对象map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            for (String s : StringUtils.split(key, ",")) {
                if (s.contains("-")) {
                    splitMap.put(s, entry.getValue());
                } else {
                    standardMap.put(Integer.valueOf(s), entry.getValue());
                }
            }
        }
        Map<Integer, String> sortMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(standardMap);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);

        List<String> removeKeys = Lists.newArrayList();
        Map<String, String> resultMap = Maps.newHashMap();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            StringBuilder numberBuilder = new StringBuilder(convertNumber(stringListEntry.getValue()));
            for (Map.Entry<String, String> splitEntry : splitMap.entrySet()) {
                if (StringUtils.equals(splitEntry.getValue(), stringListEntry.getKey())) {
                    numberBuilder.append(",").append(splitEntry.getKey());
                    removeKeys.add(splitEntry.getKey());
                }
            }
            resultMap.put(StringUtils.strip(numberBuilder.toString(), ","), stringListEntry.getKey());
        }
        removeKeys.forEach(o -> splitMap.remove(o));
        if (!splitMap.isEmpty()) {
            resultMap.putAll(splitMap);
        }
        StringBuilder builder = new StringBuilder();
        if (!resultMap.isEmpty()) {
            for (Map.Entry<String, String> resultEntry : resultMap.entrySet()) {
                String content = resultEntry.getValue().replaceAll("^<[^>]+>|<[^>]+>$", "");
                if (resultMap.size() <= 1 && isShowJudgeNumner == Boolean.FALSE) {
                    return explain + content;
                }
                builder.append(String.format("%s号", resultEntry.getKey())).append(StringUtils.defaultString(explain)).append(content).append(symbol);
            }
        }
        return builder.toString();
    }


    /**
     * 字符串合并描述
     * 日照：好 采光：好 通风：差 隔音：差  explain:效果 symbol：，
     * 日照、采光效果好，通风、隔音效果差
     *
     * @param map
     * @param explain
     * @return
     */
    public String stringSummaryDesc(Map<String, String> map, String explain, String symbol) {
        if (map == null || map.size() <= 0) return "";
        Map<String, List<String>> listMap = Maps.newHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (listMap.containsKey(entry.getValue())) {
                List<String> list = listMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                listMap.put(entry.getValue(), Lists.newArrayList(entry.getKey()));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<String>> stringListEntry : listMap.entrySet()) {
            List<String> strings = stringListEntry.getValue();
            for (int i = 0; i < strings.size(); i++) {
                stringBuilder.append(strings.get(i));
                if (strings.size() - 1 > i)
                    stringBuilder.append("、");
            }
            String content = stringListEntry.getKey().replaceAll("^<[^>]+>|<[^>]+>$", "");
            stringBuilder.append(explain).append(content).append(symbol);
        }
        return stringBuilder.toString();
    }

    /**
     * 处理字符串中错误的标点符号
     *
     * @param str
     * @return
     */
    public String trim(String str) {
        if (StringUtils.isBlank(str)) return str;
        str = StringUtils.strip(str.replaceAll("^<[^>]+>|<[^>]+>$", ""), "。");
        str += "。";
        str = str.replaceAll(",+", ",").replaceAll(";+", ";")
                .replaceAll("，+", "，").replaceAll("、+", "、")
                .replaceAll("。+", "。").replaceAll("；+", "；")
                .replaceAll("，\\s+。", "。").replaceAll("；\\s。", "。")
                .replaceAll("[,|，|、|;|；|.|。]+$", "。");
        return str;
    }


    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    public String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return "0";
        }
        if (NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : "0";
    }

    /**
     * 估价对象编号zhuanintege
     *
     * @param number
     * @return
     */
    public Integer parseIntJudgeNumber(String number) {
        if (StringUtils.isBlank(number)) return null;
        if (number.contains(","))
            number = number.split(",")[0];
        if (number.contains("-"))
            number = number.split("-")[0];
        Integer i = Integer.valueOf(number);
        return i;
    }


    public List<String> changeMapToList(Map<String, String> map, boolean flag, String suffix) {
        List<String> stringList = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder(8);
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                if (flag) {
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append(entry.getValue());
                } else {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(entry.getKey());
                }
                if (StringUtils.isNotEmpty(suffix)) {
                    stringBuilder.append(suffix);
                    stringList.add(stringBuilder.toString());
                } else {
                    stringList.add(stringBuilder.toString());
                }
                stringBuilder.delete(0, stringBuilder.toString().length());
            });
        }
        return stringList;
    }

    /**
     * 数字转换为带圆圈的数字 (由于word目前标准子集中只有20,所以暂时只有20)
     *
     * @param number
     * @return
     */
    public String parseToCircleNumber(final Integer number) {
        final String s = "①,②,③,④,⑤,⑥,⑦,⑧,⑨,⑩,⑪,⑫,⑬,⑭,⑮,⑯,⑰,⑱,⑲,⑳";
        final String[] strs = s.split(",");
        List<String> stringList = Lists.newArrayList();
        Map<Integer, String> map = Maps.newHashMap();
        for (int i = 0; i < strs.length; i++) {
            map.put(i + 1, strs[i]);
            stringList.add(String.valueOf(i + 1));
        }
        if (number == null) {
            return null;
        }
        if (!stringList.contains(number.toString())) {
            return number.toString();
        }
        return map.get(number);
    }

    /**
     * 根据距离分组
     *
     * @param map
     * @return (距离, List < id >)
     */
    public Map<String, List<Integer>> getGroupByDistance(Map<Integer, String> map) {
        Map<String, List<Integer>> listMap = Maps.newHashMap();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                String key = entry.getValue();
                if (NumberUtils.isNumber(entry.getValue())) {
                    key = baseDataDicService.getNameById(entry.getValue());
                }
                key = getNumber(key);
                List<Integer> integerList = listMap.get(key);
                if (CollectionUtils.isEmpty(integerList)) {
                    integerList = Lists.newArrayList();
                }
                integerList.add(entry.getKey());
                if (StringUtils.isNumeric(key)) {
                    listMap.put(key, integerList);
                }
            });
        }
        return listMap;
    }

    /**
     * 根据估价对象获取 BasicApply
     *
     * @param schemeJudgeObject
     * @return
     */
    public BasicApply getBasicApplyBySchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject.getDeclareRecordId() == null) {
            return null;
        }
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        if (declareRecord == null) {
            return null;
        }
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        if (basicApply == null) {
            return null;
        }
        return basicApply;
    }

    /**
     * 计算数值差异
     *
     * @param var1
     * @param var2
     * @return 返回10则有10%的差异
     */
    public int computeDifference(BigDecimal var1, BigDecimal var2) {
        return publicService.computeDifference(var1, var2);
    }

    public void writeWordTitle(DocumentBuilder builder, LinkedList<String> titles) throws Exception {
        if (CollectionUtils.isNotEmpty(titles)) {
            for (String title : titles) {
                builder.insertCell();
                builder.write(title);
            }
            builder.endRow();
        }
    }

    public void writeWordTitle(DocumentBuilder builder, LinkedList<Double> doubleLinkedList, LinkedList<String> linkedLists) throws Exception {
        if (CollectionUtils.isNotEmpty(linkedLists) && CollectionUtils.isNotEmpty(doubleLinkedList)) {
            if (linkedLists.size() != doubleLinkedList.size()) {
                return;
            }
            for (int i = 0; i < linkedLists.size(); i++) {
                Cell cell = builder.insertCell();
                cell.getCellFormat().setWidth(doubleLinkedList.get(i).doubleValue());
                builder.write(linkedLists.get(i));
            }
            builder.endRow();
        }
    }

}
