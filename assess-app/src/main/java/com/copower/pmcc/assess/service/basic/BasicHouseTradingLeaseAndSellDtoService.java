package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dto.input.basic.BasicHouseTradingLeaseAndSellDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.base.Objects;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:28
 * @Description:
 */
@Service
public class BasicHouseTradingLeaseAndSellDtoService {
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void saveBasicHouseTradingLeaseAndSellDto(BasicHouseTradingLeaseAndSellDto basicHouseTradingLeaseAndSellDto) throws Exception {
        String tradingType = basicHouseTradingLeaseAndSellDto.getTradingType();
        if (StringUtils.isNotEmpty(tradingType)) {
            if (Objects.equal(tradingType, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_LEASE)) {
                BasicHouseTradingLease basicHouseTradingLease = new BasicHouseTradingLease();
                BeanUtils.copyProperties(basicHouseTradingLeaseAndSellDto, basicHouseTradingLease);
                basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(basicHouseTradingLease);
            }
            if (Objects.equal(tradingType, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_SELL)) {
                BasicHouseTradingSell basicHouseTradingSell = new BasicHouseTradingSell();
                BeanUtils.copyProperties(basicHouseTradingLeaseAndSellDto, basicHouseTradingSell);
                basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(basicHouseTradingSell);
            }
        }
    }

    public BootstrapTableVo getVoList(String type, BasicHouseTradingLease basicHouseTradingLease, BasicHouseTradingSell basicHouseTradingSell) throws Exception {
        BootstrapTableVo vo = null;
        if (Objects.equal(type, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_LEASE)) {
            vo = basicHouseTradingLeaseService.getBootstrapTableVo(basicHouseTradingLease, type);
        }
        if (Objects.equal(type, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_SELL)) {
            vo = basicHouseTradingSellService.getBootstrapTableVo(basicHouseTradingSell, type);
        }
        return vo;
    }

    public void remove(String type, Integer id) throws Exception {
        if (Objects.equal(type, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_LEASE)) {
            basicHouseTradingLeaseService.deleteBasicHouseTradingLease(id);
        }
        if (Objects.equal(type, AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_SELL)) {
            basicHouseTradingSellService.deleteBasicHouseTradingSell(id);
        }
    }

}
