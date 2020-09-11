package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportJiaoTongBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportJiaoTongBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
public class CustomReportJiaoTongBankService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private CustomReportJiaoTongBankMapper customReportJiaoTongBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;


    public BootstrapTableVo getCustomReportJiaoTongBankList(String numberValue, String unitName, Integer reportType, String queryStartDate,
                                                            String queryEndDate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Date startDate = null;
        if (StringUtils.isNotEmpty(queryStartDate)) {
            startDate = DateUtils.parse(queryStartDate);
        }
        Date endDate = null;
        if (StringUtils.isNotEmpty(queryEndDate)) {
            endDate = DateUtils.parse(queryEndDate);
            endDate = DateUtils.addDay(endDate, 1);
        }

        List<CustomReportJiaoTongBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportJiaoTongBankMapper.getCustomReportJiaoTongBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportJiaoTongBankMapper.getCustomReportJiaoTongBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        List<CustomReportJiaoTongBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportJiaoTongBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportJiaoTongBank>() : vos);
        return vo;
    }

    public CustomReportJiaoTongBank getCustomReportJiaoTongBank(CustomReportJiaoTongBank data) {
        if (data == null) {
            return null;
        }
        CustomReportJiaoTongBank vo = new CustomReportJiaoTongBank();
        BeanUtils.copyProperties(data, vo);
        //预评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        Integer preauditId = preauditReport.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();

        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setProjectId(data.getProjectId());
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
        StringBuilder strPreaudit = new StringBuilder();
        StringBuilder strResult = new StringBuilder();

        if (CollectionUtils.isNotEmpty(numberList)) {
            for (ProjectNumberRecord item : numberList) {
                if (item.getReportType().equals(preauditId)) {
                    strPreaudit.append(item.getNumberValue()).append("/");
                }
                if (item.getReportType().equals(resultId) || item.getReportType().equals(consultationId)) {
                    strResult.append(item.getNumberValue()).append("/");
                }
            }
        }
        if (StringUtils.isNotEmpty(strPreaudit.toString())) {
            vo.setPreviewsNumber(strPreaudit.deleteCharAt(strPreaudit.length() - 1).toString());
        }

        if (StringUtils.isNotEmpty(strResult.toString())) {
            vo.setResultNumber(strResult.deleteCharAt(strResult.length() - 1).toString());
        }


        if (data.getAreaId() != null && data.getAreaId() != 0) {
            List<SchemeJudgeFunction> functionList = schemeJudgeFunctionService.getApplicableJudgeFunctionsByAreaId(data.getAreaId());
            List<String> methods = LangUtils.transform(functionList, o -> o.getName());
            //去重
            String districtmethods = publicService.districtString(methods);
            //获取方法
            vo.setMethodNames(districtmethods);
            //评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                SchemeJudgeObject judgeObject= judgeObjectList.get(0);
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(judgeObject);
                if(evaluationArea!=null&&judgeObject.getPrice()!=null){
                    vo.setAssessTotal(evaluationArea.multiply(judgeObject.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                vo.setArea(judgeObject.getEvaluationArea());
                vo.setAssessPrice(judgeObject.getPrice());
                vo.setSeat(judgeObject.getSeat());
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //占有人
        InitiatePossessorVo possessorVo = initiatePossessorService.getDataByProjectId(data.getProjectId());
        String possessor = StringUtils.isNotBlank(possessorVo.getpName()) ? possessorVo.getpName() : possessorVo.getpEntrustmentUnit();
        vo.setPossessor(possessor);

        //类型
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        //签字估价师
        if (StringUtils.isNotEmpty(data.getRealEstateAppraiser())) {
            List<String> names = FormatUtils.transformString2List(data.getRealEstateAppraiser());
            //签字估价师1
            vo.setFirstAppraiser(publicService.getUserNameByAccount(names.get(0)));
            //签字估价师2
            names.remove(0);
            if (names.size() > 0) {
                String accountList = publicService.getUserNameByAccountList(names);
                vo.setSecondAppraiser(accountList);
            }
        }
        return vo;

    }

    /**
     * 导出
     *
     * @param response
     */
    public void export(HttpServletResponse response, String numberValue, String unitName, Integer reportType, String queryStartDate,
                       String queryEndDate) throws BusinessException, IOException {
        //获取数据
        Date startDate = null;
        if (StringUtils.isNotEmpty(queryStartDate)) {
            startDate = DateUtils.parse(queryStartDate);
        }
        Date endDate = null;
        if (StringUtils.isNotEmpty(queryEndDate)) {
            endDate = DateUtils.parse(queryEndDate);
        }

        List<CustomReportJiaoTongBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportJiaoTongBankMapper.getCustomReportJiaoTongBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportJiaoTongBankMapper.getCustomReportJiaoTongBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportJiaoTongBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportJiaoTongBank(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"预估号", "报告号", "类型", "报告日", "基准日",
                "细分2", "委托方", "产权人",
                "地址", "面积", "单价", "总价",
                "方法", "收费金额", "评估公司", "签字估价师1", "签字估价师2"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportJiaoTongBank association = vos.get(i);
            row.createCell(0).setCellValue(association.getPreviewsNumber()); // 预估号
            row.createCell(1).setCellValue(association.getResultNumber()); // 报告号
            row.createCell(2).setCellValue(association.getProjectCategoryName()); // 类型
            row.createCell(3).setCellValue(DateUtils.format(association.getReportIssuanceDate(), DateUtils.DATE_PATTERN)); // 报告日
            row.createCell(4).setCellValue(DateUtils.format(association.getValuationDate(), DateUtils.DATE_PATTERN)); // 基准日
            row.createCell(5).setCellValue(association.getUnitName()); // 细分2
            row.createCell(6).setCellValue(association.getConsignor()); // 委托方
            row.createCell(7).setCellValue(association.getPossessor()); // 产权人
            row.createCell(8).setCellValue(association.getSeat()); // 地址
            row.createCell(9).setCellValue(String.valueOf(association.getArea() == null ? "" : association.getArea())); // 面积
            row.createCell(10).setCellValue(String.valueOf(association.getAssessPrice() == null ? "" : association.getAssessPrice())); // 单价
            row.createCell(11).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 总价
            row.createCell(12).setCellValue(association.getMethodNames()); // 方法
            row.createCell(13).setCellValue(String.valueOf(association.getAssessCost() == null ? "" : association.getAssessCost())); // 收费金额
            row.createCell(14).setCellValue(association.getAssessOrganization()); // 评估公司
            row.createCell(15).setCellValue(association.getFirstAppraiser()); // 签字估价师1
            row.createCell(16).setCellValue(association.getSecondAppraiser()); // 签字估价师2
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "交通银行.XLS");
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
