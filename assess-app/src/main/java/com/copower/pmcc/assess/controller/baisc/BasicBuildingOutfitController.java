package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingOutfit;
import com.copower.pmcc.assess.service.basic.BasicBuildingOutfitService;
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
 * @Date: 2018/10/30 14:25
 * @Description:楼栋外装
 */
@RequestMapping(value = "/basicBuildingOutfit")
@Controller
public class BasicBuildingOutfitController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingOutfitById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingOutfitById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingOutfitService.getBasicBuildingOutfitById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuildingOutfit", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingOutfitService.saveAndUpdateBasicBuildingOutfit(basicBuildingOutfit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuildingOutfit", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuildingOutfit(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingOutfitService.deleteBasicBuildingOutfit(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuildingOutfit basicBuildingOutfit, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicBuildingOutfitService.getBootstrapTableVo(basicBuildingOutfit);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingOutfitList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingOutfitList(BasicBuildingOutfit basicBuildingOutfit){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingOutfitService.basicBuildingOutfitList(basicBuildingOutfit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
