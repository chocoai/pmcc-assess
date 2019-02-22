package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/2/14 16:11
 * @Description:报告模板字段(收益法)
 */
public enum BaseReportFieldMdIncomeEnum implements Serializable {
    RentGrowthForecast("租金增值预测",""),
    EntrustedInformation("委托资料",""),
    RestrictionExplain("出租率说明",""),
    MonthRentalIncome("月租金",""),
    MonthNumber("月份数",""),
    Rentals("有效出租率",""),
    RentalIncome("月租金收入",""),

    OtherIncomeContents("其它收入内容",""),
    OtherIncome("其它收入",""),

    YearDeposit("年押金",""),
    AnnualDepositIncome("年押金收入",""),

    YearDepositRate("一年期定期存款利率",""),
    RentInterestIncome("年押金利息收入",""),
    EffectiveIncomeFormula("有效收入公式",""),

    GrossIncome("毛收入",""),
    IncomeTotal("总收入",""),

    ManagemenRemark("管理费用说明",""),

    ReplacementValue("重置价格",""),
    ReplacementCost("收益法重置成本",""),//重置成本

    MaintenanceCostRatio("维修保养费率",""),
    LandUseTax("土地使用税",""),
    ManagementCost("管理费",""),

    InsurancePremium("收益法保险费率",""),//保险费
    InsurancePremium1("年保费",""),

    LandTaxFees("收益法土地使用税",""),//土地使用税费
    AnnualOperatingCost("年运营费",""),
    AnnualNetIncome("年净收益",""),
    IncomePrice("单价",""),

    RemunerationRateSheet("报酬率测算表",""),
    IncomeMethodPriceCalculatingSheet("收益法价格测算表",""),


    Tenancyrestriction("租约限制","");
    private String key;

    private String name;
    private String describe;

    BaseReportFieldMdIncomeEnum(String key, String name, String describe) {
        this.key = key;
        this.name = name;
        this.describe = describe;
    }
    BaseReportFieldMdIncomeEnum(String name, String describe) {
        this.key = key;
        this.name = name;
        this.describe = describe;
    }

    public static BaseReportFieldMdIncomeEnum getEnumByName(String name) {
        for (BaseReportFieldMdIncomeEnum e : BaseReportFieldMdIncomeEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldMdIncomeEnum e : BaseReportFieldMdIncomeEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
