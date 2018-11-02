package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.service.basic.BasicHouseRoomDecorateService;
import com.copower.pmcc.assess.service.basic.BasicHouseRoomService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
 * @Date: 2018/11/2 11:29
 * @Description:
 */
@RequestMapping(value = "/basicHouseRoom")
@Controller
public class BasicHouseRoomController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseRoomById", method = {RequestMethod.GET}, name = "房间 获取")
    public HttpResult getBasicHouseRoomById(Integer id) {
        try {
            BasicHouseRoom basicHouseRoom = basicHouseRoomService.getBasicHouseRoomById(id);
            return HttpResult.newCorrectResult(200,basicHouseRoom);
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseRoom", method = {RequestMethod.POST}, name = "房间 删除")
    public HttpResult deleteBasicHouseRoom(Integer id) {
        try {
            basicHouseRoomService.deleteBasicHouseRoom(id);
            return HttpResult.newCorrectResult(200,"success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseRoomList", method = {RequestMethod.GET}, name = "房间 列表")
    public HttpResult basicHouseRoomList(BasicHouseRoom basicHouseRoom) {
        try {
            List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
            return HttpResult.newCorrectResult(200,basicHouseRooms);
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "房间 列表")
    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoom basicHouseRoom){
        try {
            BootstrapTableVo vo = basicHouseRoomService.getBootstrapTableVo(basicHouseRoom);
            return vo;
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseRoom", method = {RequestMethod.POST}, name = "房间 更新或者新增")
    public HttpResult saveAndUpdateBasicHouseRoom(BasicHouseRoom basicHouseRoom) {
        try {
            basicHouseRoomService.saveAndUpdateBasicHouseRoom(basicHouseRoom);
            return HttpResult.newCorrectResult(200,"success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    //-------------------||----------------------//

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseRoomDecorate", method = {RequestMethod.POST}, name = "房间子类 更新或者新增")
    public HttpResult saveAndUpdateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) {
        try {
            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorate.getRoomId());
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseRoomDecorate", method = {RequestMethod.POST}, name = "房间子类 删除")
    public HttpResult deleteBasicHouseRoomDecorate(Integer id) {
        try {
            BasicHouseRoomDecorate basicHouseRoomDecorate = basicHouseRoomDecorateService.getBasicHouseRoomDecorateById(id);
            basicHouseRoomDecorateService.deleteBasicHouseRoomDecorate(id);
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorate.getRoomId());
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500,"异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getRoomDecorateBootstrapTableVo", method = {RequestMethod.GET}, name = "房间子类 列表")
    public BootstrapTableVo getRoomDecorateBootstrapTableVo(BasicHouseRoomDecorate basicHouseRoomDecorate){
        try {
            BootstrapTableVo vo = basicHouseRoomDecorateService.getBootstrapTableVo(basicHouseRoomDecorate);
            return vo;
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return null;
        }
    }
}
