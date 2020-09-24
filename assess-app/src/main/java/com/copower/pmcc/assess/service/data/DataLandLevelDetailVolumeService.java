package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailVolumeDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVolumeVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:18
 * @description:容积率修正系数配置 详情(从表)
 */
@Service
public class DataLandLevelDetailVolumeService {

    @Autowired
    private DataLandLevelDetailVolumeDao dataLandDetailAchievementDao;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    private boolean importDataAllocationCorrectionCoefficientVolumeRatio(DataLandLevelDetailVolume target, StringBuilder builder, Row row, int i) throws Exception {
        final int rowLength = 2;
        for (int j = 0; j < rowLength; j++) {
            switch (j) {
                case 0: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setPlotRatio(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                case 1: {
                    String value = PoiUtils.getCellValue(row.getCell(j));
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
                        target.setCorrectionFactor(ArithmeticUtils.createBigDecimal(value));
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return true;
    }

    public String importDataAllocationCorrectionCoefficientVolumeRatio(MultipartFile multipartFile, DataLandLevelDetailVolume input) throws Exception {
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
            baseService.writeExceptionInfo(e, "基准地价 容积率修正系数配置");
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
            DataLandLevelDetailVolume target = null;
            //标识符
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                target = new DataLandLevelDetailVolume();
                BeanUtils.copyProperties(input, target);
                target.setId(null);
                //excel 处理
                if (!importDataAllocationCorrectionCoefficientVolumeRatio(target, builder, row, i)) {
                    continue;
                }
                saveDataLandLevelDetailVolume(target);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }

        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean saveDataLandLevelDetailVolume(DataLandLevelDetailVolume oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataLandLevelDetailVolume(oo);
        } else {
            return updateDataLandLevelDetailVolume(oo, true);
        }
    }

    public boolean updateDataLandLevelDetailVolume(DataLandLevelDetailVolume oo, boolean updateNull) {
        if (updateNull) {
            DataLandLevelDetailVolume dataLandLevelDetailVolume = getDataLandLevelDetailVolumeById(oo.getId());
            if (oo.getLevelDetailId() == null) {
                oo.setLevelDetailId(dataLandLevelDetailVolume.getLevelDetailId());
            }
            if (oo.getPlotRatio() == null) {
                oo.setPlotRatio(dataLandLevelDetailVolume.getPlotRatio());
            }
            if (oo.getCorrectionFactor() == null) {
                oo.setCorrectionFactor(dataLandLevelDetailVolume.getCorrectionFactor());
            }
            if (StringUtils.isBlank(oo.getCreator())) {
                oo.setCreator(dataLandLevelDetailVolume.getCreator());
            }
            if (oo.getGmtCreated() == null) {
                oo.setGmtCreated(dataLandLevelDetailVolume.getGmtCreated());
            }
        }
        return dataLandDetailAchievementDao.updateDataLandLevelDetailVolume(oo, updateNull);
    }

    public boolean deleteDataLandLevelDetailVolume(Integer id) {
        return dataLandDetailAchievementDao.deleteDataLandLevelDetailVolume(id);
    }

    public void clear(Integer id) {
        DataLandLevelDetailVolume query = new DataLandLevelDetailVolume();
        query.setLevelDetailId(id);
        List<DataLandLevelDetailVolume> dataLandLevelDetailVolumeList = getDataLandLevelDetailVolumeList(query);
        if (CollectionUtils.isEmpty(dataLandLevelDetailVolumeList)) {
            return;
        }
        Iterator<DataLandLevelDetailVolume> iterator = dataLandLevelDetailVolumeList.iterator();
        while (iterator.hasNext()) {
            DataLandLevelDetailVolume next = iterator.next();
            deleteDataLandLevelDetailVolume(next.getId());
        }
    }

    public DataLandLevelDetailVolume getDataLandLevelDetailVolumeById(Integer id) {
        return dataLandDetailAchievementDao.getDataLandLevelDetailVolumeById(id);
    }

    public List<DataLandLevelDetailVolume> getDataLandLevelDetailVolumeList(DataLandLevelDetailVolume oo) {
        return dataLandDetailAchievementDao.getDataLandLevelDetailVolumeList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataLandLevelDetailVolume oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetailVolume> list = getDataLandLevelDetailVolumeList(oo);
        List<DataLandLevelDetailVolumeVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataLandLevelDetailVolumeVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataLandLevelDetailVolumeVo getDataLandLevelDetailVolumeVo(DataLandLevelDetailVolume oo) {
        if (oo == null) {
            return null;
        }
        DataLandLevelDetailVolumeVo vo = new DataLandLevelDetailVolumeVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        return vo;
    }

    //根据配置获取容积率修正
    public BigDecimal getAmendByVolumetricRate(BigDecimal volumeRate, Integer dataLandLevelDetailId) {
        if (volumeRate == null || dataLandLevelDetailId == null) return null;
        //没有配置则往上级找
        DataLandLevelDetail hasVolumeFractionAmendData = dataLandLevelDetailService.hasVolumeFractionAmendParent(dataLandLevelDetailId);
        DataLandLevelDetailVolume data = new DataLandLevelDetailVolume();
        data.setLevelDetailId(hasVolumeFractionAmendData.getId());
        List<DataLandLevelDetailVolume> detailList = getDataLandLevelDetailVolumeList(data);
        if (!CollectionUtils.isNotEmpty(detailList)) return null;
        for (DataLandLevelDetailVolume detailItem : detailList) {
            //直接匹配
            if (detailItem.getPlotRatio() == null) continue;
            if (detailItem.getPlotRatio().compareTo(volumeRate) == 0) {
                return detailItem.getCorrectionFactor();
            }
        }
        //不能直接匹配
        if (detailList.size() > 2) {
            return getAmend(detailList, volumeRate);
        }
        return null;
    }

    public BigDecimal getAmend(List<DataLandLevelDetailVolume> detailList, BigDecimal volumetricRate) {
        if (detailList.size() >= 2) {
            //排序
            Ordering<DataLandLevelDetailVolume> ordering = Ordering.from((o1, o2) -> { return (o1.getPlotRatio().compareTo(o2.getPlotRatio())); });
            detailList.sort(ordering);
            //如果在两个值间
            for (int i = 0; i < detailList.size() - 1; i++) {
                if (detailList.get(i).getPlotRatio().compareTo(volumetricRate) == -1 &&
                        volumetricRate.compareTo(detailList.get(i + 1).getPlotRatio()) == -1) {
                    BigDecimal cardinalNumber = detailList.get(i + 1).getCorrectionFactor().subtract(detailList.get(i).getCorrectionFactor()).divide(detailList.get(i + 1).getPlotRatio().subtract(detailList.get(i).getPlotRatio()), 6, BigDecimal.ROUND_HALF_UP);
                    BigDecimal amend = detailList.get(i).getCorrectionFactor().add(cardinalNumber.multiply(volumetricRate.subtract(detailList.get(i).getPlotRatio())));
                    return amend.setScale(6,BigDecimal.ROUND_HALF_UP);
                }
            }
            //最小值
            DataLandLevelDetailVolume minValue = detailList.get(0);
            //最大值
            DataLandLevelDetailVolume maxValue = detailList.get(detailList.size() - 1);
            //小于最小值
            if (minValue.getPlotRatio().compareTo(volumetricRate) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(1);
                BigDecimal cardinalNumber = tempValue.getCorrectionFactor().subtract(minValue.getCorrectionFactor()).divide(tempValue.getPlotRatio().subtract(minValue.getPlotRatio()), 6, BigDecimal.ROUND_HALF_UP);
                BigDecimal amend = minValue.getCorrectionFactor().subtract(cardinalNumber.multiply(minValue.getPlotRatio().subtract(volumetricRate)));
                return amend.setScale(6,BigDecimal.ROUND_HALF_UP);
            }
            //大于最大值
            if (volumetricRate.compareTo(maxValue.getPlotRatio()) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(detailList.size() - 2);
                BigDecimal cardinalNumber = maxValue.getCorrectionFactor().subtract(tempValue.getCorrectionFactor()).divide(maxValue.getPlotRatio().subtract(tempValue.getPlotRatio()), 6, BigDecimal.ROUND_HALF_UP);
                BigDecimal amend = maxValue.getCorrectionFactor().add(cardinalNumber.multiply(volumetricRate.subtract(maxValue.getPlotRatio())));
                return amend.setScale(6,BigDecimal.ROUND_HALF_UP);
            }
        }
        return null;
    }

}
