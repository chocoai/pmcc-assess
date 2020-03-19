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
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonRecordNo.getName()));
                    break;
                case ReportNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportNumber.getName()));
                    break;
                case queryCode:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonQueryCode.getName()));
                    break;
                case ThisYear:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonThisYear.getName()));
                    break;
                case recordDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonRecordDate.getName()));
                    break;
                case ReportNumber2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportNumbering.getName()));
                    break;
                case ReportQrcode:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportQrcode.getName()));
                    break;
                case ReportingCategories:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportingCategories.getName()));
                    break;
                case ReportUnitString:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportUnitString.getName()));
                    break;
                case ReportIssuanceDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonReportIssuanceDate.getName()));
                    break;
                case DelegatePurpose:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonDelegatePurpose.getName()));
                    break;
                case StatementPurposeEntrustment:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonStatementPurposeEntrustment.getName()));
                    break;
                case ValueTimePoint:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonValueTimePoint.getName()));
                    break;
                case ValueTimePointRemark:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonValueTimePointRemark.getName()));
                    break;
                case ValueType:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonValueType.getName()));
                    break;
                case ValueTypeDesc:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonValueTypeDesc.getName()));
                    break;
                case EntrustedUnit:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonEntrustedUnit.getName()));
                    break;
                case AssessTotal:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonAssessTotal.getName()));
                    break;
                case AssessTotalRMB:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonAssessTotalRMB.getName()));
                    break;
                case CertificationPurpose:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonCertificationPurpose.getName()));
                    break;
                case HotTip:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonHotTip.getName()));
                    break;
                case HotTip2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonHotTip.getName()));
                    break;
                case Atypism2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonHotTip.getName()));
                    break;
                case StatutoryPriorityAmountTotal:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportCommonEnum.CommonStatutoryPriorityAmountTotal.getName()));
                    break;

                //
                case Seat:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.Seat.getName()));
                    break;
                case Seat2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.Seat.getName()));
                    break;
                case AssessArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.AssessArea.getName()));
                    break;
                case AssessPrice:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.AssessPrice.getName()));
                    break;
                case StatutoryOptimumReimbursement:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.StatutoryOptimumReimbursement.getName()));
                    break;
                case StatutoryOptimumReimbursement3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.StatutoryOptimumReimbursementOther.getName()));
                    break;
                case ownership:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.ownership.getName()));
                    break;
                case Co_ownership:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.Co_ownership.getName()));
                    break;
                case RegistrationDate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.RegistrationDate.getName()));
                    break;
                case houseNature:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.houseNature.getName()));
                    break;
                case BuildArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BuildArea.getName()));
                    break;
                case CoverArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.CoverArea.getName()));
                    break;
                case AttachmentReark:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.AttachmentReark.getName()));
                    break;
                case FillingUnit:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.FillingUnit.getName()));
                    break;
                case estateName:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.estateName.getName()));
                    break;
                case BeCompletedTimeGetInteger:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BeCompletedTimeGetInteger.getName()));
                    break;
                case ANALYSIS_CATEGORY_LIQUIDITY3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName()));
                    break;
                case ANALYSIS_CATEGORY_LIQUIDITY2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.ANALYSIS_CATEGORY_LIQUIDITY.getName()));
                    break;
                case CERT_NAME3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.CERT_NAME.getName()));
                    break;
                case CERT_NAME2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.CERT_NAME.getName()));
                    break;
                case CERT_NAME1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.CERT_NAME.getName()));
                    break;
                case floor:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.floor.getName()));
                    break;
                case floor2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.floor.getName()));
                    break;
                case floorCount:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.floorCount.getName()));
                    break;
                case floorCount2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.floorCount.getName()));
                    break;
                case UsageStatus:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.UsageStatus.getName()));
                    break;
                case UsageStatus2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.UsageStatus.getName()));
                    break;
                case housingStructure:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.housingStructure.getName()));
                    break;
                case housingStructure2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.housingStructure.getName()));
                    break;
                case StatutoryOptimumReimbursement2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.StatutoryOptimumReimbursementEngineering.getName()));
                    break;
                case StatutoryOptimumReimbursement1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.StatutoryOptimumReimbursementMortgage.getName()));
                    break;
                case NetAssessmentGroundNum:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.NetAssessmentGroundNum.getName()));
                    break;
                case LandCertificateField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landNumber.getName()));
                    break;
                case LandCertificateField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landownership.getName()));
                    break;
                case LandCertificateField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landcert_use.getName()));
                    break;
                case LandCertificateField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.land_right_nature.getName()));
                    break;
                case LandCertificateField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landapportionment_area.getName()));
                    break;
                case LandArea:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.LandArea.getName()));
                    break;
                case LandCertificateField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landendTime.getName()));
                    break;
                case LandCertificateField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landregistration_authority.getName()));
                    break;
                case LandCertificateField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.landregistration_date.getName()));
                    break;
                case NetAssessmentOther:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.NetAssessmentOther.getName()));
                    break;
                case Orientation:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.Orientation.getName()));
                    break;
                case exteriorWallDecorate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.exteriorWallDecorate.getName()));
                    break;
                case LobbyDecorate:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.LobbyDecorate.getName()));
                    break;
                case LayerNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.LayerNumber.getName()));
                    break;
                case StoreyHeight:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.StoreyHeight.getName()));
                    break;
                case FoundationAndWall:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.FoundationAndWall.getName()));
                    break;
                case JudgeObjectDamagedDegreeField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_door.getName()));
                    break;
                case JudgeObjectDamagedDegreeField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_window.getName()));
                    break;
                case JudgeObjectDamagedDegreeField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_land.getName()));
                    break;
                case JudgeObjectDamagedDegreeField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_wall.getName()));
                    break;
                case JudgeObjectDamagedDegreeField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_Canopy.getName()));
                    break;
                case JudgeObjectDamagedDegreeField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_bathroom.getName()));
                    break;
                case JudgeObjectDamagedDegreeField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_kitchen.getName()));
                    break;
                case JudgeObjectDamagedDegreeField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.renovation_condition_maintenance.getName()));
                    break;
                case JudgeObjectLoactionField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralLocation.getName()));
                    break;
                case JudgeObjectLoactionField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralShoppingConditions.getName()));
                    break;
                case JudgeObjectLoactionField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.Bus_Convenience.getName()));
                    break;
                case JudgeObjectLoactionField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralTraffic_accessibility.getName()));
                    break;
                case JudgeObjectLoactionField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralsubway.getName()));
                    break;
                case JudgeObjectLoactionField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralinfrastructure.getName()));
                    break;
                case JudgeObjectLoactionField6B:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralExternal_facilities.getName()));
                    break;
                case JudgeObjectLoactionField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneraleducational_facility.getName()));
                    break;
                case JudgeObjectLoactionField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralMedical_Facilities.getName()));
                    break;
                case JudgeObjectLoactionField9:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralNatural_environment.getName()));
                    break;
                case JudgeObjectLoactionField10:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralcultural_environment.getName()));
                    break;
                case JudgeObjectOtherField1:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGenerallandscape.getName()));
                    break;
                case JudgeObjectOtherField2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralFrontage.getName()));
                    break;
                case JudgeObjectOtherField3:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralFloorSpacing.getName()));
                    break;
                case JudgeObjectOtherField4:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralGreenlandRate.getName()));
                    break;
                case JudgeObjectOtherField5:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralBuildingCoverage.getName()));
                    break;
                case JudgeObjectOtherField6:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralParkingLot.getName()));
                    break;
                case JudgeObjectOtherField7:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralPropertyManagement.getName()));
                    break;
                case JudgeObjectOtherField8:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.BankGeneralSuccessRate.getName()));
                    break;
                case HuxingLayout:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.HuxingLayout.getName()));
                    break;
                case AversionFacility:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportBankEnum.AversionFacility.getName()));
                    break;
                //

                case AssessPriceClassification:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.AssessPriceClassification.getName()));
                    break;
                case SetUse2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.SetUse.getName()));
                    break;
                case HotTipBank:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.HotTipBank.getName()));
                    break;
                case ArchivesDepositNumber:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.ArchivesDepositNumber.getName()));
                    break;
                case unitType:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.unitType.getName()));
                    break;
                case DecorationStatus:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.DecorationStatus.getName()));
                    break;
                case CertificationPurpose2:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportFieldConstructionBankEnum.CertificationPurpose.getName()));
                    break;

                //





                case District_Analysis:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum.District_Analysis.getName()));
                    break;
                case ICBCValuationCaseInformationSheet:
                    biConsumer.accept(appendFun.apply(findName), appendFun.apply(BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum.ICBCValuationCaseInformationSheet.getName()));
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
