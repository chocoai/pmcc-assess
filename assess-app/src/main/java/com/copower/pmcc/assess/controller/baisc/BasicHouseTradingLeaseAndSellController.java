package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dto.input.basic.BasicHouseTradingLeaseAndSellDto;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingLeaseAndSellDtoService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/11/2 11:16
 * @Description:
 */
@RequestMapping(value = "/basicHouseTradingLeaseAndSell")
@Controller
public class BasicHouseTradingLeaseAndSellController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicHouseTradingLeaseAndSellDtoService basicHouseTradingLeaseAndSellDtoService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/removeLeaseAndSell", method = {RequestMethod.POST}, name = "删除案例 房屋 出租或者出售")
    public HttpResult removeHouseTradingLeaseAndSell(Integer id, String type) {
        try {
            basicHouseTradingLeaseAndSellDtoService.remove(type, id);
            return HttpResult.newCorrectResult(200,"success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateHouseTradingLeaseAndSell", method = {RequestMethod.POST}, name = "更新或者鑫泽案例 房屋 出租或者出售")
    public HttpResult saveHouseTradingLeaseAndSell(BasicHouseTradingLeaseAndSellDto basicHouseTradingLeaseAndSellDto){
        try {
            basicHouseTradingLeaseAndSellDtoService.saveBasicHouseTradingLeaseAndSellDto(basicHouseTradingLeaseAndSellDto);
            return HttpResult.newCorrectResult(200,"success");
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getLeaseAndSellVos", method = {RequestMethod.GET}, name = "获取案例 房屋 出租或者出售 列表")
    public BootstrapTableVo getBootstrapTableVo(String type, BasicHouseTradingLease basicHouseTradingLease, BasicHouseTradingSell basicHouseTradingSell, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        BootstrapTableVo vo = null;
        if (basicHouseTradingLease == null){
            basicHouseTradingLease = new BasicHouseTradingLease();
            basicHouseTradingLease.setHouseId(0);
        }
        if (basicHouseTradingSell == null){
            basicHouseTradingSell = new BasicHouseTradingSell();
            basicHouseTradingSell.setHouseId(0);
        }
        try {
            vo = basicHouseTradingLeaseAndSellDtoService.getVoList(type, basicHouseTradingLease, basicHouseTradingSell);
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
            return new BootstrapTableVo();
        }
        return vo;
    }
}
