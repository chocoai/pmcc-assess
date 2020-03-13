package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateInvestigationDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateInvestigationVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:
 */
@Service
public class BasicEstateInvestigationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicEstateInvestigationDao basicEstateInvestigationDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicUnitHuxingService BasicUnitHuxingService;

    public List<BasicEstateInvestigation> getBasicEstateInvestigationList(BasicEstateInvestigation oo){
        return basicEstateInvestigationDao.basicUnitHuxingList(oo) ;
    }

    public List<BasicEstateInvestigationVo> getBasicEstateInvestigationVoList(BasicEstateInvestigation oo){
        List<BasicEstateInvestigation> investigationList = new ArrayList<>() ;
        List<BasicEstateInvestigationVo> investigationVoList = new ArrayList<>() ;
        if (CollectionUtils.isNotEmpty(investigationList)){
            investigationList.forEach(basicEstateInvestigation -> investigationVoList.add(getBasicEstateInvestigationVo(basicEstateInvestigation)));
        }
        return investigationVoList;
    }


    public Integer saveAndUpdateBasicEstateInvestigation(BasicEstateInvestigation basicEstateInvestigation, boolean updateNull) throws Exception {
        if (basicEstateInvestigation.getHuxingId() != null) {
            BasicUnitHuxing unitHuxing = BasicUnitHuxingService.getBasicUnitHuxingById(basicEstateInvestigation.getHuxingId());
            BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", unitHuxing.getUnitId());
            if (applyBatchDetail != null) {
                BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
                basicEstateInvestigation.setEstateId(applyBatch.getEstateId());
            }
        }
        if (basicEstateInvestigation.getId() == null || basicEstateInvestigation.getId().intValue() == 0) {
            basicEstateInvestigation.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateInvestigationDao.addBasicEstateInvestigation(basicEstateInvestigation);
            return id;
        } else {
            if (updateNull) {
                BasicEstateInvestigation unitHuxingPrice = basicEstateInvestigationDao.getBasicEstateInvestigationById(basicEstateInvestigation.getId());
                if (unitHuxingPrice != null) {
                    basicEstateInvestigation.setCreator(unitHuxingPrice.getCreator());
                    basicEstateInvestigation.setGmtCreated(unitHuxingPrice.getGmtCreated());
                }
            }
            basicEstateInvestigationDao.updateBasicEstateInvestigation(basicEstateInvestigation, updateNull);
            return basicEstateInvestigation.getId();
        }
    }

    public BasicEstateInvestigation getBasicEstateInvestigationById(Integer id) {
        return basicEstateInvestigationDao.getBasicEstateInvestigationById(id);
    }

    public BootstrapTableVo getBasicEstateInvestigationListVos(BasicEstateInvestigation basicEstateInvestigation) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());

        List<BasicEstateInvestigation> basicEstateInvestigations = basicEstateInvestigationDao.basicUnitHuxingList(basicEstateInvestigation);
        List<BasicEstateInvestigationVo> vos = Lists.newArrayList();
        basicEstateInvestigations.forEach(oo -> vos.add(getBasicEstateInvestigationVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<BasicEstateInvestigationVo>() : vos);
        return vo;
    }


    public boolean deleteBasicEstateInvestigation(Integer id) {
        return basicEstateInvestigationDao.deleteBasicEstateInvestigation(id);
    }

    public BasicEstateInvestigationVo getBasicEstateInvestigationVo(BasicEstateInvestigation basicEstateInvestigation) {
        if (basicEstateInvestigation == null) {
            return null;
        }
        BasicEstateInvestigationVo vo = new BasicEstateInvestigationVo();
        BeanUtils.copyProperties(basicEstateInvestigation, vo);
        if (basicEstateInvestigation.getPlanningUse() != null) {
            vo.setPlanningUseName(baseDataDicService.getNameById(basicEstateInvestigation.getPlanningUse()));
        }
        return vo;
    }

    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importData(MultipartFile multipartFile, Integer huxingId, Integer estateId) throws Exception {
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
            BasicEstateInvestigation basicEstateInvestigation = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                basicEstateInvestigation = new BasicEstateInvestigation();
                if (!this.importBasicEstateInvestigation(basicEstateInvestigation, builder, row, i)) {
                    continue;
                }
                if (huxingId != null) {
                    BasicUnitHuxing unitHuxing = BasicUnitHuxingService.getBasicUnitHuxingById(huxingId);
                    BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", unitHuxing.getUnitId());
                    if (applyBatchDetail != null) {
                        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
                        basicEstateInvestigation.setEstateId(applyBatch.getEstateId());
                    }
                    basicEstateInvestigation.setHuxingId(huxingId);
                } else {
                    basicEstateInvestigation.setEstateId(estateId);
                }
                basicEstateInvestigation.setCreator(commonService.thisUserAccount());
                basicEstateInvestigationDao.addBasicEstateInvestigation(basicEstateInvestigation);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }

        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public boolean importBasicEstateInvestigation(BasicEstateInvestigation basicEstateInvestigation, StringBuilder builder, Row row, int i) throws Exception {
        //规划用途
        List<BaseDataDic> planningUses = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
        //楼栋号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            basicEstateInvestigation.setBuildingNumber(PoiUtils.getCellValue(row.getCell(0)));
        }
        //单元号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            basicEstateInvestigation.setUnitNumber(PoiUtils.getCellValue(row.getCell(1)));
        }
        //房号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            basicEstateInvestigation.setHouseNumber(PoiUtils.getCellValue(row.getCell(2)));
        }
        //建筑面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(3)))) {
                basicEstateInvestigation.setBuildingArea(new BigDecimal(PoiUtils.getCellValue(row.getCell(3))));
            } else {
                builder.append(String.format("\n第%s行异常：建筑面积应填写数字", i));
                return false;
            }
        }
        //单价
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(4)))) {
                basicEstateInvestigation.setPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));
            } else {
                builder.append(String.format("\n第%s行异常：单价应填写数字", i));
                return false;
            }
        }
        //规划用途
        String planningUse = PoiUtils.getCellValue(row.getCell(5));
        if (StringUtils.isNotBlank(planningUse)) {
            BaseDataDic typeDic = baseDataDicService.getDataDicByName(planningUses, planningUse);
            if (typeDic == null) {
                builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(规划用途)", i));
                return false;
            } else {
                //规划用途
                basicEstateInvestigation.setPlanningUse(typeDic.getId());
            }
        }
        //结构
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            basicEstateInvestigation.setConstruction(PoiUtils.getCellValue(row.getCell(6)));
        }
        //备注
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            basicEstateInvestigation.setRemark(PoiUtils.getCellValue(row.getCell(7)));
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
