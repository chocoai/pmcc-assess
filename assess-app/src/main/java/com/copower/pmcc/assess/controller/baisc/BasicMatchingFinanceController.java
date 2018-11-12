package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingFinance;
import com.copower.pmcc.assess.service.basic.BasicMatchingFinanceService;
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
 * @Date: 2018/11/6 11:48
 * @Description:
 */
@RequestMapping(value = "/basicMatchingFinance")
@Controller
public class BasicMatchingFinanceController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingFinanceById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingFinanceById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingFinanceService.getBasicMatchingFinanceById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingFinance", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingFinanceService.saveAndUpdateBasicMatchingFinance(basicMatchingFinance));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingFinance", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingFinance(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingFinanceService.deleteBasicMatchingFinance(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingFinance basicMatchingFinance,@RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicMatchingFinance != null){
                if (!approval) {
                    basicMatchingFinance.setCreator(commonService.thisUserAccount());
                }
            }
            return basicMatchingFinanceService.getBootstrapTableVo(basicMatchingFinance);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingFinanceList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingFinanceList(BasicMatchingFinance basicMatchingFinance){
        try {
            return HttpResult.newCorrectResult(200,basicMatchingFinanceService.basicMatchingFinanceList(basicMatchingFinance));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
