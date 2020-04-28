package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.StreamUtils;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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

    private boolean importLandLevelDetail(DataLandLevelDetail target, StringBuilder builder, Row row, int i) throws Exception {
        List<BaseDataDic> classTypes = baseDataDicService.getCacheDataDicList("data.land.level.classify");
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList("data.land.level.roman");
        for (int j = 0; j < 18; j++) {
            switch (j) {
                case 0: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (StringUtils.isNotBlank(value)) {
                        BaseDataDic typeDic = baseDataDicService.getDataDicByName(classTypes, value);
                        if (typeDic == null) {
                            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                        } else {
                            target.setClassify(typeDic.getId());
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 1: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (StringUtils.isNotBlank(value)) {
                        BaseDataDic typeDic = baseDataDicService.getDataDicByName(types, value);
                        if (typeDic == null) {
                            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                        } else {
                            target.setType(typeDic.getId());
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 2: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setPrice(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 3: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setMuPrice(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 4: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setVolumeRate(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 5: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setLegalAge(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 6: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setLandAcquisitionProportion(value);
                    }
                    break;
                }
                case 7: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setLevelRange(value);
                    }
                    break;
                }
                case 8: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setMainStreet(value);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return true;
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
                //excel 处理
                if (!this.importLandLevelDetail(target, builder, row, i)) {
                    continue;
                }
                saveAndUpdateDataLandLevelDetail(target);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public void saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) throws Exception {
        if (dataLandLevelDetail == null) return;
        List<DataLandLevelDetail> dataLandLevelDetailList = getDataLandLevelDetailList(dataLandLevelDetail);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            return;
        }
        if (dataLandLevelDetail.getId() == null || dataLandLevelDetail.getId() <= 0) {
            dataLandLevelDetail.setCreator(commonService.thisUserAccount());
            dataLandLevelDetailDao.addDataLandLevelDetail(dataLandLevelDetail);
        } else {
            dataLandLevelDetailDao.updateDataLandLevelDetail(dataLandLevelDetail);
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
        return detailList.stream().filter(StreamUtils.distinctByKey(o -> o.getId())).collect(Collectors.toList());
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
        BaseDataDic baseDataDic = null;
        if (dataLandLevelDetail.getType() != null) {
            baseDataDic = baseDataDicService.getCacheDataDicById(dataLandLevelDetail.getType());
            if (baseDataDic == null) return null;
            name = baseDataDic.getName();
        }
        if (dataLandLevelDetail.getPid() > 0) {
            dataLandLevelDetail = getCacheDataLandLevelDetail(dataLandLevelDetail.getPid());
            baseDataDic = baseDataDicService.getCacheDataDicById(dataLandLevelDetail.getClassify());
            if (baseDataDic != null) {
                name = baseDataDic.getName() + "/" + name;
            }
        }
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
    public void removeDataLandLevelDetail(Integer id)  {
        List<DataLandLevelDetail> dataLandLevelDetails = getDataLandLevelDetailListByPid(id);
        if (CollectionUtils.isNotEmpty(dataLandLevelDetails)) {
            dataLandLevelDetails.forEach(o -> dataLandLevelDetailDao.removeDataLandLevelDetailById(o.getId()));
        }
        dataLandLevelDetailDao.removeDataLandLevelDetailById(id);
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

    //根据大类和级别获取数据
    public DataLandLevelDetail getDataByClassifyAndType(Integer landLevelId, Integer classify, Integer type) {
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


    //获取最上级明细
    public DataLandLevelDetail getPidByDataLandLevelDetail(Integer dataLandLevelDetailId) {
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetailById(dataLandLevelDetailId);
        if (dataLandLevelDetail == null) return null;
        if (dataLandLevelDetail.getPid() == null || dataLandLevelDetail.getPid() == 0) {
            return dataLandLevelDetail;
        } else {
            return getPidByDataLandLevelDetail(dataLandLevelDetail.getPid());
        }
    }
}
