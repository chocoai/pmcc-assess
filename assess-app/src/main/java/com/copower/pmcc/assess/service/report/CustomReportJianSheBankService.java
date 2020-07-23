package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportJianSheBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportJianSheBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
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
public class CustomReportJianSheBankService {
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
    private CustomReportJianSheBankMapper customReportJianSheBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;


    public BootstrapTableVo getCustomReportJianSheBankList(String numberValue, String unitName, Integer reportType, String queryStartDate,
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

        List<CustomReportJianSheBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportJianSheBankMapper.getCustomReportJianSheBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportJianSheBankMapper.getCustomReportJianSheBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }
        List<CustomReportJianSheBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportJianSheBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportJianSheBank>() : vos);
        return vo;
    }

    public CustomReportJianSheBank getCustomReportJianSheBank(CustomReportJianSheBank data) {
        if (data == null) {
            return null;
        }
        CustomReportJianSheBank vo = new CustomReportJianSheBank();
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
            //评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                vo.setArea(judgeObjectList.get(0).getEvaluationArea());
                vo.setAssessPrice(judgeObjectList.get(0).getPrice());

                if (judgeObjectList.get(0).getEvaluationArea() != null && judgeObjectList.get(0).getPrice() != null) {
                    vo.setAssessTotal(judgeObjectList.get(0).getEvaluationArea().multiply(judgeObjectList.get(0).getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    //估价对象坐落位置
                    vo.setSeat(judgeObjectList.get(0).getSeat());
                    BasicApply basicApply =  basicApplyService.getByBasicApplyId(judgeObjectList.get(0).getBasicApplyId());
                    if (basicApply != null) {
                        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                        if (basicEstate != null) {
                            vo.setEstateName(basicEstate.getName());
                        }
                    }

                }
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //抵押人(占有人)
        InitiatePossessorVo possessorVo = initiatePossessorService.getDataByProjectId(data.getProjectId());
        String pledger = StringUtils.isNotBlank(possessorVo.getpName()) ? possessorVo.getpName() : possessorVo.getpEntrustmentUnit();
        vo.setPledger(pledger);
        //业务类型
        vo.setLoanTypeName(baseDataDicService.getNameById(data.getLoanType()));
        //评估类型
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
        //注册号
        if (StringUtils.isNotEmpty(data.getRealEstateAppraiser())) {
            List<String> names = FormatUtils.transformString2List(data.getRealEstateAppraiser());
            try {
                StringBuilder s = new StringBuilder();
                for (String account : names) {
                    List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, data.getQualificationType());
                    if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                        s.append(publicService.getUserNameByAccount(account)).append(adPersonalQualificationDtoList.get(0).getCertificateNo()).append("、");
                    }
                }
                s.deleteCharAt(s.length() - 1);
                vo.setAppraiser(s.toString());
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "注册号");
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
            endDate = DateUtils.addDay(endDate, 1);
        }

        List<CustomReportJianSheBank> customNumberRecordList = null;
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();
        if (resultId.equals(reportType)) {
            customNumberRecordList = customReportJianSheBankMapper.getCustomReportJianSheBankList(numberValue, unitName, reportType, consultationId, startDate, endDate);
        } else {
            customNumberRecordList = customReportJianSheBankMapper.getCustomReportJianSheBankList(numberValue, unitName, reportType, null, startDate, endDate);
        }

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportJianSheBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportJianSheBank(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"经办行", "委托人", "抵押人", "业务类型", "评估类型",
                "估价对象所处区域", "小区名称", "估价对象坐落位置",
                "业务受理时间", "预评出具时间", "报告出具时间", "面积",
                "评估总价", "评估单价", "评估费用", "评估机构", "评估师", "备注", "报告文号"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportJianSheBank association = vos.get(i);
            row.createCell(0).setCellValue(association.getUnitName()); // 经办行
            row.createCell(1).setCellValue(association.getConsignor()); // 委托企业名称
            row.createCell(2).setCellValue(association.getPledger()); // 抵押人
            row.createCell(3).setCellValue(association.getLoanTypeName()); // 业务类型
            row.createCell(4).setCellValue(association.getProjectCategoryName()); // 评估类型
            row.createCell(5).setCellValue(association.getAreaName()); // 估价对象所处区域
            row.createCell(6).setCellValue(association.getEstateName()); // 小区名称
            row.createCell(7).setCellValue(association.getSeat()); // 估价对象坐落位置
            row.createCell(8).setCellValue(DateUtils.format(association.getHandleTieme(), DateUtils.DATE_PATTERN)); // 业务受理时间
            row.createCell(9).setCellValue(DateUtils.format(association.getPreauditNumberDate(), DateUtils.DATE_PATTERN)); // 预评出具时间
            row.createCell(10).setCellValue(DateUtils.format(association.getReportIssuanceDate(), DateUtils.DATE_PATTERN)); // 报告出具时间
            row.createCell(11).setCellValue(String.valueOf(association.getArea() == null ? "" : association.getArea())); // 面积
            row.createCell(12).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 评估总价
            row.createCell(13).setCellValue(String.valueOf(association.getAssessPrice() == null ? "" : association.getAssessPrice())); // 评估单价
            row.createCell(14).setCellValue(String.valueOf(association.getAssessCost() == null ? "" : association.getAssessCost())); // 评估费用
            row.createCell(15).setCellValue(association.getAssessOrganization()); // 评估机构
            row.createCell(16).setCellValue(association.getAppraiser()); // 评估师
            row.createCell(17).setCellValue(association.getRemark()); // 备注
            row.createCell(18).setCellValue(association.getNumberValue()); // 文号
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "建设银行.XLS");
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
