package com.copower.pmcc.assess.service.assist;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.dto.output.BaseBussinessVo;
import com.copower.pmcc.assess.proxy.face.BaseInterface;
import com.copower.pmcc.assess.service.base.BaseProcessService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by kings on 2018-3-21.
 */
@Component
@WorkFlowAnnotation(desc = "通用模板")
public class FormConfigureAssist implements BaseInterface {
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseProcessService baseProcessService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    private String getTableName(Integer boxId) {
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        return baseProcessService.getProcessByBoxName(boxReDto.getName()).getTableName();
    }

    private String getTableName(String processInsId) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        return getTableName(boxRuDto.getBoxId());
    }

    @Override
    public ModelAndView applyView(Integer boxId, Integer phaseId, Integer ruId) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/form/formApply", "0", boxId, "0", "");
        Map<String, Object> primaryData = null;
        if (ruId != null) {

        }
        if (primaryData == null) {
            primaryData = Maps.newHashMap();
            primaryData.put("primaryId", 0);
            primaryData.put("phaseId", phaseId);
            primaryData.put("primaryTableName", getTableName(boxId));
        }
        modelAndView.addObject("primaryData", primaryData);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, Integer phaseId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/form/formApproval", processInsId, boxId, taskId, agentUserAccount);
        String tableName = getTableName(boxId);
        Map<String, Object> map = formConfigureService.getObjectSingle(tableName, processInsId);
        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", map.get("id"));
        primaryData.put("phaseId",phaseId);
        primaryData.put("primaryTableName",tableName);
        modelAndView.addObject("primaryData", primaryData);
        modelAndView.addObject("applyCreator", map.get("creator"));
        Map<String, KeyValueDto> tableJsonData = formConfigureService.getAllTableJsonString(phaseId, Integer.parseInt(map.get("id").toString()));
        modelAndView.addObject("tableJsonData", tableJsonData);//所有单表的json数据
        return modelAndView;
    }

    @Override
    public ModelAndView editView(String processInsId, Integer phaseId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/form/formApply", processInsId, boxId, taskId, agentUserAccount);
        String tableName = getTableName(boxId);
        Map<String, Object> map = formConfigureService.getObjectSingle(tableName, processInsId);
        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", map.get("id"));
        primaryData.put("phaseId", phaseId);
        primaryData.put("primaryTableName",tableName);
        modelAndView.addObject("primaryData", primaryData);
        modelAndView.addObject("applyCreator", map.get("creator"));
        Map<String, KeyValueDto> tableJsonData = formConfigureService.getAllTableJsonString(phaseId, Integer.parseInt(map.get("id").toString()));
        modelAndView.addObject("tableJsonData", tableJsonData);//所有单表的json数据
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(String processInsId, Integer boxId) {
        ModelAndView modelAndView = controllerComponent.baseFormModelAndView("/form/formApproval", processInsId, boxId, "-1", "");
        String tableName = getTableName(boxId);
        Map<String, Object> map = formConfigureService.getObjectSingle(tableName, processInsId);
        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", map.get("id"));
        primaryData.put("phaseId", map.get("phase_id"));
        primaryData.put("primaryTableName",tableName);
        modelAndView.addObject("primaryData", primaryData);
        modelAndView.addObject("applyCreator", map.get("creator"));
        Map<String, KeyValueDto> tableJsonData = formConfigureService.getAllTableJsonString(Integer.parseInt(map.get("phase_id").toString()), Integer.parseInt(map.get("id").toString()));
        modelAndView.addObject("tableJsonData", tableJsonData);//所有单表的json数据
        return modelAndView;
    }

    @Override
    public BaseBussinessVo saveDraft(String formData) throws BusinessException {
        
        return null;
    }

    @Override
    public BaseBussinessVo applyCommit(String formData, String processInsId) throws BusinessException, BpmException {
        FormConfigureDto formConfigureDto = JSONObject.parseObject(formData, FormConfigureDto.class);
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxRuDto.getBoxId());
        BaseProcess hrBaseProcess = baseProcessService.getProcessByBoxName(boxReDto.getName());
        if(hrBaseProcess!=null&& StringUtils.isNotBlank(hrBaseProcess.getExecutor())){
            bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, hrBaseProcess.getExecutor());//修改监听器
        }
        formConfigureDto.setProcessInsId(processInsId);
        Integer tableId = formConfigureService.saveData(formConfigureDto);
        return new BaseBussinessVo(tableId, hrBaseProcess.getTableName());
    }


    @Override
    public void approvalCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException {
        FormConfigureDto formConfigureDto = JSONObject.parseObject(formData, FormConfigureDto.class);
        formConfigureService.saveData(formConfigureDto);
    }

    @Override
    public void editCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException {
        FormConfigureDto formConfigureDto = JSONObject.parseObject(formData, FormConfigureDto.class);
        formConfigureService.saveData(formConfigureDto);
    }

    @Override
    public void closeCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException {

    }
}
