package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping(value = "/netInfoRecordHouse")
@Controller
public class NetInfoRecordHouseController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private NetInfoRecordHouseService netInfoRecordHouseService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/index", name = "拍卖详细信息视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecordHouse");
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/houseList", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo houseList(String province, String city, String district, String street, String name) {
        BootstrapTableVo vo = netInfoRecordHouseService.getNetInfoRecordHouseListVos(1, province, city, district, street, name);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveHouseDetail", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveHouseDetail(String formData, Boolean changeStatus) {
        NetInfoRecordHouse netInfoRecordHouse = JSON.parseObject(formData, NetInfoRecordHouse.class);
        try {
            NetInfoRecordHouse houseData = netInfoRecordHouseService.saveAndUpdateNetInfoRecordHouse(netInfoRecordHouse);
            List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
            List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);
            //改为已填写状态
            if (changeStatus != null) {
                NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordHouse.getMasterId());
                if(record!=null){
                    record.setBelongType(netInfoRecordHouse.getType());
                    if (record.getStatus() == 1 || record.getStatus() == 2)
                        record.setStatus(2);
                    netInfoRecordService.updateInfo(record);
                }
            }
            return HttpResult.newCorrectResult(netInfoRecordHouseService.getNetInfoRecordHouseVo(houseData, attachmentDtos));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateHouseStatus", method = {RequestMethod.POST}, name = "保存")
    public HttpResult updateHouseStatus(Integer houseId) {
        try {
            NetInfoRecordHouse recordHouse = netInfoRecordHouseService.getNetInfoRecordHouseById(houseId);
            NetInfoRecord infoRecord = netInfoRecordDao.getInfoById(recordHouse.getMasterId());
            return HttpResult.newCorrectResult(infoRecord);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            netInfoRecordHouseService.removeNetInfoRecordHouse(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/getHouseListByMasterId", name = "取得房产历史信息", method = RequestMethod.GET)
    public BootstrapTableVo getHouseListByMasterId(Integer masterId) {
        BootstrapTableVo vo = netInfoRecordHouseService.getHouseListByMasterId(masterId);
        return vo;
    }


    @ResponseBody
    @RequestMapping(value = "/getDataById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getDataById(Integer id) {
        try {
            if (id != null) {
                NetInfoRecordHouse recordHouse = netInfoRecordHouseService.getNetInfoRecordHouseById(id);
                return HttpResult.newCorrectResult(recordHouse);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }
}
