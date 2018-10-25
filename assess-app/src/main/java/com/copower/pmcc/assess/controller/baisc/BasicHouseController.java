package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouse;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
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
 * @Description:房屋
 */
@RequestMapping(value = "/basicHouse")
@Controller
public class BasicHouseController {
    @Autowired
    private BasicHouseService basicHouseService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouse", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouse(BasicHouse basicHouse){
        try {
            return HttpResult.newCorrectResult(basicHouseService.saveAndUpdateBasicHouse(basicHouse));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouse", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouse(Integer id){
        try {
            return HttpResult.newCorrectResult(basicHouseService.deleteBasicHouse(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse){
        try {
            return basicHouseService.getBootstrapTableVo(basicHouse);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseList(BasicHouse basicHouse){
        try {
            return HttpResult.newCorrectResult(basicHouseService.basicHouseList(basicHouse));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoComplete", method = {RequestMethod.GET}, name = "房屋 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String houseNumber, Integer maxRows,Integer unitId){
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        BasicHouse basicHouse = new BasicHouse();
        basicHouse.setHouseNumber(houseNumber);
        basicHouse.setUnitId(unitId);
        try {
            List<BasicHouse> list = basicHouseService.autoComplete(basicHouse);
            for (BasicHouse oo:list){
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(oo.getId()));
                keyValueDto.setValue(String.valueOf(oo.getHouseNumber()));
                keyValueDtos.add(keyValueDto);
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
