package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateMdIncomeSelfRunService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
@RequestMapping(value = "/netInfoRecordController")
public class NetInfoRecordController {
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    @Autowired
    private SchemeInfoDao schemeInfoDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netInfoRecord");
        String[] webTypeStr = new String[]{"公拍网", "淘宝司法拍卖网", "京东司法拍卖网", "京东资产拍卖网", "中国拍卖行业协会网-司法", "中国拍卖行业协会网-标的", "公共资源交易平台-雅安", "公共资源交易平台-成都"};
        List<String> webTypes = Arrays.asList(webTypeStr);
        modelAndView.addObject("webTypes", webTypes);
        String[] unitListStr = new String[]{"建筑面积", "土地面积", "生产能力"};
        List<String> unitList = Arrays.asList(unitListStr);
        modelAndView.addObject("unitList", unitList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoRecordList", name = "信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getInfoRecordList(String queryTitle, String queryWebName, String province, String city, String queryContent, String queryType, String queryStartTime, String queryEndTime) throws Exception {
        return netInfoRecordService.getInfoRecordList(queryTitle, queryWebName, province, city, queryContent, queryType, queryStartTime, queryEndTime);
    }

    @ResponseBody
    @RequestMapping(value = "/getOldData", name = "获取前两年数据", method = RequestMethod.POST)
    public HttpResult getOldData() {
        try {
            //netInfoRecordService.climbingData();
            SchemeInfo info = schemeInfoDao.getInfoById(582);
            GenerateMdIncomeSelfRunService generateMdIncomeSelfRunService = new GenerateMdIncomeSelfRunService(info, 382);
            generateMdIncomeSelfRunService.generateCompareFile();

        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getNetInfoRecordById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        NetInfoRecord netInfoRecord = null;
        try {
            if (id != null) {
                netInfoRecord = netInfoRecordDao.getInfoById(id);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(netInfoRecord);
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
}
