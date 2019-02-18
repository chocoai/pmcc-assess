package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.service.basic.BasicHouseIntelligentService;
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
 * @Date: 2018/11/6 11:58
 * @Description:
 */
@RequestMapping(value = "/basicHouseIntelligent")
@Controller
public class BasicHouseIntelligentController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseIntelligentById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseIntelligentById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseIntelligentService.getBasicHouseIntelligentById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseIntelligent", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent){
        try {
            return HttpResult.newCorrectResult(200,basicHouseIntelligentService.saveAndUpdateBasicHouseIntelligent(basicHouseIntelligent));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseIntelligent", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseIntelligent(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseIntelligentService.deleteBasicHouseIntelligent(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseIntelligent basicHouseIntelligent, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicHouseIntelligent != null){
                if (!approval) {
                    basicHouseIntelligent.setCreator(commonService.thisUserAccount());
                }
            }
            return basicHouseIntelligentService.getBootstrapTableVo(basicHouseIntelligent);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseIntelligentList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent){
        try {
            return HttpResult.newCorrectResult(200,basicHouseIntelligentService.basicHouseIntelligentList(basicHouseIntelligent));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
