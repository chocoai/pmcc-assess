package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.SurveyTaxesPaymentDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataReportAnalysisRiskService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;


    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_RISK);
            dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(dataReportAnalysis.getId(), SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_RISK.getKey());
        }
    }

    /**
     * 获取上报告的风险提示数据
     *
     * @return
     */
    public String getReportRisk(Integer areaGroupId) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_RISK);
        if (baseDataDic == null) return "";
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisService.getReportAnalysisList(baseDataDic.getId(),areaGroupId);
        if (CollectionUtils.isEmpty(reportAnalysisList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        //对应委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            //风险分析税费缴纳
            if (AssessReportFieldConstant.RISK_ANALYSIS_TAXES_PAYMENT.equals(dataReportAnalysis.getFieldName())) {
                //缴纳正常委估对象
                ArrayList<Integer> paymentNormal = Lists.newArrayList();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    StringBuilder damageContent = new StringBuilder();
                    ArrayList<Integer> paymentAbnormality = Lists.newArrayList();
                    if (judgeObject.getDeclareRecordId() == null || judgeObject.getDeclareRecordId() == 0) {
                        continue;
                    }
                    List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
                    if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                        continue;
                    }
                    //对应资产清查内容
                    for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                        if (!"不正常".equals(surveyAssetInventory.getPaymentStatus())) {
                            if (NumberUtils.isNumber(judgeObject.getNumber())) {
                                paymentNormal.add(Integer.valueOf(judgeObject.getNumber()));
                            }
                        } else {

                            if (NumberUtils.isNumber(judgeObject.getNumber())) {
                                paymentAbnormality.add(Integer.valueOf(judgeObject.getNumber()));
                            }

                            damageContent.append(generateCommonMethod.convertNumber(paymentAbnormality)).append("号估价对象税费缴纳不正常，");
                            List<SurveyTaxesPaymentDto> dataList = JSON.parseArray(surveyAssetInventory.getPaymentContent(), SurveyTaxesPaymentDto.class);
                            if (CollectionUtils.isNotEmpty(dataList)) {
                                for (SurveyTaxesPaymentDto dto : dataList) {
                                    damageContent.append(dto.getProjectName()).append(dto.getRemark()).append(dto.getMoney()).append("元;");
                                }
                                damageContent.deleteCharAt(damageContent.length() - 1);
                                damageContent.append("。");
                            }
                        }
                    }


                    if (StringUtils.isNotBlank(damageContent)) {
                        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, dataReportAnalysis.getName())));
                        stringBuilder.append(dataReportAnalysis.getTemplate());

                        DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.RISK_ANALYSIS_TAXES_PAYMENT_SURVEY);
                        stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{税费缴纳调查}", damageContent)).append("</p>");
                    }
                }
//                if (paymentNormal != null) {
//                    String number = generateCommonMethod.convertNumber(paymentNormal);
//                    StringBuilder content = new StringBuilder();
//                    content.append(number).append("号估价对象缴纳税费正常。");
//                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.RISK_ANALYSIS_TAXES_PAYMENT_SURVEY);
//                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{税费缴纳调查}", content)).append("</p>");
//                }
            } else {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, dataReportAnalysis.getName())));
                stringBuilder.append(dataReportAnalysis.getTemplate());
            }
        }
        return generateCommonMethod.getWarpCssHtml(stringBuilder.toString());
    }
}
