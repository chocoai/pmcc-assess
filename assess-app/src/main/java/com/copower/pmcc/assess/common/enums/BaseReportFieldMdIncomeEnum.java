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
    AAA1("收益法申报产权证类型",""),// 房产证、不动产证
    AAA12("收益法设定用途",""),//方案中设定用途
    AAA13("收益法土地终止日期",""),//土地证终止日期
    AAA14("收益法价值时点",""),//方案价值时点
    AAA15("收益法剩余土地使用年限",""),
    AAA16("收益法竣工时间",""),//楼栋竣工时间
    AAA17("收益法已使用年限",""),//价值时点-竣工时间
    AAA18("收益法建筑结构类别",""),//楼栋建筑结构类别
    AAA19("收益法经济耐用年限",""),//楼栋建筑使用年限
    AAA20("收益法房产剩余年限",""),
    AAA21("收益法收益年限",""),//收益法房产剩余年限 收益法剩余土地使用年限 中最小值
    AAA22("收益法中的比较法",""),
    MonthRentalIncome("收益法月租金收入",""),
    Tenancyrestriction("收益法租赁限制说明",""),
    AAA23("收益法区域城市",""),//当前所在区域的城市
    AAA24("收益法评估面积",""),//方案中评估面积
    AAA25("收益法其他收入说明",""),
    RestrictionExplain("收益法出租率说明",""),
    AAA28("收益法押金说明",""),
    Rentals("收益法出租率",""),
    OtherIncome("收益法其它收入",""),
    ReplacementCost("收益法重置成本",""),
    MaintenanceCostRatio("收益法维修保养费率",""),
    ManagementCost("收益法管理费率",""),
    InsurancePremium("收益法保险费率",""),//保险费
    AAA29("收益法租赁税费",""),
    AAA30("收益法房产税",""),
    AAA31("收益法印花税",""),
    AAA32("收益法合计税费",""),
    AAA33("收益法土地使用税",""),
    AAA34("收益法交易费率",""),
    AAA35("收益法年有效毛收入",""),
    AnnualOperatingCost("收益法年运营费",""),
    AnnualNetIncome("收益法年净收益",""),
    RentGrowthForecast("收益法租金增长率",""),
    AAA36("收益法机会成本说明",""),
    AAA37("收益法投资风险补偿",""),
    AAA38("收益法管理负担补偿",""),
    AAA39("收益法缺乏流动性补偿",""),
    AAA40("收益法投资带来的优惠",""),
    AAA41("收益法报酬率",""),
    IncomePrice("收益法测算价格",""),

    //------------------------------------------------------

    EntrustedInformation("委托资料",""),
    MonthNumber("月份数",""),

    OtherIncomeContents("其它收入内容",""),

    YearDeposit("年押金",""),
    AnnualDepositIncome("年押金收入",""),

    YearDepositRate("一年期定期存款利率",""),
    RentInterestIncome("年押金利息收入",""),
    EffectiveIncomeFormula("有效收入公式",""),

    GrossIncome("毛收入",""),
    IncomeTotal("总收入",""),

    ManagemenRemark("管理费用说明",""),


    LandUseTax("土地使用税",""),



    InsurancePremium1("年保费",""),

    LandTaxFees("收益法土地使用税",""),//土地使用税费


    RemunerationRateSheet("报酬率测算表",""),
    IncomeMethodPriceCalculatingSheet("收益法价格测算表","")
    ;
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
