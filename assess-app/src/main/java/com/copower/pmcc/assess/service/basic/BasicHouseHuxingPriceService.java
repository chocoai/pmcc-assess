package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseHuxingPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:
 */
@Service
public class BasicHouseHuxingPriceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicHouseHuxingPriceDao basicHouseHuxingPriceDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRecordService declareRecordService;

    public Integer saveAndUpdateBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxingPrice, boolean updateNull) throws Exception {
        if (basicHouseHuxingPrice.getId() == null || basicHouseHuxingPrice.getId().intValue() == 0) {
            basicHouseHuxingPrice.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseHuxingPriceDao.addBasicHouseHuxingPrice(basicHouseHuxingPrice);
            return id;
        } else {
            if (updateNull) {
                BasicHouseHuxingPrice unitHuxingPrice = basicHouseHuxingPriceDao.getBasicHouseHuxingPriceById(basicHouseHuxingPrice.getId());
                if (unitHuxingPrice != null) {
                    basicHouseHuxingPrice.setCreator(unitHuxingPrice.getCreator());
                    basicHouseHuxingPrice.setGmtCreated(unitHuxingPrice.getGmtCreated());
                    basicHouseHuxingPrice.setGmtModified(DateUtils.now());
                }
            }
            basicHouseHuxingPriceDao.updateBasicHouseHuxingPrice(basicHouseHuxingPrice, updateNull);
            return basicHouseHuxingPrice.getId();
        }
    }

    public BasicHouseHuxingPrice getBasicHouseHuxingPriceById(Integer id) {
        return basicHouseHuxingPriceDao.getBasicHouseHuxingPriceById(id);
    }

    public BootstrapTableVo getBasicHouseHuxingPriceListVos(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());

        List<BasicHouseHuxingPrice> basicHouseHuxingPrices = basicHouseHuxingPriceDao.basicHouseHuxingList(basicHouseHuxingPrice);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(basicHouseHuxingPrices) ? new ArrayList<BasicHouseHuxingPrice>() : basicHouseHuxingPrices);
        return vo;
    }


    public boolean deleteBasicHouseHuxingPrice(Integer id) {
        return basicHouseHuxingPriceDao.deleteBasicHouseHuxingPrice(id);
    }

    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importData(MultipartFile multipartFile, Integer huxingId, Integer projectId) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            BasicHouseHuxingPrice basicHouseHuxingPrice = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                basicHouseHuxingPrice = new BasicHouseHuxingPrice();
                basicHouseHuxingPrice.setHuxingId(huxingId);
                if (!this.importBasicHouseHuxingPrice(basicHouseHuxingPrice, builder, row, colLength, i, sheet.getRow(0), projectId)) {
                    continue;
                }
                saveAndUpdateBasicHouseHuxingPrice(basicHouseHuxingPrice, false);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }

        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public boolean importBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxingPrice, StringBuilder builder, Row row, int colLength, int i, Row header, Integer projectId) throws Exception {
        //房号
        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(0)))) {
            basicHouseHuxingPrice.setHouseNumber(PoiUtils.getCellValue(row.getCell(0)));
            List<BasicHouseHuxingPrice> list = basicHouseHuxingPriceDao.basicHouseHuxingList(basicHouseHuxingPrice);
            if (CollectionUtils.isNotEmpty(list)) {
                BeanUtils.copyProperties(list.get(0), basicHouseHuxingPrice);
            }
        }

        //权证号
        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(1)))) {
            String value = PoiUtils.getCellValue(row.getCell(1));
            basicHouseHuxingPrice.setDeclareName(value);
            List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
            if (CollectionUtils.isNotEmpty(declareRecordList)) {
                for (DeclareRecord item : declareRecordList) {
                    if (value.equals(item.getName())) {
                        basicHouseHuxingPrice.setDeclareId(item.getId());
                    }
                }
            }
        }

        //面积
        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(2)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(2)))) {
                basicHouseHuxingPrice.setArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(2))));
            } else {
                builder.append(String.format("\n第%s行异常：面积应填写数字", i));
                return false;
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        //自定义数据
        for (int c = 3; c < colLength; c++) {
            String value = PoiUtils.getCellValue(row.getCell(c));
            map.put(PoiUtils.getCellValue(header.getCell(c)), value);
        }
        String string = JSONObject.toJSONString(map);
        if (StringUtils.isNotEmpty(string)) {
            basicHouseHuxingPrice.setJsonData(string);
        }
        return true;
    }

    //验证是否是数字或小数
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) { //判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }

    }
}
