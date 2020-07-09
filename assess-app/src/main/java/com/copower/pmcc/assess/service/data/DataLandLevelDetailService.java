package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ExcelImportUtils;
import com.copower.pmcc.assess.common.StreamUtils;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:土地级别维护
 */
@Service
public class DataLandLevelDetailService {
    @Autowired
    private DataLandLevelDetailDao dataLandLevelDetailDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private DataLandLevelDetailAchievementService landLevelDetailAchievementService;
    @Autowired
    private DataLandLevelDetailVolumeService volumeRatioDetailService;

    private String getFilterName(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        String nameById = baseDataDicService.getNameById(value);
        if (StringUtils.isNotBlank(nameById)) {
            return nameById;
        }
        return value;
    }

    private void importLandLevelDetail(DataLandLevelDetail target, Integer landLevelId) {
        int i = 0;
        int j = 0;
        DataLandLevelDetail query = null;
        if (StringUtils.isNotBlank(target.getClassify())) {
            i++;
            j++;
        }
        if (StringUtils.isNotBlank(target.getCategory())) {
            i++;
        }
        if (StringUtils.isNotBlank(target.getType())) {
            i++;
            j++;
        }
        if (i == 3) {
            query = new DataLandLevelDetail();
            query.setLandLevelId(landLevelId);
            query.setPid(0);
            query.setClassify(target.getClassify());
            List<DataLandLevelDetail> detailList1 = getDataLandLevelDetailList(query);
            if (CollectionUtils.isEmpty(detailList1)) {
                detailList1 = new ArrayList<>();
                BeanUtils.copyProperties(target, query);
                query.setName(getFilterName(target.getClassify()));
                query.setLandLevelId(landLevelId);
                query.setPid(0);
                query.setId(null);
                saveAndUpdateDataLandLevelDetail(query);
                detailList1.add(query);
            }
            query = new DataLandLevelDetail();
            query.setPid(detailList1.get(0).getId());
            query.setCategory(target.getCategory());
            List<DataLandLevelDetail> detailList2 = getDataLandLevelDetailList(query);
            if (CollectionUtils.isEmpty(detailList2)) {
                detailList2 = new ArrayList<>();
                BeanUtils.copyProperties(target, query);
                query.setName(getFilterName(target.getCategory()));
                query.setPid(detailList1.get(0).getId());
                query.setId(null);
                saveAndUpdateDataLandLevelDetail(query);
                detailList2.add(query);
            }

            query = new DataLandLevelDetail();
            query.setPid(detailList2.get(0).getId());
            query.setType(target.getType());
            List<DataLandLevelDetail> detailList3 = getDataLandLevelDetailList(query);
            if (CollectionUtils.isNotEmpty(detailList3)) {
                target.setId(detailList3.get(0).getId());
            } else {
                target.setPid(query.getPid());
                target.setCategory(null);
                target.setClassify(null);
                target.setName(getFilterName(target.getType()));
            }
        }
        if (j == 2 && i != 3) {
            query = new DataLandLevelDetail();
            query.setPid(0);
            query.setLandLevelId(landLevelId);
            query.setClassify(target.getClassify());
            List<DataLandLevelDetail> detailList1 = getDataLandLevelDetailList(query);
            if (CollectionUtils.isEmpty(detailList1)) {
                detailList1 = new ArrayList<>();
                BeanUtils.copyProperties(target, query);
                query.setName(getFilterName(target.getClassify()));
                query.setLandLevelId(landLevelId);
                query.setPid(0);
                query.setId(null);
                saveAndUpdateDataLandLevelDetail(query);
                detailList1.add(query);
            }
            query = new DataLandLevelDetail();
            query.setPid(detailList1.get(0).getId());
            query.setType(target.getType());
            List<DataLandLevelDetail> detailList2 = getDataLandLevelDetailList(query);
            if (CollectionUtils.isNotEmpty(detailList2)) {
                target.setId(detailList2.get(0).getId());
            } else {
                target.setPid(query.getPid());
                target.setClassify(null);
                target.setName(getFilterName(target.getType()));
            }
        }
    }

