package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang.StringUtils;
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
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/index", name = "拍卖详细信息视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecordHouse");
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/houseList", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo houseList(String province, String city, String district) {
        NetInfoRecordHouse netInfoRecordHouse = new NetInfoRecordHouse();
        if (StringUtils.isNotEmpty(province)) netInfoRecordHouse.setProvince(province);
        if (StringUtils.isNotEmpty(city)) netInfoRecordHouse.setCity(city);
        if (StringUtils.isNotEmpty(district)) netInfoRecordHouse.setDistrict(district);
        netInfoRecordHouse.setStatus(1);
        BootstrapTableVo vo = netInfoRecordHouseService.getNetInfoRecordHouseListVos(netInfoRecordHouse);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveHouseDetail", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveHouseDetail(String formData) {
        NetInfoRecordHouse netInfoRecordHouse = JSON.parseObject(formData, NetInfoRecordHouse.class);
        try {
            netInfoRecordHouseService.saveAndUpdateNetInfoRecordHouse(netInfoRecordHouse);
            NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordHouse.getMasterId());
            record.setBelongType(netInfoRecordHouse.getType());
            record.setStatus(2);
            netInfoRecordDao.updateInfo(record);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            netInfoRecordHouseService.removeNetInfoRecordHouse(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
