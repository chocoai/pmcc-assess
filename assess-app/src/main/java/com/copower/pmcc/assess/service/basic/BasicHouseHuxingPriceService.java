package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseHuxingPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHousePriceDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseHuxingPriceVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclarePublicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:21
 * @Description:供排水情况
 */
@Service
public class BasicHouseHuxingPriceService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseHuxingPriceDao basicHouseHuxingPriceDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseHuxingPrice getBasicHouseHuxingPriceById(Integer id) throws Exception {
        return basicHouseHuxingPriceDao.getBasicHouseHuxingPriceById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseHuxingPrice
     * @return
     * @throws Exception
     */
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


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouseHuxingPrice(Integer id) throws Exception {
        return basicHouseHuxingPriceDao.deleteBasicHouseHuxingPrice(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseHuxingPrice
     * @return
     * @throws Exception
     */
    public List<BasicHouseHuxingPrice> basicHouseHuxingPriceList(BasicHouseHuxingPrice basicHouseHuxingPrice) throws Exception {
        return basicHouseHuxingPriceDao.basicHouseHuxingPriceList(basicHouseHuxingPrice);
    }

    public List<BasicHouseHuxingPrice> getBasicHouseHuxingPriceList(Integer houseId) {
        BasicHouseHuxingPrice where = new BasicHouseHuxingPrice();
        where.setHouseId(houseId);
        return basicHouseHuxingPriceDao.basicHouseHuxingPriceList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseHuxingPrice basicHouseHuxingPrice) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseHuxingPrice> basicHouseHuxingPriceList = basicHouseHuxingPriceDao.basicHouseHuxingPriceList(basicHouseHuxingPrice);
        List<BasicHouseHuxingPriceVo> voList = LangUtils.transform(basicHouseHuxingPriceList, o -> getBasicHouseHuxingPriceVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(voList) ? new ArrayList<BasicHouseHuxingPriceVo>(10) : voList);
        return vo;
    }

    public BootstrapTableVo loadListByIds(List<Integer> ids, String houseNum) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());

