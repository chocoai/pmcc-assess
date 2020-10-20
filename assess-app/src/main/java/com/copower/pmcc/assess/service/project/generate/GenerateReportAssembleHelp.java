package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.common.enums.report.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by zch on 2020-4-14.
 * 组装报告字段map
 */
public final class GenerateReportAssembleHelp {


    /**
     * 司法 报告字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @throws Exception
     */
    public static boolean assembleSifaMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //司法估价项目名称
        if (Objects.equal(ReportFieldSifaEnum.SifaValuationProjectName.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName2());
        }
        return false;
    }

    /**
     * 工商银行  报告字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @throws Exception
     */
    public static boolean assembleGongshangMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //工行估价案例情况表
        if (Objects.equal(ReportFieldGongshangBankEnum.GongshangICBCValuationCaseInformationSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getICBCValuationCaseInformationSheet());
        }
        //区位分析
        if (Objects.equal(ReportFieldGongshangBankEnum.GongshangDistrict_Analysis.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getICBCDistrictAnalysisSheet());
        }
        return false;
    }


    /**
     * 建设银行 报告字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @throws Exception
     */
    public static boolean assembleJiansheMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {

        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheNetAssessmentOne.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(name));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheNetAssessmentTwo.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentNumber(name));
        }

        //建行预评数据表格
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheCCB_Pre_Evaluation_Data_Form.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCCB_Pre_Evaluation_Data_FormSheet());
        }

        //档案保管号
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheArchivesDepositNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
        }

        //户型
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheunitType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordUnitType());
        }
        //装修状况
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheDecorationStatus.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDecorationStatus());
        }
        //评估总价分述
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheAssessPriceClassification.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPriceClassification());
        }


        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheCertificationPurpose.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(false));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheSetUse.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(false));
        }
        if (Objects.equal(ReportFieldJiansheBankEnum.JiansheHotTipBank.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip2(2));
        }
        return false;
    }

    /**
     * 公共字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     */
    public static boolean assembleCommonMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //文号
        if (Objects.equal(ReportFieldCommonEnum.CommonReportNumber.getName(), name)) {
            if (Objects.equal(reportGroup.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber());
            }
        }
        //查询码
        if (Objects.equal(ReportFieldCommonEnum.CommonQueryCode.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateReportInfo.getQueryCode());
        }
        //备案号
        if (Objects.equal(ReportFieldCommonEnum.CommonRecordNo.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateReportInfo.getRecordNo());
        }
        //备案日期
        if (Objects.equal(ReportFieldCommonEnum.CommonRecordDate.getName(), name)) {
            String reportIssuanceStr = "";
            if (generateReportInfo.getRecordDate() != null) {
                reportIssuanceStr = DateUtils.format(generateReportInfo.getRecordDate(), DateUtils.DATE_CHINESE_PATTERN);
            }
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
        }
        //报告编号
        if (Objects.equal(ReportFieldCommonEnum.CommonReportNumbering.getName(), name)) {
            if (Objects.equal(reportGroup.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getWordNumber2());
            }
        }
        //报告二维码
        if (Objects.equal(ReportFieldCommonEnum.CommonReportQrcode.getName(), name)) {
            if (Objects.equal(reportGroup.getSymbolOperation(), ReportSymbolOperationEnum.GET.getKey())) {
                return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportQrcode(generateReportInfo, reportType.getFieldName(), reportGroup));
            }
        }
        //报告类别
        if (Objects.equal(ReportFieldCommonEnum.CommonReportingCategories.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportType.getName());
        }
        //报告出具日期
        if (Objects.equal(ReportFieldCommonEnum.CommonReportIssuanceDate.getName(), name)) {
            String reportIssuanceStr = null;
            if (generateReportInfo.getReportIssuanceDate() != null) {
                reportIssuanceStr = DateUtils.format(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN);
            } else {
                reportIssuanceStr = DateUtils.format(new Date(), DateUtils.DATE_CHINESE_PATTERN);
            }
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, reportIssuanceStr);
        }
        //协助工作人员
        if (Objects.equal(ReportFieldCommonEnum.CommonAssistanceStaff.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssistanceStaff(generateReportInfo));
        }


        //特别提示
        if (Objects.equal(ReportFieldCommonEnum.CommonHotTip.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHotTip2(0));
        }

        //作业结束时间
        if (Objects.equal(ReportFieldCommonEnum.CommonHomeWorkEndTime.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkEndTime(generateReportInfo.getHomeWorkEndTime()));
        }
        //作业开始时间
        if (Objects.equal(ReportFieldCommonEnum.CommonHomeWorkStartTime.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHomeWorkStartTime(generateReportInfo.getHomeWorkStartTime()));
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonInvestigationsStartDate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getConversionTime(generateReportInfo.getInvestigationsStartDate()));
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonInvestigationsEndDate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getConversionTime(generateReportInfo.getInvestigationsEndDate()));
        }

        //机构住所
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationAddress.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationAddress();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构名称
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationName.getName(), name)
                || Objects.equal(ReportFieldCommonEnum.CommonXIEHE_RealEstateValuationAgencyHouse.getName(), name)
                || Objects.equal(ReportFieldCommonEnum.CommonXIEHE_RealEstateValuationAgency.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationName();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构名称法定代表人
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_legalRepresentative.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getLegalRepresentative();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构工商注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_registeredNo.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getRegisteredNo();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构资质等级
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationRank.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getOrganizationRank();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //机构证书号
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_certificateNo.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getCertificateNo();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //证书有效期
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_certificateEffectiveDate.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getCertificateEffectiveDate();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_registrationDate.getName(), name)) {
            AdCompanyQualificationDto qualificationForPractising = generateBaseDataService.getCompanyQualificationForPractising();
            String value = qualificationForPractising == null ? "" : qualificationForPractising.getRegistrationDate();
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, value);
        }
        //房地产估价机构信息
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationHouseInfo.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo());
        }
        //估价机构信息
        if (Objects.equal(ReportFieldCommonEnum.CommonXIEHE_organizationInfo.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getXIEHE_organizationInfo());
        }

        //注册房产估价师及注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredHouseRealEstateValuerAndNumber.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportInfo));
        }
        //注册估价师及注册号
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredRealEstateValuerAndNumber.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerAndNumber(generateReportInfo));
        }
        //注册估价师
        if (Objects.equal(ReportFieldCommonEnum.CommonRegisteredRealEstateValuer.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuer(generateReportInfo));
        }


        //价值类型
        if (Objects.equal(ReportFieldCommonEnum.CommonValueType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueType());
        }

        //价值类型描述
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTypeDesc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTypeDesc());
        }
        //设定用途
        if (Objects.equal(ReportFieldCommonEnum.CommonSetUse.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
        }


        //证载用途
        if (Objects.equal(ReportFieldCommonEnum.CommonCertificationPurpose.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeparationCertificateUses(true));
        }


        //土地 实际用途
        if (Objects.equal(ReportFieldCommonEnum.CommonPracticalUse.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPracticalUse());
        }


        //评估总价
        if (Objects.equal(ReportFieldCommonEnum.CommonAssessTotal.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotal());
        }
        //评估总价大写
        if (Objects.equal(ReportFieldCommonEnum.CommonAssessTotalRMB.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessAssessTotalAssessTotalRMB());
        }


        //当前年份
        if (Objects.equal(ReportFieldCommonEnum.CommonThisYear.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getThisYear());
        }

        //委托单位
        if (Objects.equal(ReportFieldCommonEnum.CommonEntrustedUnit.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEntrustmentUnit());
        }


        //委托目的
        if (Objects.equal(ReportFieldCommonEnum.CommonDelegatePurpose.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDelegatePurpose());
        }
        //委托目的表述
        if (Objects.equal(ReportFieldCommonEnum.CommonStatementPurposeEntrustment.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatementPurposeEntrustment());
        }
        //评估方法 ,估价对象评估方法
        if (Objects.equal(ReportFieldCommonEnum.CommonEvaluationMethod.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethod());
        }
        //公共评估方法值
        if (Objects.equal(ReportFieldCommonEnum.CommonEvaluationMethodValue.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodValue());
        }

        //价值时点
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTimePoint.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePoint());
        }
        //价值时点说明
        if (Objects.equal(ReportFieldCommonEnum.CommonValueTimePointRemark.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValueTimePointRemark());
        }

        //估价技术思路
        if (Objects.equal(ReportFieldCommonEnum.CommonEvaluationThink.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationThink());
        }

        //法定优先受偿款总金额
        if (Objects.equal(ReportFieldCommonEnum.CommonStatutoryPriorityAmountTotal.getName(), name)) {
            BigDecimal bigDecimal = generateBaseDataService.getSchemeReimbursementKnowTotalPrice();
            if (bigDecimal.doubleValue() > 0) {
                bigDecimal = bigDecimal.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, bigDecimal.toString());
        }

        //估价对象描述
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalDescribe.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDescribe());
        }
        //估价信息描述
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalDataDescribe.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalDataDescribe());
        }

        //委托人 估价委托人
        if (Objects.equal(ReportFieldCommonEnum.CommonPRINCIPAL.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipal());
        }
        // 估价委托人信息
        if (Objects.equal(ReportFieldCommonEnum.CommonPrincipalInfo.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalInfo(false));
        }

        //估价项目名称
        if (Objects.equal(ReportFieldCommonEnum.CommonValuationProjectName.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuationProjectName());
        }

        //报告使用单位
        if (Objects.equal(ReportFieldCommonEnum.CommonReportUnitString.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportUnitString());
        }

        if (Objects.equal(ReportFieldCommonEnum.CommonParcelInnerDevelop.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonParcelInnerDevelopValue());
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonParcelOuterDevelop.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonParcelOuterDevelopValue());
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonParcelSettingInnerDevelop.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonParcelSettingInnerDevelopValue());
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonParcelSettingOuterDevelop.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, "无");
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonPropertyScope.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonPropertyScopeValue());
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonScopeNotInclude.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonScopeNotInclude());
        }
        if (Objects.equal(ReportFieldCommonEnum.CommonScopeInclude.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCommonScopeInclude());
        }
        return false;
    }

    /**
     * 原始报告字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @throws Exception
     */
    public static boolean assembleBaseMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //估价对象号
        if (Objects.equal(ReportFieldEnum.JudgeObjectNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectNumberMethod());
        }
        if (Objects.equal(ReportFieldEnum.JudgeObjectWeights.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectWeights());
        }
        //财产范围说明
        if (Objects.equal(ReportFieldEnum.ScopePropertyExplain.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getScopePropertyExplain());
        }
        //估价对象权属
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectSheet.getName(), name)) {
            return putValue(false, false, true, null, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectSheet());
        }
        //估价对象权属明细清单
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectCheckListSheet.getName(), name)) {
            return putValue(false, false, true, null, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectSheetCheckList());
        }
        //权属证号
        if (Objects.equal(ReportFieldEnum.EquityStatusObjectNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEquityStatusObjectNumber());
        }
        //分类评估方法结果
        if (Objects.equal(ReportFieldEnum.EvaluationMethodResult.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEvaluationMethodResult());
        }
        //清查资料
        if (Objects.equal(ReportFieldEnum.AssetInventoryFile.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssetInventoryFile());
        }
        if (Objects.equal(ReportFieldEnum.DeclareRecordFile.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordFile());
        }
        //估价委托书复印件
        if (Objects.equal(ReportFieldEnum.JudgeObjectPrincipalCopySheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJUDGEOBJECTPRINCIPALCOPYSHEET());
        }
        //估计对象位置示意图
        if (Objects.equal(ReportFieldEnum.EstimatedObjectLocationDiagram.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstimatedObjectLocationDiagram());
        }
        //估价对象实况照片
        if (Objects.equal(ReportFieldEnum.Valuation_Target_Live_Photos.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getValuation_Target_Live_Photos());
        }
        //估价对象权属证明复印件
        if (Objects.equal(ReportFieldEnum.Copies_the_Ownership_Certificate_the_Valuation_Object.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopies_the_Ownership_Certificate_the_Valuation_Object());
        }
        //估价中引用的专用文件资料
        if (Objects.equal(ReportFieldEnum.Special_documentation_referenced_in_valuation.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSpecial_documentation_referenced_in_valuation());
        }
        //相关参数选取与应用
        if (Objects.equal(ReportFieldEnum.SelectionApplicationParameters.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionApplicationParameters());
        }
        //主要计算过程
        if (Objects.equal(ReportFieldEnum.ComputationProcess.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getComputationProcess());
        }
        //估价对象市场价值的确定
        if (Objects.equal(ReportFieldEnum.DeterminationMarketValueValuationObject.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeterminationMarketValueValuationObject());
        }
        //估价结果一览表
        if (Objects.equal(ReportFieldEnum.JudgeBuildResultSurveySheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildResultSurveySheet());
        }
        //估价对象区位状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectAreaStatusSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectAreaStatusSheet());
        }
        //估价土地实体状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectLandStateSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLandStateSheet());
        }
        //估价对象建筑实体状况表
        if (Objects.equal(ReportFieldEnum.JudgeBuildLandStateSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeBuildLandStateSheet());
        }
        //估价对象权益状况表
        if (Objects.equal(ReportFieldEnum.JudgeObjectEquitySheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectEquitySheet());
        }
        //估价对象因素表
        if (Objects.equal(ReportFieldEnum.JudgeObjectFactorSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectFactorSheet());
        }
        //汇总表
        if (Objects.equal(ReportFieldEnum.judgeSummarySheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeSummarySheet());
        }

        //估价对象适用的估价方法
        if (Objects.equal(ReportFieldEnum.SelectionValuationMethod.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionValuationMethod());
        }
        //估价对象不适用的估价方法
        if (Objects.equal(ReportFieldEnum.NotSelectionValuationMethod.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNotSelectionValuationMethod());
        }
        if (Objects.equal(ReportFieldEnum.SelectionValuationMethodFoundation.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSelectionValuationMethodFoundation());
        }
        //变现分析税费
        if (Objects.equal(ReportFieldEnum.LIQUIDATION_ANALYSIS.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidationAnalysis());
        }

        //抵押价值总金额
        if (Objects.equal(ReportFieldEnum.totalAmountMortgageValue.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValue());
        }
        //抵押价值总金额大写
        if (Objects.equal(ReportFieldEnum.totalAmountMortgageValueCapitalization.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalAmountMortgageValueCapitalization());
        }
        //他权类别
        if (Objects.equal(ReportFieldEnum.HisRightType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(false));
        }
        //他权类别及明细
        if (Objects.equal(ReportFieldEnum.HisRightTypeAndDetail.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHisRightType(true));
        }
        //最佳利用方式
        if (Objects.equal(ReportFieldEnum.BestUseDesc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOptimumUtilizationMode());
        }
        //申报启用表单类型
        if (Objects.equal(ReportFieldEnum.DecalreFormTypeAll.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTypesFormEnabledDeclarationOffice());
        }
        //出租或占用情况
        if (Objects.equal(ReportFieldEnum.rentalPossessionDesc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRentalPossessionDesc());
        }
        //房产类型
        if (Objects.equal(ReportFieldEnum.HouseType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSetUse(true));
        }
        //物业类型
        if (Objects.equal(ReportFieldEnum.PROPERTY_TYPE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPROPERTY_TYPE());
        }
        //出具报告城市
        if (Objects.equal(ReportFieldEnum.ReportArea.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAreaName());
        }

        //注册房地产估价师注册证书复印件
        if (Objects.equal(ReportFieldEnum.RegisteredRealEstateValuerValuationInstitution.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegisteredRealEstateValuerValuationInstitution(generateReportInfo));
        }
        //房地产总价
        if (Objects.equal(ReportFieldEnum.TotalRealEstatePrice.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTotalRealEstatePrice());
        }
        //房地产总价大写金额
        if (Objects.equal(ReportFieldEnum.CapitalizationAmount.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCapitalizationAmount());
        }
        //外聘专家工作概况
        if (Objects.equal(ReportFieldEnum.ExpertWorkOverview.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getExpertWorkOverview());
        }

        //估价对象详细测算过程( 收益法 , 市场比较法)
        if (Objects.equal(ReportFieldEnum.DetailedCalculationProcessValuationObject.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDetailedCalculationProcessValuationObject());
        }
        //经营范围
        if (Objects.equal(ReportFieldEnum.BusinessScope.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBusinessScope());
        }
        //现场查勘期
        if (Objects.equal(ReportFieldEnum.surveyExamineDate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineDate(generateReportInfo.getInvestigationsStartDate(), generateReportInfo.getInvestigationsEndDate()));
        }
        //现场查勘人员
        if (Objects.equal(ReportFieldEnum.surveyExamineCreate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSurveyExamineCreate());
        }
        //房地产估价机构营业执照复印件
        if (Objects.equal(ReportFieldEnum.CopyBusinessLicenseRealEstateValuationAgency.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyBusinessLicenseRealEstateValuationAgency());
        }
        //房地产估价机构资质证书复印件
        if (Objects.equal(ReportFieldEnum.CopyQualificationCertificateRealEstateValuationInstitution.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCopyQualificationCertificateRealEstateValuationInstitution());
        }
        //评估假设
        if (Objects.equal(ReportFieldEnum.EVALUATION_HYPOTHESIS.getName(), name)) {
            String hypothesis = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.HYPOTHESIS);
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, hypothesis);
        }
        //评估依据
        if (Objects.equal(ReportFieldEnum.EVALUATION_BASIS.getName(), name)) {
            String basic = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.BASIS);
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, basic);
        }
        //评估原则
        if (Objects.equal(ReportFieldEnum.EVALUATION_PRINCIPLE.getName(), name)) {
            String principle = generateBaseDataService.getPrincipleBasisHypothesis(SchemeSupportTypeEnum.PRINCIPLE);
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, principle);
        }
        //变现能力分析
        if (Objects.equal(ReportFieldEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY));
        }
        //风险提示
        if (Objects.equal(ReportFieldEnum.ANALYSIS_CATEGORY_RISK.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRisk(SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_RISK));
        }
        //社会经济发展概况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_DEVELOPMENT.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_DEVELOPMENT));
        }
        //房地产市场总体概况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_GENERAL.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_GENERAL));
        }
        //同类房地产市场状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_MARKET.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_MARKET));
        }
        //同类房地产市场版块状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_BLOCK.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_BLOCK));
        }
        //背景估价对象区域物业总体状况
        if (Objects.equal(ReportFieldEnum.BACKGROUND_ANALYSIS_PROPERTY.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportAnalysis(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND_PROPERTY));
        }

        //---
        if (Objects.equal(ReportFieldEnum.ENUM_CITY_RESOURCE_STATUS.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_REAL_ESTATE_SYSTEM.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_land_MARKET_CONDITION.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_HOUSE_MARKET_CONDITION.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_INDUSTRIAL_POLICY.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_TAX_POLICY.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_FINANCIAL_POLICY.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_URBAN_PLANNING_DEVELOPMENT_GOALS.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_URBAN_SOCIAL_ECONOMIC_DEVELOPMENT.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_SUMMARY_GENERAL_FACTORS.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_MARKET_RISK.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_REALIZATION_RISK.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_TAX_RISK.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        if (Objects.equal(ReportFieldEnum.ENUM_OPTIMAL_USE_LAND.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getReportNewLiquidity(ReportFieldEnum.getEnumByName(name)));
        }
        //--
        return false;
    }

    /**
     * 银行通用报告字段
     *
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @throws Exception
     */
    public static boolean assembleUniversalBankMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {

        //厌恶设施
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAversionFacility.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAversionFacility());
        }
        //户型及布局
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralHuxingLayout.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHuxingLayout());
        }
        //获取某些土地证字段信息
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLandArea.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLandNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandownership.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandcert_use.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLand_right_nature.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandapportionment_area.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandendTime.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandregistration_authority.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandregistration_date.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(name));
        }
        //获取某些区位字段信息
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLocation.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralShoppingConditions.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.Bus_Convenience.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralTraffic_accessibility.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralsubway.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralinfrastructure.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralExternal_facilities.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneraleducational_facility.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralMedical_Facilities.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNatural_environment.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralcultural_environment.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectLocationValue(name));
        }
        //获取房屋的装修情况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_door.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_window.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_land.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_wall.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_Canopy.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldA(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_bathroom.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_kitchen.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldB(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralrenovation_condition_maintenance.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectDamagedDegreeFieldValue());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGenerallandscape.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFrontage.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloorSpacing.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralGreenlandRate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBuildingCoverage.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralParkingLot.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralPropertyManagement.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralSuccessRate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getJudgeObjectOtherFieldValue(name));
        }
        //登记时间
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralRegistrationDate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegistrationDate());
        }
        //房屋结构 建筑结构
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralHousingStructure.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildingStructureCategory());
        }
        //总层数
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloorCount.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFloorCount());
        }
        //楼盘名称
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralEstateName.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getEstateName());
        }
        //房屋性质
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralhouseNature.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getHouseNature());
        }
        //房屋所有权人
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralOwnership.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOwnership());
        }
        //填发单位
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFillingUnit.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFillingUnit());
        }
        //附记
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAttachmentReark.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAttachmentReark());
        }
        //建成年代
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBeCompletedTimeGetInteger.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBeCompletedTimeGetInteger());
        }
        //楼层
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFloor.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getDeclareRecordFloor());
        }
        //层户数
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLayerNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLayerNumber());
        }
        //朝向
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralOrientation.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOrientation());
        }
        //建行个贷层高
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStoreyHeight.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStoreyHeight());
        }
        //建行个贷其它
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNetAssessmentOther.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentOther());
        }
        //建行个贷丘地号
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralNetAssessmentGroundNum.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getNetAssessmentGroundNum());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralexteriorWallDecorate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate1());
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralLobbyDecorate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getOutfitDecorate2());
        }
        //地基及墙面
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralFoundationAndWall.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getFoundationAndWall());
        }
        //使用状况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralUsageStatus.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getUsageStatus());
        }
        //法定优先受偿款
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursement.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(0));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementMortgage.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(1));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementEngineering.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(2));
        }
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralStatutoryOptimumReimbursementOther.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getStatutoryOptimumReimbursement(3));
        }
        //评估面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAssessArea.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessArea());
        }
        //评估单价
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralAssessPrice.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getAssessPrice());
        }
        //坐落
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralSeat.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeatText());
        }
        //银行通用权证号
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCERT_NAME.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSchemeJudgeObjectCertText());
        }
        //建筑面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralBuildArea.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getBuildArea());
        }
        //套内建筑面积
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCoverArea.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCoverArea());
        }
        //共有权情况
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralCo_ownership.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getCo_ownership());
        }
        //银行变现能力分析
        if (Objects.equal(ReportFieldUniversalBankEnum.BankGeneralANALYSIS_CATEGORY_LIQUIDITY.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLiquidityRiskLittle());
        }
        return false;
    }

    /**
     * 其它组装
     *
     * @param name
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @return
     * @throws Exception
     */
    public static boolean assembleOtherMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //收益法租赁限制说明
        if (Objects.equal(ReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getTenancyrestrictionRemark());
        }
        return false;
    }

    /**
     * 土地报告
     *
     * @param name
     * @param textMap
     * @param bookmarkMap
     * @param fileMap
     * @param generateBaseDataService
     * @param generateReportInfo
     * @param reportType
     * @param reportGroup
     * @return
     * @throws Exception
     */
    public static boolean assembleLandMap(String name, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, GenerateBaseDataService generateBaseDataService, GenerateReportInfo generateReportInfo, BaseDataDic reportType, GenerateReportGroup reportGroup) throws Exception {
        //土地所有权人
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_OWNERSHIP.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(ReportFieldUniversalBankEnum.BankGenerallandownership.getName()));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_Seat.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getSeatText());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_EndTime.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandCertificateFieldValue(ReportFieldUniversalBankEnum.BankGenerallandendTime.getName()));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_landNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_RemainingYear.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_LandRightType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_acquisitionType.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        // 估价委托人信息
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_PrincipalInfo.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalInfo(true));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_PrincipalIdNumber.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getPrincipalIdNumber());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_RightType_Desc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandRightTypeDesc());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_RightType_Content.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandRightTypeContent());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_PlotRatio.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_BuildingDensity.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_SetPlotRatio.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_GreeningRate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_CompatibilityRate.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_BEST_USE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_PlotRatio_Desc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_USE_MATERIAL.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_OpenTime.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_CERTIFICATE_CONTRACT.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_SurveyExplore_TYPE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_BaseLandPrice.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_FaceStreet_TYPE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_NATURE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandReportFieldValue(name));
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_TOTAL_LAND_AREA.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandAssessTotalArea());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_UNIT_LAND_PRICE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandUnitPriceAssess());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_ESTATE_LAND_PRICE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandEstatePriceAssess());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_EVALUATION_EFFECTIVE_DATE.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLand_evaluation_effective_date());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_JudgeObjectSheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandEnumJudgeObjectSheet());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_JudgeBuildResultSurveySheet.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandJudgeObjectSheet());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_RegionalFactorsDesc.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getRegionalFactorsDescSheet());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_IndividualFactorsDesc.getName(), name)) {
            return putValue(false, false, true, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getIndividualFactorsDescSheet());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_Asset_InventoryDesc.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandInventoryDesc());
        }
        if (Objects.equal(ReportFieldLandEnum.LAND_ENUM_Asset_InventoryAddRess.getName(), name)) {
            return putValue(true, true, false, textMap, bookmarkMap, fileMap, name, generateBaseDataService.getLandInventoryAddRessText());
        }
        return false;
    }

    /**
     * 填充值
     *
     * @param text
     * @param bookmark
     * @param textMap
     * @param bookmarkMap
     * @param key
     * @param value
     */
    public static boolean putValue(boolean text, boolean bookmark, boolean fileFlag, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String key, String value) {
        if (value == null) {//不要用isEmpty ，这里可以这样''  或者这样"" ,只要不是null就行
            return false;
        }
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        if (text) {
            textMap.put(String.format("${%s}", key), value);
            textMap.put(String.format("$(%s)", key), value);
        }
        if (bookmark) {
            bookmarkMap.put(key, value);
        }
        if (fileFlag) {
            fileMap.put(String.format("${%s}", key), value);
            fileMap.put(String.format("$(%s)", key), value);
        }
        return true;
    }


}
