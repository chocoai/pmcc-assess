package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoom;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseHouseRoomDecorateService;
import com.copower.pmcc.assess.service.cases.CaseHouseRoomService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:48
 * @Description:
 */
@RequestMapping(value = "/caseHouseRoom")
@Controller
public class CaseHouseRoomController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseRoomById",method = {RequestMethod.GET},name = "获取房间 (父类)")
    public HttpResult getById(Integer id) {
        CaseHouseRoom caseHouseRoom = null;
        try {
            if (id!=null){
                caseHouseRoom = caseHouseRoomService.getCaseHouseRoomById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouseRoom);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseRoomList",method = {RequestMethod.GET},name = "获取房间列表 (父类)")
    public BootstrapTableVo getCaseHouseRoomList(Integer houseId) {
        BootstrapTableVo vo = null;
        try {
            CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
            if (!ObjectUtils.isEmpty(houseId)){
                caseHouseRoom.setHouseId(houseId);
            }
            vo = caseHouseRoomService.getCaseHouseRoomLists(caseHouseRoom);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseRoomById",method = {RequestMethod.POST},name = "删除房间 (父类)")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseHouseRoomService.deleteCaseHouseRoom(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouseRoom",method = {RequestMethod.POST},name = "更新房间 (父类)")
    public HttpResult save(CaseHouseRoom caseHouseRoom){
        try {
            if (caseHouseRoom.getId()==null || caseHouseRoom.getId().equals(0)){
                caseHouseRoomService.addCaseHouseRoom(caseHouseRoom);
            }else {
                caseHouseRoomService.updateCaseHouseRoom(caseHouseRoom);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }



    /*----------------------------------------------------------分割一下  子类方法-----------------------------------------------------------*/

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseRoomDecorateLists",method = {RequestMethod.GET},name = "获取房间装修列表 (子类)")
    public BootstrapTableVo getCaseHouseRoomDecorateLists(Integer roomId) {
        BootstrapTableVo vo = null;
        try {
            CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
            if (roomId != null){
                caseHouseRoomDecorate.setRoomId(roomId);
            }
            vo = caseHouseRoomDecorateService.getCaseHouseRoomDecorateLists(caseHouseRoomDecorate);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouseRoomDecorate",method = {RequestMethod.POST},name = "更新房间装修 (子类)")
    public HttpResult saveCaseHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate){
        try {
            if (caseHouseRoomDecorate.getId()==null || caseHouseRoomDecorate.getId().equals(0)){
                caseHouseRoomDecorateService.addCaseHouseRoomDecorate(caseHouseRoomDecorate);
            }else {
                caseHouseRoomDecorateService.updateCaseHouseRoomDecorate(caseHouseRoomDecorate);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseRoomDecorateById",method = {RequestMethod.POST},name = "删除房间装修 (子类)")
    public HttpResult deleteCaseHouseRoomDecorate(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseHouseRoomDecorateService.deleteCaseHouseRoomDecorate(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

}
