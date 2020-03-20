package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-10-24.
 * 案例基础数据
 */
@Controller
@RequestMapping("/basicApply")
public class BasicApplyController extends BaseController {
    @Autowired
    private BasicApplyService basicApplyService;

    @ResponseBody
    @RequestMapping(value = "/getBasicApplyById", name = "根据id获取", method = RequestMethod.GET)
    public HttpResult getBasicApplyById(Integer id) {
        BasicApply basicApply = basicApplyService.getByBasicApplyId(id);
        return HttpResult.newCorrectResult(basicApply);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBasicApply", name = "获取案列或查勘对应basicApply", method = RequestMethod.GET)
    public HttpResult getCaseBasicApplyId(Integer planDetailsId) {
        try {
            return HttpResult.newCorrectResult(basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId));
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

}
