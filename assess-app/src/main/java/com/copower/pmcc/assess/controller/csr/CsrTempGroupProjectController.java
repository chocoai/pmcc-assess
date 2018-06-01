package com.copower.pmcc.assess.controller.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoGroupService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/csrTempGroupProject",name = "项目组临时 控制器")
@Controller
public class CsrTempGroupProjectController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private CsrProjectInfoGroupService projectInfoGroupService;

    @Autowired
    private CsrBorrowerService service;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/csrTempGroupProject");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method ={ RequestMethod.GET})
    public BootstrapTableVo list() {
        BootstrapTableVo vo = service.borrowerLists();
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdate", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult add(CsrProjectInfoGroup csrProjectInfoGroup) {
        try {
            if (!ObjectUtils.isEmpty(csrProjectInfoGroup) && ObjectUtils.isEmpty(csrProjectInfoGroup.getId())){
                projectInfoGroupService.add(csrProjectInfoGroup);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


}
