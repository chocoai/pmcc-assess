package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportHuaXiaBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportHuaXiaBankMapper;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomReportHuaXiaBankService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private CustomReportHuaXiaBankMapper customReportHuaXiaBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;


    public BootstrapTableVo getCustomReportHuaXiaBankList(String numberValue, String unitName, Integer reportType, String queryPreviewsStartDate,
                                                          String queryPreviewsEndDate, String queryResultStartDate, String queryResultEndDate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Date previewsStartDate = null;
        if (StringUtils.isNotEmpty(queryPreviewsStartDate)) {
            previewsStartDate = DateUtils.parse(queryPreviewsStartDate);
        }
        Date previewsEndDate = null;
        if (StringUtils.isNotEmpty(queryPreviewsStartDate)) {
            previewsEndDate = DateUtils.parse(queryPreviewsEndDate);
        }
        Date resultStartDate = null;
        if (StringUtils.isNotEmpty(queryResultStartDate)) {
            resultStartDate = DateUtils.parse(queryResultStartDate);
        }
        Date resultEndDate = null;
        if (StringUtils.isNotEmpty(queryResultEndDate)) {
            resultEndDate = DateUtils.parse(queryResultEndDate);
        }
        List<CustomReportHuaXiaBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (reportType == resultId) {
            customNumberRecordList = customReportHuaXiaBankMapper.getCustomReportHuaXiaBankList(numberValue, unitName, reportType, consultationId, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        } else {
            customNumberRecordList = customReportHuaXiaBankMapper.getCustomReportHuaXiaBankList(numberValue, unitName, reportType, null, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        }
        List<CustomReportHuaXiaBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportHuaXiaBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportHuaXiaBank>() : vos);
        return vo;
    }

    public CustomReportHuaXiaBank getCustomReportHuaXiaBank(CustomReportHuaXiaBank data) {
        if (data == null) {
            return null;
        }
        CustomReportHuaXiaBank vo = new CustomReportHuaXiaBank();
        BeanUtils.copyProperties(data, vo);

        if (data.getAreaId() != null && data.getAreaId() != 0) {
            //评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                if (judgeObjectList.get(0).getEvaluationArea() != null && judgeObjectList.get(0).getPrice() != null) {
                    vo.setAssessTotal(judgeObjectList.get(0).getEvaluationArea().multiply(judgeObjectList.get(0).getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //项目具体地址及位置
                vo.setSeat(judgeObjectList.get(0).getSeat());
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //评估标的
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        //抵押人(占有人)
        InitiatePossessorVo possessorVo = initiatePossessorService.getDataByProjectId(data.getProjectId());
        String pledger = StringUtils.isNotBlank(possessorVo.getpName()) ? possessorVo.getpName() : possessorVo.getpEntrustmentUnit();
        vo.setPledger(pledger);
        //预评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        Integer preauditId = preauditReport.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (vo.getReportType().equals(preauditId)) {
            vo.setHasPreviews("是");
            vo.setPreviewsNumber(vo.getNumberValue());
            vo.setHasResult("否");
            List<String> consultationNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), consultationId);
            if (CollectionUtils.isNotEmpty(consultationNumberList)) {
                vo.setHasResult("是");
                vo.setResultNumber(consultationNumberList.get(0));
            }
            List<String> resultNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), resultId);
            if (CollectionUtils.isNotEmpty(resultNumberList)) {
                vo.setHasResult("是");
                vo.setResultNumber(resultNumberList.get(0));
            }
        } else if (vo.getReportType().equals(resultId) || vo.getReportType().equals(consultationId)) {
            vo.setHasResult("是");
            vo.setResultNumber(vo.getNumberValue());
            vo.setHasPreviews("否");
            List<String> preauditNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), preauditId);
            if (CollectionUtils.isNotEmpty(preauditNumberList)) {
                vo.setHasPreviews("是");
                vo.setPreviewsNumber(preauditNumberList.get(0));
            }
        } else {
            vo.setHasPreviews("否");
            vo.setHasResult("否");
            List<String> preauditNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), preauditId);
            if (CollectionUtils.isNotEmpty(preauditNumberList)) {
                vo.setHasPreviews("是");
                vo.setPreviewsNumber(preauditNumberList.get(0));
            }
            List<String> consultationNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), consultationId);
            if (CollectionUtils.isNotEmpty(consultationNumberList)) {
                vo.setHasResult("是");
                vo.setResultNumber(consultationNumberList.get(0));
            }
            List<String> resultNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), resultId);
            if (CollectionUtils.isNotEmpty(resultNumberList)) {
                vo.setHasResult("是");
                vo.setResultNumber(resultNumberList.get(0));
            }
        }


        return vo;

    }

    /**
     * 导出
     *
     * @param response
     */
    public void export(HttpServletResponse response, String numberValue, String unitName, Integer reportType, String queryPreviewsStartDate,
                       String queryPreviewsEndDate, String queryResultStartDate, String queryResultEndDate) throws BusinessException, IOException {
        //获取数据
        Date previewsStartDate = null;
        if (StringUtils.isNotEmpty(queryPreviewsStartDate)) {
            previewsStartDate = DateUtils.parse(queryPreviewsStartDate);
        }
        Date previewsEndDate = null;
        if (StringUtils.isNotEmpty(queryPreviewsStartDate)) {
            previewsEndDate = DateUtils.parse(queryPreviewsEndDate);
        }
        Date resultStartDate = null;
        if (StringUtils.isNotEmpty(queryResultStartDate)) {
            resultStartDate = DateUtils.parse(queryResultStartDate);
        }
        Date resultEndDate = null;
        if (StringUtils.isNotEmpty(queryResultEndDate)) {
            resultEndDate = DateUtils.parse(queryResultEndDate);
        }
        List<CustomReportHuaXiaBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (reportType == resultId) {
            customNumberRecordList = customReportHuaXiaBankMapper.getCustomReportHuaXiaBankList(numberValue, unitName, reportType, consultationId, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        } else {
            customNumberRecordList = customReportHuaXiaBankMapper.getCustomReportHuaXiaBankList(numberValue, unitName, reportType, null, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        }

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportHuaXiaBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportHuaXiaBank(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"支行", "委托企业名称", "评估标的", "项目具体地址及位置", "抵押人名称",
                "抵押人取得项目所有权时支付的价格", "抵押人取得项目所有权时间", "评估价值",
                "是否出具预评估报告", "预评报告文号", "是否出具正式报告", "正式报告编号",
                "实际收费金额", "标准收费金额", "实际收费折扣", "未出具正式评估报告的详细原因", "备注"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportHuaXiaBank association = vos.get(i);
            row.createCell(0).setCellValue(association.getUnitName()); // 支行
            row.createCell(1).setCellValue(association.getConsignor()); // 委托企业名称
            row.createCell(2).setCellValue(association.getProjectCategoryName()); // 评估标的
            row.createCell(3).setCellValue(association.getSeat()); // 项目具体地址及位置
            row.createCell(4).setCellValue(association.getPledger()); // 抵押人名称
            row.createCell(5).setCellValue(String.valueOf(association.getAcquirePrice() == null ? "" : association.getAcquirePrice())); // 抵押人取得项目所有权时支付的价格
            row.createCell(6).setCellValue(DateUtils.format(association.getAcquireTieme(), DateUtils.DATE_PATTERN)); // 抵押人取得项目所有权时间
            row.createCell(7).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 评估价值
            row.createCell(8).setCellValue(association.getHasPreviews()); // 是否出具预评估报告
            row.createCell(9).setCellValue(association.getPreviewsNumber()); // 预评报告文号
            row.createCell(10).setCellValue(association.getHasResult()); // 是否出具正式报告
            row.createCell(11).setCellValue(association.getResultNumber()); // 正式报告编号
            row.createCell(12).setCellValue(String.valueOf(association.getContractPrice() == null ? "" : association.getContractPrice())); // 评估价值
            row.createCell(13).setCellValue(String.valueOf(association.getStandardPrice() == null ? "" : association.getStandardPrice())); // 标准收费金额
            row.createCell(14).setCellValue(association.getDiscount()); // 实际收费折扣
            row.createCell(15).setCellValue(association.getReason()); // 未出具正式评估报告的详细原因
            row.createCell(16).setCellValue(association.getRemark()); // 备注
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "华夏银行.XLS");
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
}
