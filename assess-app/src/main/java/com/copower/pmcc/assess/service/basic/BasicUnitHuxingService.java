package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitHuxingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:13
 * @Description:户型
 */
@Service
public class BasicUnitHuxingService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicHouseDao basicHouseDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicUnitHuxing getBasicUnitHuxingById(Integer id) {
        return basicUnitHuxingDao.getBasicUnitHuxingById(id);
    }

    public BasicUnitHuxing getHuxingByHouseId(Integer houseId) {
        BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
        basicUnitHuxing.setHouseId(houseId);
        List<BasicUnitHuxing> tradings = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
        if (org.springframework.util.CollectionUtils.isEmpty(tradings)) return null;
        return tradings.get(0);
    }


    /**
     * 新增或者修改
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing, boolean updateNull) throws Exception {
        if (basicUnitHuxing.getId() == null || basicUnitHuxing.getId().intValue() == 0) {
            basicUnitHuxing.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitHuxingDao.addBasicUnitHuxing(basicUnitHuxing);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicUnitHuxing unitHuxing = basicUnitHuxingDao.getBasicUnitHuxingById(basicUnitHuxing.getId());
                if (unitHuxing != null) {
                    basicUnitHuxing.setBisDelete(unitHuxing.getBisDelete());
                    basicUnitHuxing.setCreator(unitHuxing.getCreator());
                    basicUnitHuxing.setGmtCreated(unitHuxing.getGmtCreated());
                    basicUnitHuxing.setGmtModified(unitHuxing.getGmtModified());
                }
            }
            basicUnitHuxingDao.updateBasicUnitHuxing(basicUnitHuxing, updateNull);
            return basicUnitHuxing.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param huxingId
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnitHuxing(Integer huxingId) throws Exception {
        //如果户型已被引用，则不允许删除
        long count = basicHouseDao.getCountByHuxingId(huxingId);
        if (count > 0)
            throw new BusinessException("已被引用，不允许删除");
        return basicUnitHuxingDao.deleteBasicUnitHuxing(huxingId);
    }


    /**
     * 引用户型
     *
     * @param huxingId
     * @param houseId
     * @throws Exception
     */
    public void referenceHuxing(Integer huxingId, Integer houseId) throws Exception {
        if (huxingId == null || houseId == null)
            throw new BusinessException("参数为空");
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(houseId);
        if (basicHouse != null) {
            basicHouse.setHuxingId(huxingId);
            basicHouseService.saveAndUpdate(basicHouse, false);
        }
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public List<BasicUnitHuxing> basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) {
        return basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
    }

    public BootstrapTableVo getBootstrapTableVo(Integer applyBatchId, String name) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.getHuxingList(applyBatchId, name);
        List<BasicUnitHuxingVo> vos = Lists.newArrayList();
        basicUnitHuxingList.forEach(oo -> vos.add(getBasicUnitHuxingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitHuxingVo>(10) : vos);
        return vo;
    }

    public BasicUnitHuxingVo getBasicUnitHuxingVo(BasicUnitHuxing basicUnitHuxing) {
        if (basicUnitHuxing == null) {
            return null;
        }
        BasicUnitHuxingVo vo = new BasicUnitHuxingVo();
        BeanUtils.copyProperties(basicUnitHuxing, vo);
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitHuxing.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                if (sysAttachmentDto != null) {
                    builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                    builder.append(" ");
                }
            }
            vo.setFileViewName(builder.toString());
        }
        if (NumberUtils.isNumber(basicUnitHuxing.getOrientation())) {
            vo.setOrientationName(String.format("%s%s", StringUtils.defaultString(basicUnitHuxing.getReference()), StringUtils.defaultString(baseDataDicService.getNameById(basicUnitHuxing.getOrientation()))));
        } else {
            vo.setOrientationName(String.format("%s%s", StringUtils.defaultString(basicUnitHuxing.getReference()), StringUtils.defaultString(basicUnitHuxing.getOrientation())));
        }
        vo.setSpatialDistributionName(baseDataDicService.getNameById(NumberUtils.isNumber(basicUnitHuxing.getSpatialDistribution()) ? Integer.parseInt(basicUnitHuxing.getSpatialDistribution()) : null));
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnitHuxing.getCreator()));
        vo.setUtilitiesMeasureName(baseDataDicService.getNameById(basicUnitHuxing.getUtilitiesMeasure()));
        vo.setUtilitiesTypeName(baseDataDicService.getNameById(basicUnitHuxing.getUtilitiesType()));
        if (commonService.thisUserAccount().equalsIgnoreCase(basicUnitHuxing.getCreator()))
            vo.setCanManage(true);
        return vo;
    }

    /**
     * 获取选择户型数据列表
     *
     * @param basicApplyId
     * @return
     */
    public BootstrapTableVo getSelectHuxingList(Integer basicApplyId, Integer caseUnitId) throws Exception {
        BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApplyId);
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxingVo> list = Lists.newArrayList();
        if (basicUnit != null) {
            BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
            basicUnitHuxing.setUnitId(basicUnit.getId());
            List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
            if (!CollectionUtils.isEmpty(basicUnitHuxingList)) {
                for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                    BasicUnitHuxingVo unitHuxingVo = getBasicUnitHuxingVo(unitHuxing);
                    unitHuxingVo.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    list.add(unitHuxingVo);
                }
            }
        }
        vo.setRows(ObjectUtils.isEmpty(list) ? Lists.newArrayList() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 获取选择户型数据列表
     *
     * @param basicUnitId
     * @return
     */
    public BootstrapTableVo getSelectHuxingListByUnitId(Integer basicUnitId) throws Exception {
        BasicUnit basicUnit = basicUnitService.getBasicUnitById(basicUnitId);
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxingVo> list = Lists.newArrayList();
        if (basicUnit != null) {
            BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
            basicUnitHuxing.setUnitId(basicUnit.getId());
            List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
            if (!CollectionUtils.isEmpty(basicUnitHuxingList)) {
                for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                    BasicUnitHuxingVo unitHuxingVo = getBasicUnitHuxingVo(unitHuxing);
                    unitHuxingVo.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    list.add(unitHuxingVo);
                }
            }
        }
        vo.setRows(ObjectUtils.isEmpty(list) ? Lists.newArrayList() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 生成Excel模板
     */
    public Integer createExcelFile(Integer unitHuxingId) throws Exception {
        BasicUnitHuxing unitHuxing = this.getBasicUnitHuxingById(unitHuxingId);
        if (unitHuxing == null) {
            throw new BusinessException("没有获取到有效的数据");
        }
        String path = generateCommonMethod.getLocalPath("房间模板", "xls");

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);

        //创建Excel表头
        List<String> title = Lists.newArrayList();
        title.add("标准房号");
        title.add("房号");
        title.add("楼层");
        title.add("建筑面积");
        JSONObject jsonObject = JSON.parseObject(unitHuxing.getHouseCategory());
        Integer houseNumber = JSON.parseObject(jsonObject.getString("house"), Integer.class);
        for (int i = 1; i <= houseNumber; i++) {
            title.add(String.format("%s%s", "房间", i));
        }

        for (int i = 0; i < title.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            if (title.get(i).contains("房间")) {
                sheet.setColumnWidth(i, 20000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
        }

        //默认生成第二行
        row = sheet.createRow(1);
        for (int i = 0; i < title.size(); i++) {
            Cell cell = row.createCell(i);
            if (title.get(i).contains("房间")) {
                cell.setCellValue("面积()m²,层高()米,净高()米,开间/宽()米,进深/长()米,日照(),采光(),通风(),隔音()");
            }
        }


        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path);
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            throw new BusinessException("生成Excel出错:" + e);
        } finally {
            os.close();
        }

        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(unitHuxing.getId());
        sysAttachmentDto.setTableName("unitHuxing_house");
        sysAttachmentDto.setFileName("房间模板");
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        //上传形成附件
        SysAttachmentDto data = baseAttachmentService.importAjaxFileHandle(path, sysAttachmentDto);
        return data.getId();
    }


    /**
     * 获取附件id
     *
     * @return
     */
    public Integer getAttachmentId(Integer tableId) throws Exception {
        //生成Excel并上传
        return createExcelFile(tableId);
    }

    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importHouse(MultipartFile multipartFile, Integer unitId) throws Exception {
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
            BasicHouse basicHouse = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                basicHouse = new BasicHouse();
                if (!this.writeData(unitId, basicHouse, builder, row, colLength, i)) {
                    continue;
                }

                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }

        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean writeData(Integer unitId, BasicHouse basicHouse, StringBuilder builder, Row row, int colLength, int i) throws Exception {
        BasicApplyBatchDetail unitBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", unitId);
        //标准房号
        String standardHouseNum = PoiUtils.getCellValue(row.getCell(0));
        BasicHouse standardsource = null;
        if (StringUtils.isNotEmpty(standardHouseNum)) {
            //获取单元下所有房屋
            List<BasicApplyBatchDetail> houseListBatch = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(unitBatchDetail.getId(), unitBatchDetail.getApplyBatchId());
            if (CollectionUtils.isNotEmpty(houseListBatch)) {
                for (BasicApplyBatchDetail houseBatch : houseListBatch) {
                    if (StringUtils.equals(standardHouseNum, houseBatch.getName())) {
                        standardsource = basicHouseService.getBasicHouseById(houseBatch.getTableId());
                        break;
                    }
                }
            }
        }
        //将标准信息代入
        if (standardsource != null) {
            basicHouseService.copyBasicEntity(standardsource.getId(), basicHouse.getId(), true);
        }
        //房号
        if (StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            basicHouse.setHouseNumber(PoiUtils.getCellValue(row.getCell(1)));
        }
        //楼层
        if (StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            basicHouse.setFloor(PoiUtils.getCellValue(row.getCell(2)));
        }
        //建筑面积
        if (StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(3)))) {
                basicHouse.setArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(3))));
            } else {
                builder.append(String.format("\n第%s行异常：建筑面积应填写数字", i));
                return false;
            }
        }
        basicHouseService.saveAndUpdate(basicHouse, false);
        //删除复制来的房间数据
        List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
        if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom houseRoom : basicHouseRoomList) {
                basicHouseRoomService.deleteBasicHouseRoom(houseRoom.getId());
            }

        }
        //读取excel房间数据
        for (int c = 4; c < colLength; c++) {
            BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
            basicHouseRoom.setHouseId(basicHouse.getId());
            String roomData = PoiUtils.getCellValue(row.getCell(c));
            String[] split = roomData.split(",|，");
            List<String> value = Lists.newArrayList();
            for (int n = 0; n < split.length; n++) {
                String regex = "(?<=\\()[^\\)]+";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(split[n]);
                if (!m.find()) {
                    value.add("");
                } else {
                    value.add(m.group());
                }

            }

            if (CollectionUtils.isNotEmpty(value)) {
                if (StringUtils.isNotEmpty(value.get(0))) {
                    basicHouseRoom.setArea(new BigDecimal(value.get(0)));
                }
                if (StringUtils.isNotEmpty(value.get(1))) {
                    basicHouseRoom.setLayerHeight(new BigDecimal(value.get(1)));
                }
                if (StringUtils.isNotEmpty(value.get(2))) {
                    basicHouseRoom.setClearHeight(new BigDecimal(value.get(2)));
                }
                if (StringUtils.isNotEmpty(value.get(3)))
                    basicHouseRoom.setOpening(value.get(3));
                if (StringUtils.isNotEmpty(value.get(4)))
                    basicHouseRoom.setDepth(value.get(4));
                if (StringUtils.isNotEmpty(value.get(5)))
                    basicHouseRoom.setSunshine(value.get(5));
                if (StringUtils.isNotEmpty(value.get(6)))
                    basicHouseRoom.setLighting(value.get(6));
                if (StringUtils.isNotEmpty(value.get(7)))
                    basicHouseRoom.setAeration(value.get(7));
                if (StringUtils.isNotEmpty(value.get(8)))
                    basicHouseRoom.setSoundInsulation(value.get(8));
            }
            basicHouseRoomService.saveAndUpdateBasicHouseRoom(basicHouseRoom, false);
        }
        //生成房屋的basicApplyBatchDetail数据
        BasicApplyBatchDetail houseBatchDetail = new BasicApplyBatchDetail();
        houseBatchDetail.setTableId(basicHouse.getId());
        houseBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        houseBatchDetail.setPid(unitBatchDetail.getId());
        houseBatchDetail.setApplyBatchId(unitBatchDetail.getApplyBatchId());
        houseBatchDetail.setName(basicHouse.getHouseNumber());
        houseBatchDetail.setDisplayName(basicHouse.getHouseNumber());
        houseBatchDetail.setCreator(commonService.thisUserAccount());
        houseBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailDao.addInfo(houseBatchDetail);
        basicApplyBatchDetailService.insertBasicApply(houseBatchDetail);
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
