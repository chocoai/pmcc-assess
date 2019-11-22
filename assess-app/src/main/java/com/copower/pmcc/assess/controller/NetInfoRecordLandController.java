package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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

    @RequestMapping(value = "/index", name = "拍卖详细信息视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecordLand");
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/landList", name = "取得土地信息", method = RequestMethod.GET)
    public BootstrapTableVo landList() {
        NetInfoRecordLand netInfoRecordLand = new NetInfoRecordLand();
        BootstrapTableVo vo = netInfoRecordLandService.getNetInfoRecordLandListVos(netInfoRecordLand);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveLandDetail", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveLandDetail(String formData) {
        NetInfoRecordLand netInfoRecordLand = JSON.parseObject(formData, NetInfoRecordLand.class);
        try {
            netInfoRecordLandService.saveAndUpdateNetInfoRecordLand(netInfoRecordLand);
            NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordLand.getMasterId());
            record.setBelongType(netInfoRecordLand.getType());
            record.setStatus(2);
            netInfoRecordDao.updateInfo(record);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除文号规则", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            netInfoRecordLandService.removeNetInfoRecordLand(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
