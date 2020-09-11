package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportHengFengBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportHengFengBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
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
public class CustomReportHengFengBankService {
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
    private CustomReportHengFengBankMapper customReportHengFengBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;


    public BootstrapTableVo getCustomReportHengFengBankList(String numberValue, String unitName, Integer reportType, String queryStartDate,
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
        }

        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        List<CustomReportHengFengBank> customNumberRecordList = null;
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportHengFengBankMapper.getCustomReportHengFengBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportHengFengBankMapper.getCustomReportHengFengBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        List<CustomReportHengFengBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportHengFengBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportHengFengBank>() : vos);
        return vo;
    }

    public CustomReportHengFengBank getCustomReportHengFengBank(CustomReportHengFengBank data) {
        if (data == null) {
            return null;
        }
        CustomReportHengFengBank vo = new CustomReportHengFengBank();
        BeanUtils.copyProperties(data, vo);
        //月份
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
            if (DateUtils.getMonth(data.getPreauditNumberDate()) != -1) {
                vo.setMonth(String.format("%s%s", DateUtils.getMonth(data.getPreauditNumberDate()), "月"));
            } else {
                vo.setMonth("-");
            }
        }
        if (vo.getReportType().equals(resultId) || vo.getReportType().equals(consultationId)) {
            if (DateUtils.getMonth(data.getResultNumberDate()) != -1) {
                vo.setMonth(String.format("%s%s", DateUtils.getMonth(data.getResultNumberDate()), "月"));
            } else {
                vo.setMonth("-");
            }
        }
        //工作日期
        if (data.getReportIssuanceDate() != null && data.getHomeWorkEndTime() != null) {
            vo.setWorkDate(String.format("%s~%s", DateUtils.format(data.getReportIssuanceDate(), DateUtils.DATE_PATTERN), DateUtils.format(data.getHomeWorkEndTime(), DateUtils.DATE_PATTERN)));
        }
        //抵押物性质
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        if (data.getAreaId() != null && data.getAreaId() != 0) {
            List<SchemeJudgeFunction> functionList = schemeJudgeFunctionService.getApplicableJudgeFunctionsByAreaId(data.getAreaId());
            List<String> methods = LangUtils.transform(functionList, o -> o.getName());
            //去重
            String districtmethods = publicService.districtString(methods);
            //获取方法
            vo.setMethodNames(districtmethods);
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject=judgeObjectList.get(0);
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(schemeJudgeObject);
                vo.setArea(schemeJudgeObject.getEvaluationArea());
                //评估总价
                if (evaluationArea != null && schemeJudgeObject.getPrice() != null) {
                    vo.setAssessTotal(evaluationArea.multiply(schemeJudgeObject.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //抵押物地址
                vo.setSeat(schemeJudgeObject.getSeat());
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //评估类型
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        //签字估价师
        if (StringUtils.isNotEmpty(data.getRealEstateAppraiser())) {
            List<String> names = FormatUtils.transformString2List(data.getRealEstateAppraiser());
            String accountList = publicService.getUserNameByAccountList(names);
            vo.setAppraiser(accountList);
        }
        //文号
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setProjectId(data.getProjectId());
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
        StringBuilder numberStr = new StringBuilder();


        if (CollectionUtils.isNotEmpty(numberList)) {
            for (ProjectNumberRecord item : numberList) {
                numberStr.append(item.getNumberValue()).append("/");
            }
        }
        if (StringUtils.isNotEmpty(numberStr.toString())) {
            vo.setNumberValue(numberStr.deleteCharAt(numberStr.length() - 1).toString());
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
            endDate = DateUtils.addDay(endDate, 1);
        }

        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        List<CustomReportHengFengBank> customNumberRecordList = null;
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportHengFengBankMapper.getCustomReportHengFengBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportHengFengBankMapper.getCustomReportHengFengBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportHengFengBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportHengFengBank(o));

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"月份", "委托方", "上报部门", "报告编号", "项目名称",
                "工作日期", "抵押物性质", "抵押物地址",
                "面积", "评估金额", "评估方法", "签字估价师",
                "贷款金额", "抵押率", "收费金额", "评估公司", "备注"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportHengFengBank association = vos.get(i);
            row.createCell(0).setCellValue(association.getMonth()); // 月份
            row.createCell(1).setCellValue(association.getConsignor()); // 委托人
            row.createCell(2).setCellValue(association.getUnitName()); // 报告使用单位
            row.createCell(3).setCellValue(association.getNumberValue()); // 报告编号
            row.createCell(4).setCellValue(association.getProjectName()); // 项目名称
            row.createCell(5).setCellValue(association.getWorkDate()); // 工作日期
            row.createCell(6).setCellValue(association.getProjectCategoryName()); // 抵押物性质
            row.createCell(7).setCellValue(association.getSeat()); // 抵押物地址
            row.createCell(8).setCellValue(String.valueOf(association.getArea() == null ? "" : association.getArea())); // 面积
            row.createCell(9).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 评估金额
            row.createCell(10).setCellValue(association.getMethodNames()); // 评估方法
            row.createCell(11).setCellValue(association.getAppraiser()); // 评估方法
            row.createCell(12).setCellValue(String.valueOf(association.getLoan() == null ? "" : association.getLoan())); // 贷款金额
            row.createCell(13).setCellValue(association.getPledgeRatio()); // 抵押率
            row.createCell(14).setCellValue(String.valueOf(association.getChargePrice() == null ? "" : association.getChargePrice())); // 收费金额
            row.createCell(15).setCellValue(association.getAssessCompany()); // 评估公司
            row.createCell(16).setCellValue(association.getRemark()); // 备注
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "恒丰银行.XLS");
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
