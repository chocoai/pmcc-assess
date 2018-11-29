package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.ExamineHouseTradingSellAndLeaseDtoTypeEnum;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLease;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSell;
import com.copower.pmcc.assess.dto.input.cases.CaseHouseTradingLeaseAndSellDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Auther: zch
 * @Date: 2018/9/14 11:06
 * @Description:处理案例信息的房屋出售和房屋出租
 */
@Service
public class CaseHouseTradingLeaseAndSellDtoService {
    @Autowired
    private CaseHouseTradingSellService caseHouseTradingSellService;
    @Autowired
    private CaseHouseTradingLeaseService caseHouseTradingLeaseService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public void saveCaseHouseTradingLeaseAndSellDto(CaseHouseTradingLeaseAndSellDto caseHouseTradingLeaseAndSellDto) {
        if (caseHouseTradingLeaseAndSellDto != null) {
            String tradingType = caseHouseTradingLeaseAndSellDto.getTradingType();
            if (!StringUtils.isEmpty(tradingType)) {
                if (Objects.equal(tradingType, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey())){//房屋出租
                    CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
                    BeanUtils.copyProperties(caseHouseTradingLeaseAndSellDto,caseHouseTradingLease);
                    caseHouseTradingLeaseService.saveAndUpdateCaseHouseTradingLease(caseHouseTradingLease);
                }
                if (Objects.equal(tradingType, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey())){//房屋出售
                    CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
                    BeanUtils.copyProperties(caseHouseTradingLeaseAndSellDto,caseHouseTradingSell);
                    caseHouseTradingSellService.saveAndUpdateCaseHouseTradingSell(caseHouseTradingSell);
                }
            }
        }
    }

    public BootstrapTableVo getVoList(String type,CaseHouseTradingLease caseHouseTradingLease,CaseHouseTradingSell caseHouseTradingSell){
        BootstrapTableVo vo = null;
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey())){//房屋出租
            vo = caseHouseTradingLeaseService.getBootstrapTableVo(caseHouseTradingLease,type);
        }
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey())){//房屋出售
            vo = caseHouseTradingSellService.getBootstrapTableVo(caseHouseTradingSell,type);
        }
        return vo;
    }

    @Transactional
    public boolean remove(String type, Integer id) {
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey())) {//房屋出租
            CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
            caseHouseTradingLease.setId(id);
            caseHouseTradingLeaseService.deleteCaseHouseTradingLease(caseHouseTradingLease);
        }
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey())) {//房屋出售
            CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
            caseHouseTradingSell.setId(id);
            caseHouseTradingSellService.deleteCaseHouseTradingSell(caseHouseTradingSell);
        }
        return false;
    }
}
