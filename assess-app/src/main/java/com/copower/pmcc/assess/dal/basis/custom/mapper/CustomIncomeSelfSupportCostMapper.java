package com.copower.pmcc.assess.dal.basis.custom.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-8-20.
 */
public interface CustomIncomeSelfSupportCostMapper {
    BigDecimal getAmountMoneyTotal(@Param("supportId") Integer supportId,@Param("type") Integer type,@Param("creator") String creator);//获取金额合计
}
