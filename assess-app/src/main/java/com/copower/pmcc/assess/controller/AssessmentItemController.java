package com.copower.pmcc.assess.controller;

import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/21
 * @time: 14:35
 */
@Controller
@RequestMapping(value = "/AssessmentItem", name = "考核内容明细")
public class AssessmentItemController {
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/assessment/baseIndex");

        BootstrapTableVo boxReDtoList = bpmRpcBoxService.getBoxReDtoList("", "", 0, 1000);
        modelAndView.addObject("boxRe", (List<BoxReDto>) boxReDtoList.getRows());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBoxReActivityByBoxId", name = "根据模型编号取得节点信息", method = RequestMethod.GET)
    public HttpResult getBoxReActivityByBoxId(Integer boxId) {
        try {
            List<BoxReActivityDto> boxReActivityByBoxId = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
            if (CollectionUtils.isNotEmpty(boxReActivityByBoxId)) {
                return HttpResult.newCorrectResult(boxReActivityByBoxId);
            } else {
                return HttpResult.newCorrectResult(new ArrayList<BoxReActivityDto>());
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAssessmentItemList", name = "查询配置的考核模板数据", method = RequestMethod.GET)
    public BootstrapTableVo getAssessmentItemList(Integer boxId, Integer activityId, String des) {

        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        if (boxId < 0) {
            boxId = null;
        }
        if (activityId < 0) {
            activityId = null;
        }
        BootstrapTableVo assessmentItemAll = bpmRpcBoxService.getAssessmentItemAll(boxId, activityId, des, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        return assessmentItemAll;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAssessmentItem", name = "保存基础模板数据", method = RequestMethod.POST)
    public HttpResult saveAssessmentItem(AssessmentItemDto assessmentItemDto) {
        if (assessmentItemDto.getId() == 0) {
            //新增时
            assessmentItemDto.setBoxId(0);
            assessmentItemDto.setBoxReActivitiId(0);
        }
        bpmRpcBoxService.saveAssessmentItem(assessmentItemDto);
        return HttpResult.newCorrectResult();
    }
}
