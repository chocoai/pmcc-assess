package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.RomanNumeral;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    private boolean importLandLevelDetail(DataLandLevelDetail oo, StringBuilder builder, Row row, int i) throws Exception {
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
                            oo.setClassify(typeDic.getId().toString());
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
                            oo.setType(typeDic.getId().toString());
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：类型没有填写正确", i));
                    }
                    break;
                }
                case 2: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setPrice(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 3: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setMuPrice(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 4: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setVolumeRate(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 5: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setLegalAge(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 6: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setLandAcquisitionProportion(value);
                    }
                    break;
                }
                case 7: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setLevelRange(value);
                    }
                    break;
                }
                case 8: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        oo.setMainStreet(value);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return true;
    }

    public String importLandLevelDetail(DataLandLevelDetail input,List<DataLandLevelDetail> dataLandLevelDetailList ,MultipartFile multipartFile) throws Exception {
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
            boolean flag = true;
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
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                saveAndUpdateDataLandLevelDetail(target);
                dataLandLevelDetailList.add(target) ;
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public void saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        if (dataLandLevelDetail.getId() == null || dataLandLevelDetail.getId() == 0) {
            dataLandLevelDetail.setCreator(commonService.thisUserAccount());
            dataLandLevelDetailDao.addDataLandLevelDetail(dataLandLevelDetail);
        } else {
            dataLandLevelDetailDao.updateDataLandLevelDetail(dataLandLevelDetail);
        }
    }

    public DataLandLevelDetail getDataLandLevelDetailById(Integer id) {
        return dataLandLevelDetailDao.getDataLandLevelDetailById(id);
    }

    /**
     * 获取列表数据
     *
     * @param dataLandLevelDetail
     * @return
     */
    public BootstrapTableVo getDataLandLevelDetailTableVo(DataLandLevelDetail dataLandLevelDetail) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailSorted(getDataLandLevelDetailList(dataLandLevelDetail));
        List<DataLandLevelDetailVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            dataLandLevelDetailList.forEach(obj -> vos.add(getDataLandLevelDetailVo(obj)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    /**
     * 注意这是 landLevelId 列表
     *
     * @param integerList
     * @return
     */
    public List<DataLandLevelDetail> getByMasterIdInfo(List<Integer> integerList) {
        return dataLandLevelDetailDao.getByMasterIdInfo(integerList);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(DataLandLevelDetail oo) {
        return dataLandLevelDetailSorted(dataLandLevelDetailDao.getDataLandLevelDetailList(oo));
    }

    /**
     * 分类并且排序
     *
     * @param dataLandLevelDetails
     * @return
     */
    public List<DataLandLevelDetail> dataLandLevelDetailSorted(List<DataLandLevelDetail> dataLandLevelDetails) {
        LinkedHashMap<String, List<DataLandLevelDetail>> listLinkedHashMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(dataLandLevelDetails)) {
            dataLandLevelDetails.forEach(oo -> {
                List<DataLandLevelDetail> details = listLinkedHashMap.get(oo.getClassify());
                if (CollectionUtils.isEmpty(details)) {
                    details = Lists.newArrayList();
                }
                details.add(oo);
                listLinkedHashMap.put(oo.getClassify(), details);
            });
        }
        List<DataLandLevelDetail> dataLandLevelDetailList = Lists.newArrayList();
        if (!listLinkedHashMap.isEmpty()) {
            listLinkedHashMap.entrySet().stream().forEachOrdered(stringListEntry -> {
                if (CollectionUtils.isNotEmpty(stringListEntry.getValue())) {
                    List<DataLandLevelDetail> landLevelDetailList = stringListEntry.getValue().stream().sorted((o1, o2) -> {
                        int i = 0;
                        final String remove = "级";
                        if (StringUtils.isNotEmpty(o1.getType())) {
                            i++;
                        }
                        if (StringUtils.isNotEmpty(o2.getType())) {
                            i++;
                        }
                        if (i == 2) {
                            if (StringUtils.contains(o1.getType(), remove) && StringUtils.contains(o2.getType(), remove)) {
                                String value1 = StringUtils.trim(StringUtils.remove(o1.getType(), remove));
                                String value2 = StringUtils.trim(StringUtils.remove(o2.getType(), remove));
                                try {
                                    return Integer.compare(RomanNumeral.intValue(value1), RomanNumeral.intValue(value2));
                                } catch (Exception e) {
                                }
                            }
                        }
                        if (NumberUtils.isNumber(o1.getType()) && NumberUtils.isNumber(o2.getType())) {
                            String value1 = baseDataDicService.getNameById(o1.getType());
                            String value2 = baseDataDicService.getNameById(o2.getType());
                            try {
                                return Integer.compare(RomanNumeral.intValue(value1), RomanNumeral.intValue(value2));
                            } catch (Exception e) {
                            }
                        }
                        return 0;
                    }).collect(Collectors.toList());
                    dataLandLevelDetailList.addAll(landLevelDetailList);
                }
            });
        }
        return dataLandLevelDetailList;
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getCacheNameById(Integer id) {
        DataLandLevelDetail dataLandLevelDetail = null;
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_LAND_LEVEL_ID, String.valueOf(id));
            dataLandLevelDetail = LangUtils.singleCache(costsKeyPrefix, id, DataLandLevelDetail.class, (o) -> {
                return dataLandLevelDetailDao.getDataLandLevelDetailById(o);
            });
        } catch (Exception e) {
            dataLandLevelDetail = dataLandLevelDetailDao.getDataLandLevelDetailById(id);
        }
        if (dataLandLevelDetail == null) return null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dataLandLevelDetail.getClassify());
        if (StringUtils.isNotBlank(dataLandLevelDetail.getType()))
            stringBuffer.append("/").append(dataLandLevelDetail.getType());
        if (StringUtils.isNotBlank(dataLandLevelDetail.getCategory()))
            stringBuffer.append("/").append(dataLandLevelDetail.getCategory());
        return stringBuffer.toString();
    }

    /**
     * 刪除土地級別
     *
     * @param id
     */
    public void removeDataLandLevelDetail(Integer id) {
        DataLandLevelDetail levelDetail = dataLandLevelDetailDao.getDataLandLevelDetailById(id);
        if (levelDetail != null) {
            levelDetail.setBisDelete(true);
            dataLandLevelDetailDao.updateDataLandLevelDetail(levelDetail);
        }
    }

    /**
     * @param landLevelId
     * @return
     */
    public int getCountByLandLevelId(Integer landLevelId) {
        return dataLandLevelDetailDao.getCountByLandLevelId(landLevelId);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailListByPid(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        return dataLandLevelDetailSorted(getDataLandLevelDetailList(oo));
    }

    //根据大类和级别获取数据
    public DataLandLevelDetail getDataByClassifyAndType(String classify, String type, Integer landLevelId) {
        List<DataLandLevelDetail> dataList = dataLandLevelDetailDao.getDataByClassifyAndType(classify, type, landLevelId);
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
        if (NumberUtils.isNumber(oo.getClassify())) {
            vo.setClassifyName(baseDataDicService.getNameById(oo.getClassify()));
        } else {
            vo.setClassifyName(oo.getClassify());
        }
        if (NumberUtils.isNumber(oo.getType())) {
            vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        } else {
            vo.setTypeName(oo.getType());
        }
        return vo;
    }

}
