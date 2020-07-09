package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseRoomDecorateDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomVo;
import com.copower.pmcc.assess.service.basic.BasicHouseRoomDecorateService;
import com.copower.pmcc.assess.service.basic.BasicHouseRoomService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private CommonService commonService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseRoomDecorateDao basicHouseRoomDecorateDao;

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseRoomById", method = {RequestMethod.GET}, name = "房间 获取")
    public HttpResult getBasicHouseRoomById(Integer id) {
        try {
            BasicHouseRoom basicHouseRoom = basicHouseRoomService.getBasicHouseRoomById(id);
            return HttpResult.newCorrectResult(200, basicHouseRoom);
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseRoom", method = {RequestMethod.POST}, name = "房间 删除")
    public HttpResult deleteBasicHouseRoom(Integer id) {
        try {
            basicHouseRoomService.deleteBasicHouseRoom(id);
            return HttpResult.newCorrectResult(200, "success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseRoomList", method = {RequestMethod.GET}, name = "房间 列表")
    public HttpResult basicHouseRoomList(BasicHouseRoom basicHouseRoom) {
        try {
            List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
            List<BasicHouseRoomVo> vos = LangUtils.transform(basicHouseRooms,(oo -> basicHouseRoomService.getBasicHouseRoomVo(oo)));
            return HttpResult.newCorrectResult(200, vos);
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "房间 列表")
    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoom basicHouseRoom, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            if (basicHouseRoom == null) {
                basicHouseRoom = new BasicHouseRoom();
            }
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
            basicHouseRoomService.saveAndUpdateBasicHouseRoom(basicHouseRoom,true);
            return HttpResult.newCorrectResult(200, "success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/copyBasicHouseRoom", method = {RequestMethod.POST}, name = "房间 复制")
    public HttpResult copyBasicHouseRoom(Integer sourceId,Integer targetId) {
        try {
            BasicHouseRoom source = basicHouseRoomService.getBasicHouseRoomById(sourceId);
            BasicHouseRoom target = basicHouseRoomService.getBasicHouseRoomById(targetId);
            BeanUtils.copyProperties(source, target, "id","name");
            basicHouseRoomService.saveAndUpdateBasicHouseRoom(target,false);

            return HttpResult.newCorrectResult(200, "success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoGenerate", method = {RequestMethod.POST}, name = "自动生成房间")
    public HttpResult autoGenerate(String huxingData,Integer houseId) {
        try {
            basicHouseRoomService.autoGenerate(huxingData,houseId);

            return HttpResult.newCorrectResult(200, "success!");
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    //-------------------||----------------------//

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseRoomDecorateById", method = {RequestMethod.GET}, name = "房间子类 获取")
    public HttpResult getBasicHouseRoomDecorateById(Integer id) {
        try {
            BasicHouseRoomDecorate basicHouseRoomDecorateById = basicHouseRoomDecorateService.getBasicHouseRoomDecorateById(id);
            return HttpResult.newCorrectResult(200, basicHouseRoomDecorateById);
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseRoomDecorate", method = {RequestMethod.POST}, name = "房间子类 更新或者新增")
    public HttpResult saveAndUpdateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) {
        try {
            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate,true);
            return HttpResult.newCorrectResult(200, basicHouseRoomDecorate.getRoomId());
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseRoomDecorate", method = {RequestMethod.POST}, name = "房间子类 删除")
    public HttpResult deleteBasicHouseRoomDecorate(Integer id) {
        try {
            BasicHouseRoomDecorate basicHouseRoomDecorate = basicHouseRoomDecorateService.getBasicHouseRoomDecorateById(id);
            basicHouseRoomDecorateService.deleteBasicHouseRoomDecorate(id);
            return HttpResult.newCorrectResult(200, basicHouseRoomDecorate.getRoomId());
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getRoomDecorateBootstrapTableVo", method = {RequestMethod.GET}, name = "房间子类 列表")
    public BootstrapTableVo getRoomDecorateBootstrapTableVo(BasicHouseRoomDecorate basicHouseRoomDecorate, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            BootstrapTableVo vo = basicHouseRoomDecorateService.getBootstrapTableVo(basicHouseRoomDecorate);
            return vo;
        } catch (Exception e) {
            logger.error(String.format("exception:%s", e.getMessage()), e);
            return null;
        }
    }


    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getRoomTypeList")
    public BootstrapTableVo getRoomTypeList(String name, Integer pid) {
        return basicHouseRoomService.getRoomTypeList(name, pid);
    }

}