        List<BasicHouseHuxingPrice> basicHouseHuxingPriceList = basicHouseHuxingPriceDao.getListByIds(ids, houseNum);
        List<BasicHouseHuxingPriceVo> voList = LangUtils.transform(basicHouseHuxingPriceList, o -> getBasicHouseHuxingPriceVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(voList) ? new ArrayList<BasicHouseHuxingPriceVo>(10) : voList);
        return vo;
    }

    public BootstrapTableVo getListByQuery(String judgeIds, String houseNum) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseHuxingPrice> huxingPriceList = Lists.newArrayList();
        if(StringUtils.isNotEmpty(judgeIds)){
            List<String> idsStr = Arrays.asList(judgeIds.split(","));
            ArrayList<Integer> completeIds = Lists.newArrayList();
            for (String id : idsStr) {
                if (NumberUtils.isNumber(id)) {
                    BasicHouse basicHouse = schemeSurePriceService.getBasicHouse(Integer.valueOf(id));
                    completeIds.add(basicHouse.getId());
                }
            }
            huxingPriceList = basicHouseHuxingPriceDao.getListByQuery(completeIds, houseNum);
        }

        List<BasicHouseHuxingPriceVo> voList = LangUtils.transform(huxingPriceList, o -> getBasicHouseHuxingPriceVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(voList) ? new ArrayList<BasicHouseHuxingPriceVo>(10) : voList);
        return vo;
    }

    public BasicHouseHuxingPriceVo getBasicHouseHuxingPriceVo(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        if (basicHouseHuxingPrice == null) {
            return null;
        }
        BasicHouseHuxingPriceVo vo = new BasicHouseHuxingPriceVo();
        BeanUtils.copyProperties(basicHouseHuxingPrice, vo);
        vo.setOrientationName(baseDataDicService.getNameById(basicHouseHuxingPrice.getOrientation()));
        vo.setStandardMeasureName(baseDataDicService.getNameById(basicHouseHuxingPrice.getStandardMeasure()));
        vo.setStorageRequestName(baseDataDicService.getNameById(basicHouseHuxingPrice.getStorageRequest()));
        vo.setAdjacentPositionName(baseDataDicService.getNameById(basicHouseHuxingPrice.getAdjacentPosition()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseHuxingPrice.getCreator()));
        if (!StringUtils.isEmpty(basicHouseHuxingPrice.getAdjacentPosition()) && !StringUtils.isEmpty(basicHouseHuxingPrice.getDistance())) {
            String[] adjacentPositions = basicHouseHuxingPrice.getAdjacentPosition().split(",");
            String[] distances = basicHouseHuxingPrice.getDistance().split(",");
            StringBuilder s = new StringBuilder();
            if (adjacentPositions.length > 0) {
                for (int i = 0; i < adjacentPositions.length; i++) {
                    s.append("距离").append(baseDataDicService.getNameById(adjacentPositions[i])).append(":").append(adjacentPositions[i]).append("m").append(";");
                }
            }
            vo.setAdjacentPositionDescribe(s.toString());
        }
        if (StringUtils.isNotEmpty(basicHouseHuxingPrice.getJsonData())) {
            List<ExamineHousePriceDto> list = JSON.parseArray(basicHouseHuxingPrice.getJsonData(), ExamineHousePriceDto.class);
            if (CollectionUtils.isNotEmpty(list)) {
                StringBuilder factorDescribe = new StringBuilder();
                for (ExamineHousePriceDto item : list) {
                    factorDescribe.append(item.getName()).append(":").append(item.getValue()).append(";");
                }
                vo.setFactorDescribe(factorDescribe.toString());
            }
        }
        return vo;
    }


    /**
     * 导出
     *
     * @param response
     */
    public void generateAndExport(HttpServletResponse response, List<ExamineHousePriceDto> dynamicList, String source) throws BusinessException, IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        //创建Excel标题 估价对象名称、评估面积、楼层、房号、评估价格、因素
        ArrayList<ExamineHousePriceDto> columnsList = Lists.newArrayList();
        LinkedHashMap<String, String> base = new LinkedHashMap<>();
        base.put("houseNumber", "房号");
        base.put("area", "面积");
        base.put("floor", "楼层");
        if (StringUtils.isNotEmpty(source)) {
            base.put("price", "价格");
            base.put("adjustFactor", "因素");
        }
        for (Map.Entry<String, String> stringObjectEntry : base.entrySet()) {
            ExamineHousePriceDto dto = new ExamineHousePriceDto();
            dto.setKey(stringObjectEntry.getKey());
            dto.setValue(stringObjectEntry.getValue());
            columnsList.add(dto);
        }
        columnsList.addAll(dynamicList);
        for (int i = 0; i < columnsList.size(); i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(columnsList.get(i).getKey());
            Cell cell2 = row2.createCell(i);
            cell2.setCellValue(columnsList.get(i).getValue());
            sheet.setColumnWidth(i, 4000);
        }
        row1.setZeroHeight(true);

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "户型差异调查表模板.XLS");
            wb.write(os);
        } catch (Exception e) {
            throw new BusinessException("导出Excel出错:" + e);
        } finally {
            os.flush();
            os.close();
        }
    }

    /**
     * 响应流
     *
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importData(MultipartFile multipartFile, Integer houseId, Integer projectId) throws Exception {
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
        int startRowNumber = 2;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;

        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = declarePublicService.getMultimapByClass(BasicHouseHuxingPrice.class, row);

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
                basicHouseHuxingPrice.setHouseId(houseId);
                if (!this.importBasicHouseHuxingPrice(classArrayListMultimap, basicHouseHuxingPrice, builder, row, colLength, i, sheet.getRow(1), projectId)) {
                    continue;
                }
                //权证号默认与房屋权证一致
                BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), houseId);
                basicHouseHuxingPrice.setDeclareId(houseDetail.getDeclareRecordId());
                saveAndUpdateBasicHouseHuxingPrice(basicHouseHuxingPrice, false);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }

        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public boolean importBasicHouseHuxingPrice(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, BasicHouseHuxingPrice basicHouseHuxingPrice, StringBuilder builder, Row row, int colLength, int i, Row header, Integer projectId) throws Exception {
        //必填项
        List<String> requiredList = new ArrayList<>();
        requiredList.addAll(Arrays.asList("houseNumber", "area"));

        //数据字典 map
        Multimap<String, List<BaseDataDic>> baseMap = ArrayListMultimap.create();
        baseMap.put("standardMeasure", baseDataDicService.getCacheDataDicList("examine.house.room.standard.measure"));
        baseMap.put("storageRequest", baseDataDicService.getCacheDataDicList("examine.house.room.storage.request"));
        baseMap.put("adjacentPosition", baseDataDicService.getCacheDataDicList("examine.house.room.adjacent.position"));
        baseMap.put("orientation", baseDataDicService.getCacheDataDicList("examine.house.room.orientation"));

        //房号
        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(0)))) {
            basicHouseHuxingPrice.setHouseNumber(PoiUtils.getCellValue(row.getCell(0)));
            List<BasicHouseHuxingPrice> list = basicHouseHuxingPriceDao.basicHouseHuxingPriceList(basicHouseHuxingPrice);
            if (CollectionUtils.isNotEmpty(list)) {
                basicHouseHuxingPrice.setId(list.get(0).getId());
            }
        }

        boolean check = declarePublicService.excelImportHelp(classArrayListMultimap, basicHouseHuxingPrice, builder, row, baseMap, requiredList);
        return check;
    }

}
