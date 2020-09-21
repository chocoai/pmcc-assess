package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 */
@Controller
@RequestMapping(value = "/netInfoAssignTask")
public class NetInfoAssignTaskController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    @Autowired
    private NetInfoAssignTaskService netInfoAssignTaskService;
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private AssessmentCommonService assessmentCommonService;

    @RequestMapping(value = "/apply", name = "拍卖信息补充申请")
    public ModelAndView apply(String ids) throws BusinessException {
        NetInfoAssignTask netInfoAssignTask = new NetInfoAssignTask();
        netInfoAssignTask.setNetInfoIds(ids);
        netInfoAssignTask.setCreator(commonService.thisUserAccount());
        netInfoAssignTask.setSource("net");//网络
        netInfoAssignTaskService.addNetInfoAssignTask(netInfoAssignTask);
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.NET_INFO_COMPLEMENT_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApply", boxReDto.getId());
        modelAndView.addObject("netInfoAssignTask", netInfoAssignTask);
        return modelAndView;
    }

    @RequestMapping(value = "/applyOffline", name = "线下案例申请")
    public ModelAndView applyOffline() throws BusinessException {
        NetInfoAssignTask netInfoAssignTask = netInfoAssignTaskService.getNetInfoAssignTaskBySource("offline");
        if (netInfoAssignTask == null) {
            netInfoAssignTask = new NetInfoAssignTask();
            netInfoAssignTask.setCreator(commonService.thisUserAccount());
            netInfoAssignTask.setSource("offline");//线下
            netInfoAssignTaskService.addNetInfoAssignTask(netInfoAssignTask);
        }
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.NET_INFO_COMPLEMENT_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApplyOffline", boxReDto.getId());
        modelAndView.addObject("netInfoAssignTask", netInfoAssignTask);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "拍卖信息补充申请提交")
    public HttpResult applyCommit(Integer id) {
        try {
            netInfoAssignTaskService.applyCommit(id, BaseParameterEnum.NET_INFO_COMPLEMENT_PROCESS_KEY);
        } catch (BusinessException e) {
            logger.error("修改项目信息异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "拍卖信息补充审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApproval", processInsId, boxId, taskId, agentUserAccount);
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("netInfoAssignTask", data);
        //创建考核任务
        assessmentCommonService.generateAssessmentTask(processInsId,boxId,taskId,null,null);
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
            netInfoAssignTaskService.approvalCommit(approvalModelDto, processInsId);

            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("提交失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        String viewUrl = "net/netInfoAssignTaskApply";
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processInsId);
        if ("offline".equals(data.getSource())) {
            viewUrl = "net/netInfoAssignTaskApplyOffline";
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(viewUrl, processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("netInfoAssignTask", data);
        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(Integer assignTaskId, ApprovalModelDto approvalModelDto) {
        try {
            NetInfoAssignTask netInfoAssignTask = netInfoAssignTaskService.getDataById(assignTaskId);
            if(StringUtils.isNotBlank(netInfoAssignTask.getNetInfoIds())){
                List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(netInfoAssignTask.getNetInfoIds()));
                List<NetInfoRecord> infoRecords = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));
                if (CollectionUtils.isNotEmpty(infoRecords)) {
                    for (NetInfoRecord netInfo : infoRecords) {
                        netInfo.setStatus(3);
                        netInfoRecordService.updateInfo(netInfo);
                    }
                }
            }
            netInfoAssignTaskService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/landList", name = "取得土地信息", method = RequestMethod.GET)
    public BootstrapTableVo landList(String ids) {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        return netInfoAssignTaskService.getNetInfoRecordLandList(integers);
    }

    @ResponseBody
    @RequestMapping(value = "/houseList", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo houseList(String ids) {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        return netInfoAssignTaskService.getNetInfoRecordHouseList(integers);
    }

    @ResponseBody
    @RequestMapping(value = "/getLandListByAssignTaskId", name = "取得土地信息", method = RequestMethod.GET)
    public BootstrapTableVo getLandListByAssignTaskId(Integer assignTaskId) {
        return netInfoAssignTaskService.getLandListByAssignTaskId(assignTaskId);
    }

    @ResponseBody
    @RequestMapping(value = "/getHouseListByAssignTaskId", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo getHouseListByAssignTaskId(Integer assignTaskId) {
        return netInfoAssignTaskService.getHouseListByAssignTaskId(assignTaskId);
    }


    @ResponseBody
    @RequestMapping(value = "/backTask", name = "任务退回", method = RequestMethod.POST)
    public HttpResult backTask(String ids) {
        try {
            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
            if (CollectionUtils.isNotEmpty(integers)) {
                for (Integer id : integers) {
                    NetInfoRecord infoRecord = netInfoRecordDao.getInfoById(id);
                    infoRecord.setStatus(0);
                    infoRecord.setExecutor("");
                    netInfoRecordService.updateInfo(infoRecord);
                }
                //删除子数据
                List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByMasterIds(integers);
                if (CollectionUtils.isNotEmpty(netInfoRecordHouses)) {
                    netInfoRecordHouses.forEach(o -> {
                        netInfoRecordHouseDao.deleteInfo(o.getId());
                    });
                }
                List<NetInfoRecordLand> netInfoRecordLandList = netInfoRecordLandDao.getLandListByMasterIds(integers);
                if (CollectionUtils.isNotEmpty(netInfoRecordLandList)) {
                    netInfoRecordLandList.forEach(o -> {
                        netInfoRecordLandDao.deleteInfo(o.getId());
                    });
                }
            }
        } catch (Exception e) {
            logger.error("任务退回失败", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/importAssignHouseData", name = "导入房产数据", method = RequestMethod.POST)
    public HttpResult importAssignHouseData(HttpServletRequest request, NetInfoRecordHouse house) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = netInfoRecordService.importAssignHouseData(house, multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/importAssignLandData", name = "导入土地数据", method = RequestMethod.POST)
    public HttpResult importAssignLandData(HttpServletRequest request, NetInfoRecordLand land) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = netInfoRecordService.importAssignLandData(land, multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }
}
