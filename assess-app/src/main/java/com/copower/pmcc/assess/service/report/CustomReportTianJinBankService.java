package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportTianJinBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportTianJinBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
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
public class CustomReportTianJinBankService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private AdRpcQualificationsService adRpcQualificationsService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private CustomReportTianJinBankMapper customReportTianJinBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;


    public BootstrapTableVo getCustomReportTianJinBankList(String numberValue, String unitName, Integer reportType, String queryStartDate,
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

        List<CustomReportTianJinBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportTianJinBankMapper.getCustomReportTianJinBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportTianJinBankMapper.getCustomReportTianJinBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        List<CustomReportTianJinBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportTianJinBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportTianJinBank>() : vos);
        return vo;
    }

    public CustomReportTianJinBank getCustomReportTianJinBank(CustomReportTianJinBank data) {
        if (data == null) {
            return null;
        }
        CustomReportTianJinBank vo = new CustomReportTianJinBank();
        BeanUtils.copyProperties(data, vo);

        if (data.getAreaId() != null && data.getAreaId() != 0) {
            //评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject=judgeObjectList.get(0);
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(schemeJudgeObject);
                if (evaluationArea != null && schemeJudgeObject.getPrice() != null) {
                    vo.setAssessTotal(evaluationArea.multiply(schemeJudgeObject.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //面积
                vo.setArea(schemeJudgeObject.getEvaluationArea());
                //坐落
                vo.setSeat(schemeJudgeObject.getSeat());
            }
        }

        //抵押人(占有人)
        InitiatePossessorVo possessorVo = initiatePossessorService.getDataByProjectId(data.getProjectId());
        String pledger = StringUtils.isNotBlank(possessorVo.getpName()) ? possessorVo.getpName() : possessorVo.getpEntrustmentUnit();
        vo.setPledger(pledger);
        //业务类型
        vo.setLoanTypeName(baseDataDicService.getNameById(data.getLoanType()));
        //预评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        Integer preauditId = preauditReport.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();

        //文号
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
                if (item.getReportType().equals(resultId)  || item.getReportType().equals(consultationId)) {
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

        List<CustomReportTianJinBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportTianJinBankMapper.getCustomReportTianJinBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportTianJinBankMapper.getCustomReportTianJinBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportTianJinBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportTianJinBank(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"抵押人", "抵押物", "位置", "价值时点", "报告日", "面积", "总价", "预估文号",
                "正式报告文号", "评估收费", "标准收费", "优惠折扣", "备注"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }
        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportTianJinBank tianJinBank = vos.get(i);
            row.createCell(0).setCellValue(tianJinBank.getPledger()); // 抵押人
            row.createCell(1).setCellValue(tianJinBank.getLoanTypeName()); // 抵押物
            row.createCell(2).setCellValue(tianJinBank.getSeat()); // 位置
            row.createCell(3).setCellValue(DateUtils.format(tianJinBank.getValuationDate(), DateUtils.DATE_PATTERN)); // 价值时点
            row.createCell(4).setCellValue(DateUtils.format(tianJinBank.getReportIssuanceDate(), DateUtils.DATE_PATTERN)); // 报告日
            row.createCell(5).setCellValue(String.valueOf(tianJinBank.getArea() == null ? "" : tianJinBank.getArea())); // 面积
            row.createCell(6).setCellValue(String.valueOf(tianJinBank.getAssessTotal() == null ? "" : tianJinBank.getAssessTotal())); // 评估总价
            row.createCell(7).setCellValue(tianJinBank.getPreviewsNumber()); // 预估文号
            row.createCell(8).setCellValue(tianJinBank.getResultNumber()); // 正式报告文号
            row.createCell(9).setCellValue(String.valueOf(tianJinBank.getAssessCost() == null ? "" : tianJinBank.getAssessCost())); // 评估费用
            row.createCell(10).setCellValue(String.valueOf(tianJinBank.getStandardCost() == null ? "" : tianJinBank.getStandardCost())); // 标准收费
            row.createCell(11).setCellValue(tianJinBank.getDiscount()); // 折扣
            row.createCell(12).setCellValue(tianJinBank.getRemark()); // 备注
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "天津银行.XLS");
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
