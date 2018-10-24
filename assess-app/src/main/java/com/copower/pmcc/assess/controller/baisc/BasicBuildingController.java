package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:楼栋
 */
@RequestMapping(value = "/basicBuilding")
@Controller
public class BasicBuildingController {
    @Autowired
    private BasicBuildingService basicBuildingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.getBasicBuildingById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuilding", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuilding(BasicBuilding basicBuilding){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuilding", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuilding(Integer id){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.deleteBasicBuilding(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding){
        try {
            return basicBuildingService.getBootstrapTableVo(basicBuilding);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingList(BasicBuilding basicBuilding){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.basicBuildingList(basicBuilding));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoComplete", method = {RequestMethod.GET}, name = "楼栋 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String identifier, Integer maxRows,Integer estateId){
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        BasicBuilding basicBuilding = new BasicBuilding();
        basicBuilding.setIdentifier(identifier);
        basicBuilding.setEstateId(estateId);
        try {
            List<BasicBuilding> list = basicBuildingService.autoComplete(basicBuilding);
            for (BasicBuilding oo:list){
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(oo.getId()));
                keyValueDto.setValue(String.valueOf(oo.getIdentifier()));
                keyValueDtos.add(keyValueDto);
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
