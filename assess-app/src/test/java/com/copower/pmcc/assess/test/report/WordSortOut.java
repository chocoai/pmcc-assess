package com.copower.pmcc.assess.test.report;

import com.aspose.words.SaveFormat;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by zch on 2020-3-19.
 * 报告整理
 */
public class WordSortOut {

    /*思路
     *
      * 1:第一步先下载下来所有要整理的word文档
      * 2:把 下载下来的word放在一个目录下,记录下这个目录
      * 3:建立过滤和替换的方法 如"文号"  这个字段进来会被替换为"公共文号"
      * 4:保存替换后的目录
      * 5:将替换后的word模板文档 上传之 服务器上
      * 6:测试  是否有遗落的文档字段
      *
      * 测试处理办法  当只有一个字段的时候考虑直接在线上更改，当超过2个字段那么就应该在过滤和替换方法里面新增字段然后重新执行在上传服务器上
      * */

    public Map<String, String> fieldAssembly() {
        Function<String, String> appendFun = ((value) -> "${" + value + "}");
        Map<String, String> resultMap = new HashMap<>();
        BiConsumer<String, String> biConsumer = ((find, replace) -> resultMap.put(find, replace));
        for (AbandonedZCHEnum zchEnum : AbandonedZCHEnum.values()) {
            String findName = zchEnum.getName();
            switch (zchEnum) {
                case recordNo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonRecordNo.getName()));
                    break;
                case ReportNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportNumber.getName()));
                    break;
                case queryCode:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonQueryCode.getName()));
                    break;
                case ThisYear:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonThisYear.getName()));
                    break;
                case recordDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonRecordDate.getName()));
                    break;
                case ReportNumber2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportNumbering.getName()));
                    break;
                case ReportQrcode:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportQrcode.getName()));
                    break;
                case ReportingCategories:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportingCategories.getName()));
                    break;
                case ReportUnitString:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportUnitString.getName()));
                    break;
                case ReportIssuanceDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonReportIssuanceDate.getName()));
                    break;
                case DelegatePurpose:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonDelegatePurpose.getName()));
                    break;
                case StatementPurposeEntrustment:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonStatementPurposeEntrustment.getName()));
                    break;
                case ValueTimePoint:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonValueTimePoint.getName()));
                    break;
                case ValueTimePointRemark:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonValueTimePointRemark.getName()));
                    break;
                case ValueType:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonValueType.getName()));
                    break;
                case ValueTypeDesc:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonValueTypeDesc.getName()));
                    break;
                case EntrustedUnit:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonEntrustedUnit.getName()));
                    break;
                case AssessTotal:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonAssessTotal.getName()));
                    break;
                case AssessTotalRMB:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonAssessTotalRMB.getName()));
                    break;
                case CertificationPurpose:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonCertificationPurpose.getName()));
                    break;
                case HotTip:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonHotTip.getName()));
                    break;
                case HotTip2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonHotTip.getName()));
                    break;
                case Atypism2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonHotTip.getName()));
                    break;
                case StatutoryPriorityAmountTotal:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.CommonStatutoryPriorityAmountTotal.getName()));
                    break;


                case ValuationProjectName:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.ValuationProjectName.getName()));
                    break;
                case PracticalUse:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.PracticalUse.getName()));
                    break;
                case EvaluationMethod:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.EvaluationMethod.getName()));
                    break;
                case HomeWorkEndTime:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.HomeWorkEndTime.getName()));
                    break;
                case HomeWorkStartTime:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.HomeWorkStartTime.getName()));
                    break;
                case AssistanceStaff:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.AssistanceStaff.getName()));
                    break;
                case SetUse:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.SetUse.getName()));
                    break;
                case PRINCIPAL:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.PRINCIPAL.getName()));
                    break;
                case EvaluationThink:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.EvaluationThink.getName()));
                    break;
                case PrincipalInfo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.PrincipalInfo.getName()));
                    break;
                case PrincipalDescribe:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.PrincipalDescribe.getName()));
                    break;
                case PrincipalDataDescribe:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.PrincipalDataDescribe.getName()));
                    break;
                case RegisteredRealEstateValuerAndNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.RegisteredRealEstateValuerAndNumber.getName()));
                    break;
                case RegisteredHouseRealEstateValuerAndNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.RegisteredHouseRealEstateValuerAndNumber.getName()));
                    break;
                case RegisteredRealEstateValuer:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.RegisteredRealEstateValuer.getName()));
                    break;
                case XIEHE_organizationHouseInfo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_organizationHouseInfo.getName()));
                    break;
                case XIEHE_organizationInfo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_organizationInfo.getName()));
                    break;
                case XIEHE_organizationName:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_organizationName.getName()));
                    break;
                case XIEHE_RealEstateValuationAgencyHouse:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_RealEstateValuationAgencyHouse.getName()));
                    break;
                case XIEHE_RealEstateValuationAgency:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_RealEstateValuationAgency.getName()));
                    break;
                case XIEHE_organizationAddress:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_organizationAddress.getName()));
                    break;
                case XIEHE_legalRepresentative:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_legalRepresentative.getName()));
                    break;
                case XIEHE_registeredNo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_registeredNo.getName()));
                    break;
                case XIEHE_organizationRank:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_organizationRank.getName()));
                    break;
                case XIEHE_certificateNo:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_certificateNo.getName()));
                    break;
                case XIEHE_certificateEffectiveDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldCommonEnum.XIEHE_certificateEffectiveDate.getName()));
                    break;

                //
                case Seat:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.Seat.getName()));
                    break;
                case Seat2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.Seat.getName()));
                    break;
                case AssessArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.AssessArea.getName()));
                    break;
                case AssessPrice:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.AssessPrice.getName()));
                    break;
                case StatutoryOptimumReimbursement:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.StatutoryOptimumReimbursement.getName()));
                    break;
                case StatutoryOptimumReimbursement3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.StatutoryOptimumReimbursementOther.getName()));
                    break;
                case ownership:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.ownership.getName()));
                    break;
                case Co_ownership:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.Co_ownership.getName()));
                    break;
                case RegistrationDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.RegistrationDate.getName()));
                    break;
                case houseNature:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.houseNature.getName()));
                    break;
                case BuildArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BuildArea.getName()));
                    break;
                case CoverArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.CoverArea.getName()));
                    break;
                case AttachmentReark:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.AttachmentReark.getName()));
                    break;
                case FillingUnit:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.FillingUnit.getName()));
                    break;
                case estateName:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.estateName.getName()));
                    break;
                case BeCompletedTimeGetInteger:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BeCompletedTimeGetInteger.getName()));
                    break;
                case ANALYSIS_CATEGORY_LIQUIDITY3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName()));
                    break;
                case ANALYSIS_CATEGORY_LIQUIDITY2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName()));
                    break;
                case CERT_NAME3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.CERT_NAME.getName()));
                    break;
                case CERT_NAME2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.CERT_NAME.getName()));
                    break;
                case CERT_NAME1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.CERT_NAME.getName()));
                    break;
                case floor:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.floor.getName()));
                    break;
                case floor2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.floor.getName()));
                    break;
                case floorCount:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.floorCount.getName()));
                    break;
                case floorCount2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.floorCount.getName()));
                    break;
                case UsageStatus:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.UsageStatus.getName()));
                    break;
                case UsageStatus2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.UsageStatus.getName()));
                    break;
                case housingStructure:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.housingStructure.getName()));
                    break;
                case housingStructure2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.housingStructure.getName()));
                    break;
                case StatutoryOptimumReimbursement2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.StatutoryOptimumReimbursementEngineering.getName()));
                    break;
                case StatutoryOptimumReimbursement1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.StatutoryOptimumReimbursementMortgage.getName()));
                    break;
                case NetAssessmentGroundNum:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.NetAssessmentGroundNum.getName()));
                    break;
                case LandCertificateField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landNumber.getName()));
                    break;
                case LandCertificateField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landownership.getName()));
                    break;
                case LandCertificateField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landcert_use.getName()));
                    break;
                case LandCertificateField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.land_right_nature.getName()));
                    break;
                case LandCertificateField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landapportionment_area.getName()));
                    break;
                case LandArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.LandArea.getName()));
                    break;
                case LandCertificateField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landendTime.getName()));
                    break;
                case LandCertificateField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landregistration_authority.getName()));
                    break;
                case LandCertificateField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.landregistration_date.getName()));
                    break;
                case NetAssessmentOther:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.NetAssessmentOther.getName()));
                    break;
                case Orientation:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.Orientation.getName()));
                    break;
                case exteriorWallDecorate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.exteriorWallDecorate.getName()));
                    break;
                case LobbyDecorate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.LobbyDecorate.getName()));
                    break;
                case LayerNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.LayerNumber.getName()));
                    break;
                case StoreyHeight:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.StoreyHeight.getName()));
                    break;
                case FoundationAndWall:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.FoundationAndWall.getName()));
                    break;
                case JudgeObjectDamagedDegreeField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_door.getName()));
                    break;
                case JudgeObjectDamagedDegreeField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_window.getName()));
                    break;
                case JudgeObjectDamagedDegreeField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_land.getName()));
                    break;
                case JudgeObjectDamagedDegreeField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_wall.getName()));
                    break;
                case JudgeObjectDamagedDegreeField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_Canopy.getName()));
                    break;
                case JudgeObjectDamagedDegreeField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_bathroom.getName()));
                    break;
                case JudgeObjectDamagedDegreeField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_kitchen.getName()));
                    break;
                case JudgeObjectDamagedDegreeField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.renovation_condition_maintenance.getName()));
                    break;
                case JudgeObjectLoactionField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralLocation.getName()));
                    break;
                case JudgeObjectLoactionField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralShoppingConditions.getName()));
                    break;
                case JudgeObjectLoactionField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.Bus_Convenience.getName()));
                    break;
                case JudgeObjectLoactionField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralTraffic_accessibility.getName()));
                    break;
                case JudgeObjectLoactionField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralsubway.getName()));
                    break;
                case JudgeObjectLoactionField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralinfrastructure.getName()));
                    break;
                case JudgeObjectLoactionField6B:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralExternal_facilities.getName()));
                    break;
                case JudgeObjectLoactionField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneraleducational_facility.getName()));
                    break;
                case JudgeObjectLoactionField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralMedical_Facilities.getName()));
                    break;
                case JudgeObjectLoactionField9:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralNatural_environment.getName()));
                    break;
                case JudgeObjectLoactionField10:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralcultural_environment.getName()));
                    break;
                case JudgeObjectOtherField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGenerallandscape.getName()));
                    break;
                case JudgeObjectOtherField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralFrontage.getName()));
                    break;
                case JudgeObjectOtherField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralFloorSpacing.getName()));
                    break;
                case JudgeObjectOtherField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralGreenlandRate.getName()));
                    break;
                case JudgeObjectOtherField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralBuildingCoverage.getName()));
                    break;
                case JudgeObjectOtherField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralParkingLot.getName()));
                    break;
                case JudgeObjectOtherField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralPropertyManagement.getName()));
                    break;
                case JudgeObjectOtherField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.BankGeneralSuccessRate.getName()));
                    break;
                case HuxingLayout:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.HuxingLayout.getName()));
                    break;
                case AversionFacility:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldUniversalBankEnum.AversionFacility.getName()));
                    break;
                //

                case AssessPriceClassification:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.AssessPriceClassification.getName()));
                    break;
                case SetUse2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.SetUse.getName()));
                    break;
                case HotTipBank:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.HotTipBank.getName()));
                    break;
                case ArchivesDepositNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.ArchivesDepositNumber.getName()));
                    break;
                case unitType:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.unitType.getName()));
                    break;
                case DecorationStatus:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.DecorationStatus.getName()));
                    break;
                case CertificationPurpose2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldJiansheBankEnum.CertificationPurpose.getName()));
                    break;

                //


                case District_Analysis:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldGongshangBankEnum.District_Analysis.getName()));
                    break;
                case ICBCValuationCaseInformationSheet:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(ReportFieldGongshangBankEnum.ICBCValuationCaseInformationSheet.getName()));
                    break;

                //

                default:
                    break;
            }
        }
        return resultMap;
    }

    /**
     * 正式处理
     *
     * @throws Exception
     */
    @Test
    public void handle() throws Exception {
        String inputPath = "D:\\data\\WordSortOut\\input";
        String outPath = "D:\\data\\WordSortOut\\out";
        BiFunction<File, String, String> biFunction = (((file, s) -> {
            String separator = StringUtils.repeat(File.separator, 2);
            String[] strings = file.getParentFile().getPath().split(separator);
            String newPath = outPath + separator + strings[strings.length - 1] + separator + file.getName();
            try {
                FileUtils.copyFile(file, new File(newPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newPath;

        }));
        Map<String, String> stringMap = fieldAssembly();
        List<String> stringList = new ArrayList<>();
        getWordUrls(new File(inputPath), stringList);
        Iterator<String> iterator = stringList.iterator();
        try {
            FileUtils.deleteDirectory(new File(outPath));
        } catch (IOException e) {
        }
        while (iterator.hasNext()) {
            String oldPath = iterator.next();
            File file = new File(oldPath);
            String newPath = biFunction.apply(file, outPath);
            System.out.println(file.getName() + " 开始替换!...");
            AsposeUtils.replaceText(newPath, stringMap);
            System.out.println(file.getName() + " 替换完成!");
            System.out.println();
        }

    }

    /**
     * 收集需要替换的word
     *
     * @param file
     * @param stringList
     */
    private void getWordUrls(File file, List<String> stringList) {
        List<String> filterSuffix = Arrays.asList(com.aspose.words.SaveFormat.getName(SaveFormat.DOC), com.aspose.words.SaveFormat.getName(SaveFormat.DOCX), com.aspose.words.SaveFormat.getName(SaveFormat.DOTX));

        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                File fileTo = listFiles[i];
                getWordUrls(fileTo, stringList);
            }
        }
        if (file.isFile()) {
            String extension = FilenameUtils.getExtension(file.getName());
            if (filterSuffix.contains(extension.toUpperCase())) {
                stringList.add(file.getPath());
            }
        }
    }

    @Test
    public void print() {
        Map<String, String> stringMap = fieldAssembly();
        stringMap.forEach((findName, replaceName) -> {
            System.out.println(findName + " | " + replaceName);
        });
    }
}
