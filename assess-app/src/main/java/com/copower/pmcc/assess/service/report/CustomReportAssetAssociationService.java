package com.copower.pmcc.assess.service.report;

import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomReportAssetAssociation;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomReportAssetAssociationMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
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

/**
 * Created by kings on 2019-1-14.
 */
@Service
public class CustomReportAssetAssociationService {
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
    private CustomReportAssetAssociationMapper customReportAssetAssociationMapper;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;


    public BootstrapTableVo getCustomReportAssetAssociationList(String projectName, String numberValue, String unitName,
                                                                String queryStartDate, String queryEndDate, Integer limit, Integer offset) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(offset, limit);
        Date startDate = null;
        if (StringUtils.isNotEmpty(queryStartDate)) {
            startDate = DateUtils.parse(queryStartDate);
        }
        Date endDate = null;
        if (StringUtils.isNotEmpty(queryEndDate)) {
            endDate = DateUtils.parse(queryEndDate);
            endDate = DateUtils.addDay(endDate, 1);
        }
        List<CustomReportAssetAssociation> customNumberRecordList = null;
        //资产
        BaseProjectClassify projectCategory = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.COMPREHENSIVE_ASSETS_TYPE);
        Integer projectCategoryId = projectCategory.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();

        customNumberRecordList = customReportAssetAssociationMapper.getCustomReportAssetAssociationList(projectName, projectCategoryId, resultId, numberValue, unitName, startDate, endDate);

        List<CustomReportAssetAssociation> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportAssetAssociation(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CustomReportAssetAssociation>() : vos);
        return vo;
    }

    public CustomReportAssetAssociation getCustomReportAssetAssociation(CustomReportAssetAssociation data) {
        if (data == null) {
            return null;
        }
        CustomReportAssetAssociation vo = new CustomReportAssetAssociation();
        BeanUtils.copyProperties(data, vo);
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setReportType(resultId);
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

        if (data.getServiceComeFrom() != null) {
            vo.setServiceComeFromName(baseDataDicService.getNameById(data.getServiceComeFrom()));
        }
        if (data.getAreaId() != null && data.getAreaId() != 0) {
            List<SchemeJudgeFunction> functionList = schemeJudgeFunctionService.getApplicableJudgeFunctionsByAreaId(data.getAreaId());
            List<String> methods = LangUtils.transform(functionList, o -> o.getName());
            //去重
            String districtmethods = publicService.districtString(methods);
            //获取方法
            vo.setMethodNames(districtmethods);
            //获取面积，评估总价
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(data.getAreaId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(0);
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(schemeJudgeObject);
                vo.setEvaluationArea(schemeJudgeObject.getEvaluationArea());
                if (evaluationArea != null && schemeJudgeObject.getPrice() != null) {
                    vo.setAssessTotal(evaluationArea.multiply(schemeJudgeObject.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //单价
                vo.setPrice(schemeJudgeObject.getPrice());
                //坐落
                vo.setSeat(schemeJudgeObject.getSeat());
            }
        }
        //委托人
        InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(data.getProjectId());
        String principalStr = StringUtils.isNotBlank(consignorVo.getCsName()) ? consignorVo.getCsName() : consignorVo.getCsEntrustmentUnit();
        vo.setConsignor(principalStr);
        List<InitiateContacts> contactsList = initiateContactsDao.getList(consignorVo.getId(), 1, null, null, null);
        if (CollectionUtils.isNotEmpty(contactsList)) {
            vo.setCsPhnoe(contactsList.get(0).getcPhone());
            vo.setCsPostcode(contactsList.get(0).getcEmail());
        }
        //第一报告人
        if (StringUtils.isNotEmpty(data.getRealEstateAppraiser())) {
            List<String> names = FormatUtils.transformString2List(data.getRealEstateAppraiser());
            vo.setFirstAppraiser(publicService.getUserNameByAccount(names.get(0)));
            //第一报告人注册号
            try {
                List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(names.get(0), data.getQualificationType());
                if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList))
                    vo.setFirstRegistrationNumber(adPersonalQualificationDtoList.get(0).getCertificateNo());
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "获取第一报告人注册号");
            }
            //注册号
            try {
                if (names.size() > 1) {
                    List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(names.get(1), data.getQualificationType());
                    if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList))
                        vo.setRegistrationNumber(adPersonalQualificationDtoList.get(0).getCertificateNo());
                }
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "获取注册号");
            }
            //获取其他估价师，移除第一个
            names.remove(0);
            if (names.size() > 0) {
                String accountList = publicService.getUserNameByAccountList(names);
                vo.setParticipationAppraiser(accountList);
            }
        }

        return vo;
    }

    /**
     * 导出
     *
     * @param response
     */
    public void export(HttpServletResponse response, String projectName, String numberValue, String unitName,
                       String queryStartDate, String queryEndDate) throws BusinessException, IOException {
        //获取数据
        Date startDate = null;
        if (StringUtils.isNotEmpty(queryStartDate)) {
            startDate = DateUtils.parse(queryStartDate);
        }
        Date endDate = null;
        if (StringUtils.isNotEmpty(queryEndDate)) {
            endDate = DateUtils.parse(queryEndDate);
        }

        List<CustomReportAssetAssociation> customNumberRecordList = null;
        //资产
        BaseProjectClassify projectCategory = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.COMPREHENSIVE_ASSETS_TYPE);
        Integer projectCategoryId = projectCategory.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();

        customNumberRecordList = customReportAssetAssociationMapper.getCustomReportAssetAssociationList(projectName, projectCategoryId, resultId, numberValue, unitName, startDate, endDate);

        if (CollectionUtils.isEmpty(customNumberRecordList)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        List<CustomReportAssetAssociation> vos = LangUtils.transform(customNumberRecordList, o -> getCustomReportAssetAssociation(o));
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题

        String[] title = {"估价报告编号", "项目名称", "业务来源", "估价目的", "估价方法",
                "估价作业开始日", "估价作业结束日", "估价时点", "评估总值（万元）", "评估单价（元）", "估价对象位置",
                "委托人", "委托人地址", "委托人邮编", "委托人电话",
                "第一报告人", "第一报告人注册号", "参与报告人", "注册号", "收费"};
        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }

        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            CustomReportAssetAssociation association = vos.get(i);
            row.createCell(0).setCellValue(association.getNumberValue()); // 估价报告编号
            row.createCell(1).setCellValue(association.getProjectName()); // 项目名称
            row.createCell(2).setCellValue(association.getServiceComeFromName()); // 业务来源
            row.createCell(3).setCellValue(association.getEntrustPurposeName()); // 估价目的
            row.createCell(4).setCellValue(association.getMethodNames()); // 估价方法
            row.createCell(5).setCellValue(DateUtils.format(association.getInvestigationsStartDate(), DateUtils.DATE_PATTERN)); // 估价作业开始日
            row.createCell(6).setCellValue(DateUtils.format(association.getHomeWorkEndTime(), DateUtils.DATE_PATTERN)); // 估价作业结束日
            row.createCell(7).setCellValue(DateUtils.format(association.getValuationDate(), DateUtils.DATE_PATTERN)); // 估价时点
            row.createCell(8).setCellValue(String.valueOf(association.getAssessTotal() == null ? "" : association.getAssessTotal())); // 评估总值
            row.createCell(9).setCellValue(String.valueOf(association.getPrice() == null ? "" : association.getPrice())); // 评估单价
            row.createCell(10).setCellValue(association.getSeat()); // 估价对象位置
            row.createCell(11).setCellValue(association.getConsignor()); // 委托人
            row.createCell(12).setCellValue(association.getCsAddress()); // 委托人地址
            row.createCell(13).setCellValue(association.getCsPostcode()); // 委托人邮编
            row.createCell(14).setCellValue(association.getCsPhnoe()); // 委托人电话
            row.createCell(15).setCellValue(association.getFirstAppraiser()); // 第一报告人
            row.createCell(16).setCellValue(association.getFirstRegistrationNumber()); // 第一报告人注册号
            row.createCell(17).setCellValue(association.getParticipationAppraiser()); // 参与报告人
            row.createCell(18).setCellValue(association.getRegistrationNumber()); // 注册号
            row.createCell(19).setCellValue(association.getContractPrice()); // 收费
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "中国评协绩报表.XLS");
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
