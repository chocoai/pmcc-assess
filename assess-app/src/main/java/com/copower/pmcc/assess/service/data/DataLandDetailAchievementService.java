package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandDetailAchievementDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.dto.output.data.DataLandDetailAchievementVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/5 10:18
 * @description:基准地价 土地因素
 */
@Service
public class DataLandDetailAchievementService {
    @Autowired
    private DataLandDetailAchievementDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    private boolean importDataLandDetailAchievement(DataLandDetailAchievement target, StringBuilder builder, Row row, int i) throws Exception {
        final int rowLength = 5;
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList("programme.market.costApproach.factor");
        List<BaseDataDic> grades = baseDataDicService.getCacheDataDicList("programme.market.costApproach.grade");
        for (int j = 0; j < rowLength; j++) {
            switch (j) {
                case 0: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang.StringUtils.isNotBlank(value)) {
                        BaseDataDic typeDic = baseDataDicService.getDataDicByName(types, value);
                        if (typeDic == null) {
                            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                        } else {
                            target.setType(typeDic.getId());
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 1: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang.StringUtils.isNotBlank(value)) {
                        if (target.getType() != null) {
                            BaseDataDic typeDic = baseDataDicService.getDataDicByName(baseDataDicService.getCacheDataDicListByPid(target.getType()), value);
                            if (typeDic == null) {
                                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                            } else {
                                target.setCategory(typeDic.getId().toString());
                            }
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 2: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang.StringUtils.isNotBlank(value)) {
                        if (target.getType() != null) {
                            BaseDataDic typeDic = baseDataDicService.getDataDicByName(grades, value);
                            if (typeDic == null) {
                                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i));
                            } else {
                                target.setGrade(typeDic.getId());
                            }
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 3: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setAchievement(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 4: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setReamark(value);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return true;
    }

    public String importDataLandDetailAchievement(MultipartFile multipartFile, DataLandDetailAchievement input) throws Exception {
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
            baseService.writeExceptionInfo(e, "基准地价 土地因素");
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
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
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DataLandDetailAchievement target = null;
            //标识符
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                target = new DataLandDetailAchievement();
                BeanUtils.copyProperties(input, target);
                target.setId(null);
                //excel 处理
                if (!this.importDataLandDetailAchievement(target, builder, row, i)) {
                    continue;
                }
                saveDataLandDetailAchievement(target);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean saveDataLandDetailAchievement(DataLandDetailAchievement oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataLandDetailAchievement(oo);
        } else {
            return dataLandDetailAchievementDao.editDataLandDetailAchievement(oo);
        }
    }

    public boolean deleteDataLandDetailAchievement(Integer id) {
        return dataLandDetailAchievementDao.deleteDataLandDetailAchievement(id);
    }

    public DataLandDetailAchievement getDataLandDetailAchievementById(Integer id) {
        return dataLandDetailAchievementDao.getDataLandDetailAchievementById(id);
    }

    public List<DataLandDetailAchievement> getDataLandDetailAchievementList(DataLandDetailAchievement oo) {
        return dataLandDetailAchievementDao.getDataLandDetailAchievementList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataLandDetailAchievement oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandDetailAchievement> list = getDataLandDetailAchievementList(oo);
        List<DataLandDetailAchievementVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataLandDetailAchievementVo(po)));
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
    public Set<List<List<DataLandDetailAchievementVo>>> landLevelFilter(DataLandDetailAchievement oo) {
        List<DataLandDetailAchievement> dataLandDetailAchievementVoList = getDataLandDetailAchievementList(oo);
        List<List<DataLandDetailAchievementVo>> listList = landLevelFilter2(dataLandDetailAchievementVoList.stream().map(po -> getDataLandDetailAchievementVo(po)).collect(Collectors.toList()));
        Set<List<List<DataLandDetailAchievementVo>>> set = landLevelFilter1(listList);
        return set;
    }

    public List<List<DataLandDetailAchievementVo>> landLevelFilter2(List<DataLandDetailAchievementVo> dataLandDetailAchievementVoList) {
        Map<String, List<DataLandDetailAchievementVo>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(dataLandDetailAchievementVoList)) {
            dataLandDetailAchievementVoList.forEach(oo -> {
                List<DataLandDetailAchievementVo> voList = map.get(oo.getCategory());
                if (CollectionUtils.isEmpty(voList)) {
                    voList = Lists.newArrayList();
                }
                voList.add(oo);
                map.put(oo.getCategory(), voList);
            });
        }
        List<List<DataLandDetailAchievementVo>> listList = Lists.newArrayList();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEachOrdered(stringListEntry -> {
                listList.add(stringListEntry.getValue());
            });
        }
        return listList;
    }

    public Set<List<List<DataLandDetailAchievementVo>>> landLevelFilter1(List<List<DataLandDetailAchievementVo>> listList) {
        Set<List<List<DataLandDetailAchievementVo>>> set = Sets.newHashSet();
        Map<Integer, List<List<DataLandDetailAchievementVo>>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(listList)) {
            listList.forEach(po -> {
                int random = RandomUtils.nextInt(0, po.size() - 1);
                Integer key = po.get(random).getType();
                List<List<DataLandDetailAchievementVo>> lists = map.get(key);
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


    public DataLandDetailAchievementVo getDataLandDetailAchievementVo(DataLandDetailAchievement oo) {
        if (oo == null) {
            return null;
        }
        DataLandDetailAchievementVo vo = new DataLandDetailAchievementVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        if (StringUtils.isNotEmpty(oo.getCategory())) {
            vo.setCategoryName(oo.getCategory());
            if (NumberUtils.isNumber(oo.getCategory())) {
                vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
            }
        }
        vo.setGradeName(baseDataDicService.getNameById(oo.getGrade()));
//        vo.setJsonObj(JSON.toJSONString(vo));
        return vo;
    }

    public DataLandDetailAchievement getDataLandDetailAchievement(Integer levelDetailId, String category, Integer grade, Integer type) {
        List<DataLandDetailAchievement> dataLandDetailAchievement = dataLandDetailAchievementDao.getDataLandDetailAchievement(levelDetailId, category, grade, type);
        if (CollectionUtils.isNotEmpty(dataLandDetailAchievement)) {
            return dataLandDetailAchievement.get(0);
        }
        return null;
    }


}
