package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportGongShangBank;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportGongShangBankMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
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
public class CustomReportGongShangBankService {
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
    private CustomReportGongShangBankMapper customReportGongShangBankMapper;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private InitiatePossessorService initiatePossessorService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;
    @Autowired
    private InitiateContactsDao initiateContactsDao;


    public BootstrapTableVo getCustomReportGongShangBankList(String numberValue, String unitName, Integer reportType,String queryPreviewsStartDate,
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
        List<CustomReportGongShangBank> customNumberRecordList = customReportGongShangBankMapper.getCustomReportGongShangBankList(numberValue, unitName, reportType, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        List<CustomReportGongShangBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportGongShangBank(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportGongShangBank>() : vos);
        return vo;
    }

    public CustomReportGongShangBank getCustomReportGongShangBank(CustomReportGongShangBank data) {
        if (data == null) {
            return null;
        }
        CustomReportGongShangBank vo = new CustomReportGongShangBank();
        BeanUtils.copyProperties(data, vo);
        //客户经理（使用单位联系人）
        InitiateUnitInformationVo unitInformationVo = initiateUnitInformationService.getDataByProjectId(data.getProjectId());
        List<InitiateContacts> contactsList = initiateContactsDao.getList(unitInformationVo.getId(), 3, null, null, null);
        if (CollectionUtils.isNotEmpty(contactsList)) {
            vo.setPhone(contactsList.get(0).getcPhone());
            vo.setClientManager(contactsList.get(0).getcName());
        }
        if (data.getAreaId() != null && data.getAreaId() != 0) {
            //评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                if (judgeObjectList.get(0).getEvaluationArea() != null && judgeObjectList.get(0).getPrice() != null) {
                    vo.setAssessTotal(judgeObjectList.get(0).getEvaluationArea().multiply(judgeObjectList.get(0).getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        //评估标的
        vo.setProjectCategoryName(baseProjectClassifyService.getNameById(data.getProjectCategoryId()));
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
            vo.setPreviewsNumber(vo.getNumberValue());
            List<String> consultationNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), consultationId);
            if (CollectionUtils.isNotEmpty(consultationNumberList)) {
                vo.setResultNumber(consultationNumberList.get(0));
            }
            List<String> resultNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), resultId);
            if (CollectionUtils.isNotEmpty(resultNumberList)) { ;
                vo.setResultNumber(resultNumberList.get(0));
            }
        } else if (vo.getReportType().equals(resultId) || vo.getReportType().equals(consultationId)) {
            vo.setResultNumber(vo.getNumberValue());
            List<String> preauditNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), preauditId);
            if (CollectionUtils.isNotEmpty(preauditNumberList)) {
                vo.setPreviewsNumber(preauditNumberList.get(0));
            }
        } else {
            List<String> preauditNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), preauditId);
            if (CollectionUtils.isNotEmpty(preauditNumberList)) {
                vo.setPreviewsNumber(preauditNumberList.get(0));
            }
            List<String> consultationNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), consultationId);
            if (CollectionUtils.isNotEmpty(consultationNumberList)) {
                vo.setResultNumber(consultationNumberList.get(0));
            }
            List<String> resultNumberList = projectNumberRecordService.getReportNumberByArea(vo.getProjectId(), vo.getAreaId(), resultId);
            if (CollectionUtils.isNotEmpty(resultNumberList)) {
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
    public void export(HttpServletResponse response, String numberValue, String unitName, Integer reportType,String queryPreviewsStartDate,
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
        List<CustomReportGongShangBank> customNumberRecordList = customReportGongShangBankMapper.getCustomReportGongShangBankList(numberValue, unitName, reportType, previewsStartDate, previewsEndDate, resultStartDate, resultEndDate);
        List<CustomReportGongShangBank> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportGongShangBank(o));

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题
        String[] title = {"支行", "工行系统最终推送日期", "工行系统推送项目编号", "客户经理", "联系电话",
                "委托方", "评估标的", "现场查看时间", "评估价值", "预估号",
                "报告号", "收费金额", "开票时间", "备注"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportGongShangBank association = vos.get(i);
            row.createCell(0).setCellValue(association.getUnitName()); // 支行
            row.createCell(1).setCellValue(DateUtils.format(association.getPushTime(), DateUtils.DATE_PATTERN)); // 工行系统最终推送日期
            row.createCell(2).setCellValue(association.getPushNumber()); // 工行系统推送项目编号
            row.createCell(3).setCellValue(association.getClientManager()); // 客户经理
            row.createCell(4).setCellValue(association.getPhone()); // 联系电话
            row.createCell(5).setCellValue(association.getConsignor()); // 委托方
            row.createCell(6).setCellValue(association.getProjectCategoryName()); // 评估标的
            row.createCell(7).setCellValue(DateUtils.format(association.getCheckTime(), DateUtils.DATE_PATTERN));//现场查看时间
            row.createCell(8).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 评估价值
            row.createCell(9).setCellValue(association.getPreviewsNumber()); // 预估号
            row.createCell(10).setCellValue(association.getResultNumber()); // 报告号
            row.createCell(11).setCellValue(String.valueOf(association.getAssessCost() == null ? "" : association.getAssessCost())); // 收费金额
            row.createCell(12).setCellValue(DateUtils.format(association.getMakeOutDate(), DateUtils.DATE_PATTERN)); // 开票时间
            row.createCell(13).setCellValue(association.getRemark()); // 备注
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "工商银行.XLS");
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
