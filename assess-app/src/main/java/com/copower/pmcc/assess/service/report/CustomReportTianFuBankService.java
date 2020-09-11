package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportTianFuBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportTianFuBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
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
public class CustomReportTianFuBankService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private InitiateContactsDao initiateContactsDao;
    @Autowired
    private AdRpcQualificationsService adRpcQualificationsService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private CustomReportTianFuBankMapper customReportTianFuBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;


    public BootstrapTableVo getCustomReportTianFuBankList(String numberValue, String unitName, Integer reportType, String queryStartDate,
                                                          String queryPreviewsEndDate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Date startDate = null;
        if (StringUtils.isNotEmpty(queryStartDate)) {
            startDate = DateUtils.parse(queryStartDate);
        }
        Date endDate = null;
        if (StringUtils.isNotEmpty(queryPreviewsEndDate)) {
            endDate = DateUtils.parse(queryPreviewsEndDate);
        }
        List<CustomReportTianFuBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportTianFuBankMapper.getCustomReportTianFuBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportTianFuBankMapper.getCustomReportTianFuBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        List<CustomReportTianFuBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportTianFuBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportTianFuBank>() : vos);
        return vo;
    }

    public CustomReportTianFuBank getCustomReportTianFuBank(CustomReportTianFuBank data) {
        if (data == null) {
            return null;
        }
        CustomReportTianFuBank vo = new CustomReportTianFuBank();
        BeanUtils.copyProperties(data, vo);
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

        if (data.getAreaId() != null && data.getAreaId() != 0) {
            //借款金额
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject=judgeObjectList.get(0);
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(schemeJudgeObject);
                if (evaluationArea!= null && schemeJudgeObject.getPrice() != null) {
                    vo.setAssessTotal(evaluationArea.multiply(schemeJudgeObject.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //评估标的
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        //经办客户经理（使用单位联系人）
        InitiateUnitInformationVo unitInformationVo = initiateUnitInformationService.getDataByProjectId(data.getProjectId());
        List<InitiateContacts> contactsList = initiateContactsDao.getList(unitInformationVo.getId(), 3, null, null, null);
        if (CollectionUtils.isNotEmpty(contactsList)) {
            vo.setCustomerManager(contactsList.get(0).getcName());
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

        List<CustomReportTianFuBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportTianFuBankMapper.getCustomReportTianFuBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportTianFuBankMapper.getCustomReportTianFuBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportTianFuBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportTianFuBank(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"评估时间", "所属支行", "客户名称", "借款金额", "抵押物品种",
                "评估机构", "评估报告编号", "评估费金额", "经办客户经理", "机构负责人", "入账日期"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportTianFuBank tianFuBank = vos.get(i);
            row.createCell(0).setCellValue(DateUtils.format(tianFuBank.getValuationDate(), DateUtils.DATE_PATTERN)); // 评估时间
            row.createCell(1).setCellValue(tianFuBank.getUnitName()); // 所属支行
            row.createCell(2).setCellValue(tianFuBank.getConsignor()); // 客户名称
            row.createCell(3).setCellValue(String.valueOf(tianFuBank.getAssessTotal() == null ? "" : tianFuBank.getAssessTotal())); // 借款金额
            row.createCell(4).setCellValue(tianFuBank.getProjectCategoryName()); // 抵押物品种
            row.createCell(5).setCellValue(tianFuBank.getAssessOrganization()); // 评估机构
            row.createCell(6).setCellValue(tianFuBank.getNumberValue()); // 评估报告编号
            row.createCell(7).setCellValue(String.valueOf(tianFuBank.getAssessCost() == null ? "" : tianFuBank.getAssessCost())); // 评估费金额
            row.createCell(8).setCellValue(tianFuBank.getCustomerManager()); // 经办客户经理
            row.createCell(9).setCellValue(tianFuBank.getPrincipal()); // 机构负责人
            row.createCell(10).setCellValue(DateUtils.format(tianFuBank.getEnterTieme(), DateUtils.DATE_PATTERN)); // 入账日期
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "天府银行.XLS");
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
