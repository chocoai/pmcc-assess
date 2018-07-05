package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseForm;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.basis.entity.BaseProcess;
import com.copower.pmcc.assess.dal.basis.entity.BaseProcessForm;
import com.copower.pmcc.assess.dto.output.base.BaseProcessFormModelVo;
import com.copower.pmcc.assess.service.base.BaseFormService;
import com.copower.pmcc.assess.service.base.BaseProcessService;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 15:40
 */
@Controller
@RequestMapping(value = "/baseProcess")
public class BaseProcessController {
    @Autowired
    private BaseProcessService hrBaseProcessService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseFormService hrBaseFormService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;

    @RequestMapping(value = "/processIndex", name = "流程设置首页")
    public ModelAndView processIndex() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/processIndex");

        List<BaseForm> hrBaseForm = hrBaseFormService.getBaseFormList();
        modelAndView.addObject("hrBaseForm", hrBaseForm);

        BootstrapTableVo boxReDtoList = bpmRpcBoxService.getBoxReDtoList("", BaseConstant.ASSESS_BOX_RE_GROUP_KEY, 0, 100);
        modelAndView.addObject("boxRe", (List<BoxReDto>) boxReDtoList.getRows());
        return modelAndView;
    }

    //////////////////////////////////////////////////
    @ResponseBody
    @RequestMapping(value = "/saveBaseProcess", name = "保存流程", method = RequestMethod.POST)
    public HttpResult saveBaseProcess(BaseProcess hrBaseProcess) {
        try {
            hrBaseProcessService.saveBaseProcess(hrBaseProcess);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBaseProcess", name = "删除流程", method = RequestMethod.POST)
    public HttpResult deleteBaseProcess(Integer id) {
        try {
            hrBaseProcessService.deleteBaseProcess(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseProcessList", name = "取得流程列表", method = RequestMethod.GET)
    public BootstrapTableVo getBaseProcessList() {
        return hrBaseProcessService.getBaseProcessList();
    }

    ////////////////////////////////////////////////////////////////////////////////
    @ResponseBody
    @RequestMapping(value = "/saveBaseProcessForm", name = "保存流程业务表单", method = RequestMethod.POST)
    public HttpResult saveBaseProcessForm(BaseProcessForm hrBaseProcess) {
        try {
            hrBaseProcessService.saveBaseProcessForm(hrBaseProcess);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseProcessFormList", name = "取得业务表单列表", method = RequestMethod.GET)
    public BootstrapTableVo getBaseProcessFormList(Integer processId) {
        return hrBaseProcessService.getBaseProcessFormList(processId);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBaseProcessForm", name = "删除流程表单", method = RequestMethod.POST)
    public HttpResult deleteBaseProcessForm(Integer id) {
        try {
            hrBaseProcessService.deleteBaseProcessForm(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @ResponseBody
    @RequestMapping(value = "/getFormListByProcess", name = "取得所有的表单数据", method = RequestMethod.GET)
    public HttpResult getFormListByProcess(Integer processId) {

        BaseProcess hrBaseProcess = hrBaseProcessService.getProcessById(processId);

        List<BaseFormModule> hrbaseFormList = hrBaseFormService.getBaseFormModuleList(hrBaseProcess.getId());

        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(hrBaseProcess.getBoxName());
        List<BoxReActivityDto> boxReActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByActivityNameSorting(boxId, ProcessActivityEnum.START.getValue());
        boxReActivityDtos.add(boxReActivityDto);
        BaseProcessFormModelVo hrBaseProcessFormModelVo = new BaseProcessFormModelVo();
        hrBaseProcessFormModelVo.setBaseFormModules(hrbaseFormList);
        hrBaseProcessFormModelVo.setBoxReActivityDtoList(boxReActivityDtos);

        return HttpResult.newCorrectResult(hrBaseProcessFormModelVo);

    }

    @ResponseBody
    @RequestMapping(value = "/getBoxReActivityList", name = "取得节点数据", method = RequestMethod.GET)
    public HttpResult getBoxReActivityList(Integer processId) {
        BaseProcess hrBaseProcess = hrBaseProcessService.getProcessById(processId);
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(hrBaseProcess.getBoxName());
        List<BoxReActivityDto> boxReActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByActivityNameSorting(boxId, ProcessActivityEnum.START.getValue());
        boxReActivityDtos.add(boxReActivityDto);
        return HttpResult.newCorrectResult(boxReActivityDtos);

    }
}
