package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 17:30
 */
@Controller
@RequestMapping(value = "/netInfoRecordMyTaskController")
public class NetInfoRecordMyTaskController {
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private NetInfoRecordHouseService netInfoRecordHouseService;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    @Autowired
    private NetInfoRecordLandService netInfoRecordLandService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecordMyTask");
        String[] webTypeStr = new String[]{"公拍网", "淘宝司法拍卖网", "京东司法拍卖网", "京东资产拍卖网", "中国拍卖行业协会网-司法", "中国拍卖行业协会网-标的", "公共资源交易平台-雅安",
                "公共资源交易平台-成都", "公共资源交易平台-凉山州", "公共资源交易平台-攀枝花", "土流网", "农村产权交易中心"};
        List<String> webTypes = Arrays.asList(webTypeStr);
        modelAndView.addObject("webTypes", webTypes);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoRecordList", name = "信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getInfoRecordList(String queryTitle, String queryWebName, String province, String city, String queryContent, String queryType, String queryStartTime, String queryEndTime) throws Exception {
        String executor = commonService.thisUserAccount();
        return netInfoRecordService.getInfoRecordList(queryTitle, queryWebName, province, city, queryContent, queryType, queryStartTime, queryEndTime, executor, null);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailByMasterId", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getDetailByMasterId(Integer id) {
        try {
            if (id != null) {
                NetInfoRecord netInfoRecord = netInfoRecordDao.getInfoById(id);
                if (StringUtils.equals(netInfoRecord.getBelongType(), "房产")) {
                    NetInfoRecordHouse house = new NetInfoRecordHouse();
                    house.setMasterId(id);
                    List<NetInfoRecordHouseVo> vos = netInfoRecordHouseService.getVos(house);
                    if (CollectionUtils.isNotEmpty(vos)) {
                        return HttpResult.newCorrectResult(vos.get(vos.size() - 1));
                    }
                    return HttpResult.newCorrectResult(new NetInfoRecordHouse());
                }
                if (StringUtils.equals(netInfoRecord.getBelongType(), "土地")) {
                    NetInfoRecordLand land = new NetInfoRecordLand();
                    land.setMasterId(id);
                    List<NetInfoRecordLandVo> vos = netInfoRecordLandService.getVos(land);
                    if (CollectionUtils.isNotEmpty(vos)) {
                        return HttpResult.newCorrectResult(vos.get(vos.size() - 1));
                    }
                    return HttpResult.newCorrectResult(new NetInfoRecordLand());
                }
                NetInfoRecordLandVo data = new NetInfoRecordLandVo();
                data.setProvinceName(netInfoRecord.getProvince());
                data.setCityName(netInfoRecord.getCity());
                return HttpResult.newCorrectResult(data);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/updateNetInfoRecord", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(String formData) {
        NetInfoRecord netInfoRecord = JSON.parseObject(formData, NetInfoRecord.class);
        try {
            if (netInfoRecord.getId() != null && !netInfoRecord.getId().equals(0)) {
                netInfoRecordDao.updateInfo(netInfoRecord);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/assignTask", name = "分派任务", method = {RequestMethod.POST})
    public HttpResult assignTask(String executor, String ids) {
        try {
            netInfoRecordService.assignTask(executor, ids);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoRecordListByIds", name = "信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getInfoRecordListByIds(String ids) throws Exception {
        return netInfoRecordService.getInfoRecordListByIds(ids);
    }
}