    public String importLandLevelDetail(DataLandLevelDetail input, MultipartFile multipartFile) throws Exception {
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
            baseService.writeExceptionInfo(e, "土地级别 详情");
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
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = ExcelImportUtils.getMultimapByClass(DataLandLevelDetail.class, row);
        List<String> requiredList = Arrays.asList("classify");
        Multimap<String, List<BaseDataDic>> baseMap = ArrayListMultimap.create();
        baseMap.put("classify", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LAND_LEVEL_CLASSIFY));
        baseMap.put("type", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LAND_LEVEL_ROMAN));
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DataLandLevelDetail target = null;
            //标识符
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                target = new DataLandLevelDetail();
                BeanUtils.copyProperties(input, target);
                target.setId(null);
                //excel 导入处理
                if (!ExcelImportUtils.excelImportHelp(classArrayListMultimap, target, builder, row, baseMap, requiredList, false)) {
                    continue;
                }
                //excel数据 判断 重复和其它操作
                importLandLevelDetail(target, input.getLandLevelId());
                saveAndUpdateDataLandLevelDetail(target);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public void saveAndUpdateDataLandLevelDetail(DataLandLevelDetail target) {
        if (target == null) return;
        List<DataLandLevelDetail> dataLandLevelDetailList = getDataLandLevelDetailList(target);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            return;
        }
        if (target.getId() == null || target.getId() <= 0) {
            target.setCreator(commonService.thisUserAccount());
            dataLandLevelDetailDao.addDataLandLevelDetail(target);
        } else {
            updateDataLandLevelDetail(target, true);
        }
    }

    public boolean updateDataLandLevelDetail(DataLandLevelDetail target, boolean updateNull) {
        if (updateNull) {
            DataLandLevelDetail source = getCacheDataLandLevelDetail(target.getId());
            if (target.getPid() == null) {
                target.setPid(source.getPid());
            }
            if (target.getLandLevelId() == null) {
                target.setLandLevelId(source.getLandLevelId());
            }
            if (StringUtils.isBlank(target.getCreator())) {
                target.setCreator(source.getCreator());
            }
            if (StringUtils.isBlank(target.getClassify())) {
                target.setClassify(source.getClassify());
            }
            if (StringUtils.isBlank(target.getType())) {
                target.setType(source.getType());
            }
            if (StringUtils.isBlank(target.getCategory())) {
                target.setCategory(source.getCategory());
            }
            if (StringUtils.isBlank(target.getName())) {
                target.setName(source.getName());
            }
            if (target.getGmtCreated() == null) {
                target.setGmtCreated(source.getGmtCreated());
            }
//            if (target.getMainStreet() == null){
//                target.setMainStreet(source.getMainStreet());
//            }

//            if (target.getPrice() == null ) {
//                target.setPrice(source.getPrice());
//            }
//            if (target.getFloorPrice() == null ) {
//                target.setFloorPrice(source.getFloorPrice());
//            }
//            if (target.getMuPrice() == null ) {
//                target.setMuPrice(source.getMuPrice());
//            }
        }
        return dataLandLevelDetailDao.updateDataLandLevelDetail(target, updateNull);
    }

    public boolean updateDataLandLevelDetail(DataLandLevelDetail oo) {
        return updateDataLandLevelDetail(oo, false);
    }

    public void clearNodeChildDataLandLevelDetail(String id) {
        List<Integer> integers = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isEmpty(integers)) {
            return;
        }
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            landLevelDetailAchievementService.clear(integer);
            volumeRatioDetailService.clear(integer);
        }
    }


    public DataLandLevelDetail getDataLandLevelDetailById(Integer id) {
        return dataLandLevelDetailDao.getDataLandLevelDetailById(id);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(DataLandLevelDetail oo) {
        return dataLandLevelDetailDao.getDataLandLevelDetailList(oo);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailTree(DataLandLevelDetail obj) {
        List<DataLandLevelDetail> detailList = new ArrayList<>();
        List<DataLandLevelDetail> dataLandLevelDetailList = getDataLandLevelDetailList(obj);
        settingRecursiveData(dataLandLevelDetailList, detailList);
        //最后去重 根据对象id
        List<DataLandLevelDetail> collect = detailList.stream().filter(StreamUtils.distinctByKey(o -> o.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(collect)) {
            Iterator<DataLandLevelDetail> iterator = collect.iterator();
            while (iterator.hasNext()) {
                DataLandLevelDetail levelDetail = iterator.next();
                if (StringUtils.isNotBlank(levelDetail.getName())) {
                    continue;
                }
                levelDetail.setName(getCacheNameById(levelDetail.getId()));
            }
        }
        Ordering<DataLandLevelDetail> ordering = Ordering.from(new Comparator<DataLandLevelDetail>() {
            @Override
            public int compare(DataLandLevelDetail o1, DataLandLevelDetail o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        Collections.sort(collect, ordering);
        return collect;
    }


    private void settingRecursiveData(List<DataLandLevelDetail> dataLandLevelDetailList, List<DataLandLevelDetail> detailList) {
        if (CollectionUtils.isEmpty(dataLandLevelDetailList)) {
            return;
        }
        Iterator<DataLandLevelDetail> iterator = dataLandLevelDetailList.iterator();
        while (iterator.hasNext()) {
            DataLandLevelDetail landLevelDetail = iterator.next();
            DataLandLevelDetail query = new DataLandLevelDetail();
            query.setPid(landLevelDetail.getId());
            detailList.add(landLevelDetail);
            List<DataLandLevelDetail> levelDetailList = getDataLandLevelDetailList(query);
            if (CollectionUtils.isNotEmpty(levelDetailList)) {
                settingRecursiveData(levelDetailList, detailList);
            }
        }
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getCacheNameById(Integer id) {
        DataLandLevelDetail dataLandLevelDetail = getCacheDataLandLevelDetail(id);
        if (dataLandLevelDetail == null) return null;
        if (StringUtils.isNotBlank(dataLandLevelDetail.getName())) {
            return dataLandLevelDetail.getName();
        }
        String name = StringUtils.EMPTY;
        BiFunction<DataLandLevelDetail, String, String> biConsumer = new BiFunction<DataLandLevelDetail, String, String>() {
            @Override
            public String apply(DataLandLevelDetail dataLandLevelDetail, String s) {
                String name = s;
                if (StringUtils.isBlank(name)) {
                    if (NumberUtils.isNumber(dataLandLevelDetail.getClassify())) {
                        name = baseDataDicService.getNameById(dataLandLevelDetail.getClassify());
                    }
                }
                if (StringUtils.isBlank(name)) {
                    if (NumberUtils.isNumber(dataLandLevelDetail.getType())) {
                        name = baseDataDicService.getNameById(dataLandLevelDetail.getType());
                    }
                }
                if (StringUtils.isBlank(name)) {
                    if (NumberUtils.isNumber(dataLandLevelDetail.getCategory())) {
                        name = baseDataDicService.getNameById(dataLandLevelDetail.getCategory());
                    }
                }
                return name;
            }
        };


        //解决老数据问题
        name = biConsumer.apply(dataLandLevelDetail, name);
        return name;
    }


    /**
     * 缓存中获取
     *
     * @param id
     * @return
     */
    public DataLandLevelDetail getCacheDataLandLevelDetail(Integer id) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_LAND_LEVEL_ID, String.valueOf(id));
        try {
            return LangUtils.singleCache(costsKeyPrefix, id, DataLandLevelDetail.class, (o) -> {
                return dataLandLevelDetailDao.getDataLandLevelDetailById(o);
            });
        } catch (Exception e) {
            return dataLandLevelDetailDao.getDataLandLevelDetailById(id);
        }
    }

    /**
     * 刪除土地級別
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeDataLandLevelDetail(Integer id) {
        DataLandLevelDetail query = new DataLandLevelDetail();
        query.setPid(id);
        List<DataLandLevelDetail> dataLandLevelDetailTree = getDataLandLevelDetailTree(query);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailTree)) {
            Iterator<DataLandLevelDetail> iterator = dataLandLevelDetailTree.iterator();
            while (iterator.hasNext()) {
                DataLandLevelDetail detail = iterator.next();
                dataLandLevelDetailDao.removeDataLandLevelDetailById(detail.getId());
                clearNodeChildDataLandLevelDetail(detail.getId().toString());
            }
        }
        dataLandLevelDetailDao.removeDataLandLevelDetailById(id);
        clearNodeChildDataLandLevelDetail(id.toString());
    }

    /**
     * @param landLevelId
     * @return
     */
    public int getCountByLandLevelId(Integer landLevelId) {
        return dataLandLevelDetailDao.getCountByLandLevelId(landLevelId, null, null);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailListByPid(Integer pid) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setPid(pid);
        return getDataLandLevelDetailList(oo);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailListByLandLevelId(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        return getDataLandLevelDetailList(oo);
    }


    //根据大类和级别获取数据
    public DataLandLevelDetail getDataByClassifyAndType(Integer landLevelId, String classify, String type) {
        List<DataLandLevelDetail> dataList = dataLandLevelDetailDao.getDataByClassifyAndType(landLevelId, classify, type);
        if (CollectionUtils.isNotEmpty(dataList)) {
            return dataList.get(0);
        }
        return null;
    }

    public DataLandLevelDetailVo getDataLandLevelDetailVo(DataLandLevelDetail oo) {
        DataLandLevelDetailVo vo = new DataLandLevelDetailVo();
        if (oo == null) {
            return vo;
        }
        BeanUtils.copyProperties(oo, vo);
        vo.setClassifyName(baseDataDicService.getNameById(oo.getClassify()));
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        return vo;
    }

    /**
     * 获取完整名称
     *
     * @param dataLandLevelDetailId
     * @return
     */
    public String getFullNameByBatchDetailId(Integer dataLandLevelDetailId) {
        List<DataLandLevelDetail> list = Lists.newArrayList();
        collectionParentItems(dataLandLevelDetailId, list);
        if (CollectionUtils.isEmpty(list)) return null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i).getName()).append("/");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 收集该节点的所有上级节点
     *
     * @param dataLandLevelDetailId
     * @param list
     */
    public void collectionParentItems(Integer dataLandLevelDetailId, List<DataLandLevelDetail> list) {
        if (list == null) return;
        DataLandLevelDetail landLevelDetail = getDataLandLevelDetailById(dataLandLevelDetailId);
        if (landLevelDetail == null) return;
        list.add(landLevelDetail);
        if (landLevelDetail.getPid() != null && landLevelDetail.getPid() > 0)
            collectionParentItems(landLevelDetail.getPid(), list);
    }


    /**
     * 收集该节点的所有子节点
     *
     * @param list
     * @param pid
     */
    public void getBestLowItemsByPid(List<DataLandLevelDetail> list, Integer pid) {
        List<DataLandLevelDetail> detailList = getDataLandLevelDetailListByPid(pid);
        if (CollectionUtils.isNotEmpty(detailList)) {
            for (DataLandLevelDetail item : detailList) {
                getBestLowItemsByPid(list, item.getId());
            }
        } else {
            DataLandLevelDetail singleObject = getDataLandLevelDetailById(pid);
            list.add(singleObject);
        }
    }

    /**
     * 获取该节点最上级
     *
     * @param id
     */
    public DataLandLevelDetail getAncestorById(Integer id) {
        DataLandLevelDetail levelDetail = getDataLandLevelDetailById(id);
        if (levelDetail.getPid() == 0||levelDetail.getPid() == null) {
            return levelDetail;
        }
        return getAncestorById(levelDetail.getPid());
    }

    //获取有基准地价的父级(本身有则除外)
    public DataLandLevelDetail hasStandardPremiumParent(Integer dataLandLevelDetailId) {
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetailById(dataLandLevelDetailId);
        if (dataLandLevelDetail == null) return null;
        if (dataLandLevelDetail.getPrice() != null) {
            return dataLandLevelDetail;
        }
        if (dataLandLevelDetail.getPid() == null || dataLandLevelDetail.getPid() == 0) {
            return dataLandLevelDetail;
        }
        return hasStandardPremiumParent(dataLandLevelDetail.getPid());

    }

    //获取有法定年限父级(本身有则除外)
    public DataLandLevelDetail hasLegalAgeParent(Integer dataLandLevelDetailId) {
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetailById(dataLandLevelDetailId);
        if (dataLandLevelDetail == null) return null;
        if (dataLandLevelDetail.getLegalAge() != null) {
            return dataLandLevelDetail;
        }
        if (dataLandLevelDetail.getPid() == null || dataLandLevelDetail.getPid() == 0) {
            return dataLandLevelDetail;
        }
        return hasLegalAgeParent(dataLandLevelDetail.getPid());

    }

    //获取有容积率修正配置的父级(本身有则除外)
    public DataLandLevelDetail hasVolumeFractionAmendParent(Integer dataLandLevelDetailId) {
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetailById(dataLandLevelDetailId);
        if (dataLandLevelDetail == null) return null;
        DataLandLevelDetailVolume data = new DataLandLevelDetailVolume();
        data.setLevelDetailId(dataLandLevelDetail.getId());
        List<DataLandLevelDetailVolume> detailList = volumeRatioDetailService.getDataLandLevelDetailVolumeList(data);
        if (CollectionUtils.isNotEmpty(detailList)) {
            return dataLandLevelDetail;
        }
        if (dataLandLevelDetail.getPid() == null || dataLandLevelDetail.getPid() == 0) {
            return dataLandLevelDetail;
        }
        return hasVolumeFractionAmendParent(dataLandLevelDetail.getPid());

    }
}
