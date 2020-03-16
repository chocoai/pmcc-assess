package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgrade;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.NetInfoUpgradeService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 */
@Controller
@RequestMapping(value = "/netInfoUpgrade")
public class NetInfoUpgradeController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    @Autowired
    private NetInfoUpgradeService netInfoUpgradeService;
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;

    @RequestMapping(value = "/apply", name = "拍卖信息升级申请")
    public ModelAndView apply(Integer dataId,String type,Integer masterDataId) throws BusinessException {
        netInfoUpgradeService.cleanInvalidData(dataId,type);
        NetInfoUpgrade netInfoUpgrade = new NetInfoUpgrade();
        netInfoUpgrade.setDataId(dataId);
        netInfoUpgrade.setType(type);
        netInfoUpgrade.setMasterDataId(masterDataId);
        netInfoUpgrade.setCreator(commonService.thisUserAccount());
        netInfoUpgradeService.addNetInfoUpgrade(netInfoUpgrade);
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.NET_INFO_UPGRADE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoUpgradeApply", boxReDto.getId());
        modelAndView.addObject("netInfoUpgrade", netInfoUpgrade);

        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "拍卖信息升级申请提交")
    public HttpResult applyCommit(Integer id) {
        try {
            netInfoUpgradeService.applyCommit(id,BaseParameterEnum.NET_INFO_UPGRADE_PROCESS_KEY);
            return HttpResult.newCorrectResult();
        }  catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/approvalView", name = "拍卖信息升级审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoUpgradeApproval", processInsId, boxId, taskId, agentUserAccount);
        NetInfoUpgrade data = netInfoUpgradeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("netInfoUpgrade", data);
        return modelAndView;
    }

    @RequestMapping(value = "/detailView", name = "详情页", method = RequestMethod.GET)
    public ModelAndView detailsView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto, String processInsId) {
        try {
            netInfoUpgradeService.approvalCommit(approvalModelDto, processInsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoUpgradeApply", processInsId, boxId, taskId, agentUserAccount);
        NetInfoUpgrade data = netInfoUpgradeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("netInfoUpgrade", data);
        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(String businessDataJson, ApprovalModelDto approvalModelDto) {
        try {
            netInfoUpgradeService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/landList", name = "取得土地信息", method = RequestMethod.GET)
    public BootstrapTableVo landList(Integer id,Integer applyId) {
        return netInfoUpgradeService.getNetInfoRecordLandList(id,applyId);
    }

    @ResponseBody
    @RequestMapping(value = "/houseList", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo houseList(Integer id,Integer applyId) {
        return netInfoUpgradeService.getNetInfoRecordHouseList(id,applyId);
    }

    @RequestMapping(value = "/saveJsonData", name = "保存jsonData数据", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult saveJsonData(String jsonData,Integer id) {
        try {
            NetInfoUpgrade upgrade = netInfoUpgradeService.getDataById(id);
            upgrade.setJsonData(jsonData);
            netInfoUpgradeService.editData(upgrade);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

}
