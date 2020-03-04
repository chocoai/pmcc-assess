package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
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


@RequestMapping(value = "/netInfoRecordLand")
@Controller
public class NetInfoRecordLandController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private NetInfoRecordLandService netInfoRecordLandService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/index", name = "拍卖详细信息视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecordLand");
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/landList", name = "取得土地信息", method = RequestMethod.GET)//belongType,belongCategory,dealType,negotiatedDateStart,negotiatedDateEnd
    public BootstrapTableVo landList(String province, String city, String district, String street, Integer belongType,String belongCategory,Integer dealType,String negotiatedDateStart,String negotiatedDateEnd) {
        BootstrapTableVo vo = netInfoRecordLandService.getNetInfoRecordLandListVos(1, province, city, district, street, belongType,belongCategory,dealType,negotiatedDateStart,negotiatedDateEnd);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getLandListByMasterId", name = "取得土地历史信息", method = RequestMethod.GET)
    public BootstrapTableVo getLandListByMasterId(Integer masterId) {
        BootstrapTableVo vo = netInfoRecordLandService.getLandListByMasterId(masterId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveLandDetail", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveLandDetail(String formData, boolean changeStatus) {
        NetInfoRecordLand netInfoRecordLand = JSON.parseObject(formData, NetInfoRecordLand.class);
        try {
            NetInfoRecordLand landData = netInfoRecordLandService.saveAndUpdateNetInfoRecordLand(netInfoRecordLand);
            List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(netInfoRecordLand);
            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
            List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);
            //改为已填写状态
            if (changeStatus) {
                NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordLand.getMasterId());
                record.setBelongType(netInfoRecordLand.getType());
                if(record.getStatus()==1||record.getStatus()==2)
                record.setStatus(2);
                netInfoRecordDao.updateInfo(record);
            }
            return HttpResult.newCorrectResult(netInfoRecordLandService.getNetInfoRecordLandVo(landData, attachmentDtos));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateLandStatus", method = {RequestMethod.POST}, name = "保存")
    public HttpResult updateLandStatus(Integer landId) {
        try {
            NetInfoRecordLand landData = netInfoRecordLandService.getNetInfoRecordLandById(landId);
            NetInfoRecord infoRecord = netInfoRecordDao.getInfoById(landData.getMasterId());
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
            netInfoRecordLandService.removeNetInfoRecordLand(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getDataById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getDataById(Integer id) {
        try {
            if (id != null) {
                NetInfoRecordLand recordLand = netInfoRecordLandService.getNetInfoRecordLandById(id);
                NetInfoRecordLandVo netInfoRecordLandVo = netInfoRecordLandService.getNetInfoRecordLandVo(recordLand, null);
                return HttpResult.newCorrectResult(netInfoRecordLandVo);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }

}
