package com.copower.pmcc.assess.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.QueryProjectInfo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.data.DataValueDefinitionService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/25
 * @time: 14:12
 */
@Controller
@RequestMapping(value = "/mobileProjectInfo", name = "移动端项目控制器")
public class MobileProjectInfoController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/projectIndex", name = "项目列表", method = RequestMethod.GET)
    public ModelAndView projectIndex() {
        ModelAndView modelAndView = commonService.baseView("mobile/projectList");
        return modelAndView;
    }

    @RequestMapping(value = "/projectInfo", name = "项目信息", method = RequestMethod.GET)
    public ModelAndView projectInfo() {
        ModelAndView modelAndView = commonService.baseView("mobile/projectInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/projectSurvey", name = "项目查勘", method = RequestMethod.GET)
    public ModelAndView projectSurvey() {
        ModelAndView modelAndView = commonService.baseView("mobile/projectSurvey");
        return modelAndView;
    }

    @RequestMapping(value = "/projectCase", name = "项目案例", method = RequestMethod.GET)
    public ModelAndView projectCase() {
        ModelAndView modelAndView = commonService.baseView("mobile/projectCase");
        return modelAndView;
    }

    @RequestMapping(value = "/projectInventory", name = "项目清查", method = RequestMethod.GET)
    public ModelAndView projectInventory() {
        ModelAndView modelAndView = commonService.baseView("mobile/projectInventory");
        return modelAndView;
    }
}
