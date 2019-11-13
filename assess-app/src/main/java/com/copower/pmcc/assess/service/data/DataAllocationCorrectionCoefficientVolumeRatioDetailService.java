package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dto.output.data.DataAllocationCorrectionCoefficientVolumeRatioDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:18
 * @description:容积率修正系数配置 详情(从表)
 */
@Service
public class DataAllocationCorrectionCoefficientVolumeRatioDetailService {

    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    private boolean importDataAllocationCorrectionCoefficientVolumeRatio(DataAllocationCorrectionCoefficientVolumeRatioDetail target, StringBuilder builder, Row row, int i) throws Exception {
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

    public String importDataAllocationCorrectionCoefficientVolumeRatio(MultipartFile multipartFile, DataAllocationCorrectionCoefficientVolumeRatioDetail input) throws Exception {
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
            DataAllocationCorrectionCoefficientVolumeRatioDetail target = null;
            //标识符
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                target = new DataAllocationCorrectionCoefficientVolumeRatioDetail();
                BeanUtils.copyProperties(input, target);
                target.setId(null);
                //excel 处理
                if (!importDataAllocationCorrectionCoefficientVolumeRatio(target, builder, row, i)) {
                    continue;
                }
                saveDataAllocationCorrectionCoefficientVolumeRatioDetail(target);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }

        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean saveDataAllocationCorrectionCoefficientVolumeRatioDetail(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataAllocationCorrectionCoefficientVolumeRatioDetail(oo);
        } else {
            return dataLandDetailAchievementDao.editDataAllocationCorrectionCoefficientVolumeRatioDetail(oo);
        }
    }

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(Integer id) {
        return dataLandDetailAchievementDao.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(id);
    }

    public DataAllocationCorrectionCoefficientVolumeRatioDetail getDataAllocationCorrectionCoefficientVolumeRatioDetailById(Integer id) {
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailById(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatioDetail> getDataAllocationCorrectionCoefficientVolumeRatioDetailList(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAllocationCorrectionCoefficientVolumeRatioDetail> list = getDataAllocationCorrectionCoefficientVolumeRatioDetailList(oo);
        List<DataAllocationCorrectionCoefficientVolumeRatioDetailVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataAllocationCorrectionCoefficientVolumeRatioDetailVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataAllocationCorrectionCoefficientVolumeRatioDetailVo getDataAllocationCorrectionCoefficientVolumeRatioDetailVo(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        if (oo == null) {
            return null;
        }
        DataAllocationCorrectionCoefficientVolumeRatioDetailVo vo = new DataAllocationCorrectionCoefficientVolumeRatioDetailVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        return vo;
    }

}
