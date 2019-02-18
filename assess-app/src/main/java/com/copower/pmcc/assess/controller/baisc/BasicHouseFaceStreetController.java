package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.service.basic.BasicHouseFaceStreetService;
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
 * @Date: 2018/11/6 11:59
 * @Description:
 */
@RequestMapping(value = "/basicHouseFaceStreet")
@Controller
public class BasicHouseFaceStreetController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseFaceStreetService basicHouseFaceStreetService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseFaceStreetById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseFaceStreetById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseFaceStreetService.getBasicHouseFaceStreetById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseFaceStreet", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet){
        try {
            return HttpResult.newCorrectResult(200,basicHouseFaceStreetService.saveAndUpdateBasicHouseFaceStreet(basicHouseFaceStreet));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseFaceStreet", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseFaceStreet(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseFaceStreetService.deleteBasicHouseFaceStreet(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseFaceStreet basicHouseFaceStreet, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicHouseFaceStreet != null){
                if (!approval) {
                    basicHouseFaceStreet.setCreator(commonService.thisUserAccount());
                }
            }
            return basicHouseFaceStreetService.getBootstrapTableVo(basicHouseFaceStreet);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseFaceStreetList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseFaceStreetList(BasicHouseFaceStreet basicHouseFaceStreet){
        try {
            return HttpResult.newCorrectResult(200,basicHouseFaceStreetService.basicHouseFaceStreetList(basicHouseFaceStreet));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
