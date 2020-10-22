package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicHouseTrading implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer applyId;

    /**
     * 
     */
    private Integer houseId;

    /**
     * 交易时间
     */
    private Date tradingTime;

    /**
     * 交易类型
     */
    private Integer tradingType;

    /**
     * 交易总价
     */
    private BigDecimal tradingTotalPrice;

    /**
     * 交易级别
     */
    private String transactionLevel;

    /**
     * 交易单价
     */
    private BigDecimal tradingUnitPrice;

    /**
     * 买方额外支付的税
     */
    private String buyerExtraTax;

    /**
     * 买方额外支付的费
     */
    private String buyerExtraFee;

    /**
     * 承租方额外支付的税
     */
    private String rentingExtraTax;

    /**
     * 承租方额外支付的费
     */
    private String rentingExtraFee;

    /**
     * 说明事项类型
     */
    private Integer descriptionType;

    /**
     * 说明事项内容
     */
    private String descriptionContent;

    /**
     * 分期支付利率
     */
    private String installmentInterestRate;

    /**
     * 付款方式
     */
    private Integer paymentMethod;

    /**
     * 交易情况
     */
    private Integer transactionSituation;

    /**
     * 税费负担
     */
    private Integer taxBurden;

    /**
     * 财产范围
     */
    private Integer scopeProperty;

    /**
     * 范围包含
     */
    private String scopeInclude;

    /**
     * 范围不包含
     */
    private String scopeNotInclude;

    /**
     * 财产范围说明
     */
    private String scopePropertyExplain;

    /**
     * 首付比例
     */
    private String downPaymentRatio;

    /**
     * 贷款利率
     */
    private String lendingRate;

    /**
     * 贷款期限
     */
    private Integer loanPeriod;

    /**
     * 押金
     */
    private String deposit;

    /**
     * 信息来源类型
     */
    private Integer informationType;

    /**
     * 信息来源类别
     */
    private Integer informationCategory;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 土地买售人
     */
    private String landBuyerSeller;

    /**
     * 价格类型
     */
    private Integer priceType;

    /**
     * 单价内涵
     */
    private Integer priceConnotation;

    /**
     * 单价内涵单位
     */
    private String priceConnotationUnit;

    /**
     * 每亩单价
     */
    private BigDecimal perMuPrice;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 标识
     */
    private Boolean bisMark;

    /**
     * 限制说明
     */
    private String restrictionsRemark;

    /**
     * 限制事项
     */
    private String restrictions;

    /**
     * 
     */
    private Boolean bisDelete;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * tb_basic_house_trading
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return apply_id 
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 
     * @param applyId 
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 
     * @return house_id 
     */
    public Integer getHouseId() {
        return houseId;
    }

    /**
     * 
     * @param houseId 
     */
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /**
     * 交易时间
     * @return trading_time 交易时间
     */
    public Date getTradingTime() {
        return tradingTime;
    }

    /**
     * 交易时间
     * @param tradingTime 交易时间
     */
    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    /**
     * 交易类型
     * @return trading_type 交易类型
     */
    public Integer getTradingType() {
        return tradingType;
    }

    /**
     * 交易类型
     * @param tradingType 交易类型
     */
    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    /**
     * 交易总价
     * @return trading_total_price 交易总价
     */
    public BigDecimal getTradingTotalPrice() {
        return tradingTotalPrice;
    }

    /**
     * 交易总价
     * @param tradingTotalPrice 交易总价
     */
    public void setTradingTotalPrice(BigDecimal tradingTotalPrice) {
        this.tradingTotalPrice = tradingTotalPrice;
    }

    /**
     * 交易级别
     * @return transaction_level 交易级别
     */
    public String getTransactionLevel() {
        return transactionLevel;
    }

    /**
     * 交易级别
     * @param transactionLevel 交易级别
     */
    public void setTransactionLevel(String transactionLevel) {
        this.transactionLevel = transactionLevel == null ? null : transactionLevel.trim();
    }

    /**
     * 交易单价
     * @return trading_unit_price 交易单价
     */
    public BigDecimal getTradingUnitPrice() {
        return tradingUnitPrice;
    }

    /**
     * 交易单价
     * @param tradingUnitPrice 交易单价
     */
    public void setTradingUnitPrice(BigDecimal tradingUnitPrice) {
        this.tradingUnitPrice = tradingUnitPrice;
    }

    /**
     * 买方额外支付的税
     * @return buyer_extra_tax 买方额外支付的税
     */
    public String getBuyerExtraTax() {
        return buyerExtraTax;
    }

    /**
     * 买方额外支付的税
     * @param buyerExtraTax 买方额外支付的税
     */
    public void setBuyerExtraTax(String buyerExtraTax) {
        this.buyerExtraTax = buyerExtraTax == null ? null : buyerExtraTax.trim();
    }

    /**
     * 买方额外支付的费
     * @return buyer_extra_fee 买方额外支付的费
     */
    public String getBuyerExtraFee() {
        return buyerExtraFee;
    }

    /**
     * 买方额外支付的费
     * @param buyerExtraFee 买方额外支付的费
     */
    public void setBuyerExtraFee(String buyerExtraFee) {
        this.buyerExtraFee = buyerExtraFee == null ? null : buyerExtraFee.trim();
    }

    /**
     * 承租方额外支付的税
     * @return renting_extra_tax 承租方额外支付的税
     */
    public String getRentingExtraTax() {
        return rentingExtraTax;
    }

    /**
     * 承租方额外支付的税
     * @param rentingExtraTax 承租方额外支付的税
     */
    public void setRentingExtraTax(String rentingExtraTax) {
        this.rentingExtraTax = rentingExtraTax == null ? null : rentingExtraTax.trim();
    }

    /**
     * 承租方额外支付的费
     * @return renting_extra_fee 承租方额外支付的费
     */
    public String getRentingExtraFee() {
        return rentingExtraFee;
    }

    /**
     * 承租方额外支付的费
     * @param rentingExtraFee 承租方额外支付的费
     */
    public void setRentingExtraFee(String rentingExtraFee) {
        this.rentingExtraFee = rentingExtraFee == null ? null : rentingExtraFee.trim();
    }

    /**
     * 说明事项类型
     * @return description_type 说明事项类型
     */
    public Integer getDescriptionType() {
        return descriptionType;
    }

    /**
     * 说明事项类型
     * @param descriptionType 说明事项类型
     */
    public void setDescriptionType(Integer descriptionType) {
        this.descriptionType = descriptionType;
    }

    /**
     * 说明事项内容
     * @return description_content 说明事项内容
     */
    public String getDescriptionContent() {
        return descriptionContent;
    }

    /**
     * 说明事项内容
     * @param descriptionContent 说明事项内容
     */
    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent == null ? null : descriptionContent.trim();
    }

    /**
     * 分期支付利率
     * @return installment_interest_rate 分期支付利率
     */
    public String getInstallmentInterestRate() {
        return installmentInterestRate;
    }

    /**
     * 分期支付利率
     * @param installmentInterestRate 分期支付利率
     */
    public void setInstallmentInterestRate(String installmentInterestRate) {
        this.installmentInterestRate = installmentInterestRate == null ? null : installmentInterestRate.trim();
    }

    /**
     * 付款方式
     * @return payment_method 付款方式
     */
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 付款方式
     * @param paymentMethod 付款方式
     */
    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 交易情况
     * @return transaction_situation 交易情况
     */
    public Integer getTransactionSituation() {
        return transactionSituation;
    }

    /**
     * 交易情况
     * @param transactionSituation 交易情况
     */
    public void setTransactionSituation(Integer transactionSituation) {
        this.transactionSituation = transactionSituation;
    }

    /**
     * 税费负担
     * @return tax_burden 税费负担
     */
    public Integer getTaxBurden() {
        return taxBurden;
    }

    /**
     * 税费负担
     * @param taxBurden 税费负担
     */
    public void setTaxBurden(Integer taxBurden) {
        this.taxBurden = taxBurden;
    }

    /**
     * 财产范围
     * @return scope_property 财产范围
     */
    public Integer getScopeProperty() {
        return scopeProperty;
    }

    /**
     * 财产范围
     * @param scopeProperty 财产范围
     */
    public void setScopeProperty(Integer scopeProperty) {
        this.scopeProperty = scopeProperty;
    }

    /**
     * 范围包含
     * @return scope_include 范围包含
     */
    public String getScopeInclude() {
        return scopeInclude;
    }

    /**
     * 范围包含
     * @param scopeInclude 范围包含
     */
    public void setScopeInclude(String scopeInclude) {
        this.scopeInclude = scopeInclude == null ? null : scopeInclude.trim();
    }

    /**
     * 范围不包含
     * @return scope_not_include 范围不包含
     */
    public String getScopeNotInclude() {
        return scopeNotInclude;
    }

    /**
     * 范围不包含
     * @param scopeNotInclude 范围不包含
     */
    public void setScopeNotInclude(String scopeNotInclude) {
        this.scopeNotInclude = scopeNotInclude == null ? null : scopeNotInclude.trim();
    }

    /**
     * 财产范围说明
     * @return scope_property_explain 财产范围说明
     */
    public String getScopePropertyExplain() {
        return scopePropertyExplain;
    }

    /**
     * 财产范围说明
     * @param scopePropertyExplain 财产范围说明
     */
    public void setScopePropertyExplain(String scopePropertyExplain) {
        this.scopePropertyExplain = scopePropertyExplain == null ? null : scopePropertyExplain.trim();
    }

    /**
     * 首付比例
     * @return down_payment_ratio 首付比例
     */
    public String getDownPaymentRatio() {
        return downPaymentRatio;
    }

    /**
     * 首付比例
     * @param downPaymentRatio 首付比例
     */
    public void setDownPaymentRatio(String downPaymentRatio) {
        this.downPaymentRatio = downPaymentRatio == null ? null : downPaymentRatio.trim();
    }

    /**
     * 贷款利率
     * @return lending_rate 贷款利率
     */
    public String getLendingRate() {
        return lendingRate;
    }

    /**
     * 贷款利率
     * @param lendingRate 贷款利率
     */
    public void setLendingRate(String lendingRate) {
        this.lendingRate = lendingRate == null ? null : lendingRate.trim();
    }

    /**
     * 贷款期限
     * @return loan_period 贷款期限
     */
    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * 贷款期限
     * @param loanPeriod 贷款期限
     */
    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    /**
     * 押金
     * @return deposit 押金
     */
    public String getDeposit() {
        return deposit;
    }

    /**
     * 押金
     * @param deposit 押金
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    /**
     * 信息来源类型
     * @return information_type 信息来源类型
     */
    public Integer getInformationType() {
        return informationType;
    }

    /**
     * 信息来源类型
     * @param informationType 信息来源类型
     */
    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
    }

    /**
     * 信息来源类别
     * @return information_category 信息来源类别
     */
    public Integer getInformationCategory() {
        return informationCategory;
    }

    /**
     * 信息来源类别
     * @param informationCategory 信息来源类别
     */
    public void setInformationCategory(Integer informationCategory) {
        this.informationCategory = informationCategory;
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 电话
     * @return phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 土地买售人
     * @return land_buyer_seller 土地买售人
     */
    public String getLandBuyerSeller() {
        return landBuyerSeller;
    }

    /**
     * 土地买售人
     * @param landBuyerSeller 土地买售人
     */
    public void setLandBuyerSeller(String landBuyerSeller) {
        this.landBuyerSeller = landBuyerSeller == null ? null : landBuyerSeller.trim();
    }

    /**
     * 价格类型
     * @return price_type 价格类型
     */
    public Integer getPriceType() {
        return priceType;
    }

    /**
     * 价格类型
     * @param priceType 价格类型
     */
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    /**
     * 单价内涵
     * @return price_connotation 单价内涵
     */
    public Integer getPriceConnotation() {
        return priceConnotation;
    }

    /**
     * 单价内涵
     * @param priceConnotation 单价内涵
     */
    public void setPriceConnotation(Integer priceConnotation) {
        this.priceConnotation = priceConnotation;
    }

    /**
     * 单价内涵单位
     * @return price_connotation_unit 单价内涵单位
     */
    public String getPriceConnotationUnit() {
        return priceConnotationUnit;
    }

    /**
     * 单价内涵单位
     * @param priceConnotationUnit 单价内涵单位
     */
    public void setPriceConnotationUnit(String priceConnotationUnit) {
        this.priceConnotationUnit = priceConnotationUnit == null ? null : priceConnotationUnit.trim();
    }

    /**
     * 每亩单价
     * @return per_mu_price 每亩单价
     */
    public BigDecimal getPerMuPrice() {
        return perMuPrice;
    }

    /**
     * 每亩单价
     * @param perMuPrice 每亩单价
     */
    public void setPerMuPrice(BigDecimal perMuPrice) {
        this.perMuPrice = perMuPrice;
    }

    /**
     * 成本
     * @return cost 成本
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 成本
     * @param cost 成本
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 标识
     * @return bis_mark 标识
     */
    public Boolean getBisMark() {
        return bisMark;
    }

    /**
     * 标识
     * @param bisMark 标识
     */
    public void setBisMark(Boolean bisMark) {
        this.bisMark = bisMark;
    }

    /**
     * 限制说明
     * @return restrictions_remark 限制说明
     */
    public String getRestrictionsRemark() {
        return restrictionsRemark;
    }

    /**
     * 限制说明
     * @param restrictionsRemark 限制说明
     */
    public void setRestrictionsRemark(String restrictionsRemark) {
        this.restrictionsRemark = restrictionsRemark == null ? null : restrictionsRemark.trim();
    }

    /**
     * 限制事项
     * @return restrictions 限制事项
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * 限制事项
     * @param restrictions 限制事项
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions == null ? null : restrictions.trim();
    }

    /**
     * 
     * @return bis_delete 
     */
    public Boolean getBisDelete() {
        return bisDelete;
    }

    /**
     * 
     * @param bisDelete 
     */
    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public static BasicHouseTrading.Builder builder() {
        return new BasicHouseTrading.Builder();
    }

    /**
     * tb_basic_house_trading
     */
    public static class Builder {
        /**
         * tb_basic_house_trading
         */
        private BasicHouseTrading obj;

        public Builder() {
            this.obj = new BasicHouseTrading();
        }

        /**
         * id
         * @param id id
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 
         * @param applyId 
         */
        public Builder applyId(Integer applyId) {
            obj.setApplyId(applyId);
            return this;
        }

        /**
         * 
         * @param houseId 
         */
        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        /**
         * 交易时间
         * @param tradingTime 交易时间
         */
        public Builder tradingTime(Date tradingTime) {
            obj.setTradingTime(tradingTime);
            return this;
        }

        /**
         * 交易类型
         * @param tradingType 交易类型
         */
        public Builder tradingType(Integer tradingType) {
            obj.setTradingType(tradingType);
            return this;
        }

        /**
         * 交易总价
         * @param tradingTotalPrice 交易总价
         */
        public Builder tradingTotalPrice(BigDecimal tradingTotalPrice) {
            obj.setTradingTotalPrice(tradingTotalPrice);
            return this;
        }

        /**
         * 交易级别
         * @param transactionLevel 交易级别
         */
        public Builder transactionLevel(String transactionLevel) {
            obj.setTransactionLevel(transactionLevel);
            return this;
        }

        /**
         * 交易单价
         * @param tradingUnitPrice 交易单价
         */
        public Builder tradingUnitPrice(BigDecimal tradingUnitPrice) {
            obj.setTradingUnitPrice(tradingUnitPrice);
            return this;
        }

        /**
         * 买方额外支付的税
         * @param buyerExtraTax 买方额外支付的税
         */
        public Builder buyerExtraTax(String buyerExtraTax) {
            obj.setBuyerExtraTax(buyerExtraTax);
            return this;
        }

        /**
         * 买方额外支付的费
         * @param buyerExtraFee 买方额外支付的费
         */
        public Builder buyerExtraFee(String buyerExtraFee) {
            obj.setBuyerExtraFee(buyerExtraFee);
            return this;
        }

        /**
         * 承租方额外支付的税
         * @param rentingExtraTax 承租方额外支付的税
         */
        public Builder rentingExtraTax(String rentingExtraTax) {
            obj.setRentingExtraTax(rentingExtraTax);
            return this;
        }

        /**
         * 承租方额外支付的费
         * @param rentingExtraFee 承租方额外支付的费
         */
        public Builder rentingExtraFee(String rentingExtraFee) {
            obj.setRentingExtraFee(rentingExtraFee);
            return this;
        }

        /**
         * 说明事项类型
         * @param descriptionType 说明事项类型
         */
        public Builder descriptionType(Integer descriptionType) {
            obj.setDescriptionType(descriptionType);
            return this;
        }

        /**
         * 说明事项内容
         * @param descriptionContent 说明事项内容
         */
        public Builder descriptionContent(String descriptionContent) {
            obj.setDescriptionContent(descriptionContent);
            return this;
        }

        /**
         * 分期支付利率
         * @param installmentInterestRate 分期支付利率
         */
        public Builder installmentInterestRate(String installmentInterestRate) {
            obj.setInstallmentInterestRate(installmentInterestRate);
            return this;
        }

        /**
         * 付款方式
         * @param paymentMethod 付款方式
         */
        public Builder paymentMethod(Integer paymentMethod) {
            obj.setPaymentMethod(paymentMethod);
            return this;
        }

        /**
         * 交易情况
         * @param transactionSituation 交易情况
         */
        public Builder transactionSituation(Integer transactionSituation) {
            obj.setTransactionSituation(transactionSituation);
            return this;
        }

        /**
         * 税费负担
         * @param taxBurden 税费负担
         */
        public Builder taxBurden(Integer taxBurden) {
            obj.setTaxBurden(taxBurden);
            return this;
        }

        /**
         * 财产范围
         * @param scopeProperty 财产范围
         */
        public Builder scopeProperty(Integer scopeProperty) {
            obj.setScopeProperty(scopeProperty);
            return this;
        }

        /**
         * 财产范围说明
         * @param scopePropertyExplain 财产范围说明
         */
        public Builder scopePropertyExplain(String scopePropertyExplain) {
            obj.setScopePropertyExplain(scopePropertyExplain);
            return this;
        }

        /**
         * 范围包含
         * @param scopeInclude 范围包含
         */
        public Builder scopeInclude(String scopeInclude) {
            obj.setScopeInclude(scopeInclude);
            return this;
        }

        /**
         * 范围不包含
         * @param scopeNotInclude 范围不包含
         */
        public Builder scopeNotInclude(String scopeNotInclude) {
            obj.setScopeNotInclude(scopeNotInclude);
            return this;
        }

        /**
         * 首付比例
         * @param downPaymentRatio 首付比例
         */
        public Builder downPaymentRatio(String downPaymentRatio) {
            obj.setDownPaymentRatio(downPaymentRatio);
            return this;
        }

        /**
         * 贷款利率
         * @param lendingRate 贷款利率
         */
        public Builder lendingRate(String lendingRate) {
            obj.setLendingRate(lendingRate);
            return this;
        }

        /**
         * 贷款期限
         * @param loanPeriod 贷款期限
         */
        public Builder loanPeriod(Integer loanPeriod) {
            obj.setLoanPeriod(loanPeriod);
            return this;
        }

        /**
         * 押金
         * @param deposit 押金
         */
        public Builder deposit(String deposit) {
            obj.setDeposit(deposit);
            return this;
        }

        /**
         * 信息来源类型
         * @param informationType 信息来源类型
         */
        public Builder informationType(Integer informationType) {
            obj.setInformationType(informationType);
            return this;
        }

        /**
         * 信息来源类别
         * @param informationCategory 信息来源类别
         */
        public Builder informationCategory(Integer informationCategory) {
            obj.setInformationCategory(informationCategory);
            return this;
        }

        /**
         * 姓名
         * @param name 姓名
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 电话
         * @param phone 电话
         */
        public Builder phone(String phone) {
            obj.setPhone(phone);
            return this;
        }

        /**
         * 土地买售人
         * @param landBuyerSeller 土地买售人
         */
        public Builder landBuyerSeller(String landBuyerSeller) {
            obj.setLandBuyerSeller(landBuyerSeller);
            return this;
        }

        /**
         * 价格类型
         * @param priceType 价格类型
         */
        public Builder priceType(Integer priceType) {
            obj.setPriceType(priceType);
            return this;
        }

        /**
         * 单价内涵
         * @param priceConnotation 单价内涵
         */
        public Builder priceConnotation(Integer priceConnotation) {
            obj.setPriceConnotation(priceConnotation);
            return this;
        }

        /**
         * 单价内涵单位
         * @param priceConnotationUnit 单价内涵单位
         */
        public Builder priceConnotationUnit(String priceConnotationUnit) {
            obj.setPriceConnotationUnit(priceConnotationUnit);
            return this;
        }

        /**
         * 每亩单价
         * @param perMuPrice 每亩单价
         */
        public Builder perMuPrice(BigDecimal perMuPrice) {
            obj.setPerMuPrice(perMuPrice);
            return this;
        }

        /**
         * 成本
         * @param cost 成本
         */
        public Builder cost(BigDecimal cost) {
            obj.setCost(cost);
            return this;
        }

        /**
         * 标识
         * @param bisMark 标识
         */
        public Builder bisMark(Boolean bisMark) {
            obj.setBisMark(bisMark);
            return this;
        }

        /**
         * 限制事项
         * @param restrictions 限制事项
         */
        public Builder restrictions(String restrictions) {
            obj.setRestrictions(restrictions);
            return this;
        }

        /**
         * 限制说明
         * @param restrictionsRemark 限制说明
         */
        public Builder restrictionsRemark(String restrictionsRemark) {
            obj.setRestrictionsRemark(restrictionsRemark);
            return this;
        }

        /**
         * 
         * @param bisDelete 
         */
        public Builder bisDelete(Boolean bisDelete) {
            obj.setBisDelete(bisDelete);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        public BasicHouseTrading build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        applyId("apply_id", "applyId", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        tradingTime("trading_time", "tradingTime", "TIMESTAMP", false),
        tradingType("trading_type", "tradingType", "INTEGER", false),
        tradingTotalPrice("trading_total_price", "tradingTotalPrice", "DECIMAL", false),
        transactionLevel("transaction_level", "transactionLevel", "VARCHAR", false),
        tradingUnitPrice("trading_unit_price", "tradingUnitPrice", "DECIMAL", false),
        buyerExtraTax("buyer_extra_tax", "buyerExtraTax", "VARCHAR", false),
        buyerExtraFee("buyer_extra_fee", "buyerExtraFee", "VARCHAR", false),
        rentingExtraTax("renting_extra_tax", "rentingExtraTax", "VARCHAR", false),
        rentingExtraFee("renting_extra_fee", "rentingExtraFee", "VARCHAR", false),
        descriptionType("description_type", "descriptionType", "INTEGER", false),
        descriptionContent("description_content", "descriptionContent", "VARCHAR", false),
        installmentInterestRate("installment_interest_rate", "installmentInterestRate", "VARCHAR", false),
        paymentMethod("payment_method", "paymentMethod", "INTEGER", false),
        transactionSituation("transaction_situation", "transactionSituation", "INTEGER", false),
        taxBurden("tax_burden", "taxBurden", "INTEGER", false),
        scopeProperty("scope_property", "scopeProperty", "INTEGER", false),
        scopeInclude("scope_include", "scopeInclude", "VARCHAR", false),
        scopeNotInclude("scope_not_include", "scopeNotInclude", "VARCHAR", false),
        scopePropertyExplain("scope_property_explain", "scopePropertyExplain", "VARCHAR", false),
        downPaymentRatio("down_payment_ratio", "downPaymentRatio", "VARCHAR", false),
        lendingRate("lending_rate", "lendingRate", "VARCHAR", false),
        loanPeriod("loan_period", "loanPeriod", "INTEGER", false),
        deposit("deposit", "deposit", "VARCHAR", false),
        informationType("information_type", "informationType", "INTEGER", false),
        informationCategory("information_category", "informationCategory", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        phone("phone", "phone", "VARCHAR", false),
        landBuyerSeller("land_buyer_seller", "landBuyerSeller", "VARCHAR", false),
        priceType("price_type", "priceType", "INTEGER", false),
        priceConnotation("price_connotation", "priceConnotation", "INTEGER", false),
        priceConnotationUnit("price_connotation_unit", "priceConnotationUnit", "VARCHAR", false),
        perMuPrice("per_mu_price", "perMuPrice", "DECIMAL", false),
        cost("cost", "cost", "DECIMAL", false),
        bisMark("bis_mark", "bisMark", "BIT", false),
        restrictionsRemark("restrictions_remark", "restrictionsRemark", "VARCHAR", false),
        restrictions("restrictions", "restrictions", "VARCHAR", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_basic_house_trading
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_house_trading
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_house_trading
         */
        private final String column;

        /**
         * tb_basic_house_trading
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_house_trading
         */
        private final String javaProperty;

        /**
         * tb_basic_house_trading
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}