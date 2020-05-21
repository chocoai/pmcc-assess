package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailAchievementDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.data.DataAchievementDto;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailAchievementVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/5 10:18
 * @description:基准地价 土地因素
 */
@Service
public class DataLandLevelDetailAchievementService {
    @Autowired
    private DataLandLevelDetailAchievementDao dataLandLevelDetailAchievementDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    private final String FACTOR = "因素";//修正说明 自定义的和页面一致 ,以后优化需要专门放在一个地方 FACTOR
    private final String COEFFICIENT = "系数"; //修正系数 自定义的和页面一致
    private final String Alias = "个别因素";//这个是数据字典里面的请注意

    /**
     * 往下查找最后的层级id
     *
     * @param landLevelId
     * @param stringList
     * @return
     */
    private Integer settingRecursiveHandle(Integer landLevelId, List<String> stringList) {
        DataLandLevelDetail query = null;
        Integer pid = 0;
        for (int i = 0; i < stringList.size(); i++) {
            String name = stringList.get(i);
            query = new DataLandLevelDetail();
            if (i == 0) {
                query.setLandLevelId(landLevelId);
            }
            query.setName(name);
            query.setPid(pid);
            List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailList(query);
            if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
                pid = dataLandLevelDetailList.get(0).getId();
            }
        }
        return pid;
    }

    /**
     * 获取树的节点 id
     *
     * @param achievementDto
     * @param landLevelId
     * @return
     */
    private Integer getFilterTreeId(DataAchievementDto achievementDto, Integer landLevelId) {
        String text = StringUtils.isNotBlank(achievementDto.getaName()) ? achievementDto.getaName() : achievementDto.getBName();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        List<String> stringList = FormatUtils.transformString2List(text, "-");
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (Objects.equal(name, FACTOR) || Objects.equal(name, COEFFICIENT)) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }
        Integer id = settingRecursiveHandle(landLevelId, stringList);
        if (id != 0) {
            return id;
        }
        return null;
    }

    /**
     * 土地级别详情导入的树结构处理
     *
     * @param multipartFile
     * @param landLevelId
     * @return
     * @throws Exception
     */
    public String importDataLandLevelDetailAchievementNew(MultipartFile multipartFile, Integer landLevelId) throws Exception {
        StringBuilder builder = new StringBuilder(10);
        //自定义的收集导入数量处理
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final AtomicInteger relatedAtomicInteger = new AtomicInteger(0);
        final List<BaseDataDic> grades = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.programmeMarketCostapproachGrade);
        final List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.programmeMarketCostapproachFactor);
        final List<BaseDataDic> numbers = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LAND_LEVEL_ROMAN);
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        List<Sheet> sheetList = PoiUtils.getSheet(filePath);
        List<DataAchievementDto> dataAchievementDtoList = getFilterList(sheetList, numbers);
        if (CollectionUtils.isNotEmpty(dataAchievementDtoList)) {
            Iterator<DataAchievementDto> iterator = dataAchievementDtoList.iterator();
            while (iterator.hasNext()) {
                DataAchievementDto achievementDto = iterator.next();
                Integer levelDetailId = getFilterTreeId(achievementDto, landLevelId);
                if (levelDetailId == null) {
                    continue;
                }
                DataLandLevelDetailAchievement dataLandLevelDetailAchievement = new DataLandLevelDetailAchievement();
                dataLandLevelDetailAchievement.setLevelDetailId(levelDetailId);
                List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementList = getDataLandLevelDetailAchievementList(dataLandLevelDetailAchievement);
                handleSheet(achievementDto, dataLandLevelDetailAchievement, grades, types, dataLandLevelDetailAchievementList, atomicInteger, relatedAtomicInteger);
            }
        }
        if (atomicInteger.get() != 0) {
            builder.append("\r\n").append("共导入").append(atomicInteger.get()).append("条数据");
        }
        if (relatedAtomicInteger.get() != 0) {
            builder.append("\r\n").append("共关联").append(relatedAtomicInteger.get()).append("条数据");
        }
        return builder.toString();
    }

    /**
     * 土地因素 具体导入设置值
     *
     * @param achievementDto
     * @param input
     * @param grades
     * @param types
     * @param atomicInteger
     */
    private void handleSheet(DataAchievementDto achievementDto, DataLandLevelDetailAchievement input, List<BaseDataDic> grades, List<BaseDataDic> types, List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementList, AtomicInteger atomicInteger, AtomicInteger relatedAtomicInteger) {
        Sheet sheetA = achievementDto.getA();
        Sheet sheetB = achievementDto.getB();
        final int cellLength = 8;//固定列数
        BiFunction<DataLandLevelDetailAchievement, List<DataLandLevelDetailAchievement>, DataLandLevelDetailAchievement> biFunction = (((dataLandLevelDetailAchievement, dataLandLevelDetailAchievements) -> {
            Iterator<DataLandLevelDetailAchievement> iterator = dataLandLevelDetailAchievements.iterator();
            while (iterator.hasNext()) {
                DataLandLevelDetailAchievement achievement = iterator.next();
                int num = 0;
                if (com.google.common.base.Objects.equal(dataLandLevelDetailAchievement.getType(), achievement.getType())) {
                    num++;
                }
                if (com.google.common.base.Objects.equal(dataLandLevelDetailAchievement.getGrade(), achievement.getGrade())) {
                    num++;
                }
                if (com.google.common.base.Objects.equal(dataLandLevelDetailAchievement.getLevelDetailId(), achievement.getLevelDetailId())) {
                    num++;
                }
                if (num == 3) {
                    return achievement;
                }
            }
            return null;
        }));
        for (int k = achievementDto.getStartRow(); k < achievementDto.getEndRow(); k++) {
            Row aRow = null, bRow = null;
            if (sheetA != null) {
                aRow = sheetA.getRow(k);
            }
            if (sheetB != null) {
                bRow = sheetB.getRow(k);
            }
            for (int i = 0; i < cellLength; i++) {
                DataLandLevelDetailAchievement landAchievement = new DataLandLevelDetailAchievement();
                BeanUtils.copyProperties(input, landAchievement);
                switch (i) {
                    case 3: {
                        fiveHandle(i, "优", aRow, bRow, landAchievement, grades);
                        break;
                    }
                    case 4: {
                        fiveHandle(i, "较优", aRow, bRow, landAchievement, grades);
                        break;
                    }
                    case 5: {
                        fiveHandle(i, "一般", aRow, bRow, landAchievement, grades);
                        break;
                    }
                    case 6: {
                        fiveHandle(i, "较劣", aRow, bRow, landAchievement, grades);
                        break;
                    }
                    case 7: {
                        fiveHandle(i, "劣", aRow, bRow, landAchievement, grades);
                        break;
                    }
                    default:
                        break;
                }
                if (k >= achievementDto.getOneRow()) {
                    landAchievement.setType(types.stream().filter(baseDataDic -> Alias.equals(baseDataDic.getName())).findFirst().get().getId());
                } else {
                    landAchievement.setType(types.stream().filter(baseDataDic -> !Alias.equals(baseDataDic.getName())).findFirst().get().getId());
                }
                if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievementList)) {
                    DataLandLevelDetailAchievement target = biFunction.apply(landAchievement, dataLandLevelDetailAchievementList);
                    if (target != null) {
                        landAchievement.setId(target.getId());
                    }
                }
                if (landAchievement.getGrade() == null) {
                    continue;
                }
                if (landAchievement.getType() == null) {
                    continue;
                }
                if (landAchievement.getId() != null && landAchievement.getId() != 0) {
                    relatedAtomicInteger.incrementAndGet();
                } else {
                    atomicInteger.incrementAndGet();
                }
                if (StringUtils.isBlank(landAchievement.getReamark())){
                    if (landAchievement !=null){

                    }
                }
                saveDataLandLevelDetailAchievement(landAchievement);
            }
        }
    }

    private void fiveHandle(final int i, final String name, Row aRow, Row bRow, DataLandLevelDetailAchievement landAchievement, List<BaseDataDic> grades) {
        if (aRow != null) {
            if (aRow.getCell(i) != null) {
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(PoiUtils.getCellValue(aRow.getCell(i)))) {
                    landAchievement.setReamark(PoiUtils.getCellValue(aRow.getCell(i)));
                }
            }
            if (aRow.getCell(1) != null) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(aRow.getCell(1)))) {
                    landAchievement.setClassification(PoiUtils.getCellValue(aRow.getCell(1)));
                }
            }
            if (aRow.getCell(2) != null) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(aRow.getCell(2)))) {
                    landAchievement.setCategory(PoiUtils.getCellValue(aRow.getCell(2)));
                }
            }
        }
        if (bRow != null) {
            if (bRow.getCell(i) != null) {
                String achievement = PoiUtils.getCellValue(bRow.getCell(i));
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(achievement)) {
                    if (NumberUtils.isNumber(achievement)) {
                        if ("0".equals(achievement)) {
                            landAchievement.setAchievement(ArithmeticUtils.createBigDecimal(achievement));
                        } else {
                            landAchievement.setAchievement(ArithmeticUtils.createBigDecimal(ArithmeticUtils.div(achievement, "100")));
                        }
                    }
                }
            }
            if (bRow.getCell(1) != null) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(bRow.getCell(1)))) {
                    landAchievement.setClassification(PoiUtils.getCellValue(bRow.getCell(1)));
                }
            }
            if (bRow.getCell(2) != null) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(bRow.getCell(2)))) {
                    landAchievement.setCategory(PoiUtils.getCellValue(bRow.getCell(2)));
                }
            }
        }
        landAchievement.setGrade(grades.stream().filter(baseDataDic -> name.equals(baseDataDic.getName())).findFirst().get().getId());
        //处理只导入了 系数和因素的一种情况
        //当只导入一种情况需要考虑是否曾经导入过数据,如果导入过数据那么 则需要关联更新的情况
    }

    /**
     * 过滤 并且分组
     *
     * @param sheetList
     * @param baseDataDicList
     * @return
     */
    private List<DataAchievementDto> getFilterList(List<Sheet> sheetList, List<BaseDataDic> baseDataDicList) {
        List<Sheet> list = new ArrayList<>(sheetList.size());
        List<DataAchievementDto> dataAchievementDtoList = new ArrayList<>();
        for (Sheet sheet : sheetList) {
            list.add(sheet);
        }
        Iterator<Sheet> iterator = sheetList.iterator();
        while (iterator.hasNext()) {
            Sheet sheet = iterator.next();
            String sheetName = sheet.getSheetName();
            String typeName = null;
            String filterName = null;
            if (StringUtils.contains(sheetName, FACTOR)) {
                typeName = StringUtils.remove(sheetName, FACTOR);
                filterName = COEFFICIENT;
            }
            if (StringUtils.contains(sheetName, COEFFICIENT)) {
                typeName = StringUtils.remove(sheetName, COEFFICIENT);
                filterName = FACTOR;
            }
            if (StringUtils.isBlank(typeName)) {
                continue;
            }
            Sheet sheetV = null;
            for (Sheet sheet2 : list) {
                String name = String.format("%s%s", typeName, filterName);
                if (StringUtils.equals(sheet2.getSheetName(), name)) {
                    sheetV = sheet2;
                }
            }
            DataAchievementDto dataAchievementDto = getDataAchievementDto(sheet);
            if (CollectionUtils.isNotEmpty(baseDataDicList)) {
                for (BaseDataDic baseDataDic : baseDataDicList) {
                    if (StringUtils.equals(baseDataDic.getName(), typeName)) {
                        dataAchievementDto.setType(baseDataDic.getId());
                    }
                }
            }
            if (StringUtils.contains(sheetName, FACTOR)) {
                dataAchievementDto.setA(sheet);
                dataAchievementDto.setaName(sheet.getSheetName());
                if (sheetV != null) {
                    dataAchievementDto.setB(sheetV);
                    dataAchievementDto.setBName(sheetV.getSheetName());
                }
            }
            if (StringUtils.contains(sheetName, COEFFICIENT)) {
                if (sheetV != null) {
                    dataAchievementDto.setA(sheetV);
                    dataAchievementDto.setaName(sheetV.getSheetName());
                }
                dataAchievementDto.setB(sheet);
                dataAchievementDto.setBName(sheet.getSheetName());
            }
            if (sheetV == null) {
                dataAchievementDtoList.add(dataAchievementDto);
                continue;
            }
            List<String> collect = dataAchievementDtoList.stream().map(oo -> oo.getA().getSheetName()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)) {
                String v = typeName;
                boolean anyMatch = collect.stream().anyMatch(s -> StringUtils.contains(s, v));
                if (anyMatch) {
                    continue;
                }
            }
            dataAchievementDtoList.add(dataAchievementDto);
        }
        return dataAchievementDtoList;
    }

    private DataAchievementDto getDataAchievementDto(Sheet sheet) {
        DataAchievementDto achievementDto = new DataAchievementDto();
        achievementDto.setStartRow(3);
        Row row = null;
        //读取数据的起始行
        int startRowNumber = achievementDto.getStartRow();
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        achievementDto.setEndRow(rowLength);
        for (int i = startRowNumber; i < rowLength; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Cell cell = row.getCell(0);
            String value = PoiUtils.getCellValue(cell);
            if (Alias.equals(value)) {
                achievementDto.setTwoRow(i);
            }
        }
        if (achievementDto.getTwoRow() != null) {
            achievementDto.setOneRow(achievementDto.getEndRow() - achievementDto.getTwoRow());
        } else {
            achievementDto.setOneRow(achievementDto.getEndRow());
        }
        return achievementDto;
    }


    public boolean saveDataLandLevelDetailAchievement(DataLandLevelDetailAchievement oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandLevelDetailAchievementDao.saveDataLandLevelDetailAchievement(oo);
        } else {
            return updateDataLandLevelDetailAchievement(oo,true);
        }
    }

    public boolean updateDataLandLevelDetailAchievement(DataLandLevelDetailAchievement oo, boolean updateNull){
        if (updateNull){
            DataLandLevelDetailAchievement dataLandLevelDetailAchievement = getDataLandLevelDetailAchievementById(oo.getId()) ;
            if (oo.getLevelDetailId() == null ){
                oo.setLevelDetailId(dataLandLevelDetailAchievement.getLevelDetailId());
            }
            if (oo.getAchievement() == null ){
                oo.setAchievement(dataLandLevelDetailAchievement.getAchievement());
            }
            if (oo.getGrade() == null ){
                oo.setGrade(dataLandLevelDetailAchievement.getGrade());
            }
            if (StringUtils.isBlank(oo.getCreator())) {
                oo.setCreator(dataLandLevelDetailAchievement.getCreator());
            }
            if (StringUtils.isBlank(oo.getCategory())) {
                oo.setCategory(dataLandLevelDetailAchievement.getCategory());
            }
            if (oo.getType() == null) {
                oo.setType(dataLandLevelDetailAchievement.getType());
            }
            if (StringUtils.isBlank(oo.getClassification())) {
                oo.setClassification(dataLandLevelDetailAchievement.getClassification());
            }
            if (oo.getGmtCreated() == null){
                oo.setGmtCreated(dataLandLevelDetailAchievement.getGmtCreated());
            }
        }
        return dataLandLevelDetailAchievementDao.updateDataLandLevelDetailAchievement(oo, updateNull) ;
    }

    public boolean deleteDataLandLevelDetailAchievement(Integer id) {
        return dataLandLevelDetailAchievementDao.deleteDataLandLevelDetailAchievement(id);
    }

    public void clear(Integer id) {
        DataLandLevelDetailAchievement query = new DataLandLevelDetailAchievement();
        query.setLevelDetailId(id);
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementList = getDataLandLevelDetailAchievementList(query);
        if (CollectionUtils.isEmpty(dataLandLevelDetailAchievementList)) {
            return;
        }
        Iterator<DataLandLevelDetailAchievement> iterator = dataLandLevelDetailAchievementList.iterator();
        while (iterator.hasNext()) {
            DataLandLevelDetailAchievement next = iterator.next();
            deleteDataLandLevelDetailAchievement(next.getId());
        }
    }

    public DataLandLevelDetailAchievement getDataLandLevelDetailAchievementById(Integer id) {
        return dataLandLevelDetailAchievementDao.getDataLandLevelDetailAchievementById(id);
    }

    public List<DataLandLevelDetailAchievement> getDataLandLevelDetailAchievementList(DataLandLevelDetailAchievement oo) {
        return dataLandLevelDetailAchievementDao.getDataLandLevelDetailAchievementList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataLandLevelDetailAchievement oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetailAchievement> list = getDataLandLevelDetailAchievementList(oo);
        List<DataLandLevelDetailAchievementVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataLandLevelDetailAchievementVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    /**
     * 过滤 类型
     *
     * @param oo
     * @return
     */
    public Set<List<List<DataLandLevelDetailAchievementVo>>> landLevelFilter(DataLandLevelDetailAchievement oo) {
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementVoList = getDataLandLevelDetailAchievementList(oo);
        List<List<DataLandLevelDetailAchievementVo>> listList = landLevelFilter2(dataLandLevelDetailAchievementVoList.stream().map(po -> getDataLandLevelDetailAchievementVo(po)).collect(Collectors.toList()));
        Set<List<List<DataLandLevelDetailAchievementVo>>> set = landLevelFilter1(listList);
        return set;
    }

    public List<List<DataLandLevelDetailAchievementVo>> landLevelFilter2(List<DataLandLevelDetailAchievementVo> dataLandLevelDetailAchievementVoList) {
        Map<String, List<DataLandLevelDetailAchievementVo>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievementVoList)) {
            dataLandLevelDetailAchievementVoList.forEach(oo -> {
                List<DataLandLevelDetailAchievementVo> voList = map.get(oo.getCategory());
                if (CollectionUtils.isEmpty(voList)) {
                    voList = Lists.newArrayList();
                }
                voList.add(oo);
                map.put(oo.getCategory(), voList);
            });
        }
        List<List<DataLandLevelDetailAchievementVo>> listList = Lists.newArrayList();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEachOrdered(stringListEntry -> {
                listList.add(stringListEntry.getValue());
            });
        }
        return listList;
    }

    public Set<List<List<DataLandLevelDetailAchievementVo>>> landLevelFilter1(List<List<DataLandLevelDetailAchievementVo>> listList) {
        Set<List<List<DataLandLevelDetailAchievementVo>>> set = Sets.newHashSet();
        Map<Integer, List<List<DataLandLevelDetailAchievementVo>>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(listList)) {
            listList.forEach(po -> {
                int random = RandomUtils.nextInt(0, po.size() - 1);
                Integer key = po.get(random).getType();
                List<List<DataLandLevelDetailAchievementVo>> lists = map.get(key);
                if (CollectionUtils.isEmpty(lists)) {
                    lists = Lists.newArrayList();
                }
                lists.add(po);
                map.put(key, lists);
            });
        }
        if (!map.isEmpty()) {
            map.entrySet().forEach(oo -> {
                set.add(oo.getValue());
            });
        }
        return set;
    }


    public DataLandLevelDetailAchievementVo getDataLandLevelDetailAchievementVo(DataLandLevelDetailAchievement oo) {
        if (oo == null) {
            return null;
        }
        DataLandLevelDetailAchievementVo vo = new DataLandLevelDetailAchievementVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        vo.setGradeName(baseDataDicService.getNameById(oo.getGrade()));
        if (StringUtils.isNotEmpty(oo.getCategory())) {
            vo.setCategoryName(oo.getCategory());
            if (NumberUtils.isNumber(oo.getCategory())) {
                vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
            }
        }
        //vo.setAchievement(oo.getAchievement().multiply(new BigDecimal(100)));
        return vo;
    }

    public DataLandLevelDetailAchievement getDataLandLevelDetailAchievement(Integer levelDetailId, String category, Integer grade, Integer type) {
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievement = dataLandLevelDetailAchievementDao.getDataLandLevelDetailAchievement(levelDetailId, category, grade, type);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievement)) {
            return dataLandLevelDetailAchievement.get(0);
        }
        return null;
    }

    /**
     * 递归往上 查找
     *
     * @param id
     * @param stringList
     */
    private void settingRecursiveHandle(Integer id, LinkedList<String> stringList) {
        DataLandLevelDetail dataLandLevelDetail = dataLandLevelDetailService.getCacheDataLandLevelDetail(id);
        if (dataLandLevelDetail == null) {
            return;
        }
        stringList.add(dataLandLevelDetailService.getCacheNameById(id));
        if (dataLandLevelDetail.getPid() == null || dataLandLevelDetail.getPid() == 0) {
            return;
        } else {
            settingRecursiveHandle(dataLandLevelDetail.getPid(), stringList);
        }
    }

    public String getLandLevelDetailTree(String ids) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.TURQUOISE1.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        List<Integer> integerList = FormatUtils.transformString2Integer(ids);
        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            LinkedList<String> stringList = new LinkedList<>();
            settingRecursiveHandle(id, stringList);
            Collections.reverse(stringList);
            String sheetName = StringUtils.join(stringList, "-");
            downloadDataLandDetailAchievementFile(sheetName, id, wb, style);
        }

        OutputStream fileOut = null;
        String fileName = String.join("", "土地因素模板" + ".xls");
        File file = new File(String.join(File.separator, FileUtils.getTempDirectoryPath(), fileName));
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        sysAttachmentDto.setFieldsName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());

        sysAttachmentDto.setFileName(fileName);
        sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setFileSize(org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file).toString());
        //注意这里因为是linux 路径所以采用/ 或者使用Java自带的判断符号 windows下 WinNTFileSystem linux 下UnixFileSystem
        String ftpBasePath = baseAttachmentService.createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(baseAttachmentService.createNoRepeatFileName(sysAttachmentDto.getFileExtension()));
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file.getPath()), sysAttachmentDto.getFtpFileName());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "erp上传文件出错");
        }
        baseAttachmentService.addAttachment(sysAttachmentDto);
        //完毕之后删除临时文件
        FileUtils.deleteQuietly(file);
        return sysAttachmentDto.getId().toString();
    }


    public void downloadDataLandDetailAchievementFile(String sheetName, Integer id, HSSFWorkbook wb, CellStyle style) throws Exception {
        BiConsumer<HSSFSheet, Integer> baseDataDicBiConsumer = (((sheet, integer) -> {
            for (int i = 0; i < 3; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < 8; j++) {
                    Cell cell = row.createCell(j);
                    if (i == 0 && j == 0) {
                        cell.setCellValue("影响因素           标准");
                    }
                    if (i == 1 && j == 0) {

                    }
                    if (i == 2 && j == 0) {
                        cell.setCellValue("影响因素");
                    }
                    if (i == 0 && j == 3) {
                        cell.setCellValue("优");
                    }
                    if (i == 0 && j == 4) {
                        cell.setCellValue("较优");
                    }
                    if (i == 0 && j == 5) {
                        cell.setCellValue("一般");
                    }
                    if (i == 0 && j == 6) {
                        cell.setCellValue("较劣");
                    }
                    if (i == 0 && j == 7) {
                        cell.setCellValue("劣");
                    }
                    cell.setCellStyle(style);
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));

            sheet.addMergedRegion(new CellRangeAddress(0, 2, 3, 3));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 7, 7));

            //画线(由左上到右下的斜线)
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short) 0, 0, (short) 2, 2);
            HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
            shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_DASHGEL);

        }));
        HSSFSheet wbSheet = wb.createSheet(String.join("-", sheetName, FACTOR));
        baseDataDicBiConsumer.accept(wbSheet, null);
        wbSheet = wb.createSheet(String.join("-", sheetName, COEFFICIENT));
        baseDataDicBiConsumer.accept(wbSheet, null);
    }

}
